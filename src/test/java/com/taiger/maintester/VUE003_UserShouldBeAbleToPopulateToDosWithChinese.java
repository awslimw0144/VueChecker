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
public class VUE003_UserShouldBeAbleToPopulateToDosWithChinese {

    // BS_Data PREPERATION
    String filePath = ResrcUtils.getFilePath(VUE002_UserShouldBeAbleToDeleteToDos.class,"BS_Data_VUE.xlsx");
    ExcelObject BS_DATA_VUE = new ExcelObject(filePath);
    String sShtNme = "BS_DATA_VUE";
    String sHdrNme = "ListOfToDos";
    String sContextNme = "VUE003_UserShouldBeAbleToPopulateToDosWithChinese";
    String sToDoItem = BS_DATA_VUE.getWSValue(sShtNme,sHdrNme,sContextNme);

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
    public void user_Should_Be_Able_To_Populate_ToDos_With_Chinese(){
        givenThat(userTaiger).wasAbleTo(
                NavigateToWebPage.toVUEToDo()
        );
        when(userTaiger).attemptsTo(
                ThinkOfToDoItem.andWriteToDoItem(sToDoItem)
        );
        then(userTaiger).should(
                seeThat("upon visual inspection, the total count is ", LookingAtTheToDoLists.userWillVisuallyCountTheTotalNumberOfToDoItems(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, the amount of items left will be ", LookingAtTheToDoLists.userWillSeeTheCountNumber(), Matchers.equalTo(2)),
                seeThat("upon visual inspection, all of the to-do items shows", LookingAtTheToDoLists.userWillVisuallySeeWhatIsTheToDoItem(sToDoItem), Matchers.is(true))
        );
    }
}
