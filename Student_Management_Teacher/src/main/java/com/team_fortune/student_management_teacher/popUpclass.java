/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tranp
 */
public class popUpclass implements Initializable{
     @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class>tblSubkect=new TableView<>();
      ObservableList<com.team_fortune.student_management_teacher.model.Class> popup=FXCollections.observableArrayList();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class,String>ClassSubject=new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class,String>classStudent=new TableColumn<>();
    public void Informationclass(String class_name){
        try {
            Connection conn=DBConnection.getConnection();
            String query="Select a.name,b.name From class a "+"JOIN class_subject c ON a.id=c.id_class "
                    +"JOIN student b ON c.id_student=b.id "+"JOIN teacher d ON d.id=c.id_teacher "+"Where d.username=? And a.name=?";
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, class_name);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                String name_class=rs.getString("a.name");
                String name_student=rs.getString("b.name");
                com.team_fortune.student_management_teacher.model.Class ob = new com.team_fortune.student_management_teacher.model.Class(name_class, name_student);
               popup.add(ob);
            }
            tblSubkect.setItems(popup);
            ClassSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
            classStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void up(){
    
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(MainClassController.class_name);
        Informationclass(MainClassController.class_name);
    }
}
