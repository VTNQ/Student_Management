/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.team_fortune.student_management_student.dao.daodb;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class Registered_classController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label name_class = new Label();
    private int id_class;
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colsubject = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colteacher = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_student.models.classmodel> tbldetail = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> ColMyJoin = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Integer> colDetail = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colregisterDetail = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_student.models.classmodel> tblclass = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colclass = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_student.models.classmodel> tblRegister = new TableView<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colJoin = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colDe = new TableColumn<>();
    ObservableList<com.team_fortune.student_management_student.models.classmodel> model = FXCollections.observableArrayList();

    private void Joinedclass() {
        List<com.team_fortune.student_management_student.models.classmodel> arraylist = daodb.joined_class();
        ObservableList<com.team_fortune.student_management_student.models.classmodel> observable = FXCollections.observableArrayList(arraylist);
        tblRegister.setItems(observable);
        ColMyJoin.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDetail.setCellValueFactory(new PropertyValueFactory<>("Active"));
        colDetail.setCellFactory(column -> new TableCell<com.team_fortune.student_management_student.models.classmodel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    if (item == 0) {
                        setText("marking");
                    } else if (item == 1) {
                        setText("obtain");
                        setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                    } else if (item == 2) {
                        setText("fail");
                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    }
                }
            }

        });
    }

    private void displayrecord() {
        String query = "SELECT a.id, a.name,d.id as id_subject,f.id as id_teacher "
                + "FROM class a "
                + "LEFT JOIN class_subject b ON a.id = b.id_class "
                + "Left Join subject d ON d.id=b.id_subject "
                + "Left Join teacher f ON f.id=b.id_teacher "
                + "WHERE ( b.id_exam IS Null And b.id_assignments IS NULL And b.id_teacher IS NOT NULL AND (b.id_student != ? OR b.id_student IS NULL)) "
                + "GROUP BY a.id, a.name,d.id,f.id";
        try {
            model.clear();
            Connection conn = PrimaryController.connectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int id_subject = result.getInt("id_subject");
                int id_teacher = result.getInt("id_teacher");
                com.team_fortune.student_management_student.models.classmodel class_name = new com.team_fortune.student_management_student.models.classmodel(id, name, id_subject, id_teacher);
                model.add(class_name);
            }
            tblclass.setItems(model);
            colclass.setCellValueFactory(new PropertyValueFactory<>("name"));
            colJoin.setCellValueFactory(new PropertyValueFactory<>("detail"));
            colJoin.setCellFactory(column -> new TableCell<com.team_fortune.student_management_student.models.classmodel, Boolean>() {
                private final MFXButton button = new MFXButton("Join");

                {
                    button.setOnAction(event -> {
                        com.team_fortune.student_management_student.models.classmodel cl = getTableView().getItems().get(getIndex());
                        try {
                            String check_query = "Select count(*) as total From class_subject where id_class=? And id_teacher=? And id_subject=? And id_student IS null";
                            String update_query="Update class_subject set id_student=?,Active=? where id_class=? And id_teacher=? And id_subject=?";
                            Connection conn = PrimaryController.connectDB();
                            PreparedStatement checksmtmt = conn.prepareStatement(check_query);
                            checksmtmt.setInt(1, cl.getId());
                            checksmtmt.setInt(2, cl.getId_teacher());
                            checksmtmt.setInt(3, cl.getId_Subject());
                          
                            ResultSet rs = checksmtmt.executeQuery();
                            if (rs.next()) {
                                int total=rs.getInt("total");
                                System.out.println(total);
                                if(total>0){
                                   PreparedStatement updatestmt=conn.prepareStatement(update_query);
                                updatestmt.setInt(1, PrimaryController.loggedInStudentId);
                                updatestmt.setInt(2, 0);
                                updatestmt.setInt(3, cl.getId());
                                updatestmt.setInt(4, cl.getId_teacher());
                                updatestmt.setInt(5, cl.getId_Subject());
                                updatestmt.executeUpdate();
                                 PrimaryController.displaysuccessfully("Join successfully"); 
                                }else{
                                        String query = "Insert into class_subject(id_class,id_teacher,id_student,Active,id_subject) values(?,?,?,?,?)";

                                PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setInt(1, cl.getId());
                                stmt.setInt(2, cl.getId_teacher());

                                stmt.setInt(3, PrimaryController.loggedInStudentId);
                                stmt.setInt(4, 0);
                                stmt.setInt(5, cl.getId_Subject());
                                stmt.executeUpdate();
                                PrimaryController.displaysuccessfully("Join successfully");
                                }
                                
                              
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Boolean Item, boolean empty) {
                    super.updateItem(Item, empty);
                    button.getStyleClass().add("btn-design");
                    if (empty || Item == null) {
                        setGraphic(null);
                    } else {

                        setGraphic(button);

                    }
                }

            });
            colDe.setCellValueFactory(new PropertyValueFactory<>("isActive"));
            colDe.setCellFactory(column -> new TableCell<com.team_fortune.student_management_student.models.classmodel, Boolean>() {
                private final MFXButton button = new MFXButton("Detail");

                {
                    button.setOnAction(event -> {
                        com.team_fortune.student_management_student.models.classmodel cl = getTableView().getItems().get(getIndex());
                        try {
                            FXMLLoader fxloader = new FXMLLoader(App.class.getResource("Detailclass.fxml"));
                            AnchorPane newPopup;
                            newPopup = fxloader.load();
                            Registered_classController exercise = fxloader.getController();
                            exercise.detailclass(cl.getId());
                            exercise.detailnameclass(cl.getName());
                            Stage PopupStage = new Stage();
                            PopupStage.initModality(Modality.APPLICATION_MODAL);
                            PopupStage.setScene(new Scene(newPopup, 500, 400));
                            PopupStage.setResizable(false);
                            PopupStage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Registered_classController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });
                }

                @Override
                protected void updateItem(Boolean Item, boolean empty) {
                    super.updateItem(Item, empty);
                    button.getStyleClass().add("btn-design");
                    if (empty || Item == null) {
                        setGraphic(null);
                    } else {

                        setGraphic(button);

                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startUpdating() {
        ScheduledExecutorService Excuter = Executors.newSingleThreadScheduledExecutor();
        Excuter.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                displayrecord();
                Joinedclass();

            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void detailclass(int id_class) {
        List<com.team_fortune.student_management_student.models.classmodel> resultList = daodb.Detailclass(id_class);
        ObservableList<com.team_fortune.student_management_student.models.classmodel> observableList = FXCollections.observableArrayList(resultList);
        tbldetail.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
    }

    private void detailnameclass(String name_class) {
        this.name_class.setText(name_class);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        displayrecord();
        Joinedclass();
        startUpdating();
    }

}
