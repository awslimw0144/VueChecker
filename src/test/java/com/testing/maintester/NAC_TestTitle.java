package com.testing.maintester;

import com.testing.data.Application_ExcelObject;
import com.testing.pojo.*;
import com.testing.question.VerifyAPIReponse;
import com.testing.task.AuthenticateUser;
import com.testing.task.RetrieveAPIResponse;
import com.testing.utils.ResrcUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class NAC_TestTitle {
    private List<String> sTestIDs = new ArrayList<>();
    private String sTestID;
    private String sTitle;

    Actor userTaiger = Actor.named("userTaiger");

    @Before
    public void setUpData(){
        String filePath = ResrcUtils.getFilePath(Application_ExcelObject.class,"Nac-database-20201009-Oct-Accuracy-Report.xlsx");
        ExcelTestable nacDataBase = new NAC_DataBase(filePath);
        nacDataBase.set_Header_Index("DataSet",0);
        nacDataBase.set_TestID_Index("DataSet","id");
        this.sTestID = "esplanade_intersections";
        this.sTitle = nacDataBase.getValue("DataSet",this.sTestID,"title");
        this.sTestIDs.add(this.sTestID);
        this.sTestIDs.add("zlcontact1120");
        this.sTestIDs.remove("esplanade_intersections"); // <--Expired
    }

    @Managed(driver="chrome", uniqueSession = true)
    public WebDriver hisBrowser;

    @Before
    public void userCanBrowseTheWeb(){
        userTaiger.can(
                CallAnApi.at("https://")
        );
    }

    @Test
    public void user_Should_Be_Able_To_See_The_Matched_Title(){
        givenThat(userTaiger).wasAbleTo(
                AuthenticateUser.withBasicAuthentication("auth.stixcloud.com/auth/v0/ALIST/oauth/token?grant_type=client_credentials","YWxpc3QtYXBpLXByb2Q6MTc0NTQ5NDU5ODU3NTgxNTRDOURCOTQ4QjI3Qzc=")
        );

        when(userTaiger).attemptsTo(
                RetrieveAPIResponse.toGetEventDetails("api.stixcloud.com/api/v0/ALIST/icc?size=30")
        );

        then(userTaiger).should(
                seeThat("the title retrieved from response is ",VerifyAPIReponse.responseTitle(sTestID),equalTo(this.sTitle))
        );
    }
}