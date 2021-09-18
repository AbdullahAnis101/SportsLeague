package com.example.sportsleaguev2;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

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

// Mock the RequestServerForService class
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class SystemTest1 {
    private static final int SIMULATED_DELAY_MS = 500;

    @Rule   // needed to launch the activity
    public ActivityTestRule<SignUpActivity> activityRule = new ActivityTestRule<>(SignUpActivity.class);


    /**
     * Start the server and run this test
     */
    @Test
    public void signUp(){
        String testString = "test_system1";
        String resultString = "null";
        // Type in testString and send request
        onView(withId(R.id.first_name)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.last_name)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.phone_number)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.password2)).perform(typeText(testString), closeSoftKeyboard());
        onView(withId(R.id.btn_signup)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Verify that volley returned the correct value
        onView(withId(R.id.serverResponse)).check(matches(withText(resultString)));

    }

}