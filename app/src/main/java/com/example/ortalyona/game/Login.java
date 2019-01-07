package com.example.ortalyona.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.invoke.MethodHandles;

public class Login extends AppCompatActivity {

    Button login;
    EditText userName;
    EditText Pass;
    boolean flag = false;
    String dataToLogFile;
    File file = new File("logs.txt");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            file.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(file, false);

        } catch (IOException e) {
            e.printStackTrace();
        }

        userName = (EditText) findViewById(R.id.username);
        Pass = (EditText) findViewById(R.id.password);
        final String editTextName = userName.getText().toString();
        final String editTextPass = Pass.getText().toString();
        login = (Button) findViewById(R.id.loginbutton);
        login.setEnabled(false);

        userName.addTextChangedListener(watcher);
        Pass.addTextChangedListener(watcher);

            login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dataToLogFile = "--------------------------";
                    writeLogToFile(dataToLogFile);
                    dataToLogFile = userName.getText().toString() + " Logged In";
                    writeLogToFile(dataToLogFile);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);

                }
            });


    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
           if (userName.getText().toString().length() == 0  || Pass.getText().toString().length() == 0){
                login.setEnabled(false);
            } else if((userName.getText().toString()).compareTo(Pass.getText().toString())==0){
                login.setEnabled(true);
            }
        }
    };

    public void writeLogToFile(String data) {
        try {

            FileOutputStream fou = openFileOutput("logs.txt", MODE_APPEND);
            Login.this.getFilesDir().getAbsolutePath();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data + "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
