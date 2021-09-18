package sportsLeague.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sportsLeague.entity.Game;
import sportsLeague.entity.Prediction;
import sportsLeague.services.PredictionService;


@RestController
//@RequestMapping
public class PredictionController {

    @Autowired
    PredictionService GS;

//    @RequestMapping("/prediction")
//    public List<Prediction> getAllGames() {
//        List<Prediction> games = GS.getAllGames();
//        return games;
//    }

    @RequestMapping("/prediction/get")
    public List<Prediction> getPredictionByName(@RequestBody String nametoGet) {
        List<Prediction> getPrediction = GS.getPredictionByName(nametoGet);
        return getPrediction;
    }

    @RequestMapping("/prediction/getByMatch")
    public Prediction getPredictionByMatch(@RequestBody String Get) {
        Prediction p = GS.getPredictionforGame(Get);
        return p;
    }

    @PostMapping("/prediction/add" )
    public void setName (@RequestBody Prediction prediction) {
        GS.addPrediction(prediction);
    }









}
