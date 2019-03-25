package com.api.mysqlUtil.Test;

import java.util.HashMap;
import java.util.Map;

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


    public static void main(String[] args) {

        Map<String,String> mm = new HashMap<>();
        mm.put("asdasdasasd","asd");
        mm.put("asdasdasad","asd");

        for (Map.Entry<String,String> entry:mm.entrySet()){
            String k = entry.getKey();
            if (k.equals("asdasdasad")){
                continue;
            }else {
                mm.remove(k);
            }
        }

        System.out.println(mm.size());
    }
}
