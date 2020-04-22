package com.example.myapp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    WebView webView;
    ImageButton prev_page_btn,forw_page_btn;
    String defaulturl="https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prev_page_btn = findViewById(R.id.previous_btn);
        forw_page_btn = findViewById(R.id.forward_btn);
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web1);
        //設定toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //設定webview
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //載入預設網址(google)
        webView.loadUrl(defaulturl);
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
        //TODO 儲存登入資訊

    }
    //設定toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //TODO 選單功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.user_login:
                Toast.makeText(this, "登入", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_logout:
                Toast.makeText(this, "登出", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_program:
                ShowMsgDialog("關於程式","Author : Hong Ju Dong");
                break;
        }
        return super.onOptionsItemSelected(item);
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
    //跳出對話方塊
    private void ShowMsgDialog(String title,String Msg)
    {
        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle(title);
        MyAlertDialog.setMessage(Msg);
        DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
        //如果不做任何事情 就會直接關閉 對話方塊
            }
        };;
        MyAlertDialog.setNeutralButton("關閉",OkClick );
        MyAlertDialog.show();
    }
    //防止webview crach
    @Override
    protected void onDestroy() {
        if (webView != null)
            webView.destroy();
        super.onDestroy();
    }
}




