package com.testing.action;

import com.testing.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;

public class ClickOnCancel implements Interaction {

    private String sToDoItem;

    public ClickOnCancel(String sToDoItem){
        this.sToDoItem = sToDoItem;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        BrowseTheWeb browseTheWeb = t.abilityTo(BrowseTheWeb.class);
        browseTheWeb.as(t).evaluateJavascript("arguments[0].style.display='inline'",WebPageVUEHome.ITEM_VUE_TODO_CANCEL.of(sToDoItem).resolveFor(t));
        t.attemptsTo(
                Click.on(WebPageVUEHome.ITEM_VUE_TODO_CANCEL.of(sToDoItem).resolveFor(t))
        );
    }

    public static ClickOnCancel toDeleteThisToDoItem(String sToDoItem){
        return Tasks.instrumented(ClickOnCancel.class, sToDoItem);
    }
}
