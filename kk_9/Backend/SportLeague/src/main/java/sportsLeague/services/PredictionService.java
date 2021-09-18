/**
 *
 */
package sportsLeague.services;

import org.springframework.stereotype.Service;

import sportsLeague.entity.Game;
import sportsLeague.entity.Prediction;
import sportsLeague.entity.Store;
import sportsLeague.entity.User;
import sportsLeague.repo.GameRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import sportsLeague.repo.PredictionRepository;

/**
 * @author
 * Richard Gonzalez
 * Jacob Kinser
 *
 *
 */

@Service
public class PredictionService {

    @Autowired
    public PredictionRepository predictionRepo;




    public void addPrediction(Prediction p) {
        predictionRepo.save(new Prediction(0,p.getDate(),p.getSport(),p.getTeams(), p.getPredictionForGame(),p.getUsername(),p.getSavename()));
    }


    public List<Prediction> getPredictionByName(String nametoGet) {
        List<Prediction> returnList = new ArrayList<>();
        List<Prediction> allpredictions = new ArrayList<>();
        predictionRepo.findAll().forEach(allpredictions::add);
        for(int i = 0; i < allpredictions.size();i++){
            if(allpredictions.get(i).getUsername().equals(nametoGet)){
                returnList.add(allpredictions.get(i));
            }
        }


        return returnList;

    }

    public Prediction getPredictionforGame(String info){//info = username/BearsZPackers
        String[] a = info.split("/");
        String sn = a[0];
        String un = a[1];
        String game =a[2];
        Prediction returnP = null;
        List<Prediction> allpredictions = new ArrayList<>();
        predictionRepo.findAll().forEach(allpredictions::add);
        for(int i = 0; i < allpredictions.size();i++){
            if(allpredictions.get(i).getUsername().equals(un) && allpredictions.get(i).getTeams().equals(game) && allpredictions.get(i).getSavename().equals(sn)){
                returnP = allpredictions.get(i);
            }
        }
        return returnP;
    }
}

