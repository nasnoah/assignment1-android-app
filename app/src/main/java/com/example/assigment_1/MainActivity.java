package com.example.assigment_1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare components/views
    TextView tvFirstName, tvLastName, tvYOB, tvHometown, tvGender, tvGoodManners;
    EditText etFirstName, etLastName, etYOB, etHometown;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    CheckBox cbRespect, cbHelp, cbHonest;
    Button btnReset, btnSubmit;

    // Declare and initialized needed variables and constants
    String firstName, lastName, strYOB, hometown, gender = "Male", errorMsg;
    int yob, age, viewId;
    ArrayList<String> goodMannerList, errorFieldList;
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the components/views
        // TextView
        tvFirstName = findViewById(R.id.tv_first_name);
        tvLastName = findViewById(R.id.tv_last_name);
        tvYOB = findViewById(R.id.tv_yob);
        tvHometown = findViewById(R.id.tv_hometown);
        tvGender = findViewById(R.id.tv_gender);
        tvGoodManners = findViewById(R.id.tv_good_manners);
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

        // Get Gender Data
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

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
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

            // Clear All Errors
            etFirstName.setError(null);
            etLastName.setError(null);
            etYOB.setError(null);
            etHometown.setError(null);
            rbMale.setError(null);
            rbFemale.setError(null);
            cbRespect.setError(null);
            cbHelp.setError(null);
            cbHonest.setError(null);
        }
        else if (viewId  == btnSubmit.getId()) {

            // Get Data From EditText
            firstName = etFirstName.getText().toString().trim();
            lastName = etLastName.getText().toString().trim();
            strYOB = etYOB.getText().toString().trim();
            if (!strYOB.isBlank()) {
                yob = Integer.parseInt(strYOB);
            }
            hometown = etHometown.getText().toString().trim();

            // Get Data From CheckBox
            goodMannerList = new ArrayList<>();
            if (cbRespect.isChecked()) {
                goodMannerList.add(cbRespect.getText().toString());
            }
            if (cbHelp.isChecked()) {
                goodMannerList.add(cbHelp.getText().toString());
            }
            if (cbHonest.isChecked()) {
                goodMannerList.add(cbHonest.getText().toString());
            }

            // Validate Empty Fields
            errorFieldList = new ArrayList<>();
            if (firstName.isBlank() || lastName.isBlank() || strYOB.isBlank() || hometown.isBlank() || gender.isBlank() || goodMannerList.isEmpty()) {
                errorMsg = "Please fill in ";
                if (goodMannerList.isEmpty()) {
                    errorFieldList.add(tvGoodManners.getText().toString());
                    cbRespect.setError("At least one " + tvGoodManners.getText().toString() + " is required!");
                    cbHelp.setError("At least one " + tvGoodManners.getText().toString() + " is required!");
                    cbHonest.setError("At least one " + tvGoodManners.getText().toString() + " is required!");
                    cbRespect.requestFocus();
                }
                else {
                    cbRespect.setError(null);
                    cbHelp.setError(null);
                    cbHonest.setError(null);
                }
                if (gender.isBlank()) {
                    errorFieldList.add(tvGender.getText().toString());
                    rbMale.setError(tvGender.getText().toString() + " is required!");
                    rbFemale.setError(tvGender.getText().toString() + " is required!");
                    rgGender.requestFocus();
                }
                if (hometown.isBlank()) {
                    errorFieldList.add(tvHometown.getText().toString());
                    etHometown.setError(tvHometown.getText().toString() + " is required!");
                    etHometown.requestFocus();
                }
                if (strYOB.isBlank()) {
                    errorFieldList.add(tvYOB.getText().toString());
                    etYOB.setError(tvYOB.getText().toString() + " is required!");
                    etYOB.requestFocus();
                }
                if (lastName.isBlank()) {
                    errorFieldList.add(tvLastName.getText().toString());
                    etLastName.setError(tvLastName.getText().toString() + " is required!");
                    etLastName.requestFocus();
                }
                if (firstName.isBlank()) {
                    errorFieldList.add(tvFirstName.getText().toString());
                    etFirstName.setError(tvFirstName.getText().toString() + " is required!");
                    etFirstName.requestFocus();
                }

                int index = 0;
                for (String errorField : errorFieldList.reversed()) {
                    errorMsg += errorField;
                    if (index != errorFieldList.size() - 1) {
                        errorMsg += ", ";
                    }
                    index++;
                }

                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                return;
            }

            // Validate YOB
            if (yob < 1900 || yob > CURRENT_YEAR) {
                cbRespect.setError(null);
                cbHelp.setError(null);
                cbHonest.setError(null);

                errorMsg = "Invalid Year of Birth (Valid: 1900 - " + CURRENT_YEAR + ")";
                etYOB.setError(errorMsg);
                etYOB.requestFocus();
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                return;
            }

            // Calculate Age
            age = CURRENT_YEAR - yob;

            // Pass data to OutputActivity
            Intent intent = new Intent(MainActivity.this, OutputActivity.class);

            intent.putExtra("FIRST NAME", firstName);
            intent.putExtra("LAST NAME", lastName);
            intent.putExtra("YOB", yob);
            intent.putExtra("AGE", age);
            intent.putExtra("HOMETOWN", hometown);
            intent.putExtra("GENDER", gender);
            intent.putStringArrayListExtra("GOOD MANNERS", goodMannerList);

            startActivity(intent);
            //finish();
        }
    }
}