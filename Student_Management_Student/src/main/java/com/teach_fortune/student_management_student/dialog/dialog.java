/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teach_fortune.student_management_student.dialog;

import javafx.scene.control.Alert;

/**
 *
 * @author tranp
 */
public class dialog {
     public static void displaysuccessfully(String message){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
     public static void displayErrorMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
