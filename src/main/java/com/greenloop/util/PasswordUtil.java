package com.greenloop.util;

import java.security.*;
import java.util.Base64;

public class PasswordUtil {
    public static String genSalt(){
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    public static String hash(String plain, String salt){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashed = md.digest(plain.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashed);
        }catch(Exception e){ throw new RuntimeException(e); }
    }
}
