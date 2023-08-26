package com.team_fortune.student_management_teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.team_fortune.student_management_teacher.Panel.*;
import javafx.scene.control.ScrollPane;

public class MainController {

    @FXML
    private Label btn_add_class;
    @FXML
    private Label btn_add_subject;
    @FXML
    private Label btn_list_class;
    @FXML
    private Label btn_list_subject;
    @FXML
    private ScrollPane main_display;
    @FXML
    private PnlHome pnlHome;
    @FXML
    void btn_home(MouseEvent event) {
        pnlHome=new PnlHome();
        main_display.setContent(pnlHome);
    }
}