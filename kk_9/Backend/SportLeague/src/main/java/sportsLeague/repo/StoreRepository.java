package sportsLeague.repo;

import org.springframework.stereotype.Repository;

import sportsLeague.entity.Store;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author richardgonzalez
 *
 */

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {



}