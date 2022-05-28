package com.varsitycollege.collectionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPageActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    Button signup;
    userDetailsDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        email = (EditText) findViewById(R.id.EmailEtxt);
        password = (EditText) findViewById(R.id.PasswordEtxt);
        name = (EditText) findViewById(R.id.FullNameEtxt);
        signup = (Button) findViewById(R.id.SignUpBtn);
        DB = new userDetailsDB(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String pass = password.getText().toString();
                String userName = name.getText().toString();

                //Checks if user has filled all fields or if user exists
                if (userEmail.equals("") | pass.equals("") | userName.equals(""))
                    Toast.makeText(RegistrationPageActivity.this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkEmail(userEmail);
                    if (checkUser == false) {
                        Boolean insert = DB.insertData(userEmail, pass, userName);
                        if (insert == true) {
                            Toast.makeText(RegistrationPageActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegistrationPageActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(RegistrationPageActivity.this, "User already exists. Please sign in.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}