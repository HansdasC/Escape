package com.example.escape;

import java.util.Random;

public class Cat {
    private int likePerson; // 0-100
    private int satiety; // 0-100
    private int happiness; // 0-100
    private int goodPoint_init;

    Cat(int goodPoint){
        Random r = new Random();
        likePerson = goodPoint;
        goodPoint_init = goodPoint;
        satiety = r.nextInt(60);
        if (satiety>50){
            happiness = r.nextInt(50);
        }
        else {
            happiness = r.nextInt(60);
        }
    }

    public int getLikePerson() {
        return likePerson;
    }

    public int getSatiety() {
        return satiety;
    }

    public int getHappiness() {
        return happiness;
    }

    protected void setLikePerson(int likePerson) {
        if(likePerson>100){
            this.likePerson = 100;
        }
        else {
            this.likePerson = likePerson;
        }
    }

    public void setLikePerson(int goodPoint, boolean from_goodPoint){
        int delta_goodPoint = goodPoint - goodPoint_init; // 前后功德差值作为好感度增量
        this.likePerson = this.likePerson + delta_goodPoint;
        if(likePerson>100){
            this.likePerson = 100;
        }
    }

    public void addHappiness(int happiness_added) {
        this.happiness = happiness+happiness_added;
        setLikePerson(this.likePerson+happiness_added);
        if (happiness>100){
            this.happiness =100;
        }
    }

    public void addSatiety(int satiety_added) {
        this.satiety = satiety+satiety_added;
        setLikePerson(this.likePerson+satiety_added);
        if(satiety>100){
            this.satiety=100;
        }
    }

    public String getStatus(){
        return  "【猫的状态】"+
                "\n好感度："+String.valueOf(likePerson)+
                "\n饱腹感："+String.valueOf(satiety)+
                "\n愉悦度："+String.valueOf(happiness);

    }


}
