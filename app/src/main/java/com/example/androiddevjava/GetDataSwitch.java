package com.example.androiddevjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.Data;
import com.example.androiddevjava.retrofit.DataAPI;
import com.example.androiddevjava.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataSwitch extends AppCompatActivity {
    private EditText nameEdt, jobEdt;
    private Button postDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_switch);

        nameEdt = findViewById(R.id.idEdtName);
        jobEdt = findViewById(R.id.idEdtJob);
        postDataBtn = findViewById(R.id.idBtnPost);

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEdt.getText().toString().isEmpty() ||
                        jobEdt.getText().toString().isEmpty()) {
                    Toast.makeText(GetDataSwitch.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(nameEdt.getText().toString(), jobEdt.getText().toString());
            }
        });
    }

    private void postData(String name, String job) {
        RetrofitService retrofitService = new RetrofitService();
        DataAPI dataAPI = retrofitService.getRetrofit().create(DataAPI.class);

        Data model = new Data(name, job);
        Call<Data> call = dataAPI.createPost(model);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                jobEdt.setText("");
                nameEdt.setText("");
                Data responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();
                Intent intent = new Intent(GetDataSwitch.this, PostDataSwitch.class);
                intent.putExtra("user_data",model);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(GetDataSwitch.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}