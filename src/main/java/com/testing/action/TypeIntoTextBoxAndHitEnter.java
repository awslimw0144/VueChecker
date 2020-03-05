package com.testing.action;

import com.testing.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;

public class TypeIntoTextBoxAndHitEnter implements Interaction {

    private String sValue;

    public TypeIntoTextBoxAndHitEnter(String sValue){
        this.sValue = sValue;
    }

    @Override
    @Step("{0} enters [#sValue] to Textbox and hit on enter-key")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Enter.theValue(sValue)
                        .into(WebPageVUEHome.TEXTBOX_VUE_TODO.resolveFor(t))
                        .thenHit(Keys.ENTER)
        );
    }

    public static TypeIntoTextBoxAndHitEnter withThisString(String sValue){
        return Tasks.instrumented(TypeIntoTextBoxAndHitEnter.class, sValue);
    }
}
