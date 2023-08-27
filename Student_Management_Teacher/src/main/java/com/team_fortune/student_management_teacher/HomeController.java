package com.team_fortune.student_management_teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class HomeController {

    @FXML
    private Label btn_add_class;
    @FXML
    private Label btn_add_subject;
    @FXML
    private Label btn_list_class;
    @FXML
    private Label btn_list_subject;
    @FXML
    private ScrollPane main_display;
    @FXML
    private TextField showUsername;
    @FXML
    private TextField showEmail;
    @FXML
    private TextField showPhone;
    @FXML
    private TextField showSince;
     
    @FXML
    void btn_home(MouseEvent event) {
        FXMLLoader loader=new FXMLLoader(App.class.getResource("view/home.fxml"));
        try{
            AnchorPane HomePage=loader.load();
            main_display.setContent(HomePage);
            
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    void btn_add_class(MouseEvent event){
        
    }
    void btn_list_class(MouseEvent event){
        
    }
    void btn_add_subject(MouseEvent event){
        
    }
    void btn_list_subject(MouseEvent event){
        
    }
}