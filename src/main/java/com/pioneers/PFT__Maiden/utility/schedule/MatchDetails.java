package com.pioneers.PFT__Maiden.utility.schedule;

import java.util.List;

import com.pioneers.PFT__Maiden.models.Player;

public class MatchDetails {
	
	private String time;
	private String date;
	private String homeTeam;
	private String awayTeam;
	private String winner;
	private String stage;
	private String title;
	private String homeScore;
	private String awayScore;
	private long matchId;
	private boolean live, ended;
	private List<Player> homeSquad;
	private List<Player> awaySquad;
	private List<Player> homeLineUp;
	private List<Player> awayLineUp;
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public long getMatchId() {
		return matchId;
	}
	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
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
	
	public String getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(String homeScore) {
		this.homeScore = homeScore;
	}
	public String getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(String awayScore) {
		this.awayScore = awayScore;
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
	public List<Player> getHomeSquad() {
		return homeSquad;
	}
	public void setHomeSquad(List<Player> homeSquad) {
		this.homeSquad = homeSquad;
	}
	public List<Player> getAwaySquad() {
		return awaySquad;
	}
	public void setAwaySquad(List<Player> awaySquad) {
		this.awaySquad = awaySquad;
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
	
}
