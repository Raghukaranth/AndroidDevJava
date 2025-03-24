package com.example.androiddevjava.retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androiddevjava.model.UserLoginModel;

public class LoginViewModel extends ViewModel {
    private UserLoginRepository userLoginRepository;
    private LiveData<UserLoginModel> loginUser;

    public LoginViewModel(Long id) {
        userLoginRepository = new UserLoginRepository();
        loginUser = userLoginRepository.getUsers(id);
    }

    public LiveData<UserLoginModel> getLoginUser() {
        return loginUser;
    }
}
