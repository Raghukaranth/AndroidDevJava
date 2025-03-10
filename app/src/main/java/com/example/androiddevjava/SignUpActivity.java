package com.example.androiddevjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.Data;
import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.retrofit.RetrofitService;
import com.example.androiddevjava.retrofit.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameTextBox;
    private Button signUpButton;
    private TextView responseTV;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameTextBox = findViewById(R.id.idNameTextBox);
        signUpButton = findViewById(R.id.idSignUpButton);
        responseTV = findViewById(R.id.idTVResponse);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameTextBox.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter the value", Toast.LENGTH_SHORT).show();
                }
                postSignUpData(nameTextBox.getText().toString());
            }
        });
    }

    private void postSignUpData(String name) {
        RetrofitService retrofitService = new RetrofitService();
        UserLogin userLoginApi = retrofitService.getRetrofit().create(UserLogin.class);

        UserLoginModel userLoginModel = new UserLoginModel(name);
        Call<UserLoginModel> call = userLoginApi.signUpForTheApp(userLoginModel);

        call.enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                nameTextBox.setText("");
                UserLoginModel responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n";
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}