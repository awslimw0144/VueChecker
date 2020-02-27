package com.taiger.task;

import com.taiger.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClearOff implements Task {

    WebPageVUEHome webPageVUEHome;

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(webPageVUEHome)
        );
    }
    public static ClearOff allPreExistingTasks(){
        return instrumented(ClearOff.class);
    }
}
