/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelsearch;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class SearchSubjectController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TableColumn<modelsearch, String> colsubject;

    @FXML
    private TableColumn<modelsearch, String> colteacher;

    @FXML
    private TableView<modelsearch> tbl;

    @FXML
    private TableColumn<modelsearch, String> tblteacher;

    @FXML
    private TextField txtserach;
    private void searchsubject(String searchItem){
        List<modelsearch> resultList = daodb.searchdata(searchItem);
        ObservableList<modelsearch> observableList=FXCollections.observableArrayList(resultList);
        tbl.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
        tblteacher.setCellValueFactory(new PropertyValueFactory<>("name_class"));
    }
    private void searchdisplay(){
        List<modelsearch>result=daodb.Display_subject();
          ObservableList<modelsearch> observableList=FXCollections.observableArrayList(result);
          tbl.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
        tblteacher.setCellValueFactory(new PropertyValueFactory<>("name_class"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchdisplay();
        txtserach.textProperty().addListener((observable, oldValue, newValue)->{
            searchsubject(newValue);
        });
    }    
    
}
