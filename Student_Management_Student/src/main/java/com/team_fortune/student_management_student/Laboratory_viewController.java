/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;
import com.team_fortune.student_management_student.dao.daodb;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class Laboratory_viewController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Label Endcoutdown=new Label() ;
    @FXML
    private Hyperlink Examp_topic;
        @FXML
    private Label clock;
public void settext(String hypelink){
    Examp_topic.setText(hypelink);
}

public  void EndCountDownEverySecond(LocalDateTime endTime){
      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        LocalDateTime now = LocalDateTime.now();
        long secondsRemaining = java.time.Duration.between(now, endTime).getSeconds();
if(secondsRemaining>0){
    
        
            long minutes = secondsRemaining / 60;
            long seconds = secondsRemaining % 60;
            String countdown = String.format("%02d:%02d", minutes, seconds);
            Endcoutdown.setText(countdown); 
       
        }else {
            Endcoutdown.setText("00:00");
            
           Endcoutdown.setStyle("-fx-text-fill:red");
          
        }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
            }
public void ClockEnd(LocalDateTime start,LocalDateTime Endtime){
  LocalDateTime now = LocalDateTime.now();
    long secondsRemaining=java.time.Duration.between(start, Endtime).getSeconds();
    if(secondsRemaining>0){
        long minutes=secondsRemaining/60;
        long seconds=secondsRemaining%60;
        String format=String.format("%02d", minutes);
        clock.setText(format);
    }else{
        clock.setText("");
        
    }
    
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Examp_topic.setOnAction(event->{
            try {
                Desktop.getDesktop().browse(new URI(Examp_topic.getText()));
            } catch (Exception e) {
                    dialog.displayErrorMessage("Error URL is not found");
            }
        });
    }    
    
}
