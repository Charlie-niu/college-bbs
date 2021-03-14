package com.charlie.college_bbs.utils;

import java.util.Random;

public class GenRandomString {
    public static final String All = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String Character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String Number = "0123456789";


    public static String generater(int length, String type) //参数为返回随机数的长度
    {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        if (type.equals(GenRandomString.All)){
            for (int i = 0; i < length; i++) {
                sb.append(All.charAt(random.nextInt(All.length())));
            }
        }

        if (type.equals(GenRandomString.Character)){
            for (int i = 0; i < length; i++) {
                sb.append(Character.charAt(random.nextInt(Character.length())));
            }
        }
        if (type.equals(GenRandomString.Number)){
            for (int i = 0; i < length; i++) {
                sb.append(Number.charAt(random.nextInt(Number.length())));
            }
        }

        return sb.toString();
    }
}
