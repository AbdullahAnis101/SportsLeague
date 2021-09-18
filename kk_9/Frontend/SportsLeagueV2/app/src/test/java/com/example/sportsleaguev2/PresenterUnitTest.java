package com.example.sportsleaguev2;

import android.content.Context;

import com.example.sportsleaguev2.Model.RequestServerForService;
import com.example.sportsleaguev2.Presenter.Login_SignUp_Presenter;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void signUp() throws JSONException {
        // want to call register and then verify showProgressBar and signUp are called.

        // Random string to update model
        String testUserName = "test_presenter1";
        String testPassword = "test_presenter1";
        String testFirstName = "test_presenter1";
        String testLastName = "test_presenter1";
        String testPhoneNumber = "test_presenter1";
        int testRank = 0;

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.register(testUserName, testPassword, testFirstName, testLastName, testPhoneNumber, testRank);

        // Verify it worked
        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).signUp(testUserName, testPassword, testFirstName, testLastName, testPhoneNumber, testRank);
    }

    @Test
    public void addPrediction() throws JSONException {

        // Random string to update model
        int predictionID = 0;
        int date = 0;
        String sport = "testpresenter";
        String teams = "testpresenter";
        String predictionForGame = "testpresenter";
        String username = "testpresenter";
        String savename = "testpresenter";

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.addPrediction(predictionID, date, sport, teams, predictionForGame, username, savename);

        // Verify it worked
//        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).add_prediction(predictionID, date, sport, teams, predictionForGame, username, savename);
    }

    @Test
    public void login() throws JSONException {

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.getUserList();

        // Verify it worked
//        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).pullUsers();
    }

    @Test
    public void getSchedule() throws JSONException {

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.getGameSchedule();

        // Verify it worked
//        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).pullSchedule();
    }

    @Test
    public void getPredictions () throws JSONException {
        String username = "b";

        // Initialize presenter and inject mocks
        Login_SignUp_Presenter presenter = new Login_SignUp_Presenter(view, context);
        presenter.setModel(stringServer); // injecting mock server

        // Test method
        presenter.getPredictions(username);

        // Verify it worked
//        verify(view, times(1)).showProgressBar();
        verify(stringServer,times(1)).pullPredictions(username);
    }

}