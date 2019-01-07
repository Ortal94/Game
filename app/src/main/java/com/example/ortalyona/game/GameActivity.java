package com.example.ortalyona.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Timer;

public class GameActivity extends Activity {
    FloatingActionButton dot;
    String dataToFile;
    String dataToLogFile;
    TextView textViewScore;
    TextView textViewgameover;
    TextView textViewTime;
    int score=0;
    Random r = new Random();
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    CountDownTimer time;
    long millisInFuture = 3000;
    long countDownInterval = 100;
    File file = new File("score.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new GameView(this));
        try {
            file.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(file, false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_game);
        dot = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        textViewScore = (TextView) findViewById(R.id.scores);
        textViewgameover = (TextView) findViewById(R.id.gameover);
        textViewTime = (TextView) findViewById(R.id.time);
        textViewScore.setText("SCORES: "+ score);
        textViewgameover.setText("");
        time = Timer(millisInFuture,countDownInterval);
        dot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                time.cancel();
                score++;
                textViewScore.setText("SCORES: "+ score);
                millisInFuture = millisInFuture*9500/10000;
                Timer(millisInFuture,countDownInterval);
                dot.animate().x(r.nextInt(screenWidth)).y(r.nextInt(screenHeight));
            }
        });
    }
    public CountDownTimer Timer(long millisInFuture,long countDownInterval) {
        time = new CountDownTimer(millisInFuture, countDownInterval) {
            public void onTick(long millisUntilFinished) {
                textViewTime.setText("Time to finish: " + millisUntilFinished / 1000 + " sec");
            }
            public void onFinish() {
                dataToFile = "The score:" + score;
                writeToFile(dataToFile);
                dataToLogFile = "Player Finish to play";
                writeLogToFile(dataToLogFile);
                startActivity(new Intent(GameActivity.this, ScoresActivity.class));
                textViewgameover.setText("GAME OVER");
            }
        }.start();
        return time;
    }
    public void writeToFile(String data) {
        try {

            FileOutputStream fou = openFileOutput("scores.txt", MODE_APPEND);
            GameActivity.this.getFilesDir().getAbsolutePath();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void writeLogToFile(String data) {
        try {

            FileOutputStream fou = openFileOutput("logs.txt", MODE_APPEND);
            GameActivity.this.getFilesDir().getAbsolutePath();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
