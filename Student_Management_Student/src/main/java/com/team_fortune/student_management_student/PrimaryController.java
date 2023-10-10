package com.team_fortune.student_management_student;

import com.team_fortune.student_management_student.models.*;
import com.team_fortune.student_management_student.dao.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class PrimaryController implements Initializable {

    public static Connection conn;
    public static int loggedInStudentId;
    @FXML
    private TextField username;
    public static String user;
public static String loggedInUsername;
private PreparedStatement statement;
private static ResultSet result;
public static Connection connectDB(){
    try {
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Anhthang61@");
        return conn;
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return null;
}
private static boolean isaccoutExists(String productName){
    boolean exists=false;
    conn=connectDB();
    try {
        PreparedStatement stmt=conn.prepareStatement("Select * From student Where username=? ");
        stmt.setString(1, productName);
       
        result=stmt.executeQuery();
        exists=result.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}
private static boolean isaccoutusername(String Name){
    boolean exists=false;
    conn=connectDB();
    try {
        PreparedStatement stmt=conn.prepareStatement("Select * From student Where name=? ");
        stmt.setString(1, Name);
       
        result=stmt.executeQuery();
        exists=result.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}
private static boolean isaccoutphone(String phone){
    boolean exists=false;
    conn=connectDB();
    try {
        PreparedStatement stmt=conn.prepareStatement("Select * From student Where phone=? ");
        stmt.setString(1, phone);
       
        result=stmt.executeQuery();
        exists=result.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exists;
}
    public void login(ActionEvent event) {
    conn = connectDB();
   
    try {
        String sql = "SELECT * FROM student WHERE username=? And password=?";
        statement = conn.prepareStatement(sql);
        user=username.getText();
 String usernamtxt=encryptPasswordMD5(username.getText());
      String passtxt=encryptPasswordMD5(password.getText());

        statement.setString(1, usernamtxt);
        statement.setString(2,passtxt);

        result = statement.executeQuery();
        if (result.next()) {
           loggedInStudentId = result.getInt("id");
             loggedInUsername = result.getString("username");
          
            displaysuccessfully("Login successfully");
             App.setRoot("admin");
              
          
            
          
        } else {
            displayErrorMessage("Wrong Username or Password");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
private void displayErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

public static void displaysuccessfully(String message){
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    @FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private Button login_btn;
@FXML
    private PasswordField new_pass;

    @FXML
    private TextField pass_username;
       @FXML
    private PasswordField renew_pass;
    @FXML
    private Label m;

    @FXML
    private Label m1;

    @FXML
    private Label marco;
    @FXML
    private                   Button back;
    @FXML
    private Label marco1;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup_btn;
  @FXML
    private AnchorPane reset;
    @FXML
    private DatePicker su_date;

    @FXML
    private TextField su_name;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_phone;

    @FXML
    private TextField su_username;

    
        @FXML
    private AnchorPane signup_form;
            @FXML
    private AnchorPane login_form;
             @FXML
    private Hyperlink forgot_form;
               @FXML
   
             private boolean isEmpty(TextField textField) {
        return textField.getText().trim().isEmpty();
    }
                @FXML
    void updatepassword(ActionEvent event) throws NoSuchAlgorithmException {
        String username=encryptPasswordMD5(pass_username.getText());
        String hashedPassword;
  
        
          hashedPassword=encryptPasswordMD5(new_pass.getText());
        String renew_pass=this.renew_pass.getText();
        if(!username.isEmpty() && !hashedPassword.isEmpty() && !renew_pass.isEmpty()){
            if(isaccoutExists(username)){
                if(hashedPassword.equals(hashedPassword)){
                    Updatepasswordindb(username, hashedPassword);
                    displaysuccessfully("update successfully");
                    pass_username.setText("");
                    new_pass.setText("");
                    this.renew_pass.setText("");
                }else{
                    displayErrorMessage("New passwords do not match");
                }
            }else{
                displayErrorMessage("Username does not exists");
            }
        }else{
            displayErrorMessage("Field is empty");
        }
    }
private void Updatepasswordindb(String username,String newpassword){
    conn=connectDB();
    try {
      
        String query="Update student set password=? where username=?";
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1, newpassword);
        stmt.setString(2, username);
        stmt.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
            public void signup(ActionEvent event){
                LocalDate selectedDate=su_date.getValue();
                String phonetext=su_phone.getText();
                boolean isexist=isaccoutExists(su_username.getText());
               boolean name=isaccoutusername(su_name.getText());
               boolean phone=isaccoutphone(su_phone.getText());
                   
                    if(!su_name.getText().isEmpty() && !su_password.getText().isEmpty() && !su_username.getText().isEmpty() && !phonetext.isEmpty()){
                      if(selectedDate!=null){
                          if(!isexist && !name && !phone){
                              if(su_password.getText().length()>8){
                                  
                                  if(su_phone.getText().length()==10){
                                       int suphone=Integer.parseInt(su_phone.getText());
                                      java.sql.Date mysqlDate=Date.valueOf(selectedDate);
                                      
                                      modelprimary newitem=new modelprimary();
                                      newitem.setName(su_name.getText());
                                     
                                      newitem.setPhone(suphone);
                                      newitem.setSince(mysqlDate);
                                      String hashedPassword;
                                      try {
                                          hashedPassword=encryptPasswordMD5(su_password.getText());
                                          String hashedusername=encryptPasswordMD5(su_username.getText());
                                          newitem.setPassword(hashedPassword);
                                          newitem.setUsername(hashedusername);
                                      } catch (Exception e) {
                                          e.printStackTrace();
                                      }
                                     
                                      newitem.setStatus(Boolean.TRUE);
                                      daoprimary jp=new daoprimary();
                                      boolean status=jp.register(newitem);
                                      displaysuccessfully("registered successfully");
                                      if(status){
                                          
                                      }
                                  }else {
                                      displayErrorMessage("Invalid phone number.");
                                  }
                                  
                              }else{
                                  displayErrorMessage("Password must be 8 characters");
                              }
                              
                          }else{
                              displayErrorMessage("Account is already exists");
                          }
                          
                      }
                  }else{
                        su_name.getStyleClass().add("text_field_error");
                        su_name.applyCss();
                        
                      displayErrorMessage("filed is empty");
                  }
              
             
            }
public static String encryptPasswordMD5(String input){

    try {
        String base64Encode=Base64.getEncoder().encodeToString(input.getBytes());
        MessageDigest md=MessageDigest.getInstance("MD5");
        byte[] md5byte=md.digest(base64Encode.getBytes());
        StringBuilder sb=new StringBuilder();
        for(byte b:md5byte){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
} 
    public void textfield(MouseEvent event){
        if(event.getSource()==username){
            username.setStyle("-fx-background-color:#fff;"+"-fx-border-width:3px;");
            password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        }else if(event.getSource()==password){
            username.setStyle("-fx-background-color:#eef3ff"+"-fx-border-width:1px");
            password.setStyle("-fx-background-color:#eef3ff"+"-fx-border-width:3px");
        }
    }
  
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
public void changeForm(ActionEvent event){
    if(event.getSource()==login_acc){
        signup_form.setVisible(false);
        login_form.setVisible(true);
        su_name.setText("");
        su_phone.setText("");
        su_username.setText("");
        su_password.setText("");
        su_date.setValue(null);
        reset.setVisible(false);
    }else if(event.getSource()==create_acc){
        signup_form.setVisible(true);
        username.setText("");
        password.setText("");
        login_form.setVisible(false);
        reset.setVisible(false);
    }else if(event.getSource()==forgot_form){
          username.setText("");
        password.setText("");
        signup_form.setVisible(false);
        login_form.setVisible(false);
        reset.setVisible(true);
    }else if(event.getSource()==back){
        pass_username.setText("");
        new_pass.setText("");
        renew_pass.setText("");
        signup_form.setVisible(false);
         reset.setVisible(false);
            login_form.setVisible(true);
    }
}

public void su_textfield(MouseEvent event){
    if(event.getSource()==su_username){
        su_username.setStyle("-fx-background-color:#fff"+"-fx-border-width:3px");
        su_password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_name.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_phone.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        
    }else if(event.getSource()==su_password){
         su_username.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_password.setStyle("-fx-background-color:#fff"+"-fx-border-width:3px");
        su_name.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_phone.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
    }else if(event.getSource()==su_name){
         su_username.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_name.setStyle("-fx-background-color:#fff"+"-fx-border-width:3px");
        su_phone.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
    }else if(event.getSource()==su_phone){
         su_username.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_name.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        su_phone.setStyle("-fx-background-color:#fff"+"-fx-border-width:3px");
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             username.setStyle("-fx-background-color:#fff;"+"-fx-border-width:3px;");
        DropShadow original=new DropShadow(20,Color.valueOf("#6a9ae7"));
        m.setEffect(original);
        marco.setEffect(original);   m1.setEffect(original);
        marco1.setEffect(original);
        
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
        m.setEffect(original);
        marco.setEffect(original);
        m1.setOnMouseEntered((MouseEvent event)->{
            DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
            m1.setStyle("-fx-text-fill:#fff");
            m1.setEffect(shadow);
            marco1.setEffect(shadow);
        });
        m1.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow=new DropShadow(20,Color.valueOf("#6a9ae7"));
        m1.setStyle("-fx-text-fill:#6a9ae7");
        m1.setEffect(shadow);
        marco1.setEffect(shadow);
        });
              marco1.setOnMouseEntered((MouseEvent event)->{
            DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
            m1.setStyle("-fx-text-fill:#fff");
            m1.setEffect(shadow);
                marco1.setEffect(shadow);
        });
        marco1.setOnMouseExited((MouseEvent event)->{
        DropShadow shadow=new DropShadow(50,Color.valueOf("#6a9ae7"));
        m1.setStyle("-fx-text-fill:#6a9ae7");
        m1.setEffect(shadow);
        marco1.setEffect(shadow);
        });

        } catch (Exception e) {
        }
       
    }
    
   
}
