package com.example.androiddevjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.Data;

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