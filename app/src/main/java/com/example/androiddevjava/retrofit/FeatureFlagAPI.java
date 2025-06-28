package com.example.androiddevjava.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeatureFlagAPI {
    @GET("/api/user-feature-flags/{userId}/{featureName}/enabled")
    Call<Boolean> isFeatureEnabledForUser(@Path("userId")Long userId, @Path("featureName")String featureName);
}
