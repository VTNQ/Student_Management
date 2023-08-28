package com.team_fortune.student_management_teacher.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class DialogAlert {
    public static void DialogSuccess(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS");
        alert.setContentText(message);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
   }
    public static void  DialogError(String message){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(message);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }
}
