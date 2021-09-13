package com.pioneers.PFT__Maiden.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

}
