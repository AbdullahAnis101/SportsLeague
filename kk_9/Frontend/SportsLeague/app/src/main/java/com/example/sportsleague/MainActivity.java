package com.example.sportsleague;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sportsleague.Presenter.Login_SignUp_Presenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.UnsupportedEncodingException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TestingView{
    public static final String EXTRA_MESSAGE = "com.example.SportsLeague.MESSAGE";

    private String name, password, serverResponseBody;;

    private Login_SignUp_Presenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Login_SignUp_Presenter(this, getApplicationContext());
        initProgressBar();

        try {
            presenter.getUserList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                Resources.getSystem().getDisplayMetrics().widthPixels, 250
        );
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        hideProgressBar();
    }

    /**
     * Parses the username and password from layout page and calls helper method validateUserName
     * @param view
     * @throws JSONException
     */
    public void login(View view) throws JSONException {
        this.showProgressBar();
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);
        String username = user.getText().toString();
        String password = pass.getText().toString();

        validateUser(username, password);
    }

    /**
     * Cross references username and password with database
     * @param username
     * @param password
     * @throws JSONException
     */
    public void validateUser(String username, String password) throws JSONException {
        Intent intent = new Intent(this, HomeActivity.class);

        JSONArray array = new JSONArray(this.getUser());

        for (int i = 0; i < array.length(); i++) {
            JSONObject currObject = array.getJSONObject(i);
            String name_cur = currObject.getString("name");
            String pass_cur = currObject.getString("password");
            if (username.equals(name_cur)) {
                if (password.equals(pass_cur)) {
                    this.hideProgressBar();
                    intent.putExtra("name", username);
                    startActivity(intent);
                }
            }
        }
        CharSequence text = "Username or password did not match with our records.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }


    /**
     * Navigates to signup page
     * @param view
     */
    public void nav_to_signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /**
     * returns the response body from the server call in RequestServerForService
     * @return
     */
    @Override
    public String getUser() {
        return serverResponseBody;
    }

    /**
     * updates the serverResponseBody string with the response body from the RequestServerForService
     * @param info
     * @return
     */
    @Override
    public void updateUserInfoTextView(String info) {
//        Log.d("USERS: ", info);
        this.serverResponseBody = info;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void test_setPresenter(Login_SignUp_Presenter p) { this.presenter = p;}

    public Login_SignUp_Presenter test_getPresenter() {return presenter;}

}