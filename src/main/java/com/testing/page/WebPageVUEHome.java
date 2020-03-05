package com.testing.page;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

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

    public static Target ITEM_VUE_LEFTWITH_CHECKBOX = Target.the("ITEM_VUE_LEFTWITH_CHECKBOX")
            .locatedBy("//label[contains(text(),'{0}')]/preceding-sibling::input");

    // ITEM_VUE_TODO_CANCEL
    //label[contains(text(), "I need to wake up")]/following-sibling::button
    public static Target ITEM_VUE_TODO_CANCEL = Target.the("ITEM_VUE_TODO_CANCEL")
            .locatedBy("//label[contains(text(),'{0}')]/following-sibling::button");

    public static Target LINK_FILTER = Target.the("Filter link to select")
            .locatedBy("//ul[@class='filters']//a[contains(text(),'{0}')]");

}
