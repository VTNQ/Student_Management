/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.dao;

import com.teach_fortune.student_management_student.util.DBconnect;
import com.team_fortune.student_management_student.PrimaryController;
import com.team_fortune.student_management_student.models.classmodel;
import com.team_fortune.student_management_student.models.modelExample;
import com.team_fortune.student_management_student.models.modelScore;
import com.team_fortune.student_management_student.models.modelWord;
import com.team_fortune.student_management_student.models.modelcalender;
import com.team_fortune.student_management_student.models.modelsearch;
import com.team_fortune.student_management_student.models.modelsubject;
import com.team_fortune.student_management_student.models.myclass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author tranp
 */
public class daodb {

    public static int id;
    public static String link;
    public static String link_examp;
    public static LocalDateTime starTime;
    public static LocalDateTime endtime;
    public static String endfor;
    public static String linkDisplay;

    public static List<com.team_fortune.student_management_student.models.modelsearch> searchdata(String searchItem) {
        List<com.team_fortune.student_management_student.models.modelsearch> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "SELECT t1.id,tc.name,st.name as name_subject,sc.name as name_class FROM student t1 "
                + "JOIN class_subject t2 ON t1.id = t2.id_student "
                + "JOIN class sc ON t2.id_class = sc.id "
                + "JOIN subject st ON t2.id_subject = st.id "
                + "JOIN teacher tc ON t2.id_teacher = tc.id "
                + "WHERE t1.id = ? AND st.name LIKE ? And t2.Active=1 "
                +"Group by t1.id,tc.name,st.name,sc.name";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            stmt.setString(2, "%" + searchItem + "%");
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String name_teacher = result.getString("name");
                String name_subject = result.getString("name_subject");
                String name_class = result.getString("name_class");
                resultList.add(new modelsearch(name_teacher, name_subject, name_class));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<com.team_fortune.student_management_student.models.modelWord> worldsubject() {
        List<com.team_fortune.student_management_student.models.modelWord> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "Select t1.name as name_subject,t3.name,t1.lession_link,t6.name as name_class From subject t1 "
                + "Join class_subject t2 ON t1.id=t2.id_subject "
                + "JOIN teacher t3 ON t2.id_teacher=t3.id "
                + "JOIN class t6 ON t2.id_class=t6.id "
                + "JOIN student t7 ON t2.id_student=t7.id "
                + "Where t7.id=? And t2.Active=1 "
                +"Group by t1.name,t3.name,t1.lession_link,t6.name";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_subject = rs.getString("name_subject");
                String name_teacher = rs.getString("name");
                String link = rs.getString("lession_link");
                String name_class = rs.getString("name_class");
                resultList.add(new modelWord(name_subject, name_teacher, link, name_class));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<com.team_fortune.student_management_student.models.modelcalender> SelectCalender() {
        List<com.team_fortune.student_management_student.models.modelcalender> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "SELECT t4.id as id, t1.name as name_subject, t3.name as name_class, t4.start, t4.link_exam, t4.end "
                + "FROM subject t1 "
                + "JOIN class_subject t5 ON t1.id = t5.id_subject "
                + "JOIN class t3 ON t5.id_class = t3.id "
                + "JOIN student t6 ON t5.id_student = t6.id "
                + "JOIN exam_schedule t4 ON t5.id_exam = t4.id "
                + "JOIN teacher t2 ON t2.id = t5.id_teacher "
                
                + "WHERE t6.id = ?  AND t4.end > NOW()";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String subject = result.getString("name_subject");
                int id = result.getInt("id");
                String className = result.getString("name_class");
                link_examp = result.getString("link_exam");
                starTime = result.getTimestamp("start").toLocalDateTime();
                endtime = result.getTimestamp("end").toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                String formatdate = starTime.format(formatter);

                DateTimeFormatter endformat = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm", new Locale("vi", "VN"));
                endfor = endtime.format(endformat);
                linkDisplay = result.getString("link_exam");
                resultList.add(new modelcalender(id, subject, className, formatdate, linkDisplay, endfor));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
    public static List<com.team_fortune.student_management_student.models.classmodel>joined_class(){
        List<com.team_fortune.student_management_student.models.classmodel>Joined_Class=new ArrayList<>();
        Connection conn=DBconnect.connectDB();
        String query="Select a.name,a.id,b.Active From class a JOIN class_Subject b ON a.id=b.id_class Where b.id_student=?";
        try {
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                int Active=rs.getInt("Active");
               Joined_Class.add(new classmodel(id,name,Active));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Joined_Class;
    }
    public static List<com.team_fortune.student_management_student.models.myclass> Myclass() {
        List<com.team_fortune.student_management_student.models.myclass> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "Select a.name,a.id From class a JOIN class_Subject b ON a.id=b.id_class Where b.id_student <> ? OR b.id_student IS Null";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                resultList.add(new myclass(name, id));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<com.team_fortune.student_management_student.models.modelExample> ListExample() {
        List<com.team_fortune.student_management_student.models.modelExample> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "Select t1.name as name_subject,t3.name,t4.link,t5.name as name_class,t4.id as id_ass From subject t1 "
                + "JOIN class_subject t2 ON t1.id=t2.id_subject "
                + "JOIN teacher t3 ON t2.id_teacher=t3.id "
                + "Join assignments t4 On t4.id=t2.id_assignments "
                + "Join class t5 ON t5.id=t2.id_class "
                + "Join student t6 ON t6.id=t2.id_student "
                + "Where t6.id = ? And t2.Active=1";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_subject = rs.getString("name_subject");
                String name = rs.getString("name");
                id = rs.getInt("id_ass");
                link = rs.getString("link");
                String name_class = rs.getString("name_class");
                resultList.add(new modelExample(name_subject, name, link, name_class, id));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static List<com.team_fortune.student_management_student.models.classmodel> Detailclass(int id_class) {
        List<com.team_fortune.student_management_student.models.classmodel> result = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "Select a.name as name_subject,b.name as name_teacher From subject a Join class_subject c ON a.id=c.id_subject Join teacher b ON b.id=c.id_teacher Where c.id_class=? Group by a.name,b.name";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_class);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_subject = rs.getString("name_subject");
                String name_teacher = rs.getString("name_teacher");
                result.add(new classmodel(name_subject, name_teacher));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<com.team_fortune.student_management_student.models.modelsubject> Scorestudent() {
        List<com.team_fortune.student_management_student.models.modelsubject> result = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "Select a.name as Name_subject,b.score,c.name as name_class,b.status From subject a "
                + "Join class_subject e ON a.id=e.id_subject "
                + "Join exam_schedule f ON f.id=e.id_exam "
                + "JOIN transcript b ON b.id=e.id_transcipt "
                + "JOIN class c ON c.id=e.id_class "
                + "Join student g ON g.id=e.id_student "
                + "Where g.id=? And e.Active=1";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name_Subject = rs.getString("Name_subject");
                float score = rs.getFloat("score");
                String name_class = rs.getString("name_class");
                int status = rs.getInt("status");
                
                result.add(new modelsubject(name_Subject, score, name_class, status));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<com.team_fortune.student_management_student.models.modelsearch> Display_subject() {
        List<com.team_fortune.student_management_student.models.modelsearch> resultList = new ArrayList<>();
        Connection conn = DBconnect.connectDB();
        String query = "SELECT t1.id,tc.name,st.name as name_subject,sc.name as name_class FROM student t1 "
                + "JOIN class_subject t2 ON t1.id = t2.id_student "
                + "JOIN class sc ON t2.id_class = sc.id "
                + "JOIN subject st ON t2.id_subject = st.id "
                + "JOIN teacher tc ON t2.id_teacher = tc.id "
                + "WHERE t1.id = ? And t2.Active=1 "
                +"Group by t1.id,tc.name,st.name,sc.name";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, PrimaryController.loggedInStudentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String name_teacher = result.getString("name");
                String name_subject = result.getString("name_subject");
                String name_class = result.getString("name_class");
                resultList.add(new modelsearch(name_teacher, name_subject, name_class));
            }
            DBconnect.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
