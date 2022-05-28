package com.varsitycollege.collectionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginBtn;
    userDetailsDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.emailEtxt);
        password = (EditText) findViewById(R.id.passwordEtxt);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        DB = new userDetailsDB(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();

                if (userEmail.equals("") | userPass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkEmailPassword = DB.checkEmailPassword(userEmail, userPass);
                        if (checkEmailPassword == true) {
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
    }
}