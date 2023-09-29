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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Label name_class=new Label();
    private int id_class;
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colsubject=new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colteacher=new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_student.models.classmodel> tbldetail=new TableView<>();
      @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> ColMyJoin=new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colregisterDetail=new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_student.models.classmodel> tblclass=new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, String> colclass=new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colJoin=new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.classmodel, Boolean> colDe=new TableColumn<>();
    ObservableList<com.team_fortune.student_management_student.models.classmodel> model = FXCollections.observableArrayList();
private void displayrecord() {
        String query = "Select a.id,a.name From class a JOIN class_subject b ON a.id=b.id_class Where b.id_student!=? OR b.id_student IS  NULL Group by a.id,a.name";
        try {
            model.clear();
            Connection conn = PrimaryController.connectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                com.team_fortune.student_management_student.models.classmodel class_name = new com.team_fortune.student_management_student.models.classmodel(id, name);
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
                        try{
                            String query="Update class_subject set id_student=? Where id_class=?";
                        Connection conn=PrimaryController.connectDB();
                            PreparedStatement stmt=conn.prepareStatement(query);
                            stmt.setInt(1, PrimaryController.loggedInStudentId);
                            stmt.setInt(2, cl.getId());
                            stmt.executeUpdate();
                            PrimaryController.displaysuccessfully("Join successfully");
                        }catch(Exception ex){
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
                            FXMLLoader fxloader=new FXMLLoader(App.class.getResource("Detalclass.fxml"));
                            AnchorPane newPopup;
                            newPopup=fxloader.load();
                            Registered_classController exercise=fxloader.getController();
                            exercise.detailclass(cl.getId());
                            exercise.detailnameclass(cl.getName());
                            Stage PopupStage=new Stage();
                            PopupStage.initModality(Modality.APPLICATION_MODAL);
                            PopupStage.setScene(new Scene(newPopup,500,400));
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
    private void detailclass(int id_class){
          List<com.team_fortune.student_management_student.models.classmodel> resultList = daodb.Detailclass(id_class);
  ObservableList<com.team_fortune.student_management_student.models.classmodel> observableList = FXCollections.observableArrayList(resultList);
        tbldetail.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
    }
    private void detailnameclass(String name_class){
        this.name_class.setText(name_class);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayrecord();
        
    }

    
}
