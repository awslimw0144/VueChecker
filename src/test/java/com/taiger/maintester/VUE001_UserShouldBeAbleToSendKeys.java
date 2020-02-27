package com.taiger.maintester;

import com.taiger.task.ClearOff;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;

@RunWith(SerenityRunner.class)
public class VUE001_UserShouldBeAbleToSendKeys {

    // Actor
    Actor userTaiger = Actor.named("userTaiger");

    @Managed(driver="chrome", uniqueSession = true)
    public WebDriver hisBrowser;

    @Before
    public void userCanBrowseTheWeb(){
        userTaiger.can(
                BrowseTheWeb.with(hisBrowser)
        );
    }

    @Test
    public void user_Should_Be_Able_To_Write_A_Task_To_VUE(){
        givenThat(userTaiger).wasAbleTo(
                ClearOff.allPreExistingTasks()
        );
//        when(userTaiger).attemptsTo(
//                LookAtToDos.andWriteToDoItem("I need to wake up at 6.30am"),
//                LookAtToDos.andWriteToDoItem("I need to brush my teeth")
//        );
//        then(hisBrowser).should(
//                seeThat()
//        );
    }
}
