/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.util.DBconnect;
import static com.team_fortune.student_management_student.SecondaryController.assignmentId;
import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelExample;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class ExerciseviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, Boolean> Columnassignment;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> Columnclass;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> colExam;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> columnsubject;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> columnteacher;
    ObservableList<com.team_fortune.student_management_student.models.modelExample> observableList;
    public static int assignmentId;

    public int idassignment(String assignment) {
        int id = -1;
        Connection connection = DBconnect.connectDB();
        String query = "Select t1.id From assignments t1 "
                + "JOIN class_subject  t2 ON t1.id=t2.id_assignments "
                + "JOIN subject t3 ON t2.id_subject=t3.id " + "Where t3.name = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, assignment);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            DBconnect.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void DisplayExercise() {
        List<com.team_fortune.student_management_student.models.modelExample> resultList = daodb.ListExample();
  observableList = FXCollections.observableArrayList(resultList);
        tblexample.setItems(observableList);
        columnsubject.setCellValueFactory(new PropertyValueFactory<>("nameSubject"));
        columnteacher.setCellValueFactory(new PropertyValueFactory<>("Home"));
        colExam.setCellValueFactory(new PropertyValueFactory<>("Examp"));
        colExam.setCellFactory(column -> {
            return new TableCell<modelExample, String>() {
                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (!empty && Item != null && !Item.isEmpty()) {
                        Hyperlink hyperlink = new Hyperlink(Item);
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(Item));
                            } catch (Exception ex) {
                                com.teach_fortune.student_management_student.dialog.dialog.displayErrorMessage("Url is not found");
                            }
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }

            };
        });
        Columnclass.setCellValueFactory(new PropertyValueFactory<>("classmy"));
        Columnassignment.setCellValueFactory(new PropertyValueFactory<>("button"));
        Columnassignment.setCellFactory(column -> new TableCell<modelExample, Boolean>() {
            private final MFXButton submit = new MFXButton("Submit");
            private final MFXButton home = new MFXButton("Detail");
            private final HBox buttonsContainer = new HBox();

            {
                submit.setOnAction(event -> {
                    modelExample example = getTableView().getItems().get(getIndex());
                    String subject = example.getNameSubject();
                    assignmentId = idassignment(subject);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("submitassignment.fxml"));
                        AnchorPane newPopup;
                        newPopup = fxmlLoader.load();
                        SubmitassignmentController forgot_password = fxmlLoader.getController();
                        forgot_password.init(subject);
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newPopup, 600, 400));
                        popupStage.setResizable(false);
                        popupStage.show();
                         
                       
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                });
                home.setOnAction(event -> {
                    modelExample example = getTableView().getItems().get(getIndex());
                    String subject = example.getNameSubject();
                    assignmentId = idassignment(subject);
                    try{
                        FXMLLoader fxmlLoader=new FXMLLoader(App.class.getResource("detail.fxml"));
            AnchorPane newPopup;
                newPopup=fxmlLoader.load();
                DetailController detail=fxmlLoader.getController();
                detail.init();
                detail.displayrecord(example.getId());
                      
                Stage popupStage=new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setScene(new Scene(newPopup,668,462));
                popupStage.setResizable(false);
                popupStage.show();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                });
                buttonsContainer.getChildren().addAll(submit, home);
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                submit.getStyleClass().add("btn-design");
                home.getStyleClass().add("btn-design");
                if (empty || Item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsContainer);
                }
            }

        });
        tblexample.refresh();
    }

    @FXML
    private TableView<com.team_fortune.student_management_student.models.modelExample> tblexample;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayExercise();
    }

}
