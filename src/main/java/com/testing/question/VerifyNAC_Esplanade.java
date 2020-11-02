package com.testing.question;

import com.testing.page.PageOfEsplanade;
import com.testing.utils.ExtractTagField;
import com.testing.utils.StringFilter;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class VerifyNAC_Esplanade {
    public static Question<String> title(){
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_CONTENT.resolveFor(actor))
                    );
            return PageOfEsplanade.PAGE_TITLE.resolveFor(actor).getText();
        };
    }
    public static Question<Boolean> content(String sExpectedContent){
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_CONTENT.resolveFor(actor))
                    );

            StringBuffer stringBuffer = new StringBuffer();
            PageOfEsplanade.PAGE_CONTENT_ARTICLE.resolveAllFor(actor).forEach(webElementFacade -> {
                stringBuffer.append(webElementFacade.getText()).append(" ");
            });
            return stringBuffer.toString().trim().contains(sExpectedContent
                    .replace("</em>","")
                    .replace("<em>","")
                    .replace("</p>"," ")
                    .replace("<p>","")
                    .replace("&nbsp;"," ")
                    .trim());
        };
    }
    public static Question<String> metaPrice(){
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_PRICE_DETAILS.resolveFor(actor))
                    );
            return PageOfEsplanade.PAGE_PRICE_DETAILS.resolveFor(actor).getText();
        };
    }
    public static Question<Boolean> tagPriceMatches(List<String> setsOfPriceRanges){
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_PRICE_DETAILS.resolveFor(actor))
                    );

            // Get the string that contains the pricing (Inclusive of 'Free') and return an integer value based on pricing.
            int iTestPrice = StringFilter.filterOutIntegers(PageOfEsplanade.PAGE_PRICE_DETAILS.resolveFor(actor).getText());
            boolean biggerThan100 = (iTestPrice>=100);
            boolean biggerThan50 = (iTestPrice>=50);
            boolean notFree = (iTestPrice>0&&iTestPrice<50);
            boolean isFree = (iTestPrice==0);

            if(biggerThan100){
                return (setsOfPriceRanges.toString().contains(">100") &&
                        !setsOfPriceRanges.toString().contains("<100") &&
                        !setsOfPriceRanges.toString().contains("<50") &&
                        !setsOfPriceRanges.toString().contains("Free")
                );
            } else {
                if(biggerThan50){
                    return (!setsOfPriceRanges.toString().contains(">100") &&
                            setsOfPriceRanges.toString().contains("<100") &&
                            !setsOfPriceRanges.toString().contains("<50") &&
                            !setsOfPriceRanges.toString().contains("Free")
                    );
                }
                else {
                    if(notFree){
                        return (!setsOfPriceRanges.toString().contains(">100") &&
                                setsOfPriceRanges.toString().contains("<100") &&
                                setsOfPriceRanges.toString().contains("<50") &&
                                !setsOfPriceRanges.toString().contains("Free")
                        );
                    } else if (isFree){
                        return (!setsOfPriceRanges.toString().contains(">100") &&
                                setsOfPriceRanges.toString().contains("<100") &&
                                setsOfPriceRanges.toString().contains("<50") &&
                                setsOfPriceRanges.toString().contains("Free")
                        );
                    } else {
                        return false;
                    }
                }
            }
        };
    }
    public static Question<String> tagGenre(String sIndividuals) {
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_CONTENT_DESCRIPTION.resolveFor(actor))
                    );

            List<String> genre_count_list = new ArrayList<>();
            Map<String,Integer> genre_count_map = new LinkedHashMap<>();
            String sContent = PageOfEsplanade.PAGE_CONTENT_DESCRIPTION.resolveFor(actor).getText();
            List<String> lBuilder = new ArrayList<>();

            // Get the number of occurences of the individual based on Genre
            Arrays.stream(sIndividuals.split(","))
                    .filter(sIndividual->!(sIndividual.equals("Surprise Me")))
                    .collect(Collectors.toList()).forEach(sIndividual->{
                        ExtractTagField.of("Genre")
                                .stream()
                                .filter(sIndividual2->!(sIndividual2.equals("Surprise Me")))
                                .forEach(sAltLabel -> {
                                    if(sContent.toLowerCase().contains(sAltLabel.toLowerCase())){
                                    genre_count_list.add(sIndividual);
                                }
                        });
            });

            // To be used to stash the amount of counts for each of the individual
            Arrays.stream(sIndividuals.split(","))
                    .filter(sIndividual-> !(sIndividual.equals("Surprise Me")))
                    .collect(Collectors.toList()).forEach(sIndividual->{
                        genre_count_map.put(sIndividual,Collections.frequency(genre_count_list, sIndividual));
            });

            // Find the highest number of word count so that later on we will be able to find if each of the main words
            // is at its highest count
            int highestCount = genre_count_map.entrySet().stream()
                    .mapToInt(Map.Entry::getValue)
                    .max()
                    .getAsInt();

            long totalSize = genre_count_map.entrySet().stream()
                    .filter(map->map.getValue()==highestCount)
                    .count();

            genre_count_map.entrySet().stream()
                    .filter(map->map.getValue()==highestCount)
                    .forEach(stringIntegerEntry -> {
                        lBuilder.add(stringIntegerEntry.getKey());
                    });

            if(totalSize>1){
                lBuilder.add(lBuilder.size()-1,"Surprise_Me");
            }

            return lBuilder.toString().replace("[","")
                    .replace("]","")
                    .replace(" ","")
                    .replace("_"," ");

        };
    }
    public static Question<String> language(String sExpectedLanguage){
        // Note : By default,tags.language is generated as 'English' if there is no specification for other languages
        Map<String,Boolean> mapLanguageToExist = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        // [1] Initialize tags
        // Note : Assumption that presence of 'English' is true. As such 'English' will not be checked.
        Arrays.stream(sExpectedLanguage.split(",")).forEach(sLanguage->{
            if(sLanguage.equalsIgnoreCase("English")){
                mapLanguageToExist.put(sLanguage,true);
            } else {
                mapLanguageToExist.put(sLanguage,false);
            }
        });

        // [2] Wait for all the articles to show before commencing testing
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_CONTENT_ARTICLE.resolveFor(actor)));

            // [3] For each of the content row, besides 'English', find any other name of language
            // Note : As of now, there is only 'Chinese', 'Malay' and 'Tamil' as per the rule.
            //        So don't care about the rest
            PageOfEsplanade.PAGE_CONTENT_ARTICLE.resolveAllFor(actor).forEach(webElementFacade -> {
                Arrays.stream(sExpectedLanguage.split(","))
                        .filter(sLanguage->!sLanguage.equalsIgnoreCase("English"))
                        .forEach(sLanguage -> {
                            mapLanguageToExist.put(sLanguage,webElementFacade.getText().contains(sLanguage));
                        });
            });

            // [4] To append the findings.
            // Note : Only languages mapped with 'true' will be appended to stringbuilder, which will be returned to
            //        perform comparison with the expected language.
            mapLanguageToExist.forEach((key, value) -> {
                if (value) {
                    stringBuilder.append(key);
                    stringBuilder.append(" ");
                }
            });
            return stringBuilder.toString().trim().replace(" ",",");
        };
    }
    public static Question<Boolean> location(String sMetaLocation){
        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_ADDRESS.resolveFor(actor)));

            String address = PageOfEsplanade.PAGE_ADDRESS.resolveFor(actor).getText();

            return sMetaLocation.contains(address);
        };
    }
    public static Question<String> region(String sMetaLocation){
        Map<String, String> mapVenue = new HashMap<>();
        mapVenue.put("Online", "Esplanade");
        mapVenue.put("Esplanade Concourse","Esplanade");
        mapVenue.put("Esplanade Tunnel","Esplanade");
        mapVenue.put("Jendela (Visual Arts Space)","Esplanade");

        return actor -> {
            new WebDriverWait(getDriver(), 20)
                    .withTimeout(Duration.ofMinutes(1))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(PageOfEsplanade.PAGE_ADDRESS.resolveFor(actor)));

            String sVenue = PageOfEsplanade.PAGE_ADDRESS.resolveFor(actor).getText();
            String sAddress = mapVenue.get(sVenue);
            String sActualRegion = ExtractTagField.ofPostalRegion().get(
                    ExtractTagField.ofLocation().get(sAddress).substring(0,2));
            System.out.println(sAddress+" region is at " +sActualRegion);
            return sActualRegion;
        };
    }
}