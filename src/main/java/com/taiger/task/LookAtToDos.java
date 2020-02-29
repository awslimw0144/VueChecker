package com.taiger.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LookAtToDos implements Task {

    private String toDoItem;

    public LookAtToDos(String toDoItem){
        this.toDoItem = toDoItem;
    }

    @Override
    public <T extends Actor> void performAs(T t) {

    }


    public static LookAtToDos andWriteToDoItem(String strToDoItem){
        return Tasks.instrumented(LookAtToDos.class, strToDoItem);
    }
}
