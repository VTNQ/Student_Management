package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.util.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ShowInformationUserController implements Initializable {

    @FXML
    private TextField showUsername;
    @FXML
    private TextField showPhone;

    @FXML
    private TextField showSince;

    @FXML
    private TextField showName;

    public void Information() throws SQLException{
        try {
            String searchQuery="select*from teacher where status=1 and username=?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setString(1, HomeController.username);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                showName.setText(rs.getString("name"));
                showSince.setText(rs.getString("since"));
                if(rs.getString("phone").isEmpty()||"0".equals(rs.getString("phone"))){
                    showPhone.setText("");
                }else{
                    showPhone.setText(rs.getString("phone"));
                }
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShowInformationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Information();
            showUsername.setText(HomeController.username);
        } catch (SQLException ex) {
            Logger.getLogger(ShowInformationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
