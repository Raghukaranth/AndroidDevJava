package com.example.androiddevjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.UserResponse;
import com.example.androiddevjava.retrofit.ApiService;
import com.example.androiddevjava.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelloUser extends AppCompatActivity {

    private EditText editTextUsername;
    private TextView textViewResponse;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_user);

         editTextUsername = findViewById(R.id.editTextUsername);
        textViewResponse = findViewById(R.id.textViewResponse);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        RetrofitService retrofitService = new RetrofitService();
        apiService = retrofitService.getRetrofit().create(ApiService.class);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString().trim();
                if (!userName.isEmpty()) fetchUserData(userName);
                else
                    Toast.makeText(HelloUser.this, "Please enter User name", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchUserData(String userName) {
        Call<UserResponse> call = apiService.getUser(userName);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    UserResponse userResponse = response.body();
                    textViewResponse.setText("UserName: "+ userResponse.getUserName());
                } else {
                    Toast.makeText(HelloUser.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(HelloUser.this, "Request Failed: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}