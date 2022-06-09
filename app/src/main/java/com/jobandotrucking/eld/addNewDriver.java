package com.jobandotrucking.eld;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eld.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class addNewDriver extends AppCompatActivity {

    private Button generate;


    public AlertDialog.Builder dialogBuilder;
    public AlertDialog dialog;
    public TextView usernameL, passwordL, usernameT, passwordT;
    public Button copyToClipboard, register;
    final Random rand = new Random();

    FirebaseFirestore db;

    public EditText fName, mName, lName, email, age, exp, tModel, tPlateNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_driver);

        db = FirebaseFirestore.getInstance();

        fName = (EditText) findViewById(R.id.fName);
        mName = (EditText) findViewById(R.id.mName);
        lName = (EditText) findViewById(R.id.lName);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        exp = (EditText) findViewById(R.id.dExp);
        tModel = (EditText) findViewById(R.id.tModel);
        tPlateNum = (EditText) findViewById(R.id.pNumber);

        usernameT = findViewById(R.id.userNameText);
        passwordT = findViewById(R.id.PasswordText);

        generate = (Button) findViewById(R.id.generate);
        register = (Button) findViewById(R.id.register);



        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog();

                String fname = String.valueOf(fName.getText());

                if(fName.equals("") || mName.equals("") || lName.equals("") || age.equals("") || exp.equals("") || tModel.equals("") || tPlateNum.equals("")){;

                    Toast.makeText(addNewDriver.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    usernameT.setVisibility(View.VISIBLE);
                    passwordT.setVisibility(View.VISIBLE);

                    usernameT.setText(fname + rand.nextInt(100));
                    passwordT.setText(generatePass());

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FName = fName.getText().toString();
                String MName = mName.getText().toString();
                String LName = lName.getText().toString();
                String FullName = FName + MName + LName;
                String Email = email.getText().toString();
                String Age = age.getText().toString();
                String DYears = exp.getText().toString();
                String TModel = tModel.getText().toString();
                String PNumber = tPlateNum.getText().toString();

                Map<String, Object> drivers = new HashMap<>();
                drivers.put("Full Name", FullName);
                drivers.put("First Name", FName);
                drivers.put("Middle Name", MName);
                drivers.put("Last Name", LName);
                drivers.put("Age", Age);
                drivers.put("Email", Email);
                drivers.put("Driving Experience", DYears);
                drivers.put("Truck Model", TModel);
                drivers.put("Plate Number", PNumber);

                db.collection("drivers").add(drivers).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(addNewDriver.this, "Register Success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addNewDriver.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void createPopupDialog(){

        dialogBuilder = new AlertDialog.Builder(this);
        final View popUp = getLayoutInflater().inflate(R.layout.popup, null);
        usernameL = (TextView) popUp.findViewById(R.id.usernameLabel);
        passwordL = (TextView) popUp.findViewById(R.id.passwordLabel);
        usernameT = (TextView) popUp.findViewById(R.id.userNameText);
        passwordT = (TextView) popUp.findViewById(R.id.PasswordText);
        copyToClipboard = (Button) popUp.findViewById(R.id.copyBtn);

        dialogBuilder.setView(popUp);
        dialog = dialogBuilder.create();
        dialog.show();

        copyToClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", "Username: " + usernameT.getText().toString() + "\nPassword: " + passwordT.getText().toString());
                cm.setPrimaryClip(clip);

                Toast.makeText(addNewDriver.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String generatePass(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            char c = chars[rand.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}