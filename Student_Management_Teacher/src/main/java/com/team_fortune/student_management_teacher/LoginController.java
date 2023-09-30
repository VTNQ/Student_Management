package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dao.SearchTeacher;
import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private MFXPasswordField password_field;
    @FXML
    private MFXTextField username_field;
    @FXML
    private MFXButton btn_Login;
    public static String username;
    @FXML

    public void CheckLogin(MouseEvent event) throws IOException {
        
        String username = username_field.getText();
        HomeController.username=username;
        String password = password_field.getText();
        if (username.isEmpty() && password.isEmpty()) {
            username_field.getStyleClass().add("text_field_error");
            username_field.applyCss();
            
            password_field.getStyleClass().add("text_field_error");
            password_field.applyCss();
            
            DialogAlert.DialogError("Username and Password is require!");
        } else if (username.isEmpty()) {
            username_field.getStyleClass().add("text_field_error");
            username_field.applyCss();
            
            DialogAlert.DialogError("Username is require!");
        } else if (password.isEmpty()) {
            password_field.getStyleClass().add("text_field_error");
            password_field.applyCss();
            
            DialogAlert.DialogError("Password is require");
        } else {
                try {
                    int isFound = SearchTeacher.searchTeacherAll(username_field.getText(), password_field.getText());
                    if (isFound == 0) {
                        DialogAlert.DialogSuccess("Account not exist!");
                    } else switch (isFound) {
                        case 1:
                            
                            DialogAlert.DialogSuccess("Login Successfully");
                            App.setRoot("main");
                            break;
                        case 2:
                            DialogAlert.DialogError("Username or Password Incorrect!");
                            break;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
