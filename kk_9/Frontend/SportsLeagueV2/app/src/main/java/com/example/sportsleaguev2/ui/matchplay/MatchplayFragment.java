package com.example.sportsleaguev2.ui.matchplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsleaguev2.R;
import com.example.sportsleaguev2.TestingView;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

import com.example.sportsleaguev2.Presenter.Login_SignUp_Presenter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MatchplayFragment extends Fragment implements TestingView {
    TextView day, month, year;
    EditText savename;
    Button submit;
    Spinner spinner_sport, spinner, spinner_win;
    JSONArray games;
    String winner, match;
    String[] games_match;
    String[][] selected_match;

    int date, game_id;


    private Login_SignUp_Presenter presenter;
    private String serverResponseBody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_matchplay, container, false);

        presenter = new Login_SignUp_Presenter(this, getActivity().getApplicationContext());

        day = root.findViewById(R.id.match_day);
        month = root.findViewById(R.id.match_month);
        year = root.findViewById(R.id.match_year);
        submit = (Button) root.findViewById(R.id.button);
        savename = (EditText) root.findViewById(R.id.InsertMessage);

        try {
            games = new JSONArray(getActivity().getIntent().getExtras().getString("Games"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        games_match = new String[games.length()];
        selected_match = new String[1][2];

        for (int i = 0; i < games.length(); i++) {
            try {
                games_match[i] = games.getJSONObject(i).get("teams").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spinner_sport = root.findViewById(R.id.spinner5);
        spinner_win = root.findViewById(R.id.spinner10);
        spinner = root.findViewById(R.id.spinner4);


        spinner_sport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,games_match);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                  try {
                      date = (Integer) games.getJSONObject(position).get("date");
                      game_id = (Integer) games.getJSONObject(position).get("gameid");
                      day.setText(Integer.toString((Integer) games.getJSONObject(position).get("date")).substring(0,2));
                      month.setText(Integer.toString((Integer) games.getJSONObject(position).get("date")).substring(2,4));
                      year.setText(Integer.toString((Integer) games.getJSONObject(position).get("date")).substring(4,8));
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }

                  selected_match[0] = games_match[position].split("/");
                  match = games_match[position];

                  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, selected_match[0]);
                  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                  spinner_win.setAdapter(adapter);
              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
        });

        spinner_win.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                winner = selected_match[0][position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try {
                      addMatch();
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
        });
//        spinner_win.getOnItemSelectedListener();
//                Log.d("SELECTED", spinner_win.getOnItemSelectedListener().toString());
                // Inflate the layout for this fragment
        return root;
    }

    public void addMatch() throws JSONException {
        Log.d("ID", Integer.toString(game_id));
        Log.d("DATE", Integer.toString(date));
        Log.d("MATCH", match);
        Log.d("WINNER", winner);
        Log.d("USER", getActivity().getIntent().getExtras().getString("email"));


        presenter.addPrediction(game_id, date,"football", match, winner, getActivity().getIntent().getExtras().getString("email"), savename.getText().toString());
        Toast.makeText(getActivity().getApplicationContext(), "Succefully Added Prediction", Toast.LENGTH_LONG).show();
    }
    @Override
    public void updateUserInfoTextView(String info, String key) {
        this.serverResponseBody = info;
    }

    @Override
    public String getResponse() {
        return this.serverResponseBody;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}