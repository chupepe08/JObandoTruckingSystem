package com.jobandotrucking.eld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eld.R;
import com.google.android.material.textfield.TextInputEditText;

public class assignJob extends AppCompatActivity {

    public EditText clientName;
    public TextInputEditText jobDetails, jobLocation, jobDate;
    public Button assign;


    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_job);

        clientName = (EditText) findViewById(R.id.clientName);
        jobDetails = (TextInputEditText) findViewById(R.id.jobDetails);
        jobLocation = (TextInputEditText) findViewById(R.id.jobLocation);
        jobDate = (TextInputEditText) findViewById(R.id.jobDate);



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}