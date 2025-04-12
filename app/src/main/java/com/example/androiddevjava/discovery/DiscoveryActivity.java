package com.example.androiddevjava.discovery;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.androiddevjava.R;
import com.example.androiddevjava.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DiscoveryActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        long userId = getIntent().getLongExtra("userLongValue", -1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Fragment firstFragment = FirstFragment.newInstance(userId);
        Fragment secondFragment = new SecondFragment();
        Fragment thirdFragment = new ThirdFragment();

        setCurrentFragment(firstFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home) { setCurrentFragment(firstFragment); }
            if(item.getItemId() == R.id.profile) { webView.loadUrl("https://www.google.com/"); }
            if (item.getItemId() == R.id.settings) { webView.loadUrl("https://www.perplexity.ai/"); }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }
}