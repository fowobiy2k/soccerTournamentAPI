package com.pioneers.PFT__Maiden.models;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "tbl_players")
public class Player implements Comparable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	private String teamName;
	private String firstName;
	private String lastName;
	private String position;
	private String weight;
	private String dateOfBirth;
	private int goals;
	private int assists;
	private int yellowCards;
	private int redCards;
	private int caps;
	private int foulsCommitted;
	private int numberOfTimesFouled;
	
//	@OneToMany
//	@JoinTable(name= "tbl_prev")
//	private List<String> previousTeams;
	
	public Player() {
		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}

	public int getCaps() {
		return caps;
	}

	public void setCaps(int caps) {
		this.caps = caps;
	}
	
	public int getNumberOfTimesFouled() {
		return numberOfTimesFouled;
	}
	
	public int getFoulsCommitted() {
		return foulsCommitted;
	}


	public void setFoulsCommitted(int foulsCommitted) {
		this.foulsCommitted = foulsCommitted;
	}


	public void setNumberOfTimesFouled(int numberOfTimesFouled) {
		this.numberOfTimesFouled = numberOfTimesFouled;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	@Override
	public int compareTo(Object p) {
		// TODO Auto-generated method stub
		
		int compareTally = ((Player)p).getGoals();
		
		return compareTally - goals;
	}


//	public List<String> getPreviousTeams() {
//		return previousTeams;
//	}
//
//
//	public void setPreviousTeams(List<String> previousTeams) {
//		this.previousTeams = previousTeams;
//	}
	
}
