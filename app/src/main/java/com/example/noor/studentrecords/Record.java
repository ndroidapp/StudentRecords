package com.example.noor.studentrecords;

/**
 * Created by Noor on 2/5/2018.
 */

public class Record {
    private int roll;
    private String name,phone,email,gender;

    public Record() {
    }

    public Record(int roll, String name, String phone, String email, String gender) {
        this.roll = roll;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
