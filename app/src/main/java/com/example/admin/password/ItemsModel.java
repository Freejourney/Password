package com.example.admin.password;

import android.graphics.Color;

import java.io.Serializable;

/*
 * 文件名：MySQLiteHelper
 * 作者：created by admin on 2018 十一月
 * 描述：
 */
public class ItemsModel implements Serializable
{
    private int image;
    private Pwd pwd;
    private int btn_color;

    public ItemsModel(int image, Pwd pwd, int btn_color)
    {
        this.image = image;
        this.pwd = pwd;
        this.btn_color = btn_color;
    }

    public int getBtn_color() {
        return btn_color;
    }

    public void setBtn_color(int btn_color) {
        this.btn_color = btn_color;
    }

    public Pwd getPwd() {
        return pwd;
    }

    public void setPwd(Pwd pwd) {
        this.pwd = pwd;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
