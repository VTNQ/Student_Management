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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
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
    private Pagination pagination = new Pagination();
    private int itemsperPage = 5;
    private int totalItems;
    private int currentPageIndex = 0;
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
        totalItems = observable.size();
        int Pagecout = (totalItems / itemsperPage) + 1;
        pagination.setPageCount(Pagecout);

        if (currentPageIndex >= Pagecout) {
            currentPageIndex = Pagecout - 1;
        }

        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Student> St = observable.subList(startIndex, endIndex);
        tblRequest.setItems(FXCollections.observableArrayList(St));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colApprove.setCellValueFactory(new PropertyValueFactory<>("Active"));
        colApprove.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Student, Integer>() {
            private final MFXButton button = new MFXButton("Approve");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Student cls = getTableView().getItems().get(getIndex());
                    boolean active=Activeboolean(cls.getId(),cls.getId_Class());
                    if(active){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you Approve");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.CANCEL) {
                            alert.close();
                        }
                        if (response == ButtonType.OK) {
                            String query = "Update class_subject set Active=1 Where id_student=? And id_class=? ";
                            try {
                                Connection conn = DBConnection.getConnection();
                                PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setInt(1, cls.getId());
                                stmt.setInt(2, cls.getId_Class());
                                stmt.executeUpdate();

                                displayrecord();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    }else{
                        DialogAlert.DialogError("Approved");
                    }
                    

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
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Student cls = getTableView().getItems().get(getIndex());
                    boolean active = Activeboolean(cls.getId(), cls.getId_Class());
                    if (active) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("ConFirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you cancel");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.CANCEL) {
                                alert.close();
                            }
                            if (response == ButtonType.OK) {

                                String query = "Update class_subject set Active=2 Where id_student=? And id_class=? ";
                                try {
                                    Connection conn = DBConnection.getConnection();
                                    PreparedStatement stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, cls.getId());
                                    stmt.setInt(2, cls.getId_Class());
                                    stmt.executeUpdate();
                                    displayrecord();

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    } else {
                        DialogAlert.DialogError("Canceled");
                    }

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

    private boolean Activeboolean(int id_Student, int id_class) {
        String query = "Select Active From class_subject Where id_student=? And id_class=? And Active=0 ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_Student);
            stmt.setInt(2, id_class);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void startUpdating() {
        ScheduledExecutorService Excuter = Executors.newSingleThreadScheduledExecutor();
        Excuter.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {

                displayrecord();

            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            currentPageIndex = newIndex.intValue();
            displayrecord();
        });
        displayrecord();
        startUpdating();
    }

}
