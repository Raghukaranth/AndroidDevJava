package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/hello/{username}")
    Call<String> getUserData(@Path("username") String username);
}
