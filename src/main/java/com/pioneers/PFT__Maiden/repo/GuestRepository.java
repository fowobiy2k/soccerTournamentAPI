package com.pioneers.PFT__Maiden.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pioneers.PFT__Maiden.models.Guest;



@Repository
public interface GuestRepository extends JpaRepository<Guest, Long>{

}
