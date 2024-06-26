package com.team_fortune.student_management_teacher.util;

import com.team_fortune.student_management_teacher.HomeController;
import com.team_fortune.student_management_teacher.model.Assignments;
import com.team_fortune.student_management_teacher.model.Exam;
import com.team_fortune.student_management_teacher.model.Student;
import com.team_fortune.student_management_teacher.model.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;

public class getDatabaseToModel {

    public static int id_teacher = 0;
    public static int id_class = 0;
    public static int id_subject = 0;

    public static List<com.team_fortune.student_management_teacher.model.Student> GetTranscriptByClass(String selectedClass, String selectedSubject) {
        List<com.team_fortune.student_management_teacher.model.Student> resulList = new ArrayList<>();
        String query = "Select c.id,a.name,c.link,c.score,c.status From student a "
                + "Join class_subject b ON a.id=b.id_student "
                + "Join transcript c ON b.id_transcipt=c.id "
                + "Join teacher h ON h.id=b.id_teacher "
                + "Join class p ON p.id=b.id_class "
                + "Join subject L ON L.id=b.id_subject "
                + "Where h.username=? And (p.name=? or L.name=?) And (c.score<>0 And c.status<>2)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, selectedClass);
            stmt.setString(3, selectedSubject);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name_Student = rs.getString("name");
                String link = rs.getString("link");
                Float Score = rs.getFloat("score");
                int status = rs.getInt("status");
                resulList.add(new Student(id, status, link, Score, name_Student));
            }
              DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resulList;
    }
    public static List<com.team_fortune.student_management_teacher.model.Exam>DetailStudent(String name_subject,String name_class,int id_exam){
        List<com.team_fortune.student_management_teacher.model.Exam>Arr=new ArrayList<>();
        int id_class=-1;
        int id_subject=-1;
        int id_teacher=-1;
        String query_class="Select id From class Where name=?";
        String query_subject="Select id From subject where name=?";
        String query_teacher="Select id from teacher where username=?";
        String query="Select a.name From student a Join class_subject b ON a.id=b.id_student Where b.id_exam=? And b.id_class=? And b.id_subject=? And id_teacher=?  Group by a.name";
        try {
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmtclass=conn.prepareStatement(query_class);
            stmtclass.setString(1, name_class);
            ResultSet rsclass=stmtclass.executeQuery();
            if(rsclass.next()){
                id_class=rsclass.getInt("id");
            }
            PreparedStatement stmtsubject=conn.prepareStatement(query_subject);
            stmtsubject.setString(1, name_subject);
            ResultSet rssubject=stmtsubject.executeQuery();
            if(rssubject.next()){
                id_subject=rssubject.getInt("id");
            }
            PreparedStatement stmtteacher=conn.prepareStatement(query_teacher);
            stmtteacher.setString(1, MD5.Md5(HomeController.username));
            ResultSet rsteacher=stmtteacher.executeQuery();
            if(rsteacher.next()){
                id_teacher=rsteacher.getInt("id");
            }
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, id_exam);
            stmt.setInt(2, id_class);
            stmt.setInt(3, id_subject);
            stmt.setInt(4, id_teacher);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                
                Arr.add(new Exam(name));
            }
        } catch (Exception e) {
        }
        return Arr;
    }
    public static List<com.team_fortune.student_management_teacher.model.Class> getDataFromDatabaseClass() {
        List<com.team_fortune.student_management_teacher.model.Class>arraylist=new ArrayList<>();
        try {
            String SearchQuery = "select c.id,c.name from class c inner join class_subject cs on cs.id_class=c.id where cs.id_teacher=? group by c.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement s = conn.prepareStatement("select id from teacher where username=?");
            s.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                id_teacher = rs.getInt("id");
            }
            PreparedStatement ps = conn.prepareStatement(SearchQuery);
            ps.setInt(1, id_teacher);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                com.team_fortune.student_management_teacher.model.Class clss = new com.team_fortune.student_management_teacher.model.Class();
                int id=resultSet.getInt("c.id");
                String name=resultSet.getString("c.name");
                arraylist.add(new com.team_fortune.student_management_teacher.model.Class(id, name));
            }
              DBConnection.closeConnection(conn);
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arraylist;

    }

    public static List<com.team_fortune.student_management_teacher.model.Student> GetTranscript() {
        String query = "Select c.id,a.name,c.link,c.score,c.status From student a "
                + "Join class_subject b ON a.id=b.id_student "
                + "Join transcript c ON b.id_transcipt=c.id "
                + "Join teacher h ON h.id=b.id_teacher "
                + "Join class p ON p.id=b.id_class "
                + "Join subject L ON L.id=b.id_subject "
                + "Where h.username=? And (c.score<>0 And c.status<>2)";

        List<com.team_fortune.student_management_teacher.model.Student> arraylist = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name_Student = rs.getString("name");
                String link = rs.getString("link");
                Float Score = rs.getFloat("score");
                int status = rs.getInt("status");
                arraylist.add(new Student(id, status, link, Score, name_Student));
            }
              DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arraylist;
    }

    public static List<com.team_fortune.student_management_teacher.model.Exam> GetExam() {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = new ArrayList<>();
        try {
            String query = "Select d.id as id_Exam,a.name as name_class,b.name as name_subject,d.start,d.end,d.link_exam From class a "
                    + "Join class_subject c ON a.id=c.id_class "
                    + "Join subject b ON b.id=c.id_subject "
                    + "Join exam_schedule d ON d.id=c.id_exam "
                    + "Join teacher e ON e.id=c.id_teacher "
                    + "Where e.username=? And c.id_transcipt IS NULL "+"Group by d.id,a.name,b.name,d.start,d.end,d.link_exam";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_exam = rs.getInt("id_Exam");
                String name_class = rs.getString("name_class");
                String name_subject = rs.getString("name_subject");
                LocalDateTime starTime = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endtime = rs.getTimestamp("end").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String formatdate = starTime.format(formatter);
                String link_exam = rs.getString("link_exam");
                DateTimeFormatter endformat = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String endfor = endtime.format(endformat);
                Array.add(new Exam(id_exam, formatdate, endfor, name_class, name_subject, link_exam));
            }
              DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Array;

    }
     public static List<com.team_fortune.student_management_teacher.model.Exam> GetAllExam( ) {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = new ArrayList<>();
        try {
            String query = "Select d.id as id_Exam,a.name as name_class,b.name as name_subject,d.start,d.end,d.link_exam From class a "
                    + "Join class_subject c ON a.id=c.id_class "
                    + "Join subject b ON b.id=c.id_subject "
                    + "Join exam_schedule d ON d.id=c.id_exam "
                    + "Join teacher e ON e.id=c.id_teacher "
                    + "Where e.username=? And c.id_transcipt IS  NULL "+"Group by d.id,a.name,b.name,d.start,d.end,d.link_exam";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
          
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_exam = rs.getInt("id_Exam");
                String name_class = rs.getString("name_class");
                String name_subject = rs.getString("name_subject");
                LocalDateTime starTime = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endtime = rs.getTimestamp("end").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String formatdate = starTime.format(formatter);
                String link_exam = rs.getString("link_exam");
                DateTimeFormatter endformat = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String endfor = endtime.format(endformat);
                Array.add(new Exam(id_exam, formatdate, endfor, name_class, name_subject, link_exam));
            }
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Array; 
     }
    public static List<com.team_fortune.student_management_teacher.model.Exam> GetExamwithkey(String key ) {
        List<com.team_fortune.student_management_teacher.model.Exam> Array = new ArrayList<>();
        try {
            String query = "Select d.id as id_Exam,a.name as name_class,b.name as name_subject,d.start,d.end,d.link_exam From class a "
                    + "Join class_subject c ON a.id=c.id_class "
                    + "Join subject b ON b.id=c.id_subject "
                    + "Join exam_schedule d ON d.id=c.id_exam "
                    + "Join teacher e ON e.id=c.id_teacher "
                    + "Where e.username=? And c.id_transcipt IS NULL And a.name like ? OR b.name like ? ";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            stmt.setString(2, key);
            stmt.setString(3, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id_exam = rs.getInt("id_Exam");
                String name_class = rs.getString("name_class");
                String name_subject = rs.getString("name_subject");
                LocalDateTime starTime = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime endtime = rs.getTimestamp("end").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String formatdate = starTime.format(formatter);
                String link_exam = rs.getString("link_exam");
                DateTimeFormatter endformat = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String endfor = endtime.format(endformat);
                Array.add(new Exam(id_exam, formatdate, endfor, name_class, name_subject, link_exam));
            }
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Array;

    }


    public static List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubject() {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id  where cs.id_teacher=? group by s.id,s.name,s.session,s.lession_link";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SearchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {

                int id = resultSet.getInt("s.id");
                String name = resultSet.getString("s.name");
                String Session = resultSet.getString("s.session");
                String Session_link = resultSet.getString("s.lession_link");

                subjects.add(new Subject(id, name, Session, Session_link));
            }
            DBConnection.closeConnection(conn);
            return subjects;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<com.team_fortune.student_management_teacher.model.Subject> getFromsubject() {
        List<com.team_fortune.student_management_teacher.model.Subject> arraylist = new ArrayList<>();
        String query = "Select a.id,a.name,a.session,a.lession_link from subject a JOIN class_subject b On a.id=b.id_subject Join teacher c ON c.id=b.id_teacher Where c.username=? Group by  a.id,a.name,a.session,a.lession_link";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String session = rs.getString("session");
                String lession_link = rs.getString("lession_link");

                arraylist.add(new Subject(id, name, session, lession_link));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arraylist;
    }
    public static List<com.team_fortune.student_management_teacher.model.Assignments>Assignmentkey(String key){
                List<com.team_fortune.student_management_teacher.model.Assignments>array=new ArrayList<>();
                String query = "SELECT  c.id AS id,a.name AS subject_name, b.name AS class_name, c.link FROM subject a "
                + "INNER JOIN class_subject d ON a.id = d.id_subject "
                + "INNER JOIN class b ON b.id = d.id_class "
                + "INNER JOIN assignments c ON c.id = d.id_assignments "
                + "INNER JOIN teacher f ON f.id = d.id_teacher "
                + "WHERE f.username = ? And b.name like ? " + "Group by c.id,a.name,b.name,c.link";
           try {
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1,MD5.Md5(HomeController.username));
            stmt.setString(2, key);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name_subject=rs.getString("subject_name");
                String name_class=rs.getString("class_name");
                String link=rs.getString("link");
                array.add(new Assignments(name_subject, name_class, link, id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return array;
    }
    public static List<com.team_fortune.student_management_teacher.model.Class>getDataFromDatabaseClasswithkey(String search){
        List<com.team_fortune.student_management_teacher.model.Class>array=new ArrayList<>();
        try {
            String query="Select a.id,a.name From class a Join class_subject b ON a.id=b.id_class Join teacher c ON b.id_teacher=c.id where a.name like ? And c.username=? Group by a.id,a.name";
            Connection conn=DBConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1, search);
            stmt.setString(2, MD5.Md5(HomeController.username));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                array.add(new com.team_fortune.student_management_teacher.model.Class(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public static List<com.team_fortune.student_management_teacher.model.Subject> getDataFromDatabaseSubjectWithKey(String key) {
        try {
            String SearchQuery = "select s.id,s.name,s.session,s.lession_link from subject s inner join class_subject cs on cs.id_subject=s.id where cs.id_teacher=? and s.name like ? group by s.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SearchQuery);
            preparedStatement.setInt(1, id_teacher);
           
            preparedStatement.setString(2, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Subject> subjects = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("s.id");
                String name = resultSet.getString("s.name");
                String Session = resultSet.getString("s.session");
                String Session_link = resultSet.getString("s.lession_link");
                subjects.add(new Subject(id, name, Session, Session_link));
            }
            DBConnection.closeConnection(conn);
            return subjects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<com.team_fortune.student_management_teacher.model.Student> Request_Class() {
        List<com.team_fortune.student_management_teacher.model.Student> Arraylist = new ArrayList<>();
        try {

            String query = "Select a.id as id_student,c.id as id_class,a.name as name_Student,c.name as name_class,b.Active From student a Join class_subject b ON a.id=b.id_student Join class c ON b.id_class=c.id Join teacher d ON b.id_teacher=d.id Where d.username=? ";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_student = rs.getString("name_Student");
                String name_class = rs.getString("name_class");
                int id_Student = rs.getInt("id_student");
                int Active = rs.getInt("Active");
                int id_class = rs.getInt("id_class");
                Arraylist.add(new Student(id_Student, id_class, name_student, name_class, Active));

            }
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Arraylist;
    }

    public List<com.team_fortune.student_management_teacher.model.Class_Subject> getDataFromDatabaseClassSubject() {
        try {
            String searchQuery = "select cs.id_class,cs.id_subject,cs.id_teacher,cs.id_student,cs.id_assignments,cs.id_exam from class_subject cs where cs.id_teacher=? Group by cs.id_class,cs.id_subject,cs.id_teacher,cs.id_student,cs.id_assignments,cs.id_exam";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class_Subject> classs_subjects = new ArrayList<>();
            while (resultSet.next()) {
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

    public List<com.team_fortune.student_management_teacher.model.Class> getAllDataFromDataBaseclass() {
        try {
            String searchQuery = "Select name From class";
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(searchQuery);
            ResultSet rs = stmt.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Class> Class = new ArrayList<>();

            while (rs.next()) {
                com.team_fortune.student_management_teacher.model.Class classes = new com.team_fortune.student_management_teacher.model.Class();

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

    public static List<com.team_fortune.student_management_teacher.model.Class> getDataFromDatabaseClassWithSubject(String selectSubject) {
        try {
            String SearchQuery = "select c.id,c.name from class c inner join class_subject cs on cs.id_class=c.id inner join teacher t on t.id=cs.id_teacher inner join subject s on s.id=cs.id_subject where t.id=? and s.name=? group by c.id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SearchQuery);
            ps.setInt(1, id_teacher);
            ps.setString(2, selectSubject);
            ResultSet resultSet = ps.executeQuery();
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
            String query = "SELECT  c.id AS id,a.name AS subject_name, b.name AS class_name, c.link as link_ass FROM subject a "
                    + "INNER JOIN class_subject d ON a.id = d.id_subject "
                    + "INNER JOIN class b ON b.id = d.id_class "
                    + "INNER JOIN assignments c ON c.id = d.id_assignments "
                    + "INNER JOIN teacher f ON f.id = d.id_teacher "
                    + "WHERE f.username = ? " + "Group by c.id,a.name,b.name,c.link";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id_class = result.getInt("id");
                String name_subject = result.getString("subject_name");
                String class_name = result.getString("class_name");
                String link = result.getString("link_ass");

                AssignmentList.add(new Assignments(name_subject, class_name, link, id_class));
            }
            DBConnection.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return AssignmentList;
    }

    public List<com.team_fortune.student_management_teacher.model.Student> getDataFromDatabaseStudent() {
        try {
            String searchQuery = "select s.id,s.name,s.phone,s.since,s.status from student s inner join class_subject cs on cs.id_student=s.id inner join teacher t on cs.id_teacher=t.id where t.id=? group by s.name";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet rs = preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Student> students = new ArrayList<>();
            while (rs.next()) {
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

    public static List<com.team_fortune.student_management_teacher.model.Assignments> WatchAssStudent() {
        List<com.team_fortune.student_management_teacher.model.Assignments> ExAssign = new ArrayList<>();
        String query = "Select b.id as id_solution,d.name as name_subject,a.name as name_student,b.link as link,b.status as status,e.name as name_class,b.reason  From student a Join class_subject c ON a.id=c.id_student "
                + "Join subject d ON d.id=c.id_subject JOIN class e ON e.id=c.id_class JOIN assignments f ON f.id=c.id_assignments "
                + "Join solution b ON b.id=c.id_solution " + "Join teacher t1 ON t1.id=c.id_teacher " + "Where t1.username=? And c.id_assignments IS NOT NULL And c.id_solution is NOT NULL "+"Group by b.id,d.name,a.name,b.link,e.name";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, MD5.Md5(HomeController.username));

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String name_student = result.getString("name_student");
                int id = result.getInt("id_solution");
                String Link = result.getString("link");
                int Status = result.getInt("status");
                String name_subject = result.getString("name_subject");
                String name_class = result.getString("name_class");
                String reason=result.getString("reason");
                ExAssign.add(new Assignments(id, name_student, Link, Status, name_subject, name_class,reason));
            }
            DBConnection.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExAssign;
    }

    public static List<com.team_fortune.student_management_teacher.model.Student> getDataFromDatabaseStudentWithSubject(String selectSubject) {
        List<com.team_fortune.student_management_teacher.model.Student> students = new ArrayList<>();
        try {
            String searchQuery = "select s.id,s.name,s.username,s.password,s.phone,s.since,s.status from student s  join class_subject cs on cs.id_student=s.id  join subject sub on sub.id=cs.id_subject JOIN teacher t ON t.id=cs.id_teacher where t.id=? and sub.name=? group by s.name";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(searchQuery);
            System.out.println(id_teacher);
            preparedStatement.setInt(1, id_teacher);
            preparedStatement.setString(2, selectSubject);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                com.team_fortune.student_management_teacher.model.Student Stu = new com.team_fortune.student_management_teacher.model.Student();
                int id = rs.getInt("s.id");
                String name_student = rs.getString("s.name");
                String phone = rs.getString("s.phone");
                String username = MD5.Md5(rs.getString("username"));
                String password = MD5.Md5(rs.getString("password"));
                Boolean status = rs.getBoolean("s.status");
                students.add(new Student(id, name_student, username, phone, password, status));
            }
            DBConnection.closeConnection(conn);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }

    public List<com.team_fortune.student_management_teacher.model.Teacher> getDataFromDatabaseTeacher() {
        try {
            String searchQuery = "select t.id,t.name,t.phone,t.since,t.status from teacher t inner join class_subject cs on cs.id_teacher=t.id inner where t.id=? group by t.name";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(searchQuery);
            preparedStatement.setInt(1, id_teacher);
            ResultSet rs = preparedStatement.executeQuery();
            List<com.team_fortune.student_management_teacher.model.Teacher> Teachers = new ArrayList<>();
            while (rs.next()) {
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
