package com.shiva.ananta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class StoryActivity extends AppCompatActivity {

    int progresstime = 0;
    LinearLayout storyll;
    boolean isTimerRunning = true;
    TextView anantaporfilestoryname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        int colorFFA800 = Color.parseColor("#FFA800");
        progressBar.getProgressDrawable().setColorFilter(colorFFA800, PorterDuff.Mode.SRC_IN);
        storyll = findViewById(R.id.storyll);
//        storyll.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(StoryActivity.this, "Long pressed", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        progressBar.setProgress(progresstime);
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                progresstime +=5;
//                progressBar.setProgress(progresstime);
//                if(progressBar.getProgress()>=100){
//                    timer.cancel();
//                    finish();
//
//                }
//            }
//        },1000,30);

        storyll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(StoryActivity.this, "Long pressed", Toast.LENGTH_SHORT).show();
                isTimerRunning = !isTimerRunning;
                return true;
            }
        });

        progressBar.setProgress(progresstime);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isTimerRunning) {
                    progresstime += 5;
                    progressBar.setProgress(progresstime);
                if (progressBar.getProgress() >= 100) {
                    timer.cancel();
                    finish();
                }
                }
            }
        }, 1000, 30);



        anantaporfilestoryname = findViewById(R.id.anantaporfilestoryname);
        Intent intent = getIntent();
        if (intent != null) {
            String profileName = intent.getStringExtra("name");
            if (profileName != null) {
                anantaporfilestoryname.setText(profileName);
            }
        }

    }
}