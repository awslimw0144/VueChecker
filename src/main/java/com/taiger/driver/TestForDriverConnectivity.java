package com.taiger.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestForDriverConnectivity {
    // Test that the chromedriver extension is working
    public static void main(String... args){
        String fileChrmDrv = "C:\\Users\\angel\\IdeaProjects\\VueChecker\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", fileChrmDrv);
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.youtube.com");
        webDriver.quit();
    }
}
