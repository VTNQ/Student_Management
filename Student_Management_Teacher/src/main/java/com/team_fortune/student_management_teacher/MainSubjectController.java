package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.model.Student;
import com.team_fortune.student_management_teacher.model.Subject;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSubjectController implements Initializable {

    @FXML
    private MFXComboBox<String> name_class=new MFXComboBox<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableSubject=new TableView<>();

    @FXML
    private Tab addSubject;

    @FXML
    private TableColumn<?, ?> colLessionSubject=new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colNameSubject=new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colSessionSubject=new TableColumn<>();

    @FXML
    private Tab deleteSubject;

    @FXML
    private MFXTextField key_search =new MFXTextField();

    @FXML
    private MFXTextField lession_link =new MFXTextField();

    @FXML
    private Tab listSubject;

    @FXML
    private TabPane mainSubject;

    @FXML
    private MFXTextField name_subject =new MFXTextField();

    @FXML
    private MFXTextField session =new MFXTextField();

    @FXML
    private Tab updateSubject;

    @FXML
    private MFXTextField lessionSubject =new MFXTextField();

    @FXML
    private MFXTextField nameSubject =new MFXTextField();

    @FXML
    private MFXTextField sessionSubject =new MFXTextField();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, Boolean> colClass =new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colLession_Subject=new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colName_Subject=new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colSession_Subject=new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, Boolean> colStudent=new TableColumn<>();
    
    @FXML
    private TableColumn<?, ?> col_class=new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> list_class=new TableView<>();
    
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableListSubject=new TableView<>();
    
    @FXML
    private TableColumn<?, ?> col_student=new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Student> list_student=new TableView<>();

    private ObservableList<com.team_fortune.student_management_teacher.model.Class> Class = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Subject> Subject = FXCollections.observableArrayList();
 private ObservableList<com.team_fortune.student_management_teacher.model.Subject> model = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Class_Subject> Class_Subject = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Student> Student = FXCollections.observableArrayList();

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
                        PreparedStatement ps = conn.prepareStatement("select id_subject from class_subject where id_class=? and id_teacher=? and id_subject=?");
                        ps.setInt(1, getDatabaseToModel.id_class);
                        ps.setInt(2, getDatabaseToModel.id_teacher);
                        ps.setNull(3, Types.INTEGER);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            if(rs.getString("id_subject")!=null){
                                isFound = true;
                            }
                        }
                        if (isFound == false) {
                            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                            preparedStatement.setInt(1, getDatabaseToModel.id_subject);
                            preparedStatement.setInt(2, getDatabaseToModel.id_class);
                            preparedStatement.executeUpdate();
                        } else {
                            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery2);
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

    void showListSubject() {
        TableListSubject.setItems(Subject);
        colName_Subject.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSession_Subject.setCellValueFactory(new PropertyValueFactory<>("session"));
        colLession_Subject.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colClass.setCellFactory(column-> new TableCell<Subject,Boolean>(){
            private MFXButton button=new MFXButton("View");
            {
                button.setOnAction(event->{
                    Subject selectSubject =getTableView().getItems().get(getIndex());
                    try{
                        FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/ListClass.fxml"));
                        AnchorPane listClass=loader.load();
                        Class.addAll(new getDatabaseToModel().getDataFromDatabaseClassWithSubject(selectSubject.getName()));
                        list_class.setItems(Class);
                        col_class.setCellValueFactory(new PropertyValueFactory<>("name"));
                        Stage list= new Stage();
                        list.initModality(Modality.APPLICATION_MODAL);
                        list.setScene(new Scene(listClass));
                        list.showAndWait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                });
            }
            @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
        });
        colStudent.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colStudent.setCellFactory(column->new TableCell<Subject,Boolean>(){
            private final MFXButton button=new MFXButton("View");
            {
                button.setOnAction(event->{
                    Subject selectSubject =getTableView().getItems().get(getIndex());
                    try{
                        FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/ListStudent.fxml"));
                        AnchorPane listStudent=loader.load();
                        Student.addAll(new getDatabaseToModel().getDataFromDatabaseStudentWithSubject(selectSubject.getName()));
                        list_student.setItems(Student);
                        col_student.setCellValueFactory(new PropertyValueFactory<>("name"));
                        Stage list= new Stage();
                        list.initModality(Modality.APPLICATION_MODAL);
                        list.setScene(new Scene(listStudent));
                        list.showAndWait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                });
            }
            @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || isEmpty()) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
        });
    }

    public void showUpdate(com.team_fortune.student_management_teacher.model.Subject selectSubject) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateSubject.fxml"));
            AnchorPane showUpdate = loader.load();
            nameSubject.setText(selectSubject.getName());
            sessionSubject.setText(selectSubject.getSession());
            lessionSubject.setText(selectSubject.getLession_link());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(showUpdate, 400, 300));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Class.addAll(new getDatabaseToModel().getDataFromDatabaseClass());
        Class_Subject.addAll(new getDatabaseToModel().getDataFromDatabaseClassSubject());
        name_class.getItems().addAll(getClassNames());
       
        showSuject();
        showListSubject();
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
        TableSubject.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    com.team_fortune.student_management_teacher.model.Subject sub = TableSubject.getSelectionModel().getSelectedItem();
                    if (!sub.equals(null)) {
                        showUpdate(sub);
                    }
                }
            }
        });
    }
}
