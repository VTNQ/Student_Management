package com.team_fortune.student_management_teacher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
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
    void openPopupForgetgPassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(App.class.getResource("forgot_password.fxml"));
            AnchorPane newPopup =fxmlLoader.load();
            Forgot_PasswordController forgot_password=fxmlLoader.getController();
            forgot_password.init();
            Stage popupStage=new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newPopup,300,200));
            popupStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void CheckLogin(ActionEvent event) throws IOException{
        
        App.setRoot("main");
    }
}
