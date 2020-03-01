package com.taiger.maintester;

import com.taiger.data.ExcelObject;
import com.taiger.question.LookingAtTheToDoLists;
import com.taiger.task.ReviewCurrentToDoItems;
import com.taiger.task.ThinkOfToDoItem;
import com.taiger.task.NavigateToWebPage;
import com.taiger.utils.ResrcUtils;
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
    // BS_Data PREPERATION
    String filePath = ResrcUtils.getFilePath(VUE002_UserShouldBeAbleToDeleteToDos.class,"BS_Data_VUE.xlsx");
    ExcelObject BS_DATA_VUE = new ExcelObject(filePath);
    String sShtNme = "BS_DATA_VUE";
    String sHdrNme_ListOfToDos = "ListOfToDos";
    String sHdrNme_ListOfToBeDeletedItems = "ListOfToBeDeletedItems";
    String sContextNme = "VUE002_UserShouldBeAbleToDeleteToDos";
    String sToDoItem = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme_ListOfToDos,sContextNme);
    String sToBeDeleted = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme_ListOfToBeDeletedItems,sContextNme);
    // TO VERIFY
    String sToDoItem_1 = "I need to wake up at 6.30am";
    String sToDoItem_2 = "I need to brush my teeth";
    int intExpectedTotalCount = Integer.parseInt("1");

    Actor userTaiger = Actor.named("userTaiger");

    @Managed(driver = "chrome", uniqueSession = true)
    public WebDriver hisBrowser;

    @Before
    public void userCanBrowseTheWeb() {
        userTaiger.can(
                BrowseTheWeb.with(hisBrowser)
        );
    }

    /**
     * @Author : Vic Lim
     * @Date : 1st March 2020
     * @Description : Business Scenario - User should be able to delete a task at VUE
     */
    @Test
    public void user_Should_Be_Able_To_Delete_A_Task_At_VUE() {
        givenThat(userTaiger).wasAbleTo(
                NavigateToWebPage.toVUEToDo(),
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem)
        );
        when(userTaiger).attemptsTo(
                ReviewCurrentToDoItems.andDeleteToDoItem(sToBeDeleted)
        );
        then(userTaiger).should(
                seeThat("upon visual inspection, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(intExpectedTotalCount)),
                seeThat("upon visual inspection, the to-do item number " + 1 + " should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sToDoItem_2)),
                seeThat("upon visual inspection, the to-do item " + sToDoItem_1 + " should not appear", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sToDoItem_2))
        );
    }
}
