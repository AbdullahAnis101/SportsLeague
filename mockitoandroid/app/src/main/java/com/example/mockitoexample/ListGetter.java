package com.example.mockitoexample;

import java.util.List;

public class ListGetter {

    /**
     * This is the method that is being mocked.
     * Currently, this method returns null by default.
     * Using Mockito, we can simulate desired functionality (in this case, retrieving an
     * list of events from the server) without having to wait on the backend.
     * @return
     */
    public List<Event> getEventList()
    {
        return null;
    }
}
