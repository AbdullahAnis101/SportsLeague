/**
 *
 */
package sportsLeague.repo;

import org.springframework.stereotype.Repository;

import sportsLeague.entity.Game;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {



}

