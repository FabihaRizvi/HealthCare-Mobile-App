package com.academics.hospitalmanagementsystem;

public class Medicine {
    public String name;
    public String description;
    public double price;
    public int imageResId;
    public int quantity;

    public Medicine(String name, String description,double price, int imageResId, int quantity){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1;
    }
}
