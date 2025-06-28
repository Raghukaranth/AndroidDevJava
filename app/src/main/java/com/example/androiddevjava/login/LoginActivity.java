package com.example.androiddevjava.login;

import static com.example.androiddevjava.utils.FeatureFlags.FEATURE_FLAG_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.dataSwitch.GetAndPostData;
import com.example.androiddevjava.discovery.DiscoveryActivity;
import com.example.androiddevjava.R;
import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.retrofit.FeatureFlagAPI;
import com.example.androiddevjava.retrofit.RetrofitService;
import com.example.androiddevjava.retrofit.UserLoginAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String ALTERNATE_ACTIVITY = "com.example.androiddevjava.dataSwitch.GetAndPostData";

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
                    checkFeatureFlagAndNavigate(id);
                }
                else Toast.makeText(LoginActivity.this, "You are not the member of our group please sign up first", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Not able to go to the screen please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkFeatureFlagAndNavigate(long userId) {
        RetrofitService retrofitService = new RetrofitService();
        FeatureFlagAPI featureFlagAPI = retrofitService.getRetrofit().create(FeatureFlagAPI.class);

        Call<Boolean> featureFlagCall = featureFlagAPI.isFeatureEnabledForUser(userId, FEATURE_FLAG_NAME);

        featureFlagCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean isFeatureEnabled = response.body();
                    navigateBasedOnFeatureFlag(userId, isFeatureEnabled);
                } else {
                    // If feature flag check fails, default to original behavior
                    navigateToDefaultActivity(userId);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                // If feature flag API call fails, default to GetAndPostData activity
                Toast.makeText(LoginActivity.this, "Feature flag check failed, using default flow", Toast.LENGTH_SHORT).show();
                navigateToDefaultActivity(userId);
            }
        });
    }

    private void navigateBasedOnFeatureFlag(long userId, boolean isFeatureEnabled) {
        Intent intent;

        if (isFeatureEnabled) {
            // Feature flag is true - go to the DiscoveryActivity
            intent = new Intent(LoginActivity.this, DiscoveryActivity.class);
        } else {
            // Feature flag is false - go to GetAndPostData activity
            intent = new Intent(LoginActivity.this, GetAndPostData.class);
        }

        intent.putExtra("userLongValue", userId);
        startActivity(intent);
    }

    private void navigateToDefaultActivity(long userId) {
        Intent intent = new Intent(LoginActivity.this, GetAndPostData.class);
        intent.putExtra("userLongValue", userId);
        startActivity(intent);
    }

}