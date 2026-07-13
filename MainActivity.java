package com.example.yourapp;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mainWebView = findViewById(R.id.main_webview);
        WebView topWebView = findViewById(R.id.top_webview);

        // বড় ওয়েব ভিউতে আপনার মেইন সাইটের লিংক দিন
        setupWebView(mainWebView, "https://jaya9bangladesh.com");
        
        // ছোট ওয়েব ভিউতে গুগলের বা আপনার অন্য লিংকটি দিন
        setupWebView(topWebView, "https://www.google.com");
    }

    private void setupWebView(WebView webView, String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // জাভাস্ক্রিপ্ট অন
        settings.setDomStorageEnabled(true); // লোকাল স্টোরেজ অন
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 💥 সিকিউরিটি লক বাইপাস করার মূল ট্রিক (আসল ক্রোম ব্রাউজারের ইউজার এজেন্ট)
        String chromeUserAgent = "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36";
        settings.setUserAgentString(chromeUserAgent);

        // থার্ড-পার্টি কুকি ও ডাটা লোড এক্সেপ্ট করা
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

        // লিংকগুলো যেন বাইরের কোনো ব্রাউজারে চলে না গিয়ে অ্যাপের ভেতরেই খোলে
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(url);
    }
}
