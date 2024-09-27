package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/hello/userName")
    Call<UserResponse> getUser(@Query("username") String username);
}
