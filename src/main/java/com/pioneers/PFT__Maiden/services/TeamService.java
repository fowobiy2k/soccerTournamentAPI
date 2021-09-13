package com.pioneers.PFT__Maiden.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Coach;
import com.pioneers.PFT__Maiden.models.Team;
import com.pioneers.PFT__Maiden.repo.CoachRepository;
import com.pioneers.PFT__Maiden.repo.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository repository;
	
	public Team createTeam(Team team) {
		return repository.save(team);
	}
	
	public Team getTeamById(long id) {
		return repository.findById(id).get();
	}
	
	public List<Team> getAllTeams(){
		return repository.findAll();
	}
	
	
	public List<Team> findByName(String name){
		
		List<Team> list = repository.findAll();
		List<Team> output = new ArrayList<Team>();
		
		for(Team team : list) {
			if(team.getName().equalsIgnoreCase(name)) {
				output.add(team);
			}
		}
		
		return output;
	}
}
