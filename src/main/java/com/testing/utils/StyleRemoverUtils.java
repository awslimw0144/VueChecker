package com.testing.utils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StyleRemoverUtils {
    public static String with(String str){
        String[] newStrTargets = str.replace("><", "> || <").split(Pattern.quote(" || "));
        StringBuffer buffer = new StringBuffer();
        Arrays.stream(newStrTargets).forEach(tag->{
            String newStr;
            if(tag.contains(" style=")){
                newStr = removeStyle(tag);
            } else {
                newStr = tag;
            }
            buffer.append(newStr);
        });
        return buffer.toString();
    }

    private static String removeStyle(String str){
        String replaced = "";
        int startIndex = str.indexOf(" style=");
        int endIndex = str.indexOf(">");
        return str.replace(str.substring(startIndex, endIndex),replaced);
    }
}
