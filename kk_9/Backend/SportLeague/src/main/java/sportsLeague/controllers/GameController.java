package sportsLeague.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sportsLeague.entity.Game;
import sportsLeague.entity.Store;
import sportsLeague.entity.User;
import sportsLeague.services.GameService;
import sportsLeague.services.UserService;

@RestController
//@RequestMapping
public class GameController {

    @Autowired
    GameService GS;

    @RequestMapping("/game")
    public List<Game> getAllGames() {
        List<Game> games = GS.getAllGames();
        return games;
    }

    @RequestMapping("/game/get")
    public List<Game> getGameByName(@RequestBody String nametoGet) {
        List<Game> getGame = GS.getGameByName(nametoGet);
        return getGame;
    }


    //@RequestMapping(method = RequestMethod.POST, path = "/user/")
    @PostMapping("/game/add" )
    public void setName (@RequestBody Game game) {
        GS.addGame(game);
    }


//    @RequestMapping(method = RequestMethod.DELETE, value = "/user")
//    public void deleteUser(@PathVariable String userName) {
//        // userService.deleteUser(userName);
//    }




}
