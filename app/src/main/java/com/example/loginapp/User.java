package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class User {


    public String fullName;
    public String email;
    public int currentQueue;
    public String currentClinic;

    public User(String fullName, String email, int currentQueue, String currentClinic) {
        this.fullName = fullName;
        this.email = email;
        this.currentQueue = currentQueue;
        this.currentClinic = currentClinic;

    }


    public int getCurrentQueue() {
        return currentQueue;
    }

    public void setCurrentQueue(int currentQueue) {
        this.currentQueue = currentQueue;
    }

    public String getCurrentClinic() {
        return currentClinic;
    }

    public void setCurrentClinic(String currentClinic) {
        this.currentClinic = currentClinic;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserEmail(){
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

}
