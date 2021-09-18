package com.example.sportsleague;

import android.content.Context;

import com.example.sportsleague.Model.RequestServerForService;
import com.example.sportsleague.Presenter.Login_SignUp_Presenter;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresenterUnitTest {


    @Mock
    private Context context;

    @Mock
    private TestingView view;

    @Mock
    private RequestServerForService stringServer;

    @Test
    public void whenLogging_Validate() throws JSONException {
        // want to call Login and then verify showProgressBar and validateUser are called.

        // Random string to update model
        String testUserName = "hello";
        String testPassword = "hello";

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.register(testUserName, testPassword);

        // Verify it worked
        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).signUp(testUserName, testPassword);
    }

}