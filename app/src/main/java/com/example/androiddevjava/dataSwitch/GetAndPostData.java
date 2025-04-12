package com.example.androiddevjava.dataSwitch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.R;
import com.example.androiddevjava.model.Data;
import com.example.androiddevjava.retrofit.DataAPI;
import com.example.androiddevjava.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAndPostData extends AppCompatActivity {
    private EditText nameEdt, jobEdt;
    private Button postDataBtn;
    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_and_post_data);

        nameEdt = findViewById(R.id.idEdtName);
        jobEdt = findViewById(R.id.idEdtJob);
        postDataBtn = findViewById(R.id.idBtnPost);
        responseTV = findViewById(R.id.idTVResponse);
        loadingPB = findViewById(R.id.idLoadingPB);

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameEdt.getText().toString().isEmpty() &&
                   jobEdt.getText().toString().isEmpty()) {
                    Toast.makeText(GetAndPostData.this, "Please enter both values", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(nameEdt.getText().toString(), jobEdt.getText().toString());
            }
        });
    }

    private void postData(String name, String job) {
        loadingPB.setVisibility(View.VISIBLE);
        RetrofitService retrofitService = new RetrofitService();
        DataAPI dataAPI = retrofitService.getRetrofit().create(DataAPI.class);

        Data model = new Data(name, job);
        Call<Data> call = dataAPI.createPost(model);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                loadingPB.setVisibility(View.GONE);
                jobEdt.setText("");
                nameEdt.setText("");
                Data responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });

    }
}