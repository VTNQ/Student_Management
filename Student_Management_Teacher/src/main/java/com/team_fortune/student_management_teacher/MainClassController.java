package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.util.DBConnection;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainClassController implements Initializable{

    @FXML
    private TabPane mainClass;
     
    @FXML
    private Tab addClass;

    @FXML
    private Tab deleteClass;
    @FXML
    private MFXTextField txtsearch;

    @FXML
    private Tab listClass;

    @FXML
    private Tab updateClass;
    
    @FXML
    private TableView<modelclass> Classtbl;
    

    
    @FXML
    private TableColumn<modelclass, String> colClass;
    private Connection conn;
     private final ObservableList<modelclass>model=FXCollections.observableArrayList();
     void searchdata(String searchitem){
        try {
            conn=DBConnection.getConnection();
            model.clear();
            String query="Select name From class Where name LIKE ?";
           
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, "%"+searchitem+"%");
            
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                String name_class=result.getString("name");
                model.add(new modelclass( name_class));
              
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         updatetabledata(model);
         if(searchitem.isEmpty()){
             model.clear();
         }
         
     }
     public void updatetabledata(ObservableList<modelclass> data){
         Classtbl.setItems(data);
        
         colClass.setCellValueFactory(new PropertyValueFactory<>("myclass"));
     }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txtsearch.textProperty().addListener((observable,oldvalue,newValue)->{
           searchdata(newValue);
           
       });
       
       System.out.print("test");
    }
}
