package com.example.androiddevjava.ams;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androiddevjava.R;
import com.example.androiddevjava.utils.AttendanceDbHelper;

public class AttendenceActivity extends AppCompatActivity {

    // Existing views
    CheckBox chkbxk1, chkbxk2, chkbxk3;
    EditText time1, time2, time3;
    EditText reason1, reason2, reason3;
    Button submitButton, readDataButton;

    AttendanceDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        // Initialize views
        chkbxk1 = findViewById(R.id.checkbox_reason1);
        time1 = findViewById(R.id.time_input1);
        reason1 = findViewById(R.id.reason_input1);

        chkbxk2 = findViewById(R.id.checkbox_reason2);
        time2 = findViewById(R.id.time_input2);
        reason2 = findViewById(R.id.reason_input2);

        chkbxk3 = findViewById(R.id.checkbox_reason3);
        time3 = findViewById(R.id.time_input3);
        reason3 = findViewById(R.id.reason_input3);

        submitButton = findViewById(R.id.submitButton);
        readDataButton = findViewById(R.id.readDataButton);

        dbHelper = new AttendanceDbHelper(this);

        setupCheckboxListener(chkbxk1, time1, reason1);
        setupCheckboxListener(chkbxk2, time2, reason2);
        setupCheckboxListener(chkbxk3, time3, reason3);

        submitButton.setOnClickListener(v -> saveDataToDatabase());

        readDataButton.setOnClickListener(v -> readDataFromDatabase());
    }

    private void setupCheckboxListener(CheckBox checkbox, EditText timeInput, EditText reasonInput) {
        checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                timeInput.setVisibility(View.VISIBLE);
                reasonInput.setVisibility(View.GONE);
            } else {
                timeInput.setVisibility(View.GONE);
                reasonInput.setVisibility(View.VISIBLE);
            }
        });
    }

    private void saveDataToDatabase() {
        saveSingleEntry(chkbxk1, time1, reason1);
        saveSingleEntry(chkbxk2, time2, reason2);
        saveSingleEntry(chkbxk3, time3, reason3);

        Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void saveSingleEntry(CheckBox checkbox, EditText timeInput, EditText reasonInput) {
        boolean isChecked = checkbox.isChecked();
        String time = isChecked ? timeInput.getText().toString().trim() : "";
        String reason = !isChecked ? reasonInput.getText().toString().trim() : "";

        long id = dbHelper.insertAttendance(reason, time, isChecked);
        if (id == -1) {
            Toast.makeText(this, "Error saving data for " + checkbox.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readDataFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                AttendanceDbHelper.COLUMN_ID,
                AttendanceDbHelper.COLUMN_REASON,
                AttendanceDbHelper.COLUMN_TIME,
                AttendanceDbHelper.COLUMN_IS_CHECKED
        };

        Cursor cursor = db.query(
                AttendanceDbHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                AttendanceDbHelper.COLUMN_ID + " DESC"
        );

        StringBuilder dataBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(AttendanceDbHelper.COLUMN_ID));
            String reason = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceDbHelper.COLUMN_REASON));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceDbHelper.COLUMN_TIME));
            int isCheckedInt = cursor.getInt(cursor.getColumnIndexOrThrow(AttendanceDbHelper.COLUMN_IS_CHECKED));
            boolean isChecked = isCheckedInt == 1;

            dataBuilder.append("Entry ID: ").append(id).append("\n");
            dataBuilder.append("Checked: ").append(isChecked).append("\n");
            dataBuilder.append("Time: ").append(time.isEmpty() ? "N/A" : time).append("\n");
            dataBuilder.append("Reason: ").append(reason.isEmpty() ? "N/A" : reason).append("\n\n");
        }
        cursor.close();

        if (dataBuilder.length() == 0) {
            dataBuilder.append("No data found.");
        }

        // Show data in a simple AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Saved Attendance Data")
                .setMessage(dataBuilder.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}
