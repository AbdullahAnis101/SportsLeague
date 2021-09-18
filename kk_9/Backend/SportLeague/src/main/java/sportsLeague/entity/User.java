package sportsLeague.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	@NotFound(action = NotFoundAction.IGNORE)
	private int userid;

	@Column(name = "username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="leaderboard")
	private int leaderboard;

	@Column(name="firstname")
	private String firstname;

	@Column(name="lastname")
	private String lastname;

	@Column(name="phone")
	private String phone;

	public User() {
	}

    /* @param userid
     * @param name
     */
	public User(int userid, String username,String password,int leaderboard,String firstname, String lastname,String phone) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.leaderboard = leaderboard;
		this.phone = phone;
//		int strength = 10; // work factor of bcrypt
//		BCryptPasswordEncoder bCryptPasswordEncoder =
//				new BCryptPasswordEncoder(strength, new SecureRandom());
//		this.password = bCryptPasswordEncoder.encode(password);
	}


    /* @return the userID
     */
	public int getUserid() {
		return userid;
	}

    /* @param userID the userID to set
     */
			 // public void setUserID(int userID) {
			 //     this.userID = userID;
			 // }

			 /* @return the name
     */
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.username = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getLeaderboard() {
		return leaderboard;
	}

	public String getPhone() {
		return phone;
	}
}