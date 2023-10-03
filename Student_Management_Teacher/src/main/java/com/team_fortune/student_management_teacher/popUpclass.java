package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import java.awt.Dialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class popUpclass implements Initializable {

    @FXML
    private MFXButton change;
    @FXML
    private MFXPasswordField RePassword;

    @FXML
    private MFXPasswordField newPassword;
    @FXML
    public TableView<com.team_fortune.student_management_teacher.model.Class> tblSubkect = new TableView<>();
    public ObservableList<com.team_fortune.student_management_teacher.model.Class> popup = FXCollections.observableArrayList();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> ClassSubject = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> classStudent = new TableColumn<>();

    public void Informationclass(String class_name) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select a.name,b.name From class a " + "JOIN class_subject c ON a.id=c.id_class "
                    + "JOIN student b ON c.id_student=b.id " + "JOIN teacher d ON d.id=c.id_teacher " + "Where d.username=? And a.name=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, class_name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_class = rs.getString("a.name");
                String name_student = rs.getString("b.name");
                com.team_fortune.student_management_teacher.model.Class newclass = new com.team_fortune.student_management_teacher.model.Class();
                newclass.setName(name_class);

                newclass.setName_student(name_student);
                popup.add(newclass);
            }

            tblSubkect.setItems(popup);
            System.out.println(tblSubkect);
            ClassSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
            classStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));

        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void closepopup(){
        Stage stage=(Stage) change.getScene().getWindow();
        stage.close();
    }
    @FXML
    void ChangePassword(ActionEvent event) {
        if(!newPassword.getText().isEmpty()&& !RePassword.getText().isEmpty()){
              if (newPassword.getText().equals(RePassword.getText())) {
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement("update teacher set password=?,status=1 where username=?");
                ps.setString(1, MD5.Md5(newPassword.getText()));
                ps.setString(2, MD5.Md5(HomeController.username));
                System.out.println(getDatabaseToModel.id_teacher);
                ps.executeUpdate();
                closepopup();
            } catch (SQLException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           DialogAlert.DialogError("password is not valid");
        }
        }else{
            DialogAlert.DialogError("Filed is Empty");
        }
      

    }

    public static boolean infoclass(String class_name) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select a.name,b.name From class a " + "JOIN class_subject c ON a.id=c.id_class "
                    + "JOIN student b ON c.id_student=b.id " + "JOIN teacher d ON d.id=c.id_teacher " + "Where d.username=? And a.name=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, class_name);
            ResultSet rs = stmt.executeQuery();
            boolean hasData = rs.next();
            rs.close();
            stmt.close();
            conn.close();
            return hasData;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void up() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Informationclass(MainClassController.class_name);

    }
}
