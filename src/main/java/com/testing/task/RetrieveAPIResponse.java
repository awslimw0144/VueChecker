package com.testing.task;

import com.testing.pojo.Client;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class RetrieveAPIResponse implements Task {
    private String sURL;

    public RetrieveAPIResponse(String sURL){
        this.sURL = sURL;
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        Client client = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", Client.class);

        t.attemptsTo(
                Get.resource(this.sURL).with(requestSpecification -> requestSpecification.header(
                        "Authorization","bearer "+ client.getAccess_token()
                ))
        );
    }
    public static RetrieveAPIResponse toGetEventDetails(String sURL){
        return Tasks.instrumented(RetrieveAPIResponse.class, sURL);
    }
}
