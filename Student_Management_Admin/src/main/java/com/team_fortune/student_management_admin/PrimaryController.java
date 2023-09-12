package com.team_fortune.student_management_admin;

import com.team_fortune.student_management_admin.dialog.DialogAlert;
import com.team_fortune.student_management_admin.util.DBConnection;
import com.team_fortune.student_management_admin.util.MD5;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private MFXButton btnLogin;

    @FXML
    private MFXPasswordField password_admin;

    @FXML
    private MFXTextField username_admin;

    @FXML
    void checkLogin(MouseEvent event)throws IOException{
        String username=new String(MD5.Md5(username_admin.getText()),StandardCharsets.UTF_8);
        String password=new String(MD5.Md5(password_admin.getText()),StandardCharsets.UTF_8);
        try {
            String searchQuery="select*from admin where username=? and password=?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(searchQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();
            if(!rs.equals(null)){
                DialogAlert.DialogSuccess("Login Success");
                App.setRoot("secondary");
            }else{
                DialogAlert.DialogError("Username or Password is incorrect");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}