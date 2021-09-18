package SportsLeague;


/*
Importing all classes
 */
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.ActiveProfiles;
import sportsLeague.controllers.GameController;
import sportsLeague.controllers.ScheduleController;
import sportsLeague.controllers.UserController;
import sportsLeague.entity.Game;
import sportsLeague.entity.Schedule;
import sportsLeague.entity.User;
import sportsLeague.repo.GameRepository;
import sportsLeague.repo.ScheduleRepository;
import sportsLeague.repo.UserRepository;
import sportsLeague.services.GameService;
import sportsLeague.services.ScheduleService;
import sportsLeague.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 Import Java libraries
 */
import java.util.List;
import java.util.ArrayList;

/*
 import junit/spring tests
 */
//import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/*
 import mockito related
 */
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
@WebMvcTest(controllers = ScheduleController.class)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class TestingDemo4 {

    @Autowired
    private MockMvc controller;

    @MockBean
    private ScheduleService uService;

    @MockBean
    private ScheduleRepository scheduleRepo;

    private List<Schedule> ScheduleList;

    public void main(String[] args) throws Exception {
        setUp();
        CreateMatch();
        FetchMatches();
        setUpWOwinner();
        AddWinner();

    }



    @BeforeEach
    void setUp() {
        this.ScheduleList = new ArrayList<>();
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZVikings", "Bears"));
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZPackers", "Bears"));
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZLions", "Bears"));

    }

    @Test
    public void FetchMatches() throws Exception{
        given(uService.getSchedules()).willReturn(ScheduleList);

        this.controller.perform(get("/schedule/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(ScheduleList.size())));
    }

    @BeforeEach
    void setUpWOwinner() {
        /*
        adds above but without the winner
         */
        this.ScheduleList = new ArrayList<>();
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZVikings", "null"));
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZPackers", "null"));
        this.ScheduleList.add(new Schedule(0,"football", 12152020, "BearsZLions", "null"));

    }

    @Test
    public void AddWinner() throws Exception{
//        given(uService.addToSchedule(any(Schedule.class)))
//                .willAnswer((invocation -> invocation.getArgument(0)));

        Schedule s = new Schedule(0,"football", 12152020, "BearsZLions", "Bears");

        ObjectMapper OM = new ObjectMapper();

        this.controller.perform(post("/schedule/addWinner")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(OM.writeValueAsString(s)))
                .andExpect(status().isOk());
    }

    @Test
    public void CreateMatch() throws Exception{
        given(uService.addToSchedule(any(Schedule.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        Schedule s = new Schedule(0,"football", 12152020, "BearsZTexans", "Bears");

        ObjectMapper OM = new ObjectMapper();

        this.controller.perform(post("/schedule/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(OM.writeValueAsString(s)))
                .andExpect(status().isOk());


    }


}



