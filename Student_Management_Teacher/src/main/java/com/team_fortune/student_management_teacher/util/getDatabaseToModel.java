package com.team_fortune.student_management_teacher.util;

import com.team_fortune.student_management_teacher.HomeController;
import com.team_fortune.student_management_teacher.MainSubjectController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class getDatabaseToModel {
    
    public static int id_teacher = 0;
    public static int id_class = 0;
    public static int id_subject = 0;
    
    public List<com.team_fortune.student_management_teacher.model.Class> getDataFromDatabaseClass() {
        try {
            String SearchQuery = "select c.id,c.name from class c inner join class_subject cs on cs.id_class=c.id inner join teacher t on t.id=cs.id_teacher where t.id=? group by c.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement s = conn.prepareStatement("select id from teacher where username=?");
            s.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                id_teacher=rs.getInt("id");
            }
            PreparedStatement ps=conn.prepareStatement(SearchQuery);
            ps.setInt(1, id_teacher);
            ResultSet resultSet=ps.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class> classes = new ArrayList<>();
            while (resultSet.next()) {
                com.team_fortune.student_management_teacher.model.Class clss = new com.team_fortune.student_management_teacher.model.Class();
                clss.setId(resultSet.getInt("c.id"));
                clss.setName(resultSet.getString("c.name"));
                classes.add(clss);
            }
            return classes;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubject() {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id inner join teacher t on cs.id_teacher=t.id where t.id=? group by s.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement =conn.prepareStatement(SearchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet resultSet =preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                com.team_fortune.student_management_teacher.model.Subject sub = new com.team_fortune.student_management_teacher.model.Subject();
                sub.setId(resultSet.getInt("s.id"));
                sub.setName(resultSet.getString("s.name"));
                sub.setSession(resultSet.getString("s.session"));
                sub.setLession_link(resultSet.getString("s.lession_link"));
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubjectWithKey(String key) {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id inner join teacher t on cs.id_teacher=t.id where t.id=? and s.name like ? group by s.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement =conn.prepareStatement(SearchQuery);
            preparedStatement.setInt(1, id_teacher);
            preparedStatement.setString(2, "%"+key+"%");
            ResultSet resultSet =preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                com.team_fortune.student_management_teacher.model.Subject sub = new com.team_fortune.student_management_teacher.model.Subject();
                sub.setId(resultSet.getInt("s.id"));
                sub.setName(resultSet.getString("s.name"));
                sub.setSession(resultSet.getString("s.session"));
                sub.setLession_link(resultSet.getString("s.lession_link"));
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<com.team_fortune.student_management_teacher.model.Class_Subject>getDataFromDatabaseClassSubject(){
        try {
            String searchQuery="select cs.id_class,cs.id_subject,cs.id_teacher,cs.id_student,cs.id_assignments,cs.id_exam from class_subject cs inner join teacher t on t.id=cs.id_teacher where t.id=?";
            Connection conn=DBConnection.getConnection();
            PreparedStatement preparedStatement =conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet resultSet=preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class_Subject> classs_subjects = new ArrayList<>();
            while(resultSet.next()){
                com.team_fortune.student_management_teacher.model.Class_Subject CS = new com.team_fortune.student_management_teacher.model.Class_Subject();
                CS.setId_class(resultSet.getInt("cs.id_class"));
                CS.setId_subject(resultSet.getInt("cs.id_subject"));
                CS.setId_teacher(resultSet.getInt("cs.id_teacher"));
                CS.setId_student(resultSet.getInt("id_student"));
                CS.setId_assignments(resultSet.getInt("cs.id_assignments"));
                CS.setId_exam(resultSet.getInt("cs.id_exam"));
                classs_subjects.add(CS);
            }
            return classs_subjects;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<com.team_fortune.student_management_teacher.model.Student>getDataFromDatabaseStudent(){
        try {
            String searchQuery="select s.id,s.name from student s inner join class_subject cs on cs.id_student=s.id inner join teacher t on cs.id_teacher=t.id where t.id=? group by s.name";
            Connection conn=DBConnection.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet rs =preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Student> students=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_teacher.model.Student Stu = new com.team_fortune.student_management_teacher.model.Student();
                Stu.setId(rs.getInt("s.id"));
                Stu.setName(rs.getString("s.name"));
                Stu.setPhone(rs.getString("s.phone"));
                Stu.setSince(rs.getDate("s.since"));
                Stu.setStatus(rs.getBoolean("s.status"));
                students.add(Stu);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(MainSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
