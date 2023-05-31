package com.example.demo.drive;

import com.example.demo.drive.Drive;

public class HardDiskDrive implements Drive {
    @Override
    public void save(String string) {
        System.out.println("使用机械硬盘保存： "+string);
    }
}
