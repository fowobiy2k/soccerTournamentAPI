package com.pioneers.PFT__Maiden.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.json.simple.JSONObject;

@Entity
@Table(name= "tbl_matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	
	private String homeTeam;
	
	private String awayTeam;
	
	private String winner;
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name= "match_home_lineup")
	private List<Player> homeLineUp;
	
	@ManyToMany(cascade= CascadeType.ALL)
	@JoinTable(name= "match_away_lineup")
	private List<Player> awayLineUp;
	
//	@ManyToMany(cascade= CascadeType.ALL)
//	@JoinTable(name= "match_lineup")
//	private List<Player> list;
	
	@OneToMany
	@JoinTable(name= "match_events")
	private List<Event> events;
	
	
	private int homeScore;
	private int awayScore;
	private int matchNumber;
	private String date;
	private String kickOff;
	
//	private String homeLineUp;
//	private String awayLineUp; 
	
	private String stage;
	private String title;
	private boolean live, ended;
	
	public Match() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public List<Player> getHomeLineUp() {
		return homeLineUp;
	}

	public void setHomeLineUp(List<Player> homeLineUp) {
		this.homeLineUp = homeLineUp;
	}

	public List<Player> getAwayLineUp() {
		return awayLineUp;
	}

	public void setAwayLineUp(List<Player> awayLineUp) {
		this.awayLineUp = awayLineUp;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKickOff() {
		return kickOff;
	}

	public void setKickOff(String kickOff) {
		this.kickOff = kickOff;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}
	
		
}
