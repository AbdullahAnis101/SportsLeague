package com.example.mockitoexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public boolean tryLogin(String username, String password, LoginHandler loginHandler) throws JSONException {

        //Does not work because .getResponse has not been implemented
        if (loginHandler.getResponse(username, password).getBoolean("loginSuccess")) {
            return true;
        }

        return false;
    }






}

