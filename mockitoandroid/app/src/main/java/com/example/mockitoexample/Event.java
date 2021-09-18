package com.example.mockitoexample;

import java.util.Comparator;

public class Event  {

    private String eventTitle;

    //Time is in format M/D/YYYY
    private String eventTime;


    public Event(String title, String time) {
        this.eventTitle = title;
        this.eventTime = time;
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }




}
