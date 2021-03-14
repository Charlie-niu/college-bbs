package com.charlie.college_bbs.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderUtil {

    private static BCryptPasswordEncoder passwordEncoder;

    public static BCryptPasswordEncoder passwordEncoderHandler() {
        if (passwordEncoder == null) {
            return new BCryptPasswordEncoder();
        } else {
            return passwordEncoder;
        }
    }

    public static String encryptPassword(String password) {
        if (passwordEncoder == null) {
            return new BCryptPasswordEncoder().encode(password);
        } else {
            return passwordEncoder.encode(password);
        }
    }


}
