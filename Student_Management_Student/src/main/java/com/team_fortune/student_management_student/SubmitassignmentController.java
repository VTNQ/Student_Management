/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.xml.transform.Result;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class SubmitassignmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField linkfield;
            @FXML
    private Label subject;
        private Connection conn;
                 private int assignmentId;
            @FXML
    void submit(ActionEvent event) {

        conn=PrimaryController.connectDB();
        String checkQuery="Select * From solution WHERE id_assignments = ?";
        String updateQuery="UPDATE solution SET link=? WHERE id_assignments = ?";
        String query="INSERT INTO solution(id_assignments,link,status) values (?,?,?)";
                try {
                    PreparedStatement ps=conn.prepareStatement(checkQuery);
                 ps.setInt(1, ExerciseviewController.assignmentId);
                    ResultSet resultSet=ps.executeQuery();
                  if(resultSet.next()){
                      PreparedStatement updatestmt=conn.prepareStatement(updateQuery);
                      updatestmt.setString(1,linkfield.getText());
                      updatestmt.setInt(2, ExerciseviewController.assignmentId);
                      
                      updatestmt.executeUpdate();
                      displaysuccessfully("Update Successfully");
                  }else{
                      PreparedStatement insertstmt=conn.prepareStatement(query);
                      insertstmt.setInt(1, SecondaryController.assignmentId);
                      insertstmt.setString(2, linkfield.getText());
                      insertstmt.setBoolean(3, true);
                      insertstmt.executeUpdate();
                      displaysuccessfully("insert successfully");
                  }
               
                } catch (Exception e) {
                    e.printStackTrace();
                }


    }
    private void displaysuccessfully(String message){
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
     @FXML
    public void init(String subject) {
       this.subject.setText(subject);
    }
}
