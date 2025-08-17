package com.greenloop.util;

public class ValidationUtil {
    public static boolean isEmail(String s){
        return s!=null && s.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$");
    }
}
