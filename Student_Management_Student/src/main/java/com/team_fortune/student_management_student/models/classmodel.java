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
    private int id_Subject;
    private int id_teacher;
    private int Active;

    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }
    
    public int getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(int id_Subject) {
        this.id_Subject = id_Subject;
    }
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
public classmodel(int id,String name,int id_subject,int id_teacher) {
        this.id=id;
        this.name = name;
        this.isActive = true;
        this.detail=true;
        this.id_Subject=id_subject;
        this.id_teacher=id_teacher;
    }
public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }
    public classmodel( int id,String name,int Active) {
        this.name = name;
        this.id = id;
        this.Active=Active;
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
