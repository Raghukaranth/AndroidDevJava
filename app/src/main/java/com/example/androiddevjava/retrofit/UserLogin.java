package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.UserLoginModel;
import com.example.androiddevjava.utils.UtilsInterface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserLogin {
    @POST(UtilsInterface.SIGN_UP)
    Call<UserLoginModel> signUpForTheApp(@Body UserLoginModel userLoginModel);

    @GET(UtilsInterface.LOGIN_WITH_ID)
    Call<UserLoginModel> loginToApp(@Path("id") Long id);
}
