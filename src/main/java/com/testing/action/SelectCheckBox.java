package com.testing.action;

import com.testing.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

public class SelectCheckBox implements Interaction {
    private String sTodoItem;

    public SelectCheckBox(String sTodoItem){
        this.sTodoItem = sTodoItem;
    }

    @Override
    @Step("{0} will select check box for item #sToDoItem")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Click.on(WebPageVUEHome.ITEM_VUE_LEFTWITH_CHECKBOX.of(sTodoItem).resolveFor(t))
        );
    }

    public static SelectCheckBox forThisToDoItem(String sToDoItem){
        return Tasks.instrumented(SelectCheckBox.class, sToDoItem);
    }
}
