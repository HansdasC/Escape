package com.example.escape;

import static com.example.escape.MainActivity.person;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SceneLabActivity extends AppCompatActivity {
    public static final String TAG = "SceneLabActivity";
    ImageView imageView_scene;
    TextView textView_dialogue,textView_money,textView_good_point;
    Button btn_dialogue_choose_yes;
    Button btn_dialogue_choose_no;
    Button btn_time_machine,btn_computer,btn_files;
    ImageButton imageBtn_setting;
    ImageButton imageBtn_bag;
    ImageButton imageBtn_map,imageBtn_post;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_lab);
        String money= String.valueOf(person.getMoney());
        String good_point = String.valueOf(person.getGoodPoint());

        imageView_scene = findViewById(R.id.imageview_scene_lab);
        textView_dialogue = findViewById(R.id.textView_dialog_lab);
        textView_money = findViewById(R.id.textView_money_lab);
        textView_good_point = findViewById(R.id.textView_goodpoint_lab);

        btn_dialogue_choose_yes = findViewById(R.id.btn_dialogue_choose_yes_lab);
        btn_dialogue_choose_no = findViewById(R.id.btn_dialogue_choose_no_lab);
        btn_time_machine = findViewById(R.id.btn_time_machine_lab);
        btn_computer = findViewById(R.id.btn_computer_lab);
        btn_files = findViewById(R.id.btn_files_lab);

        imageBtn_bag = findViewById(R.id.image_btn_bag_lab);
        imageBtn_map = findViewById(R.id.image_btn_map_lab);
        imageBtn_setting = findViewById(R.id.image_btn_setting_lab);
        imageBtn_post = findViewById(R.id.image_btn_post_lab);

        textView_money.setText(money);
        textView_good_point.setText(good_point);



        btn_dialogue_choose_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn_dialogue_choice_yes");
                if(person.items[1].getNumber()!=0){
//                    textView_dialogue.setText("时间机器正在重启");
//                    textView_dialogue.setText("时间机器正在重启.");
//
//                    textView_dialogue.setText("时间机器正在重启..");

                    textView_dialogue.setText("时间机器正在重启...");
                    Intent end = new Intent(SceneLabActivity.this, EndActivity.class);
                    startActivity(end);

                }else {
                    textView_dialogue.setText("【手机】似乎关机了......\n可能是因为电量不足......\n或许【充电器】可以有所帮助......");
                }
            }
        });

        btn_dialogue_choose_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_no.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_yes.setVisibility(View.INVISIBLE);
            }
        });

        btn_time_machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.VISIBLE);
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                if(person.items[0].getNumber()!=0){
                    textView_dialogue.setText("【微波炉】似乎可以配合【手机】使用...\n好像可以组成一种类似【电话微波炉】的时间机器......");
                    btn_dialogue_choose_yes.setVisibility(View.VISIBLE);
                    btn_dialogue_choose_no.setText("取消");
                    btn_dialogue_choose_yes.setText("使用手机");
                } else {
                    textView_dialogue.setText("好像是一个微博炉......\n" +
                            "很奇怪的微波炉...\n" +
                            "似乎能运行，但目前没什么用处...\n");
                }
            }
        });

        btn_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.VISIBLE);
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                textView_dialogue.setText("好像是一些文件......\n" +
                        "但是没什么有价值的信息...\n" );
            }
        });

        btn_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.VISIBLE);
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                textView_dialogue.setText("一台老式电脑......\n" +
                        "很遗憾，已经损坏了......\n" );
            }
        });


        imageBtn_post.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.VISIBLE);
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                textView_dialogue.setText("这是一张海报......\n" +
                        "《命运石之门》系列的一部作品...\n" +
                        "似乎提到过电话微波炉一类的时间机器...\n" +
                        "手机或许也可以？但没电的话也没办法吧...");
            }
        });


        imageBtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(SceneLabActivity.this,SettingActivity.class);
                startActivity(setting);

            }
        });

        imageBtn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(SceneLabActivity.this,MapActivity.class);
                startActivity(map);
            }
        });

        imageBtn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bag = new Intent(SceneLabActivity.this,BagActivity.class);
                startActivity(bag);
            }
        });

    }



}