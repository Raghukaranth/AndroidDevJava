package com.example.androiddevjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.retrofit.RetrofitService;
import com.example.androiddevjava.retrofit.UserLoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String str = idNumberTextBox.getEditableText().toString();
        Long id = Long.parseLong(str);

        idLoginSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        idLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idNumberTextBox.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter the value", Toast.LENGTH_SHORT).show();
                }
                postData(id.intValue());
            }
        });
    }

    private void postData(long id) {

        RetrofitService retrofitService = new RetrofitService();
        UserLoginAPI userLoginAPI = retrofitService.getRetrofit().create(UserLoginAPI.class);

        UserLoginModel model = new UserLoginModel(id);
        Call<UserLoginModel> call = userLoginAPI.signUpUser(model);

        call.enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {

            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Not able to go to the screen please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}