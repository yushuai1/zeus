public class TestEquery {

    private int age;
    private String name;

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
        return "TestEquery{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (!(anObject instanceof TestEquery)) {
            return false;
        }

        TestEquery s = (TestEquery)anObject;


        return this.toString().equals(s.toString());
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
