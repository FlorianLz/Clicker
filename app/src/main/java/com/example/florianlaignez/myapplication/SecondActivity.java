package com.example.florianlaignez.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void onClickJouer(View view) {
        Intent intent = new Intent(this, ApplicationFlorian.class);
        startActivity(intent);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
