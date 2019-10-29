package com.example.loginsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button loginemail,logingoogle;
    List<AuthUI.IdpConfig> providers;
    public static final int RC_SIGN_IN =12345;
    private static final String TAG = "MainActivity";
    SharedPreferences preferences;
    boolean checked;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Auth in sqlite or google");
        loginemail = findViewById(R.id.logemail);
        logingoogle = findViewById(R.id.loggoogle);
        //initializing Sharedpreferences
        preferences =  this.getSharedPreferences("usermail", Context.MODE_PRIVATE);
        //
        //get a value from shared preferences that is linked to the key `remember`
        checked = preferences.getBoolean("remember", false);
        Log.d(TAG, "onCreate: "+checked);
        //
        //set onclicklistener on button loginemail.
        loginemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                //start an Intent that launches screen Login once the button is clicked.
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                //
                //if user had checked the remember me checkbox, direct him to the welcome screen.
                if (checked==true){
                    startActivity(new Intent(MainActivity.this,Welcome.class));
                }
            }
        });
        //
        //set an onclick listener to button logingoogle.
        logingoogle.setOnClickListener(this);
        //choose authentication providers.
        providers= Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

    }

    @Override
    public void onClick(View v) {
        //activity for result creates a screen that contains the providers once button logingoogle is clicked.
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(), RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                //create a user from the FirebaseUser class
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //
                //create an intent that will launch the welcome screen if resultcode = result_ok
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                intent.putExtra("user",user.getDisplayName());
                startActivity(intent);
                // ...
            } else {
                Log.e(TAG, "onActivityResult: "+response);
                Log.e(TAG, "onActivityResult: something went wrong");
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
