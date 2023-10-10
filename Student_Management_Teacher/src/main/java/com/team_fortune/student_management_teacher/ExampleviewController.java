package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class ExampleviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int id_ex;
        @FXML
    private MFXTextField txtdelete;
    String name_subject;
    String name_Class;
    private int id_exam;
    @FXML
    private TableColumn<?, ?> colDlEnd = new TableColumn<>();

    @FXML
    private Pagination pagination = new Pagination();

    @FXML
    private Pagination pagination1 = new Pagination();
    @FXML
    private Pagination pagination2 = new Pagination();
    @FXML
    private TableColumn<?, ?> colDtclass = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Exam, String> coldlExam = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> coldlStart = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> coldlsubject = new TableColumn<>();

    @FXML
    private DatePicker colEndUpdate = new DatePicker();

    @FXML
    private MFXTextField colLink = new MFXTextField();

    @FXML
    private MFXComboBox<String> colStartUpdate = new MFXComboBox<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Exam, Boolean> coldetail = new TableColumn<>();
    @FXML
    private TableColumn<?, ?> colistEnd = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Exam, String> collistExam = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> collistclass = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colliststart = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> collistsubject = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Exam> tbllist = new TableView<>();
    @FXML
    private MFXComboBox<String> colTimeEnd = new MFXComboBox<>();
    @FXML
    private DatePicker colUpdateStart = new DatePicker();
    @FXML
    private DatePicker End = new DatePicker();
    @FXML
    private TableColumn<?, ?> colEnd = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Exam, String> colExam = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Exam> tbldelete = new TableView<>();
    @FXML
    private TableColumn<?, ?> colSubject = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Exam, Boolean> colUpdate = new TableColumn<>();

    @FXML
    private MFXTextField txtsearch = new MFXTextField();
    @FXML
    private TableColumn<?, ?> colclass = new TableColumn<>();
    @FXML
    private Label total;
    private int itemsperPage = 5;
    private int totalItems;
    private int currentPageIndex = 0;
    @FXML
    private TableColumn<?, ?> colstart = new TableColumn<>();
    @FXML
    private MFXTextField Link_Examp = new MFXTextField();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Exam> tblExam = new TableView<>();

    @FXML
    private DatePicker Start = new DatePicker();
    @FXML
    private MFXComboBox<String> TimeStartpicker = new MFXComboBox<>();
    @FXML
    private MFXComboBox<String> TimeEndpicker = new MFXComboBox<>();
    @FXML
    private MFXComboBox<String> name_class = new MFXComboBox<>();
    @FXML
    private TableColumn<?, ?> colstudent;

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Exam> tblStudent;

    @FXML
    private MFXComboBox<String> Subject_name = new MFXComboBox<>();

    private void setidExam(int id_exam) {
        this.id_exam = id_exam;
    }

    private int getidexam() {
        return id_exam;
    }
    private void deletesearch(String text){
          List<com.team_fortune.student_management_teacher.model.Exam> Array = getDatabaseToModel.GetExamwithkey(text);
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Array);
          totalItems = Obserable.size();
        int PageCout = (totalItems / itemsperPage) + 1;
        pagination1.setPageCount(PageCout);
        if (currentPageIndex >= PageCout) {
            currentPageIndex = PageCout - 1;
        }
        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Exam> Exam = Obserable.subList(startIndex, endIndex);
        tbldelete.setItems(FXCollections.observableArrayList(Exam));
        colDtclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        coldlsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        coldlStart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colDlEnd.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        coldlExam.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        coldlExam.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, String>() {
            private final Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(item));
                        } catch (Exception e) {
                            DialogAlert.DialogError("URL is not Found");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });
        tbldelete.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                com.team_fortune.student_management_teacher.model.Exam ex = tbldelete.getSelectionModel().getSelectedItem();
                name_subject = ex.getName_subject();
                name_Class = ex.getName_class();
                id_ex = ex.getId();
            }
        });
    }
    private void deleteview() {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = getDatabaseToModel.GetExam();
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Array);
        totalItems = Obserable.size();
        int PageCout = (totalItems / itemsperPage) + 1;
        pagination1.setPageCount(PageCout);
        if (currentPageIndex >= PageCout) {
            currentPageIndex = PageCout - 1;
        }
        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Exam> Exam = Obserable.subList(startIndex, endIndex);
        tbldelete.setItems(FXCollections.observableArrayList(Exam));
        colDtclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        coldlsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        coldlStart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colDlEnd.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        coldlExam.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        coldlExam.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, String>() {
            private final Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(item));
                        } catch (Exception e) {
                            DialogAlert.DialogError("URL is not Found");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });
        tbldelete.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                com.team_fortune.student_management_teacher.model.Exam ex = tbldelete.getSelectionModel().getSelectedItem();
                name_subject = ex.getName_subject();
                name_Class = ex.getName_class();
                id_ex = ex.getId();
            }
        });
    }

    private void detail_student(String name_class,String name_subject,int id_exam) {
        List<com.team_fortune.student_management_teacher.model.Exam> Ex = getDatabaseToModel.DetailStudent(name_class,name_subject,id_exam);
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Ex);
        tblStudent.setItems(Obserable);
        colstudent.setCellValueFactory(new PropertyValueFactory<>("name_Student"));
    }

    private void listExam() {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = getDatabaseToModel.GetAllExam();
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Array);
        totalItems = Obserable.size();
        int PageCout = (totalItems / itemsperPage) + 1;
        pagination.setPageCount(PageCout);
        if (currentPageIndex >= PageCout) {
            currentPageIndex = PageCout - 1;
        }
        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Exam> Exam = Obserable.subList(startIndex, endIndex);
        tbllist.setItems(FXCollections.observableArrayList(Exam));
        collistclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        collistsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colliststart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colistEnd.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        collistExam.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        collistExam.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, String>() {
            private final Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(item));
                        } catch (Exception e) {
                            DialogAlert.DialogError("URL is not Found");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });
        coldetail.setCellValueFactory(new PropertyValueFactory<>("IsActive"));

        coldetail.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, Boolean>() {
            private final MFXButton button = new MFXButton("Detail");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Exam cls = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/DetailExam.fxml"));
                        AnchorPane newpopup = loader.load();
                        ExampleviewController Exam = loader.getController();
                        String name_Class = cls.getName_class();
                        String name_subject = cls.getName_subject();
                        String total_student = Exam.totalstudent(cls.getId(), name_Class, name_subject);
                        Exam.total.setText(total_student);
                    
                        System.out.println(Exam.ExistStudent(cls.getId(), name_Class, name_subject));
                        Exam.detail_student(cls.getName_class(),cls.getName_subject(),cls.getId());
                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newpopup));
                        popupStage.setResizable(false);
                        popupStage.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(ExampleviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item != null || !empty) {
                    setGraphic(button);
                } else {
                    setGraphic(null);
                }
            }
        });
    }
    private boolean ExistStudent(int id_exam,String name_class,String name_subject){
        int id_class = -1;
        int id_subject = -1;
        int id_teacher=-1;
        String query_class = "Select id From class Where name=?";
        String query_subject = "Select id From subject where name=?";
        String query_teacher="Select id From teacher where username=?";
        String Exsistteacher="Select id_student from class_subject where id_exam=? And id_class=? And id_subject=? And id_teacher=? And id_Student IS NOT NULL";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmtclas = conn.prepareStatement(query_class);
            stmtclas.setString(1, name_class);
            ResultSet rs = stmtclas.executeQuery();
            if (rs.next()) {
                id_class = rs.getInt("id");
            }
            PreparedStatement stmtsubject = conn.prepareStatement(query_subject);
            stmtsubject.setString(1, name_subject);
            ResultSet rssubject = stmtsubject.executeQuery();
            if (rssubject.next()) {
                id_subject = rssubject.getInt("id");
            }
            PreparedStatement stmtteacher=conn.prepareStatement(query_teacher);
            stmtteacher.setString(1, MD5.Md5(HomeController.username));
            ResultSet resultteacher=stmtteacher.executeQuery();
            if(resultteacher.next()){
                id_teacher=resultteacher.getInt("id");
            }
            PreparedStatement isexit=conn.prepareStatement(Exsistteacher);
            isexit.setInt(1, id_exam);
            isexit.setInt(2, id_class);
            isexit.setInt(3, id_subject);
            isexit.setInt(4, id_teacher);
            ResultSet rsexist=isexit.executeQuery();
            if(rsexist.next()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private String totalstudent(int id_exam, String name_class, String name_subject) {
        int total = -1;
        int id_class = -1;
        int id_subject = -1;
        int id_teacher=-1;
        String query_class = "Select id From class Where name=?";
        String query_subject = "Select id From subject where name=?";
        String query_teacher="Select id From teacher where username=?";
        String query = "Select count(id_student) as total From class_subject where id_exam=? And id_subject=? And id_class=? And id_teacher=?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmtclas = conn.prepareStatement(query_class);
            stmtclas.setString(1, name_class);
            ResultSet rs = stmtclas.executeQuery();
            if (rs.next()) {
                id_class = rs.getInt("id");
            }
            PreparedStatement stmtsubject = conn.prepareStatement(query_subject);
            stmtsubject.setString(1, name_subject);
            ResultSet rssubject = stmtsubject.executeQuery();
            if (rssubject.next()) {
                id_subject = rssubject.getInt("id");
            }
            PreparedStatement stmtteacher=conn.prepareStatement(query_teacher);
            stmtteacher.setString(1, MD5.Md5(HomeController.username));
            ResultSet resultteacher=stmtteacher.executeQuery();
            if(resultteacher.next()){
                id_teacher=resultteacher.getInt("id");
            }
            PreparedStatement stmtex = conn.prepareStatement(query);
            stmtex.setInt(1, id_exam);
            stmtex.setInt(2, id_subject);
            stmtex.setInt(3, id_class);
            stmtex.setInt(4, id_teacher);
            ResultSet result = stmtex.executeQuery();
            while (result.next()) {
                total = result.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExampleviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(total);
    }

    @FXML
    void Add_Examp(ActionEvent event) {
        int id_Exam = -1;
        int id_class = -1;
        int id_subject = -1;
        if (name_class.getSelectionModel().getSelectedItem() != null && Subject_name.getSelectionModel().getSelectedItem() != null && !Link_Examp.getText().isEmpty() && TimeStartpicker.getSelectionModel().getSelectedItem() != null && TimeEndpicker.getSelectionModel().getSelectedItem() != null && Start.getValue() != null && End.getValue() != null) {
            String query = "Insert into exam_schedule(start,end,link_exam) values(?,?,?)";
            String queryid = "Select id From class where name=?";
            String querysubject = "Select id From subject Where name=?";
            String checkquery = "Select id_class,id_subject from class_subject where id_class=? And id_subject=? And id_exam IS Null And id_transcipt IS NULL";
            String latestquery = "Select id From exam_schedule ORDER BY id DESC LIMIT 1";
            String updateExam = "Update class_subject set id_exam=? Where id_class=? And id_subject=?";
            try {
                Connection conn = DBConnection.getConnection();
                PreparedStatement querystmt = conn.prepareStatement(queryid);
                querystmt.setString(1, name_class.getValue());

                ResultSet rs = querystmt.executeQuery();
                if (rs.next()) {

                    id_class = rs.getInt("id");

                }
                PreparedStatement stmtsubject = conn.prepareStatement(querysubject);
                stmtsubject.setString(1, Subject_name.getValue());
                ResultSet rssubject = stmtsubject.executeQuery();
                if (rssubject.next()) {
                    id_subject = rssubject.getInt("id");
                }
                PreparedStatement checkstmt = conn.prepareStatement(checkquery);
                checkstmt.setInt(1, id_class);
                checkstmt.setInt(2, id_subject);
                ResultSet rscheck = checkstmt.executeQuery();
                if (rscheck.next()) {
                    PreparedStatement stmt = conn.prepareStatement(query);
                    LocalDate startDate = Start.getValue();
                    String startime = TimeStartpicker.getValue();
                    LocalDate endDate = End.getValue();
                    String endtime = TimeEndpicker.getValue();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime startDatetime = LocalDateTime.parse(startDate.toString() + " " + startime, formatter);
                    LocalDateTime EndDatetime = LocalDateTime.parse(endDate.toString() + " " + endtime, formatter);
                    if (startDatetime.isBefore(EndDatetime)) {
                        stmt.setString(1, startDatetime.toString());
                        stmt.setString(2, EndDatetime.toString());
                        stmt.setString(3, Link_Examp.getText());
                        stmt.executeUpdate();
                        PreparedStatement latestmt = conn.prepareStatement(latestquery);
                        ResultSet laters = latestmt.executeQuery();
                        if (laters.next()) {
                            id_Exam = laters.getInt("id");
                        }
                        PreparedStatement Updatestmt = conn.prepareStatement(updateExam);
                        Updatestmt.setInt(1, id_Exam);
                        Updatestmt.setInt(2, id_class);
                        Updatestmt.setInt(3, id_subject);
                        Updatestmt.executeUpdate();
                        DialogAlert.DialogSuccess("Add Exam Successfully");
                        Start.setValue(null);
                        End.setValue(null);
                        TimeStartpicker.getSelectionModel().clearSelection();
                        TimeEndpicker.getSelectionModel().clearSelection();
                        name_class.getSelectionModel().clearSelection();
                        Subject_name.getSelectionModel().clearSelection();
                        Link_Examp.setText("");

                    } else {
                        DialogAlert.DialogError("The start date must be less than the end date");
                    }
                } else {
                    DialogAlert.DialogError("Error Add Exam");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            DialogAlert.DialogError("Please enter complete information");
        }

    }

    private void diplayExam() {
        List<com.team_fortune.student_management_teacher.model.Exam> Ex = getDatabaseToModel.GetExam();
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Ex);
        totalItems = Obserable.size();
        int pageCout = (totalItems / itemsperPage) + 1;
        pagination2.setPageCount(pageCout);
        if (currentPageIndex >= pageCout) {
            currentPageIndex = pageCout - 1;
        }
        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Exam> Exam = Obserable.subList(startIndex, endIndex);
        tblExam.setItems(FXCollections.observableArrayList(Exam));
        colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colstart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        colExam.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        colExam.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, String>() {
            private Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(item));
                        } catch (Exception e) {
                            DialogAlert.DialogError("URL is not Found");
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }

        });
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("IsActive"));
        colUpdate.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, Boolean>() {
            private final MFXButton button = new MFXButton("Update");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Exam cls = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateExam.fxml"));
                        AnchorPane newpopup = loader.load();
                        ExampleviewController Exam = loader.getController();
                        Exam.setidExam(cls.getId());

                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newpopup));
                        popupStage.setResizable(false);
                        popupStage.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(ExampleviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item != null || !empty) {
                    setGraphic(button);
                } else {
                    setGraphic(null);
                }
            }

        });
    }

    @FXML
    void update(ActionEvent event) {
        if (!colLink.getText().isEmpty() && colStartUpdate.getSelectionModel().getSelectedItem() != null && colTimeEnd.getSelectionModel().getSelectedItem() != null && colUpdateStart.getValue() != null && colEndUpdate.getValue() != null) {
            try {
                String query = "Update exam_schedule set start=?,end=?,link_exam=? Where id=?";
                Connection conn = DBConnection.getConnection();
                LocalDate startDate = colUpdateStart.getValue();
                String startime = colStartUpdate.getValue();
                LocalDate endDate = colEndUpdate.getValue();
                String endtime = colTimeEnd.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startDatetime = LocalDateTime.parse(startDate.toString() + " " + startime, formatter);
                LocalDateTime EndDatetime = LocalDateTime.parse(endDate.toString() + " " + endtime, formatter);
                if (startDatetime.isBefore(EndDatetime)) {
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, startDatetime.toString());
                    stmt.setString(2, EndDatetime.toString());
                    stmt.setString(3, colLink.getText());
                    stmt.setInt(4, getidexam());
                    stmt.executeUpdate();
                    DialogAlert.DialogSuccess("Update Exam Successfully");
                    colUpdateStart.setValue(null);
                    colStartUpdate.getSelectionModel().clearSelection();
                    colEndUpdate.setValue(null);
                    colTimeEnd.getSelectionModel().clearSelection();
                    colLink.setText("");

                } else {
                    DialogAlert.DialogError("The start date must be less than the end date");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            DialogAlert.DialogError("Please enter complete information");
        }

    }

    private void startUpdatime() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                
                diplayExam();
                  
                deleteview();
               if(!txtdelete.getText().isEmpty()){
                   deletesearch(txtdelete.getText());
               }
                
                seardisplay(txtsearch.getText());
                
               
            });
        }, 0, 1, TimeUnit.SECONDS);

    }

    private List<String> getnameClass() {
        List<com.team_fortune.student_management_teacher.model.Class> ClassName = new getDatabaseToModel().getDataFromDatabaseClass();
        List<String> Class = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class Clas : ClassName) {
            Class.add(Clas.getName());
        }
        return Class;
    }

    private List<String> getClassSubject() {
        List<com.team_fortune.student_management_teacher.model.Subject> Subject = new getDatabaseToModel().getDataFromDatabaseSubject();
        List<String> Subjectname = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Subject sj : Subject) {
            Subjectname.add(sj.getName());
        }
        return Subjectname;
    }

    private void seardisplay(String key) {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = getDatabaseToModel.GetExamwithkey(key);
        ObservableList<com.team_fortune.student_management_teacher.model.Exam> Obserable = FXCollections.observableArrayList(Array);
        totalItems = Obserable.size();
        int pageCout = (totalItems / itemsperPage) + 1;
        pagination2.setPageCount(pageCout);
        if (currentPageIndex >= pageCout) {
            currentPageIndex = pageCout - 1;
        }
        int startIndex = currentPageIndex * itemsperPage;
        int endIndex = Math.min(startIndex + itemsperPage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Exam> Exam = Obserable.subList(startIndex, endIndex);
        tblExam.setItems(FXCollections.observableArrayList(Exam));
        colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
        colstart.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        colExam.setCellValueFactory(new PropertyValueFactory<>("link_Examp"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("IsActive"));
        colUpdate.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Exam, Boolean>() {
            private final MFXButton button = new MFXButton("Update");

            {
                button.setOnAction(event -> {
                    com.team_fortune.student_management_teacher.model.Exam cls = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateExam.fxml"));
                        AnchorPane newpopup = loader.load();
                        ExampleviewController Exam = loader.getController();
                        Exam.setidExam(cls.getId());

                        Stage popupStage = new Stage();
                        popupStage.initModality(Modality.APPLICATION_MODAL);
                        popupStage.setScene(new Scene(newpopup));
                        popupStage.setResizable(false);
                        popupStage.showAndWait();
                    } catch (IOException ex) {
                        Logger.getLogger(ExampleviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item != null || !empty) {
                    setGraphic(button);
                } else {
                    setGraphic(null);
                }
            }

        });
        if (txtsearch.getText().isEmpty()) {
            diplayExam();
        }
    }

    @FXML
    void deleteExam(ActionEvent event) {
        try {

            int id_class = -1;
            int id_subject = -1;

            String query = "Select id from class where name=?";
            String query_subject = "Select id from subject where name=?";
            String updatequery = "Update class_subject set id_exam=Null where id_class=? And id_subject=?";
            String deletequery = "Delete from exam_schedule where id=?";

            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name_Class);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id_class = rs.getInt("id");
            }
            PreparedStatement stmtop = conn.prepareStatement(query_subject);
            stmtop.setString(1, name_subject);
            ResultSet rssubject = stmtop.executeQuery();
            if (rssubject.next()) {
                id_subject = rssubject.getInt("id");
            }

            PreparedStatement stmtupdate = conn.prepareStatement(updatequery);
            stmtupdate.setInt(1, id_class);
            stmtupdate.setInt(2, id_subject);
            stmtupdate.executeUpdate();
            PreparedStatement insertupdate = conn.prepareStatement(deletequery);

            insertupdate.setInt(1, id_ex);
            insertupdate.executeUpdate();
            DialogAlert.DialogSuccess("Delete successfully");
            deleteview();
        } catch (Exception ex) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           deleteview();
         listExam();
        pagination.currentPageIndexProperty().addListener((obs, oldindex, newindx) -> {
            
            currentPageIndex = newindx.intValue();
             listExam();
        });
        pagination1.currentPageIndexProperty().addListener((obs, oldindex, newindex) -> {
            currentPageIndex = newindex.intValue();
            deleteview();
        });
        pagination2.currentPageIndexProperty().addListener((obs, oldindex, newindex) -> {
            currentPageIndex = newindex.intValue();
            diplayExam();
            seardisplay(txtsearch.getText());
        });
       
        ObservableList<String> timeOption = FXCollections.observableArrayList();
        for (int hour = 0; hour < 24; hour++) {
            for (int minutes = 0; minutes < 60; minutes++) {
                String time = String.format("%02d:%02d", hour, minutes);
                timeOption.add(time);
            }
        }
        startUpdatime();
        txtdelete.textProperty().addListener((observableList, oldvalue, newValue)->{
            deletesearch(newValue);
         
        });
        txtsearch.textProperty().addListener((observableList, oldvalue, newValue) -> {
            seardisplay(newValue);
        });
        name_class.getItems().addAll(getnameClass());
        Subject_name.getItems().addAll(getClassSubject());
        TimeStartpicker.setItems(timeOption);
        TimeEndpicker.setItems(timeOption);
        colTimeEnd.setItems(timeOption);
        colStartUpdate.setItems(timeOption);
   
    }

}
