package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserResponse;
import com.example.androiddevjava.utils.UtilsInterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET(UtilsInterface.USER_NAME)
    Call<UserResponse> getUser(@Path("username") String username);
}
