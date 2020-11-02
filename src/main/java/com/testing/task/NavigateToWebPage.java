package com.testing.task;

import com.testing.page.WebPageVUEHome;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToWebPage implements Task {

    WebPageVUEHome webPageVUEHome;
    private String sURL="";

    public NavigateToWebPage(){}
    public NavigateToWebPage(String sURL){
        this.sURL = sURL;
    }

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(this.sURL)
        );
//        if(this.sURL.equals("")){
//            actor.attemptsTo(
//                    Open.browserOn().the(webPageVUEHome)
//            );
//        } else {
//            if(this.sURL.contains("https://ticketing.a-list.sg/alist/booking")){
//                String[] arrURL = this.sURL.split(Pattern.quote("/"));
//                String sEventID = arrURL[arrURL.length-1]; // screen1020e
//                this.sURL = "https://www.a-list.sg/event-details/?event_id="+sEventID;
//            }
//            actor.attemptsTo(
//                    Open.url(this.sURL)
//            );
//        }
    }
    public static NavigateToWebPage toVUEToDo(){
        return instrumented(NavigateToWebPage.class);
    }
    public static NavigateToWebPage toPageWithThisURL(String sURL){
        return Tasks.instrumented(NavigateToWebPage.class, sURL);
    }
}
