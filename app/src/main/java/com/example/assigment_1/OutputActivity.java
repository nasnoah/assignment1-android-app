package com.example.assigment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class OutputActivity extends AppCompatActivity {

    // Declare components/views
    TextView tvOutput;
    Button btnBack;

    // Declare and initialized needed variables and constants
    String firstName, lastName, hometown, gender, msg;
    int yob, age;
    ArrayList<String> goodManners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        // Find the components/views
        tvOutput = findViewById(R.id.tv_output);
        btnBack = findViewById(R.id.btn_back);

        // Handling the Exceptions/Errors
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        // Read and Assign the Values to Variables
        firstName = bundle.getString("FIRST NAME");
        lastName = bundle.getString("LAST NAME");
        yob = bundle.getInt("YOB");
        age = bundle.getInt("AGE");
        hometown = bundle.getString("HOMETOWN");
        gender = bundle.getString("GENDER");
        goodManners = bundle.getStringArrayList("GOOD MANNERS");

        msg = "Name: " + firstName + " " + lastName
            + "\nYOB: " + yob
            + "\nAge: " + age
            + "\nHometown: " + hometown
            + "\nGender: " + gender
            + "\nGood Manners: ";

        assert goodManners != null;
        int index = 1;
        for(String goodManner : goodManners) {
            msg += "\n" + index + ". " + goodManner;
            index++;
        }

        tvOutput.setText(msg);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutputActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}