package com.team_fortune.student_management_admin.model;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String username;
    private String phone;
    private Date since;
    private String password;
    private boolean status;
    private boolean isActive;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Student() {
    }

    public Student(int id, String name, String username, String phone, Date since, String password, boolean status) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.since = since;
        this.password = password;
        this.status = status;
        this.isActive=true;
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
