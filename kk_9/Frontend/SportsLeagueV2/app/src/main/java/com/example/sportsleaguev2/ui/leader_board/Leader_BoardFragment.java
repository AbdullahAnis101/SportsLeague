package com.example.sportsleaguev2.ui.leader_board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportsleaguev2.R;
import com.example.sportsleaguev2.TestingView;

import org.json.JSONArray;
import org.json.JSONException;

public class Leader_BoardFragment extends Fragment implements TestingView {
    EditText savegames;
    JSONArray games;
    private Leader_BoardViewModel leaderBoardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        leaderBoardViewModel = ViewModelProviders.of(this).get(Leader_BoardViewModel.class);
//                ViewModelProviders.of(this).get(Leader_BoardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_leader_board, container, false);
        savegames = root.findViewById(R.id.textView8);

        try {
            games = new JSONArray(getActivity().getIntent().getExtras().getString("Predictions"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(getActivity().getIntent().getExtras().getString("Predictions") !=  null) {
            for (int i = 0; i < games.length(); i++) {
                try {
                    savegames.append("Save Name: " + games.getJSONObject(i).get("savename").toString());
                    savegames.append("\n" + "Prediction: " + games.getJSONObject(i).get("predictionForGame").toString());
                    savegames.append("\n" + "Match Up: " + games.getJSONObject(i).get("date").toString());
                    savegames.append("\n" + "Match Up: " + games.getJSONObject(i).get("teams").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return root;
    }

    @Override
    public void updateUserInfoTextView(String info, String key) {

    }

    @Override
    public String getResponse() {
        return null;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}