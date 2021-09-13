package com.pioneers.PFT__Maiden.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Coach;
import com.pioneers.PFT__Maiden.repo.CoachRepository;

@Service
public class CoachService {
	
	@Autowired
	private CoachRepository repository;
	
	public void createCoach(Coach coach) {
		repository.save(coach);
	}
	
	public Coach getCoachById(long id) {
		return repository.findById(id).get();
	}
	
	public List<Coach> getAllCoaches(){
		return repository.findAll();
	}
	
	public List<Coach> findByDesignation(String designation){
		
		List<Coach> list = repository.findAll();
		List<Coach> output = new ArrayList<Coach>();
		
		for(Coach coach : list) {
			if(coach.getDesignation().equalsIgnoreCase(designation)) {
				output.add(coach);
			}
		}
		
		return output;
	}
	
	public List<Coach> findByLastName(String lastName){
		
		List<Coach> list = repository.findAll();
		List<Coach> output = new ArrayList<Coach>();
		
		for(Coach coach : list) {
			if(coach.getLastName().equalsIgnoreCase(lastName)) {
				output.add(coach);
			}
		}
		
		return output;
	}

	public List<Coach> findByFirstName(String firstName){
	
	List<Coach> list = repository.findAll();
	List<Coach> output = new ArrayList<Coach>();
	
	for(Coach coach : list) {
		System.out.println(coach.getFirstName());
		if(coach.getFirstName().equalsIgnoreCase(firstName)) {
			output.add(coach); 
		}
	}
	
	return output;
}

}
