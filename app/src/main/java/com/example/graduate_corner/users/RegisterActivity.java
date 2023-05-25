package com.example.graduate_corner.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduate_corner.MainDashboardActivity;
import com.example.graduate_corner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    //Variable Declarations
    EditText full_name, email, institution, cellphone,password;
    Button registerbtn;
    TextView login;

    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        // Bind views to Variables

        full_name = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        institution = findViewById(R.id.institution);
        cellphone = findViewById(R.id.cellphone);
        password = findViewById(R.id.password);

        login = findViewById(R.id.txt_login);
        registerbtn = findViewById(R.id.register_btn);

        //Create a Firebase Instance

        auth = FirebaseAuth.getInstance();

        //Redirect to the Login Page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });

        //Pass the Data into a separate Registration Method
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setTitle("Registering");
                pd.setMessage("Please wait...");
                pd.show();
                //Convert all the edit text values into simple string characters to be passed into the database
                String str_full_name = full_name.getText().toString();
                String str_email = email.getText().toString();
                String str_institution = institution.getText().toString();
                String str_cellphone = cellphone.getText().toString();
                String str_password = password.getText().toString();

                //Verify all fields are entered
                if (TextUtils.isEmpty(str_full_name)  || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_institution) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_cellphone)) {

                    Toast.makeText(RegisterActivity.this, "All fields are needed", Toast.LENGTH_SHORT).show();

                }

                //Verify the password isn't less than 8
                else if (str_password.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Password can't be less than 8 Characters", Toast.LENGTH_SHORT).show();
                }

                else {
                    register(str_full_name, str_email, str_institution, str_cellphone, str_password);
                }

            }
        });

    }

    /**
     *
     * @param str_full_name
     * @param str_email
     * @param str_institution
     * @param str_password
     * @param str_cellphone
     * Ensure yu pass all the above methods into the database
     *
     */
    private void register(String str_full_name, String str_email, String str_institution,String str_password, String str_cellphone){

        //Use the Firebase Authentication Instance to invoke a built in method for custom authentication with email and password
        auth.createUserWithEmailAndPassword(str_email, str_password).addOnCompleteListener(RegisterActivity.this, task -> {

            if (task.isSuccessful()){
                //Create a new Variable to hold the value of the Newly Registered User in Firebase RTDB
                FirebaseUser firebaseUser = auth.getCurrentUser();
                String userId = firebaseUser.getUid();
                //Create a Database Reference Telling Firebase how to store your new user
                ref = FirebaseDatabase.getInstance().getReference().child("Users").child("Software").child("Namibia").child(userId);

                //Use a Key-Value Pair data structure such as a HashMap to store the database values
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", userId);
                hashMap.put("Full_Name", str_full_name);
                hashMap.put("Email",str_email);
                hashMap.put("Password", str_password);
                hashMap.put("Institution",str_institution);
                hashMap.put("Cellphone",str_cellphone);

                //Use your Database Reference to now push the new user details into the database
                ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            Intent intent = new Intent(RegisterActivity.this, MainDashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }
                });
            }

            else {
                Toast.makeText(RegisterActivity.this, "Something is wrong with this email address.", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
            pd.dismiss();

        });

    }



}