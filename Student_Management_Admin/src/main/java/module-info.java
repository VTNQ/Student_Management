module com.team_fortune.student_management_admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires java.sql;
    requires java.mail;

    opens com.team_fortune.student_management_admin to javafx.fxml;
    opens com.team_fortune.student_management_admin.model to javafx.base;
    exports com.team_fortune.student_management_admin;
}
