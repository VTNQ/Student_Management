                /*
                 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
                 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
                 */
                package com.team_fortune.student_management_student.models;

                import java.sql.Timestamp;
                import java.time.LocalDateTime;
                import javafx.scene.control.Button;
                import javafx.scene.control.Label;

                /**
                 *
                 * @author tranp
                 */
                public class modelcalender {
              
                private String name_Subject;
                
                private String name_class;
                private String StartTime;
              private String Endtime;
              


                    private boolean buttonVisible;
                    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

                    public modelcalender( String name_Subject, String name_class,String StartTime,String link,String endtime ) {
                        this.name_Subject = name_Subject;
                       
                        this.name_class = name_class;
                        this.StartTime=StartTime;
                       
                        this.link=link;
                        this.buttonVisible=true;
                        this.Endtime=endtime;

                    }

        public boolean isButtonVisible() {
            return buttonVisible;
        }

        public void setButtonVisible(boolean buttonVisible) {
            this.buttonVisible = buttonVisible;
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

    public String getEndtime() {
        return Endtime;
    }

    public void setEndtime(String Endtime) {
        this.Endtime = Endtime;
    }

                    public void setName_class(String name_class) {
                        this.name_class = name_class;
                    }

                    public String getStartTime() {
                        return StartTime;
                    }

                    public void setStartTime(String StartTime) {
                        this.StartTime = StartTime;
                    }

                   


                }
