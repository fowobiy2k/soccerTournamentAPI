package com.pioneers.PFT__Maiden.models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name= "tbl_teams",  uniqueConstraints={@UniqueConstraint(columnNames="name")})
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	private String name;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinTable(name= "team_coaches")
	@OrderColumn(name= "type")
	private List<Coach> coaches;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "team_id")
	@OrderColumn(name= "type")
	private List<Player> players;
	private String[] colors;
	private String lastStage;

	public Team() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Coach> getCoaches() {
		return coaches;
	}

	public void setCoaches(List<Coach> coaches) {
		this.coaches = coaches;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public String getLastStage() {
		return lastStage;
	}

	public void setLastStage(String lastStage) {
		this.lastStage = lastStage;
	}
	
}
