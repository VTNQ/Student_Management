/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_teacher.model;

/**
 *
 * @author tranp
 */
public class Exam {
    private String StartTime;
    private String EndTime;
    private String name_class;
    private String name_subject;
    private String link_Examp;
    private boolean IsActive;
    private String name_Student;

    public Exam(String name_Student) {
        this.name_Student = name_Student;
    }

    public String getName_Student() {
        return name_Student;
    }

    public void setName_Student(String name_Student) {
        this.name_Student = name_Student;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getLink_Examp() {
        return link_Examp;
    }

    public void setLink_Examp(String link_Examp) {
        this.link_Examp = link_Examp;
    }

    public boolean isIsActive() {
        return IsActive;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }

    public Exam(int id,String StartTime, String EndTime, String name_class, String name_subject, String link_Examp) {
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.name_class = name_class;
        this.name_subject = name_subject;
        this.link_Examp = link_Examp;
        this.IsActive=true;
        this.id=id;
    }
}
