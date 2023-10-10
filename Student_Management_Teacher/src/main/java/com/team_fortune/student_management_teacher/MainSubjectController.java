package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.model.Subject;
import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSubjectController implements Initializable {

    @FXML
    private Pagination pagination = new Pagination();
    @FXML
    private Pagination pagination1 = new Pagination();
    @FXML
    private Pagination pagination2 = new Pagination();
    private final int ItemPerpage = 5;
    private int totalItems;
    private int currentPageIndex = 0;
    private int id_subject;
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, Boolean> colupdate = new TableColumn<>();
    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> tblDelete = new TableView<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> colDeleteSession = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> colNamedelete = new TableColumn<>();
    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> coldlLession = new TableColumn<>();
    @FXML
    private MFXComboBox<String> name_class = new MFXComboBox<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableSubject = new TableView<>();
    private int updateid_subject;
    @FXML
    private Tab addSubject;

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> colLessionSubject = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colNameSubject = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colSessionSubject = new TableColumn<>();
    @FXML
    private TableColumn<?, ?> collistclass = new TableColumn<>();
    @FXML
    private Tab deleteSubject;
    @FXML
    private MFXTextField searchdelete=new MFXTextField();
    @FXML
    private MFXTextField key_search = new MFXTextField();

    @FXML
    private MFXTextField lession_link = new MFXTextField();

    @FXML
    private Tab listSubject;

    @FXML
    private TabPane mainSubject;

    @FXML
    private MFXTextField name_subject = new MFXTextField();

    @FXML
    private MFXTextField session = new MFXTextField();

    @FXML
    private Tab updateSubject;

    @FXML
    private MFXTextField lessionSubject = new MFXTextField();

    @FXML
    private MFXTextField nameSubject = new MFXTextField();

    @FXML
    private MFXTextField sessionSubject = new MFXTextField();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, Boolean> colClass = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, String> colLession_Subject = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colName_Subject = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> colSession_Subject = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Subject, Boolean> colStudent = new TableColumn<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Class, String> col_class = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Class> list_class = new TableView<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Subject> TableListSubject = new TableView<>();

    @FXML
    private TableColumn<com.team_fortune.student_management_teacher.model.Student, ?> col_student = new TableColumn<>();

    @FXML
    private TableView<com.team_fortune.student_management_teacher.model.Student> list_student = new TableView<>();

    private ObservableList<com.team_fortune.student_management_teacher.model.Class> Class = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Subject> Subject = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Subject> model = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Class_Subject> Class_Subject = FXCollections.observableArrayList();
    private ObservableList<com.team_fortune.student_management_teacher.model.Student> Student = FXCollections.observableArrayList();

    private void deletesearchkey(String key) {
        List<com.team_fortune.student_management_teacher.model.Subject> listsubject = getDatabaseToModel.getDataFromDatabaseSubjectWithKey(key);
        ObservableList<com.team_fortune.student_management_teacher.model.Subject> obserable = FXCollections.observableArrayList(listsubject);
        totalItems = obserable.size();
        int Pagecout = (totalItems / ItemPerpage) + 1;
        pagination.setPageCount(Pagecout);
        if (currentPageIndex >= Pagecout) {
            currentPageIndex = Pagecout - 1;
        }
        int startIndex = currentPageIndex * ItemPerpage;
        int endIndex = Math.min(startIndex + ItemPerpage, totalItems);

        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Subject> sublist = obserable.subList(startIndex, endIndex);
        tblDelete.setItems(FXCollections.observableArrayList(sublist));
        colNamedelete.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDeleteSession.setCellValueFactory(new PropertyValueFactory<>("session"));
        coldlLession.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        coldlLession.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, String>() {
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
        
    }

    private void deletedisplay() {
        List<com.team_fortune.student_management_teacher.model.Subject> result = getDatabaseToModel.getFromsubject();
        ObservableList<com.team_fortune.student_management_teacher.model.Subject> obserable = FXCollections.observableArrayList(result);

        totalItems = obserable.size();
        int Pagecout = (totalItems / ItemPerpage) + 1;
        pagination.setPageCount(Pagecout);
        if (currentPageIndex >= Pagecout) {
            currentPageIndex = Pagecout - 1;
        }
        int startIndex = currentPageIndex * ItemPerpage;
        int endIndex = Math.min(startIndex + ItemPerpage, totalItems);

        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Subject> sublist = obserable.subList(startIndex, endIndex);
        tblDelete.setItems(FXCollections.observableArrayList(sublist));
        colNamedelete.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDeleteSession.setCellValueFactory(new PropertyValueFactory<>("session"));
        coldlLession.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        coldlLession.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, String>() {
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
    }

    private List<String> getClassNames() {
        List<com.team_fortune.student_management_teacher.model.Class> classes = new getDatabaseToModel().getDataFromDatabaseClass();
        List<String> classNames = new ArrayList<>();
        for (com.team_fortune.student_management_teacher.model.Class cls : classes) {
            classNames.add(cls.getName());
        }
        return classNames;
    }

    private void liststudent(String name_Subject) {
        List<com.team_fortune.student_management_teacher.model.Student> Arraylist = getDatabaseToModel.getDataFromDatabaseStudentWithSubject(name_Subject);
        ObservableList<com.team_fortune.student_management_teacher.model.Student> obserable = FXCollections.observableArrayList(Arraylist);
        list_student.setItems(obserable);
        col_student.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void listClass(String name_subject) {
        List<com.team_fortune.student_management_teacher.model.Class> ArrayList = getDatabaseToModel.getDataFromDatabaseClassWithSubject(name_subject);
        ObservableList<com.team_fortune.student_management_teacher.model.Class> obserable = FXCollections.observableArrayList(ArrayList);
        list_class.setItems(obserable);
        col_class.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    @FXML
    void AddSubject(ActionEvent event) {
        try {
            boolean isFound = false;
            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet resultSet = s.executeQuery("select*from subject");
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(name_subject.getText())) {
                    isFound = true;
                }
            }
            if (isFound == false) {
                if (name_subject.getText().isEmpty()) {
                    name_subject.getStyleClass().add("text_field_error");
                    name_subject.applyCss();
                }
                if (session.getText().isEmpty()) {
                    session.getStyleClass().add("text_field_error");
                    session.applyCss();
                }
                if (name_class.getSelectedItem() == null) {
                    name_class.getStyleClass().add("text_field_error");
                    name_class.applyCss();
                }if (!name_subject.getText().isEmpty() && !session.getText().isEmpty() && name_class.getSelectedItem() != null) {

                    String insertQuery1 = "insert into subject(name,session,lession_link) values(?,?,?)";
                    String insertQuery2 = "insert into class_subject (id_class,id_subject,id_teacher,Active) values(?,?,?,?)";
                    String updateQuery = "update class_subject set id_subject=? where id_class=?";
                    String searchQuery1 = "select id from class where name=?";
                    String searchQuery2 = "select id from subject where name=?";
                    try {
                        PreparedStatement ps1 = conn.prepareStatement(insertQuery1);
                        ps1.setString(1, name_subject.getText());
                        ps1.setInt(2, Integer.parseInt(session.getText()));
                        ps1.setString(3, lession_link.getText());
                        ps1.executeUpdate();
                        PreparedStatement ps2 = conn.prepareStatement(searchQuery1);
                        ps2.setString(1, name_class.getSelectedItem());
                        ResultSet rs1 = ps2.executeQuery();
                        while (rs1.next()) {
                            getDatabaseToModel.id_class = rs1.getInt("id");
                        }
                        PreparedStatement ps3 = conn.prepareStatement(searchQuery2);
                        ps3.setString(1, name_subject.getText());
                        ResultSet rs2 = ps3.executeQuery();
                        while (rs2.next()) {
                            getDatabaseToModel.id_subject = rs2.getInt("id");
                        }
                        PreparedStatement ps = conn.prepareStatement("select id_subject from class_subject where id_class=? and id_teacher=? and id_subject=?");
                        ps.setInt(1, getDatabaseToModel.id_class);
                        ps.setInt(2, getDatabaseToModel.id_teacher);
                        ps.setNull(3, Types.INTEGER);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            if (rs.getString("id_subject") != null) {
                                isFound = true;
                            }
                        }
                        if (isFound == false) {
                            PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);
                            preparedStatement.setInt(1, getDatabaseToModel.id_subject);
                            preparedStatement.setInt(2, getDatabaseToModel.id_class);
                            preparedStatement.executeUpdate();
                        } else {
                            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery2);
                            preparedStatement.setInt(1, getDatabaseToModel.id_class);
                            preparedStatement.setInt(2, getDatabaseToModel.id_subject);
                            preparedStatement.setInt(3, getDatabaseToModel.id_teacher);
                            preparedStatement.setInt(4, 0);
                            preparedStatement.executeUpdate();
                        }
                        DialogAlert.DialogSuccess("Add Subject Success");
                        name_subject.setText("");
                        session.setText("");
                        lession_link.setText("");
                        name_class.getSelectionModel().clearSelection();
                    } catch (SQLException ex) {
                        Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                DialogAlert.DialogError("Subject Exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void showSuject() {
        List<com.team_fortune.student_management_teacher.model.Subject> array = getDatabaseToModel.getDataFromDatabaseSubject();
        ObservableList<com.team_fortune.student_management_teacher.model.Subject> obserable = FXCollections.observableArrayList(array);

        totalItems = obserable.size();
        int pageCout = (totalItems / ItemPerpage) + 1;
        pagination2.setPageCount(pageCout);
        if (currentPageIndex >= pageCout) {
            currentPageIndex = pageCout - 1;
        }
        int startIndex = currentPageIndex * ItemPerpage;
        int endIndex = Math.min(startIndex + ItemPerpage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Subject> sublist = obserable.subList(startIndex, endIndex);
        TableSubject.setItems(FXCollections.observableArrayList(sublist));
        colNameSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSessionSubject.setCellValueFactory(new PropertyValueFactory<>("session"));
        colLessionSubject.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        colLessionSubject.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, String>() {
            private Hyperlink hyperlink = new Hyperlink();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null || !empty) {
                    hyperlink.setText(item);
                    setGraphic(hyperlink);
                    {
                        hyperlink.setOnAction(event -> {
                            try {
                                java.awt.Desktop.getDesktop().browse(new URI(item));
                            } catch (Exception e) {
                                DialogAlert.DialogError("URL is not Found");
                            }
                        });
                    }
                } else {
                    setGraphic(null);
                }
            }

        });
        collistclass.setCellValueFactory(new PropertyValueFactory<>("name_Class"));
        colupdate.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colupdate.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, Boolean>() {
            private final MFXButton button = new MFXButton("Update");

            {
                button.setOnAction(event -> {
                    try {
                        com.team_fortune.student_management_teacher.model.Subject sj = getTableView().getItems().get(getIndex());

                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateSubject.fxml"));
                        AnchorPane showUpdate = loader.load();
                        MainSubjectController cs = loader.getController();
                        cs.setidsubject(sj.getId());
                        nameSubject.setText(sj.getName());
                        sessionSubject.setText(sj.getSession());
                        lessionSubject.setText(sj.getLession_link());

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(showUpdate, 400, 300));
                        stage.show();
                       stage.setResizable(true);

                    } catch (IOException ex) {
                        Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });

    }

    void showListSubject() {
        List<com.team_fortune.student_management_teacher.model.Subject> result = getDatabaseToModel.getFromsubject();
        ObservableList<com.team_fortune.student_management_teacher.model.Subject> obserable = FXCollections.observableArrayList(result);
        totalItems = obserable.size();
        int pageCout = (totalItems / ItemPerpage) + 1;
        pagination1.setPageCount(pageCout);
        if (currentPageIndex >= pageCout) {
            currentPageIndex = pageCout - 1;
        }
        int startIndex = currentPageIndex * ItemPerpage;
        int endIndex = Math.min(startIndex + ItemPerpage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Subject> sublist = obserable.subList(startIndex, endIndex);
        TableListSubject.setItems(FXCollections.observableArrayList(sublist));
        colName_Subject.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSession_Subject.setCellValueFactory(new PropertyValueFactory<>("session"));
        colLession_Subject.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        colLession_Subject.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, String>() {
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
        colClass.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colClass.setCellFactory(column -> new TableCell<Subject, Boolean>() {
            private MFXButton button = new MFXButton("View");

            {
                button.setOnAction(event -> {
                    Subject selectSubject = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/ListClass.fxml"));
                        AnchorPane listClass = loader.load();
                        MainSubjectController cls = loader.getController();
                        cls.listClass(selectSubject.getName());

                        Stage list = new Stage();
                        list.initModality(Modality.APPLICATION_MODAL);
                        list.setScene(new Scene(listClass));
                        list.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
        colStudent.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colStudent.setCellFactory(column -> new TableCell<Subject, Boolean>() {
            private final MFXButton button = new MFXButton("View");

            {
                button.setOnAction(event -> {
                    Subject selectSubject = getTableView().getItems().get(getIndex());
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/ListStudent.fxml"));
                        AnchorPane listStudent = loader.load();

                        MainSubjectController cl = loader.getController();
                        cl.liststudent(selectSubject.getName());
                        Stage list = new Stage();
                        list.initModality(Modality.APPLICATION_MODAL);
                        list.setScene(new Scene(listStudent));
                        list.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item == null || isEmpty()) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });
    }

    @FXML
    void Delete(ActionEvent event) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "Select a.id From subject a Join class_subject b ON a.id=b.id_subject Where b.id_student IS NOT NULL  ";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DialogAlert.DialogError("Cannot Delete");

            } else {
                String checkquery = "Select id_class,id_teacher,id_assignments,id_exam,id_transcipt,id_solution From class_subject Where id_class IS null And id_subject IS Null  And id_student IS NULL And id_assignments IS null And id_exam IS NULL AND id_transcipt IS NULL AND id_solution IS NULL";
                PreparedStatement stmtcheck = conn.prepareStatement(checkquery);
                ResultSet rscheck = stmtcheck.executeQuery();
                if (rscheck.next()) {
                    String deletQuery = "Delete From class_subject Where id_subject=?";
                    PreparedStatement delete = conn.prepareStatement(deletQuery);
                    delete.setInt(1, id_subject);
                    delete.executeUpdate();
                } else {
                    String Updatequery = "Update class_subject set id_subject=NULL where id_subject=?";
                    PreparedStatement updatestmt = conn.prepareStatement(Updatequery);
                    updatestmt.setInt(1, id_subject);
                    updatestmt.executeUpdate();
                }
                String Deletequery = "Delete From subject Where id=?";
                PreparedStatement deletesmt = conn.prepareStatement(Deletequery);
                deletesmt.setInt(1, id_subject);
                deletesmt.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Updatesubject(ActionEvent event) {
        String query = "Select id_subject From class_subject where id_subject=? And id_student is NOT NULL";
        String update = "Update subject set name=?,session=?,lession_link=? Where id=?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, getidsubject());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DialogAlert.DialogError("Can not Update Subject");
            } else {
                PreparedStatement udpatestmt = conn.prepareStatement(update);
                udpatestmt.setString(1, nameSubject.getText());
                udpatestmt.setString(2, sessionSubject.getText());
                udpatestmt.setString(3, lessionSubject.getText());
                udpatestmt.setInt(4, getidsubject());
                System.out.println(id_subject);
                udpatestmt.executeUpdate();
                DialogAlert.DialogSuccess("Update Subject Successfully");
                nameSubject.setText("");
                sessionSubject.setText("");
                lessionSubject.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getidsubject() {
        return updateid_subject;
    }

    private void setidsubject(int id_subject) {
        updateid_subject = id_subject;
    }

    private void searchSubject(String newvalue) {
        List<com.team_fortune.student_management_teacher.model.Subject> listsubject = getDatabaseToModel.getDataFromDatabaseSubjectWithKey(newvalue);
        ObservableList<com.team_fortune.student_management_teacher.model.Subject> obserable = FXCollections.observableArrayList(listsubject);
        totalItems = obserable.size();
        int pageCout = (totalItems / ItemPerpage) + 1;
        pagination2.setPageCount(pageCout);
        if (currentPageIndex >= pageCout) {
            currentPageIndex = pageCout - 1;
        }
        int startIndex = currentPageIndex * ItemPerpage;
        int endIndex = Math.min(startIndex + ItemPerpage, totalItems);
        startIndex = Math.min(startIndex, totalItems);
        endIndex = Math.min(endIndex, totalItems);
        List<com.team_fortune.student_management_teacher.model.Subject> sublist = obserable.subList(startIndex, endIndex);
        TableSubject.setItems(FXCollections.observableArrayList(sublist));
        colNameSubject.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSessionSubject.setCellValueFactory(new PropertyValueFactory<>("session"));
        colLessionSubject.setCellValueFactory(new PropertyValueFactory<>("lession_link"));
        collistclass.setCellValueFactory(new PropertyValueFactory<>("name_Class"));
        colupdate.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        colupdate.setCellFactory(column -> new TableCell<com.team_fortune.student_management_teacher.model.Subject, Boolean>() {
            private final MFXButton button = new MFXButton("Update");

            {
                button.setOnAction(event -> {
                    try {
                        com.team_fortune.student_management_teacher.model.Subject sj = getTableView().getItems().get(getIndex());

                        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/team_fortune/student_management_teacher/view/UpdateSubject.fxml"));
                        AnchorPane showUpdate = loader.load();
                        MainSubjectController cs = loader.getController();
                        cs.setidsubject(sj.getId());
                        nameSubject.setText(sj.getName());
                        sessionSubject.setText(sj.getSession());
                        lessionSubject.setText(sj.getLession_link());

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(showUpdate, 400, 300));
                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                button.getStyleClass().add("button-design");
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }

        });

    }

    private void startUpdating() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {

                showSuject();
                showListSubject();
                deletedisplay();
                searchSubject(key_search.getText());
                deletesearchkey(searchdelete.getText());
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Class.addAll(new getDatabaseToModel().getDataFromDatabaseClass());
        Class_Subject.addAll(new getDatabaseToModel().getDataFromDatabaseClassSubject());

        name_class.getItems().addAll(getClassNames());
        searchdelete.textProperty().addListener((observable, oldvalue, newValue)->{
            deletesearchkey(newValue);
        });
        startUpdating();
        showSuject();
        showListSubject();
        key_search.textProperty().addListener((observable, oldvalue, newValue) -> {
            searchSubject(newValue);
        });
        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            currentPageIndex = newIndex.byteValue();
            deletedisplay();
        });
        pagination1.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            currentPageIndex = newIndex.byteValue();
            showListSubject();
        });
        pagination2.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            currentPageIndex = newIndex.byteValue();
            showSuject();
            searchSubject(key_search.getText());
        });
        
        tblDelete.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                com.team_fortune.student_management_teacher.model.Subject selectedItem = tblDelete.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    id_subject = selectedItem.getId();

                }
            }
        });

    }

}
