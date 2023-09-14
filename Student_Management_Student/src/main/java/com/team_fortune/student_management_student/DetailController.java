/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import java.lang.module.FindException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML
    private TableColumn<modelsolution, String> colclass;

    @FXML
    private TableColumn<modelsolution, String> colexercise;

    @FXML
    private TableColumn<modelsolution, String> colreason;

    @FXML
    private TableColumn<modelsolution, String> colstatus;

    @FXML
    private TableColumn<modelsolution, String> colstt;

    @FXML
    private TableColumn<modelsolution, String> colsubject;
    
    @FXML
    private TableView<modelsolution> tblexercise;
     private final ObservableList<modelsolution> model = FXCollections.observableArrayList();
   
    @FXML
    public void init() {
       
    }
    public void updatestart(ObservableList<modelsolution> data){
        tblexercise.setItems(data);
        colstt.setCellValueFactory(new PropertyValueFactory<>("index"));
        colsubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colclass.setCellValueFactory(new PropertyValueFactory<>("Myclass"));
        colexercise.setCellValueFactory(new PropertyValueFactory<>("exercises"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colreason.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }
    void displayrecord(){
        conn=PrimaryController.connectDB();
        model.clear();
        String query="Select t1.name_subject,t2.name_class,t3.link,t3.status,t3.reason From subject t1 "
                +"JOIN class_subject t4 ON t1.id=t4.id_subject "
                +"JOIN class t2 ON t2.id=t4.id_class "
                +"JOIN  assignments t5 ON t4.id_assignments=t5.id_ass "
                +"JOIN  assignments_student t3 ON t3.id_assignments=t5.id_ass "
                +"JOIN  student t6 ON t6.id=t4.id_student "
                +"Where t6.id =? And t3.id_assignments = ?";
        try {
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            System.out.println(PrimaryController.loggedInStudentId);
            stmt.setInt(2, SecondaryController.assignmentId);
             System.out.println(SecondaryController.assignmentId);
             ResultSet rs=stmt.executeQuery();
             while(rs.next()){
                 int index=1;
                 String name_subject=rs.getString("name_subject");
                 String name_class=rs.getString("name_class");
                 String link=rs.getString("link");
                 Boolean status=rs.getBoolean("status");
                 String resultstatus=(status)?"tot":"lam lai";
                 String reason=rs.getString("reason");
                 model.add(new modelsolution(index, name_subject, name_class, link, resultstatus, reason));
                 index++;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updatestart(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       displayrecord();
    }
}
