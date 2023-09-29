/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelcalender;
import com.team_fortune.student_management_student.models.modelsearch;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class CalenderviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
      Long startsecondsRemaining;
       private long secondsRemaining;
    @FXML
    private TableColumn<modelcalender, String> Endtime;
    

    @FXML
    private TableColumn<modelcalender, String> Time;
 private boolean buttonclicked=false;
    @FXML
    private TableColumn<modelcalender, Boolean> colCountdown;

    @FXML
    private TableColumn<modelcalender, String> colclass;

    @FXML
    private TableColumn<modelcalender, String> colsubject;

    @FXML
    private TableView<modelcalender> tblview;
    private void selectexam(){
          List<com.team_fortune.student_management_student.models.modelcalender> resultList = daodb.SelectCalender();
             ObservableList<modelcalender> observableList=FXCollections.observableArrayList(resultList);
             tblview.setItems(observableList);
             colsubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
             colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
             Time.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
               Time.setCellFactory(column -> {
    return new TableCell<modelcalender, String>() {
         Label label=new Label();
          HBox hbox=new HBox();
            
          private void startCountdownEverySecond(LocalDateTime startTime) {
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        LocalDateTime now = LocalDateTime.now();
        startsecondsRemaining = java.time.Duration.between(now, daodb.starTime).getSeconds();

        if (startsecondsRemaining < 900 && startsecondsRemaining>0) {
            long minutes = startsecondsRemaining / 60;
            long seconds = startsecondsRemaining % 60;
            String countdown = String.format("%02d:%02d", minutes, seconds);
            setText(countdown);
            
        
        } else if(startsecondsRemaining>900){
            String formatdaated=daodb.starTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            setText(formatdaated);// Countdown đã kết thúc
            
        }else if(startsecondsRemaining<=0){
            setText("00:00");
            setStyle("-fx-text-fill:red");
         
        }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
}
   
        Label la=new Label();
       
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                setText(item);
             
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("vi", "VN"));
                LocalDateTime startTime = LocalDateTime.parse(item, formatter);
                
               
                startCountdownEverySecond(startTime);
              
            }
           
        }
        
    };
});
             Endtime.setCellValueFactory(new PropertyValueFactory<>("Endtime"));
             colCountdown.setCellValueFactory(new PropertyValueFactory<>("buttonVisible"));
             colCountdown.setCellFactory(column->new TableCell<com.team_fortune.student_management_student.models.modelcalender,Boolean>(){
             private final MFXButton button=new MFXButton("Join");
                     {
                  button.setOnAction(event->{
                      com.team_fortune.student_management_student.models.modelcalender cls=getTableView().getItems().get(getIndex());
                  try {
                      FXMLLoader loader=new FXMLLoader(App.class.getResource("laboratory_view.fxml"));
                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("vi", "VN"));
                LocalDateTime Endtime = LocalDateTime.parse(cls.getEndtime(), formatter);
                      LocalDateTime starttime=LocalDateTime.parse(cls.getStartTime(),formatter);
                      AnchorPane popup=loader.load();
                      Laboratory_viewController exercise=loader.getController();
                      exercise.settext(cls.getLink());
                      exercise.ClockEnd(starttime,Endtime);
                      exercise.EndCountDownEverySecond(Endtime);
                     
                      Stage popupStage=new Stage();
                      popupStage.initModality(Modality.APPLICATION_MODAL);
                      popupStage.setScene(new Scene(popup,600,400));
                      popupStage.setResizable(false);
                      popupStage.showAndWait();
                  } catch (IOException ex) {
                      Logger.getLogger(CalenderviewController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  });
                     }
              @Override
              protected void updateItem(Boolean Item, boolean empty) {
                  super.updateItem(Item, empty);
                  button.getStyleClass().add("btn-design");
                  if(Item==null||empty){
                      setGraphic(null);
                  }else{
                      setGraphic(button);
                  }
              }
             
             });
    }         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectexam();
    }    
    
}
