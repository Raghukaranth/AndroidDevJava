package com.example.androiddevjava.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.discovery.DiscoveryActivity;
import com.example.androiddevjava.R;
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

        idLoginButton.setEnabled(false);

        idNumberTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                idLoginButton.setEnabled(!charSequence.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                long id = Long.parseLong(idNumberTextBox.getText().toString());
                if (idNumberTextBox.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter the value", Toast.LENGTH_SHORT).show();
                }
                postData(id);
            }
        });
    }

    private void postData(long id) {

        RetrofitService retrofitService = new RetrofitService();
        UserLoginAPI userLoginAPI = retrofitService.getRetrofit().create(UserLoginAPI.class);

        UserLoginModel model = new UserLoginModel(id);
        Call<UserLoginModel> call = userLoginAPI.loginUser(id);

        call.enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                if(response.isSuccessful()) {

                    Intent intent = new Intent(LoginActivity.this, DiscoveryActivity.class);
                    intent.putExtra("userLongValue", id);
                    startActivity(intent);
                }
                else Toast.makeText(LoginActivity.this, "You are not the member of our group please sign up first", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Not able to go to the screen please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}