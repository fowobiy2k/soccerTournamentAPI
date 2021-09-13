package com.pioneers.PFT__Maiden.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.json.simple.JSONObject;


@Entity
@Table(name= "tbl_editions",  uniqueConstraints={@UniqueConstraint(columnNames="year")})
public class Edition {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id")
	private long id;
	private String theme;
	private final String title = "pioneers football tournament";
	private String year;
	private String startDate;
	private String endDate;
	private String drawDate;
	private String screeningDate;
	private boolean isStarted;
	private boolean isCompleted;
	
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "edition_id")
	private List<Guest> guest;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "edition_id")
	private List<Team> teams;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name= "edition_id")
	private List<Match> fixtures;

	public Edition() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDrawDate() {
		return drawDate;
	}

	public void setDrawDate(String drawDate) {
		this.drawDate = drawDate;
	}

	public String getScreeningDate() {
		return screeningDate;
	}

	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}

	public List<Guest> getGuest() {
		return guest;
	}

	public void setGuest(List<Guest> guest) {
		this.guest = guest;
	}

	public String getTitle() {
		return title;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public List<Match> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Match> fixtures) {
		this.fixtures = fixtures;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
