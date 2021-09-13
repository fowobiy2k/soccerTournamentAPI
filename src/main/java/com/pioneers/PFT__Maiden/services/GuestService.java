package com.pioneers.PFT__Maiden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Guest;
import com.pioneers.PFT__Maiden.repo.GuestRepository;

@Service
public class GuestService {
	
	@Autowired
	private GuestRepository repository;
	
	public void createGuest(Guest g) {
		repository.save(g);
	}
	
	public List<Guest> fetchAllGuests(){
		return repository.findAll();
	}
	
	public Guest findById(long id) {
		return repository.findById(id).get();
	}
}
