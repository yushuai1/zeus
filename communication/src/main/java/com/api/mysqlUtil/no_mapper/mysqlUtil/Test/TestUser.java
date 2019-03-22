package com.api.mysqlUtil.no_mapper.mysqlUtil.Test;

public class TestUser {

    private String name;
    private int id;
    private int age;
    private String classes;

    public TestUser(String name, int id, int age, String classes) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.classes = classes;
    }

    public TestUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", classes='" + classes + '\'' +
                '}';
    }
}
