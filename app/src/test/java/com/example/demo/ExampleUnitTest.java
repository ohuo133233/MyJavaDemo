package com.example.demo;

import org.junit.Test;

import com.example.demo.adapter.DC5;
import com.example.demo.adapter.PowerAdapter;
import com.example.demo.drive.Computer;
import com.example.demo.drive.HardDiskDrive;
import com.example.demo.drive.SolidStateDisk;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void drive() {

        HardDiskDrive hardDiskDrive = new HardDiskDrive();
        SolidStateDisk solidStateDisk = new SolidStateDisk();

        Computer hardDiskDriveComputer = new Computer(hardDiskDrive);
        Computer solidStateDiskComputer = new Computer(solidStateDisk);

        hardDiskDriveComputer.save("这段文字");
        solidStateDiskComputer.save("这段文字");
    }

    @Test
    public void adapter() {

        DC5 dc5 = new PowerAdapter();
        dc5.outputDC5V();
    }

    @Test
    public void textComposite() {
        User wang = new User("姓王的集合", "男");
        User wang2 = new User("王二", "男");
        User wang3 = new User("王三", "男");
        User wang4 = new User("王四", "男");

        wang.add(wang2);
        wang.add(wang3);
        wang.add(wang4);

        User li = new User("姓李的集合", "男");
        User li2 = new User("李二", "男");
        User li3 = new User("李三", "男");
        User li4 = new User("李四", "男");

        li.add(li2);
        li.add(li3);
        li.add(li4);

        System.out.println("输出姓王的： "+wang.getUserArrayList());
        System.out.println("输出姓李的： "+li.getUserArrayList());
    }
}