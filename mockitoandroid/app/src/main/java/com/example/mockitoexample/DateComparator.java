package com.example.mockitoexample;

import android.util.Log;

import java.util.Comparator;

public class DateComparator implements Comparator<Event> {



    @Override
    public int compare(Event o1, Event o2) {
        //Time is in format M/D/YYYY

        int[] timeE = new int[3];
        int[] timeO = new int[3];
        int result = 0;
        timeE[0] = Integer.parseInt(o1.getEventTime().split("/")[0])*1000;
        timeE[1] = Integer.parseInt(o1.getEventTime().split("/")[1])*10;
        timeE[2] = Integer.parseInt(o1.getEventTime().split("/")[2])*100000;

        timeO[0] = Integer.parseInt(o2.getEventTime().split("/")[0])*1000;
        timeO[1] = Integer.parseInt(o2.getEventTime().split("/")[1])*10;
        timeO[2] = Integer.parseInt(o2.getEventTime().split("/")[2])*100000;

        result = (timeO[0] + timeO[1] + timeO[2]) - (timeE[0] + timeE[1] + timeE[2]);

        if(result < 0)
        {
            Log.d("Sorted: ", o1.getEventTime() + " > " + o2.getEventTime() + " val: "+ result);
            return 1;
        }
        if (result > 0)
        {
            Log.d("Sorted: ", o1.getEventTime() + " < " + o2.getEventTime() + " " + result);
            return -1;
        }
        Log.d("Sorted: ", o1.getEventTime() + " = " + o2.getEventTime() + " " + result);
        return  0;
    }

}
