package com.example.demo.drive;

public abstract class AbstractComputer {
    private Drive drive;

    public AbstractComputer(Drive drive) {
        this.drive = drive;
    }

    public abstract void save(String s);
}
