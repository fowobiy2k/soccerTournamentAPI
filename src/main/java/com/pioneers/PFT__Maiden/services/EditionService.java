package com.pioneers.PFT__Maiden.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Edition;
import com.pioneers.PFT__Maiden.repo.EditionRepository;

@Service
public class EditionService {
	
	@Autowired
	private EditionRepository repository;
	
	public void createEdition(Edition e) {
		repository.save(e);
	}
	
	public List<Edition> fetchAllEditions(){
		return repository.findAll();
	}
	
	public Edition findById(long id) {
		return repository.findById(id).get();
	}
	
	public Edition findByYear(String year) {
		return repository.findByYear(year);
	}
}
