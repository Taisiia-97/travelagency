package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

    Continent findContinentByContinentName(String continentName);

    Set<Continent> findByContinentNameIn(Set<String> continents);
}