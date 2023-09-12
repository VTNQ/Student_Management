package com.team_fortune.student_management_teacher.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {

    public static byte[] Md5(String input) {
        try {
            String base64Encoding = Base64.getEncoder().encodeToString(input.getBytes());
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.update(base64Encoding.getBytes());
            byte[] digest = ms.digest();
            return digest;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
