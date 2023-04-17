package com.example.chat.base;

public class User {

    private volatile static User user;
    private static int ID = 1;
    private static String NAME="王智峰";


    private User() {

    }

    public synchronized static User getInstance() {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    user = new User();
                }
            }
        }
        return user;
    }


    public void setId(int id) {
        User.ID = id;
    }

    public  void setName(String name) {
        User.NAME = name;
    }

    public  int getID() {
        return ID;
    }

    public  String getNAME() {
        return NAME;
    }


}
