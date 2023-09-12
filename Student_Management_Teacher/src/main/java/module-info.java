module com.team_fortune.student_management_teacher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.base;
    requires java.sql;
    requires MaterialFX;
    requires VirtualizedFX;
    requires mfx.core;
    
    opens com.team_fortune.student_management_teacher to javafx.fxml;
    exports com.team_fortune.student_management_teacher;
}
