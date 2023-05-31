package com.example.demo.drive;

public class Computer extends AbstractComputer {
    private Drive drive;
    public Computer(Drive drive) {
        super(drive);
        this.drive=drive;
    }

    @Override
    public void save(String s) {
        drive.save(s);
    }
}
