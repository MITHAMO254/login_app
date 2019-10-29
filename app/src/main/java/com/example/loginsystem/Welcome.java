package com.example.loginsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Welcome extends AppCompatActivity implements View.OnClickListener{
    Button logout;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final String TAG = "Welcome";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout = findViewById(R.id.logout);
        preferences = this.getSharedPreferences("usermail",MODE_PRIVATE);
        logout.setOnClickListener(this);
        if (getIntent().hasExtra("user")){
            String message = "welcome"+getIntent().getStringExtra("user");
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: Logout button clicked");
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Intent intent = new Intent(Welcome.this, MainActivity.class);
                        intent.putExtra("logout", "You have logged out");
                        startActivity(intent);

                    }
                });
    }
}
