package com.taiger.task;

import com.taiger.action.TypeIntoTextBoxAndHitEnter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import java.util.regex.Pattern;

public class ThinkOfToDoItem implements Task {

    private String sToDoItem;

    public ThinkOfToDoItem(String sToDoItem){
        this.sToDoItem = sToDoItem;
    }

    @Override
    @Step("{0} attempts to send [#sToDoItem] to Main SearchBox")
    public <T extends Actor> void performAs(T t) {
        String[] arrToDoItems = this.sToDoItem.split(Pattern.quote(" | "));
        for(int i=0; i<arrToDoItems.length; i++){
            t.attemptsTo(
                    TypeIntoTextBoxAndHitEnter.withThisString(arrToDoItems[i])
            );
        }
    }


    public static ThinkOfToDoItem andWriteToDoItem(String strToDoItem){
        return Tasks.instrumented(ThinkOfToDoItem.class, strToDoItem);
    }
}
