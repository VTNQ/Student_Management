package com.team_fortune.student_management_teacher.model;

public class Class_Subject {
    private int id_class;
    private int id_subject;
    private int id_teacher;
    private int id_student;
    private int id_assignments;
    private int id_exam;

    public Class_Subject() {
    }

    public Class_Subject(int id_class, int id_subject, int id_teacher, int id_student, int id_assignments, int id_exam) {
        this.id_class = id_class;
        this.id_subject = id_subject;
        this.id_teacher = id_teacher;
        this.id_student = id_student;
        this.id_assignments = id_assignments;
        this.id_exam = id_exam;
    }

    public int getId_class() {
        return id_class;
    }

    public int getId_subject() {
        return id_subject;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public int getId_student() {
        return id_student;
    }

    public int getId_assignments() {
        return id_assignments;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public void setId_assignments(int id_assignments) {
        this.id_assignments = id_assignments;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
    }
        
}
