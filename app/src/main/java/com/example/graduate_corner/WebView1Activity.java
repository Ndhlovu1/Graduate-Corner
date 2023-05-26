package com.example.graduate_corner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebView1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_view1);

        WebView webView = findViewById(R.id.web_view);
        //Webview client
        String url = getIntent().getStringExtra("pdf_url");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);

    }
}