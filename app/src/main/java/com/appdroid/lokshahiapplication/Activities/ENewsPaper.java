package com.appdroid.lokshahiapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.appdroid.lokshahiapplication.R;

public class ENewsPaper extends AppCompatActivity {
    private WebView WebViewNews;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enews_paper);
        WebViewNews = (WebView)findViewById(R.id.WebViewNews);

        WebViewNews.setVisibility(View.GONE);
        WebSettings webSettings=WebViewNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewNews.loadUrl("http://epaper.lokshahilive.com/#Page/1");

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                WebViewNews.setVisibility(View.VISIBLE);
            }
        },1000);
    }
}