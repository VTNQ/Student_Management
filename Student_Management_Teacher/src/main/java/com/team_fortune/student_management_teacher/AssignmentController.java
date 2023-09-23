/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.model.Assignments;

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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TableView<com.team_fortune.student_management_teacher.model.Assignments>ListAssignment=new TableView<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>name_Subject=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>Class_ass=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colAssignment=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,Boolean>colStatus=new TableColumn<>();
@FXML
private TableView<com.team_fortune.student_management_teacher.model.Assignments>colupdatetable=new TableView<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>coluddatesubject=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colUpdateclass=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,String>colupdateAssignment=new TableColumn<>();
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Assignments,Boolean>colupdateStatus=new TableColumn<>();
    
    @FXML
    private MFXComboBox<String> name_student=new MFXComboBox<>();
        @FXML
    private MFXComboBox<String> name_subject=new MFXComboBox<>();
        @FXML
        private Label labelClass;
        @FXML
        private TextField Assignment_link;
        private Connection conn;
            @FXML
    private MFXTextField txtsearch=new MFXTextField();
    @FXML
    private MFXComboBox<String> name_class=new MFXComboBox<>();
    private int id_ass;
    
     ObservableList<com.team_fortune.student_management_teacher.model.Assignments> model=FXCollections.observableArrayList();
    private List<String> getClassName(){
        List<com.team_fortune.student_management_teacher.model.Class>classes=new getDatabaseToModel().getDataFromDatabaseClass();
        List<String>Classnames=new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            Classnames.add(cls.getName());
        }
        return Classnames;
    }
    private void searchassignmentdisplay(){
        
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
                          updateAssignment(assignment.getStatus(),assignment.getId());
                          if(assignment.getStatus()==true){
                               DialogAlert.DialogSuccess("Active Success");
                          }else{
                               DialogAlert.DialogSuccess("Unactive Success");
                          }
                         
                       });
                   }else{
                       setGraphic(null);
                   }
               }
                
                });
               
       }
      
    }
    private void updateAssignment(Boolean status,int id_Assignment){
        try {
            conn=DBConnection.getConnection();
            String query="Update assignments set status=? Where id=?";
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setBoolean(1, status);
            stmt.setInt(2, id_Assignment);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public void searchdisplay(){
        com.team_fortune.student_management_teacher.util.getDatabaseToModel modest=new com.team_fortune.student_management_teacher.util.getDatabaseToModel();
        List<com.team_fortune.student_management_teacher.model.Assignments>Assign=modest.getAssignments();
        if(Assign!=null){
            model.clear();
            model.addAll(Assign);
             colupdatetable.setItems(model);
            coluddatesubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
            colUpdateclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
            colupdateAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
             colupdateAssignment.setCellFactory(column->{
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
             
            colupdateStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            colupdateStatus.setCellFactory(column->new CheckBoxTableCell<Assignments,Boolean>(){
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
                            updateAssignment(assignment.getStatus(), assignment.getId());
                            if(assignment.getStatus()==true){
                               DialogAlert.DialogSuccess("Active is success");
                            }else{
                                DialogAlert.DialogSuccess("Unsctive is success");
                            }
                        });
                    }else{
                        setGraphic(null);
                    }
                }
                
        });
        }
    }
    private void showpopup(Assignments assign){
        try {
            FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assign_popup.fxml"));
            AnchorPane  newpopup=loader.load();
            AssignmentController assignment=loader.getController();
            assignment.init(assign.getName_class());
            assignment.setidass(assign.getId());
            Stage popupStage=new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));
            popupStage.setResizable(false);
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init(String name_class){
        labelClass.setText(name_class);
    }
    @FXML
    private void updatepopup(){
        String query="Update assignments set link=? Where id=?";
        try {
            conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, Assignment_link.getText());
            stmt.setInt(2, getidass());
            stmt.executeUpdate();
            Assignment_link.setText("");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void searchfield(String searchfield){
        String query="SELECT  c.id AS id,a.name AS subject_name, b.name AS class_name, c.link, c.status FROM subject a " +
                       "INNER JOIN class_subject d ON a.id = d.id_subject " +
                       "INNER JOIN class b ON b.id = d.id_class " +
                       "INNER JOIN assignments c ON c.id = d.id_assignments " +
                       "INNER JOIN teacher f ON f.id = d.id_teacher " +
                       "WHERE f.username = ? And b.name like ? "+"Group by c.id,a.name,b.name,c.link,c.status";
        try {
            model.clear();
            conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, "%" + searchfield + "%");
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                int id=result.getInt("id");
                String name_subject=result.getString("subject_name");
                String name_class=result.getString("class_name");
                String link=result.getString("link");
                Boolean status=result.getBoolean("status");
                com.team_fortune.student_management_teacher.model.Assignments ass=new com.team_fortune.student_management_teacher.model.Assignments(name_subject, name_class, link, status, id);
                model.add(ass);
            }
            colupdatetable.setItems(model);
            coluddatesubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
            colUpdateclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
            colupdateAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
            colupdateAssignment.setCellFactory(column->{
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

            colupdateStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            colupdateStatus.setCellFactory(column->new CheckBoxTableCell<Assignments,Boolean>(){
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
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    private void setidass(int id_Ass){
        id_ass=id_Ass;
    }
    private int getidass(){
        return id_ass;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        searchdisplay();
             txtsearch.textProperty().addListener((observable, oldvalue, newValue) -> {
                 if(!newValue.isEmpty()){
                     model.clear();
                    searchfield(newValue);
                 }else{
                    searchdisplay();
                 }
                 
    });
                     
        name_class.getItems().addAll(getClassName());
        name_subject.getItems().addAll(getClassSubject());
        displayrecord();
          colupdatetable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    com.team_fortune.student_management_teacher.model.Assignments ass = colupdatetable.getSelectionModel().getSelectedItem();
                    if (ass != null) {
                        showpopup(ass);
                        txtsearch.setText("");
                        searchdisplay();
                    }
                }
            }
        });

    }   
    
}
