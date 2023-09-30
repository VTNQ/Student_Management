/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;
import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelWord;
import com.team_fortune.student_management_student.models.modelsearch;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class WordviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelWord, String> colclass;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelWord, String> colsubject;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelWord, String> colteacher;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelWord, String> colword;

    @FXML
    private TableView<com.team_fortune.student_management_student.models.modelWord> tblWord;
    private void Wordstudent(){
        List<modelWord>result=daodb.worldsubject();
          ObservableList<modelWord> observableList=FXCollections.observableArrayList(result);
          tblWord.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
        colword.setCellValueFactory(new PropertyValueFactory<>("World"));
        colword.setCellFactory(column ->{
              TableCell<modelWord,String> cell=new TableCell<modelWord,String>(){
                  @Override
                  protected void updateItem(String item,boolean empty){
                      super.updateItem(item, empty);
                      if(empty ||item==null ){
                          setText(null);
                          setGraphic(null);
                      }else{
                          Hyperlink hyperlink=new Hyperlink(item);
                          hyperlink.setOnAction(event->{
                              try{
                                   Desktop.getDesktop().browse(new URI(item));
                              }catch(Exception ex){
                                  dialog.displayErrorMessage("URL is not found");
                              
                              }
                             
                          
                          });
                          setGraphic(hyperlink);
                          
                      }
                  }
              };
                  return cell;
              });
        colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Wordstudent();
    }    
    
}
