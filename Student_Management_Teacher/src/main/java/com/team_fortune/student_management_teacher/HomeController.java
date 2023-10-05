package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dao.SearchTeacher;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            ps.setString(1, MD5.Md5(username));
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }

    @FXML
    void update_Assignment(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel = loader.load();
            AssignmentPanel.getSelectionModel().select(1);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ListAssignment(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel = loader.load();
            AssignmentPanel.getSelectionModel().select(2);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteAssign(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel = loader.load();
            AssignmentPanel.getSelectionModel().select(3);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Add_Examp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Exampleview.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel = loader.load();
            AssignmentPanel.getSelectionModel().select(0);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void Add_assignment(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/Assignment.fxml"));
        btnHome.getStyleClass().remove("bg-active");
        btnExam.getStyleClass().remove("bg-active");
        btnAssignment.getStyleClass().remove("bg-active");
        btnSubject.getStyleClass().add("bg-active");
        btnClass.getStyleClass().remove("bg-active");
        try {
            TabPane AssignmentPanel = loader.load();
            AssignmentPanel.getSelectionModel().select(0);
            main_display.getChildren().clear();
            main_display.getChildren().setAll(AssignmentPanel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void btnTranscript(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/Transcript.fxml"));
        try {
            AnchorPane InformationPage = loader.load();
            main_display.getChildren().clear();
            main_display.getChildren().setAll(InformationPage);
        } catch (Exception e) {
            e.printStackTrace();
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
            ex.printStackTrace();
        }
    }

    @FXML
    void Solutionbtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/watchassign.fxml"));
        try {
            AnchorPane InformationPage = loader.load();
            main_display.getChildren().clear();
            main_display.getChildren().setAll(InformationPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Request_class(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/Request_class.fxml"));
        try {
            AnchorPane Information = loader.load();
            main_display.getChildren().clear();
            main_display.getChildren().setAll(Information);
        } catch (Exception e) {
            e.printStackTrace();
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
            ex.printStackTrace();
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
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void openPopupChangePassword() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/changePassword.fxml"));
            AnchorPane newPopup = fxmlLoader.load();
            popUpclass change_password = fxmlLoader.getController();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newPopup, 400, 300));
            popupStage.setOnCloseRequest(event -> {
                try {
                    String query = "Update teacher set status=1 Where username=?";
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, MD5.Md5(HomeController.username));
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            popupStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void closePopupChangePassword() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("changePassword.fxml"));
            AnchorPane newPopup = fxmlLoader.load();
            popUpclass change_password = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newPopup, 400, 300));
            popupStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
