package com.academics.hospitalmanagementsystem;

public class Medicine {
    public String name;
    public String description;
    public double price;
    public int imageResId;

    public Medicine(String name, String description,double price, int imageResId){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }
}
