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
import com.team_fortune.student_management_teacher.util.MD5;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
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

    @FXML
    private Tab deleteClass;
    @FXML
    private MFXTextField txtsearch = new MFXTextField();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class,String>ViewCLass=new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class,Boolean>View=new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class>ListTable=new TableView<>();
    @FXML
    private Tab listClass;

    @FXML
    private Tab updateClass;
    @FXML
    private TableView<?> tblremove;
    @FXML
    private TableColumn<?,?>DeleteName;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> Classtbl = new TableView<>();

    @FXML
    private TableColumn<Class, String> colClass;
    private Connection conn;
    ObservableList<com.team_fortune.student_management_teacher.model.Class> model = FXCollections.observableArrayList();
    ObservableList<com.team_fortune.student_management_teacher.model.Class> models = FXCollections.observableArrayList();
   
    void searchdata(String searchitem) {
        try {
            conn = DBConnection.getConnection();
            model.clear();
            String query = "Select id,name From class Where name like ?";
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
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void displayrecord(){
        try {
           Connection  conn=DBConnection.getConnection();
           
           String query="Select a.name_class,a.id From class a "+"Join class_subject b ON a.id=b.id_class "+
                   "Join teacher C ON C.id=b.id_teacher "+"Where C.username=?";
           PreparedStatement stmt=conn.prepareStatement(query);
           stmt.setString(1, MD5.Md5(HomeController.username));
        
           ResultSet rs=stmt.executeQuery();
           while(rs.next()){
               String name_class=rs.getString("name_class");
               int id=rs.getInt("id");
                com.team_fortune.student_management_teacher.model.Class ob = new com.team_fortune.student_management_teacher.model.Class(id, name_class);
               models.add(ob);
           }
           ListTable.setItems(models);
           ViewCLass.setCellValueFactory(new PropertyValueFactory<>("name"));
           View.setCellValueFactory(new PropertyValueFactory<>("isstatic"));
           View.setCellFactory(column->new TableCell<Class,Boolean>(){
        private final MFXButton button=new MFXButton("view");
        {
        button.setOnAction(event->{
            Class classcontroller=getTableView().getItems().get(getIndex());
              class_name=classcontroller.getName();
              System.out.println(class_name);
              
        try{
        FXMLLoader fxmloader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/popclass.fxml"));
        AnchorPane newpopup=fxmloader.load();
        popUpclass main=fxmloader.getController();
        main.up();
      
           
          
           
            
        Stage popupStage=new Stage();
         popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));
    
            popupStage.showAndWait();
          
        }catch(Exception ex){
        ex.printStackTrace();
        }
        });
        }
         @Override
        protected void updateItem(Boolean item, boolean empty){
            super.updateItem(item,empty);
            if(item==null||empty){
                setGraphic(null);
            }else{
                setGraphic(button);
                   button.getStyleClass().add("button-design");
            }
        }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPopup(Class selectedclass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Update.fxml"));
            AnchorPane newpopup = fxmlLoader.load();
            MainClassController mainclass = fxmlLoader.getController();
            mainclass.init(selectedclass.getName());
            Stage popupStage = new Stage();
            System.out.println(selectedclass.getName());

            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));

            popupStage.showAndWait();
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
            conn = DBConnection.getConnection();
            String query = "Insert into class(name) values(?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name_class.getText());
            stmt.executeUpdate();
            name_class.setText("");
            DialogAlert.DialogSuccess("Add class successfully   ");
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Updateclass(ActionEvent event) {
        try {
            conn = DBConnection.getConnection();
            String query = "Update class set name=? where name=? ";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newName.getText());
            stmt.setString(2, oldname.getText());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        txtsearch.textProperty().addListener((observable, oldvalue, newValue) -> {
            searchdata(newValue);
        });
        
        displayrecord();
        Classtbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    com.team_fortune.student_management_teacher.model.Class tbl = Classtbl.getSelectionModel().getSelectedItem();
                    if (tbl != null) {
                        showPopup(tbl);
                    }
                }
            }
        });

    }

}
