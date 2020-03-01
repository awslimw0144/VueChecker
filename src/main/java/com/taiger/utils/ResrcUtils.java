package com.taiger.utils;

public class ResrcUtils {
    public static String getFilePath(Class className, String fileName){
        return className.getResource("/"+fileName).getPath();
    }
}
