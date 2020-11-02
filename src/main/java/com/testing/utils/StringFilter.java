package com.testing.utils;

public class StringFilter {
    public static int filterOutIntegers(String sRaw){
        StringBuilder sb = new StringBuilder();
        char[] cArr = sRaw.toCharArray();
        for(char c : cArr){
            if(Character.isDigit(c)) sb.append(c);
        }
        if(sb.toString().isEmpty()) {
            if(sRaw.toUpperCase().contains("FREE") || sRaw.toUpperCase().contains("LIVED STREAM")){
                return 0;
            }
            else return -1;
        }
        return Integer.parseInt(sb.toString());
    }
}
