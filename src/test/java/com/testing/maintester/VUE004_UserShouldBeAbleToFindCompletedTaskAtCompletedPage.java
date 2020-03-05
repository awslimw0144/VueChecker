package com.testing.maintester;

import com.testing.data.ExcelObject;
import com.testing.question.LookingAtTheToDoLists;
import com.testing.task.FilterToDoList;
import com.testing.task.NavigateToWebPage;
import com.testing.task.ReviewCurrentToDoItems;
import com.testing.task.ThinkOfToDoItem;
import com.testing.utils.ResrcUtils;
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
public class VUE004_UserShouldBeAbleToFindCompletedTaskAtCompletedPage {

    // BS_Data PREPERATION
    String filePath = ResrcUtils.getFilePath(VUE004_UserShouldBeAbleToFindCompletedTaskAtCompletedPage.class,"BS_Data_VUE.xlsx");
    ExcelObject BS_DATA_VUE = new ExcelObject(filePath);
    String sShtNme = "BS_DATA_VUE";
    String sHdrNme_ListOfToDos = "ListOfToDos";
    String sHdrNme_ListOfCompletedItems = "ListOfCompletedItems";
    String sContextNme = "VUE004_UserShouldBeAbleToFindCompletedTaskAtCompletedPage";
    String sToDoItem = BS_DATA_VUE.getWSValue(sShtNme, sHdrNme_ListOfToDos,sContextNme);
    String sCompletedItem = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme_ListOfCompletedItems,sContextNme);

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
    public void user_Should_Be_Able_To_View_A_Completed_Task_At_VUE(){
        givenThat(userTaiger).wasAbleTo(
                NavigateToWebPage.toVUEToDo(),
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem),
                ReviewCurrentToDoItems.andSelectItemsCompleted(sCompletedItem)
        );
        when(userTaiger).attemptsTo(
                FilterToDoList.byClickingOnCompleted()
        );
        then(userTaiger).should(
                seeThat("upon visual inspection for all items, the total count is ", LookingAtTheToDoLists.userWillVisuallyCountTheTotalNumberOfToDoItems(), Matchers.equalTo(1)),
                seeThat("on the bottom left, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(1)),
                seeThat("upon visual inspection, the to-do item number "+ 1 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(1), Matchers.equalTo(sCompletedItem))
//                seeThat("upon visual inspection, the to-do item number "+ 2 +" should show", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(2), Matchers.not(s))
        );
    }
}
