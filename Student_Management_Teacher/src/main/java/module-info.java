module com.team_fortune.student_management_teacher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.base;

    opens com.team_fortune.student_management_teacher to javafx.fxml;
    exports com.team_fortune.student_management_teacher;
}
