    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.team_fortune.student_management_student;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import java.sql.Date;


    /**
     *
     * @author tranp
     */
    public class model {

        public model(int id, String name_teacher, String name_subject, String name_class) {
            this.id = id;
            this.name_teacher = name_teacher;
            this.name_subject = name_subject;
            this.name_class = name_class;
        }





  
        private int id;
        private String Name;
        private String name_teacher;
        private String name_subject;
        private String name_class;
        private Float score;
     public model(int id, String name_subject, Float score) {
            this.id = id;
            this.name_subject = name_subject;
            this.score = score;
        }

        public String getName_teacher() {
            return name_teacher;
        }

        public void setName_teacher(String name_teacher) {
            this.name_teacher = name_teacher;
        }

        public String getName_subject() {
            return name_subject;
        }

        public void setName_subject(String name_subject) {
            this.name_subject = name_subject;
        }

        public String getName_class() {
            return name_class;
        }

        public void setName_class(String name_class) {
            this.name_class = name_class;
        }
        private String username;
        private int phone;
        private Date since;
        private String password;
        private Boolean status;


        public model(){

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public Date getSince() {
            return since;
        }

        public void setSince(Date since) {
            this.since = since;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

    }
