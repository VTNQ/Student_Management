package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class MainSubjectController implements Initializable {

    @FXML
    private MFXComboBox<String> name_class;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableSubject;

    @FXML
    private Tab addSubject;

    @FXML
    private TableColumn<?, ?> colLessionSubject;

    @FXML
    private TableColumn<?, ?> colNameSubject;

    @FXML
    private TableColumn<?, ?> colSessionSubject;

    @FXML
    private TableColumn<?, ?> colView;

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

    private ObservableList<com.team_fortune.student_management_teacher.model.Class_Subject> Class_Subject = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Student> students = FXCollections.observableArrayList();

    private List<String> getClassNames() {
        List<com.team_fortune.student_management_teacher.model.Class> classes = new getDatabaseToModel().getDataFromDatabaseClass();
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
                if (resultSet.getString("name").equals(name_subject.getText())) {
                    isFound = true;
                }
            }
            if (isFound == false) {
                if (!name_subject.getText().isEmpty() && !session.getText().isEmpty() && name_class.getSelectedItem() != null) {

                    String insertQuery1 = "insert into subject(name,session,lession_link) values(?,?,?)";
                    String insertQuery2 = "insert into class_subject (id_class,id_subject,id_teacher) values(?,?,?)";
                    String updateQuery = "update class_subject set id_subject=? where id_class=?";
                    String searchQuery1 = "select id from class where name=?";
                    String searchQuery2 = "select id from subject where name=?";
                    try {
                        PreparedStatement ps1 = conn.prepareStatement(insertQuery1);
                        ps1.setString(1, name_subject.getText());
                        ps1.setInt(2, Integer.parseInt(session.getText()));
                        ps1.setString(3, lession_link.getText());
                        ps1.executeUpdate();
                        PreparedStatement ps2 = conn.prepareStatement(searchQuery1);
                        ps2.setString(1, name_class.getSelectedItem());
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            getDatabaseToModel.id_class = rs1.getInt("id");
                        }
                        PreparedStatement ps3 = conn.prepareStatement(searchQuery2);
                        ps3.setString(1, name_subject.getText());
                        ResultSet rs2 = ps3.executeQuery();
                        while (rs2.next()) {
                            getDatabaseToModel.id_subject = rs2.getInt("id");
                        }
                        PreparedStatement ps = conn.prepareStatement("select id_subject from class_subject where id_class=? and id_teacher=?");
                        ps.setInt(1, getDatabaseToModel.id_class);
                        ps.setInt(2, getDatabaseToModel.id_teacher);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            isFound = true;
                        }
                        if (isFound == false) {
                            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                            preparedStatement.setInt(1, getDatabaseToModel.id_subject);
                            preparedStatement.setInt(2, getDatabaseToModel.id_class);
                            preparedStatement.executeUpdate();
                        }else{
                            PreparedStatement preparedStatement=conn.prepareStatement(insertQuery2);
                            preparedStatement.setInt(1, getDatabaseToModel.id_class);
                            preparedStatement.setInt(2, getDatabaseToModel.id_subject);
                            preparedStatement.setInt(3, getDatabaseToModel.id_teacher);
                            preparedStatement.executeUpdate();
                        }
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
            } else {
                DialogAlert.DialogError("Subject Exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void showSuject() {
        TableSubject.setItems(Subject);
        colNameSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSessionSubject.setCellValueFactory(new PropertyValueFactory<>("session"));
        colLessionSubject.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class.addAll(new getDatabaseToModel().getDataFromDatabaseClass());
        Class_Subject.addAll(new getDatabaseToModel().getDataFromDatabaseClassSubject());
        name_class.getItems().addAll(getClassNames());
        Subject.addAll(new getDatabaseToModel().getDataFromDatabaseSubject());
        showSuject();
        key_search.textProperty().addListener((observable, oldvalue, newValue) -> {
            if (key_search.getText().isEmpty() || key_search.getText().isBlank()) {
                Subject.clear();
                Subject.addAll(new getDatabaseToModel().getDataFromDatabaseSubject());
                TableSubject.refresh();
            } else {

                Subject.clear();
                Subject.addAll(new getDatabaseToModel().getDataFromDatabaseSubjectWithKey(newValue));
                TableSubject.refresh();
            }
        });
    }
}
