package com.team_fortune.student_management_teacher.dao;

import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchTeacher{
    public static int searchTeacherAll(String username, String password) throws SQLException{
        String username_teacher=MD5.Md5(username);
        String password_teacher=MD5.Md5(password);
        String searchQuery="select*from teacher where status=1";
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(searchQuery);
        int isFound=0;
        while(rs.next()){
            if(username_teacher.equals(rs.getString("username"))&&password_teacher.equals(rs.getString("password"))){
                isFound=1;
            }else if(username_teacher.equals(rs.getString("username"))||password_teacher.equals(rs.getString("password"))){
                isFound=2;
            }
        }
        DBConnection.closeConnection(conn);
        return isFound;
    }
}
