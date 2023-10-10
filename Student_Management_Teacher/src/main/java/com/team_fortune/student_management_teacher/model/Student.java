package com.team_fortune.student_management_teacher.model;

import java.sql.Date;

public class Student {

    private int id;
    private String name;
    private String username;
    private String phone;
    private Date since;
    private int id_Class;

    public int getId_Class() {
        return id_Class;
    }

    public void setId_Class(int id_Class) {
        this.id_Class = id_Class;
    }
    private String password;
    private boolean status;
    private boolean isActive;
    private int Active;
    private String link_Examp;
    private Float score;

    public Student(int id, int status, String link_Examp, Float score, String name_student) {
        this.id = id;
        this.Active = status;
        this.link_Examp = link_Examp;
        this.score = score;
        this.name_student = name_student;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getLink_Examp() {
        return link_Examp;
    }

    public void setLink_Examp(String link_Examp) {
        this.link_Examp = link_Examp;
    }
    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }
    private String name_student;
    private String name_class;

    public Student(int id,int id_class, String name_student, String name_class,int Active) {
        this.name_student = name_student;
        this.name_class = name_class;
        this.Active = Active;
        this.id = id;
        this.id_Class=id_class;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Student() {
    }

    public Student(int id, String name, String username, String phone, String password, boolean status) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
       
        this.password = password;
        this.status = status;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public Date getSince() {
        return since;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
