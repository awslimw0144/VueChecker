package com.taiger.page;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://todomvc.com/examples/vue/")
public class WebPageVUEHome extends PageObject {
    public static Target TEXTBOX_VUE_TODO = Target.the("TEXTBOX_VUE_TODO")
            .locatedBy("//input[@autofocus]");

    public static Target LIST_OF_ITEMS_VUE_TODO = Target.the("LIST_OF_ITEMS_VUE_TODO")
            .locatedBy("//input[@type='checkbox' and @class='toggle']");

    public static Target ITEM_VUE_TODO = Target.the("ITEM_VUE_TODO")
            .locatedBy("(//input[@type='checkbox' and @class='toggle'])[{0}]/following-sibling::label");

    public static Target ITEM_VUE_LEFTWITH_NUMBER = Target.the("ITEM_VUE_LEFTWITH_TEXT")
            .locatedBy("//span/strong");

    public static Target ITEM_VUE_LEFTWITH_TEXT = Target.the("ITEM_VUE_LEFTWITH_NUMBER")
            .locatedBy("//span/strong/following-sibling::text");
}
