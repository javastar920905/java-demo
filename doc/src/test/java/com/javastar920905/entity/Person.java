package com.javastar920905.entity;

/**
 * @author ouzhx on 2018/10/17.
 * <p>
 * 元素的自然排序，也可称为默认排序: 元素需要实现Comparable接口，覆盖compareTo 方法。
 */
public class Person implements Comparable {

    private String name;
    private int age;
    private String gender;

    /**
     * 年龄按照首要条件，年龄相同再比姓名
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        System.out.println(this + " compareTo:" + p);
        //升序排序
        if (this.age > p.age) {
            return 1;
        }
        if (this.age < p.age) {
            return -1;
        }

        //年龄相同 按照姓名排序
        return this.name.compareTo(p.name);
    }

    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", gender=" + gender
                + "]";
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
