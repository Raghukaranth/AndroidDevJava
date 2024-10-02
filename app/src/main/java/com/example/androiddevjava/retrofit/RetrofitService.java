package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.utils.UtilsInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;

    public  RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(UtilsInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
