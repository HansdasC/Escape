package com.example.escape;


import static com.example.escape.MainActivity.person;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    public static final String TAG = "SettingActivity";
    static Button btn_music_open,btn_music_close;
    Button btn_save,btn_back2strat,btn_back2game;
    String[] color=new String[2];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        color[0] = "#BDBABA";
        color[1] = "#79636363";

        btn_music_open = findViewById(R.id.btn_music_open);
        btn_music_close = findViewById(R.id.btn_music_close);
        btn_save = findViewById(R.id.btn_save);
        btn_back2strat = findViewById(R.id.btn_back2start);
        btn_back2game = findViewById(R.id.btn_back2game);

        changeBtnMusic(btn_music_open,btn_music_close,MainActivity.openMusic);
        
        btn_music_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Music open");
                MainActivity.openMusic=true;
                changeBtnMusic(btn_music_open,btn_music_close,MainActivity.openMusic);
                if(MainActivity.mediaPlayer!=null && !MainActivity.mediaPlayer.isPlaying()){
                    MainActivity.mediaPlayer.start();
                }
            }
        });
        btn_music_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Music close");
                MainActivity.openMusic=false;
                changeBtnMusic(btn_music_open,btn_music_close,MainActivity.openMusic);
                if(MainActivity.mediaPlayer!=null && MainActivity.mediaPlayer.isPlaying()){
                    MainActivity.mediaPlayer.pause();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn save");
//                changeBtn(btn_save,color[0],color[1]);
//                changeBtn(btn_save,color[1],color[0]);
                save_person(person);
                Toast.makeText(SettingActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });

        btn_back2strat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn back to start");
                Intent back2start = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(back2start);
            }
        });

        btn_back2game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void changeBtnMusic(Button btn_music_open,Button btn_music_close,boolean openMusic) {
//        开启状态：
//        background color：color[0]=#BDBABA
//        text color：color[1]=#79636363
//        关闭状态：
//        background color：color[0]=#79636363
//        text color：color[1]=#BDBABA
        if (openMusic){
            changeBtn(btn_music_open,color[0],color[1]);
            changeBtn(btn_music_close,color[1],color[0]);
        }else {
            changeBtn(btn_music_close,color[0],color[1]);
            changeBtn(btn_music_open,color[1],color[0]);
        }
    }

    public void changeBtn(Button btn,String background_color, String text_color){
//        点击：
//        background color：color[0]=#BDBABA
//        text color：color[1]=#79636363
//        不操作：
//        background color：color[0]=#79636363
//        text color：color[1]=#BDBABA
        btn.setBackgroundColor(Color.parseColor(background_color));
        btn.setTextColor(Color.parseColor(text_color));
    }

    public void save_person(Person person) {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putInt("money",person.getMoney());
        editor.putInt("good_point",person.getGoodPoint());
        editor.putInt("item_phone", person.items[0].getNumber());
        editor.putInt("item_charge",person.items[1].getNumber());
        editor.putInt("item_cat_book",person.items[2].getNumber());
        editor.putInt("item_fish",person.items[3].getNumber());
        editor.putInt("item_cat_toy",person.items[4].getNumber());
        editor.putInt("item_quest",person.items[5].getNumber());
        editor.putInt("item_quest_big",person.items[6].getNumber());
        editor.putInt("goodPoint_addedByMuyu",person.getGoodPoint_addedByMuyu());
        editor.apply();
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        Log.d(TAG, "load_person: "+
                "money"+pref.getInt("money",200)+" "+
                "good_point"+pref.getInt("good_point",10)+" "+
                "item_phone"+pref.getInt("item_phone",0)+" "+
                "item_charge"+pref.getInt("item_charge",0)+" "+
                "item_cat_book"+pref.getInt("item_cat_book",0)+" "+
                "item_fish"+pref.getInt("item_fish",0)+" "+
                "cat_toy"+pref.getInt("cat_toy",0)+ " "+
                "item_quest"+pref.getInt("item_quest",0)+" "+
                "item_quest_big"+pref.getInt("item_quest_big",0)+
                "goodPoint_addedByMuyu"+pref.getInt("goodPoint_addedByMuyu",0));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent: -----------------------------");
        super.onNewIntent(intent);
    }


}

