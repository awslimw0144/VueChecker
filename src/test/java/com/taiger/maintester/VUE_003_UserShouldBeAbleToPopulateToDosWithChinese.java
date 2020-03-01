package com.taiger.maintester;

import com.taiger.data.ExcelObject;
import com.taiger.question.LookingAtTheToDoLists;
import com.taiger.task.NavigateToWebPage;
import com.taiger.task.ThinkOfToDoItem;
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

@RunWith(SerenityRunner.class)
public class VUE_003_UserShouldBeAbleToPopulateToDosWithChinese {

    // BS_Data PREPERATION
    String filePath = ResrcUtils.getFilePath(VUE002_UserShouldBeAbleToDeleteToDos.class,"BS_Data_VUE.xlsx");
    ExcelObject BS_DATA_VUE = new ExcelObject(filePath);
    String sShtNme = "BS_DATA_VUE";
    String sHdrNme = "ListOfToDos";
    String sContextNme = "VUE_003_UserShouldBeAbleToPopulateToDosWithChinese";
    String sToDoItem = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme,sContextNme);
    // TO VERIFY
    String sToDoItem_1 = "我需要在早上6.30醒來";
    String sToDoItem_2 = "我需要刷牙";
    int intExpectedTotalCount = Integer.parseInt("1");

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
                NavigateToWebPage.toVUEToDo()
        );
        when(userTaiger).attemptsTo(
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem_1),
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem_2)
        );
        then(userTaiger).should(
                seeThat("upon visual inspection, the total count is ", LookingAtTheToDoLists.userWillVisuallyCountTheTotalNumberOfToDoItems(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, the to-do item number "+ 1 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sToDoItem_1)),
                seeThat("upon visual inspection, the to-do item number "+ 2 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(2), Matchers.equalTo(sToDoItem_2))
        );
    }
}
