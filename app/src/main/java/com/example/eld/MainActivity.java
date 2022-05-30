package com.example.eld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper database = new DBHelper(this);
        login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userN = findViewById(R.id.uname);
                String user = userN.getText().toString();
                TextView passW = findViewById(R.id.pass);
                String pass = passW.getText().toString();

                if(user.equals("root")){
                    if(pass.equals("admin")){
                        TextView invalid = findViewById(R.id.invalid);
                        invalid.setText("Login Successfully");
                        invalid.setVisibility(View.VISIBLE);

                        Intent newAct = new Intent(MainActivity.this, dashboard.class);
                        startActivity(newAct);

                    }else{
                        TextView invalid = findViewById(R.id.invalid);
                        invalid.setVisibility(View.VISIBLE);

                        invalid.setText("Invalid username or password");

                        userN.setText("");
                        passW.setText("");
                    }
                }else{
                    TextView invalid = findViewById(R.id.invalid);
                    invalid.setVisibility(View.VISIBLE);

                    userN.setText("");
                    passW.setText("");
                }
            }
        });
    }
}