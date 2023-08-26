package com.team_fortune.student_management_teacher;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {
    
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Regiser");
    }
}
