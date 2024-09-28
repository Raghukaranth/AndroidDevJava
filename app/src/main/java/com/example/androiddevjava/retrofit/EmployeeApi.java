package com.example.androiddevjava.retrofit;

import com.example.androiddevjava.model.Employee;
import com.example.androiddevjava.utils.UtilsInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {
    @GET(UtilsInterface.GET_ALL_EMPLOYEE)
    Call<List<Employee>> getAllEmployees();

    @POST(UtilsInterface.SAVE_EMPLOYEE)
    Call<Employee> save(@Body Employee employee);

}
