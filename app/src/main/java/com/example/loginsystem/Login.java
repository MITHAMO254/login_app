package com.example.loginsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    EditText email,password;
    Button login;
    TextView remember,query,signup;
    CheckBox check;
    Database database;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isCheck = false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        //et
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        check = findViewById(R.id.checkBox);
        remember = findViewById(R.id.remember);
        remember.setTextColor(getColor(R.color.black));
        query = findViewById(R.id.query);
        query.setTextColor(getColorStateList(R.color.black));
        signup = findViewById(R.id.sign_up);
        preferences = this.getSharedPreferences("usermail", Context.MODE_PRIVATE);
        editor = preferences.edit();

        database = new Database(Login.this);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();

                Log.d(TAG, "onClick: Button clicked");
                if (mail.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "fields can`t be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!database.emailExists(mail)) {
                    Toast.makeText(getApplicationContext(), "Email doesn`t exist", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!database.checkPassword(mail, pass)) {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                    return;
                }
                //did he check the box
                if (isCheck) {
                    editor.putBoolean("remember", isCheck);
                    editor.commit();
                }
                Log.e(TAG, "onClick: error");
                Intent intent = new Intent(Login.this, Welcome.class);
                intent.putExtra("email", mail);
                startActivity(intent);
            }
        });


        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = isChecked;
            }


        });
            signup.setTextColor(getColor(R.color.blue));
            signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });
    }
}
