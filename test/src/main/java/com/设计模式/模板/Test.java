package com.设计模式.模板;

public class Test extends AbsTest implements InterfaceTest{


    public Test() {
        super();
    }

    @Override
    public int getAge() {
        String s = getResult(null);
        System.out.println(s);
        return 0;
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.getAge();
    }

    @Override
    public int add(int m) {
        return 0;
    }

    @Override
    public int del(int m) {
        return 0;
    }
}
