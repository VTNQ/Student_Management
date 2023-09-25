package com.team_fortune.student_management_admin;

import com.team_fortune.student_management_admin.util.DBConnection;
import com.team_fortune.student_management_admin.util.MD5;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecondaryController {

    @FXML
    private MFXDatePicker SinceTeacher;

    @FXML
    private MFXTextField nameTeacher;

    @FXML
    private MFXTextField phoneTeacher;

    @FXML
    private MFXTextField usernameTeacher;

    @FXML
    private MFXTextField emailTeacher;

    @FXML
    void addTeacher(MouseEvent event) {
        String username = MD5.Md5(usernameTeacher.getText());
        System.out.println(username);
        try {
            String insertQuery = "insert into teacher(name,username,phone,email,since,password,status) values(?,?,?,?,?,?,?)";
            DateTimeFormatter local = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String since = SinceTeacher.getValue().format(local);
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, nameTeacher.getText());
            ps.setString(2, username);
            ps.setString(3, phoneTeacher.getText());
            ps.setString(4, emailTeacher.getText());
            ps.setString(5, since);
            ps.setString(6, username);
            ps.setInt(7, 0);

            sendConfirmationEmail(emailTeacher.getText());

            ps.executeUpdate();
            usernameTeacher.clear();
            nameTeacher.clear();
            phoneTeacher.clear();
            SinceTeacher.clear();
        } catch (SQLException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendConfirmationEmail(String email) {

        String username = "votannamquoc@gmail.com";
        String password = "fjez jcln hjst fxts";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Thông tin tài khoản");
            String body = "Xin chào,\n\nUsername:" + usernameTeacher.getText() + "\nPassword:" + usernameTeacher.getText();
            message.setText(body);
            Transport.send(message);
            System.out.println("Email đã được gửi thành công.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
