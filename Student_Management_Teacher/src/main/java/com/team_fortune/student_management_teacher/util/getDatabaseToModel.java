package com.team_fortune.student_management_teacher.util;

import com.team_fortune.student_management_teacher.HomeController;
import com.team_fortune.student_management_teacher.model.Assignments;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class getDatabaseToModel {
    
    public static int id_teacher = 0;
    public static int id_class = 0;
    public static int id_subject = 0;
    
    public List<com.team_fortune.student_management_teacher.model.Class> getDataFromDatabaseClass() {
        try {
            String SearchQuery = "select c.id,c.name from class c inner join class_subject cs on cs.id_class=c.id where cs.id_teacher=? group by c.id";
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
            ex.printStackTrace();
        }
        return null;
       
    }

    public List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubject() {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id where cs.id_teacher=? group by s.id";
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
             DBConnection.closeConnection(conn);
            return subjects;
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubjectWithKey(String key) {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id where cs.id_teacher=? and s.name like ? group by s.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement =conn.prepareStatement(SearchQuery);
            preparedStatement.setInt(1, id_teacher);
            System.out.println(id_teacher);
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
            DBConnection.closeConnection(conn);
            return subjects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
   
    public List<com.team_fortune.student_management_teacher.model.Class_Subject>getDataFromDatabaseClassSubject(){
        try {
            String searchQuery="select cs.id_class,cs.id_subject,cs.id_teacher,cs.id_student,cs.id_assignments,cs.id_exam from class_subject cs where cs.id_teacher=?";
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
            DBConnection.closeConnection(conn);
            return classs_subjects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    public List<com.team_fortune.student_management_teacher.model.Class> getAllDataFromDataBaseclass(){
        try {
            String searchQuery="Select name From class";
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(searchQuery);
            ResultSet rs=stmt.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class> Class=new ArrayList<>();
            
            while(rs.next()){
                com.team_fortune.student_management_teacher.model.Class classes=new com.team_fortune.student_management_teacher.model.Class();
              
                classes.setName("name");
                Class.add(classes);
            }
            DBConnection.closeConnection(conn);
            return Class;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<com.team_fortune.student_management_teacher.model.Class> getDataFromDatabaseClassWithSubject(String selectSubject) {
        try {
            String SearchQuery = "select c.id,c.name from class c inner join class_subject cs on cs.id_class=c.id inner join teacher t on t.id=cs.id_teacher inner join subject s on s.id=cs.id_subject where t.id=? and s.name=? group by c.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(SearchQuery);
            ps.setInt(1, id_teacher);
            ps.setString(2, selectSubject);
            ResultSet resultSet=ps.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class> classes = new ArrayList<>();
            while (resultSet.next()) {
                com.team_fortune.student_management_teacher.model.Class clss = new com.team_fortune.student_management_teacher.model.Class();
                clss.setId(resultSet.getInt("c.id"));
                clss.setName(resultSet.getString("c.name"));
                classes.add(clss);
            }
            DBConnection.closeConnection(conn);
            return classes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
   public static List<com.team_fortune.student_management_teacher.model.Assignments> getAssignments() {
        List<com.team_fortune.student_management_teacher.model.Assignments> AssignmentList = new ArrayList<>();
    try {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT  c.id AS id,a.name AS subject_name, b.name AS class_name, c.link, c.status FROM subject a " +
                       "INNER JOIN class_subject d ON a.id = d.id_subject " +
                       "INNER JOIN class b ON b.id = d.id_class " +
                       "INNER JOIN assignments c ON c.id = d.id_assignments " +
                       "INNER JOIN teacher f ON f.id = d.id_teacher " +
                       "WHERE f.username = ? "+"Group by c.id,a.name,b.name,c.link,c.status";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, MD5.Md5(HomeController.username));

        ResultSet result = stmt.executeQuery();
       

        while (result.next()) {
           int id_class=result.getInt("id");
       String name_subject=result.getString("subject_name");
       String class_name= result.getString("class_name");
        String link=result.getString("link");
          Boolean status=result.getBoolean("status");
            AssignmentList.add(new Assignments(name_subject, class_name, link, status, id_class));
        }
        DBConnection.closeConnection(conn);
       
    } catch (Exception e) {
        e.printStackTrace();
    }

    return AssignmentList;
}
    
    public List<com.team_fortune.student_management_teacher.model.Student>getDataFromDatabaseStudent(){
        try {
            String searchQuery="select s.id,s.name,s.phone,s.since,s.status from student s inner join class_subject cs on cs.id_student=s.id inner join teacher t on cs.id_teacher=t.id where t.id=? group by s.name";
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
            DBConnection.closeConnection(conn);
            return students;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
   public static List<com.team_fortune.student_management_teacher.model.Assignments>WatchAssStudent(){
       List<com.team_fortune.student_management_teacher.model.Assignments>ExAssign=new ArrayList<>();
       String query="Select b.id_assignments,d.name as name_subject,a.name as name_student,b.link as link,b.status as status,e.name as name_class  From student a Join class_subject c ON a.id=c.id_student "
               + "Join subject d ON d.id=c.id_subject JOIN class e ON e.id=c.id_class JOIN assignments f ON f.id=c.id_assignments "
               + "Join solution b ON f.id=b.id_assignments "+"Join teacher t1 ON t1.id=c.id_teacher "+"Where t1.username=?";
       try {
           Connection conn=DBConnection.getConnection();
           PreparedStatement stmt=conn.prepareStatement(query);
           stmt.setString(1, MD5.Md5(HomeController.username));
           ResultSet result=stmt.executeQuery();
           while(result.next()){
               String name_student=result.getString("name_student");
               int id=result.getInt("id_assignments");
               String Link=result.getString("link");
               int Status=result.getInt("status");
               String name_subject=result.getString("name_subject");
               String name_class=result.getString("name_class");
               ExAssign.add(new Assignments(id,name_student, Link, Status,name_class,name_subject));
           }
           DBConnection.closeConnection(conn);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return ExAssign;
   }
    public List<com.team_fortune.student_management_teacher.model.Student>getDataFromDatabaseStudentWithSubject(String selectSubject){
        try {
            String searchQuery="select s.id,s.name,s.phone,s.since,s.status from student s inner join class_subject cs on cs.id_student=s.id inner join subject sub on sub.id=cs.id_subject where t.id=? and sub.name=? group by s.name";
            Connection conn=DBConnection.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            preparedStatement.setString(2, selectSubject);
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
            DBConnection.closeConnection(conn);
            return students;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<com.team_fortune.student_management_teacher.model.Teacher>getDataFromDatabaseTeacher(){
        try {
            String searchQuery="select t.id,t.name,t.phone,t.since,t.status from teacher t inner join class_subject cs on cs.id_teacher=t.id inner where t.id=? group by t.name";
            Connection conn=DBConnection.getConnection();
            PreparedStatement preparedStatement=conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet rs =preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Teacher> Teachers=new ArrayList<>();
            while(rs.next()){
                com.team_fortune.student_management_teacher.model.Teacher Tea = new com.team_fortune.student_management_teacher.model.Teacher();
                Tea.setId(rs.getInt("t.id"));
                Tea.setName(rs.getString("t.name"));
                Tea.setPhone(rs.getString("t.phone"));
                Tea.setSince(rs.getDate("t.since"));
                Tea.setStatus(rs.getBoolean("t.status"));
                Teachers.add(Tea);
            }
            return Teachers;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
