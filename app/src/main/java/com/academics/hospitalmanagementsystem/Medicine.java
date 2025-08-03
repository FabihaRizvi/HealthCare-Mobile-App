package com.academics.hospitalmanagementsystem;

public class Medicine {
    public String name;
    public String description;
    public double price;
    public String imageResId;
    public int imageDrawableId;
    public int quantity;

    public Medicine(){}

    public Medicine(String name, String description,double price, String imageResId, int quantity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1;
    }
}
