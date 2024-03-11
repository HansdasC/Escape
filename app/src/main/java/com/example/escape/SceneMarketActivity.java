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
import android.widget.Toast;

public class SceneMarketActivity extends AppCompatActivity {
    public static final String TAG = "SceneMarketActivity";
    int buy=0;
    /*
    buy
    0: phone
    1: charge
    2: cat_book
    3: fish
    4: cat_toy
    5: quest
    6: quest_big
    7: 敲木鱼
     */
    ImageView imageView_scene;
    TextView textView_dialogue,textView_money,textView_good_point;
    Button btn_dialogue_choose_yes;
    Button btn_dialogue_choose_no;
    ImageButton imageBtn_setting;
    ImageButton imageBtn_bag;
    ImageButton imageBtn_map;
    ImageButton imageBtn_fish, imageBtn_catBook, imageBtn_catToy, imageBtn_charge, imageBtn_quest, imageBtn_questBig, imageBtn_muyu;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_market);
        String money= String.valueOf(person.getMoney());
        String good_point = String.valueOf(person.getGoodPoint());

        imageView_scene = findViewById(R.id.imageview_scene_market);
        textView_dialogue = findViewById(R.id.textView_dialog_market);
        textView_money = findViewById(R.id.textView_money_market);
        textView_good_point = findViewById(R.id.textView_goodpoint_market);

        btn_dialogue_choose_yes = findViewById(R.id.btn_dialogue_choose_yes_market);
        btn_dialogue_choose_no = findViewById(R.id.btn_dialogue_choose_no_market);

        imageBtn_bag = findViewById(R.id.image_btn_bag_market);
        imageBtn_map = findViewById(R.id.image_btn_map_market);
        imageBtn_setting = findViewById(R.id.image_btn_setting_market);

        imageBtn_charge = findViewById(R.id.image_btn_charge_market); // item[1] charge
        imageBtn_catBook = findViewById(R.id.image_btn_cat_book_market); // item[2] cat book
        imageBtn_fish = findViewById(R.id.image_btn_fish_market); // item[3] fish
        imageBtn_catToy = findViewById(R.id.image_btn_cat_toy_market); // item[4] cat toy
        imageBtn_quest = findViewById(R.id.image_btn_quest_market);
        imageBtn_questBig = findViewById(R.id.image_btn_quest_big_market);
        imageBtn_muyu = findViewById(R.id.image_btn_muyu_market);

        textView_money.setText(money);
        textView_good_point.setText(good_point);

        btn_dialogue_choose_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: btn_dialogue_choice_yes");
                buyItem(buy);
            }
        });

        btn_dialogue_choose_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 0;
                btn_dialogue_choose_yes.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_no.setVisibility(View.INVISIBLE);
                textView_dialogue.setText("欢迎光临，这里是【教育超市】\n\n请选择您感兴趣的商品......");
            }
        });

        imageBtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(SceneMarketActivity.this,SettingActivity.class);
                startActivity(setting);

            }
        });

        imageBtn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(SceneMarketActivity.this,MapActivity.class);
                startActivity(map);
            }
        });

        imageBtn_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bag = new Intent(SceneMarketActivity.this,BagActivity.class);
                startActivity(bag);
            }
        });

        /*
        buy
        0: phone
        1: charge
        2: cat_book
        3: fish
        4: cat_toy
        5: quest
        6: quest_big
         */
        imageBtn_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 1;
                set_itemMarketIntro(buy);
            }
        });

        imageBtn_catBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 2;
                set_itemMarketIntro(buy);
            }
        });

        imageBtn_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 3;
                set_itemMarketIntro(buy);
            }
        });

        imageBtn_catToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 4;
                set_itemMarketIntro(buy);
            }
        });


        imageBtn_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 5;
                set_itemMarketIntro(buy);
            }
        });

        imageBtn_questBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buy = 6;
                set_itemMarketIntro(buy);
            }
        });

        imageBtn_muyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_dialogue_choose_yes.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                textView_dialogue.setText("【木鱼】\n价格：2\n" +
                        "\n虔诚地敲一敲可以增加功德，\n或许会让你看起来更像个好人，\n也或许会让动物更亲近你..." +
                        "\n非卖品，但是支付一定金额可以敲一次...嗯...直接点击木鱼即可...");
                if(buy == 7){
                    if(person.getGoodPoint_addedByMuyu()<=20)
                    {
                        person.setGoodPoint(person.getGoodPoint()+1);
                        if (person.getMoney()>=2){
                            person.setMoney(person.getMoney()-2);
                            person.setGoodPoint(person.getGoodPoint()+1);
                            person.setGoodPoint_addedByMuyu(person.getGoodPoint_addedByMuyu()+1);
                            textView_good_point.setText(String.valueOf(person.getGoodPoint()));
                            textView_money.setText(String.valueOf(person.getMoney()));
                            textView_dialogue.setText("功德+1");
                        }
                        else{
                            textView_dialogue.setText("余额不足");
                        }
                    }else {
                        textView_dialogue.setText("敲的太多了，木鱼觉得你不虔诚");
                    }
                }
                buy = 7;
            }
        });
    }

    protected void buyItem(int buy){
        int price = person.items[buy].getPrice();
        if (person.getMoney()>=price){
            person.items[buy].getItem();
            person.setMoney(person.getMoney()-price);
            textView_money.setText(String.valueOf(person.getMoney()));
            textView_dialogue.setText("购买成功，余额-" +price+"\n拥有数量："+person.items[buy].getNumber()+
                    "\n再来一个？");
            btn_dialogue_choose_yes.setVisibility(View.VISIBLE);
        }
        else {
            textView_dialogue.setText("余额不足");
        }
    }

    public void set_itemMarketIntro(int buy){
        btn_dialogue_choose_yes.setVisibility(View.VISIBLE);
        btn_dialogue_choose_no.setVisibility(View.VISIBLE);
        textView_dialogue.setText(person.items[buy].getName() +"\n价格:"+person.items[buy].getPrice()+"\n"+person.items[buy].getIntroduction());
    }

}