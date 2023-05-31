package com.example.demo.drive;

import com.example.demo.drive.Drive;

public class SolidStateDisk implements Drive {
    @Override
    public void save(String string) {
        System.out.println("使用固态硬盘保存： " + string);
    }
}
