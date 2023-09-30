/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class modelScore {
    private String name_Subject;
    private String score;
    private String name_class;
    private String status;

    public String getName_Subject() {
        return name_Subject;
    }

    public void setName_Subject(String name_Subject) {
        this.name_Subject = name_Subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public modelScore(String name_Subject, String score, String name_class, String status) {
        this.name_Subject = name_Subject;
        this.score = score;
        this.name_class = name_class;
        this.status = status;
    }
    
}
