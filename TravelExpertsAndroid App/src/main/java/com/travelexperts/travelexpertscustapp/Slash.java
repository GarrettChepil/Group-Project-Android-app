package com.travelexperts.travelexpertscustapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class Slash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);

        Thread splashTimer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }

        };
        splashTimer.start();
    }




}
