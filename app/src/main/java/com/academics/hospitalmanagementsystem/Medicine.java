package com.academics.hospitalmanagementsystem;

import java.io.Serializable;

public class Medicine implements Serializable {
    public String name;
    public String description;
    public double price;
    public String imageResId;
    public int imageDrawableId;
    public int quantity = 1;

    public Medicine(){}

    public Medicine(String name, String description,double price, String imageResId){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }
}
