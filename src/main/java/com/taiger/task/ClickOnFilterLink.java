package com.taiger.task;

import com.taiger.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class ClickOnFilterLink implements Task {
    private String sFilterLink;
    public ClickOnFilterLink(String sFilterLink){
        this.sFilterLink = sFilterLink;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Click.on(WebPageVUEHome.LINK_FILTER.of(this.sFilterLink).resolveFor(t))
        );
    }

    public static ClickOnFilterLink toFilterOutToDoItems(String sFilterLink){
        return Tasks.instrumented(ClickOnFilterLink.class, sFilterLink);
    }
}
