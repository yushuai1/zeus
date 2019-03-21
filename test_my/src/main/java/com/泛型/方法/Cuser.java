package com.泛型.方法;

import java.io.Serializable;

public class Cuser{
    private int age;

    private String name;

    public Cuser(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cuser{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
