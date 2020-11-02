package com.testing.maintester;

import com.testing.data.Application_ExcelObject;
import com.testing.pojo.ExcelTestable;
import com.testing.pojo.NAC_DataBase;
import com.testing.pojo.NAC_OntologyCSV;
import com.testing.question.VerifyNAC_Esplanade;
import com.testing.task.NavigateToWebPage;
import com.testing.utils.LoggerUtils;
import com.testing.utils.ResrcUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.TreeSet;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class NACWeb_Esplanade_TestLocation {
    private TreeSet<String> sTestIDs;
    private final ExcelTestable nacDataBase = new NAC_DataBase(ResrcUtils
            .getFilePath(Application_ExcelObject.class,"Nac-database-20201009-Oct-Accuracy-Report.xlsx"));

    Actor userTaiger = Actor.named("userTaiger");

    @Before
    public void setUpData(){
        this.nacDataBase.set_Header_Index("DataSet",0);
        this.nacDataBase.set_TestID_Index("DataSet","id");
        this.sTestIDs = nacDataBase.getTestIDs("DataSet","esplanade");
        NAC_OntologyCSV.getInstance().init(ResrcUtils.getFilePath(NAC_OntologyCSV.class,"nac-ontology.csv"));
    }

    @Managed(driver="chrome", uniqueSession = true)
    public WebDriver hisBrowser;

    @Before
    public void userCanBrowseTheWeb(){
        userTaiger.can(
                BrowseTheWeb.with(hisBrowser)
        );
    }

    @Test
    public void user_Should_Be_Able_To_See_The_Matched_LocationRegion(){
        this.sTestIDs.forEach(sTestID -> {
            LoggerUtils.printInfo("=============  TEST ["+sTestID+"] =========================");
            String sPath = nacDataBase.getValue("DataSet",sTestID,"path");
            String sMetaLocation = nacDataBase.getValue("DataSet",sTestID,"metadata.location");
            String sExpectedLocation = nacDataBase.getValue("DataSet",sTestID,"tags.location");
            String sExpectedRegion = nacDataBase.getValue("DataSet",sTestID,"tags.region");

            when(userTaiger).attemptsTo(
                    NavigateToWebPage.toPageWithThisURL(sPath)
            );

            then(userTaiger).should(
                    seeThat("the title retrieved from response matches ",
                            VerifyNAC_Esplanade.location(sMetaLocation),equalTo(true)
                    ),
                    seeThat("The region matches",VerifyNAC_Esplanade.region(sMetaLocation),equalTo(sExpectedRegion))
            );
        });
    }
}