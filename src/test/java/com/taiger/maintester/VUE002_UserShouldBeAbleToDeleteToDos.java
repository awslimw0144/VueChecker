package com.taiger.maintester;

import com.taiger.question.LookingAtTheToDoLists;
import com.taiger.task.ReviewCurrentToDoItems;
import com.taiger.task.ThinkOfToDoItem;
import com.taiger.task.NavigateToWebPage;
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
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

@RunWith(SerenityRunner.class)
public class VUE002_UserShouldBeAbleToDeleteToDos {
    // BS_Data
//    String sToDoItem = BS_Data
    String sToDoItem_1 = "I need to wake up at 6.30am";
    String sToDoItem_2 = "I need to brush my teeth";
    int intExpectedTotalCount = Integer.parseInt("1");

    // Actor
    Actor userTaiger = Actor.named("userTaiger");

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver hisBrowser;

    @Before
    public void userCanBrowseTheWeb() {
        userTaiger.can(
                BrowseTheWeb.with(hisBrowser)
        );
    }

    @Test
    public void user_Should_Be_Able_To_Delete_A_Task_At_VUE() {
        givenThat(userTaiger).wasAbleTo(
                NavigateToWebPage.toVUEToDo(),
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem_1),
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem_2)
        );
        when(userTaiger).attemptsTo(
                ReviewCurrentToDoItems.andDeleteToDoItem(sToDoItem_1)
        );
        then(userTaiger).should(
                seeThat("upon visual inspection, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(intExpectedTotalCount)),
                seeThat("upon visual inspection, the to-do item number " + 1 + " should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sToDoItem_2)),
                seeThat("upon visual inspection, the to-do item " + sToDoItem_1 + " should not appear", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(true))
        );
    }
}
