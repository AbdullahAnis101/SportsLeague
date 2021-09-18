package sportsLeague.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="Prediction")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "predictionid")
    @NotFound(action = NotFoundAction.IGNORE)
    private int predictionid;


    @Column(name = "date")
    private int date;

    @Column(name = "sport")
    private String sport;

    @Column(name="teams")
    private String teams;


    @Column(name="predictionForGame")
    private String predictionForGame;

    @Column(name="savename")
    private String savename;

    @Column(name="username")
    private String username;


    public Prediction() {
    }

    /*
     * primary keys are username,savename
     */
    public Prediction(int predictionid,int date, String sport, String teams, String predictionForGame, String username, String savename) {
        super();
        this.predictionid = predictionid;
        this.date = date;
        this.sport = sport;
        this.teams = teams;
        this.predictionForGame = predictionForGame;
        this.username = username;
        this.savename = savename;

    }

    public int getPredictionid() {
        return predictionid;
    }

    public int getDate() {
        return date;
    }

    public String getSport() {
        return sport;
    }

    public String getTeams() {
        return teams;
    }

    public String getPredictionForGame() {
        return predictionForGame;
    }

    public String getUsername() {
        return username;
    }

    public String getSavename() {
        return savename;
    }
}