package com.example.ortalyona.game;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button startGame, scores, logs, web;
    String dataToLogFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = (Button)findViewById(R.id.StartGame);
        scores = (Button)findViewById(R.id.Scores);
        logs = (Button)findViewById(R.id.logs);
        web = (Button)findViewById(R.id.webview);


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataToLogFile = "User Click on Start Game";
                writeLogToFile(dataToLogFile);
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataToLogFile = "User Click on Scores";
                writeLogToFile(dataToLogFile);
                startActivity(new Intent(MainActivity.this, ScoresActivity.class));
            }
        });
        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataToLogFile = "User Click on Logs";
                writeLogToFile(dataToLogFile);
                startActivity(new Intent(MainActivity.this, LogActivity.class));
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataToLogFile = "User Click on Web View";
                writeLogToFile(dataToLogFile);
                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
            }
        });
//
//        String ssid = getWifiName(this);
//        TextView ssidTextView = (TextView) findViewById(R.id.wifiSSID);
//        ssidTextView.setText(ssid);
        // this.setTitle(ssid);
    }
//    public String getWifiName(Context context) {
//        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//        if (manager.isWifiEnabled()) {
//            WifiInfo wifiInfo = manager.getConnectionInfo();
//            if (wifiInfo != null) {
//                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
//                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
//                    return wifiInfo.getSSID();
//                }
//            }
//        }
//        return null;
//    }


    public void writeLogToFile(String data) {
        try {

            FileOutputStream fou = openFileOutput("logs.txt", MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
