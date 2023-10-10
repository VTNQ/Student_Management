/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;
import com.teach_fortune.student_management_student.util.DBconnect;
import static com.team_fortune.student_management_student.SecondaryController.assignmentId;
import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelExample;
import com.team_fortune.student_management_student.models.modelsolution;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class ExerciseviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<modelsolution, String> colclass = new TableColumn<>();

    @FXML
    private TableColumn<modelsolution, String> colexercise = new TableColumn<>();

    @FXML
    private TableColumn<modelsolution, String> colreason = new TableColumn<>();

    @FXML
    private TableColumn<modelsolution, Integer> colstatus = new TableColumn<>();

    @FXML
    private TableColumn<modelsolution, String> colstt = new TableColumn<>();

    @FXML
    private TableColumn<modelsolution, String> colsubject = new TableColumn<>();

    @FXML
    private TableView<modelsolution> tblexercise = new TableView<>();

    private final ObservableList<modelsolution> model = FXCollections.observableArrayList();
    @FXML
    private TableView<com.team_fortune.student_management_student.models.modelExample> tblexample = new TableView<>();
    @FXML
    private TextField linkfield;
    @FXML
    private Label subject;
    private Connection conn;
    private int subjectid;
    private int classid;
    private int assid;
    private int id_class;
    private int id_subject;
    private int id_solution;

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, Boolean> Columnassignment = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> Columnclass = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> colExam = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> columnsubject = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_student.models.modelExample, String> columnteacher = new TableColumn<>();
    ObservableList<com.team_fortune.student_management_student.models.modelExample> observableList;
    public static int assignmentId;

    public int idassignment(String assignment) {
        int id = -1;
        Connection connection = DBconnect.connectDB();
        String query = "Select t1.id From assignments t1 "
                + "JOIN class_subject  t2 ON t1.id=t2.id_assignments "
                + "JOIN subject t3 ON t2.id_subject=t3.id " + "Where t3.name = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, assignment);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
            DBconnect.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void setid_class(int id_class) {
        this.classid = id_class;
    }

    private int getid_class() {
        return classid;
    }

    private void setid_subject(int id_subject) {
        this.subjectid = id_subject;
    }

    private int getsubject() {
        return subjectid;
    }

    private void setid_ass(int id_ass) {
        this.assid = id_ass;
    }

    private int getid_ss() {
        return assid;
    }

    private boolean tableEmpty(int id_Assignment) {
        String query = "Select * From class_subject Where id_assignments=? And id_solution IS Not null";
        try {
            Connection conn = DBconnect.connectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_Assignment);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void init(String subject) {
        this.subject.setText(subject);
    }

    private void DisplayExercise() {
        List<com.team_fortune.student_management_student.models.modelExample> resultList = daodb.ListExample();
        observableList = FXCollections.observableArrayList(resultList);
        tblexample.setItems(observableList);
        columnsubject.setCellValueFactory(new PropertyValueFactory<>("nameSubject"));
        columnteacher.setCellValueFactory(new PropertyValueFactory<>("Home"));
        colExam.setCellValueFactory(new PropertyValueFactory<>("Examp"));
        colExam.setCellFactory(column -> {
            return new TableCell<modelExample, String>() {
                @Override
                protected void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    if (!empty && Item != null && !Item.isEmpty()) {
                        Hyperlink hyperlink = new Hyperlink(Item);
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(Item));
                            } catch (Exception ex) {
                                com.teach_fortune.student_management_student.dialog.dialog.displayErrorMessage("Url is not found");
                            }
                        });
                        setGraphic(hyperlink);
                    } else {
                        setGraphic(null);
                    }
                }

            };
        });

        Columnclass.setCellValueFactory(new PropertyValueFactory<>("classmy"));
        Columnassignment.setCellValueFactory(new PropertyValueFactory<>("button"));
        Columnassignment.setCellFactory(column -> new TableCell<modelExample, Boolean>() {
            private final MFXButton submit = new MFXButton("Submit");
            private final MFXButton home = new MFXButton("Detail");
            private final HBox buttonsContainer = new HBox();

            {
                submit.setOnAction(event -> {
                    modelExample example = getTableView().getItems().get(getIndex());
                    String subject = example.getNameSubject();
                    assignmentId = idassignment(subject);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("submitassignment.fxml"));
                        AnchorPane newPopup;
                        newPopup = fxmlLoader.load();
                        ExerciseviewController forgot_password = fxmlLoader.getController();
                        forgot_password.init(subject);
                        id_class = forgot_password.get_class(example.getClassmy());

                        id_subject = forgot_password.getid_subject(example.getNameSubject());
                        forgot_password.setid_subject(id_subject);
                        forgot_password.setid_ass(example.getId());
                        forgot_password.setid_class(id_class);

                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newPopup, 600, 400));
                        popupStage.setResizable(false);
                        popupStage.show();

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                });
                home.setOnAction(event -> {
                    modelExample example = getTableView().getItems().get(getIndex());
                    String subject = example.getNameSubject();
                    assignmentId = idassignment(subject);
                    if (tableEmpty(assignmentId)) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("detail.fxml"));
                            AnchorPane newPopup;
                            newPopup = fxmlLoader.load();
                            ExerciseviewController detail = fxmlLoader.getController();

                            int id_class = detail.get_class(example.getClassmy());
                            int id_subject = detail.getid_subject(example.getNameSubject());
                            detail.displayrecord(id_class, id_subject);
                            Stage popupStage = new Stage();
                            popupStage.initModality(Modality.APPLICATION_MODAL);
                            popupStage.setScene(new Scene(newPopup, 668, 462));
                            popupStage.setResizable(false);
                            popupStage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        dialog.displayErrorMessage("Student is submit");
                    }

                });
                buttonsContainer.getChildren().addAll(submit, home);
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                submit.getStyleClass().add("btn-design");
                home.getStyleClass().add("btn-design");
                if (empty || Item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsContainer);
                }
            }

        });
        tblexample.refresh();
    }

    private int get_class(String name_class) {
        int id_class = -1;
        try {
            conn = PrimaryController.connectDB();
            String checkquery = "Select id From class where name=?";
            PreparedStatement stmt = conn.prepareStatement(checkquery);
            stmt.setString(1, name_class);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id_class = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_class;

    }

    private int getid_subject(String name_subject) {
        int id_subject = -1;
        try {
            conn = PrimaryController.connectDB();
            String checkquery = "Select id From subject class where name=?";
            PreparedStatement stmt = conn.prepareStatement(checkquery);
            stmt.setString(1, name_subject);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id_subject = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_subject;
    }

    @FXML
    void submit(ActionEvent event) {
        int id_solution = -1;
        if (!linkfield.getText().isEmpty()) {
            conn = PrimaryController.connectDB();

            String query = "INSERT INTO solution(link,status) values (?,?)";
            String latestquery = "Select id From solution ORDER BY id DESC LIMIT 1";
            String checkquery = "Select count(*) as total From solution a Join class_subject b ON a.id=b.id_solution where b.id_assignments=? And id_student=?";

            String solutionid = "Select a.id From solution a Join class_subject b ON a.id=b.id_solution Where b.id_student=? And id_assignments=?";
            String updateQuery = "UPDATE class_subject SET id_solution=? WHERE id_class=? And id_subject=? And id_assignments=? And id_student=?";
            String Updatesolution = "Update solution set link=?,status=0,reason=Null where id=?";
            try {
                PreparedStatement stmtcheckquery = conn.prepareStatement(checkquery);
                stmtcheckquery.setInt(1, getid_ss());
                stmtcheckquery.setInt(2, PrimaryController.loggedInStudentId);
                stmtcheckquery.executeQuery();
                ResultSet rscheckquery = stmtcheckquery.executeQuery();
                if (rscheckquery.next()) {
                    int cout = rscheckquery.getInt("total");
                    if (cout > 0) {
                        PreparedStatement stmtsolution = conn.prepareStatement(solutionid);
                        stmtsolution.setInt(1, PrimaryController.loggedInStudentId);
                        stmtsolution.setInt(2, getid_ss());
                        ResultSet rssolution = stmtsolution.executeQuery();
                        if (rssolution.next()) {
                            id_solution = rssolution.getInt("id");

                        }
                        PreparedStatement stmtupdate = conn.prepareStatement(Updatesolution);
                        stmtupdate.setString(1, linkfield.getText());
                        stmtupdate.setInt(2, id_solution);
                        stmtupdate.executeUpdate();
                        displaysuccessfully("submit solution successfully");
                    }else{
                        
  PreparedStatement stmt=conn.prepareStatement(query);
          stmt.setString(1, linkfield.getText());
          stmt.setInt(2, 0);
          stmt.executeUpdate();
          PreparedStatement lateststmt=conn.prepareStatement(latestquery);
          ResultSet rs=lateststmt.executeQuery();
          while(rs.next()){
              id_solution=rs.getInt("id");
          }
          PreparedStatement inserstmt=conn.prepareStatement(updateQuery);
          inserstmt.setInt(1, id_solution);
          inserstmt.setInt(2, getid_class());
          inserstmt.setInt(3, getsubject());
          inserstmt.setInt(4, getid_ss());
          inserstmt.setInt(5, PrimaryController.loggedInStudentId);
          inserstmt.executeUpdate();
            displaysuccessfully("submit solution successfully");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dialog.displayErrorMessage("Please fill in the solution link");
        }

    }

    private void displaysuccessfully(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updatestart(ObservableList<modelsolution> data) {
        tblexercise.setItems(data);

        colsubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colclass.setCellValueFactory(new PropertyValueFactory<>("Myclass"));
        colexercise.setCellValueFactory(new PropertyValueFactory<>("exercises"));
        colexercise.setCellFactory(column -> {
            TableCell<modelsolution, String> cell = new TableCell<modelsolution, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Hyperlink hyperlink = new Hyperlink(item);
                        hyperlink.setOnAction(event -> {
                            try {
                                Desktop.getDesktop().browse(new URI(item));
                            } catch (Exception ex) {
                                displayErrorMessage("URL is not found");

                            }
                        });
                        setGraphic(hyperlink);
                    }
                }
            };
            return cell;
        });
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colstatus.setCellFactory(column -> {
            return new TableCell<modelsolution, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null || !empty) {
                        if (item == 0) {
                            setText("marking");
                        } else if (item == 1) {
                            setText("obtain");
                            setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                        } else if (item == 2) {
                            setText("fail");
                            setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        }

                    }
                }
            };
        });
        colreason.setCellValueFactory(new PropertyValueFactory<>("reason"));
    }

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void displayrecord(int id_class, int id_subject) {
        conn = PrimaryController.connectDB();
        model.clear();
        String query = "Select t1.name as name_subject,t2.name as name_class,t3.link,t3.status,t3.reason From subject t1 "
                + "JOIN class_subject t4 ON t1.id=t4.id_subject "
                + "JOIN class t2 ON t2.id=t4.id_class "
                + "JOIN  assignments t5 ON t4.id_assignments=t5.id "
                + "JOIN  solution t3 ON t3.id=t4.id_solution "
                + "JOIN  student t6 ON t6.id=t4.id_student "
                + "Where t6.id =? And t4.id_assignments = ? And t4.id_class=? And t4.id_subject=?  Group by t1.name,t2.name,t3.link,t3.status,t3.reason";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);

            stmt.setInt(2, ExerciseviewController.assignmentId);

            stmt.setInt(3, id_class);
            stmt.setInt(4, id_subject);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int index = 1;
                String name_subject = rs.getString("name_subject");
                String name_class = rs.getString("name_class");
                String link = rs.getString("link");
                int status = rs.getInt("status");

                String reason = rs.getString("reason");
                model.add(new modelsolution(index, name_subject, name_class, link, status, reason));
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        updatestart(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisplayExercise();

    }

}
