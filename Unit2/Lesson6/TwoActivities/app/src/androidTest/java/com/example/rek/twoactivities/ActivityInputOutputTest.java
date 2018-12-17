package com.example.rek.twoactivities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void activityLaunch() {
        onView( withId(R.id.btnMain) ).perform( click() );
        onView( withId(R.id.tvTextHeader) ).check( matches(isDisplayed()) );

        onView( withId(R.id.btnSecond) ).perform( click() );
        onView( withId(R.id.tvTextHeaderReply) ).check( matches(isDisplayed()) );
    }

    @Test
    public void textInputOutput() {
        onView( withId(R.id.edtMain) ).perform( typeText("Test String") );
        onView( withId(R.id.btnMain) ).perform( click() );
        onView( withId(R.id.tvTextMessage) ).check( matches( withText("Test String") ) );
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.rek.twoactivities", appContext.getPackageName());
    }
}
