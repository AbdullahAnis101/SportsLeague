package sportsLeague.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;


@Entity
@Table(name="Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameid")
    @NotFound(action = NotFoundAction.IGNORE)
    private int gameid;

    @Column(name = "sport")
    private  String sport;

    @Column(name = "date")
    private  int date;


    @Column(name = "teams")
    private String teams;

    @Column(name = "winner")
    private String winner;

//    public Schedule(int i, String string, String string2) {
//    }

    public Schedule(){

    }
    /*
     * primary keys are username,savename
     */
    public Schedule(int gameid,String sport, int date, String teams, String winner) {
        super();
        this.date = date;
        this.sport = sport;
        this.teams = teams;
        this.gameid = gameid;
        this.winner = winner;
    }

    public int getDate() {
        return date;
    }

    public String getSport() {
        return sport;
    }

    public String getteams() {
        return teams;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getGameid() {
        return gameid;
    }
}
