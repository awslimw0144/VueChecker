package com.taiger.maintester;

import com.taiger.question.LookingAtTheToDoLists;
import com.taiger.task.ClearOff;
import com.taiger.task.LookAtToDos;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.*;

@RunWith(SerenityRunner.class)
public class VUE001_UserShouldBeAbleToPopulateToDos {

    // BS_Data
    String sToDoItem_1 = "I need to wake up at 6.30am";
    String sToDoItem_2 = "I need to brush my teeth";

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
        when(userTaiger).attemptsTo(
                LookAtToDos.andWriteToDoItem(sToDoItem_1),
                LookAtToDos.andWriteToDoItem(sToDoItem_2)
        );
        then(userTaiger).should(
                seeThat("upon visual inspection, the total count is ", LookingAtTheToDoLists.userWillVisuallyCountTheTotalNumberOfToDoItems(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, the to-do item number "+ 1 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sToDoItem_1)),
                seeThat("upon visual inspection, the to-do item number "+ 2 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(2), Matchers.equalTo(sToDoItem_2))
        );
    }
}
