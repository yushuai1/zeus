package entry.test;

import java.util.Date;

public class People {
    private int age;
    private String name;
    private Date birDate;

    public People(int age, String name, Date birDate) {
        this.age = age;
        this.name = name;
        this.birDate = birDate;
    }

    public People(){

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

    public Date getBirDate() {
        return birDate;
    }

    public void setBirDate(Date birDate) {
        this.birDate = birDate;
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", birDate=" + birDate +
                '}';
    }
}
