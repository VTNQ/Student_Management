package com.team_fortune.student_management_teacher.model;

public class Class {
    private int id;
    private String name;
    private String name_student;
    private Boolean isActive;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }
    public Class() {
    }

    

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.isActive=true;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
