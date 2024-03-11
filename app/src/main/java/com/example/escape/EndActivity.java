package com.example.escape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class EndActivity extends AppCompatActivity {
    Button btn_back2game;
    TextView textView_end1,textView_end2,textView_end3, textView_end4;
    int i=0;
    Timer timer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        btn_back2game = findViewById(R.id.btn_back2start_end);
        textView_end1 = findViewById(R.id.textView_end1);
        textView_end2 = findViewById(R.id.textView_end2);
        textView_end3 = findViewById(R.id.textView_end3);
        textView_end4 = findViewById(R.id.textView_end4);

        btn_back2game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>=5){
                    timer.cancel();
                    Intent intent=new Intent(EndActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what==0){
                    i+=1;
                    if (i==1){textView_end1.setVisibility(View.VISIBLE);}
                    if (i==2){textView_end2.setVisibility(View.VISIBLE);}
                    if (i==3){textView_end3.setVisibility(View.VISIBLE);}
                    if (i==4){textView_end4.setVisibility(View.VISIBLE);}
                    if (i==5){
                        textView_end1.setText("重启成功，但或许故事还在继续");
                        btn_back2game.setVisibility(View.VISIBLE);
                    }

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