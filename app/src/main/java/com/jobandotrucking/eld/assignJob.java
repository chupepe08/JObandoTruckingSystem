package com.jobandotrucking.eld;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eld.R;
import com.google.android.material.textfield.TextInputEditText;

public class assignJob extends AppCompatActivity {

    public EditText clientName;
    public TextInputEditText jobDetails, jobLocation, jobDate;
    public Button assign;

    DBHelper db = new DBHelper(this);

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

        assign = (Button) findViewById(R.id.assignBtn);

        autoCompleteTextView = findViewById(R.id.selectDriver);

        adapter = new ArrayAdapter<String>(this, R.layout.list_items, db.getDrivers());

        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int eid = db.getEmployeeID(autoCompleteTextView.getText().toString());


                Boolean check = db.insertCurrentJob(eid, clientName.getText().toString(), jobDetails.getText().toString(), jobLocation.getText().toString(), jobDate.getText().toString());

                if(check == true){
                    Toast.makeText(assignJob.this, "Job has been assigned", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(assignJob.this, "Error assigning job", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}