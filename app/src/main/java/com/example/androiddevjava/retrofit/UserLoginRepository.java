package com.example.androiddevjava.retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androiddevjava.model.UserLoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRepository {
    private UserLoginAPI userLoginAPI;
    private MutableLiveData<UserLoginModel> userLoginModelMutableLiveData = new MutableLiveData<>();

    public UserLoginRepository() {
        RetrofitService retrofitService = new RetrofitService();
        UserLoginAPI userLoginAPI = retrofitService.getRetrofit().create(UserLoginAPI.class);
    }

    public LiveData<UserLoginModel> getUsers(long id){
        userLoginAPI.loginUser(id).enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                if (response.isSuccessful() && response.body() != null)
                    userLoginModelMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                userLoginModelMutableLiveData.postValue(null);
            }
        });
        return userLoginModelMutableLiveData;
    }
}
