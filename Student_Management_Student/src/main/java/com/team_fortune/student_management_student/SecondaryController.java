package com.team_fortune.student_management_student;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController {
      private Connection connection;
    @FXML
    private Label sa;
   @FXML
    private Button signup;
    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }

    public void showLoggedInUsername() {
        sa.setText(loggedInUsername);
    }
   
       @FXML
    void signout(ActionEvent event) {
try {
    
        if(PrimaryController.conn!=null){
            PrimaryController.conn.close();
            signup.getScene().getWindow().hide();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/team_fortune/student_management_student/primary.fxml"));
   AnchorPane root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Login");
    stage.show();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    // ... Các phương thức khác
}