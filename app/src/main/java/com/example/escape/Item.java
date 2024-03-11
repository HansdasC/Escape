package com.example.escape;

public class Item {
    private String name;
    private int number=0;
    private int price=0;
    private String introduction="NULL";
    private boolean usable=false;
    private int imageID;
    private int addCatSatiety=0;
    private int addCatHappines=0;

    Item(String item_name, int num, int pri, int imageID, String intro){
        // 通关条件的一般物品（手机、充电器、猫的图鉴）
        this.name = item_name;
        this.number = num;
        this.price = pri;
        this.introduction = intro;
        this.imageID = imageID;
    }

    Item(String item_name, int num, int pri, int imageID, String intro, int addCatSatiety,int addCatHappines){
        // 对猫使用的物品
        this.addCatSatiety = addCatSatiety;
        this.addCatHappines = addCatHappines;
        this.name = item_name;
        this.number = num;
        this.price = pri;
        this.introduction = intro;
        this.imageID = imageID;
    }


    Item(boolean isUsable, String item_name, int num, int pri, int imageID, String intro){
        // 在背包可用的物品（大小盲盒）
        this.name = item_name;
        this.number = num;
        this.price = pri;
        this.introduction = intro;
        this.usable = isUsable;
        this.imageID = imageID;
    }

    public String getName() {
        return "【"+name+"】";
    }

    public void getItem(){
        number += 1;
    }

    public void setNumber(int num){
        number = num;
    }

    public int getNumber(){
        return number;
    }

    public int getPrice() {return price;}

    public String getIntroduction(){return "拥有数量："+number+"\n"+introduction;}

    public boolean isUsable(){return usable;}

    public int getImageID() {
        return imageID;
    }

    public int getAddCatHappines() {
        return addCatHappines;
    }

    public int getAddCatSatiety() {
        return addCatSatiety;
    }
}

