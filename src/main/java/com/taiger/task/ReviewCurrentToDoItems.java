package com.taiger.task;

import com.taiger.action.ClickOnCancel;
import com.taiger.action.SelectCheckBox;
import com.taiger.action.TypeIntoTextBoxAndHitEnter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

public class ReviewCurrentToDoItems implements Task {

    private String sToDoItem;

    public ReviewCurrentToDoItems(String sToDoItem){
        this.sToDoItem = sToDoItem;
    }

    @Override
    @Step("{0} attempts to review and delete todo item [#sToDoItem]")
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                SelectCheckBox.forThisToDoItem(sToDoItem),
                ClickOnCancel.toDeleteThisToDoItem(sToDoItem)
        );
    }


    public static ReviewCurrentToDoItems andDeleteToDoItem(String strToDoItem){
        return Tasks.instrumented(ReviewCurrentToDoItems.class, strToDoItem);
    }
}
