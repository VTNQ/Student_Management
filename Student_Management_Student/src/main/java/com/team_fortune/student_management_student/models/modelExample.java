/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class modelExample {

private String nameSubject;
private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
private String Examp;
private String classmy;
private String Home;
private boolean button;

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }
    public modelExample( String nameSubject,String Home, String Examp, String classmy,int id) {
        this.nameSubject = nameSubject;
        this.Examp = Examp;
        this.Home=Home;
        this.button=true;
        this.classmy = classmy;
        this.id=id;
    }

   
    public String getHome() {
        return Home;
    }

    public void setHome(String Home) {
        this.Home = Home;
    }
  

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getExamp() {
        return Examp;
    }

    public void setExamp(String Examp) {
        this.Examp = Examp;
    }

    public String getClassmy() {
        return classmy;
    }

    public void setClassmy(String classmy) {
        this.classmy = classmy;
    }

}
