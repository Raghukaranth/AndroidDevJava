package com.example.androiddevjava.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddevjava.R;

import okhttp3.internal.annotations.EverythingIsNonNull;

public class EmployeeHolder extends RecyclerView.ViewHolder {

    TextView name, location, branch;

    public EmployeeHolder(@EverythingIsNonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.employeeListItem_name);
        location = itemView.findViewById(R.id.employeeListItem_location);
        branch = itemView.findViewById(R.id.employeeListItem_branch);
    }
}
