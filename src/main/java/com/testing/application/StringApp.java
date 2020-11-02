package com.testing.application;

import com.testing.utils.StyleRemoverUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringApp {
    public static void main(String...args){
        String strTarget = "<p style=&quot;margin-left:0px; margin-right:0px&quot;><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>A tikopeh in New York and her abandoned daughter in Singapore, both grappling with </span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>thoughts of suicide</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>, pursue a</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;> change in their lives</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>. </span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>Over four days, their live</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>s unravel </span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>reveal</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;>ing an</span></span><span style=&quot;font-size:10pt&quot;><span style=&quot;background-color:inherit&quot;> inter-connectedness between mother and child. </span></span><span style=&quot;font-size:10pt&quot;> </span></p><p style=&quot;margin-left:0px; margin-right:0px&quot;><span style=&quot;font-size:10pt&quot;> </span></p><p></p><p></p><p></p><p><span style=&quot;font-size:9pt&quot;><span style=&quot;color:#000000&quot;> </span></span></p><p></p><p></p>";
        System.out.println(StyleRemoverUtils.with(strTarget));
    }
}
