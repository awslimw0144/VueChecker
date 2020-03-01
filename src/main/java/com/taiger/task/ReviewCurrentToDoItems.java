package com.taiger.task;

import com.taiger.action.ClickOnCancel;
import com.taiger.action.SelectCheckBox;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.thucydides.core.annotations.Step;

import java.util.regex.Pattern;

public class ReviewCurrentToDoItems implements Task {

    private String sToDoItem;
    private String sFilterLink;

    public ReviewCurrentToDoItems(String sToDoItem, String sFilterLink) {
        this.sToDoItem = sToDoItem;
        this.sFilterLink = sFilterLink;
    }

    @Override
    @Step("{0} attempts to review and delete todo item [#sToDoItem]")
    public <T extends Actor> void performAs(T t) {
        String[] arrToDoItems = sToDoItem.split(Pattern.quote(" | "));
        for(int i=0; i<arrToDoItems.length; i++){
            t.attemptsTo(
                    SelectCheckBox.forThisToDoItem(sToDoItem)
            );
        }

        if (sFilterLink.equals("DELETE")) {
            for(int i=0; i<arrToDoItems.length; i++){
                t.attemptsTo(
                        ClickOnCancel.toDeleteThisToDoItem(sToDoItem)
                );
            }
        }
    }


    public static ReviewCurrentToDoItems andDeleteToDoItem(String strToDoItem) {
        return Tasks.instrumented(ReviewCurrentToDoItems.class, strToDoItem, "DELETE");
    }

    public static ReviewCurrentToDoItems andSelectItemsCompleted(String strToDoItem) {
        return Tasks.instrumented(ReviewCurrentToDoItems.class, strToDoItem, "COMPLETED");
    }
}
