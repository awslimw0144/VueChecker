package com.taiger.question;

import com.taiger.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Question;

public class LookingAtTheToDoLists {

    public static Question<Integer> userWillVisuallyCountTheTotalNumberOfToDoItems(){
        return actor -> WebPageVUEHome.LIST_OF_ITEMS_VUE_TODO.resolveAllFor(actor).size();
    }

    public static Question<String> userWillVisuallySeeWhatIsTheToDoItem(int itemNo){
        return actor -> WebPageVUEHome.ITEM_VUE_TODO.of(Integer.toString(itemNo)).resolveFor(actor).getText();
    }

//    public static Question<String> userWillNotBeAbleToSeeWhatIsTheToDoItem(String sToDoItem){
//        return actor -> {
//            WebPageVUEHome.ITEM_VUE_TODO.of(Integer.toString(itemNo)).resolveFor(actor).getText();
//        }
//    }

    public static Question<Integer> userWillSeeTheCountNumber(){
        return actor -> {
            String sCount = WebPageVUEHome.ITEM_VUE_LEFTWITH_NUMBER.resolveFor(actor).getText();
            return Integer.parseInt(sCount);
        };
    }
}
