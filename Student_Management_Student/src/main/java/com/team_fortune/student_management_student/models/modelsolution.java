/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class modelsolution {
private int index;
private String subject;
private String Myclass;
private String exercises;
private String status;
private String reason;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMyclass() {
        return Myclass;
    }

    public void setMyclass(String Myclass) {
        this.Myclass = Myclass;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public modelsolution(int index, String subject, String Myclass, String exercises, String status, String reason) {
        this.index = index;
        this.subject = subject;
        this.Myclass = Myclass;
        this.exercises = exercises;
        this.status = status;
        this.reason = reason;
    }

}
