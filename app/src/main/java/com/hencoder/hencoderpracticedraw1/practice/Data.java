package com.hencoder.hencoderpracticedraw1.practice;

import android.support.annotation.ColorInt;

/**
 * Created by weizhang on 2017/11/26.
 */

public class Data {
    private String name = "";
    private int number;
    @ColorInt
    private int color;

    public Data(String name, int number, @ColorInt int color) {
        this.name = name;
        this.number = number;
        this.color = color;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
