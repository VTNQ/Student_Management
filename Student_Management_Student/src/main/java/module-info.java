module com.team_fortune.student_management_student {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires MaterialFX;

    
    opens com.team_fortune.student_management_student to javafx.fxml;

    exports com.team_fortune.student_management_student;
}