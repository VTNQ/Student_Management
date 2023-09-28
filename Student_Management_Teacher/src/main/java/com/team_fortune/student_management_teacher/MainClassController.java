package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
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
import com.team_fortune.student_management_teacher.model.Class;
import static com.team_fortune.student_management_teacher.popUpclass.infoclass;
import com.team_fortune.student_management_teacher.util.MD5;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainClassController implements Initializable {

    @FXML
    private TabPane mainClass;

    @FXML
    private TextField name_class;
    public static String class_name;
    @FXML
    private Label oldname;

    @FXML
    private MFXTextField newName;
    @FXML
    private Tab addClass;
    private int id_class;
    @FXML
    private Tab deleteClass;
    @FXML
    private MFXTextField txtsearch = new MFXTextField();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> ViewCLass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, Boolean> View = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> ListTable = new TableView<>();
    @FXML
    private Tab listClass;
@FXML
private TableColumn<com.team_fortune.student_management_teacher.model.Class,Boolean>colView=new TableColumn<>();
    @FXML
    private Tab updateClass;
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> tblremove = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> DeleteName = new TableColumn<>();
    String className;
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> Classtbl = new TableView<>();

    @FXML
    private TableColumn<Class, String> colClass = new TableColumn<>();
    private Connection conn;
    ObservableList<com.team_fortune.student_management_teacher.model.Class> model = FXCollections.observableArrayList();
    ObservableList<com.team_fortune.student_management_teacher.model.Class> models = FXCollections.observableArrayList();
    ObservableList<com.team_fortune.student_management_teacher.model.Class> model1 = FXCollections.observableArrayList();

    void deleteSearch() {

        
        com.team_fortune.student_management_teacher.util.getDatabaseToModel modest = new com.team_fortune.student_management_teacher.util.getDatabaseToModel();
        List<com.team_fortune.student_management_teacher.model.Class> classes = modest.getDataFromDatabaseClass();
        if (classes != null) {
            model1.clear();
            model1.addAll(classes);
            tblremove.setItems(model1);
            DeleteName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colView.setCellValueFactory(new PropertyValueFactory<>("isActive"));
            colView.setCellFactory(column->new TableCell<Class,Boolean>(){
                 private final MFXButton view=new MFXButton("Delete");
                 {
                 view.setOnAction(event->{
                     com.team_fortune.student_management_teacher.model.Class cl=getTableView().getItems().get(getIndex());
                     id_class=cl.getId();
                     className=cl.getName();
                     try {
            String selectquery="Select count(*) From class a "+"Join class_subject b ON a.id=b.id_class "+"Where a.name=? And b.id_student IS  NULL";
            conn=DBConnection.getConnection();
              String query="Delete From class_subject Where id_class=?";
            String deleteQuery="Delete From class where  name=?";
            PreparedStatement smt=conn.prepareStatement(selectquery);
            smt.setString(1, className);
            ResultSet rs=smt.executeQuery();
           
            PreparedStatement stmt=conn.prepareStatement(query);
            if(rs.next()){
                int count=rs.getInt(1);
                System.out.println(count);
                if(count>0){
                     stmt.setInt(1, id_class);
            stmt.executeUpdate();
            PreparedStatement stmt2=conn.prepareStatement(deleteQuery);
            stmt2.setString(1, className);
            stmt2.executeUpdate();
                com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogSuccess("Delete success");
                model1.clear();
                deleteSearch();
                   stmt.close();
                   stmt2.close();
                 
                }else{
                         com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogInformation("This is class have student");
                }
            }
          
            
            conn.close();
            rs.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
                    
                    

                 });
                 }
                   @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        view.getStyleClass().add("button-design");
        if (empty || item == null ) {
            setGraphic(null);
        } else {
           
           setGraphic(view);
           
          
        
        }
    }
            });
        } else {
            
        }
    }

   

    void searchdata(String searchitem) {
        try {
            conn = DBConnection.getConnection();
            model.clear();
            String query = "Select a.id,a.name From class a Join class_subject b ON a.id=b.id_class Where name like ? GROUP BY a.id,a.name";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchitem + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");

                int id = rs.getInt("id");
                com.team_fortune.student_management_teacher.model.Class ob = new com.team_fortune.student_management_teacher.model.Class(id, name);
                model.add(ob);
            }

            Classtbl.setItems(model);
            colClass.setCellValueFactory(new PropertyValueFactory<>("name"));
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (txtsearch.getText().isEmpty()) {
            model.clear();
            
        }

    }

    void searchdisplay() {
        com.team_fortune.student_management_teacher.util.getDatabaseToModel modest = new com.team_fortune.student_management_teacher.util.getDatabaseToModel();
        List<com.team_fortune.student_management_teacher.model.Class> classes = modest.getDataFromDatabaseClass();
        if (classes != null) {
            model.clear();
            model.addAll(classes);
            Classtbl.setItems(model);
            colClass.setCellValueFactory(new PropertyValueFactory<>("name"));
        } else {

        }

    }

    void displayrecord() {
      try{
          String query="Select a.id,a.name From class a "+"Join class_subject b ON a.id=b.id_class "+"Group by a.id,a.name";
          conn=DBConnection.getConnection();
          model1.clear();
          PreparedStatement stmt=conn.prepareStatement(query);
          ResultSet rs=stmt.executeQuery();
          while(rs.next()){
              int id=rs.getInt("id");
              String name=rs.getString("name");
              com.team_fortune.student_management_teacher.model.Class cla=new com.team_fortune.student_management_teacher.model.Class(id, name);
              model1.add(cla);
          }
           ListTable.setItems(model1);
           ViewCLass.setCellValueFactory(new PropertyValueFactory<>("name"));
           View.setCellValueFactory(new PropertyValueFactory<>("isActive"));
           View.setCellFactory(column->new TableCell<Class,Boolean>(){
           private final MFXButton view=new MFXButton("view");
           {
               view.setOnAction(event->{
                   try {
                        com.team_fortune.student_management_teacher.model.Class cl=getTableView().getItems().get(getIndex());
                       com.team_fortune.student_management_teacher.popUpclass popup=new com.team_fortune.student_management_teacher.popUpclass();
                  if(popup.infoclass(cl.getName())==true){
                      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/popclass.fxml"));
            AnchorPane newpopup = fxmlLoader.load();
            popUpclass mainclass = fxmlLoader.getController();
           
                       mainclass.Informationclass(cl.getName());
            mainclass.up();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));
            popupStage.showAndWait();
            
                  }else{
                      DialogAlert.DialogError("Class haven't student");
                  }
            

          
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                   
           });
           }
            @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        view.getStyleClass().add("button-design");
        if (empty || item == null ) {
            setGraphic(null);
        } else {
           
           setGraphic(view);
           
          
        
        }
    }
           });
      }catch(Exception ex){
          ex.printStackTrace();
      }
           
          
  
}

    private void startUpdating() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Platform.runLater(this::displayrecord);
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void showPopup(Class selectedclass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateClass.fxml"));
            AnchorPane newpopup = fxmlLoader.load();
            MainClassController mainclass = fxmlLoader.getController();
            mainclass.init(selectedclass.getName());
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));
            popupStage.showAndWait();

            searchdisplay();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void init(String className) {
        oldname.setText(className);
    }

    @FXML
    void Addclass(ActionEvent event) {
        try {
            if(!name_class.getText().isEmpty()){
                 int id = 0;
            int id_class=0;
            conn = DBConnection.getConnection();
            String query1="Select id From teacher Where username=?";
            String latest_Query="Select id From class ORDER BY id DESC LIMIT 1";
            String query = "Insert into class(name) values(?)";
            String query_class_subject="Insert into class_subject(id_class,id_teacher) values (?,?)";
            PreparedStatement stmt = conn.prepareStatement(query1);
            stmt.setString(1, MD5.Md5(HomeController.username));
            ResultSet result=stmt.executeQuery();
            while(result.next()){
                id=result.getInt("id");
            }
            PreparedStatement insert=conn.prepareStatement(query);
            insert.setString(1, name_class.getText());
            insert.executeUpdate();
            PreparedStatement Catch_id=conn.prepareStatement(latest_Query);
            ResultSet result1=Catch_id.executeQuery();
            while(result1.next()){
                id_class=result1.getInt("id");
            }
            PreparedStatement stmt2=conn.prepareStatement(query_class_subject);
            stmt2.setInt(1, id_class);
            stmt2.setInt(2, id);
            stmt2.executeUpdate();
            DialogAlert.DialogSuccess("Add class successfully");
            name_class.setText("");
            }else{
                DialogAlert.DialogError("Fiele is Empty");
            }
           conn.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Updateclass(ActionEvent event) {
        try {
            if(!newName.getText().isEmpty()){
               conn = DBConnection.getConnection();
            String query = "Update class set name=? where name=? ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newName.getText());
            stmt.setString(2, oldname.getText());

            stmt.executeUpdate();
            oldname.setText(newName.getText());
            Classtbl.refresh();

               
            } else {
                 com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogError("Delete error");
}
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deleteSearch();
        searchdisplay();
        txtsearch.textProperty().addListener((observable, oldvalue, newValue) -> {
           
    if (!newValue.isEmpty()) {
         model.clear(); 
        searchdata(newValue); 
    } else {
       searchdisplay();
    }
        });

        displayrecord();
        startUpdating();
        Classtbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    com.team_fortune.student_management_teacher.model.Class tbl = Classtbl.getSelectionModel().getSelectedItem();
                    if (tbl != null) {
                        showPopup(tbl);
                        txtsearch.setText("");
                        searchdisplay();
                    }
                }
            }
        });

    }

}

