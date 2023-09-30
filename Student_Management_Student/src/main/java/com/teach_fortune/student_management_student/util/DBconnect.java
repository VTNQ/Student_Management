/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teach_fortune.student_management_student.util;

import static com.team_fortune.student_management_student.PrimaryController.conn;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tranp
 */
public class DBconnect {
    public static Connection connectDB(){
    try {
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "Anhthang61@");
        return conn;
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return null;
}
    public static void closeConnection(Connection conn){
        try {
            if(conn!=null && !conn.isClosed()){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
