package com.example.androiddevjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddevjava.R;
import com.example.androiddevjava.model.Employee;

import java.util.List;

import okhttp3.internal.annotations.EverythingIsNonNull;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder>{
    private List<Employee> employeeList;

    public EmployeeAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @EverythingIsNonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@EverythingIsNonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_employee_item, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@EverythingIsNonNull EmployeeHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.name.setText(employee.getName());
        holder.location.setText(employee.getLocation());
        holder.branch.setText(employee.getBranch());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
}
