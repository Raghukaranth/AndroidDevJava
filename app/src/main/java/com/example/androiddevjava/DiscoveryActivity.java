package com.example.androiddevjava;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Fragment firstFragment = new FirstFragment();
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