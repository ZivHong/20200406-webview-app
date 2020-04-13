package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    EditText editurl;
    Button gopage_btn;
    ProgressBar progressWeb;
    ImageButton prev_page_btn,forw_page_btn;
    String defaulturl="https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editurl = findViewById(R.id.edit_url);
        gopage_btn = findViewById(R.id.go_btn);
        webView = findViewById(R.id.web1);
        prev_page_btn = findViewById(R.id.previous_btn);
        forw_page_btn = findViewById(R.id.forward_btn);
        progressWeb  = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar);
//載入預設網址(google)
        webView.loadUrl(defaulturl);

        gopage_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int status =(Integer) v.getTag();
                //在網頁title跟輸入網址中做切換
                if(status == 1) {//載入網頁
                    String url = editurl.getText().toString();
                    webView.loadUrl(url);
                    gopage_btn.setText("enter");
                    v.setTag(0);
                } else {//切換成title，且設定不可輸入
                    gopage_btn.setText("go");
                    v.setTag(1);
                    editurl.setFocusableInTouchMode(true);
                }
            }
        });
        //前一頁
        prev_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack()){
                    webView.goBack();
                }
            }
        });
        //後一頁
        forw_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressWeb.setVisibility(View.VISIBLE);
                progressWeb.setProgress(newProgress);
                //設定進度條
                if(newProgress==100){
                    progressWeb.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //載入網頁結束時，設定網址列為html title
                gopage_btn.setText("enter");
                gopage_btn.setTag(0);
                editurl.setFocusable(false);
                editurl.setText(view.getTitle());
            }
        });
    }
    //允許實體按鍵返回前一頁
    @Override
    public void onBackPressed(){
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    //防止webview crach
    @Override
    protected void onDestroy() {
        if (webView != null)
            webView.destroy();
        super.onDestroy();
    }
}




