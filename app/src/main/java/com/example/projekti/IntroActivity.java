package com.example.projekti;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Timer;

import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {
    Timer time;
//private ConstraintLayout btn_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}