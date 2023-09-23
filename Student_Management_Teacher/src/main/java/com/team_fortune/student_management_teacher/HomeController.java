package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.util.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class HomeController implements Initializable {
    
    public static String username;
    
    @FXML
    private Pane main_display;
    @FXML
    private BarChart<String, Number> Char_Class;
    @FXML
    private MenuButton btnAssignment;

    @FXML
    private MenuButton btnClass;

    @FXML
    private MenuButton btnExam;

    @FXML
    private Button btnHome;

    @FXML
    private MenuButton btnSubject;

    public void chartClass() throws SQLException {
        String searchClassStudent = "select c.name, count(cs.id_student) as total from class_subject cs join class c on c.id=cs.id_class join teacher t on cs.id_teacher=t.id where t.username=? group by c.name order by c.id ASC";
        Connection conn = DBConnection.getConnection();
        try {
            Char_Class.setTitle("Student Of Class");
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            PreparedStatement ps = conn.prepareStatement(searchClassStudent);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nameClass = rs.getString("name");
                Integer Number = rs.getInt("total");
                series.getData().add(new XYChart.Data<>(nameClass, Number));
            }
            Char_Class.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
    }

    @FXML
    void btn_home(MouseEvent event) throws IOException {
        App.setRoot("main");
        btnClass.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().remove("bg-active");
        btnHome.getStyleClass().add("bg-active");
    }

    @FXML
    void add_class(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainClass.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().remove("bg-active");
        btnClass.getStyleClass().add("bg-active");
        try {
            TabPane classPane = loader.load();
            classPane.getSelectionModel().select(0);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(classPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void update_class(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainClass.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().remove("bg-active");
        btnClass.getStyleClass().add("bg-active");
        try {
            TabPane classPane = loader.load();
            classPane.getSelectionModel().select(1);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(classPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void delete_class(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainClass.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().remove("bg-active");
        btnClass.getStyleClass().add("bg-active");
        try {
            TabPane classPane = loader.load();
            classPane.getSelectionModel().select(3);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(classPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void list_class(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainClass.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().remove("bg-active");
        btnClass.getStyleClass().add("bg-active");
        try {
            TabPane classPane = loader.load();
            classPane.getSelectionModel().select(2);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(classPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void add_subject(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainSubject.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane subjectPane = loader.load();
            subjectPane.getSelectionModel().select(0);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(subjectPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void update_subject(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainSubject.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane subjectPane = loader.load();
            subjectPane.getSelectionModel().select(1);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(subjectPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void list_subject(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainSubject.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane subjectPane = loader.load();
            subjectPane.getSelectionModel().select(2);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(subjectPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void update_Assignment(ActionEvent event) {
FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
         btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel=loader.load();
            AssignmentPanel.getSelectionModel().select(1);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void ListAssignment(ActionEvent event){
        FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
         btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel=loader.load();
            AssignmentPanel.getSelectionModel().select(2);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void DeleteAssign(ActionEvent event){
        FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
         btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel=loader.load();
            AssignmentPanel.getSelectionModel().select(3);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@FXML
    void Add_assignment(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
         btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
        TabPane AssignmentPanel=loader.load();
        AssignmentPanel.getSelectionModel().select(0);
         main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
    } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void delete_subject(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/MainSubject.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane subjectPane = loader.load();
            subjectPane.getSelectionModel().select(3);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(subjectPane);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showInformationUser(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/Information.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        try {
            AnchorPane InformationPage = loader.load();
            main_display.getChildren().clear();
            main_display.getChildren().setAll(InformationPage);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Are You Logout?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.CANCEL) {
                alert.close();
            }
            if (response == ButtonType.OK) {
                try {
                    username = "";
                    App.setRoot("login");
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            btnHome.getStyleClass().add("bg-active");
            chartClass();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
