package com.example.chat.base.manager;

public class UserManager {

    private volatile static UserManager userManager;
    private static int ID = 1;
    private static String NAME="王智峰";


    private UserManager() {

    }

    public synchronized static UserManager getInstance() {
        if (userManager == null) {
            synchronized (UserManager.class) {
                if (userManager == null) {
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }


    public void setId(int id) {
        UserManager.ID = id;
    }

    public  void setName(String name) {
        UserManager.NAME = name;
    }

    public  int getID() {
        return ID;
    }

    public  String getNAME() {
        return NAME;
    }


}
