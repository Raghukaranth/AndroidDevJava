package com.example.androiddevjava.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserLogin {
    @POST("/loginUser/signup")
    Call<UserLogin> signUpForTheApp(@Body UserLogin userLogin);

    @GET("/loginUser/login/{id}")
    Call<UserLogin> loginToApp(@Path("id") Long id);
}
