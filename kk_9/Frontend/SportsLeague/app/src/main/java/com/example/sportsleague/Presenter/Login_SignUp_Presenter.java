package com.example.sportsleague.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.sportsleague.Model.IVolleyListener;
import com.example.sportsleague.Model.RequestServerForService;
import com.example.sportsleague.TestingView;

import org.json.JSONException;

public class Login_SignUp_Presenter implements IVolleyListener{
    private RequestServerForService model;
    private TestingView view;
    private Context context;

    public Login_SignUp_Presenter(TestingView view, Context c) {
        this.view    = view;
        this.context = c;
        this.model   = new RequestServerForService(c, this);
    }

    public void setModel(RequestServerForService m) {
        this.model = m;
    }

    /**
     * adds new user to the database
     * @param username
     * @param password
     */
    public void register (String username, String password) throws JSONException {
        view.showProgressBar();
        model.signUp(username, password);
    }

    public void getUserList () throws JSONException {
//        view.showProgressBar();
        model.pullUsers();
    }

    /**
     * Updates a String with the returned json object that is in string format
     * @param JSONObject_String
     */
    @Override
    public void onSuccess(String JSONObject_String) {
        view.hideProgressBar();
        view.updateUserInfoTextView(JSONObject_String);
    }

    /**
     * Flags error
     * @param JSONObject_String
     */
    @Override
    public void onError(String JSONObject_String) {
        view.hideProgressBar();
        view.updateUserInfoTextView("Error:" + JSONObject_String);
    }
}
