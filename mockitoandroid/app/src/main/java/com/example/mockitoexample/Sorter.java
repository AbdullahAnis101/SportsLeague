package com.example.mockitoexample;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Sorter {
    public List<Event> sortByDate(List<Event> unsortedEvents) {

        List<Event> toSort = new ArrayList<Event>();
        toSort.addAll(unsortedEvents);
        Collections.sort(toSort,new DateComparator());
        for (int i = 0; i < toSort.size(); i++)
        {
            Log.d("Test ", "sortByDate: " + toSort.get(i).getEventTime());
        }
        return toSort;
    }
}
