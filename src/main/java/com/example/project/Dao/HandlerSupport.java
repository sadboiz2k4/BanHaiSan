package com.example.project.Dao;

import java.util.Random;

public class HandlerSupport {
    public static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }

    public static String encrypt(String s) {
        String re = "";
        byte [] byteString = s.getBytes();
        for (byte b : byteString) {
            int intValue = b & 0xFF;
            re+=intValue;
        }
        return re;
    }

    public static void main(String[] args) {
       String s= encrypt("123");
        System.out.println(s);
        if(Integer.parseInt(String.valueOf(s.charAt(0))) >5){
            System.out.println("true");
        }else{
            System.out.println("false");
        }

    }
}
