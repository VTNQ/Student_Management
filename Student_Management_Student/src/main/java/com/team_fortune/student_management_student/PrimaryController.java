package com.team_fortune.student_management_student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;

public class PrimaryController implements Initializable{
     @FXML
    private Hyperlink create_acc;

    @FXML
    private Button login_btn;

    @FXML
    private Label m;
    public void textfield(MouseEvent event){
        if(event.getSource()==username){
            username.setStyle("-fx-background-color:#fff;"+"-fx-border-width:3px;");
            password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        }else if(event.getSource()==password){
            username.setStyle("-fx-background-color:#eef3ff"+"-fx-border-width:1px");
            password.setStyle("-fx-background-color:#eef3ff"+"-fx-border-width:3px");
        }
    }
    @FXML
    private Label marco;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setStyle("-fx-background-color:#fff;"+"-fx-border-width:3px;");
        DropShadow original=new DropShadow(20,Color.valueOf("#6a9ae7"));
        m.setEffect(original);
        marco.setEffect(original);
        m.setOnMouseEntered((MouseEvent event)->{
            DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
            m.setStyle("-fx-text-fill:#fff");
            m.setEffect(shadow);
            marco.setEffect(shadow);
        });
        m.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow=new DropShadow(20,Color.valueOf("#6a9ae7"));
        m.setStyle("-fx-text-fill:#6a9ae7");
        m.setEffect(shadow);
        marco.setEffect(shadow);
        });
              marco.setOnMouseEntered((MouseEvent event)->{
            DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
            m.setStyle("-fx-text-fill:#fff");
            m.setEffect(shadow);
            marco.setEffect(shadow);
        });
        marco.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
        m.setStyle("-fx-text-fill:#6a9ae7");
        m.setEffect(shadow);
        marco.setEffect(shadow);
        });
    }
    
   
}
