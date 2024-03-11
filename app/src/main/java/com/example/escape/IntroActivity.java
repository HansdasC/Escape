package com.example.escape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends AppCompatActivity {
    public static final String TAG = "IntroActivity";
    Timer timer;
    int i = 0;
    ImageView imageView_intro;
    TextView textView_intro1,textView_intro2,textView_intro3,textView_intro4,textView_intro5,textView_intro6,textView_intro7,textView_intro8;
    TextView textView_intro9,textView_intro10,textView_intro11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        imageView_intro = (ImageView) findViewById(R.id.imageview_intro);
        textView_intro1 = (TextView) findViewById(R.id.textview_introduction1);
        textView_intro2 = (TextView) findViewById(R.id.textview_introduction2);
        textView_intro3 = (TextView) findViewById(R.id.textview_introduction3);
        textView_intro4 = (TextView) findViewById(R.id.textview_introduction4);
        textView_intro5 = (TextView) findViewById(R.id.textview_introduction5);
        textView_intro6 = (TextView) findViewById(R.id.textview_introduction6);
        textView_intro7 = (TextView) findViewById(R.id.textview_introduction7);
        textView_intro8 = (TextView) findViewById(R.id.textview_introduction8);
        textView_intro9 = (TextView) findViewById(R.id.textview_introduction9);
        textView_intro10 = (TextView) findViewById(R.id.textview_introduction10);
        textView_intro11 = (TextView) findViewById(R.id.textview_introduction11);
        imageView_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>=11){

                    timer.cancel();
                    Intent intent=new Intent(IntroActivity.this, SceneDormActivity.class);
                    startActivity(intent);}

            }
        });

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what==0){
                    i+=1;
                    if (i==1){textView_intro1.setVisibility(View.VISIBLE);}
                    if (i==2){textView_intro2.setVisibility(View.VISIBLE);}
                    if (i==3){textView_intro3.setVisibility(View.VISIBLE);}
                    if (i==4){textView_intro4.setVisibility(View.VISIBLE);}
                    if (i==5){textView_intro5.setVisibility(View.VISIBLE);}
                    if (i==6){textView_intro6.setVisibility(View.VISIBLE);}
                    if (i==7){textView_intro7.setVisibility(View.VISIBLE);}
                    if (i==8){textView_intro8.setVisibility(View.VISIBLE);}
                    if (i==9){textView_intro9.setVisibility(View.VISIBLE);}
                    if (i==10){textView_intro10.setVisibility(View.VISIBLE);}
                    if (i==11){textView_intro11.setVisibility(View.VISIBLE);}
                }
            }

        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },0,1000);


    }


}