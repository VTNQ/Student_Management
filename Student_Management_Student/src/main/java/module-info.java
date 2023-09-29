module com.team_fortune.student_management_student {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires MaterialFX;

    
    opens com.team_fortune.student_management_student to javafx.fxml;
 opens com.team_fortune.student_management_student.models to javafx.base;
    exports com.team_fortune.student_management_student;
}