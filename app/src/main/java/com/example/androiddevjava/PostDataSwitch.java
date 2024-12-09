package com.example.androiddevjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androiddevjava.model.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PostDataSwitch extends AppCompatActivity {
    private TextView responseTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_data_switch);

        Data userDataJson = (Data) getIntent().getSerializableExtra("user_data");
        Log.e("post_data","user data / "+userDataJson);


        responseTV = findViewById(R.id.idTVResponse);
        String responseString = "Name: " + userDataJson.getName() + "Job: " + userDataJson.getJob();
        responseTV.setText(responseString);
    }
}