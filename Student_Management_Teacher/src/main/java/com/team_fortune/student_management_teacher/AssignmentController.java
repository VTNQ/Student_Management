/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.model.Assignments;
import com.team_fortune.student_management_teacher.model.Subject;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class AssignmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Assignments>ListAssignment;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>name_Subject;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>Class_ass;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colAssignment;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,Boolean>colStatus;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colStudent;
    @FXML
    private MFXComboBox<String> name_student=new MFXComboBox<>();
        @FXML
    private MFXComboBox<String> name_subject=new MFXComboBox<>();
        private Connection conn;
    @FXML
    private MFXComboBox<String> name_class=new MFXComboBox<>();
     ObservableList<com.team_fortune.student_management_teacher.model.Assignments> model=FXCollections.observableArrayList();
    private List<String> getClassName(){
        List<com.team_fortune.student_management_teacher.model.Class>classes=new getDatabaseToModel().getDataFromDatabaseClass();
        List<String>Classnames=new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            Classnames.add(cls.getName());
        }
        return Classnames;
    }
    private List<String>getClassSubject(){
        List<com.team_fortune.student_management_teacher.model.Subject> Subject=new getDatabaseToModel().getAllDataFromDataBaseSubject();
        List<String>Subjectname=new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Subject sj : Subject) {
            Subjectname.add(sj.getName());
        }
        return Subjectname;
    }
    private void displayrecord(){
          com.team_fortune.student_management_teacher.util.getDatabaseToModel modest=new com.team_fortune.student_management_teacher.util.getDatabaseToModel();
       List<com.team_fortune.student_management_teacher.model.Assignments> Assignment=modest.getAssignments();
       if(Assignment!=null){
           model.clear();
           model.addAll(Assignment);
         ListAssignment.setItems(model);
                name_Subject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
                Class_ass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
                colAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
                colAssignment.setCellFactory(column->{
                return new TableCell<Assignments,String>(){

                    @Override
                    protected void updateItem(String Item, boolean empty) {
                        super.updateItem(Item, empty);
                        if(!empty && Item!=null && !Item.isEmpty()){
                            Hyperlink hyperlink=new Hyperlink(Item);
                            hyperlink.setOnAction(event->{
                                try {
                                    java.awt.Desktop.getDesktop().browse(new URI(Item));
                                } catch (Exception e) {
                                    DialogAlert.DialogError("URL is not Found");
                                }
                            });
                            setGraphic(hyperlink);
                        }else{
                            setGraphic(null);
                        }
                    }

                };
                });
                colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
                colStatus.setCellFactory(column->new CheckBoxTableCell<Assignments,Boolean>(){
               @Override
               public void updateItem(Boolean Item, boolean empty) {
                   super.updateItem(Item, empty);
                   if(Item!=null && !empty){
                       CheckBox checkbox=new CheckBox();
                       checkbox.setSelected(Item);
                       setGraphic(checkbox);
                       checkbox.setOnAction(event->{
                       Assignments assignment=getTableView().getItems().get(getIndex());
                       assignment.setStatus(checkbox.isSelected());
                       });
                   }else{
                       setGraphic(null);
                   }
               }
                
                });
                colStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
       }
      
    }
    private List<String>getStudent(){
        List<com.team_fortune.student_management_teacher.model.Student> student=new getDatabaseToModel().getDataFromDatabaseStudent();
        if(student!=null){
            List<String>StudentName=new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Student st : student) {
            StudentName.add(st.getName());
        }
        return StudentName;
        }else{
            return new ArrayList<>();
        }
        
    }
  
        @FXML
    private MFXTextField Link_assignment;
       @FXML
    void AddAssignment(ActionEvent event) {
        int id_class=0;
        int id_subject=0;
        int id_teacher=0;
        int id_Assignment=0;
          String query="Select id From class Where name=?";
          String querysubject="Select id From subject Where name=?";
          String queryclass_subject="Select COUNT(*) AS count From class_subject Where id_class=? And id_subject=?";
          String queryTeacher="Select id From teacher where username=?";
          String queryUpdate="Update class_subject set id_assignments=? Where id_teacher=? AND id_subject=? AND id_class=?";
          String insertquery="Insert into assignments(link,status) values(?,?)";
          String latestQuery="Select id From assignments ORDER BY id DESC LIMIT 1";
          String insertClass_subject="Insert into class_subject(id_class,id_subject,id_teacher,id_assignments) values(?,?,?,?)";
           try {
                conn=DBConnection.getConnection();
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1, name_class.getValue());
                ResultSet result=stmt.executeQuery();
                while(result.next()){
                    id_class=result.getInt("id");
                }
                PreparedStatement stmt2=conn.prepareStatement(querysubject);
                stmt2.setString(1, name_subject.getValue());
                ResultSet rs=stmt2.executeQuery();
                while(rs.next()){
                    id_subject=rs.getInt("id");
                }
                PreparedStatement smt3=conn.prepareStatement(queryTeacher);
                smt3.setString(1, MD5.Md5(HomeController.username));
                ResultSet rs2=smt3.executeQuery();
                while(rs2.next()){
                    id_teacher=rs2.getInt("id");
                }
                
                PreparedStatement checkStmt=conn.prepareStatement(queryclass_subject);
                checkStmt.setInt(1, id_class);
                checkStmt.setInt(2, id_subject);
                ResultSet checkResult=checkStmt.executeQuery();
                if(checkResult.next()){
                    int count=checkResult.getInt("count");
                    if(count==2){
                        PreparedStatement insert=conn.prepareStatement(insertquery);
                    insert.setString(1, Link_assignment.getText());
                    insert.setBoolean(2, false);
                    insert.executeUpdate();
                    PreparedStatement latest=conn.prepareStatement(latestQuery);
                    ResultSet latestResult=latest.executeQuery();
                    while(latestResult.next()){
                    id_Assignment=latestResult.getInt("id");
                    }
                    PreparedStatement updateInsert=conn.prepareStatement(queryUpdate);
                    updateInsert.setInt(1, id_Assignment);
                    updateInsert.setInt(2, id_teacher);
                    updateInsert.setInt(3, id_subject);
                    updateInsert.setInt(4, id_class);
                    updateInsert.executeUpdate();
                    DialogAlert.DialogSuccess("Add Assignment Successfully");
                    
                    
                }else{
                        PreparedStatement insert=conn.prepareStatement(insertquery);
                    insert.setString(1, Link_assignment.getText());
                    insert.setBoolean(2, false);
                    insert.executeUpdate();
                    PreparedStatement latest=conn.prepareStatement(latestQuery);
                    ResultSet latestResult=latest.executeQuery();
                    while(latestResult.next()){
                    id_Assignment=latestResult.getInt("id");
                    }
                    PreparedStatement queryinsert=conn.prepareStatement(insertClass_subject);
                    queryinsert.setInt(1, id_class);
                    queryinsert.setInt(2, id_subject);
                    queryinsert.setInt(3, id_teacher);
                    queryinsert.setInt(4, id_Assignment);
                    queryinsert.executeUpdate();
                    }
                    
                }
           } catch (Exception e) {
               e.printStackTrace();
           }
         
        
       

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name_class.getItems().addAll(getClassName());
        name_subject.getItems().addAll(getClassSubject());
        name_student.getItems().addAll(getStudent());
        displayrecord();
    }   
    
}
