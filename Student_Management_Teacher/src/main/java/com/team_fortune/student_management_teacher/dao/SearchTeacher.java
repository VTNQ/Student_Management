package com.team_fortune.student_management_teacher.dao;

import com.team_fortune.student_management_teacher.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchTeacher{
    public static boolean searchTeacherAll(String username, String password) throws SQLException{
        String searchQuery="select*from teacher";
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(searchQuery);
        boolean isFound=false;
        while(rs.next()){
            if(username.equals(rs.getString("username"))&&password.equals(rs.getString("password"))){
                isFound=true;
            }
        }
        return isFound;
    }
}
