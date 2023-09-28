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
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colStatus;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colStudent;
    @FXML
    private MFXComboBox<String> name_student=new MFXComboBox<>();
        @FXML
    private MFXComboBox<String> name_subject=new MFXComboBox<>();
        
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
        List<com.team_fortune.student_management_teacher.model.Subject> Subject=new getDatabaseToModel().getDataFromDatabaseSubject();
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
                            
                        }
                    }

                };
                });
                colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
                colStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
       }else{

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
    private int getSubject(String subject){
        int id=-1;
        try {
            Connection conn=DBConnection.getConnection();
            String query="Select id From subject Where name=?";
            PreparedStatement statement=conn.prepareStatement(query);
            statement.setString(1, subject);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                id=result.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
       return id;
    }
    private int getClass(String name_class){
        int id=-1;
        try {
            Connection conn=DBConnection.getConnection();
            String query="Select id From class Where name=?";
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, name_class);
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                id=result.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    private int getStudent(String name_student){
        int id=-1;
        try {
            Connection conn=DBConnection.getConnection();
            String query="Select id From student Where name=?";
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, name_student  );
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                id=result.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
  private int latest_assignment(){
      int id=-1;
      String query="Select id From assignments ORDER BY id DESC LIMIT 1";
      try {
          Connection conn=DBConnection.getConnection();
          PreparedStatement stmt=conn.prepareStatement(query);
          ResultSet result=stmt.executeQuery();
          if(result.next()){
              id=result.getInt(1);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return id;
  }
  private int getTeacher(String username){
      int id=-1;
      String query="Select id From teacher Where username=?";
        try {
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                id=result.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
  }
        @FXML
    private MFXTextField Link_assignment;
       @FXML
    void AddAssignment(ActionEvent event) {
        if(!Link_assignment.getText().isEmpty() && !name_student.getValue().isEmpty()&& !name_subject.getValue().isEmpty() && !name_class.getValue().isEmpty()){
                 String query="Insert into assignments(link) values(?)";
      String query1="Insert into class_subject(id_class,id_subject,id_teacher,id_student,id_assignments) values(?,?,?,?,?)";
           try {
               Connection conn=DBConnection.getConnection();
               PreparedStatement stmt=conn.prepareStatement(query);
               stmt.setString(1, Link_assignment.getText());
               stmt.executeUpdate();
               int id_Assignments=latest_assignment();
               int id_class=getClass(name_class.getValue());
               int id_subject=getSubject(name_subject.getValue());
               int id_student=getStudent(name_student.getValue());
               int id_teacher=getTeacher(MD5.Md5(HomeController.username));
               PreparedStatement stmt2=conn.prepareStatement(query1);
               stmt2.setInt(1, id_class);
               stmt2.setInt(2, id_subject);
               stmt2.setInt(3, id_teacher);
               stmt2.setInt(4, id_student);
               stmt2.setInt(5, id_Assignments);
               stmt2.executeUpdate();
               com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogSuccess("Insert success");
           } catch (Exception e) {
               e.printStackTrace();
           }
        }else{
            DialogAlert.DialogError("Field is Empty");
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
