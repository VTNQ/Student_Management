package com.team_fortune.student_management_teacher.model;

public class Class {
    private int id;
    private String name;

    public Class() {
    }

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
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
