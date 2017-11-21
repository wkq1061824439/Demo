package com.example.asus.mysqlitedemo.bean;

/**
 * Created by ASUS on 2017/11/8.
 */

public class User {
    private Integer id;

    private String name;

    private String grade;

    @Override
    public String toString() {
        return "User{" +
                "grade='" + grade + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

        this.grade = grade;
        this.id = id;
        this.name = name;
    }
}
