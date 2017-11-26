package com.hencoder.hencoderpracticedraw1.practice;

/**
 * Created by weizhang on 2017/11/26.
 */

public class Data {
    String name = "";
    int number;

    public Data(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
