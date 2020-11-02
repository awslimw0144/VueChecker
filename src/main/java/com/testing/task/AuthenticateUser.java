package com.testing.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class AuthenticateUser implements Task {
    private String sURL;
    private String authString;

    public AuthenticateUser(String sURL,String authString){
        this.sURL = sURL;
        this.authString = authString;
    }
    @Override
    public <T extends Actor> void performAs(T t) {
        t.attemptsTo(
                Post.to(this.sURL).with(requestSpecification -> requestSpecification.header(
                        "Authorization",this.authString
                ))
        );
    }

    public static AuthenticateUser withBasicAuthentication(String sURL, String authString){
        return Tasks.instrumented(AuthenticateUser.class,sURL,"Basic "+authString);
    }
    public static AuthenticateUser withBearerAuthentication(String sURL, String authString){
        return Tasks.instrumented(AuthenticateUser.class, sURL,"bearer "+authString);
    }
}