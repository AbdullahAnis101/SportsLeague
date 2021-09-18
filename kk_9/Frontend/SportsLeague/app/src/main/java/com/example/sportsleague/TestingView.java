package com.example.sportsleague;

import android.view.View;

import org.json.JSONException;

public interface TestingView {
//    void login(View view) throws JSONException;
    void updateUserInfoTextView(String info);
    String getUser();
    void showProgressBar();
    void hideProgressBar();
}
