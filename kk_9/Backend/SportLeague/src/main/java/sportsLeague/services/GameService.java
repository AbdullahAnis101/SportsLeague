/**
 *
 */
package sportsLeague.services;

import org.springframework.stereotype.Service;

import sportsLeague.entity.Game;
import sportsLeague.entity.Store;
import sportsLeague.entity.User;
import sportsLeague.repo.GameRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * Richard Gonzalez
 * Jacob Kinser
 *
 *
 */

@Service
public class GameService {

    @Autowired
    public GameRepository gameRepo;


    public List<Game> getAllGames() {
        List<Game> findgames = new ArrayList<>();
        gameRepo.findAll().forEach(findgames::add);
        return findgames;
    }

    public void addGame(Game game) {
        gameRepo.save(new Game(0,0,game.getSport(),game.getUsername(),game.getSavename()));
    }


    public List<Game> getGameByName(String nametoGet) {
        List<Game> returnList = new ArrayList<>();
        List<Game> allGames = new ArrayList<>();
        gameRepo.findAll().forEach(allGames::add);
        for(int i = 0; i < allGames.size();i++){
            if(allGames.get(i).getUsername().equals(nametoGet)){
                returnList.add(allGames.get(i));
            }
        }


        return returnList;

    }
}

