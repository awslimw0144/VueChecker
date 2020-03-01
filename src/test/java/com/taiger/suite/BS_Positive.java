package com.taiger.suite;

import com.taiger.maintester.VUE001_UserShouldBeAbleToPopulateToDos;
import com.taiger.maintester.VUE002_UserShouldBeAbleToDeleteToDos;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
        VUE001_UserShouldBeAbleToPopulateToDos.class,
        VUE002_UserShouldBeAbleToDeleteToDos.class
})

// JUST A PLACEHOLDER TO TRIGGER TEST RUN
public class BS_Positive { }
