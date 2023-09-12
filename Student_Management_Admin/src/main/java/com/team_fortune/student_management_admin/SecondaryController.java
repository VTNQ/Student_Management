package com.team_fortune.student_management_admin;

import com.team_fortune.student_management_admin.util.DBConnection;
import com.team_fortune.student_management_admin.util.MD5;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SecondaryController {

    @FXML
    private MFXDatePicker SinceTeacher;

    @FXML
    private MFXTextField nameTeacher;

    @FXML
    private MFXTextField phoneTeacher;

    @FXML
    private MFXTextField usernameTeacher;

    @FXML
    void addTeacher(MouseEvent event) {
        String username = MD5.Md5(usernameTeacher.getText());
        System.out.println(username);
        try {
            String insertQuery = "insert into teacher(name,username,phone,since,password,status) values(?,?,?,?,?,?)";
            DateTimeFormatter local=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String since=SinceTeacher.getValue().format(local);
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, nameTeacher.getText());
            ps.setString(2, username);
            ps.setString(3, phoneTeacher.getText());
            ps.setString(4, since);
            ps.setString(5, username);
            ps.setInt(6, 0);
            ps.executeUpdate();
            usernameTeacher.clear();
            nameTeacher.clear();
            phoneTeacher.clear();
            SinceTeacher.clear();
        } catch (SQLException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
