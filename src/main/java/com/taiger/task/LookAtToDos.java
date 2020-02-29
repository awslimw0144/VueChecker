package com.taiger.task;

import com.taiger.action.TypeIntoTextBoxAndHitEnter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

public class LookAtToDos implements Task {

    private String sToDoItem;

    public LookAtToDos(String sToDoItem){
        this.sToDoItem = sToDoItem;
    }

    @Override
    @Step("{0} attempts to send [#sToDoItem] to Main SearchBox")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                TypeIntoTextBoxAndHitEnter.withThisString(sToDoItem)
        );
    }


    public static LookAtToDos andWriteToDoItem(String strToDoItem){
        return Tasks.instrumented(LookAtToDos.class, strToDoItem);
    }
}
