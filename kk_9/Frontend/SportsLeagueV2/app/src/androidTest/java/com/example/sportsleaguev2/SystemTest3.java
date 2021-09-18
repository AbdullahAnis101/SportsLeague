package com.example.sportsleaguev2;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.sportsleaguev2.Presenter.Login_SignUp_Presenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
public class SystemTest3 implements TestingView {
    private static final int SIMULATED_DELAY_MS = 500;
    String resultString;
    Login_SignUp_Presenter presenter;

    @Rule   // needed to launch the activity
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    /**
     * Start the server and run this test
     */
    @Test
    public void login(){
        String testString = "test_system3";
        presenter = new Login_SignUp_Presenter(this, getApplicationContext());
        presenter.getGameSchedule();

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
        onView(withId(R.id.serverResponse)).check(matches(withText(getResponse())));
    }

    @Override
    public void updateUserInfoTextView(String info, String key) {
        resultString = info;
    }

    @Override
    public String getResponse() {
        return resultString;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
