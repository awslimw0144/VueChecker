package com.testing.suite;

import com.testing.maintester.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
        NACWeb_Esplanade_TestTitle.class,
        NACWeb_Esplanade_TestContent.class,
        NACWeb_Esplanade_TestGenre.class,
        NACWeb_Esplanade_TestTagPrice.class,
        NACWeb_Esplanade_TestMetaPrice.class,
        NACWeb_Esplanade_TestLocation.class,
        NACWeb_Esplanade_TestLanguage.class,
        NACWeb_Esplanade_TestDate.class
})

// JUST A PLACEHOLDER TO TRIGGER TEST RUN
public class NACTesting_Positive {}