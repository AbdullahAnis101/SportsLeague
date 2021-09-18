package SportsLeague;


/*
Importing all classes
 */
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.ActiveProfiles;
import sportsLeague.controllers.GameController;
import sportsLeague.controllers.UserController;
import sportsLeague.entity.Game;
import sportsLeague.entity.Schedule;
import sportsLeague.entity.User;
import sportsLeague.repo.GameRepository;
import sportsLeague.repo.UserRepository;
import sportsLeague.services.GameService;
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
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class TestingControllerUnit {

    @Autowired
    private MockMvc controller;

    @MockBean
    private UserService uService;
    
    @MockBean
    private GameService GameService;
    
    @MockBean
    private Schedule ScheduleService;

    @MockBean
    private UserRepository userRepo;

    private List<User> userList;
    
    private List<Game> gameList;
    
  
    public void main(String[] args) throws Exception {
       setUp();
        CreateUser();
        FetchUsers();
        
        CreateGame();
        FetchGame();
        
        
    }

    @BeforeEach
   void setUp() {
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
                
        String pw = "password";
        this.userList.add(new User(0,"TestUser1",pw,0,"fn","ln","5555555555"));
        this.userList.add(new User(0,"TestUser2",pw,0,"fn","ln","5555555555"));
        this.userList.add(new User(0,"TestUser3",pw,0,"fn","ln","5555555555"));
        this.gameList.add(new Game(10 , 50, "football", "rg", "mysave"));
    }

    @Test
     public void FetchUsers() throws Exception{
        given(uService.getAllUsers()).willReturn(userList);
        this.controller.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(userList.size())));
    }
    @Test
    public void CreateUser() throws Exception{
        given(uService.addUser(any(User.class)))
                .willAnswer((invocation -> invocation.getArgument(0)));

        User user = new User(0,"TestUser4","password",0,"fn","ln","5555555555");

        ObjectMapper OM = new ObjectMapper();

      this.controller.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(OM.writeValueAsString(user)))
                .andExpect(status().isOk());
               // .andReturn().getResponse().getContentAsString();
//              .andExpect(content().string(user.getName()))
//              .andExpect(content().string(user.getPassword()));

    }
    
    @Test
    public void CreateGame() throws Exception{
    	
    	GameService.addGame(any(Game.class));
        Game game = new Game(0,10,"football","rG","mysave");
        ObjectMapper OM = new ObjectMapper();
        
      this.controller.perform(post("/game/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(OM.writeValueAsString(game)));  
      	
    }
   
    @Test
    public void FetchGame() throws Exception{
    	
        given(GameService.getAllGames()).willReturn(gameList);
        this.controller.perform(get("/game/get"));
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.size()",is(gameList.size())));
        
    }
}


