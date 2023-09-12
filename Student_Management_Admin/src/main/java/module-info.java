module com.team_fortune.student_management_admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires java.sql;

    opens com.team_fortune.student_management_admin to javafx.fxml;
    exports com.team_fortune.student_management_admin;
}
