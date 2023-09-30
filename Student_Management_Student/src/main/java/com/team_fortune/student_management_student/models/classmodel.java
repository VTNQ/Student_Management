/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class classmodel {
   private String name;
    private int id;
     private Boolean isActive;
   private Boolean detail;
   private String name_subject;
   private String name_teacher;

    public classmodel(String name_subject, String name_teacher) {
        this.name_subject = name_subject;
        this.name_teacher = name_teacher;
    }
   
   

    public Boolean getDetail() {
        return detail;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getName_teacher() {
        return name_teacher;
    }

    public void setName_teacher(String name_teacher) {
        this.name_teacher = name_teacher;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }
public classmodel(int id,String name) {
        this.id=id;
        this.name = name;
        this.isActive = true;
        this.detail=true;
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    
    
}
