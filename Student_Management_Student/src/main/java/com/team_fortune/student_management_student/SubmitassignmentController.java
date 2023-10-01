/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        conn = PrimaryController.connectDB();
        String checkQuery = "Select * From assignments a JOIN solution b ON a.id=b.id_assignments JOIN class_subject c ON a.id=c.id_assignments JOIN student d ON d.id=c.id_student Where b.id_assignments=? And d.id=?";
            String query="INSERT INTO solution(id_assignments,link,status) values (?,?,?)";
            String updateQuery="UPDATE solution SET link=? WHERE id_assignments = ?";
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(checkQuery);
            stmt.setInt(1, ExerciseviewController.assignmentId);
           
            stmt.setInt(2, PrimaryController.loggedInStudentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               PreparedStatement updatestmt=conn.prepareStatement(updateQuery);
               updatestmt.setString(1, linkfield.getText());
               updatestmt.setInt(2, ExerciseviewController.assignmentId);
               updatestmt.executeUpdate();
            }else{
                PreparedStatement smt=conn.prepareStatement(query);
                smt.setInt(1, ExerciseviewController.assignmentId);
                smt.setString(2, linkfield.getText());
                smt.setInt(3, 0);
                smt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubmitassignmentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void displaysuccessfully(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
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
