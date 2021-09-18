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
@Table(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameid")
    @NotFound(action = NotFoundAction.IGNORE)
    private int gameid;

  //  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "score")
   // @NotFound(action = NotFoundAction.IGNORE)
    private int score;

    @Column(name = "sport")
    private String sport;

    @Column(name="username")
    private String username;

    @Column(name="savename")
    private String savename;

    public Game() {
    }

    /*
     * primary keys are username,savename
     */
    public Game(int gameid,int score, String sport, String username, String savename) {
        super();
        this.gameid = gameid;
        this.score = score;
        this.sport = sport;
        this.username = username;
        this.savename=savename;

    }


    public int getScore() {
        return score;
    }

    public String getSport() {
        return sport;
    }

    public String getUsername() {
        return username;
    }

    public String getSavename() {
        return savename;
    }

    public void setSavename(String savename) {
        this.savename = savename;
    }
}