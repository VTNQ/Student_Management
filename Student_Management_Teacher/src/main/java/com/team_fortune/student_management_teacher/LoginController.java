package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dao.SearchTeacher;
import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane login_form;
    @FXML
    private PasswordField password_field;
    @FXML
    private TextField username_field;
    @FXML
    private Button btn_Login;
    
    @FXML
    void openPopupForgetgPassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("forgot_password.fxml"));
            AnchorPane newPopup = fxmlLoader.load();
            Forgot_PasswordController forgot_password = fxmlLoader.getController();
            forgot_password.init();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newPopup, 300, 200));
            popupStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_],{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String Password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[a-zA-Z\\\\d]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Password);
        return matcher.matches();
    }

    public void CheckLogin(MouseEvent event) throws IOException {
        String username = username_field.getText();
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
            if (isValidUsername(username) && isValidPassword(password)) {
                try {
                    boolean isFound = SearchTeacher.searchTeacherAll(username_field.getText(), password_field.getText());
                    if (isFound == true) {
                        DialogAlert.DialogSuccess("Login Successfully");
                        App.setRoot("main");
                    } else {
                        DialogAlert.DialogError("Account not exist!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                DialogAlert.DialogError("Username or Password Incorrect!");
            }
        }
    }
}
