package com.example.eld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button login;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        DBHelper database = new DBHelper(this);
        login = findViewById(R.id.loginbtn);

        db.createAdmin();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView userN = findViewById(R.id.uname);
                String user = userN.getText().toString();
                TextView passW = findViewById(R.id.pass);
                String pass = passW.getText().toString();

                if(userN.equals("") || passW.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = db.checkUsernamePassword(user, pass);
                    if(checkuserpass == true){
                        Boolean checkusertype = db.checkUserType(user);
                        if(checkusertype == true){
                            Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), adminDashboard.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                            startActivity(intent);
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}