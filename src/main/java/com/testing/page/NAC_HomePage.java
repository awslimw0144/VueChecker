package com.testing.page;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class NAC_HomePage extends PageObject {
    public static Target TEXTBOX_VUE_TODO = Target.the("TEXTBOX_VUE_TODO")
            .locatedBy("//input[@autofocus]");
    public static Target ITEM_VUE_TODO = Target.the("ITEM_VUE_TODO")
            .locatedBy("(//input[@type='checkbox' and @class='toggle'])[{0}]/following-sibling::label");
}
