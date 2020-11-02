package com.testing.question;

import com.testing.page.WebPageVUEHome;
import com.testing.pojo.NACEvent;
import com.testing.pojo.NACEventContent;
import com.testing.utils.StyleRemoverUtils;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class VerifyAPIReponse {

    public static Question<Boolean> responseMetadate_end(String sTestID, String sMetaDate){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        String sResponse = nacEventContentActual.getEndDate()
                .replace("&lt;","")
                .replace("/","")
                .replace("p&gt;", "");

        ZonedDateTime localDateTime = ZonedDateTime.parse(sResponse);
        String responseDate = localDateTime.getDayOfMonth() + " "
                + localDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " "
                + localDateTime.getYear();

        return response -> sMetaDate.contains(responseDate);
    }

    public static Question<Boolean> responseMetadate_start(String sTestID, String sMetaDate){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        String sResponse = nacEventContentActual.getStartDate()
                .replace("&lt;","")
                .replace("/","")
                .replace("p&gt;", "");

        ZonedDateTime localDateTime = ZonedDateTime.parse(sResponse);
        String responseDate = localDateTime.getDayOfMonth() + " "
                + localDateTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " "
                + localDateTime.getYear();

        return response -> sMetaDate.contains(responseDate);

    }

    public static Question<Boolean> responseMetadate_Duration(String sTestID, String sMetaDate){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        String sResponse = nacEventContentActual.getDuration()
                .replace("&lt;","")
                .replace("/","")
                .replace("p&gt;", "");

        return response -> sMetaDate.contains(sResponse);
    }

    public static Question<String> responseMetaLanguage(String sTestID){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        if(nacEventContentActual.getLanguage()==null) return response -> "";
        return response -> nacEventContentActual.getLanguage();
    }

    public static Question<String> responseMetaLocation(String sTestID){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        return response -> nacEventContentActual.getVenue();
    }

    public static Question<String> responseMetaPrice(String sTestID){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        return response -> nacEventContentActual.getPriceDescription()
                .replace("&lt;","<")
                .replace("&gt;",">")
                .replace("p&gt;", "p")
                .replace("\n","");
    }

    public static Question<String> responseContent(String sTestID){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        String actualResponseRaw = nacEventContentActual.getSynopsis()
                .replace("&lt;","<")
                .replace("&gt;",">")
                .replace("p&gt;", "p");
        String actualResponse = StyleRemoverUtils.with(actualResponseRaw);

        return response -> actualResponse;
    }

    public static Question<String> responseTitle(String sTestID){
        NACEvent nacEvent = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", NACEvent.class);

        NACEventContent nacEventContentActual = Arrays.stream(nacEvent.getContent())
                .filter(nacEventContent -> sTestID.equals(nacEventContent.getInternetContentCode()))
                .findAny()
                .get();

        return response -> nacEventContentActual.getTitle();
    }
}
