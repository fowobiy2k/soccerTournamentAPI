package com.pioneers.PFT__Maiden.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{

}
