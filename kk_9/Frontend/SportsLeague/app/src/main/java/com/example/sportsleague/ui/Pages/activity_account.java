package com.example.sportsleague.ui.Pages;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sportsleague.R;

public class activity_account extends Fragment {
//    String username;
    TextView welcomeMes, usersName, username;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_account, container,false);
//        username = getIntent().getStringExtra("name");
//        username = "Brady";
//        Log.d("YES", "okay your in");
//        Bundle bundle = getArguments();
//        if(bundle != null) {
//            Log.d("TEST2: ", bundle.getString("message"));
            welcomeMes = (TextView) view.findViewById(R.id.textWelcomeMes);
            usersName = (TextView) view.findViewById(R.id.first_last_name);
            username = (TextView) view.findViewById(R.id.username);

//            usersName.setText("brady synstelien");
//            username.setText("brady.synstelien@gmail.com");
            welcomeMes.setText("Welcome brady");
//        }
        return view;
    }

}