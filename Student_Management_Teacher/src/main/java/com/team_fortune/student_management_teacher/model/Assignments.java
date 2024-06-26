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
    private int statussolution;
    private boolean seeAssignment;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Assignments(int id,String name_student, String link_student,int status,String name_Subject,String name_class,String reason) {
        this.name_student = name_student;
        this.link_student = link_student;
        this.statussolution=status;
         this.name_Subject = name_Subject;
        this.name_class = name_class;
        this.reason=reason;
        this.id=id;
    }

    public int getStatussolution() {
        return statussolution;
    }

    public void setStatussolution(int statussolution) {
        this.statussolution = statussolution;
    }
    private String name_student;
    private String link_student;

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getLink_student() {
        return link_student;
    }

    public void setLink_student(String link_student) {
        this.link_student = link_student;
    }
    private String name_Subject;
    private String name_class;
    private String Assignment;
    private boolean Status;
    private boolean Cancel;

    public boolean isCancel() {
        return Cancel;
    }

    public void setCancel(boolean Cancel) {
        this.Cancel = Cancel;
    }

   
public Assignments(){
     
 }
 public boolean isSeeAssignment() {
        return seeAssignment;
    }

    public void setSeeAssignment(boolean seeAssignment) {
        this.seeAssignment = seeAssignment;
    }
    
 
  

    public boolean getStatus() {
        return Status;
    }

    public void setStatus( boolean Status) {
        this.Status = Status;
    }
    
   
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

    public Assignments( String name_Subject, String name_class, String Assignment,int id) {
            
        this.name_Subject = name_Subject;
        this.name_class = name_class;
        this.Assignment= Assignment;
     
        this.id=id;
        this.seeAssignment=true;
    }

}
