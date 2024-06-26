package com.team_fortune.student_management_admin;

import com.team_fortune.student_management_admin.dialog.DialogAlert;
import com.team_fortune.student_management_admin.model.Student;
import com.team_fortune.student_management_admin.model.Teacher;
import com.team_fortune.student_management_admin.util.DBConnection;
import com.team_fortune.student_management_admin.util.MD5;
import com.team_fortune.student_management_admin.util.Regax;
import com.team_fortune.student_management_admin.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecondaryController implements Initializable {

    @FXML
    private MFXDatePicker SinceStudent;

    @FXML
    private TextField searchStudent;
    @FXML
    private MFXDatePicker SinceTeacher;

    @FXML
    private MFXTextField emailStudent;

    @FXML
    private MFXTextField emailTeacher;

    @FXML
    private MFXTextField nameStudent;

    @FXML
    private MFXTextField nameTeacher;

    @FXML
    private MFXTextField phoneStudent;

    @FXML
    private MFXTextField phoneTeacher;

    @FXML
    private MFXTextField usernameStudent;

    @FXML
    private MFXTextField usernameTeacher;

    @FXML
    private TextField searchTeacher;

    @FXML
    private TableView<com.team_fortune.student_management_admin.model.Teacher> TableTeacher;

    @FXML
    private TableView<com.team_fortune.student_management_admin.model.Student> TableStudent;

    @FXML
    private TableColumn<?, ?> colStudent;

    @FXML
    private TableColumn<?, ?> colTeacher;

    @FXML
    private TableColumn<com.team_fortune.student_management_admin.model.Student, Boolean> colResetStudent;

    @FXML
    private TableColumn<com.team_fortune.student_management_admin.model.Teacher, Boolean> colResetTeacher;

    private ObservableList<Teacher> Teacher = FXCollections.observableArrayList();
    private ObservableList<Student> Student = FXCollections.observableArrayList();

    @FXML
    void addStudent(MouseEvent event) {
        if (!usernameStudent.getText().isEmpty() && !nameStudent.getText().isEmpty() && !emailStudent.getText().isEmpty() && !phoneStudent.getText().isEmpty() && !SinceStudent.getText().isEmpty()) {
            if (Regax.isValidUsername(usernameStudent.getText())) {
                String username = MD5.Md5(usernameStudent.getText());
                boolean isFound = false;
                try {
                    PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select*from student where email=?");
                    preparedStatement.setString(1, emailStudent.getText());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        isFound = true;
                    }
                    if (isFound == true) {
                        String insertQuery = "insert into student(name,username,phone,email,since,password,status) values(?,?,?,?,?,?,?)";
                        DateTimeFormatter local = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String since = SinceStudent.getValue().format(local);
                        Connection conn = DBConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(insertQuery);
                        ps.setString(1, nameStudent.getText());
                        ps.setString(2, username);
                        ps.setString(3, phoneStudent.getText());
                        ps.setString(4, emailStudent.getText());
                        ps.setString(5, since);
                        ps.setString(6, username);
                        ps.setInt(7, 0);

                        sendConfirmationEmailStudent(emailStudent.getText());
                        DialogAlert.DialogSuccess("Add Student Success!");

                        ps.executeUpdate();
                        usernameStudent.clear();
                        nameStudent.clear();
                        phoneStudent.clear();
                        SinceStudent.clear();
                        emailStudent.clear();

                        Student.clear();
                        Student.addAll(new getDatabaseToModel().getDataFromDatabaseStudent());
                        showStudent();
                    }else{
                        DialogAlert.DialogError("Email Is Exist");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    void addTeacher(MouseEvent event) {
        if (!usernameTeacher.getText().isEmpty() && !nameTeacher.getText().isEmpty() && !emailTeacher.getText().isEmpty() && !phoneTeacher.getText().isEmpty() && !SinceTeacher.getText().isEmpty()) {
            if (Regax.isValidUsername(usernameTeacher.getText())) {
                String username = MD5.Md5(usernameTeacher.getText());
                boolean isFound = false;
                try {
                    PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement("select*from teacher where email=?");
                    preparedStatement.setString(1, emailTeacher.getText());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        isFound = true;
                    }
                    if (isFound == true) {
                        String insertQuery = "insert into teacher(name,username,phone,email,since,password,status) values(?,?,?,?,?,?,?)";
                        DateTimeFormatter local = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String since = SinceTeacher.getValue().format(local);
                        Connection conn = DBConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement(insertQuery);
                        ps.setString(1, nameTeacher.getText());
                        ps.setString(2, username);
                        ps.setString(3, phoneTeacher.getText());
                        ps.setString(4, emailTeacher.getText());
                        ps.setString(5, since);
                        ps.setString(6, username);
                        ps.setInt(7, 0);

                        sendConfirmationEmailTeacher(emailTeacher.getText());
                        DialogAlert.DialogSuccess("Add Teacher Success!");

                        ps.executeUpdate();
                        usernameTeacher.clear();
                        nameTeacher.clear();
                        phoneTeacher.clear();
                        SinceTeacher.clear();
                        emailTeacher.clear();

                        Teacher.clear();
                        Teacher.addAll(new getDatabaseToModel().getDataFromDatabaseTeacher());
                        showTeacher();
                    } else {
                        DialogAlert.DialogError("Email Is Exist");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    void showStudent() {
        TableStudent.setItems(Student);
        colStudent.setCellValueFactory(new PropertyValueFactory<>("name"));
        colResetStudent.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colResetStudent.setCellFactory(column -> new TableCell<com.team_fortune.student_management_admin.model.Student, Boolean>() {
            private final Button button = new Button("Reset");

            {
                button.setOnAction(event -> {
                    Student students = getTableView().getItems().get(getIndex());
                    try {
                        String username_student = "";
                        Connection conn = DBConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement("selecct username from student where name=?");
                        ps.setString(1, students.getName());
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            username_student = rs.getString("username");
                        }
                        PreparedStatement preparedStatement = conn.prepareStatement("update student set password=? where name=?");
                        preparedStatement.setString(1, username_student);
                        preparedStatement.setString(2, students.getName());
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        rs.close();
                        ps.close();
                        DBConnection.closeConnection(conn);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-design");
                if (Item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });

    }

    void showTeacher() {
        TableTeacher.setItems(Teacher);
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("name"));
        colResetTeacher.setCellValueFactory(new PropertyValueFactory<>("Isactive"));
        colResetTeacher.setCellFactory(column -> new TableCell<com.team_fortune.student_management_admin.model.Teacher, Boolean>() {
            private final Button button = new Button("Reset");

            {
                button.setOnAction(event -> {
                    Teacher teachers = getTableView().getItems().get(getIndex());
                    try {
                        String username_teacher = "";
                        Connection conn = DBConnection.getConnection();
                        PreparedStatement ps = conn.prepareStatement("select username from teacher where name=?");
                        ps.setString(1, teachers.getName());
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            username_teacher = rs.getString("username");
                        }
                        PreparedStatement preparedStatement = conn.prepareStatement("update teacher set password=? where name=?");
                        preparedStatement.setString(1, username_teacher);
                        preparedStatement.setString(2, teachers.getName());
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        rs.close();
                        ps.close();
                        DBConnection.closeConnection(conn);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean Item, boolean empty) {
                super.updateItem(Item, empty);
                button.getStyleClass().add("button-design");
                if (Item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });
    }

    private void sendConfirmationEmailTeacher(String email) {

        String username = "votannamquoc@gmail.com";
        String password = "itzr uyfy qjua uxoa";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account Information");
            String body = "Hello,\n\nUsername:" + usernameTeacher.getText() + "\nPassword:" + usernameTeacher.getText();
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendConfirmationEmailStudent(String email) {

        String username = "votannamquoc@gmail.com";
        String password = "itzr uyfy qjua uxoa";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account Information");
            String body = "Hello,\n\nUsername:" + usernameStudent.getText() + "\nPassword:" + usernameStudent.getText();
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Teacher.addAll(new getDatabaseToModel().getDataFromDatabaseTeacher());
        Student.addAll(new getDatabaseToModel().getDataFromDatabaseStudent());
        showTeacher();
        showStudent();
        searchTeacher.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (searchTeacher.getText().isEmpty() || searchTeacher.getText().isBlank()) {
                Teacher.clear();
                Teacher.addAll(new getDatabaseToModel().getDataFromDatabaseTeacher());
                showTeacher();
            } else {
                Teacher.clear();
                Teacher.addAll(new getDatabaseToModel().getDataFromDatabaseTeacherWithKey(newvalue));
                showTeacher();
            }
        });
        searchStudent.textProperty().addListener((ObservableList, oldvalue, newvalue) -> {
            if (searchStudent.getText().isEmpty() || searchStudent.getText().isBlank()) {
                Student.clear();
                Student.addAll(new getDatabaseToModel().getDataFromDatabaseStudent());
                showStudent();
            } else {
                Student.clear();
                Student.addAll(new getDatabaseToModel().getDataFromDatabaseStudentWithKey(newvalue));
                showStudent();
            }
        });
    }
}
