package com.example.eld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button login;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView invalid = findViewById(R.id.invalid);
        invalid.setVisibility(View.INVISIBLE);

        TextView invalid2 = findViewById(R.id.invalid2);
        invalid2.setVisibility(View.INVISIBLE);

        login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userN = findViewById(R.id.uname);
                String user = userN.getText().toString();
                TextView passW = findViewById(R.id.pass);
                String pass = passW.getText().toString();

                Log.d("info", user);
                Log.d("info", pass);

                if(user.equals("root")){
                    if(pass.equals("admin")){
                        Intent intent = new Intent(MainActivity.this, dashboard.class);
                        startActivity(intent);
                    }else{
                        userN.setHintTextColor(Color.RED);
                        userN.setText("");

                    }
                }else{
                    invalid.setTextColor(Color.RED);
                    TextView invalid = findViewById(R.id.invalid);
                    TextView invalid2 = findViewById(R.id.invalid2);
                    invalid.setVisibility(View.VISIBLE);
                    invalid2.setVisibility(View.VISIBLE);
                    userN.setText("");
                    passW.setText("");
                }
            }
        });


    }
}