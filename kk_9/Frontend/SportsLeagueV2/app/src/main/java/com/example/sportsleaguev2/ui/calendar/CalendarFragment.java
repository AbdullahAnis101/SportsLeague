package com.example.sportsleaguev2.ui.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportsleaguev2.R;

public class CalendarFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private CalendarViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        Log.d("SCHEDULE: ", getActivity().getIntent().getExtras().getString("Games"));
        String [] values  = {"Select a Team","Bears", "Vikings", "Packers"};
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        Spinner spinnner = root.findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnner.setAdapter(adapter);

        Log.d("USER_calendar", getActivity().getIntent().getExtras().toString());
        Log.d("GAME_LIST", getActivity().getIntent().getExtras().getString("Games"));
        CalendarView calendarView = (CalendarView) root.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                dateDisplay.setText("Date: " + dayOfMonth + " / " + month + " / " + year);
                Toast.makeText(getActivity().getApplicationContext(), "Bears vs Packers @ 12:00 p.m Central Time", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getParentFragment().getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

