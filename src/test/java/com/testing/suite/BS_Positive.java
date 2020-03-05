package com.testing.suite;

import com.testing.maintester.VUE001_UserShouldBeAbleToPopulateToDos;
import com.testing.maintester.VUE002_UserShouldBeAbleToDeleteToDos;
import com.testing.maintester.VUE003_UserShouldBeAbleToPopulateToDosWithChinese;
import com.testing.maintester.VUE004_UserShouldBeAbleToFindCompletedTaskAtCompletedPage;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
        VUE001_UserShouldBeAbleToPopulateToDos.class,
        VUE002_UserShouldBeAbleToDeleteToDos.class,
        VUE003_UserShouldBeAbleToPopulateToDosWithChinese.class,
        VUE004_UserShouldBeAbleToFindCompletedTaskAtCompletedPage.class
})

// JUST A PLACEHOLDER TO TRIGGER TEST RUN
public class BS_Positive { }
