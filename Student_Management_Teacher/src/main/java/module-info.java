module com.team_fortune.student_management_teacher {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.team_fortune.student_management_teacher to javafx.fxml;
    exports com.team_fortune.student_management_teacher;
}
