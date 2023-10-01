/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.dialog.dialog;

import com.teach_fortune.student_management_student.util.DBconnect;
import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelcalender;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class CalenderviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int id_Assignment;
    @FXML
    private Label Subject = new Label();
    @FXML
    private Label Endcoutdown = new Label();
    @FXML
    private Hyperlink Examp_topic = new Hyperlink();
    @FXML
    private Label clock = new Label();
    private int minutesRemaining;
    private int seconds;

    public void settext(String hypelink) {
        Examp_topic.setText(hypelink);
    }

    public void setSubject(String subject) {
        Subject.setText(subject);
    }
    @FXML
    private MFXTextField linkExamp;
    private int id_Assignments;
    private boolean popupShow = false;
    long startsecondsRemaining;
    private long secondsRemaining;

    @FXML
    private TableColumn<modelcalender, String> Endtime = new TableColumn<>();

    @FXML
    private TableColumn<modelcalender, String> Time = new TableColumn<>();
    private boolean buttonclicked = false;
    private boolean zeropointCalled = false;
    @FXML
    private TableColumn<modelcalender, Boolean> colCountdown = new TableColumn<>();

    @FXML
    private TableColumn<modelcalender, String> colclass = new TableColumn<>();

    @FXML
    private TableColumn<modelcalender, String> colsubject = new TableColumn<>();

    @FXML
    private TableView<modelcalender> tblview = new TableView<>();
    private boolean linkStudentExist(int id_Examp){
        String query="Select a.id_exam From transcript a JOIN exam_schedule b ON a.id_exam=a.id JOIN class_subject c ON b.id=c.id_exam JOIN student d ON c.id_student=d.id Where a.id_exam=? and a.link IS NOT NULL And d.id=?";
        try {
            Connection conn=DBconnect.connectDB();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, id_Examp);
            stmt.setInt(2,PrimaryController.loggedInStudentId);
            ResultSet result=stmt.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    public void EndCountDownEverySecond(LocalDateTime endTime) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            secondsRemaining = java.time.Duration.between(now, endTime).getSeconds();

            if (secondsRemaining > 0) {

                long minutes = secondsRemaining / 60;
                long seconds = secondsRemaining % 60;
                String countdown = String.format("%02d:%02d", minutes, seconds);
                Endcoutdown.setText(countdown);

            } else {

                Endcoutdown.setText("00:00");

                Endcoutdown.setStyle("-fx-text-fill:red");
                closepopup();
            if(!popupShow){
                 zeropoint(getid_Assignment());
                 popupShow=true;
            }
               
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void selectexam() {
        List<com.team_fortune.student_management_student.models.modelcalender> resultList = daodb.SelectCalender();
        ObservableList<modelcalender> observableList = FXCollections.observableArrayList(resultList);
        tblview.setItems(observableList);
        colsubject.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));
        colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        Time.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        Endtime.setCellValueFactory(new PropertyValueFactory<>("Endtime"));
        Time.setCellFactory(column -> {
            return new TableCell<modelcalender, String>() {
                Label label = new Label();
                HBox hbox = new HBox();

                private void startCountdownEverySecond(LocalDateTime startTime) {
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                        LocalDateTime now = LocalDateTime.now();
                        startsecondsRemaining = java.time.Duration.between(now, startTime).getSeconds();

                        if (startsecondsRemaining < 900 && startsecondsRemaining > 0) {
                            long minutes = startsecondsRemaining / 60;
                            long seconds = startsecondsRemaining % 60;
                            String countdown = String.format("%02d:%02d", minutes, seconds);
                            setText(countdown);
                        } else if (startsecondsRemaining <= 0) {
                            setText("00:00");
                            setStyle("-fx-text-fill:red");

                        }
                    }));
                    timeline.setCycleCount(Animation.INDEFINITE);
                    timeline.play();
                }

                Label la = new Label();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("vi", "VN"));
                        LocalDateTime startTime = LocalDateTime.parse(item, formatter);

                        startCountdownEverySecond(startTime);
                    }
                }
            };
        });
        colCountdown.setCellValueFactory(new PropertyValueFactory<>("buttonVisible"));
        colCountdown.setCellFactory(column -> new TableCell<com.team_fortune.student_management_student.models.modelcalender, Boolean>() {
            private final MFXButton button = new MFXButton("Join");

            {
                button.setOnAction(event -> {

                    com.team_fortune.student_management_student.models.modelcalender cls = getTableView().getItems().get(getIndex());

                    if (startsecondsRemaining < 0) {
                        try {
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("laboratory_view.fxml"));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", new Locale("vi", "VN"));
                            LocalDateTime Endtime = LocalDateTime.parse(cls.getEndtime(), formatter);
                            LocalDateTime starttime = LocalDateTime.parse(cls.getStartTime(), formatter);
                            AnchorPane popup = loader.load();
                            CalenderviewController exercise = loader.getController();
                           exercise.linkStudentExist(cls.getId());
                            exercise.setSubject(cls.getName_Subject());
                            exercise.settext(cls.getLink());
                            exercise.ClockEnd(starttime, Endtime);
                            exercise.setId_Assignment(cls.getId());
                            exercise.EndCountDownEverySecond(Endtime);

                            Stage popupStage = new Stage();
                            popupStage.initModality(Modality.APPLICATION_MODAL);
                            popupStage.setScene(new Scene(popup, 600, 400));
                            popupStage.setResizable(false);
                            popupStage.initStyle(StageStyle.UNDECORATED);
                            popupStage.showAndWait();
                            popupShow = true;
                        } catch (IOException ex) {
                            Logger.getLogger(CalenderviewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        dialog.displayErrorMessage("it's not time");
                    }

                });
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("btn-design");
                if (Item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
        
    }
    private void zeropoint(int id_exam){
        String query="Insert into transcript(id_exam,link,score) values(?,?,?)";
        Connection conn=PrimaryController.connectDB();
        try {
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, id_exam);
            stmt.setString(2, "khong co bai");
            stmt.setInt(3,0);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  private void startUpdating() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Platform.runLater(this::selectexam);
        }, 0, 1, TimeUnit.SECONDS);
    }
    public void setId_Assignment(int id) {
        id_Assignment = id;
    }

    public int getid_Assignment() {
        return id_Assignment;
    }

    public void closepopup() {
        Stage stage = (Stage) Endcoutdown.getScene().getWindow();
        stage.close();
    }

    public void ClockEnd(LocalDateTime start, LocalDateTime Endtime) {
        LocalDateTime now = LocalDateTime.now();
        long secondsRemaining = java.time.Duration.between(start, Endtime).getSeconds();
        if (secondsRemaining > 0) {
            long minutes = secondsRemaining / 60;
            long seconds = secondsRemaining % 60;
            String format = String.format("%02d", minutes);
            clock.setText(format + " " + "Minutes");
        } else {
            clock.setText("");

        }

    }

    @FXML
    void submit(ActionEvent event) {
        String query = "Insert into transcript(id_exam,link,status) values(?,?,?)";
        Connection conn = DBconnect.connectDB();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, getid_Assignment());
            stmt.setString(2, linkExamp.getText());
            stmt.setInt(3, 0);
            stmt.executeUpdate();
            closepopup();
        } catch (SQLException ex) {
            Logger.getLogger(CalenderviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
startUpdating();
        selectexam();
        Examp_topic.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI(Examp_topic.getText()));
            } catch (Exception e) {
                dialog.displayErrorMessage("Error URL is not found");
            }
        });
    }

}
