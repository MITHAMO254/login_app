package com.example.loginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    private static final String TAG = "Signup";
    EditText mail, pass, confirm_pass;
    Button signup;
    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        confirm_pass = findViewById(R.id.confirm_pass);
        signup = findViewById(R.id.signup);
        database = new Database(this);
        //
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button clicked");
                String email = mail.getText().toString();
                String password = pass.getText().toString();
                String confirmedpass = confirm_pass.getText().toString();

                if (email.equals("") || password.equals("") || confirmedpass.equals("")) {
                    Toast.makeText(getApplicationContext(), "fields can`t be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (database.emailExists(email)) {
                    Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
                    return;
                }

                if ((!password.equals(confirmedpass))) {
                    Toast.makeText(getApplicationContext(), "Your passwords don`t match", Toast.LENGTH_LONG).show();
                    return;
                }

                User user = new User(email, password);
                database.adduser(user);
                Log.d(TAG, "onClick: added users"+database.getusers());
                startActivity(new Intent(Signup.this, Login.class));
            }

            //


        });
    }
}
