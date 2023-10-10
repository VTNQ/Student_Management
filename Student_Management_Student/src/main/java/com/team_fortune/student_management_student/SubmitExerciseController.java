/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class SubmitExerciseController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Label name_class;
 @FXML
    private TextField txtAssignment;
    @FXML
    private Label name_subject;
    private Connection conn;
    @FXML
    public void init(String name_subject,String name_class){
        this.name_class.setText(name_class);
        this.name_subject.setText(name_subject);
    }
        @FXML
    void SubmitAssignment(ActionEvent event) {
        if(!txtAssignment.getText().isEmpty()){
            conn=PrimaryController.connectDB();
            try {
                
                String query="Insert into transcript(id_exam,link) values(?,?)";
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(2, txtAssignment.getText());
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            dialog.displayErrorMessage("Haven't entered the Assignment link yet");
        }
     
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
