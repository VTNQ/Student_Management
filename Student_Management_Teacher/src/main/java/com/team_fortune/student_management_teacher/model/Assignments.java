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
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    private String name_Subject;
    private String name_class;
    private String Assignment;
    private boolean Status;

   
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

    public Assignments( String name_Subject, String name_class, String Assignment, boolean Status,int id) {
        this.name_Subject = name_Subject;
        this.name_class = name_class;
        this.Assignment = Assignment;
        this.Status = Status;
        this.id=id;
    }

    
 public Assignments(){
     
 }
    public void setAssignment(String Assignment) {
        this.Assignment = Assignment;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus( boolean Status) {
        this.Status = Status;
    }
    
}
