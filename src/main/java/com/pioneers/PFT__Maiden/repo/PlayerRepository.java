package com.pioneers.PFT__Maiden.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
	List<Player> findByPosition(String position);
}
