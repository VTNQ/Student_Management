/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class Request_classController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Boolean> colApprove;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Boolean> colCancle;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, String> colClass;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, String> colStudent;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Student> tblRequest;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
