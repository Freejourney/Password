package com.example.admin.password;

import java.io.Serializable;

/**
 * Created by mamadhaxor on 22/01/15.
 */
public class ItemsModel implements Serializable
{
    private int image;

    public ItemsModel(int image)
    {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
