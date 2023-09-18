package com.team_fortune.student_management_student;





import io.github.palexdev.materialfx.controls.MFXDatePicker;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.image.Image;

import java.net.URL;
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
import java.util.Locale;
import javafx.scene.control.TableCell;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.util.Duration;


public class SecondaryController implements Initializable{
    private Connection connection;
    @FXML
    private TableColumn<modelExample,Boolean>Columnassignment;
    @FXML
   
    private AnchorPane Home;
    @FXML
    private Button btnexercise;
    public String name_class;
    @FXML
    private AnchorPane Exercise;
      @FXML
    private TextField idName;
      
    @FXML
    private TextField userfiled;
    
    @FXML
    private TextField phoneupdate;
        @FXML
    private MFXDatePicker sinceupdate;
            @FXML
    private PasswordField passwordudpate;
    @FXML
    private Button Homebtn3;
        @FXML
    private Button searchbutton3;
            @FXML
    private Button Scorebtn3;

    @FXML
    private AnchorPane Schedule;

    @FXML
    private AnchorPane Search;
        @FXML
    private AnchorPane Score;
            @FXML
    private PieChart biechart;
    @FXML
    private BarChart<String,Number> Barchart;
    @FXML
    public   Label sa;

   @FXML
    public   Label sa1;
    @FXML
    public   Label sa2;
    @FXML
    private TableView<model> tbl;
@FXML
private TableView<modelsubject>tbl1;
@FXML
private TableView<modelcalender>tbl11;
  @FXML
  private TableColumn<modelcalender, String>colid11;
  @FXML
  private TableColumn<modelcalender, String>colteacher11;
  @FXML
  private TableColumn<modelcalender, String> colCountdown;

  @FXML
  private TableColumn<modelcalender, String>colclass1;
  @FXML
  private TableColumn<modelcalender, String>Time;  
      
        @FXML
        private TableColumn<modelsubject,String>Status;
     @FXML
     private TableColumn<modelsubject,String>colclass;
     
@FXML 
        private TableColumn<modelsubject,String>colteacher1;
    @FXML
    private MenuItem signup;

    private String loggedInUsername;
    @FXML
    private TableColumn<modelsubject,String>colid1;
    @FXML
    private TableColumn<modelExample,String>colstt;
    @FXML
    private TableColumn<modelExample,String>columnsubject;
    @FXML
    private TableColumn<modelExample,String>columnteacher;
    @FXML
    private TableColumn<modelExample,String>colExam;
    @FXML
    private TableColumn<modelExample,String>Columnclass;
    @FXML
    private TableView<modelExample>tblexample;
    @FXML
    private TableColumn<model, String> colid;
  @FXML 
  private Button btnHomeud;
    @FXML
    private TableColumn<model, String> colsubject;

    @FXML
    private TableColumn<model, String> colteacher;
@FXML
private TableColumn<modelsubject,String>colsubject1;
    @FXML
    private TableColumn<model, String> tblteacher;
     @FXML
    private Button btncalender;
        @FXML
    private Button btncalender1;
        @FXML
        private Button btnExercise1;
            @FXML
    private Button btncalender3;
        @FXML
        private Button btnExercise4;
public static int assignmentId;
    @FXML
    private TextField txtserach = new TextField();
     @FXML
        private Button btnExercisestudent;
    @FXML
    private Button searchbutton;
        @FXML
    private Button Homebtn;
        @FXML
    private Button Scorebtn;
        @FXML
        private Button btnExercise2;
        @FXML
        private Button Homebtn311;
        @FXML
        private Button  Homebtnex;
       @FXML
       private Button update;

    
    @FXML
    private AnchorPane updateprofile;
@FXML
private Button searchbutton311;
@FXML
private Button Searchbtnex;
@FXML
private Button Scorebtnex;
@FXML 
private Button btnex;
@FXML
private Button btncalex;
@FXML
private TableColumn<modelcalender,String>Endtime;
String link_examp;
@FXML
    void changeForm(ActionEvent event) {
        if(event.getSource()==Homebtn){
            Home.setVisible(true);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==searchbutton311){
             Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==Searchbtnex){
             Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==btncalex){
             Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(true);
            Exercise.setVisible(false);
        }else if(event.getSource()==Homebtnex){
            Home.setVisible(true);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==btnex){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==Scorebtnex){
            Home.setVisible(false);
            Score.setVisible(true);
            Search.setVisible(true);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==btnexercise){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==Homebtn311){
             Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==btnExercise4){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==update){
            updateprofile.setVisible(true);
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(false);
        }else if(event.getSource()==btnExercisestudent){
              Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==searchbutton){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
        }else if(event.getSource()==Scorebtn){
            Home.setVisible(false);
            Score.setVisible(true);
            Search.setVisible(false);
            Schedule.setVisible(false);
        }else if(event.getSource()==btnExercise1){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==btnExercise2){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
            Exercise.setVisible(true);
        }else if(event.getSource()==btncalender){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(true);
        }else if(event.getSource()==Homebtn1){
    models.clear();
    txtserach.setText("");
            Home.setVisible(true);
            Score.setVisible(false);
            Search.setVisible(false);
                Schedule.setVisible(false);
        }else if(event.getSource()==searchbutton1){
            models.clear();
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
        }else if(event.getSource()==Scorebtn1){
            models.clear();
            txtserach.setText("");
            Home.setVisible(false);
            Score.setVisible(true);
            Search.setVisible(false);
        }else if(event.getSource()==Homebtn2){
            Home.setVisible(true);
            Score.setVisible(false);
            Search.setVisible(false);
                Schedule.setVisible(false);
        }else if(event.getSource()==searchbutton2){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
                Schedule.setVisible(false);
        }else if(event.getSource()==Scorebtn2){
            Home.setVisible(false);
            Score.setVisible(true);
            Search.setVisible(false);
            Schedule.setVisible(false);
        }else if(event.getSource()==btncalender1){
            model.clear();
            txtserach.setText("");
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(true);
        }else if(event.getSource()==btncalender3){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(true);
        }else if(event.getSource()==Homebtn3){
            Home.setVisible(true);
            Score.setVisible(false);
            Search.setVisible(false);
            Schedule.setVisible(false);
        }else if(event.getSource()==searchbutton3){
            Home.setVisible(false);
            Score.setVisible(false);
            Search.setVisible(true);
            Schedule.setVisible(false);
        }else if(event.getSource()==Scorebtn3){
             Home.setVisible(false);
            Score.setVisible(true);
            Search.setVisible(false);
            Schedule.setVisible(false);
        }
    }
     @FXML
    private Button Homebtn1;
     @FXML
     private TableColumn<modelcalender,String>colclock;
       @FXML
    private Button Scorebtn1;
  @FXML
    private Button searchbutton1;
  private static int id;
    @FXML
    private Button Homebtn2;
    private String link;
              @FXML
    private Button Scorebtn2;
   @FXML
    private Button searchbutton2;
   String linkDisplay;
   
    @FXML
    private TableView<modelWord> tblWord;
    @FXML
    private TableColumn<modelWord,String>colworldstt;
    @FXML
    private TableColumn<modelWord,String>colworldsubject;
    @FXML
    private TableColumn<modelWord,String>colworldteacher;
    @FXML
    private TableColumn<modelWord,String>colWorl;
    @FXML
    private TableColumn<modelWord,String>colWorldclass;
  
    private String coutdown;
 
    private void displayErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    private final ObservableList<model> models = FXCollections.observableArrayList();
private final ObservableList<modelsubject>model=FXCollections.observableArrayList();
private final ObservableList<modelcalender>data=FXCollections.observableArrayList();
private final ObservableList<modelExample>Example=FXCollections.observableArrayList();
private final ObservableList<modelWord>World=FXCollections.observableArrayList();
  public  String getClassName(String username){
  connection = PrimaryController.connectDB();
   String className = null;
      try {
          String query="Select c.name_class From class c "
                  +"JOIN class_subject cs ON c.id=cs.id_class "
                  +"JOIN student s ON cs.id_student=s.id "
                  +"Where s.username=?";
          PreparedStatement stmt=connection.prepareStatement(query);
          stmt.setString(1, username);
          ResultSet resultset=stmt.executeQuery();
          if(resultset.next()){
             className=resultset.getString("name_class");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return className;
  }

   public void chart() {
    String chartQuery = "SELECT t1.name_subject, t5.score FROM subject t1 " +
            "JOIN class_subject t2 ON t1.id = t2.id_subject " +
            "JOIN student t3 ON t2.id_student = t3.id " +
            "JOIN exam_schedule t4 ON t2.id_exam = t4.id " +
            "JOIN transcript t5 ON t4.id = t5.id_exam " +
            "WHERE t3.id = ?";

    connection = PrimaryController.connectDB();
       try {
           CategoryAxis xAxis=new CategoryAxis();
           NumberAxis yAyis=new NumberAxis();
           Barchart.setTitle("total score");
           xAxis.setLabel("Name subject");
           yAyis.setLabel("Score");
           XYChart.Series<String,Number> chartbar=new XYChart.Series<>();
           PreparedStatement stmt=connection.prepareStatement(chartQuery);
           stmt.setInt(1, PrimaryController.loggedInStudentId);
           ResultSet result=stmt.executeQuery();
           while(result.next()){
               String nameSubject=result.getString("name_subject");
               Float score=result.getFloat("score");
               chartbar.getData().add(new XYChart.Data<>(nameSubject,score));
           }
           Barchart.getData().add(chartbar);
       } catch (Exception e) {
       }
}
   public void Example(){
       String query="Select t1.name_subject,t3.name,t4.link,t5.name_class,t4.id_ass From subject t1 "
               +"JOIN class_subject t2 ON t1.id=t2.id_subject "
               +"JOIN teacher t3 ON t2.id_teacher=t3.id "
               +"Join assignments t4 On t4.id_ass=t2.id_assignments "
               +"Join class t5 ON t5.id=t2.id_class "
               +"Join student t6 ON t6.id=t2.id_student "
               +"Where t6.id = ? ";
       connection=PrimaryController.connectDB();
       try {
           PreparedStatement stmt=connection.prepareStatement(query);
           stmt.setInt(1, PrimaryController.loggedInStudentId);
         
           ResultSet rs=stmt.executeQuery();
           int index=1;
           while(rs.next()){
               String name_subject=rs.getString("name_subject");
               String name=rs.getString("name");
               id=rs.getInt("id_ass");
                link=rs.getString("link");
               String name_class=rs.getString("name_class");
               
               Example.add(new modelExample(index, name_subject, name,link, name_class,id));
                       index++;
                      
           }
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       
       updateExample(Example);
   }
   public void piechart(){
       String chart="SELECT COUNT(DISTINCT t1.id_subject) as total FROM class_subject t1 " +
                   "JOIN student t2 ON t1.id_student = t2.id " +
                   "JOIN class t3 ON t1.id_class=t3.id "+
                   "WHERE t2.id = ? AND t3.name_class = ?";
       connection=PrimaryController.connectDB();
       ObservableList<PieChart.Data> piechartData=FXCollections.observableArrayList();
       try {
           PreparedStatement st=connection.prepareStatement(chart);
           st.setInt(1, PrimaryController.loggedInStudentId);
           st.setString(2, getClassName(PrimaryController.loggedInUsername));
           System.out.println(getClassName(PrimaryController.loggedInUsername));
           ResultSet resultset=st.executeQuery();
           while(resultset.next()){
              
               int total=resultset.getInt("total");
               
               PieChart.Data data=new PieChart.Data(getClassName(PrimaryController.loggedInUsername),total);
               piechartData.add(data);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       biechart.setData(piechartData);
   }
 public void selectExample() {
    String query = "SELECT t1.name_subject, t3.name_class, t4.start,t4.link_exam,t4.end " +
                   "FROM subject t1 " +
                   "JOIN class_subject t5 ON t1.id = t5.id_subject " +
                   "JOIN class t3 ON t5.id_class = t3.id " +
                   "JOIN student t6 ON t5.id_student = t6.id " +
                   "JOIN exam_schedule t4 ON t5.id_exam = t4.id " +
                   "JOIN teacher t2 ON t2.id = t5.id_teacher " +
                   "WHERE t6.id = ? ";

    connection = PrimaryController.connectDB();
    try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, PrimaryController.loggedInStudentId);
        ResultSet rs = stmt.executeQuery();
        int index = 1;
        while(rs.next()){
            String subject = rs.getString("name_subject");
            
            String className = rs.getString("name_class");
             link_examp=rs.getString("link_exam");
            LocalDateTime startTime = rs.getTimestamp("start").toLocalDateTime();
            String startcout=startcountdown(startTime);
            System.out.println(startcout); 
            LocalDateTime endtime=rs.getTimestamp("end").toLocalDateTime();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm",new Locale("vi","VN"));
            String formatdate=startTime.format(formatter);
           
            DateTimeFormatter endformat=DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm",new Locale("vi","VN"));
            String endfor=endtime.format(endformat);
         linkDisplay ="chua co tai lieu";

            data.add(new modelcalender(index,subject , className, startcout,linkDisplay,endfor));

      

            index++;
        }

     
        updatestart(data);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 public String startcountdown(LocalDateTime startTime1){
 LocalDateTime now=LocalDateTime.now();
 
 long tottalSeconddifference=java.time.Duration.between(now, startTime1).getSeconds();
 if(tottalSeconddifference<=15*60){
     Timeline tl=new Timeline();
     tl.setCycleCount(Timeline.INDEFINITE);
     KeyFrame keyframe=new KeyFrame(Duration.seconds(1), event->{
     long seconddiiff=java.time.Duration.between(LocalDateTime.now(), startTime1).getSeconds();
     long minutes=seconddiiff/60;
     long second=seconddiiff%60;
     if(seconddiiff<=0){
         tl.stop();
     }
     String resultcoutdown=String.format("%02d:%02d", minutes,second);
     coutdown=resultcoutdown;
     });
     tl.getKeyFrames().add(keyframe);
     tl.play();
 }
 return coutdown;

 }
   private String formatTime(long minute,long seconds){
        return String.format("%02d:%02d", minute,seconds);
    }

private String caculaterating(Timestamp startTime){
    long currentTimeMillis = System.currentTimeMillis();
    long startTimeMillis = startTime.getTime();
    long remainingTimeSecond = (startTimeMillis - currentTimeMillis) / 1000;

    if (remainingTimeSecond <= 0){
        return "00:00";
    }

    long remainingMinutes = remainingTimeSecond / 60;
    long remainingSeconds = remainingTimeSecond % 60;

    return String.format("%02d:%02d", remainingMinutes, remainingSeconds);
}
    @FXML
    void signout(ActionEvent event) {
        try {
            if (PrimaryController.conn != null) {
              
                
                             App.setRoot("primary");
            PrimaryController.conn.close();
                  }
       
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
          void searchdata(String searchitem){
              connection=PrimaryController.connectDB();
            models.clear();
               String query = "SELECT t1.id,tc.name,st.name_subject,sc.name_class FROM student t1 " +
               "JOIN class_subject t2 ON t1.id = t2.id_student " +
               "JOIN class sc ON t2.id_class = sc.id " +
               "JOIN subject st ON t2.id_subject = st.id " +
               "JOIN teacher tc ON t2.id_teacher = tc.id " +
               "WHERE t1.id = ? AND tc.name LIKE ?";
                int index=1;
                try {
                    PreparedStatement preparestatement=connection.prepareStatement(query);
                    preparestatement.setInt(1, PrimaryController.loggedInStudentId);
                    System.out.print(PrimaryController.loggedInStudentId);
                    preparestatement.setString(2, "%"+searchitem+"%");
                    ResultSet result=preparestatement.executeQuery();
                    while(result.next()){
                      
                        String name_teacher=result.getString("name");
                        String name_subject=result.getString("name_subject");
                        String name_class=result.getString("name_class");
                        models.add(new model(index,name_teacher,name_subject,name_class));
                        index++;
                       
                    }
              } catch (Exception e) {
                  e.printStackTrace();
              }
                updatetabledata(models);
                if(searchitem.isEmpty()){
                    models.clear();
                }
          }
          private void WorldStudent(){
              connection=PrimaryController.connectDB();
              World.clear();
              String query="Select t1.name_subject,t3.name,t1.lession_link,t6.name_class From subject t1 "
               +"Join class_subject t2 ON t1.id=t2.id_subject "
               +"JOIN teacher t3 ON t2.id_teacher=t3.id "
               
               +"JOIN class t6 ON t2.id_class=t6.id "
               +"JOIN student t7 ON t2.id_student=t7.id "
               +"Where t7.id=? ";
              int index=1;
              try {
                  PreparedStatement stmt=connection.prepareStatement(query);
                  stmt.setInt(1, PrimaryController.loggedInStudentId);
              
                  ResultSet rs=stmt.executeQuery();
                  while(rs.next()){
                      String name_subject=rs.getString("name_subject");
                      String name_teacher=rs.getString("name");
                      String link=rs.getString("lession_link");
                      String name_class=rs.getString("name_class");
                      World.add(new modelWord(index, name_subject, name_teacher, link, name_class));
                      index++;
                  }
                  updateWord(World);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
          public int idassignment(String assignment){
              int id=-1;
              connection=PrimaryController.connectDB();
              String query="Select t1.id_ass From assignments t1 "
                      +"JOIN class_subject  t2 ON t1.id_ass=t2.id_assignments "
                      +"JOIN subject t3 ON t2.id_subject=t3.id "+"Where t3.name_subject = ?";
              try {
                  PreparedStatement stmt=connection.prepareStatement(query);
                  stmt.setString(1, assignment);
                  ResultSet rs=stmt.executeQuery();
                  while(rs.next()){
                       id=rs.getInt("id_ass");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
              return id;
          }
          public void updateExample(ObservableList<modelExample> data){
              tblexample.setItems(data);
              colstt.setCellValueFactory(new PropertyValueFactory<>("index"));
              columnsubject.setCellValueFactory(new PropertyValueFactory<>("nameSubject"));
              columnteacher.setCellValueFactory(new PropertyValueFactory<>("Home"));
              colExam.setCellValueFactory(new PropertyValueFactory<>("Examp"));
              Columnclass.setCellValueFactory(new PropertyValueFactory<>("classmy"));
              Columnassignment.setCellValueFactory(new PropertyValueFactory<>("button"));
              Columnassignment.setCellFactory(column -> new TableCell<modelExample, Boolean>() {
    private final Button  submit = new Button("Submit");
    private final Button home=new Button("Detail");
private final HBox buttonsContainer = new HBox();
    {
        submit.setOnAction(event -> {
        // This will give you the index of the clicked row
             
        modelExample calender=getTableView().getItems().get(getIndex());
        
        String subject=calender.getNameSubject();
       assignmentId=  idassignment(subject);
            System.out.println(assignmentId);
            try {
                 FXMLLoader fxmlLoader=new FXMLLoader(App.class.getResource("submitassignment.fxml"));
            AnchorPane newPopup;
                newPopup = fxmlLoader.load();
                 SubmitassignmentController forgot_password=fxmlLoader.getController();
            forgot_password.init(subject);
            Stage popupStage=new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(newPopup,600,400));
            popupStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        home.setOnAction(event -> {
            // Your home button action
           modelExample calender=getTableView().getItems().get(getIndex());
        
        String subject=calender.getNameSubject();
       assignmentId=  idassignment(subject);
            System.out.println(assignmentId);
            try {
                  FXMLLoader fxmlLoader=new FXMLLoader(App.class.getResource("detail.fxml"));
            AnchorPane newPopup;
                newPopup=fxmlLoader.load();
                DetailController detail=fxmlLoader.getController();
                detail.init();
                Stage popupStage=new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setScene(new Scene(newPopup,668,462));
                popupStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        buttonsContainer.getChildren().addAll(submit, home);
    }
  
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        submit.getStyleClass().add("button-design");
        if (empty || item == null ) {
            setGraphic(null);
        } else {
           
           setGraphic(buttonsContainer);
           
          
        
        }
    }
    
});
              tblexample.refresh();
          }
          public void updateWord(ObservableList<modelWord> data){
              tblWord.setItems(data);
              colworldstt.setCellValueFactory(new PropertyValueFactory<>("index"));
              colworldsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
              colworldteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
              colWorl.setCellValueFactory(new PropertyValueFactory<>("World"));
              colWorl.setCellFactory(column ->{
              TableCell<modelWord,String> cell=new TableCell<modelWord,String>(){
                  @Override
                  protected void updateItem(String item,boolean empty){
                      super.updateItem(item, empty);
                      if(empty ||item==null ){
                          setText(null);
                          setGraphic(null);
                      }else{
                          Hyperlink hyperlink=new Hyperlink(item);
                          hyperlink.setOnAction(event->{
                              try{
                                   Desktop.getDesktop().browse(new URI(item));
                              }catch(Exception ex){
                                  displayErrorMessage("URL is not found");
                              
                              }
                             
                          
                          });
                          setGraphic(hyperlink);
                      }
                  }
              };
                  return cell;
              });
              colWorldclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
          }
        public void updatestart(ObservableList<modelcalender> data) {
     tbl11.setItems(data);
     
     colid11.setCellValueFactory(new PropertyValueFactory<>("index"));
     colteacher11.setCellValueFactory(new PropertyValueFactory<>("name_Subject"));

     colclass1.setCellValueFactory(new PropertyValueFactory<>("name_class"));
     Time.setCellValueFactory(new PropertyValueFactory<>("StartTime")); 
 colCountdown.setCellValueFactory(new PropertyValueFactory<>("link"));
   Endtime.setCellValueFactory(new PropertyValueFactory<>("Endtime"));
} 

       
                   public void updatetabledata(ObservableList<model> data){
              tbl.setItems(data);
              colid.setCellValueFactory(new PropertyValueFactory<>("id"));
             colteacher.setCellValueFactory(new PropertyValueFactory<>("name_teacher"));
             colsubject.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
            tblteacher.setCellValueFactory(new PropertyValueFactory<>("name_class"));
          }
                   public void updatedish(ObservableList<modelsubject> data){
                       tbl1.setItems(data);
                       colteacher1.setCellValueFactory(new PropertyValueFactory<>("name_subject"));
                       colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
                       colsubject1.setCellValueFactory(new PropertyValueFactory<>("score"));
                       colclass.setCellValueFactory(new PropertyValueFactory<>("name_class"));
                       Status.setCellValueFactory(new PropertyValueFactory<>("status"));
                   }
                   private void displaysuccessfully(String message){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
                  void displayrecord(){
               connection = PrimaryController.connectDB();
    model.clear();
    String query = "SELECT t3.id,t1.name_subject,t5.score,t6.name_class,t5.status FROM subject t1 " +
                   "JOIN class_subject t2 ON t1.id = t2.id_subject " +
                   "JOIN student t3 On t2.id_student=t3.id " +
                   "JOIN exam_schedule t4 On t2.id_exam=t4.id " +
                   "JOIN transcript t5 On t4.id=t5.id_exam " +
                   "JOIN class t6 On t2.id_class=t6.id "+
                   "WHERE t3.id = ?  ";

    try {
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, PrimaryController.loggedInStudentId);
        System.out.println(PrimaryController.loggedInStudentId);
        ResultSet result = st.executeQuery();
  int index=1;
        while (result.next()) {
         
            String subject = result.getString("name_subject");
              
            Float score = result.getFloat("score");
            name_class=result.getString("name_class");
           boolean status=result.getBoolean("status");
           String resultTest=(status)?"dat":"rot";
          
            model.add(new modelsubject(index, subject, score,name_class,resultTest));
            index++;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    updatedish(model);
                  }

    @Override
   public void initialize(URL url, ResourceBundle rb) {
    selectExample();
        WorldStudent();
  colExam.setCellFactory(column -> {
        return new TableCell<modelExample, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty && item != null && !item.isEmpty()) {
                    Hyperlink hyperlink = new Hyperlink(item);
                    hyperlink.setOnAction(event -> {
                        try {
                            java.awt.Desktop.getDesktop().browse(new URI(item));
                        } catch (Exception e) {
                            displayErrorMessage("URl is not Found");
                        }
                    });
                    setGraphic(hyperlink);
                } else {
                    setGraphic(null);
                }
            }
        };
    });
    System.out.println("LoggedInStudentId in initialize: " + PrimaryController.loggedInStudentId);
    chart();
    piechart();
    sa.setText(PrimaryController.loggedInUsername);
    sa1.setText(PrimaryController.loggedInUsername);
    sa2.setText(PrimaryController.loggedInUsername);
    txtserach.textProperty().addListener((observable, oldValue, newValue) -> {
        searchdata(newValue);
    });

    displayrecord();
Example();
        System.out.println(id);
    Status.setCellFactory(column -> {
        return new TableCell<modelsubject, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty && "dat".equals(item)) {
                    getStyleClass().add("field-success");
                } else {
                    getStyleClass().add("field-error");
                }

                setText(item);
            }
        };
    });
}
}






