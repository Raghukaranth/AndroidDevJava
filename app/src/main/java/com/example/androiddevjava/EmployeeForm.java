package com.example.androiddevjava;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevjava.model.Employee;
import com.example.androiddevjava.retrofit.EmployeeApi;
import com.example.androiddevjava.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTextName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBranch);
        TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldLocation);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditTextName.getText());
            String location = String.valueOf(inputEditLocation.getText());
            String branch = String.valueOf(inputEditBranch.getText());

            Employee employee = new Employee();
            employee.setName(name);
            employee.setBranch(branch);
            employee.setLocation(location);

            employeeApi.save(employee)
                    .enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            Toast.makeText(EmployeeForm.this, "Save successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(EmployeeForm.this, "Save Failed !!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(EmployeeForm.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });
        });


    }

}