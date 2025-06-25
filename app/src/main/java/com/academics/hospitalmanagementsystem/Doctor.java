package com.academics.hospitalmanagementsystem;

public class Doctor {
    public String name, gender, fee, hospital;
    public int experience;

    public Doctor() {} // Needed for Firebase

    public Doctor(String name, String gender, int experience, String fee, String hospital) {
        this.name = name;
        this.gender = gender;
        this.experience = experience;
        this.fee = fee;
        this.hospital = hospital;
    }
}
