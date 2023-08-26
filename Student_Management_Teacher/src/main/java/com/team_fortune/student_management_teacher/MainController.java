package com.team_fortune.student_management_teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.team_fortune.student_management_teacher.Panel.*;
import javafx.scene.control.ScrollPane;

public class MainController {

    @FXML
    private Label btn_add_class;
    private Label btn_add_subject;
    private Label btn_list_class;
    private Label btn_list_subject;
    private ScrollPane main_display;
    private PnlHome pnlHome;
    void btn_home(MouseEvent event) {
        pnlHome=new PnlHome();
        main_display.setContent(pnlHome);
    }
}