package com.academics.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText signupUsername,signupEmail,signupPassword,confirmPassword;
    Button signupButton;
    TextView alreadyAccountSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsername = findViewById(R.id.signupUsername);
        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        signupButton = findViewById(R.id.signupButton);
        alreadyAccountSignup = findViewById(R.id.alreadyAccountSignup);

        alreadyAccountSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signupUsername.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String confirm = confirmPassword.getText().toString();
                MyDatabase db = new MyDatabase(getApplicationContext(),"healthcare",null, 1);

                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            db.signup(email,username, password);
                            Toast.makeText(getApplicationContext(),"Signup successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Password must contain atleast 8 characters including letters,digits and special symbols", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere){
        int f1 = 0, f2 = 0, f3 = 0;
        if(passwordhere.length()<8){
            return false;
        }else{
            for(int p = 0; p < passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1 = 1;
                }
            }
            for(int r = 0; r < passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2 = 1;
                }
            }
            for(int s = 0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if(c >= 33 && c <= 46 || c == 64){
                    f3 = 1;
                }
            }
            if(f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }
}