/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.util.DBconnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class InformationController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField showName;

    @FXML
    private TextField showPhone;

    @FXML
    private TextField showSince;

    @FXML
    private TextField showUsername;
    public void Information() throws SQLException{
        try {
            String searchQuery="select*from student where status=1 and id=?";
            Connection conn=DBconnect.connectDB();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                showName.setText(rs.getString("name"));
                showSince.setText(rs.getString("since"));
                if(rs.getString("phone")==null){
                    showPhone.setText("");
                }else{
                    showPhone.setText(rs.getString("phone"));
                }
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Information();
            showUsername.setText(PrimaryController.user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    
    
}
