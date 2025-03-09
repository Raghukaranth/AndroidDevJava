package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserLoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserLogin {
    @POST("/loginUser/signup")
    Call<UserLoginModel> signUpForTheApp(@Body UserLoginModel userLoginModel);

    @GET("/loginUser/login/{id}")
    Call<UserLogin> loginToApp(@Path("id") Long id);
}
