    package com.example.androiddevjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

    public class StorageActivity extends AppCompatActivity implements View.OnClickListener {
        Button read, write;
        EditText userInput;
        TextView fileContent;

        private String fileName = "demoFile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        read = findViewById(R.id.read_button);
        write = findViewById(R.id.write_button);
        userInput = findViewById(R.id.userInput);
        fileContent = findViewById(R.id.content);

        read.setOnClickListener(this);
        write.setOnClickListener(this);
    }

    public void printMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
        @Override
        public void onClick(View view) {
            Button b = (Button) view;

            String b_text = b.getText().toString();

            switch (b_text.toLowerCase()) {
                case "write": {
                    writeDate();
                    break;
                }
                case "read": {
                    readDate();
                    break;
                }
            }
        }

        private void writeDate() {
            try {
                FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                String data = userInput.getText().toString();
                fos.write(data.getBytes());
                fos.flush();
                fos.close();
            } catch (IOException e) { e.printStackTrace(); }
            userInput.setText("");
            printMessage("Writing to File "+ fileName + "completed ...");
        }

        private void readDate() {
            try {
                FileInputStream fin = openFileInput(fileName);
                int a;
                StringBuilder temp = new StringBuilder();
                while ((a = fin.read()) != -1) temp.append((char) a);
                fileContent.setText(temp.toString());
                fin.close();
            } catch (IOException e) { e.printStackTrace(); }
            printMessage("reading to file " + fileName + " completed..");
        }
    }