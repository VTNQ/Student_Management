/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_teacher.model;

/**
 *
 * @author tranp
 */
public class Assignments {
    private String name_student;
    private String name_Subject;
    private String name_class;
    private String Assignment;
    private String Status;

   
    public String getName_Subject() {
        return name_Subject;
    }

    public void setName_Subject(String name_Subject) {
        this.name_Subject = name_Subject;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public String getAssignment() {
        return Assignment;
    }

    public Assignments(String name_student, String name_Subject, String name_class, String Assignment, String Status) {
        this.name_student= name_student;
        this.name_Subject = name_Subject;
        this.name_class = name_class;
        this.Assignment = Assignment;
        this.Status = Status;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }
 public Assignments(){
     
 }
    public void setAssignment(String Assignment) {
        this.Assignment = Assignment;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus( String Status) {
        this.Status = Status;
    }
    
}