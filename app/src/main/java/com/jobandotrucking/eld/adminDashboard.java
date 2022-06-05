package com.jobandotrucking.eld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eld.R;

public class adminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        ImageView addDriver = findViewById(R.id.addDriverImage);
        ImageView assignJob = findViewById(R.id.assignNewJobImage);
        TextView addDiverT = findViewById(R.id.addDriverText);
        TextView assignJobT = findViewById(R.id.addWorkText);

        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addNewDriver.class);
                startActivity(intent);
            }
        });

        addDiverT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addNewDriver.class);
                startActivity(intent);
            }
        });

        assignJobT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), assignJob.class);
                startActivity(intent);
            }
        });

        assignJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), assignJob.class);
                startActivity(intent);
            }
        });

    }

}