package com.team_fortune.student_management_student;

import com.teach_fortune.student_management_student.util.DBconnect;
import com.team_fortune.student_management_student.dao.daodb;
import com.team_fortune.student_management_student.models.modelcalender;
import com.team_fortune.student_management_student.models.modelWord;
import com.team_fortune.student_management_student.models.modelExample;
import com.team_fortune.student_management_student.models.modelsubject;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.image.Image;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Locale;
import javafx.scene.control.TableCell;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.util.Duration;

public class SecondaryController implements Initializable {

    private Connection connection;

    @FXML
    private AnchorPane maindisplay;

    public String countDown;
    
    @FXML
    private Button btn_profile;

    public String name_class;

    @FXML
    private PieChart biechart;
    @FXML
    private BarChart<String, Number> Barchart;

    private String loggedInUsername;

    public static int assignmentId;

    @FXML
    private AnchorPane updateprofile;

    private Button btnWord;

    private static int id;
    private String link;
    String linkDisplay;

    private String coutdown;
    private String endfor;

    private void displayErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    private final ObservableList<com.team_fortune.student_management_student.models.modelsearch> models = FXCollections.observableArrayList();
    private final ObservableList<modelsubject> model = FXCollections.observableArrayList();
    private final ObservableList<modelcalender> data = FXCollections.observableArrayList();
    private final ObservableList<modelExample> Example = FXCollections.observableArrayList();
    private final ObservableList<modelWord> World = FXCollections.observableArrayList();

    public String getClassName(String username) {
        connection = PrimaryController.connectDB();
        String className = null;
        try {
            String query = "Select c.name as name_class From class c "
                    + "JOIN class_subject cs ON c.id=cs.id_class "
                    + "JOIN student s ON cs.id_student=s.id "
                    + "Where s.username=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet resultset = stmt.executeQuery();
            if (resultset.next()) {
                className = resultset.getString("name_class");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return className;
    }

    public void chart() {
        String chartQuery = "SELECT t1.name, t5.score FROM subject t1 "
                + "JOIN class_subject t2 ON t1.id = t2.id_subject "
                + "JOIN student t3 ON t2.id_student = t3.id "
                + "JOIN transcript t5 ON t2.id_transcipt = t5.id "
                + "WHERE t3.id = ? And Active=1";

        connection = PrimaryController.connectDB();
        try {
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAyis = new NumberAxis();
            Barchart.setTitle("total score");
            xAxis.setLabel("Name Subject");
            yAyis.setLabel("Score");
            XYChart.Series<String, Number> chartbar = new XYChart.Series<>();
            PreparedStatement stmt = connection.prepareStatement(chartQuery);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String nameSubject = result.getString("name");
                Float score = result.getFloat("score");
                chartbar.getData().add(new XYChart.Data<>(nameSubject, score));
            }
            Barchart.getData().add(chartbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Example() {
        try {
            String query = "Select id From student Where status=? And id=?";
            Connection conn = DBconnect.connectDB();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, false);
            stmt.setInt(2, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                FXMLLoader fxloader = new FXMLLoader(App.class.getResource("popupupdatepass.fxml"));
                AnchorPane newPopup;
                newPopup = fxloader.load();
                PopupupdatepassController exercise = fxloader.getController();
                Stage PopupStage = new Stage();
                PopupStage.initModality(Modality.APPLICATION_MODAL);
                PopupStage.setScene(new Scene(newPopup, 600, 400));
                PopupStage.setResizable(false);
                PopupStage.setOnCloseRequest(event->{
                    try {
                         String query1 = "Update student set status=1 Where id=?";
                        
                    PreparedStatement stmtquery = conn.prepareStatement(query1);
                    stmtquery.setInt(1, PrimaryController.loggedInStudentId);
                    
                    stmtquery.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
               
                PopupStage.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void piechart() {
        String chart = "SELECT COUNT(DISTINCT t1.id_subject) as total FROM class_subject t1 "
                + "JOIN student t2 ON t1.id_student = t2.id "
                + "JOIN class t3 ON t1.id_class=t3.id "
                + "WHERE t2.id = ? AND t3.name = ? And t1.Active=1";
        connection = PrimaryController.connectDB();
        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();
        try {
            PreparedStatement st = connection.prepareStatement(chart);
            st.setInt(1, PrimaryController.loggedInStudentId);
            st.setString(2, getClassName(PrimaryController.loggedInUsername));
            System.out.println(getClassName(PrimaryController.loggedInUsername));
            ResultSet resultset = st.executeQuery();
            while (resultset.next()) {

                int total = resultset.getInt("total");
             if(total>0){
                 PieChart.Data data = new PieChart.Data(getClassName(PrimaryController.loggedInUsername), total);
                piechartData.add(data);
             }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        biechart.setData(piechartData);
    }

    @FXML
    void logout(ActionEvent event) {

        try {
            if (PrimaryController.conn != null) {

                App.setRoot("primary");
                PrimaryController.conn.close();
            }

        } catch (Exception e) {
        }
    }

    @FXML
    void btn_home(MouseEvent event) throws IOException {
        App.setRoot("admin");

    }

   
    @FXML
    void Scorebtn() {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/Scoreview.fxml"));
        try {
            AnchorPane classPane = loader.load();

            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Worldbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/Wordview.fxml"));
        try {
            AnchorPane classPane = loader.load();

            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    void Exercisebtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/Exerciseview.fxml"));
        try {
            AnchorPane classPane = loader.load();

            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    void buttonSchedule(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/Calenderview.fxml"));
        try {
            AnchorPane classPane = loader.load();

            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    void Searchbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_student/SearchSubject.fxml"));
        try {
            AnchorPane classPane = loader.load();
            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    void SubmitCalender(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/Calenderview.fxml"));
        try {
            AnchorPane classPane = loader.load();

            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML

    void classbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class
                .getResource("/com/team_fortune/student_management_student/registered_class.fxml"));
        try {
            TabPane classPane = loader.load();
            classPane.getSelectionModel().select(0);
            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(classPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  @FXML
    void showInformationUser(ActionEvent event) {
FXMLLoader loader = new FXMLLoader(App.class.getResource("Information.fxml"));
       
        try {
            AnchorPane InformationPage = loader.load();
            maindisplay.getChildren().clear();
            maindisplay.getChildren().setAll(InformationPage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        chart();
        piechart();
        Example();
    }
}
