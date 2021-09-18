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
@Table(name="Store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "storeid")
    @NotFound(action = NotFoundAction.IGNORE)
    private int storeid;

    @Column(name = "item")
    private String item;



    @Column(name="username")
    private String username;


    public Store() {
    }

    /*
     * primary keys are username,savename
     */
    public Store(int storeid,String item,String username) {
        super();
        this.item = item;
        this.username = username;
this.storeid= storeid;

    }


    public String getItem() {
        return item;
    }

    public String getUsername() {
        return username;
    }

    public int getStoreid() {
        return storeid;
    }
}