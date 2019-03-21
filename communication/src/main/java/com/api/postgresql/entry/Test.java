package com.api.postgresql.entry;

public class Test {

    private int id;


    private String name;
    private int age;

    private String classes;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test() {
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}
