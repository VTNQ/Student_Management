package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Forgot_PasswordController{
   @FXML
    private MFXPasswordField RePassword=new MFXPasswordField();

    @FXML
    private MFXPasswordField newPassword=new MFXPasswordField();
     @FXML
     public void init(){
         
     }
         @FXML
    void ChangePassword(ActionEvent event) {
         
    }
    
}
