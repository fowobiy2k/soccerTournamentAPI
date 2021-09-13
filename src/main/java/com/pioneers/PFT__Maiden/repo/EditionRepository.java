package com.pioneers.PFT__Maiden.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long>{
	Edition findByYear(String year);
}
