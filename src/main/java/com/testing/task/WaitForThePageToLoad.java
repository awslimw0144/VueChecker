package com.testing.task;

import com.testing.page.PageOfEsplanade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class WaitForThePageToLoad implements Task {

    private String sSource;

    public WaitForThePageToLoad(String sSource){
        this.sSource = sSource;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(

        );
    }
    public static WaitForThePageToLoad of(String sSource){
        return Tasks.instrumented(WaitForThePageToLoad.class, sSource);
    }
}
