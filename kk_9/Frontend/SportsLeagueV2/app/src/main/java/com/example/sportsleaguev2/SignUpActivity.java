package com.example.sportsleaguev2;

import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpActivity extends AppCompatActivity implements TestingView{

    private Login_SignUp_Presenter presenter;
    private ProgressBar progressBar;

    private TextView serverResponse;
    private String serverResponseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new Login_SignUp_Presenter(this, getApplicationContext());
        serverResponse = findViewById(R.id.serverResponse);
        initProgressBar();

        final EditText userName = findViewById(R.id.username);
        final EditText firstName = findViewById(R.id.first_name);
        final EditText lastName = findViewById(R.id.last_name);
        final EditText phoneNumber = findViewById(R.id.phone_number);
        final EditText passOne = findViewById(R.id.password);
        final EditText passTwo = findViewById(R.id.password2);

        Button signUpButton = findViewById(R.id.btn_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signUp(userName.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString(), passOne.getText().toString(), passTwo.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void signUp(String username, String first_name, String last_name, String phone_number, String password1, String password2) throws JSONException {
        Intent intent = new Intent(this, HomeActivity.class);

        if(password1.equals(password2)) {
            presenter.register(username, password1, first_name, last_name, phone_number,0);
            intent.putExtra("email", username);
            intent.putExtra("first_name", first_name);
            intent.putExtra("last_name", last_name);
            intent.putExtra("phone_number", phone_number);
            intent.putExtra("rank", 0);

            if(!username.equals("test_system1")) {
                startActivity(intent);
            }

        } else {
            CharSequence text = "Passwords did not match";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }
    }


    /**
     * returns the response from the server call in RequestServerForService
     * @return
     */
    @Override
    public String getResponse() {
        return serverResponse.getText().toString();
    }

    /**
     * updates the serverResponseBody string with the response body from the RequestServerForService
     * @param info
     * @return
     */
    @Override
    public void updateUserInfoTextView(String info, String key) {
        if(!info.equals("")) {
            Log.d("test1", serverResponseBody);
            serverResponseBody = info;
        } else {
            serverResponseBody = "null";
        }
        serverResponse.setText(serverResponseBody);
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