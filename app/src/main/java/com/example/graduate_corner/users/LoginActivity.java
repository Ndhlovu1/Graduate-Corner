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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //Declare variable types that are/have been used in the views
    EditText email, password;
    Button login;
    TextView signup;
    //Create a Firebase Authentication Instance
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Match the variables to their respective views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.txt_signup);

        //Get the authenticated instance of the database, assigned by default
        auth = FirebaseAuth.getInstance();

        //Direct the User to the Sign Up activity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //Perform the session and authentication creation
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a Static Dialog Box for the User
                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setTitle("Signing In");
                pd.setMessage("Please Wait...");
                pd.show();

                //Convert the values in the Edit-Text View into simple string characters
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                //Verify that the Password and the Email Fields are not empty
                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)){

                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                }

                else {
                    auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Create a Database reference session and redirect the user to the MainDashboard
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("Namibia").child(auth.getCurrentUser().getUid());

                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        pd.dismiss();
                                        Intent intent= new Intent(LoginActivity.this, MainDashboardActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                                    }

                                    //Should the user cancel the logging in before it is complete then we can simply dismiss our progress dialog
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        pd.dismiss();

                                    }
                                });

                            }

                            //Geneic Mess
                            else {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "Error signing In\n\tSomething Went Wrong!\nPlease Try Again Later.", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }

            }
        });




    }
}