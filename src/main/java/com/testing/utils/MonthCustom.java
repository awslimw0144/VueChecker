package com.testing.utils;

public class MonthCustom {
    public static String getMonthNum(String monthName){
        switch (monthName){
            case "JANUARY": case "January": case "january": case "JAN": case "Jan": case "jan":
                return "01";
            case "FEBRUARY": case "February": case "february": case "FEB": case "Feb": case "feb":
                return "02";
            case "MARCH": case "March": case "march": case "MAR": case "Mar": case "mar":
                return "03";
            case "APRIL": case "April": case "april": case "APR": case "Apr": case "apr":
                return "04";
            case "MAY": case "May": case "may":
                return "05";
            case "JUNE": case "June": case "june": case "JUN": case "Jun": case "jun":
                return "06";
            case "JULY": case "July": case "july": case "JUL": case "Jul": case "jul":
                return "07";
            case "AUGUST": case "August": case "august": case "AUG": case "Aug": case "aug":
                return "08";
            case "SEPTEMBER": case "September": case "september": case "SEP": case "Sep": case "sep":
                return "09";
            case "OCTOBER": case "October": case "october": case "OCT": case "Oct": case "oct":
                return "10";
            case "NOVEMBER": case "November": case "november": case "NOV": case "Nov": case "nov":
                return "11";
            case "DECEMBER": case "December": case "december": case "DEC": case "Dec": case "dec":
                return "12";
            default:
                throw new IllegalStateException("Unexpected value: " + monthName);
        }

//        ONLY COMPATIBLE FOR JAVA 14 onwards, and Serenity-Rest API currently is not compatible with the latest java version;
//        return switch(monthName){
//            case "JANUARY", "January", "january", "JAN","Jan","jan"             -> "01";
//            case "FEBRUARY", "February", "february", "FEB","Feb","feb"          -> "02";
//            case "MARCH", "March", "march", "MAR", "Mar", "mar"                 -> "03";
//            case "APRIL", "April", "april", "APR", "Apr", "apr"                 -> "04";
//            case "MAY", "May", "may"                                            -> "05";
//            case "JUNE", "June", "june", "JUN", "Jun", "jun"                    -> "06";
//            case "JULY", "July", "july", "JUL", "Jul", "jul"                    -> "07";
//            case "AUGUST", "August", "august", "AUG", "Aug", "aug"              -> "08";
//            case "SEPTEMBER", "September", "september", "SEP", "Sep", "sep"     -> "09";
//            case "OCTOBER", "October", "october", "OCT", "Oct", "oct"           -> "10";
//            case "NOVEMBER", "November", "november", "NOV", "Nov", "nov"        -> "11";
//            case "DECEMBER", "December",  "december", "DEC", "Dec", "dec"       -> "12";
//            default -> throw new IllegalStateException("Unexpected value: " + monthName);
//        };
    }
}
