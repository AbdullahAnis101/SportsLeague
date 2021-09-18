package com.example.mockitoexample;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SortByDateTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();



@Test
public void SortByDateTest_returnsTrue() throws JSONException {
    //The sorter we are trying to test
    Sorter sorter = new Sorter();

    //The mocked (unimplemented) class that has a non-functional method (.getEventList())
    ListGetter l = mock(ListGetter.class);


    List<Event> simulatedResponse = new ArrayList<>();
    List<Event> expectedResponse = new ArrayList<>();
    List<Event> fetchedResponse = new ArrayList<>();
    List<Event> sortedResponse = new ArrayList<>();

    //Expected sorted response
    expectedResponse.add(new Event("CS Town Hall Meeting", "3/10/2019"));
    expectedResponse.add(new Event("Google Meet And Greet", "3/17/2019"));
    expectedResponse.add(new Event("John Deere Luncheon", "3/20/2019"));
    expectedResponse.add(new Event("Group Meeting", "4/18/2019"));

    //Unsorted response, simulation of returned event list
    fetchedResponse.add(new Event("Google Meet And Greet", "3/17/2019"));
    fetchedResponse.add(new Event("CS Town Hall Meeting", "3/10/2019"));
    fetchedResponse.add(new Event("Group Meeting", "4/18/2019"));
    fetchedResponse.add(new Event("John Deere Luncheon", "3/20/2019"));

    //Mocks behavior of calling unimplemented method (.getEventList())
    when(l.getEventList()).thenReturn(fetchedResponse);

    //Adds the event list into a list that will be sorted
    simulatedResponse.addAll(l.getEventList());

    //Tests the correctness of comparator
    sortedResponse.addAll(sorter.sortByDate(simulatedResponse));

    for (int i = 0; i < sortedResponse.size(); i++) {
        assertSame(expectedResponse.get(i).getEventTime(), sortedResponse.get(i).getEventTime());
    }

}

}