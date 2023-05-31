package com.example.demo;

import java.util.ArrayList;

public class User {
    private String name;
    private String sex;

    private ArrayList<User> userArrayList;

    public User(String name, String sex) {
        this.name = name;
        this.sex = sex;
        userArrayList = new ArrayList<>();
    }


    public void add(User user) {
        userArrayList.add(user);
    }

    public void remove(User user) {
        userArrayList.remove(user);
    }


    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", userArrayList=" + userArrayList +
                '}';
    }
}
