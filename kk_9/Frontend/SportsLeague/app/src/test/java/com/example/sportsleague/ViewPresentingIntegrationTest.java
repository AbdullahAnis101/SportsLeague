package com.example.sportsleague;

import android.os.Build;
import android.widget.EditText;

import com.example.sportsleague.Model.RequestServerForService;
import com.example.sportsleague.Presenter.Login_SignUp_Presenter;

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
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class ViewPresentingIntegrationTest {

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

        doNothing().when(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture());


        // enter text
        EditText stringEntry = activity.findViewById(R.id.username);
        stringEntry.setText("testUsername");

        // click the continue button
        activity.findViewById(R.id.btn_signup).callOnClick();

        // check if correct request server method was called
        //verify(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture());   // THIS WILL FAIL
        //assertEquals("testUsername", argumentCaptor2.getValue());
        verify(server).signUp(argumentCaptor1.capture(), argumentCaptor2.capture());
        assertEquals("testUsername", argumentCaptor1.getValue());


//        // check if correct request server method was called
//        activity.findViewById(R.id.submit2).callOnClick();
//        verify(server).capitalize(argumentCaptor2.capture());
//        assertEquals("hello", argumentCaptor2.getValue());

    }
}
