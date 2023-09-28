package com.team_fortune.student_management_admin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class getDatabaseToModel {
    public List<com.team_fortune.student_management_admin.model.Student>getDataFromDatabaseStudent(){
        List<com.team_fortune.student_management_admin.model.Student> Students=new ArrayList<>();
        try{
            String searchQuery="select*from student";
            Connection conn=DBConnection.getConnection();
            Statement s =conn.createStatement();
            ResultSet rs=s.executeQuery(searchQuery);
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Student student=new com.team_fortune.student_management_admin.model.Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPhone(rs.getString("phone"));
                student.setSince(rs.getDate("since"));
                student.setStatus(rs.getBoolean("status"));
                Students.add(student);
            }
            rs.close();
            s.close();
            DBConnection.closeConnection(conn);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return Students;
    }
    public List<com.team_fortune.student_management_admin.model.Teacher> getDataFromDatabaseTeacher() {
        List<com.team_fortune.student_management_admin.model.Teacher> Teachers=new ArrayList<>();
        try{
            String searchQuery="select*from teacher";
            Connection conn=DBConnection.getConnection();
            Statement s =conn.createStatement();
            ResultSet rs=s.executeQuery(searchQuery);
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Teacher teacher=new com.team_fortune.student_management_admin.model.Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setSince(rs.getDate("since"));
                teacher.setStatus(rs.getBoolean("status"));
                Teachers.add(teacher);
            }
            rs.close();
            s.close();
            DBConnection.closeConnection(conn);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return Teachers;
    }
     public List<com.team_fortune.student_management_admin.model.Teacher> getDataFromDatabaseTeacherWithKey(String name) {
         List<com.team_fortune.student_management_admin.model.Teacher> Teachers=new ArrayList<>();
        try{
            String searchQuery="select*from teacher where name like ?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setString(1,'%'+name+'%');
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Teacher teacher=new com.team_fortune.student_management_admin.model.Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setSince(rs.getDate("since"));
                teacher.setStatus(rs.getBoolean("status"));
                Teachers.add(teacher);
            }
            rs.close();
            ps.close();
            DBConnection.closeConnection(conn);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return Teachers;
    }
    public List<com.team_fortune.student_management_admin.model.Student> getDataFromDatabaseStudentWithKey(String name) {
        List<com.team_fortune.student_management_admin.model.Student> Students=new ArrayList<>();
        try{
            String searchQuery="select*from student where name like ?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setString(1,'%'+name+'%');
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Student student=new com.team_fortune.student_management_admin.model.Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPhone(rs.getString("phone"));
                student.setSince(rs.getDate("since"));
                student.setStatus(rs.getBoolean("status"));
                Students.add(student);
            }
            rs.close();
            ps.close();
            DBConnection.closeConnection(conn);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return Students;
    }
}
