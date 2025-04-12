package com.example.androiddevjava.login;

import static com.example.androiddevjava.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.R;
import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.retrofit.RetrofitService;
import com.example.androiddevjava.retrofit.UserLoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private EditText nameTextBox;
    private Button signUpBtn;
    private TextView responseTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameTextBox = findViewById(R.id.idNameText);
        signUpBtn = findViewById(id.idBtnSignUp);
        responseTV = findViewById(R.id.idTVResponse);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameTextBox.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(nameTextBox.getText().toString());
            }
        });
    }

    private void postData(String name) {
        RetrofitService retrofitService = new RetrofitService();
        UserLoginAPI userLoginAPI = retrofitService.getRetrofit().create(UserLoginAPI.class);

        UserLoginModel model = new UserLoginModel(name);
        Call<UserLoginModel> call = userLoginAPI.signUpUser(model);

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