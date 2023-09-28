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
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.awt.Desktop;
import java.io.Console;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private TableView<com.team_fortune.student_management_teacher.model.Assignments> ListAssignment = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Integer> colCancel = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> name_Subject = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> Class_ass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colAssignment = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Boolean> colStatus = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Assignments> colupdatetable = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> coluddatesubject = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colUpdateclass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colupdateAssignment = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Boolean> colupdateStatus = new TableColumn<>();

    @FXML
    private MFXComboBox<String> name_student = new MFXComboBox<>();
    @FXML
    private MFXComboBox<String> name_subject = new MFXComboBox<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Integer> colRequest;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colStudent;
    ObservableList<com.team_fortune.student_management_teacher.model.Assignments> observableList;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colStudentAssignment;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Boolean> colSubmit;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Assignments> tblexstudent;
    @FXML
    private Label labelClass;
    @FXML
    private TextField Assignment_link;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Boolean> Status = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colsubject = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> colclass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, String> Assignmentcl = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Assignments> tblDelete = new TableView<>();
    private Connection conn;
    @FXML
    private Label set_name_class;
    @FXML
    private Label set_name_subject;
    @FXML
    private MFXTextField txtsearch = new MFXTextField();
    @FXML
    private MFXComboBox<String> name_class = new MFXComboBox<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Assignments, Boolean> colassign = new TableColumn<>();
    private int id_ass;
    private int id_assignment;

    ObservableList<com.team_fortune.student_management_teacher.model.Assignments> model = FXCollections.observableArrayList();

    private List<String> getClassName() {
        List<com.team_fortune.student_management_teacher.model.Class> classes = new getDatabaseToModel().getDataFromDatabaseClass();
        List<String> Classnames = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            Classnames.add(cls.getName());
        }
        return Classnames;
    }

    private void deleteview() {
        List<com.team_fortune.student_management_teacher.model.Assignments> resultList = getDatabaseToModel.getAssignments();
        ObservableList<com.team_fortune.student_management_teacher.model.Assignments> observableList = FXCollections.observableArrayList(resultList);
        tblDelete.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
        colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        Assignmentcl.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
        Assignmentcl.setCellFactory(column -> {
            return new TableCell<Assignments, String>() {
                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (!empty && Item != null && !Item.isEmpty()) {
                        Hyperlink hyperlink = new Hyperlink(Item);
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(Item));
                            } catch (Exception e) {
                                DialogAlert.DialogError("URL is not Found");
                            }
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }

            };
        });
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        Status.setCellFactory(column -> new CheckBoxTableCell<Assignments, Boolean>() {
            @Override
            public void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                if (Item != null || !empty) {
                    CheckBox checkbox = new CheckBox();
                    checkbox.setSelected(Item);
                    setGraphic(checkbox);
                } else {
                    setGraphic(null);
                }
            }

        });
        tblDelete.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                com.team_fortune.student_management_teacher.model.Assignments selectedItem = tblDelete.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    id_assignment = selectedItem.getId();

                }
            }
        });

    }

    @FXML
    void Deletebtn(ActionEvent event) {
        String solution = "Select count(*) as total From solution where id_assignments=?";
        String query = "Update class_subject set id_assignments=Null Where id_assignments=?";
        String querydelete = "Delete From assignments where id=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmtsolution = conn.prepareStatement(solution);
            stmtsolution.setInt(1, id_assignment);
            ResultSet rssolution = stmtsolution.executeQuery();
            if (rssolution.next()) {
                int count = rssolution.getInt(1);
                if (count > 0) {
                    DialogAlert.DialogError("Can not Delete");
                } else {
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, id_assignment);
                    stmt.executeUpdate();
                    PreparedStatement deletestmt = conn.prepareStatement(querydelete);
                    deletestmt.setInt(1, id_assignment);
                    deletestmt.executeUpdate();
                    startUpdating();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startUpdating() {
        ScheduledExecutorService Excuter = Executors.newSingleThreadScheduledExecutor();
        Excuter.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                deleteview();
                displayrecord();
                searchdisplay();
                searchfield(txtsearch.getText());
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void searchassignmentdisplay() {

    }

    private List<String> getClassSubject() {
        List<com.team_fortune.student_management_teacher.model.Subject> Subject = new getDatabaseToModel().getDataFromDatabaseSubject();
        List<String> Subjectname = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Subject sj : Subject) {
            Subjectname.add(sj.getName());
        }
        return Subjectname;
    }

    private void displayrecord() {
        List<com.team_fortune.student_management_teacher.model.Assignments> resultList = getDatabaseToModel.getAssignments();
        ObservableList<com.team_fortune.student_management_teacher.model.Assignments> observableList = FXCollections.observableArrayList(resultList);

        model.clear();

        ListAssignment.setItems(observableList);
        name_Subject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
        Class_ass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
        colAssignment.setCellFactory(column -> {
            return new TableCell<Assignments, String>() {

                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (!empty && Item != null && !Item.isEmpty()) {
                        Hyperlink hyperlink = new Hyperlink(Item);
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(Item));
                            } catch (Exception e) {
                                DialogAlert.DialogError("URL is not Found");
                            }
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }

            };
        });
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colStatus.setCellFactory(column -> new CheckBoxTableCell<Assignments, Boolean>() {
            @Override
            public void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                if (Item != null && !empty) {
                    CheckBox checkbox = new CheckBox();
                    checkbox.setSelected(Item);
                    setGraphic(checkbox);
                    checkbox.setOnAction(event -> {
                        Assignments assignment = getTableView().getItems().get(getIndex());
                        assignment.setStatus(checkbox.isSelected());
                        updateAssignment(assignment.getStatus(), assignment.getId());
                        if (assignment.getStatus() == true) {
                            DialogAlert.DialogSuccess("Active Success");
                        } else {
                            DialogAlert.DialogSuccess("Unactive Success");
                        }

                    });
                } else {
                    setGraphic(null);
                }
            }

        });
        colassign.setCellValueFactory(new PropertyValueFactory<>("seeAssignment"));
        colassign.setCellFactory(column -> new TableCell<>() {
            private final MFXButton button = new MFXButton("Watch");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Assignments Ass = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/watchassign.fxml"));
                        AnchorPane newpopup = loader.load();
                        AssignmentController assignment = loader.getController();
                        assignment.settext(Ass.getName_class(), Ass.getName_Subject());
                        assignment.WatchAssign(Ass.getName_class(), Ass.getName_Subject(), Ass.getId());
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newpopup));
                        popupStage.setResizable(false);
                        popupStage.showAndWait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-design");
                if (empty || Item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });

    }

    private void updateAssignment(Boolean status, int id_Assignment) {
        try {
            conn = DBConnection.getConnection();
            String query = "Update assignments set status=? Where id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
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
        int id_class = 0;
        int id_subject = 0;
        int id_teacher = 0;
        int id_Assignment = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select id From class Where name=?";
            String update_Assign = "Update class_subject set id_assignments=? Where id_class=? And id_subject=?";
            String insertAssignment = "Insert into assignments(link) values(?)";
            String check_query = "Select Count(*) AS total From class_subject Where id_class=? And id_subject=?  And id_teacher=?";
            String query_Teacher = "Select id From teacher Where username=?";
            String latest_Query = "Select id From assignments ORDER BY id DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name_class.getValue());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                id_class = result.getInt("id");
            }
            String query_subject = "Select id From subject Where name=?";
            PreparedStatement stmtsubject = conn.prepareStatement(query_subject);
            stmtsubject.setString(1, name_subject.getValue());
            ResultSet resultsubject = stmtsubject.executeQuery();
            while (resultsubject.next()) {
                id_subject = resultsubject.getInt("id");
            }
            PreparedStatement stmt_teacher = conn.prepareStatement(query_Teacher);
            stmt_teacher.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs_teacher = stmt_teacher.executeQuery();
            while (rs_teacher.next()) {
                id_teacher = rs_teacher.getInt("id");
            }
            PreparedStatement stmt_check = conn.prepareStatement(check_query);
            stmt_check.setInt(1, id_class);
            stmt_check.setInt(2, id_subject);
            stmt_check.setInt(3, id_teacher);
            ResultSet result_check = stmt_check.executeQuery();
            if (result_check.next()) {
                int total = result_check.getInt("total");
                if (total > 0) {
                    PreparedStatement stmt_insert = conn.prepareStatement(insertAssignment);
                    stmt_insert.setString(1, Link_assignment.getText());
                    stmt_insert.executeUpdate();
                    PreparedStatement stmt_Assignment = conn.prepareStatement(latest_Query);
                    ResultSet rs_Assignment = stmt_Assignment.executeQuery();
                    while (rs_Assignment.next()) {
                        id_Assignment = rs_Assignment.getInt("id");
                    }
                    PreparedStatement updateInsert = conn.prepareStatement(update_Assign);
                    updateInsert.setInt(1, id_Assignment);
                    updateInsert.setInt(2, id_class);
                    updateInsert.setInt(3, id_subject);
                    updateInsert.executeUpdate();

                    DialogAlert.DialogSuccess("Add Assignment");
                    startUpdating();
                }

            }
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void WatchAssign(String name_class, String name_subject, int id_Assignment) {
        List<com.team_fortune.student_management_teacher.model.Assignments> resullist = getDatabaseToModel.WatchAssStudent(name_class, id_Assignment, name_subject);
        observableList = FXCollections.observableArrayList(resullist);
        tblexstudent.setItems(observableList);
        colStudent.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        colStudentAssignment.setCellValueFactory(new PropertyValueFactory<>("link_student"));
        colStudentAssignment.setCellFactory(column -> {

            return new TableCell<com.team_fortune.student_management_teacher.model.Assignments, String>() {
                private final Hyperlink hyperlink = new Hyperlink();

                @Override
                protected void updateItem(String linkStudent, boolean empty) {

                    super.updateItem(linkStudent, empty);
                    {
                        hyperlink.setOnAction(event -> {
                            try {
                                Desktop.getDesktop().browse(new URI(linkStudent));
                            } catch (Exception e) {
                                DialogAlert.DialogError("URL is not found");
                            }

                        });
                    }
                    if (!empty && getTableRow() != null && getTableRow().getItem() != null) {

                        com.team_fortune.student_management_teacher.model.Assignments assignment = getTableRow().getItem();
                        if (assignment.getName_student() != null && !assignment.getName_student().isEmpty()
                                || assignment.getLink_student() != null && !assignment.getLink_student().isEmpty()) {

                            if (linkStudent != null && !linkStudent.isEmpty()) {
                                hyperlink.setText(linkStudent);
                                setGraphic(hyperlink);
                            } else {
                                setText("The student has not submitted the assignment yet");
                            }
                        } else {
                            setText("");
                        }
                    } else {
                        setText("");
                    }
                }
            };
        });
        colRequest.setCellValueFactory(new PropertyValueFactory<>("statussolution"));

        colRequest.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Assignments, Integer>() {

            private final MFXButton button = new MFXButton("Approve");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Assignments Ass = getTableView().getItems().get(getIndex());

                    try {
                        if(Ass.getStatussolution()==0){
                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you Approved?");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.CANCEL) {
                                alert.close();
                            }
                           
                                 if (response == ButtonType.OK) {
                                String query = "Update solution set status=? where id_assignments=? And status=0";
                                try {
                                    Connection conn = DBConnection.getConnection();
                                    PreparedStatement stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, 1);
                                    stmt.setInt(2, Ass.getId());
                                    stmt.executeUpdate();
                                    List<com.team_fortune.student_management_teacher.model.Assignments> resultlist = getDatabaseToModel.WatchAssStudent(name_class, id_Assignment, name_subject);
                                    observableList.clear();
                                    observableList.addAll(resultlist);
                                    tblexstudent.refresh();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AssignmentController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                           

                            
                        });
 
                        }else{
                            DialogAlert.DialogInformation("Assignment is Approved");
                        }
                     
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

            }

            @Override
            protected void updateItem(Integer Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-success");

                if (Item == null || empty) {
                    setGraphic(null);
                } else if (Item == 0) {
                    setGraphic(button);
                } else if (Item == 1) {
                    setGraphic(button);

                } else if (Item == 2) {
                    setGraphic(button);
                    button.setDisable(true);
                }
            }

        });
        colCancel.setCellValueFactory(new PropertyValueFactory<>("statussolution"));
        colCancel.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Assignments, Integer>() {
            private final MFXButton button = new MFXButton("Cancel");

            {
                button.setOnAction(event -> {
                    try {
                          com.team_fortune.student_management_teacher.model.Assignments Ass = getTableView().getItems().get(getIndex());
                        if(Ass.getStatussolution()==0){
                              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("CONFIRMATION");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you Approved?");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.CANCEL) {
                                alert.close();
                            }
                           
                                 if (response == ButtonType.OK) {
                                String query = "Update solution set status=? where id_assignments=? And status=0";
                                try {
                                    Connection conn = DBConnection.getConnection();
                                    PreparedStatement stmt = conn.prepareStatement(query);
                                    stmt.setInt(1, 2);
                                    stmt.setInt(2, Ass.getId());
                                    stmt.executeUpdate();
                                    List<com.team_fortune.student_management_teacher.model.Assignments> resultlist = getDatabaseToModel.WatchAssStudent(name_class, id_Assignment, name_subject);
                                    observableList.clear();
                                    observableList.addAll(resultlist);
                                    tblexstudent.refresh();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AssignmentController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                           

                            
                        });
 
                        }else{
                            DialogAlert.DialogInformation("Assignment is canceled");
                        }
                     
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Integer Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-error");
                if (Item == null || empty) {
                    setGraphic(null);
                } else if (Item == 0) {
                    setGraphic(button);

                } else if (Item == 2) {
                    setGraphic(button);

                } else if (Item == 1) {
                    setGraphic(button);
                    button.setDisable(true);
                }
            }

        });
    }

    public void searchdisplay() {
        List<com.team_fortune.student_management_teacher.model.Assignments> resultList = getDatabaseToModel.getAssignments();
        ObservableList<com.team_fortune.student_management_teacher.model.Assignments> observableList = FXCollections.observableArrayList(resultList);

        colupdatetable.setItems(observableList);
        coluddatesubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
        colUpdateclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colupdateAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
        colupdateAssignment.setCellFactory(column -> {
            return new TableCell<Assignments, String>() {

                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (!empty && Item != null && !Item.isEmpty()) {
                        Hyperlink hyperlink = new Hyperlink(Item);
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(Item));
                            } catch (Exception e) {
                                DialogAlert.DialogError("URL is not Found");
                            }
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }

            };
        });

        colupdateStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colupdateStatus.setCellFactory(column -> new CheckBoxTableCell<Assignments, Boolean>() {
            @Override
            public void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                if (Item != null && !empty) {
                    CheckBox checkbox = new CheckBox();
                    checkbox.setSelected(Item);
                    setGraphic(checkbox);
                    checkbox.setOnAction(event -> {
                        Assignments assignment = getTableView().getItems().get(getIndex());
                        assignment.setStatus(checkbox.isSelected());
                        updateAssignment(assignment.getStatus(), assignment.getId());
                        if (assignment.getStatus() == true) {
                            DialogAlert.DialogSuccess("Active is success");
                        } else {
                            DialogAlert.DialogSuccess("Unsctive is success");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });

    }

    private void showpopup(Assignments assign) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assign_popup.fxml"));
            AnchorPane newpopup = loader.load();
            AssignmentController assignment = loader.getController();
            assignment.init(assign.getName_class());
            assignment.setidass(assign.getId());
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newpopup));
            popupStage.setResizable(false);
            popupStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(String name_class) {
        labelClass.setText(name_class);
    }

    @FXML
    private void updatepopup() {
        String query = "Update assignments set link=? Where id=?";
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Assignment_link.getText());
            stmt.setInt(2, getidass());
            stmt.executeUpdate();
            Assignment_link.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchfield(String searchfield) {
        String query = "SELECT  c.id AS id,a.name AS subject_name, b.name AS class_name, c.link, c.status FROM subject a "
                + "INNER JOIN class_subject d ON a.id = d.id_subject "
                + "INNER JOIN class b ON b.id = d.id_class "
                + "INNER JOIN assignments c ON c.id = d.id_assignments "
                + "INNER JOIN teacher f ON f.id = d.id_teacher "
                + "WHERE f.username = ? And b.name like ? " + "Group by c.id,a.name,b.name,c.link,c.status";
        try {
            model.clear();
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, "%" + searchfield + "%");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name_subject = result.getString("subject_name");
                String name_class = result.getString("class_name");
                String link = result.getString("link");
                Boolean status = result.getBoolean("status");
                com.team_fortune.student_management_teacher.model.Assignments ass = new com.team_fortune.student_management_teacher.model.Assignments(name_subject, name_class, link, status, id);
                model.add(ass);
            }
            colupdatetable.setItems(model);
            coluddatesubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
            colUpdateclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
            colupdateAssignment.setCellValueFactory(new PropertyValueFactory<>("Assignment"));
            colupdateAssignment.setCellFactory(column -> {
                return new TableCell<Assignments, String>() {

                    @Override
                    protected void updateItem(String Item, boolean empty) {
                        super.updateItem(Item, empty);
                        if (!empty && Item != null && !Item.isEmpty()) {
                            Hyperlink hyperlink = new Hyperlink(Item);
                            hyperlink.setOnAction(event -> {
                                try {
                                    java.awt.Desktop.getDesktop().browse(new URI(Item));
                                } catch (Exception e) {
                                    DialogAlert.DialogError("URL is not Found");
                                }
                            });
                            setGraphic(hyperlink);
                        } else {
                            setGraphic(null);
                        }
                    }

                };
            });

            colupdateStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
            colupdateStatus.setCellFactory(column -> new CheckBoxTableCell<Assignments, Boolean>() {
                @Override
                public void updateItem(Boolean Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (Item != null && !empty) {
                        CheckBox checkbox = new CheckBox();
                        checkbox.setSelected(Item);
                        setGraphic(checkbox);
                        checkbox.setOnAction(event -> {
                            Assignments assignment = getTableView().getItems().get(getIndex());
                            assignment.setStatus(checkbox.isSelected());
                        });
                    } else {
                        setGraphic(null);
                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setidass(int id_Ass) {
        id_ass = id_Ass;
    }

    private int getidass() {
        return id_ass;
    }

    private void settext(String name_class, String name_subject) {
        this.set_name_class.setText(name_class);
        this.set_name_subject.setText(name_subject);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deleteview();
        searchdisplay();

        txtsearch.textProperty().addListener((observable, oldvalue, newValue) -> {

            searchfield(newValue);

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
