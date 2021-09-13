package com.pioneers.PFT__Maiden.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pioneers.PFT__Maiden.models.Match;
import com.pioneers.PFT__Maiden.repo.MatchRepository;

@Service
public class MatchService {

	@Autowired
	private MatchRepository repository;

	public void createMatch(Match match) {
		repository.save(match);
	}

	public Match getMatchById(long id) {
		return repository.findById(id).get();
	}

	public List<Match> getAllMatches() {System.out.println("Match service says: ");
		return repository.findAll(); 
	}

//	public List<Match> findByTeam(String teamName) {
//
//		List<Match> list = repository.findAll();
//		List<Match> output = new ArrayList<Match>();
//
//		for (Match match : list) {
//			if (teamName.equalsIgnoreCase(match.getTeams().get(0).getName())
//					|| teamName.equalsIgnoreCase(match.getTeams().get(1).getName())) {
//				output.add(match);
//			}
//		}
//
//		return output;
//	}

//	public List<Match> findHomeMatch(String teamName) {
//
//		List<Match> list = repository.findAll();
//		List<Match> output = new ArrayList<Match>();
//
//		for (Match match : list) {
//			if (teamName.equalsIgnoreCase(match.getTeams().get(0).getName())) {
//				output.add(match);
//			}
//		}
//
//		return output;
//	}
//
//	public List<Match> findAwayMatch(String teamName) {
//
//		List<Match> list = repository.findAll();
//		List<Match> output = new ArrayList<Match>();
//
//		for (Match match : list) {
//			if (teamName.equalsIgnoreCase(match.getTeams().get(1).getName())) {
//				output.add(match);
//			}
//		}
//
//		return output;
//	}

}
