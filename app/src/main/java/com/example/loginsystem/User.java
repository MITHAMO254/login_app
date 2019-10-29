package com.example.loginsystem;

import androidx.annotation.NonNull;

public class User {
    String email, password;

    public User(String email, String password){
    this.email = email;
    this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The email is ")
                .append(this.email)
                .append(" and the password is ")
                .append(this.password);
        return builder.toString();
    }
}
