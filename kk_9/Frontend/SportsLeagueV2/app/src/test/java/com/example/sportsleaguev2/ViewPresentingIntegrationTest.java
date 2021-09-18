package com.example.sportsleaguev2;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import com.example.sportsleaguev2.Model.RequestServerForService;
import com.example.sportsleaguev2.Presenter.Login_SignUp_Presenter;
import com.example.sportsleaguev2.SignUpActivity;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1, manifest = Config.NONE)
public class ViewPresentingIntegrationTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Mock
    private RequestServerForService server;

    @Before
    public void setUpMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws JSONException {

        // test
        SignUpActivity activity = Robolectric.buildActivity(SignUpActivity.class)
                .create()
                .resume()
                .get();

        Login_SignUp_Presenter presenter = activity.test_getPresenter();
        presenter.setModel(server);

        ArgumentCaptor<String> argumentCaptor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentCaptor2 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentCaptor3 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentCaptor4 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentCaptor5 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> argumentCaptor6 = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture(), argumentCaptor3.capture(), argumentCaptor4.capture(), argumentCaptor5.capture(), argumentCaptor6.capture());


        // enter text
        EditText stringEntry = activity.findViewById(R.id.username);
        stringEntry.setText("test_view_presenter1");

        // click the continue button
        activity.findViewById(R.id.btn_signup).callOnClick();

        // check if correct request server method was called
        //verify(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture(), argumentCaptor3.capture(), argumentCaptor4.capture(), argumentCaptor5.capture(), argumentCaptor6.capture());   // THIS WILL FAIL
        //assertEquals("testUsername", argumentCaptor1.getValue());
        verify(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture(), argumentCaptor3.capture(), argumentCaptor4.capture(), argumentCaptor5.capture(), argumentCaptor6.capture());
        assertEquals("test_view_presenter1", argumentCaptor1.getValue());


        // check if correct request server method was called
//        activity.findViewById(R.id.submit2).callOnClick();
//        verify(server).(argumentCaptor2.capture());
//        assertEquals("hello", argumentCaptor2.getValue());

    }
}