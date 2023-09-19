package com.team_fortune.student_management_teacher.model;

public class Class {
    private int id;
    private String name;
    private Boolean isstatic;
    private String name_student;
    public Boolean getIsstatic() {
        return isstatic;
    }

    public void setIsstatic(Boolean isstatic) {
        this.isstatic = isstatic;
    }
    public Class(){
        
    }
    public Class(String name,String name_student) {
        this.name=name;
        this.name_student=name_student;
    }

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
        this.isstatic=true;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
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
