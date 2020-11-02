package com.testing.suite;

import com.testing.maintester.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
        NAC_TestTitle.class,
        NAC_TestContent.class,
        NAC_TestMetaPrice.class,
        NAC_TestMetaLocation.class,
        NAC_TestMetaLanguage.class,
        NAC_TestMetaDate.class
})

// JUST A PLACEHOLDER TO TRIGGER TEST RUN
public class NACTestingAPI_Positive {}