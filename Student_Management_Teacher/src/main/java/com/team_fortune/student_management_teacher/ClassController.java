package com.team_fortune.student_management_teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ClassController {

    @FXML
    private TabPane mainClass;
     
    @FXML
    private Tab addClass;

    @FXML
    private Tab deleteClass;

    @FXML
    private Tab listClass;

    @FXML
    private Tab updateClass;
    
    public void addClass(){
        mainClass.getSelectionModel().select(addClass);
    }
    public void updateClass(){
        mainClass.getSelectionModel().select(updateClass);
    }
    public void deleteClass(){
        mainClass.getSelectionModel().select(deleteClass);
    }
    public void listClass(){
        mainClass.getSelectionModel().select(listClass);
    }  
    
}
