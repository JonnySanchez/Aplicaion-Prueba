package com.example.aplicacindeprueba.io.model;

import androidx.annotation.NonNull;

public class UserRequest {
    String email;
    String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "email: " + email +", password: "+ password;
    }
}
