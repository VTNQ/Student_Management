package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainSubjectController implements Initializable {

    @FXML
    private MFXComboBox<String> name_class;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableSubject;

    @FXML
    private Tab addSubject;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> colSubject;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String>colSession;
    @FXML
    private TableColumn<?,?>colSessionLink;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String>colClass;
    @FXML
    private Tab deleteSubject;

    @FXML
    private MFXTextField key_search;

    @FXML
    private MFXTextField lession_link;

    @FXML
    private Tab listSubject;

    @FXML
    private TabPane mainSubject;

    @FXML
    private MFXTextField name_subject;

    @FXML
    private MFXTextField session;

    @FXML
    private Tab updateSubject;

    private ObservableList<com.team_fortune.student_management_teacher.model.Class> Class = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Subject> Subject = FXCollections.observableArrayList();

    private List<com.team_fortune.student_management_teacher.model.Class> getDataFromDataBaseClass() {
        try {
            String SearchQuery = "select*from class";
            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(SearchQuery);
            List<com.team_fortune.student_management_teacher.model.Class> classes = new ArrayList<>();
            while (rs.next()) {
                com.team_fortune.student_management_teacher.model.Class clss = new com.team_fortune.student_management_teacher.model.Class();
                clss.setId(rs.getInt("id"));
                clss.setName(rs.getString("name_class"));
                classes.add(clss);
            }
            return classes;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
    private List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDataBaseSubject() {
        try {
            String SearchQuery = "select*from subject";
            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(SearchQuery);
            List<com.team_fortune.student_management_teacher.model.Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                com.team_fortune.student_management_teacher.model.Subject sub = new com.team_fortune.student_management_teacher.model.Subject();
                sub.setId(rs.getInt("id"));
                sub.setName(rs.getString("name_subject"));
                sub.setSession(rs.getString("session"));
                sub.setLession_link(rs.getString("lession_link"));
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<String> getClassNames() {
        List<com.team_fortune.student_management_teacher.model.Class> classes = getDataFromDataBaseClass();
        List<String> classNames = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            classNames.add(cls.getName());
        }
        return classNames;
    }

    @FXML
    void AddSubject(ActionEvent event) {
        try {
            boolean isFound = false;
            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet resultSet = s.executeQuery("select*from subject");
            while (resultSet.next()) {
                if (resultSet.getString("name_subject").equals(name_subject.getText())) {
                    isFound = true;
                }
            }
            if (isFound == false) {
                if (!name_subject.getText().isEmpty() && !session.getText().isEmpty() && name_class.getSelectedItem() != null) {
                    int id_teacher = 0;
                    int id_class = 0;
                    int id_subject = 0;
                    String insertQuery1 = "insert into subject(name_subject,session,lession_link) values(?,?,?)";
                    String insertQuery2 = "insert into class_subject(id_class,id_subject,id_teacher) values(?,?,?)";
                    String searchQuery1 = "select id from teacher where username=?";
                    String searchQuery2 = "select id from class where name_class=?";
                    String searchQuery3 = "select id from subject where name_subject=?";
                    try {

                        PreparedStatement ps1 = conn.prepareStatement(insertQuery1);
                        ps1.setString(1, name_subject.getText());
                        ps1.setInt(2, Integer.parseInt(session.getText()));
                        ps1.setString(3, lession_link.getText());
                        ps1.executeUpdate();
                        PreparedStatement ps2 = conn.prepareStatement(searchQuery1);
                        ps2.setString(1, MD5.Md5(HomeController.username));
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            id_teacher = rs1.getInt("id");
                        }
                        PreparedStatement ps3 = conn.prepareStatement(searchQuery2);
                        ps3.setString(1, name_class.getSelectedItem());
                        ResultSet rs2 = ps3.executeQuery();
                        while (rs2.next()) {
                            id_class = rs2.getInt("id");
                        }
                        PreparedStatement ps4 = conn.prepareStatement(searchQuery3);
                        ps4.setString(1, name_subject.getText());
                        ResultSet rs3 = ps4.executeQuery();
                        while (rs3.next()) {
                            id_subject = rs3.getInt("id");
                        }
                        System.out.println(id_class);
                        System.out.println(id_subject);
                        System.out.println(id_teacher);
                        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery2);
                        preparedStatement.setInt(1, id_class);
                        preparedStatement.setInt(2, id_subject);
                        preparedStatement.setInt(3, id_teacher);
                        preparedStatement.executeUpdate();
                        DialogAlert.DialogSuccess("Add Subject Success");
                    } catch (SQLException ex) {
                        Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (name_subject.getText().isEmpty()) {
                    name_subject.getStyleClass().add("text_field_error");
                    name_subject.applyCss();
                }
                if (session.getText().isEmpty()) {
                    session.getStyleClass().add("text_field_error");
                    session.applyCss();
                }
                if (name_class.getSelectedItem() == null) {
                    name_class.getStyleClass().add("text_field_error");
                    name_class.applyCss();
                }
            }else{
                DialogAlert.DialogError("Subject Exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class.addAll(getDataFromDataBaseClass());
        Subject.addAll(getDataFromDataBaseSubject());
        name_class.getItems().addAll(getClassNames());
    }

}
