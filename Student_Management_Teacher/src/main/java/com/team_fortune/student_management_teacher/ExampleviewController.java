package com.team_fortune.student_management_teacher;

import com.team_fortune.student_management_teacher.dialog.DialogAlert;
import com.team_fortune.student_management_teacher.util.DBConnection;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author tranp
 */
public class ExampleviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private DatePicker End;

    @FXML
    private MFXTextField Link_Examp;

    @FXML
    private DatePicker Start;

    @FXML
    private MFXComboBox<?> name_subject;
     @FXML
    void Add_Examp(ActionEvent event) {
        String query="Insert into exam_schedule(start,end,link_exam) values(?,?,?)";
        try {
             Connection conn=DBConnection.getConnection();
             PreparedStatement stmt=conn.prepareStatement(query);
              LocalDate selectedDate=Start.getValue();
              java.sql.Date mysqlDate=Date.valueOf(selectedDate);
              LocalDate endtime=End.getValue();
                java.sql.Date EndDate=Date.valueOf(selectedDate);
                if(selectedDate.isBefore(endtime)){
                     stmt.setDate(1, mysqlDate);
                stmt.setDate(2, EndDate);
                stmt.setString(3, Link_Examp.getText());
                stmt.executeUpdate();
                }else{
                    DialogAlert.DialogError("The start date must be less than the end date ");
                }
               
         } catch (Exception e) {
         }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
