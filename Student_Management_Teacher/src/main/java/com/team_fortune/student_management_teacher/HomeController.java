package com.team_fortune.student_management_teacher;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class HomeController {
   
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_profile;
    @FXML
    private Pane main_display;
    private String username;
    public HomeController(){
        
    }
    public HomeController(String username){
        this.username=username;
    }
    @FXML
    void btn_home(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(App.class.getResource("view/home.fxml"));
        try{
            AnchorPane HomePage=loader.load();
//            main_display.setContent(HomePage);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void showInformationUser(MouseEvent event) {
        
    }
    @FXML
    void LogOut(MouseEvent event) {

    }
}