/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class TranscriptController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ToggleGroup obtainToggleGroup = new ToggleGroup();
private ToggleGroup achievedToggleGroup = new ToggleGroup();
    private boolean isObtainSelected = false;
    private boolean isAchievesedSelected = false;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, String> colExamp;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Integer> colObtain;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Float> colScore;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, Integer> colachieved;
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Student> tblTranscript;
    @FXML
    private MFXComboBox<String> name_class;

    @FXML
    private MFXComboBox<String> name_subject;

    @FXML
    void Searchbtn(ActionEvent event) {
        String selectedClass = name_class.getValue();
        String selSubject = name_subject.getValue();
        List<com.team_fortune.student_management_teacher.model.Student> resulList = getDatabaseToModel.GetTranscriptByClass(selectedClass, selSubject);
        ObservableList<com.team_fortune.student_management_teacher.model.Student> observale = FXCollections.observableArrayList(resulList);
        tblTranscript.setItems(observale);
        colName.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        colExamp.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colObtain.setCellValueFactory(new PropertyValueFactory<>("Active"));
        colachieved.setCellValueFactory(new PropertyValueFactory<>("Active"));
    }

    private List<String> getClassSubject() {
        List<com.team_fortune.student_management_teacher.model.Subject> subject = new getDatabaseToModel().getDataFromDatabaseSubject();
        List<String> Subject = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Subject sj : subject) {
            Subject.add(sj.getName());
        }
        return Subject;
    }
public ToggleGroup getObtainToggleGroup() {
    return obtainToggleGroup;
}

public ToggleGroup getAchieveToggleGroup() {
    return achievedToggleGroup;
}
    private void displayrecord() {
        List<com.team_fortune.student_management_teacher.model.Student> resulList = getDatabaseToModel.GetTranscript();
        ObservableList<com.team_fortune.student_management_teacher.model.Student> observale = FXCollections.observableArrayList(resulList);
        tblTranscript.setItems(observale);
        colName.setCellValueFactory(new PropertyValueFactory<>("name_student"));
        colExamp.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        colExamp.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Student, String>() {
            private final Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String Item, boolean empty) {
                super.updateItem(Item, empty);
                if (Item != null || !empty) {
                    hyperlink.setText(Item);
                    setGraphic(hyperlink);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(Item));
                        } catch (Exception e) {
                            DialogAlert.DialogError("URL is not Found");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });
       
        colScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colScore.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Student, Float>() {
            @Override
            protected void updateItem(Float Item, boolean empty) {
                super.updateItem(Item, empty);
                if (Item != null || !empty) {
                    if (Item == 0) {
                        TextField textField = new TextField();
                        textField.getStyleClass().add("small-text-field");
                        textField.setPromptText("Nhap diem");
                        textField.setOnAction(event -> {
                            try {
                                Float newScore = Float.parseFloat(textField.getText());
                                com.team_fortune.student_management_teacher.model.Student student = getTableView().getItems().get(getIndex());
                                student.setScore(newScore);
                                setGraphic(createLabel(String.valueOf(newScore)));
                            } catch (NumberFormatException e) {
                                DialogAlert.DialogError("Please enter number");
                            }

                        });
                        setGraphic(textField);
                    } else {
                        setGraphic(createLabel(String.valueOf(Item)));
                    }
                } else {
                    setGraphic(null);
                }

            }

            private Label createLabel(String text) {
                Label label = new Label(text);
                label.setStyle("-fx-padding: 5px;");
                return label;
            }
        });

      
    }

    private List<String> getClassNames() {
        List<com.team_fortune.student_management_teacher.model.Class> classes = new getDatabaseToModel().getDataFromDatabaseClass();
        List<String> className = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            className.add(cls.getName());

        }
        return className;
    }

    @FXML
    void savebtn(ActionEvent event) {
        for (com.team_fortune.student_management_teacher.model.Student Student : tblTranscript.getItems()) {
            String query = "Update transcript set score=?,status=? Where id=?";
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setFloat(1, Student.getScore());
                stmt.setInt(2, Student.getActive());
                stmt.setInt(3, Student.getId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(TranscriptController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayrecord();
        name_class.getItems().addAll(getClassNames());
        name_subject.getItems().addAll(getClassSubject());
    }

}
