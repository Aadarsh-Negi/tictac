package com.example.tic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class front extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }

    public void offline(View view) {
        Intent intent = new Intent(front.this, MainActivity.class);
        startActivity(intent);
    }
    public void online(View view) {
        Intent intent = new Intent(front.this, online.class);
        startActivity(intent);
    }
    public void exit(View view){
        finishAndRemoveTask();
    }
}