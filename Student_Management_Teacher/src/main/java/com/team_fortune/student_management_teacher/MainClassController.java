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
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> ViewCLass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, Boolean> View = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> ListTable = new TableView<>();
    @FXML
    private Tab listClass;

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
            tblremove.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) {
                    Class selectedClass = tblremove.getSelectionModel().getSelectedItem();
                    if (selectedClass != null) {
                        className = selectedClass.getName();

                    }
                }
            });
        } else {

        }

    }

    @FXML
    void Removebtn(ActionEvent event) {
        try {
            Connection conn = DBConnection.getConnection();
            String checkQuery = "Select Count(*) From class_subject Where id_class IN(Select id From class Where name=?)";
            PreparedStatement stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, className);
            ResultSet result = stmt.executeQuery();
            if (result.next() && result.getInt(1) > 0) {
                com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogError("cannot be deleted because the class has students in it");
            } else {
                String query = "Delete From class Where name=?";
                PreparedStatement deletestmt = conn.prepareStatement(query);
                deletestmt.executeUpdate();
                com.team_fortune.student_management_teacher.dialog.DialogAlert.DialogSuccess("Delete success");
                model1.clear();
                deleteSearch();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        try {
            Connection conn = DBConnection.getConnection();

            String query = "Select id,name From class";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            models.clear();
            while (rs.next()) {
                String name_class = rs.getString("name");
                int id = rs.getInt("id");
                com.team_fortune.student_management_teacher.model.Class ob = new com.team_fortune.student_management_teacher.model.Class(id, name_class);
                models.add(ob);
            }
            ListTable.setItems(models);
            ViewCLass.setCellValueFactory(new PropertyValueFactory<>("name"));
            View.setCellValueFactory(new PropertyValueFactory<>("isActive"));
            View.setCellFactory(column -> new TableCell<Class, Boolean>() {
                private final MFXButton button = new MFXButton("view");
                {
                    button.setOnAction(event -> {
                        Class classcontroller = getTableView().getItems().get(getIndex());
                        class_name = classcontroller.getName();

                        try {
                            FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/popclass.fxml"));
                            AnchorPane newpopup = fxmloader.load();
                            popUpclass main = fxmloader.getController();
                            main.up();
                            Stage popupStage = new Stage();
                            popupStage.initModality(Modality.APPLICATION_MODAL);
                            popupStage.setScene(new Scene(newpopup));

                            popupStage.showAndWait();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    });
                }

                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                        button.getStyleClass().add("button-design");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
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
            oldname.setText(newName.getText());
            Classtbl.refresh();
            newName.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(MainClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deleteSearch();
        searchdisplay();
        txtsearch.textProperty().addListener((observable, oldvalue, newValue) -> {
            searchdata(newValue);
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
