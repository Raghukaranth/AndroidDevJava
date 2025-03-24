package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.utils.UtilsInterface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserLoginAPI {
    @POST(UtilsInterface.POST_SIGNUP)
    Call<UserLoginModel> signUpUser(@Body UserLoginModel userLoginModel);

    @GET(UtilsInterface.GET_LOGIN)
    Call<UserLoginModel> loginUser(@Path("id") Long id);
}
