package com.example.androiddevjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androiddevjava.utils.UtilsInterface;

public class Redirection extends AppCompatActivity {
    private WebView button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirection);

        button = findViewById(R.id.button);
        button.getSettings().setJavaScriptEnabled(true);
        button.setWebChromeClient(new WebChromeClient());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.loadUrl(UtilsInterface.WEBSITE_URL);
            }
        });
    }
}