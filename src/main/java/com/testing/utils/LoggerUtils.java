package com.testing.utils;

import java.util.logging.Logger;

public class LoggerUtils {
    private static final Logger LOGGER = Logger.getLogger(LoggerUtils.class.getName());
    public static void printInfo(String sInfo){
        LOGGER.info(sInfo);
    }
}
