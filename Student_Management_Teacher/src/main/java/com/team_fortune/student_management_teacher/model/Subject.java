package com.team_fortune.student_management_teacher.model;

public class Subject {
    private int id;
    private String name;
    private String session;
    private String lession_link;
    private Boolean Act;

    public Boolean getAct() {
        return Act;
    }

    public void setAct(Boolean Act) {
        this.Act = Act;
    }

    

    public Subject() {
    }

    public Subject(int id, String name, String session, String lession_link) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.lession_link = lession_link;
         this.Act=true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSession() {
        return session;
    }

    public String getLession_link() {
        return lession_link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setLession_link(String lession_link) {
        this.lession_link = lession_link;
    }
    
}
