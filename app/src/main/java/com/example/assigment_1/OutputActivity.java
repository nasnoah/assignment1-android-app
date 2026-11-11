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
    String firstName, lastName, hometown, gender, output;
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

        output = getOutput();
        tvOutput.setText(output);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutputActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    private String getOutput() {

        String addressing, pronouns, possesive;
        if (age <= 12)// Child
            addressing = "little";
        else if (age <= 18) // Teenager
            addressing = "teenage";
        else if (age < 60) // Adult
            addressing = "";
        else // Senior Citizen
            addressing = "elderly";

        if (gender.equalsIgnoreCase("male")) {
            if (age <= 18)
                addressing += " boy";
            else
                addressing += " man";

            pronouns = "he";
            possesive = "his";
        }
        else {
            if (age <= 18)
                addressing += " girl";
            else
                addressing += " woman";

            pronouns = "she";
            possesive = "her";
        }

        assert goodManners != null;
        String manners = goodManners.size() + " rules: ";
        int index = 0;
        for(String goodManner : goodManners) {
            manners += goodManner.toLowerCase();
            if (index == goodManners.size() - 2) {
                manners += " and ";
            }
            else if (index != goodManners.size() - 1) {
                manners += ", ";
            }
            index++;
        }

        return "Villains attacked Earth, but " + firstName + " " + lastName + ", a " + addressing.trim() + " born in " + yob + ", was not afraid. " +
                "\n\nAt " + age + ", the hero from " + hometown + ", followed " + manners + ". " +
                "\n\nTrusting Allah, " + pronouns + " stayed brave, and even enemies dropped their weapons. " +
                "\n\nKindness was " + possesive + " true superpower.";
    }
}