package com.team_fortune.student_management_teacher.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {

    public static String Md5(String input) {
        try {
            String base64Encoding = Base64.getEncoder().encodeToString(input.getBytes());
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.update(base64Encoding.getBytes());
            byte[] digest = ms.digest();
            StringBuilder sb=new StringBuilder();
            for(byte b:digest){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
