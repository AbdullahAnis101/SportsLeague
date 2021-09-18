package com.example.sportsleaguev2.Presenter;

import android.content.Context;

import com.example.sportsleaguev2.Model.IVolleyListener;
import com.example.sportsleaguev2.Model.RequestServerForService;
import com.example.sportsleaguev2.TestingView;

import org.json.JSONException;

public class Login_SignUp_Presenter implements IVolleyListener {
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
    public void register (String username, String password, String first_name, String last_name, String phone_number, int rank) throws JSONException {
        view.showProgressBar();
        model.signUp(username, password, first_name, last_name, phone_number, rank);
    }

    public void addPrediction (int predictionid, int date, String sport, String teams, String predictionForGame, String username, String savename) throws JSONException {
        model.add_prediction(predictionid, date, sport, teams, predictionForGame, username, savename);
    }

    public void getUserList () throws JSONException {
//        view.showProgressBar();
        model.pullUsers();
    }

    public void getGameSchedule() {
        model.pullSchedule();
    }

    public void getPredictions(String user) {model.pullPredictions(user);}

    /**
     * Updates a String with the returned json object that is in string format
     * @param JSONObject_String
     */
    @Override
    public void onSuccess(String JSONObject_String, String key) {
        view.hideProgressBar();
        view.updateUserInfoTextView(JSONObject_String, key);
    }

    /**
     * Flags error
     * @param JSONObject_String
     */
    @Override
    public void onError(String JSONObject_String) {
        view.hideProgressBar();
        view.updateUserInfoTextView("Error:" + JSONObject_String, "");
    }
}
