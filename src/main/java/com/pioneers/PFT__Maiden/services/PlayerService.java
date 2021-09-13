package com.pioneers.PFT__Maiden.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Player;
import com.pioneers.PFT__Maiden.repo.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;
	
	public void createPlayer(Player player) {
		repository.save(player);
	}
	
	public Player getPlayerById(long id) {
		return repository.findById(id).get();
	}
	
	public List<Player> getAllPlayers(){
		return repository.findAll();
	}
	
	public List<Player> findByPosition(String position){
		
		List<Player> list = repository.findAll();
		List<Player> output = new ArrayList<Player>();
		
		for(Player player : list) {
			if(player.getPosition().equalsIgnoreCase(position)) {
				output.add(player);
			}
		}
		
		return output;
	}
	
	public List<Player> findByFirstName(String firstName){
		
		List<Player> list = repository.findAll();
		List<Player> output = new ArrayList<Player>();
		
		for(Player player : list) {
			if(player.getFirstName().equalsIgnoreCase(firstName)) {
				output.add(player);
			}
		}
		
		return output;
	}
	
	public List<Player> findByLastName(String lastName){
		
		List<Player> list = repository.findAll();
		List<Player> output = new ArrayList<Player>();
		
		for(Player player : list) {
			if(player.getLastName().equalsIgnoreCase(lastName)) {
				output.add(player);
			}
		}
		
		return output;
	}
}
