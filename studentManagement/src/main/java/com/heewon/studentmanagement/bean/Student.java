package com.heewon.studentmanagement.bean;

import com.heewon.studentmanagement.Gender;

import java.time.LocalDateTime;

public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;
    public Student(){}
    public Student(Student student){
        this.id = student.id;
        this.name = student.name;
        this.gender = student.gender;
        this.age = student.age;
        this.createdAt = student.createdAt;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public Gender getGender(){
        return this.gender;
    }
    public int getAge(){
        return this.age;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
}
