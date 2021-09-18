package com.example.sportsleaguev2;

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

import com.example.sportsleaguev2.Presenter.Login_SignUp_Presenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class LoginActivity extends AppCompatActivity implements TestingView{

    //data received from server
    private String serverResponseBody;
    private TextView serverResponse;

    //used for communicating with server
    private Login_SignUp_Presenter presenter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new Login_SignUp_Presenter(this, getApplicationContext());
        serverResponse = findViewById(R.id.serverResponse);
        initProgressBar();

        //Calling server for list of users
        try {
            presenter.getUserList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText user = (EditText) findViewById(R.id.username);
        final EditText pass = (EditText) findViewById(R.id.password);

        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login(user.getText().toString(), pass.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * initializes progress bar
     */
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
     * Cross references username and password from the user list call when page loads then logs in user
     * @param username
     * @param password
     * @throws JSONException
     */
    public void login(String username, String password) throws JSONException {

        Intent intent = new Intent(this, HomeActivity.class);
        JSONArray array = new JSONArray(this.getResponse());


        for (int i = 0; i < array.length(); i++) {
            JSONObject currObject = array.getJSONObject(i);

            if (username.equals(currObject.getString("username"))) {

                if (password.equals(currObject.getString("password"))) {
                    this.hideProgressBar();
                    intent.putExtra("email", username);
                    intent.putExtra("first_name", currObject.getString("firstname"));
                    intent.putExtra("last_name", currObject.getString("lastname"));
                    intent.putExtra("phone_number", currObject.getString("phone"));
                    intent.putExtra("rank", currObject.getString("leaderboard"));

                    serverResponse.setText("test_system1");
                    if(!username.equals("test_system1")) {
                        startActivity(intent);
                    }
                }

            }

        }
//        CharSequence text = "Username or password did not match with our records.";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
//        toast.show();
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
    public String getResponse() {
        return serverResponseBody;
    }

    /**
     * updates the serverResponseBody string with the response body from the RequestServerForService
     * @param info
     * @return
     */
    @Override
    public void updateUserInfoTextView(String info, String key) {
//        Log.d("USERS: ", info);
        this.serverResponseBody = info;
    }

    /**
     * displays the progress bar on page
     */
    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hides progress bar on screen
     */
    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void test_setPresenter(Login_SignUp_Presenter p) { this.presenter = p;}
    public Login_SignUp_Presenter test_getPresenter() {return presenter;}
}