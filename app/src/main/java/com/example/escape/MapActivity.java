package com.example.escape;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_map_yes, btn_map_no;
    ImageButton imageBtn_locat_dorm,imageBtn_locat_market,imageBtn_locat_lab;
    TextView textView_map_dialogue;
    int go = 0; // 1-dorm,2-market,3-lab

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btn_map_yes = findViewById(R.id.btn_map_yes);
        btn_map_no = findViewById(R.id.btn_map_no);
        imageBtn_locat_dorm = findViewById(R.id.image_btn_location_dorm);
        imageBtn_locat_market = findViewById(R.id.image_btn_location_market);
        imageBtn_locat_lab = findViewById(R.id.image_btn_location_lab);
        textView_map_dialogue = findViewById(R.id.textView_map_dialog);
        imageBtn_locat_dorm.setOnClickListener(this);
        imageBtn_locat_market.setOnClickListener(this);
        imageBtn_locat_lab.setOnClickListener(this);
        btn_map_no.setOnClickListener(this);
        btn_map_yes.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_btn_location_dorm:
                go = 1;
                imageBtn_locat_dorm.setImageResource(R.drawable.location);
                imageBtn_locat_market.setImageResource(R.drawable.location_new);
                imageBtn_locat_lab.setImageResource(R.drawable.location_new);
                textView_map_dialogue.setText(
                        "【宿舍】\n" +
                                "似乎曾经是一个学校宿舍......\n" +
                                "有一只看起来很凶的猫，霸占了很多物品......\n" +
                                "似乎包含【手机】一类的通讯设备......" +
                                "\n试着讨好它或许能够拿回......");
                btn_map_yes.setVisibility(View.VISIBLE);
                break;
            case R.id.image_btn_location_market:
                go = 2;
                imageBtn_locat_dorm.setImageResource(R.drawable.location_new);
                imageBtn_locat_market.setImageResource(R.drawable.location);
                imageBtn_locat_lab.setImageResource(R.drawable.location_new);
                textView_map_dialogue.setText(
                        "【教育超市】\n" +
                                "这是以前人们消费购买【商品】的地方，\n" +
                                "如今已经无人光顾，\n" +
                                "但或许还能买到需要但东西......");
                btn_map_yes.setVisibility(View.VISIBLE);
                break;
            case R.id.image_btn_location_lab:
                go = 3;
                imageBtn_locat_dorm.setImageResource(R.drawable.location_new);
                imageBtn_locat_market.setImageResource(R.drawable.location_new);
                imageBtn_locat_lab.setImageResource(R.drawable.location);
                textView_map_dialogue.setText(
                        "【实验室】\n" +
                                "这里似乎存放这一些杂乱无章的器材，\n" +
                                "或许会有【时间机器】的线索......");
                btn_map_yes.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_map_no:
                if (go==0){
                    finish();
                }
                else {
                    go = 0;
                    imageBtn_locat_dorm.setImageResource(R.drawable.location_new);
                    imageBtn_locat_market.setImageResource(R.drawable.location_new);
                    imageBtn_locat_lab.setImageResource(R.drawable.location_new);
                    textView_map_dialogue.setText("请选择想前往的位置......");
                    btn_map_yes.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.btn_map_yes:
                switch (go){
                    case 1:
                        Intent go_drom = new Intent(MapActivity.this,SceneDormActivity.class);
                        startActivity(go_drom);
                        break;
                    case 2:
                        Intent go_market = new Intent(MapActivity.this,SceneMarketActivity.class);
                        startActivity(go_market);
                        break;
                    case 3:
                        Intent go_lab = new Intent(MapActivity.this,SceneLabActivity.class);
                        startActivity(go_lab);
                        break;
                    default:
                        break;
                }

        }
    }
}