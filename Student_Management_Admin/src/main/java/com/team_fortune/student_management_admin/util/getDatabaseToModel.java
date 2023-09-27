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
        try{
            String searchQuery="select*from student";
            Connection conn=DBConnection.getConnection();
            Statement s =conn.createStatement();
            ResultSet rs=s.executeQuery(searchQuery);
            List<com.team_fortune.student_management_admin.model.Student> Students=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Student student=new com.team_fortune.student_management_admin.model.Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPhone(rs.getString("phone"));
                student.setSince(rs.getDate("since"));
                student.setStatus(rs.getBoolean("status"));
                Students.add(student);
            }
            return Students;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public List<com.team_fortune.student_management_admin.model.Teacher> getDataFromDatabaseTeacher() {
        try{
            String searchQuery="select*from teacher";
            Connection conn=DBConnection.getConnection();
            Statement s =conn.createStatement();
            ResultSet rs=s.executeQuery(searchQuery);
            List<com.team_fortune.student_management_admin.model.Teacher> Teachers=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Teacher teacher=new com.team_fortune.student_management_admin.model.Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setSince(rs.getDate("since"));
                teacher.setStatus(rs.getBoolean("status"));
                Teachers.add(teacher);
            }
            return Teachers;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
     public List<com.team_fortune.student_management_admin.model.Teacher> getDataFromDatabaseTeacherWithKey(String name) {
        try{
            String searchQuery="select*from teacher where name like ?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setString(1,'%'+name+'%');
            ResultSet rs=ps.executeQuery();
            List<com.team_fortune.student_management_admin.model.Teacher> Teachers=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Teacher teacher=new com.team_fortune.student_management_admin.model.Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setSince(rs.getDate("since"));
                teacher.setStatus(rs.getBoolean("status"));
                Teachers.add(teacher);
            }
            return Teachers;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    public List<com.team_fortune.student_management_admin.model.Student> getDataFromDatabaseStudentWithKey(String name) {
        try{
            String searchQuery="select*from student where name like ?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps =conn.prepareStatement(searchQuery);
            ps.setString(1,'%'+name+'%');
            ResultSet rs=ps.executeQuery();
            List<com.team_fortune.student_management_admin.model.Student> Students=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_admin.model.Student student=new com.team_fortune.student_management_admin.model.Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setPhone(rs.getString("phone"));
                student.setSince(rs.getDate("since"));
                student.setStatus(rs.getBoolean("status"));
                Students.add(student);
            }
            return Students;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
