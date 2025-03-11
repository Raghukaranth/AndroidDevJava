package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserLoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserLoginAPI {
    @POST("/loginUser/signup")
    Call<UserLoginModel> signUpUser(@Body UserLoginModel userLoginModel);

}
