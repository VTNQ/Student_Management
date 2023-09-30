/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelScore;
import com.team_fortune.student_management_student.models.modelsearch;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class ScoreviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TableView<com.team_fortune.student_management_student.models.modelsubject> tblscore;
       @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelsubject, String> colsubject;
       @FXML
       private TableColumn<com.team_fortune.student_management_student.models.modelsubject,String>colScore;
       @FXML
       private TableColumn<com.team_fortune.student_management_student.models.modelsubject,String>colclass;
       @FXML
       private TableColumn<com.team_fortune.student_management_student.models.modelsubject,String>Status;
       private void displayredord(){
           List<com.team_fortune.student_management_student.models.modelsubject>resultList=daodb.Scorestudent();
            ObservableList<com.team_fortune.student_management_student.models.modelsubject> observableList=FXCollections.observableArrayList(resultList);
            tblscore.setItems(observableList);
            colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
            colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
            colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
            Status.setCellValueFactory(new PropertyValueFactory<>("status"));
            Status.setCellFactory(column->{
            return new TableCell<com.team_fortune.student_management_student.models.modelsubject,String>(){
                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty); 
                    if(!empty && "dat".equals(Item)){
                        getStyleClass().add("field-success");
                    }else{
                        getStyleClass().add("field-error");
                    }
                    setText(Item);
                }
              
            };
            });
       }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayredord();
    }    
    
}
