/**
 *
 */
package sportsLeague.repo;

import org.springframework.stereotype.Repository;

import sportsLeague.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {



}

