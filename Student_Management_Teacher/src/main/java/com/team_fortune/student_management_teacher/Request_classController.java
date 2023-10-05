/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class Request_classController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Integer> colApprove = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Integer> colCancle = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, String> colClass = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, String> colStudent = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Student> tblRequest = new TableView<>();
   
    private void displayrecord() {
        List<com.team_fortune.student_management_teacher.model.Student> resultlist = getDatabaseToModel.Request_Class();
        ObservableList<com.team_fortune.student_management_teacher.model.Student> observable = FXCollections.observableArrayList(resultlist);
        tblRequest.setItems(observable);
        colStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colApprove.setCellValueFactory(new PropertyValueFactory<>("Active"));
        colApprove.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Student, Integer>() {
            private final MFXButton button = new MFXButton("Approve");

            {
                button.setOnAction(event -> {
                     com.team_fortune.student_management_teacher.model.Student cls = getTableView().getItems().get(getIndex());
                  
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you Approve");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            alert.close();
                        }
                        if (response == ButtonType.OK) {
                           String querycheck="Select Active From class_subject Where id_student=? And id_class=? And Active=0 ";
                            String query = "Update class_subject set Active=1 Where id_student=? And id_class=? ";
                            try {
                                Connection conn = DBConnection.getConnection();
                                PreparedStatement stmtcheck=conn.prepareStatement(querycheck);
                                stmtcheck.setInt(1, cls.getId());
                                stmtcheck.setInt(2, cls.getId_Class());
                                ResultSet rs=stmtcheck.executeQuery();
                                if(rs.next()){
                                      PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setInt(1, cls.getId());
                                stmt.setInt(2, cls.getId_Class());
                                stmt.executeUpdate();

                                displayrecord();
                                }else{
                                    DialogAlert.DialogError("Approved");
                                }
                              
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                });
            }

            @Override
            protected void updateItem(Integer Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-success");
                if (Item != null || !empty) {
                    setGraphic(button);
                    if (Item == 2) {
                        button.setDisable(true);
                    }
                } else {
                    setGraphic(null);
                }
            }

        });
        colCancle.setCellValueFactory(new PropertyValueFactory<>("Active"));
        colCancle.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Student, Integer>() {
            private final MFXButton button = new MFXButton("cancel");
                {
                button.setOnAction(event->{
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ConFirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you cancel");
                    alert.showAndWait().ifPresent(response->{
                    if(response==ButtonType.CANCEL){
                       alert.close();
                    }
                    if(response==ButtonType.OK){
                        com.team_fortune.student_management_teacher.model.Student cls = getTableView().getItems().get(getIndex());
                          String querycheck="Select Active From class_subject Where id_student=? And id_class=? And Active=0 ";
                            String query = "Update class_subject set Active=2 Where id_student=? And id_class=? ";
                            try{
                                Connection conn=DBConnection.getConnection();
                                PreparedStatement checkstmt=conn.prepareStatement(querycheck);
                                checkstmt.setInt(1, cls.getId());
                                checkstmt.setInt(2, cls.getId_Class());
                                ResultSet rs=checkstmt.executeQuery();
                                if(rs.next()){
                                    PreparedStatement stmt=conn.prepareStatement(query);
                                stmt.setInt(1, cls.getId());
                                stmt.setInt(2, cls.getId_Class());
                                stmt.executeUpdate();
                                displayrecord();
                                }else{
                                     DialogAlert.DialogError("Canceled");
                                }
                                
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                    }
                    });
                });
                }
            @Override
            protected void updateItem(Integer Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-error");
                if (Item != null || !empty) {
                    setGraphic(button);
                    if (Item == 1) {
                        button.setDisable(true);
                    }
                } else {
                    setGraphic(null);
                }
            }

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayrecord();
    }

}
