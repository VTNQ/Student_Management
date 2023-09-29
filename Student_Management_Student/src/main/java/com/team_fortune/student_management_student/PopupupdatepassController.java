/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;
import com.teach_fortune.student_management_student.util.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class PopupupdatepassController implements Initializable {
     
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField NewPassword;

    @FXML
    private TextField RenewPassword;

    @FXML
    void ChangePassword(ActionEvent event) {
        String query = "Update student set password=?,status=? where id=?";
        Connection conn = DBconnect.connectDB();
        try {
            if (!NewPassword.getText().isEmpty() && !RenewPassword.getText().isEmpty()) {
                if (NewPassword.getText().equals(RenewPassword.getText())) {
                    if (NewPassword.getText().length() >= 8) {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, PrimaryController.encryptPasswordMD5(RenewPassword.getText()));
                        stmt.setBoolean(2, true);
                        stmt.setInt(3, PrimaryController.loggedInStudentId);
                        stmt.executeUpdate();
                        dialog.displaysuccessfully("Change Password successfully");
                    Stage stage=(Stage) NewPassword.getScene().getWindow();
                    stage.close();
                    }else{
                         dialog.displayErrorMessage("Password must be 8 characters");
                    }

                } else {
                    dialog.displayErrorMessage("New password is not match");
                }

            } else {
                dialog.displayErrorMessage("new Password or Re-enterd Password is empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
