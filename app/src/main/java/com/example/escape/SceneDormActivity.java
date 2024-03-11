package com.example.escape;

import static com.example.escape.MainActivity.person;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SceneDormActivity extends AppCompatActivity {
    public static final String TAG = "SceneDormActivity";
    public static Cat cat = new Cat(person.getGoodPoint());
    ImageView imageView_scene;
    TextView textView_dialogue,textView_money,textView_good_point;
    Button btn_dialogue_choose1;
    Button btn_dialogue_choose2;
    Button btn_dialogue_choose_no;
    ImageButton imageBtn_setting;
    ImageButton imageBtn_bag;
    ImageButton imageBtn_map;
    ImageButton imageBtn_cat;

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== android.view.KeyEvent.KEYCODE_BACK)
            return true;

        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_dorm);
        String money= String.valueOf(person.getMoney());
        String good_point = String.valueOf(person.getGoodPoint());

        imageView_scene = findViewById(R.id.imageview_scene1);
        textView_dialogue = findViewById(R.id.textView_dialog);
        textView_money = findViewById(R.id.textView_money);
        textView_good_point = findViewById(R.id.textView_goodpoint);
        btn_dialogue_choose1 = findViewById(R.id.btn_dialogue_choose1);
        btn_dialogue_choose2 = findViewById(R.id.btn_dialogue_choose2);
        btn_dialogue_choose_no = findViewById(R.id.btn_dialogue_choose_no);
        imageBtn_bag = findViewById(R.id.image_btn_bag);
        imageBtn_map = findViewById(R.id.image_btn_map);
        imageBtn_setting = findViewById(R.id.image_btn_setting);
        imageBtn_cat = findViewById(R.id.cat);

        textView_money.setText(money);
        textView_good_point.setText(good_point);

        cat.setLikePerson(person.getGoodPoint(),true);


        
        btn_dialogue_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_dialogue_choose1.getText()=="查看收藏"){
                    if(cat.getLikePerson()>90){
                        // 好感度大于90，获得【手机】
                        person.items[0].getItem();
                        textView_dialogue.setText(" 猫猫觉得你是个好人！\n获得【手机】！");
                    }else {
                        textView_dialogue.setText(" 猫猫觉得你不像好人！\n或许需要跟它互动以努力挽回形象......");
                    }
                }else if (btn_dialogue_choose1.getText()=="喂食"){
                    if (person.items[3].getNumber()>0){
                        // 使用【逗猫棒】，增加猫的happiness
                        person.items[3].setNumber(person.items[3].getNumber()-1);
                        cat.addSatiety(person.items[3].getAddCatSatiety());
                    }else {
                        Toast.makeText(SceneDormActivity.this,"【小鱼干】数量不足",Toast.LENGTH_SHORT).show();
                    }
                    showCatStatus();
                }

            }
        });

        btn_dialogue_choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_dialogue_choose2.getText()=="互动"){
                    btn_dialogue_choose1.setText("喂食");
                    btn_dialogue_choose2.setText("逗猫");
                    textView_dialogue.setText("【互动中】");
                    showCatStatus();
                }else if(btn_dialogue_choose2.getText()=="逗猫"){
                    if (person.items[4].getNumber()>0){
                        // 使用【逗猫棒】，增加猫的happiness
                        person.items[4].setNumber(person.items[4].getNumber()-1);
                        cat.addHappiness(person.items[4].getAddCatHappines());
                    }else {
                        Toast.makeText(SceneDormActivity.this,"【逗猫棒】数量不足",Toast.LENGTH_SHORT).show();
                    }
                    showCatStatus();
                }
            }
        });

        btn_dialogue_choose_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_dialogue_choose1.setVisibility(View.INVISIBLE);
                btn_dialogue_choose2.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_no.setVisibility(View.INVISIBLE);
                textView_dialogue.setVisibility(View.INVISIBLE);
            }
        });

        imageBtn_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.VISIBLE);
                textView_dialogue.setText("一只猫\n似乎有很多收藏的物品......");
                btn_dialogue_choose1.setVisibility(View.VISIBLE);
                btn_dialogue_choose1.setText("查看收藏");
                btn_dialogue_choose2.setVisibility(View.VISIBLE);
                btn_dialogue_choose2.setText("互动");
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
            }
        });

        imageBtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(SceneDormActivity.this,SettingActivity.class);
                startActivity(setting);

            }
        });

        imageBtn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(SceneDormActivity.this,MapActivity.class);
                startActivity(map);
            }
        });

        imageBtn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bag = new Intent(SceneDormActivity.this,BagActivity.class);
                startActivity(bag);
            }
        });

    }

    public void showCatStatus(){
        if (person.items[2].getNumber()>0){
            // 有猫图鉴，显示猫的状态
            textView_dialogue.setText(cat.getStatus());
        }
    }
}