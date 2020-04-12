package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    EditText mEdtUrl;
    Button mBtnGo;
    ProgressBar progressWeb;
    String defaulturl="https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtUrl = findViewById(R.id.edit_url);
        mBtnGo = findViewById(R.id.go_btn);
        webView = findViewById(R.id.web1);
        progressWeb  = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.loadUrl(defaulturl);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        View.OnClickListener btnClickLis =
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String url = mEdtUrl.getText().toString();
                        webView.loadUrl(url);
                    }
                };
        mBtnGo.setOnClickListener(btnClickLis);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressWeb.setVisibility(View.VISIBLE);
                progressWeb.setProgress(newProgress);
                if(newProgress==100){
                    progressWeb.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                toolbar.setTitle(view.getTitle());
            }
        });
    }
    @Override
    public void onBackPressed(){
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}




