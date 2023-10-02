/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class myclass {
    private String myclass;
    private int id;
    private boolean Joinactive;

    public myclass(String myclass,int id) {
            this.myclass = myclass;
        this.Joinactive = true;
        this.id=id;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }

    public boolean isJoinactive() {
        return Joinactive;
    }

    public void setJoinactive(boolean Joinactive) {
        this.Joinactive = Joinactive;
    }
}
