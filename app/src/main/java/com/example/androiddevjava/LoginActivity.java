package com.example.androiddevjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class LoginActivity extends AppCompatActivity {
    private EditText idNumberTextBox;
    private Button idLoginButton;
    private Button idLoginSignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idNumberTextBox = findViewById(R.id.idNumberTextBox);
        idLoginButton = findViewById(R.id.idLoginButton);
        idLoginSignButton = findViewById(R.id.idLoginSignButton);

        idLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idNumberTextBox.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter the value", Toast.LENGTH_SHORT).show();
            }
        });

        idLoginSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}