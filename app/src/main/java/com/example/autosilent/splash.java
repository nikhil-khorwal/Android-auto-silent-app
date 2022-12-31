package com.example.autosilent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Thread trd = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    Intent it = new Intent(getApplication(),MainActivity.class);
                    startActivity(it);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        };
        trd.start();

    }
}