package com.team_fortune.student_management_student.models;

import java.sql.Date;

/**
 *
 * @author tranp
 */
public class modelprimary {
    private int id;
    private String Name;
    private String username;
    private int phone;
    private Date since;
    private String password;
    private Boolean status;

    public modelprimary(int id, String Name, String username, int phone, Date since, String password, Boolean status) {
        this.id = id;
        this.Name = Name;
        this.username = username;
        this.phone = phone;
        this.since = since;
        this.password = password;
        this.status = status;
    }
    public modelprimary(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
