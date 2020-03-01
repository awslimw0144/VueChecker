package com.taiger.question;

import com.taiger.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class LookingAtTheToDoLists {

    public static Question<Integer> userWillVisuallyCountTheTotalNumberOfToDoItems(){
        return actor -> WebPageVUEHome.LIST_OF_ITEMS_VUE_TODO.resolveAllFor(actor).size();
    }
    public static Question<String> userWillVisuallySeeWhatIsTheToDoItem(int itemNo){
        return actor -> WebPageVUEHome.ITEM_VUE_TODO.of(Integer.toString(itemNo)).resolveFor(actor).getText();
    }
    public static Question<Boolean> userWillVisuallySeeWhatIsTheToDoItem(String sToDoList){
        String[] arrToDos = sToDoList.split(Pattern.quote(" | "));
        List<Boolean> lTextExists = new ArrayList<>();
        return actor -> {
            for(int i=1; i<arrToDos.length; i++){
                if(WebPageVUEHome.ITEM_VUE_TODO.of(Integer.toString(i)).resolveFor(actor).isCurrentlyVisible()){
                    lTextExists.add(true);
                } else{
                    lTextExists.add(false);
                }
            }
            if(lTextExists.contains(false)) return false;
            else return true;
        };
    }

    public static Question<Integer> userWillSeeTheCountNumber(){
        return actor -> {
            String sCount = WebPageVUEHome.ITEM_VUE_LEFTWITH_NUMBER.resolveFor(actor).getText();
            return Integer.parseInt(sCount);
        };
    }
}
