/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.util.DBconnect;
import com.team_fortune.student_management_student.models.modelsolution;
import java.awt.Desktop;
import java.lang.module.FindException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class DetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Connection conn;
    int id_class;

    @FXML
    public void init() {
       
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    
    }
}
