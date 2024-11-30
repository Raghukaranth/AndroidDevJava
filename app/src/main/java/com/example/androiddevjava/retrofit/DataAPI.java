package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.Data;
import com.example.androiddevjava.utils.UtilsInterface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DataAPI {
    @POST(UtilsInterface.POST_USERS)
    Call<Data> createPost(@Body Data data);
}
