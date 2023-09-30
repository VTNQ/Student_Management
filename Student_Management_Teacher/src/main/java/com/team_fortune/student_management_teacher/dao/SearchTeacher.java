package com.team_fortune.student_management_teacher.dao;

import com.team_fortune.student_management_teacher.util.DBConnection;
import com.team_fortune.student_management_teacher.util.MD5;
import com.team_fortune.student_management_teacher.util.getDatabaseToModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchTeacher{
    public static int searchTeacherAll(String username, String password) throws SQLException{
        String username_teacher=MD5.Md5(username);
        String password_teacher=MD5.Md5(password);
        String searchQuery="select*from teacher";
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
    
    public static boolean searchTeacherWithStatus() throws SQLException{
        String searchQuery = "SELECT * FROM teacher WHERE status=? AND id=?";
    Connection conn = DBConnection.getConnection();
    PreparedStatement ps = conn.prepareStatement(searchQuery);
    ps.setBoolean(1, true);
    ps.setInt(2, getDatabaseToModel.id_teacher);
    ResultSet rs = ps.executeQuery();
    boolean isFound = rs.next(); // Check if there is at least one result
    DBConnection.closeConnection(conn);
    return isFound;
    }
}
