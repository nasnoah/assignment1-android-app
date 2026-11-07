package com.example.assigment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare components/views
    EditText etFirstName, etLastName, etYOB, etHometown;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    CheckBox cbRespect, cbHelp, cbHonest;
    Button btnReset, btnSubmit;

    // Declare and initialized needed variables and constants
    String firstName, lastName, hometown, gender = "Male";
    int yob, age, viewId;
    private static final int YEAR = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the components/views
        // EditText
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etYOB = findViewById(R.id.et_yob);
        etHometown = findViewById(R.id.et_hometown);
        // RadioGroup
        rgGender = findViewById(R.id.rg_gender);
        // RadioButton
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        // CheckBox
        cbRespect = findViewById(R.id.cb_respect);
        cbHelp = findViewById(R.id.cb_help);
        cbHonest = findViewById(R.id.cb_honest);
        // Button
        btnReset = findViewById(R.id.btn_reset);
        btnSubmit = findViewById(R.id.btn_submit);

        // RadioGroup OnCheckChange event
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (checkedId == rbMale.getId()) {
                    gender = rbMale.getText().toString();
                }
                else if (checkedId == rbFemale.getId()) {
                    gender = rbFemale.getText().toString();
                }
            }
        });

        // Set Listener to Buttons
        btnReset.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        // Focus on first EditText
        etFirstName.requestFocus();
    }

    @Override
    public void onClick(View view) {
        viewId = view.getId();
        if (viewId == btnReset.getId()) {

            // Clear EditText
            etFirstName.setText("");
            etLastName.setText("");
            etYOB.setText("");
            etHometown.setText("");

            // Clear RadioGroup
            rgGender.check(rbMale.getId());

            // Clear CheckBox
            cbRespect.setChecked(false);
            cbHelp.setChecked(false);
            cbHonest.setChecked(false);

            // Focus on EditText-FirstName
            etFirstName.requestFocus();

        }
        else if (viewId  == btnSubmit.getId()) {

            // Validate Empty Fields

            // Validate YOB

            // Pass data to OutputActivity
            Intent intent = new Intent(MainActivity.this, OutputActivity.class);
            startActivity(intent);
            //finish();
        }
    }
}