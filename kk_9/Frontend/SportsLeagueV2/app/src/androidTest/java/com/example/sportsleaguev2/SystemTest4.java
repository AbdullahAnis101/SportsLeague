package com.example.sportsleaguev2;

import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.sportsleaguev2.LoginActivity;
import com.example.sportsleaguev2.R;
import com.example.sportsleaguev2.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SystemTest4 {
    private static final int SIMULATED_DELAY_MS = 500;


    @Rule   // needed to launch the activity
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);


    //checks to see if we can login to an app, and sees if the button is working
    @Test
    public void checkcomboB(){
        String testString = "b";
        String resultString = "null";
        // Type in testString and send request
        onView(withId(R.id.username)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value
        onView(withId(R.id.serverResponse)).check(matches(withText(resultString)));
    }

    @Test
    public void checkcomboC(){
        String testString = "c";
        String resultString = "null";
        // Type in testString and send request
        onView(withId(R.id.username)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        // Verify that volley returned the correct value
        onView(withId(R.id.serverResponse)).check(matches(withText(resultString)));
    }


}
