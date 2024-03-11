package com.example.escape;

import static com.example.escape.MainActivity.person;

import android.widget.Toast;

public class Person {
    private int money;
    private int good_point;
    public Item[] items = new Item[7];
    private int goodPoint_addedByMuyu;

    Person(){
        money = 300;
        good_point = 20;
        setItems(0,0,0,0,0,0,0);
        goodPoint_addedByMuyu = 0;
    }

    Person(int init_money, int init_good_point, int num_phone, int num_charge,
           int num_cat_book, int num_fish,int num_cat_toy,int num_quest, int num_quest_big, int num_add_goodPoint_by_muyu){
        money = init_money;
        good_point = init_good_point;
        setItems(num_phone, num_charge, num_cat_book, num_fish, num_cat_toy, num_quest, num_quest_big);
        goodPoint_addedByMuyu = num_add_goodPoint_by_muyu;
    }

    private void setItems(int num_phone,int num_charge,int num_cat_book,int num_fish,
                          int num_cat_toy,int num_quest,int num_quest_big){
        items[0] = new Item("手机", num_phone,1000,R.drawable.item_phone,
                "\n可作为通讯设备使用，但似乎也有其他用途。\n但电池损坏经常，需要时刻充电...");
        items[1] = new Item("充电器", num_charge,100,R.drawable.item_charge,
                "\n很神奇的事情是它能给通讯设备充电......." +
                "\n包括手机......");
        items[2] = new Item("猫的图鉴", num_cat_book,40,R.drawable.item_cat_book,
                "\n记录了很多猫的生活习惯......\n似乎可以帮助你看透猫的心思......");
        items[3] = new Item("小鱼干", num_fish,10,R.drawable.item_fish,
                "\n猫很喜欢的食物......\n似乎喂食可以增进与猫的感情......\n人也能吃......",
                5,0);
        items[4] = new Item("逗猫棒", num_cat_toy,20,R.drawable.item_cat_toy,
                "\n猫很喜欢的玩具。\n似乎用其玩耍可以增进与猫的感情......\n但是由于存放时间过长，容易损坏......",
                0,10);
        items[5] = new Item(true,"普通的盲盒",num_quest,50,R.drawable.item_quest,
                "\n以前流行的一种商品......\n似乎普通商品做成盲盒也能卖的很贵...\n但也有几率开出好东西...");
        items[6] = new Item(true,"豪华的盲盒",num_quest_big,80, R.drawable.item_quest_big,
                "\n以前流行的一种商品......\n似乎普通商品做成盲盒也能卖的很贵......\n相比普通盲盒有更大几率开出更贵的物品......");
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int new_money){
        money = new_money;
    }

    public int getGoodPoint(){
        return good_point;
    }

    public void setGoodPoint(int new_good_point){
            good_point = new_good_point;
    }

    public int getGoodPoint_addedByMuyu(){return goodPoint_addedByMuyu;}

    public void setGoodPoint_addedByMuyu(int i){goodPoint_addedByMuyu=i;}



}
