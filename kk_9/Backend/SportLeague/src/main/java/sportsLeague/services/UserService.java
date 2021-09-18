/**
 * 
 */
package sportsLeague.services;

import org.springframework.stereotype.Service;

import sportsLeague.entity.User;
import sportsLeague.repo.UserRepository;

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
public class UserService {

	@Autowired
	public UserRepository userRepo;

	/**
	 * Returns a list of all Users found with the Repository. The user Repository makes a call to the find all method
     * from the JPA repo and uses a for each loop to add all users found to the list.
     * <p>
     * This method always returns immediately, whether or not there are users
     * which exist in the table. If users are found then they will be added to a list and returned.
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> user = new ArrayList<>();
		userRepo.findAll().forEach(user::add);
		return user;
	}
	/**
	 * This method takes in user object 
	 * and the user repo makes a method call to the jpa
	 * then passing in for user to saved
	 * @param user
	 */
	//changed void -> user
	public User addUser(User user) {
		userRepo.save(new User(user.getUserid(),user.getUsername(),user.getPassword(),user.getLeaderboard(),user.getFirstname(),user.getLastname(),user.getPhone()));
		return user;
	}
	/**
	 * This method takes in user object 
	 * then user method makes a method call to the jpa
	 * then passing in user to be deleted
	 * @param userName
	 */
	public void deleteUser(String userName) {
		// userRepo.deleteById(userName);
	}


	public User getUserByName(String nametoGet) {
		//System.out.println(nametoGet);
		ArrayList<User> user = new ArrayList<>();
		userRepo.findAll().forEach(user::add);
		User ReturnUser;
		for(int i = 0; i < user.size();i++){
			if(user.get(i).getUsername().equals(nametoGet)){
				//System.out.println(user.get(i).getName());
				ReturnUser = user.get(i);
				return ReturnUser;
			}
		}

		return null;
	}
}
