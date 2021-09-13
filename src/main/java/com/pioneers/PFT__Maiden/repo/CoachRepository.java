package com.pioneers.PFT__Maiden.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>{
	List<Coach> findByDesignation(String designation);
	List<Coach> findByLastName(String lastName);
	List<Coach> findByFirstName(String firstName);
}
