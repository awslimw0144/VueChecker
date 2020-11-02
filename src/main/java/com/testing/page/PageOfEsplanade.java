package com.testing.page;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class PageOfEsplanade extends PageObject {
    public static Target PAGE_CONTENT = Target.the("Content Details").locatedBy(".//div[@class='event-content row']");
    public static Target PAGE_TITLE = Target.the("Title of Page").locatedBy(".//h1[@itemprop='name']");
    public static Target PAGE_CONTENT_DESCRIPTION = Target.the("Passage").locatedBy(".//article[@itemprop='description']/p");
    public static Target PAGE_CONTENT_ARTICLE = Target.the("Article").locatedBy(".//article[@itemprop='description']/*");
    public static Target PAGE_PRICE_DETAILS = Target.the("Price").locatedBy(".//div[@id='event-booknow']/div/article/h3");
    public static Target PAGE_ADDRESS = Target.the("Address").locatedBy(".//div[@class='address']");
    public static Target PAGE_DATE = Target.the("Date Information").locatedBy(".//div[@class='date info-box']");
    public static Target PAGE_TIME = Target.the("Date Information").locatedBy(".//div[@class='time info-box']");
}
