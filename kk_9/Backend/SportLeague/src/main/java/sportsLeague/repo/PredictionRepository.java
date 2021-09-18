/**
 *
 */
package sportsLeague.repo;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sportsLeague.entity.Prediction;


@Repository
public interface PredictionRepository extends JpaRepository<Prediction,Integer> {



}

