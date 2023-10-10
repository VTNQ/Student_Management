package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.Regax;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import java.awt.Dialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class popUpclass implements Initializable {

    @FXML
    private Label total;
    private int itemsperPage = 5;
    private int totalItems;
    private int currentPageIndex = 0;
   @FXML
    private Pagination pagination;

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
    public static String totalStudent(String query){
        String total_student="Select count(a.id) as total From student a Join class_subject b ON a.id=b.id_student Join class c ON c.id=b.id_class Join teacher d ON d.id=b.id_teacher Where c.name=? And d.username=?";
        String total="-1";
        try {
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(total_student);
            stmt.setString(1, query);
            stmt.setString(2, MD5.Md5(HomeController.username));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                total=String.valueOf(rs.getInt("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(popUpclass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
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
            totalItems=popup.size();
            int pagecout=(totalItems/itemsperPage)+1;
            pagination.setPageCount(pagecout);
            if(currentPageIndex >=pagecout){
                currentPageIndex=pagecout-1;
            }
            int startIndex=currentPageIndex*itemsperPage;
            int endIndex=Math.min(startIndex+itemsperPage, totalItems);
            endIndex=Math.min(endIndex, totalItems);
            List<com.team_fortune.student_management_teacher.model.Class>sublish=popup.subList(startIndex, endIndex);
            tblSubkect.setItems(FXCollections.observableArrayList(sublish));
            ClassSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
            classStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public  void setttotal(String totalstudent){
        total.setText(totalstudent);
    }
    private void closepopup() {
        Stage stage = (Stage) change.getScene().getWindow();
        stage.close();
    }

    @FXML
    void ChangePassword(ActionEvent event) {
        if (!newPassword.getText().isEmpty() && !RePassword.getText().isEmpty()) {
            if (newPassword.getText().equals(RePassword.getText())) {
                if (Regax.isValidPassword(newPassword.getText())) {
                    try {
                        Connection conn = DBConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement("update teacher set password=?,status=1 where username=?");
                        ps.setString(1, MD5.Md5(newPassword.getText()));
                        ps.setString(2, MD5.Md5(HomeController.username));
                        ps.executeUpdate();
                        closepopup();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    newPassword.getStyleClass().add("text_field_error");
                    newPassword.applyCss();
                    RePassword.getStyleClass().add("text_field_error");
                    RePassword.applyCss();
                    DialogAlert.DialogError("Password must be more than 8 characters\n" + "Password must have at least 1 capital ");
                }
            } else {
                newPassword.getStyleClass().add("text_field_error");
                newPassword.applyCss();
                RePassword.getStyleClass().add("text_field_error");
                RePassword.applyCss();
                DialogAlert.DialogError("New Password and Re-enterd Password not same!");
            }
        } else if (newPassword.getText().isEmpty()) {
            newPassword.getStyleClass().add("text_field_error");
            newPassword.applyCss();
            DialogAlert.DialogError("New Password is Empty");
        } else if (RePassword.getText().isEmpty()) {
            RePassword.getStyleClass().add("text_field_error");
            RePassword.applyCss();
            DialogAlert.DialogError("Re-enterd Password is Empty");
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
pagination.currentPageIndexProperty().addListener((obs,oldIndex,NewIndex)->{
currentPageIndex=NewIndex.intValue();
Informationclass(MainClassController.class_name);
});
    }
}
