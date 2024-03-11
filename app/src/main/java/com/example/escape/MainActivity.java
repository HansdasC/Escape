package com.example.escape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static MediaPlayer mediaPlayer;
    public static boolean openMusic=true;
    static public Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_start_game = (Button) findViewById(R.id.btn_start_game);
        Button btn_continue_game = (Button) findViewById(R.id.btn_continue_game);
        Button btn_setting = (Button) findViewById(R.id.btn_setting);
        play();

//        Log.d(TAG, "onCreate: -----------------------------");
        btn_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*------------------------------------
                                   开始游戏
                 -------------------------------------*/
                person = new Person();
                save_person(person);
                Intent introduction = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(introduction);
            }
        });
        btn_continue_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*------------------------------------
                                   读档
                 -------------------------------------*/
                person = load_person();
                Intent game_continue = new Intent(MainActivity.this,SceneDormActivity.class);
                startActivity(game_continue);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*------------------------------------
                                   设置
                 -------------------------------------*/
                Intent setting = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(setting);
            }
        });

    }

    /*
    播放音乐
     */
    public void play(){
//        String path= "res/raw";
//        File file = new File(path);
        try {
            mediaPlayer = MediaPlayer.create(this,R.raw.testbgm);
            // 指定音频流类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // 设置循环播放
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
//            Log.d(TAG, "play: ");
//            Toast.makeText(MainActivity.this,"开始播放音乐",Toast.LENGTH_SHORT).show();

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    //发生错误，重新播放
                    replay();
                    return false;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
//            Toast.makeText(this,"音乐播放失败",Toast.LENGTH_SHORT).show();
        }
    }
    /*
    暂停音乐
     */
    protected void pause(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            Toast.makeText(MainActivity.this,"暂停播放音乐",Toast.LENGTH_SHORT).show();
        }
    }

    protected void replay(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(0);
            Toast.makeText(MainActivity.this,"重新播放音乐",Toast.LENGTH_SHORT).show();
            return;
        }
        play();
    }

    /*
    停止播放
     */
    public void stop(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(MainActivity.this,"停止播放",Toast.LENGTH_SHORT).show();
            return;
        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        Log.d(TAG, "onNewIntent: -----------------------------");
//        super.onNewIntent(intent);
//    }

//    @Override
//    protected void onStart() {
//        Log.d(TAG, "onStart: -----------------------------");
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        Log.d(TAG, "onResume: -----------------------------");
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d(TAG, "onPause: -----------------------------");
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d(TAG, "onStop: -----------------------------");
//        super.onStop();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d(TAG, "onDestroy: ----person-------------------------");
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
             mediaPlayer.stop();
             mediaPlayer.release();
             mediaPlayer = null;
        }
    }

    @Override
    protected void onRestart() {
//        Log.d(TAG, "onRestart: -----------------------------");
        super.onRestart();
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
        editor.putInt("goodPoint_addedByMuyu", person.getGoodPoint_addedByMuyu());
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

    public Person load_person(){
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
        person = new Person(
                pref.getInt("money",200),
                pref.getInt("good_point",10),
                pref.getInt("item_phone",0),
                pref.getInt("item_charge",0),
                pref.getInt("item_cat_book",0),
                pref.getInt("item_fish",0),
                pref.getInt("item_cat_toy",0),
                pref.getInt("item_quest",0),
                pref.getInt("item_quest_big",0),
                pref.getInt("goodPoint_addedByMuyu",0)
                );
        return person;
    }
}