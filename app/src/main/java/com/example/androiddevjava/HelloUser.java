package com.example.androiddevjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androiddevjava.model.UserResponse;
import com.example.androiddevjava.retrofit.ApiService;
import com.example.androiddevjava.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelloUser extends AppCompatActivity {

    private EditText usernameEditText;
    private TextView responseTextView;
    private Button sendButton;

    RetrofitService retrofitService = new RetrofitService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello_user);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameEditText = findViewById(R.id.usernameEditText);
        responseTextView = findViewById(R.id.responseTextView);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            fetchUserData(username);
        });
    }

    private void fetchUserData(String username) {
        ApiService apiService = retrofitService.getRetrofit().create(ApiService.class);
        Call<String> call = apiService.getUserData(username);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String data = response.body();
                    responseTextView.setText(data);
                } else {
                    responseTextView.setText("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                responseTextView.setText("Error: " + t.getMessage());
            }
        });
    }
}