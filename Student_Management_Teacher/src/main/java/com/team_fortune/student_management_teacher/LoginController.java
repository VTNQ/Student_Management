package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dao.SearchTeacher;
import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane login_form;
    @FXML
    private MFXPasswordField password_field;
    @FXML
    private MFXTextField username_field;
    @FXML
    private MFXButton btn_Login;
    
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
                try {
                    int isFound = SearchTeacher.searchTeacherAll(username_field.getText(), password_field.getText());
                    if (isFound == 0) {
                        DialogAlert.DialogSuccess("Account not exist!");
                    } else switch (isFound) {
                        case 1:
                            DialogAlert.DialogSuccess("Login Successfully");
                            HomeController homePage=new HomeController(username);
                            App.setRoot("main");
                            break;
                        case 2:
                            DialogAlert.DialogError("Username or Password Incorrect!");
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
}
