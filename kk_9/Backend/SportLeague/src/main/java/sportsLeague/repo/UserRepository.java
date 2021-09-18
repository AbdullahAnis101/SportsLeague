/**
 * 
 */
package sportsLeague.repo;

import org.springframework.stereotype.Repository;

import sportsLeague.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author richardgonzalez
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	
	
}
