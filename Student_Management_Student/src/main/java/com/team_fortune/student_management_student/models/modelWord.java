/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.models;

/**
 *
 * @author tranp
 */
public class modelWord {
private String name_subject;
private String name_teacher;
private String World;
private String name_class;

    public modelWord( String name_subject, String name_teacher, String World, String name_class) {
        this.name_subject = name_subject;
        this.name_teacher = name_teacher;
        this.World = World;
        this.name_class = name_class;
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

    public String getWorld() {
        return World;
    }

    public void setWorld(String World) {
        this.World = World;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

}
