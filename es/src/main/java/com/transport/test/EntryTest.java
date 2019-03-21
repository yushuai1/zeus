package com.transport.test;

import java.util.Random;
import java.util.UUID;

import static com.transport.test.utill.getString;

public class EntryTest {

    private String name =getString();

    private String url = UUID.randomUUID().toString().replace("-","");

    private int count = new Random().nextInt();

    private double age = new Random().nextDouble();

    private int length = 4;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
