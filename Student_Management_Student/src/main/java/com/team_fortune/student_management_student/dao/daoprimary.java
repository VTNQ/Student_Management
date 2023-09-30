/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team_fortune.student_management_student.dao;

import com.team_fortune.student_management_student.PrimaryController;
import com.team_fortune.student_management_student.models.modelprimary;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tranp
 */

public  class daoprimary {
    private String urlConnect="jdbc:mysql://localhost:3306/student_management";
    private String username="root";
    private String password="Anhthang61@";
   public boolean  register(modelprimary models){
       boolean statusExcute=false;
       try {
           Connection conn;
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn=DriverManager.getConnection(urlConnect,username,password);
           PreparedStatement ps=conn.prepareStatement("insert into student(name,username,email,phone,since,password,status) values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
           ps.setString(1, models.getName());
           ps.setString(2, models.getUsername());
           ps.setString(3, models.getEmail());
           ps.setInt(4, models.getPhone());
           ps.setDate(5, models.getSince());
           ps.setString(6, models.getPassword());
           ps.setBoolean(7, models.getStatus());
           statusExcute=ps.execute();
           ResultSet rs=ps.getGeneratedKeys();
           while(rs.next()){
               models.setId(rs.getInt("id"));
           }
            rs.close(); 

            ps.close(); 
       } catch (Exception e) {
          e.printStackTrace();
       }
       return statusExcute;
   }
}