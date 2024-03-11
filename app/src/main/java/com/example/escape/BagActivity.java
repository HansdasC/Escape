package com.example.escape;

import static com.example.escape.MainActivity.person;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BagActivity extends AppCompatActivity {
    public static final String TAG = "BagActivity";
    Button btn_back;
    TextView textView_dialogue;
    Button btn_dialogue_choose_yes, btn_dialogue_choose_no;
    private List<Item> itemBagList = new ArrayList<>();
    int use_item = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag);
        Log.d(TAG, "onCreate: -----------------------");

        btn_back = findViewById(R.id.btn_back_bag);
        btn_dialogue_choose_yes = findViewById(R.id.btn_bag_yes);
        btn_dialogue_choose_no = findViewById(R.id.btn_bag_no);
        textView_dialogue = findViewById(R.id.textView_bag_dialog);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                BagActivity.this, android.R.layout.simple_list_item_1,items_data){
//            @NonNull
//            @Override
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                TextView textView=(TextView) view.findViewById(android.R.id.text1);
//                textView.setTextColor(Color.WHITE);
//
//                return view;
//            }
//        };
        initItemBags();
        ItemsBagAdapter adapter = new ItemsBagAdapter(BagActivity.this,R.layout.items_bag,itemBagList);
        ListView listView = (ListView) findViewById(R.id.list_view_bag);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item_choosed = itemBagList.get(i); // i:position
//                Toast.makeText(BagActivity.this, String.valueOf(i),Toast.LENGTH_SHORT).show();
                use_item = i;
                if(person.items[i].isUsable()){
                    textView_dialogue.setText("是否使用该物品？");
                    textView_dialogue.setVisibility(View.VISIBLE);
                    btn_dialogue_choose_yes.setVisibility(View.VISIBLE);
                    btn_dialogue_choose_no.setVisibility(View.VISIBLE);
                }

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_dialogue_choose_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_dialogue.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_yes.setVisibility(View.INVISIBLE);
                btn_dialogue_choose_no.setVisibility(View.INVISIBLE);

            }
        });

        btn_dialogue_choose_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (use_item){
                    case 5:
                        use_quest(use_item);
                        break;
                    case 6:
                        use_quest(use_item);
                        break;
                    default:
                        textView_dialogue.setText("请在合适场景下使用");
                        break;
                }

                adapter.notifyDataSetChanged();
            }

        });

    }

    private void initItemBags() {
        int i;
        for (i=0;i<7;i++){
            itemBagList.add(person.items[i]);
        }
//        ItemBag phone = new ItemBag(getIntroBag(person.items[0]),person.items[0].getImageID());
//        itemBagList.add(phone)
//        ItemBag charge = new ItemBag(getIntroBag(person.items[1]),person.items[1].getImageID());
//        itemBagList.add(charge);
//        ItemBag cat_book = new ItemBag(getIntroBag(person.items[2]),person.items[2].getImageID());
//        itemBagList.add(cat_book);
//        ItemBag fish = new ItemBag(getIntroBag(person.items[3]),person.items[3].getImageID());
//        itemBagList.add(fish);
//        ItemBag cat_toy = new ItemBag(getIntroBag(person.items[4]),person.items[4].getImageID());
//        itemBagList.add(cat_toy);
//        ItemBag quest = new ItemBag(getIntroBag(person.items[5]),person.items[5].getImageID());
//        itemBagList.add(quest);
//        ItemBag quest_big = new ItemBag(getIntroBag(person.items[6]),person.items[6].getImageID());
//        itemBagList.add(quest_big);

    }

//    private String getIntroBag(Item item){
//        return "拥有数量："+item.getNumber()+"\n"+item.getIntroduction();
//    }

    private void use_quest(int i){
        if(person.items[i].getNumber()>0){
            int lottery = getLotter(i); // 抽奖得到的物品index[0-6]
            person.items[i].setNumber(person.items[i].getNumber()-1);
            if (lottery!=10000){
                person.items[lottery].getItem();
                textView_dialogue.setText("获得物品："+person.items[lottery].getName());
            }
        }else{
            textView_dialogue.setText("物品数量不足...请及时补充...");
        }
    }

    private int getLotter(int i) {
        // i=5 小盲盒（开不出手机0）
        // i=6 大盲盒（开得出手机）
        Random r = new Random();
        int new_item=10000;
        switch (i){
            case 5:
                // 3,4,5
                new_item = r.nextInt(2)+3;
                break;
            case 6:
                // 0,1,2,3,4,5
                new_item = r.nextInt(5);
                break;
            default:
                break;
        }
       return  new_item;
    };

}