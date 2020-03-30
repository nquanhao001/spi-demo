package com.niugege.jdkproxy;

public class HelloImpl implements IHello {
    @Override
    public void sayHello() {
        System.out.println("hello 输出了");
    }
}
