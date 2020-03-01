package com.taiger.task;

import com.taiger.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class FilterToDoList implements Task {
    private String filterLink;

    public FilterToDoList(String filterLink){
        this.filterLink = filterLink;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Click.on(WebPageVUEHome.LINK_FILTER.of(this.filterLink).resolveFor(t))
        );
    }

    public static FilterToDoList byClickingOnActive(){
        return Tasks.instrumented(FilterToDoList.class,"Active");
    }
    public static FilterToDoList byClickingOnCompleted(){
        return Tasks.instrumented(FilterToDoList.class,"Completed");
    }
}
