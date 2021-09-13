package com.pioneers.PFT__Maiden.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.json.JsonObject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pioneers.PFT__Maiden.models.*;
import com.pioneers.PFT__Maiden.services.*;
import com.pioneers.PFT__Maiden.utility.PropertyAccessor;
import com.pioneers.PFT__Maiden.utility.ReadValues;
import com.pioneers.PFT__Maiden.utility.schedule.FinalMatch;
import com.pioneers.PFT__Maiden.utility.schedule.FirstRound;
import com.pioneers.PFT__Maiden.utility.schedule.MatchDetails;
import com.pioneers.PFT__Maiden.utility.schedule.QuarterFinals;
import com.pioneers.PFT__Maiden.utility.schedule.Schedule;
import com.pioneers.PFT__Maiden.utility.schedule.SemiFinals;;

@RestController
@RequestMapping("/pft")
public class CoreController {

	@Autowired
	private EditionService editionService;

	@Autowired
	private CoachService coachService;

	@Autowired
	private GuestService guestService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private TeamService teamService;

//	@Autowired
//	private Schedule16Service schedule16Service;

	@Autowired
	private MatchService matchService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ArrayTesterService arrayTesterService;

	@Value("${edition}")
	private String edition;

	private String currentTitle = "PFT 2021";

//	@RequestMapping("/createnew")
//	@Produces(MediaType.APPLICATION_JSON)
//	public void createEdition(@RequestParam Edition e ) {
//		service.createEdition(e);
//	}

	@RequestMapping(value = "/edition/createnew", method = RequestMethod.POST)
//	@Produces(MediaType.APPLICATION_JSON)
	public String createEdition(@RequestParam String theme, @RequestParam String year) {
		Edition e = new Edition();
		e.setTheme(theme);
		e.setYear(year);
		editionService.createEdition(e);
		System.out.println("Edition added for " + e.getYear());

		String output = "Edition added for " + e.getYear();
		return output;
	}

	@RequestMapping(value = "/edition/gettitle", method = RequestMethod.GET)
	public String getEditionTitle() {

		Edition e = getCurrentEdition();

		String output = e.getTitle() + " " + edition;
		System.out.println(output);
		return output;
	}

	@RequestMapping(value = "/edition/fetchall", method = RequestMethod.GET)
//	@Produces(MediaType.APPLICATION_JSON)
	public List<Edition> fetchAllEditions() {
		List<Edition> allEditions = editionService.fetchAllEditions();

		return allEditions;
	}

	@RequestMapping(value = "/edition/fetchallyears", method = RequestMethod.GET)
//	@Produces(MediaType.APPLICATION_JSON)
	public List<String> fetchAllEditionYears() {
		List<Edition> allEditions = editionService.fetchAllEditions();

		List<String> years = new ArrayList<String>();

		for (Edition e : allEditions) {
			years.add(e.getYear());
		}

		return years;
	}

	@RequestMapping("/edition/fetchcurrent")
//		@Produces(MediaType.APPLICATION_JSON)
	public Edition getCurrentEdition() {

		Edition e = editionService.findByYear(edition);

//			List<Guest> guests = e.getGuest();
//			System.out.println("Guest name: " + guests.get(0).getName());
		return e;
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	@RequestMapping("/edition/fetchone/{year}")
//		@Produces(MediaType.APPLICATION_JSON)
	public Edition getOneEdition(@PathVariable String year) {

		Edition e = editionService.findByYear(year);
		return e;

	}

	@RequestMapping("/edition/summary")
//		@Produces(MediaType.APPLICATION_JSON)
	public List<String> getEditionSummary() {

		Edition e = editionService.findByYear(edition);

		List<String> output = new ArrayList<String>();
		output.add(e.getTheme());
		output.add(e.getTitle());

		return output;

	}

	@RequestMapping(value = "/modifyedition/createplayer", method = RequestMethod.PUT)
	public void createNewPlayer(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String teamName, @RequestParam String dateOfBirth, @RequestParam String position,
			@RequestParam(defaultValue = "") String weight) {
		Edition e = editionService.findByYear(edition);

		Player player = new Player();
		player.setFirstName(firstName);
		player.setLastName(lastName);
		player.setTeamName(teamName);
		player.setDateOfBirth(dateOfBirth);
		player.setPosition(position);
		player.setWeight(weight);
		
		playerService.createPlayer(player);

		for (Team team : e.getTeams()) {
			if (team.getName().equalsIgnoreCase(teamName)) {
				team.getPlayers().add(player);
				teamService.createTeam(team);
			}
		}

//		System.out.println("Start date modified to " + e.getStartDate());
	}

	// This method modifies theme in the edition with the given id
	@PutMapping("/modifyedition/theme")
	public void modifyTheme(@RequestParam String theme) {
		Edition e = editionService.findByYear(edition);
		e.setTheme(theme);
		editionService.createEdition(e);
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	// This method modifies startDate in the edition with the given id
	@PutMapping("/modifyedition/startdate")
	public void modifyStartDate(@RequestParam String startDate) {
		Edition e = editionService.findByYear(edition);
		e.setStartDate(startDate);
		editionService.createEdition(e);
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	// This method modifies endDate in the Edition with the given id
	@PutMapping("/modifyedition/endDate")
	public void modifyEndDate(@RequestParam String endDate) {
		Edition e = editionService.findByYear(edition);
		e.setEndDate(endDate);
		editionService.createEdition(e);
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	@PutMapping("/modifyedition/drawDate")
	public void modifyDrawDate(@RequestParam String drawDate) {
		Edition e = editionService.findByYear(edition);
		e.setDrawDate(drawDate);
		editionService.createEdition(e);
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	@PutMapping("/modifyedition/screeningdate")
	public void modifyScreeningDate(@RequestParam String screeningDate) {
		Edition e = editionService.findByYear(edition);
		e.setScreeningDate(screeningDate);
		editionService.createEdition(e);
//			System.out.println("Start date modified to " + e.getStartDate());
	}

	@PutMapping("/modifyedition/guests")
	public void modifyGuestList(@RequestParam String name, @RequestParam String title, @RequestParam String office) {
		Edition e = editionService.findByYear(edition);

		Guest guest = new Guest();
		guest.setName(name);
		guest.setTitle(title);
		guest.setOffice(office);

		e.getGuest().add(guest);

		editionService.createEdition(e);

//			System.out.println("Start date modified to " + e.getStartDate());
	}

//		@

	@PutMapping("/modifyedition/teamcreation")
	public void createTeam(@RequestParam String name) {
		Edition e = editionService.findByYear(edition);

		Team team = new Team();
		team.setName(name);
		
		teamService.createTeam(team);

		e.getTeams().add(team);

		editionService.createEdition(e);

	}

	@PutMapping("/modifyedition/createfixture")
	public void modifyFixtures(@RequestParam String homeTeamName, @RequestParam String awayTeamName,
			@RequestParam String matchTime, @RequestParam String matchDate, @RequestParam String title, @RequestParam int matchNumber, @RequestParam String stage ) {
		Edition e = editionService.findByYear(edition);

		Match match = new Match();

//		List<Team> teams = e.getTeams();

//		Team homeTeam = new Team();
//		Team awayTeam = new Team();
		
//		for (Team team : teams) {
//			if (team.getName().equalsIgnoreCase(homeTeamName)) {
//				homeTeam = team;
//				System.out.println("Home team: " + homeTeam.getName());
//			} else {
//				if (team.getName().equalsIgnoreCase(awayTeamName)) {
//					awayTeam = team;
//					System.out.println("Away team: " + awayTeam.getName());
//				}
//			}
//		}
		

		match.setHomeTeam(homeTeamName);
		match.setAwayTeam(awayTeamName);
		System.out.println(match.getHomeTeam() + " VS " + match.getAwayTeam());
		match.setDate(matchDate);
		match.setTitle(title);
		match.setKickOff(matchTime);
		match.setStage(stage);
		match.setMatchNumber(matchNumber);

		e.getFixtures().add(match);

		editionService.createEdition(e);

	}

//	@PutMapping("/modifyedition/createbaseschedule")
//	public void createBaseSchedule(@RequestParam String title, @RequestParam String stage,
//			@RequestParam String matchtime, @RequestParam String matchdate) {
//		Edition e = editionService.findByYear(edition);
//
//		Match match = new Match();
//
////		System.out.println(match.getHomeTeam().getName() + " VS " + match.getAwayTeam().getName());
//		match.setDate(matchdate);
//		match.setKickOff(matchtime);
//		match.setTitle(title);
//		match.setStage(stage);
//
//		e.getFixtures().add(match);
//
//		editionService.createEdition(e);
//
//	}

//	@PutMapping("/modifyedition/setfixture")
//	public void setFixtures(@RequestParam String homeTeamName, @RequestParam String awayTeamName,
//			@RequestParam String title) {
//		Edition e = editionService.findByYear(edition);
//
//		Match match = new Match();
//
//		List<Match> matches = matchService.getAllMatches();
//
//		for (Match m : matches) {
//			if (m.getTitle().equalsIgnoreCase(title)) {
//				match = m;
//			}
//		}
//
//		List<Team> teams = e.getTeams();
//
//		Team homeTeam = new Team();
//		Team awayTeam = new Team();
//
//		for (Team team : teams) {
//			if (team.getName().equalsIgnoreCase(homeTeamName)) {
//				homeTeam = team;
//				System.out.println("Home team: " + homeTeam.getName());
//			} else {
//				if (team.getName().equalsIgnoreCase(awayTeamName)) {
//					awayTeam = team;
//					System.out.println("Away team: " + awayTeam.getName());
//				}
//			}
//		}
//
//		match.setHomeTeam(homeTeam);
//		match.setAwayTeam(awayTeam);
//		System.out.println(match.getHomeTeam().getName() + " VS " + match.getAwayTeam().getName());
//
//		e.getFixtures().add(match);
//
//		editionService.createEdition(e);
//
//	}

	@PutMapping("/modifyedition/fixture/list")
	public void setTeamList(@RequestParam String matchId, @RequestParam String playerId) {
		Edition e = editionService.findByYear(edition);

		long matchIdLong = Long.parseLong(matchId);
		long playerIdLong = Long.parseLong(playerId);

		Player player = playerService.getPlayerById(playerIdLong);

//			Match match = matchService.getMatchById(Long.parseLong(matchId));
//			match.setLive(true);
//			System.out.println(true);
//			
//			matchService.createMatch(match);

		for (Match m : e.getFixtures()) {
			if ((m.getId() == matchIdLong)) {
				if(player.getTeamName().equalsIgnoreCase(m.getHomeTeam())) {
					m.getHomeLineUp().add(player);
				} else m.getAwayLineUp().add(player);
				
				matchService.createMatch(m);
			}
		}
		
//			editionService.createEdition(e);

	}

	@PutMapping("/modifyedition/startlive")
	public void startMatch(@RequestParam String matchId) {
		Edition e = editionService.findByYear(edition);

		long matchIdLong = Long.parseLong(matchId);

//			Match match = matchService.getMatchById(Long.parseLong(matchId));
//			match.setLive(true);
//			System.out.println(true);
//			
//			matchService.createMatch(match);

		for (Match m : e.getFixtures()) {
			if ((m.getId() == matchIdLong) && !(m.isLive()) && !(m.isEnded())) {
				m.setLive(true);
				System.out.println(true);
				matchService.createMatch(m);
			}
		}

//			editionService.createEdition(e);

	}

	@PutMapping("/modifyedition/endmatch")
	public void endMatch(@RequestParam String matchId) {
		Edition e = editionService.findByYear(edition);
		List<Match> fixtures = e.getFixtures();

//			long matchIdLong = Long.parseLong(matchId);

//			Match match = matchService.getMatchById(Long.parseLong(matchId));
//			match.setLive(true);
//			System.out.println(true);
//			
//			matchService.createMatch(match);

		fixtures.forEach((m) -> {
			if ((m.getId() == Long.parseLong(matchId)) && !(m.isEnded())) {
				m.setEnded(true);
				m.setLive(false);
				System.out.println(true);
				matchService.createMatch(m);
			}
		});

//			for(Match m : fixtures) {
//				if((m.getId() == Long.parseLong(matchId)) && !(m.isEnded())){
//					m.setEnded(true);
//					m.setLive(false);
//					System.out.println(true);
//					matchService.createMatch(m);
//					e.getFixtures().add(m);
//				}
//			}

	}
	
	@PutMapping("/modifyEdition/goalevent")
	public void simpleGoalEventUpdate( @RequestParam long playerId, @RequestParam long matchId) {
		
		Match match = matchService.getMatchById(matchId);
		
		Player player = playerService.getPlayerById(playerId);
		Goal goal = new Goal();
		
		goal.setSubject(player);
		goal.setEventType("goal");
		eventService.createEvent(goal);
		
		player.setGoals(player.getGoals() + 1);

		playerService.createPlayer(player);
		
		match.getEvents().add(goal);
		

		if(player.getTeamName().equalsIgnoreCase(match.getAwayTeam())) {
			match.setAwayScore(match.getAwayScore() + 1);
		} else match.setHomeScore(match.getHomeScore() + 1);
		
		matchService.createMatch(match);
		
	}
	
	@PutMapping("/modifyEdition/goalassistevent")
	public void goalEventUpdate(@RequestParam long scorerId, @RequestParam long assistId, @RequestParam long matchId) {
		
		Match match = matchService.getMatchById(matchId);
		
		Player player = playerService.getPlayerById(scorerId);
		Player assistBy = playerService.getPlayerById(assistId);
		
		Goal goal = new Goal();
		
		goal.setSubject(player);
		goal.setAssistBy(assistBy);
		goal.setEventType("goal");
		eventService.createEvent(goal);
		
		player.setGoals(player.getGoals() + 1);
		playerService.createPlayer(player);
		
		assistBy.setAssists(assistBy.getAssists() + 1);
		playerService.createPlayer(assistBy);
		
		match.getEvents().add(goal);
		
		if(player.getTeamName().equalsIgnoreCase(match.getAwayTeam())) {
			match.setAwayScore(match.getAwayScore() + 1);
		} else match.setHomeScore(match.getHomeScore() + 1);
		
		matchService.createMatch(match);
		
	}
	
	@PutMapping("/modifyEdition/foulevent")
	public void foulEventStorage(@RequestParam long subjectId, @RequestParam long concededBy, @RequestParam String card, @RequestParam String setPiece, @RequestParam long matchId ) {
		
		Match match = matchService.getMatchById(matchId);
		
		Player player = playerService.getPlayerById(subjectId);
		player.setNumberOfTimesFouled(player.getNumberOfTimesFouled() + 1);
		playerService.createPlayer(player);
		
		Player foulBy = playerService.getPlayerById(concededBy);
		Foul foul = new Foul();
		foul.setEventType("foul");
		
		foul.setSubject(player);
		foul.setConcededBy(foulBy);
		foul.setCardShown(card);
		foul.setSetPieceAwarded(setPiece);
		eventService.createEvent(foul);
		
		foulBy.setFoulsCommitted(foulBy.getFoulsCommitted() + 1);
		if( card.equalsIgnoreCase("yellow")) {
			foulBy.setYellowCards(foulBy.getYellowCards() + 1);
		} else {
			if(card.equalsIgnoreCase("red")) {
				foulBy.setRedCards(foulBy.getRedCards() + 1);
			}
		} 
		

		playerService.createPlayer(foulBy);
		
		match.getEvents().add(foul);
		matchService.createMatch(match);
	}
	

	@PutMapping("/test/foul/{id}")
	public void testFoulEventStorage(@PathVariable long id, @RequestParam long concededBy, @RequestParam String card, @RequestParam String setPiece) {

//		Player player = playerService.getPlayerById(id);
//
//		int currentGoalCount = player.getGoals();
//		player.setGoals(currentGoalCount + 1);
//
//		playerService.createPlayer(player);
		
		Player player = playerService.getPlayerById(id);
		Player foulBy = playerService.getPlayerById(concededBy);
		Foul foul = new Foul();
		
		foul.setSubject(player);
		foul.setConcededBy(foulBy);
		foul.setCardShown(card);
		foul.setSetPieceAwarded(setPiece);
		eventService.createEvent(foul);
		
		if( card.equalsIgnoreCase("yellow")) {
			foulBy.setYellowCards(foulBy.getYellowCards() + 1);
		} else foulBy.setRedCards(foulBy.getRedCards() + 1);
		

		playerService.createPlayer(foulBy);
	}



	@RequestMapping("/test")
//		@Produces(MediaType.APPLICATION_JSON)
	public Edition testEndPoint() {

		Edition e = editionService.findById(8);
		System.out.println(e.toString());

		return e;
	}

	@RequestMapping("/coach/fetchbydesignation/{designation}")
	public List<Coach> getCoachesByDesignation(@PathVariable String designation) {

		return coachService.findByDesignation(designation);

	}

	@RequestMapping("/coach/fetchbyfirstname/{firstName}")
	public List<Coach> getCoachesByFirstName(@PathVariable String firstName) {

		return coachService.findByFirstName(firstName);

	}

	@RequestMapping("/coach/fetchbylastname/{lastName}")
	public List<Coach> getCoachesByLastName(@PathVariable String lastName) {

		return coachService.findByLastName(lastName);

	}

	@RequestMapping(value = "/player/fetchall", method = RequestMethod.GET)
	public List<Player> fetchAllPlayer() {
		return playerService.getAllPlayers();

	}

	@RequestMapping("/player/fetchone/{id}")
	public Player getOnePlayer(@PathVariable long id) {

		return playerService.getPlayerById(id);

	}

	@RequestMapping("/player/fetchbyfirstname/{firstName}")
	public List<Player> getPlayersByFirstName(@PathVariable String firstName) {

		return playerService.findByFirstName(firstName);

	}

	@RequestMapping("/player/fetchbylastname/{lastName}")
	public List<Player> getPlayersByLastName(@PathVariable String lastName) {

		return playerService.findByFirstName(lastName);

	}

	@RequestMapping("/player/fetchbyposition/{position}")
	public List<Player> getPlayersByPosition(@PathVariable String position) {

		return playerService.findByPosition(position);

	}

	@RequestMapping(value = "/team/fetchall", method = RequestMethod.GET)
	public List<Team> fetchAllTeams() {
		return teamService.getAllTeams();

	}

	@RequestMapping("/team/fetchone/{id}")
	public Team getOneTeam(@PathVariable long id) {

		return teamService.getTeamById(id);

	}

	@RequestMapping("/team/fetchbyname/{name}")
	public List<Team> getTeamByName(@PathVariable String name) {

		return teamService.findByName(name);

	}
	
	@RequestMapping("/team/fetchsquad/{name}")
	public List<Player> getTeamSquad(@PathVariable String name) {

		return teamService.findByName(name).get(0).getPlayers();

	}

	@RequestMapping(value = "/match/fetchall", method = RequestMethod.GET)
	public List<Match> fetchAllMatches() {
		return matchService.getAllMatches();

	}

	@RequestMapping("/match/fetchone/{id}")
	public Match getOneMatch(@PathVariable long id) {

		return matchService.getMatchById(id);

	}
	
	@RequestMapping("/match/fetchHomeLineup/{id}")
	public List<Player> getHomeLineup(@PathVariable long id) {

		Match m = matchService.getMatchById(id);
		
		List<Player> homeLineup = m.getHomeLineUp();
		
		return homeLineup;

	}
	
	@RequestMapping("/match/fetchAwayLineup/{id}")
	public List<Player> getAwayLineup(@PathVariable long id) {

		Match m = matchService.getMatchById(id);
		
		List<Player> awayLineup = m.getAwayLineUp();
		
		return awayLineup;

	}

	@RequestMapping("/event/fetchone/{id}")
	public Event getOneEvent(@PathVariable long id) {

		return eventService.getEventById(id);

	}

	@RequestMapping(value = "/event/fetchall", method = RequestMethod.GET)
	public List<Event> fetchAllEvents() {
		return eventService.getAllEvents();

	}

//	@RequestMapping("/event/fetchbytype/{eventType}")
//	public List<Event> getEventByType(@PathVariable String eventType) {
//
//		// Expected values for eventType ["yellow card", "red card", "goal", "penalty"]
//		return eventService.findByType(eventType);
//
//	}
	
	@PutMapping("/modifyplayer/goals/{id}")
	public void modifyPlayerGoals(@PathVariable long id, @RequestParam int goalIncrement) {

		Player player = playerService.getPlayerById(id);

		int currentGoalCount = player.getGoals();
		player.setGoals(currentGoalCount + goalIncrement);

		playerService.createPlayer(player);
	}

	@PutMapping("/modifyplayer/assist/{id}")
	public void modifyPlayerAssists(@PathVariable long id, @RequestParam int assistIncrement) {

		Player player = playerService.getPlayerById(id);

		int currentAssistCount = player.getAssists();
		player.setGoals(currentAssistCount + assistIncrement);

		playerService.createPlayer(player);
	}

	@PutMapping("/modifyplayer/yellow/{id}")
	public void modifyPlayerYellow(@PathVariable long id, @RequestParam int yellowIncrement) {

		Player player = playerService.getPlayerById(id);

		int currentYellowCount = player.getYellowCards();
		player.setGoals(currentYellowCount + yellowIncrement);

		playerService.createPlayer(player);
	}

	@PutMapping("/modifyplayer/red/{id}")
	public void modifyPlayerRed(@PathVariable long id, @RequestParam int redIncrement) {

		Player player = playerService.getPlayerById(id);

		int currentRedCount = player.getRedCards();
		player.setGoals(currentRedCount + redIncrement);

		playerService.createPlayer(player);
	}

	@PutMapping("/modifyplayer/caps/{id}")
	public void modifyPlayerCaps(@PathVariable long id, @RequestParam int capIncrement) {

		Player player = playerService.getPlayerById(id);

		int currentCapCount = player.getCaps();
		player.setGoals(currentCapCount + capIncrement);

		playerService.createPlayer(player);
	}

	@RequestMapping(value = "/createtester", method = RequestMethod.POST)
	public void createArrayTester() {

		Player p1 = new Player();
		p1.setLastName("Folly");
		p1.setFirstName("Lucio");

		Player p2 = new Player();
		p1.setLastName("Glen");
		p1.setFirstName("Mauricio");

//			Player[] players = {p1, p2};
		String[] players = { String.valueOf(p1.getId()), String.valueOf(p2.getId()) };

		Team t1 = new Team();
		t1.setName("Mighty FC");
		String[] tColor1 = { "Brown", "White" };
		t1.setColors(tColor1);

		Team t2 = new Team();
		t1.setName("Landers FC");
		String[] tColor2 = { "Blue", "White" };
		t2.setColors(tColor2);

//			Team[] teams = {String.valueOf(t1.getId())};
		String[] teams = { String.valueOf(t1.getId()), String.valueOf(t2.getId()) };

		ArrayTester arrayTester = new ArrayTester();
		arrayTester.setMvp_list(players);
		arrayTester.setTeamList(teams);

		arrayTesterService.createTester(arrayTester);

	}

	@RequestMapping("/gettesters")
	public List<ArrayTester> getTesters() {

		JSONArray arr = new JSONArray();
		JSONParser parser = new JSONParser();

		String s = "{\"user1\" : {\"name1\" : \"n1\", \"name2\" : \"n2\"}, \"spec1\" : {\"length\" : \"l1\"}}";

		try {
			Object o = parser.parse(s);
			JSONObject obj = (JSONObject) o;

			System.out.println(obj);
			System.out.println(obj.get("user1"));
			System.out.println(obj.values());
			System.out.println("Key sets: " + obj.keySet());

		} catch (ParseException p) {
			System.out.println("ERROR!!!!!!!!!!");
		}

		return arrayTesterService.getAll();

	}

	@RequestMapping("/getschedule")
	public Schedule generateShedule() {
		
		JsonObject obj;
		Schedule schedule = new Schedule();
		FirstRound firstRound = new FirstRound();
		QuarterFinals quarterFinals = new QuarterFinals();
		SemiFinals semiFinals = new SemiFinals();
		FinalMatch finals = new FinalMatch();
		Edition e = new Edition();
		
		ArrayList<Match> firstRoundList = new ArrayList<Match>() {{
			add(null); add(null); add(null); add(null);
		}};
		System.out.println("First  list: " + firstRoundList.size());
//		ArrayList<Match> quarterFinalList = new ArrayList<Match>() {{
//			add(null); add(null); add(null); add(null);
//		}};
		ArrayList<Match> semiFinalList = new ArrayList<Match>() {{
			add(null); add(null);
		}};
		Match finalMatch = new Match();
		
		List<Match> listOfMatches = matchService.getAllMatches();
//		System.out.println("Number of matches: " + listOfMatches.size());
//		System.out.println("Number of matches: " + listOfMatches.get(0).getKickOff());
		
		for(Match m : listOfMatches) {
			if( m.getTitle().equalsIgnoreCase("Game 1")) {
				firstRoundList.set(0, m);
			} else {
				if( m.getTitle().equalsIgnoreCase("Game 2")) {
					firstRoundList.set(1, m);
				} else {
					if( m.getTitle().equalsIgnoreCase("Game 3")) {
						firstRoundList.set(2, m);
					} else {
						if( m.getTitle().equalsIgnoreCase("Game 4")) {
							firstRoundList.set(3, m);
						} else {
							if(m.getTitle().equalsIgnoreCase("SF 1")) {
								semiFinalList.set(0, m);
							} else {
								if(m.getTitle().equalsIgnoreCase("SF 2")) {
									semiFinalList.set(1, m);
								} else finalMatch = m;
							}
						}
					}
				}
			}
		}

		if ( true ) {System.out.println("Inside IF block");
			firstRound = fetchFirstRound( firstRoundList );
//			quarterFinals = fetchQuarters( quarterFinalList, firstRoundList );
//			semiFinals = fetchSemis( semiFinalList, quarterFinalList );
			semiFinals = fetchSemis( semiFinalList, firstRoundList );
			finals = fetchFinal( finalMatch, semiFinalList );
		}
		
		schedule.setFirstRound(firstRound);
//		schedule.setQuarterFinals(quarterFinals);
		schedule.setSemiFinals(semiFinals);
		schedule.setFinals(finals);

		return schedule;
	}

	public FirstRound fetchFirstRound(List<Match> listOfMatches) {
		
		System.out.println("Number of matches: " + listOfMatches.size());

		FirstRound fr = new FirstRound();
		
		System.out.println("Starting for loop");
		
		for( int n = 0; n < listOfMatches.size(); n++ ) {
			
			System.out.println("n in first round: " + n);
			
			MatchDetails md = new MatchDetails();
			md.setAwayTeam(listOfMatches.get(n).getAwayTeam());
			md.setHomeTeam(listOfMatches.get(n).getHomeTeam());
			md.setDate(listOfMatches.get(n).getDate());
			md.setTime(listOfMatches.get(n).getKickOff());
			md.setMatchId(listOfMatches.get(n).getId());
//			md.setAwayScore(listOfMatches.get(n).getAwayScore());
//			md.setHomeScore(listOfMatches.get(n).getHomeScore());
			md.setEnded(listOfMatches.get(n).isEnded());
			md.setLive(listOfMatches.get(n).isLive());
			md.setTitle(listOfMatches.get(n).getTitle());
			md.setStage(listOfMatches.get(n).getStage());
			
			md.setAwayLineUp(listOfMatches.get(n).getAwayLineUp());
			md.setHomeLineUp(listOfMatches.get(n).getHomeLineUp());
			
			if( listOfMatches.get(n).getAwayTeam() instanceof String && listOfMatches.get(n).getHomeTeam() instanceof String ) {
				
				String homeTeamStr = listOfMatches.get(n).getHomeTeam();
				String awayTeamStr = listOfMatches.get(n).getAwayTeam();
				
				List<Player> homeSquad = teamService.findByName(homeTeamStr).get(0).getPlayers();
				List<Player> awaySqaud = teamService.findByName(awayTeamStr).get(0).getPlayers();
				
				md.setAwayLineUp(awaySqaud);
				md.setHomeSquad(homeSquad);
			}
			
			if( !listOfMatches.get(n).isLive() || !listOfMatches.get(n).isEnded() ) {
				md.setAwayScore("?");
				md.setHomeScore("?");
			}
			
			if( !( listOfMatches.get(n).getWinner() == null ) ) {
				md.setWinner(listOfMatches.get(n).getWinner());
			}
			
			switch( n ) {
			case 0: 
				fr.setGame1(md);
				break;
				
			case 1:
				fr.setGame2(md);
				break;
				
			case 2:
				fr.setGame3(md);
				break;
				
			case 3:
				fr.setGame4(md);
				break;
				
//			case 4: 
//				fr.setGame5(md);
//				break;
//				
//			case 5: 
//				fr.setGame6(md);
//				break;
//				
//			case 6: 
//				fr.setGame7(md);
//				break;
//				
//			case 7: 
//				fr.setGame8(md);
//				break;
			}
			
			
		}

		return fr;

	}
	
//	public QuarterFinals fetchQuarters(List<Match> quarters, List<Match> firstRound){
//		
//		QuarterFinals qf = new QuarterFinals();
//		
//		
//		for( int n = 0; n < quarters.size(); n++ ) {
//			
//			MatchDetails md = new MatchDetails();
//			md.setDate(quarters.get(n).getDate());
//			md.setTime(quarters.get(n).getKickOff());
//			md.setMatchId(quarters.get(n).getId());
//			md.setTitle(quarters.get(n).getTitle());
//			md.setEnded(quarters.get(n).isEnded());
//			md.setLive(quarters.get(n).isLive());
//			
//			md.setAwayLineUp(quarters.get(n).getAwayLineUp());
//			md.setHomeLineUp(quarters.get(n).getHomeLineUp());
//			
//			
//			if( quarters.get(n).getAwayTeam() instanceof Team && quarters.get(n).getHomeTeam() instanceof Team ) {
//				md.setAwaySquad(quarters.get(n).getAwayTeam().getPlayers());
//				md.setHomeSquad(quarters.get(n).getHomeTeam().getPlayers());
//			}
//			
//			if( !quarters.get(n).isLive() || !quarters.get(n).isEnded() ) {
//				md.setAwayScore("?");
//				md.setHomeScore("?");
//			}
//			
//			if( quarters.get(n).getWinner() != null ) {
//				md.setWinner(quarters.get(n).getWinner().getName());
//			}
//			
//			int upper = (n * 2) + 1;
//			int lower = upper - 1;
//			
//			if( firstRound.get(lower).getWinner() != null ) {
//				
//				System.out.println("Lower: " + firstRound.get(lower).getTitle());
//				System.out.println("Upper: " + firstRound.get(upper).getTitle());
//				
//				md.setHomeTeam(firstRound.get(lower).getWinner().getName());
//			} else {
//				md.setHomeTeam("Winner " + firstRound.get(lower).getTitle());
//			}
//			
//			if( firstRound.get(upper).getWinner() != null ) {
//				md.setAwayTeam(firstRound.get(upper).getWinner().getName());
//			} else {
//				md.setAwayTeam("Winner " + firstRound.get(upper).getTitle());
//			}
//			
//			switch( n ) {
//			case 0: 
//				qf.setQf1(md); System.out.println("0: " + qf.getQf1().getTitle());
//				break;
//				
//			case 1:
//				qf.setQf2(md); System.out.println("1: " + qf.getQf2().getTitle());
//				break;
//				
//			case 2:
//				qf.setQf3(md); System.out.println("2: " + qf.getQf3().getTitle());
//				break;
//				
//			case 3:
//				qf.setQf4(md); System.out.println("3: " + qf.getQf4().getTitle());
//				break;
//			}
//		}
//		
//		return qf;
//	}
	
	public SemiFinals fetchSemis( List<Match> semis, List<Match> quarters ) {
		
		SemiFinals sf = new SemiFinals();
		
		for( int n = 0; n < semis.size(); n++) {
			
			MatchDetails md = new MatchDetails();
			md.setDate(semis.get(n).getDate());
			md.setTime(semis.get(n).getKickOff());
			md.setMatchId(semis.get(n).getId());
			md.setTitle(semis.get(n).getTitle());
			md.setEnded(semis.get(n).isEnded());
			md.setLive(semis.get(n).isLive());
			
			md.setAwayLineUp(semis.get(n).getAwayLineUp());
			md.setHomeLineUp(semis.get(n).getHomeLineUp());
			
			
			if( semis.get(n).getAwayTeam().length() > 1 && semis.get(n).getHomeTeam().length() > 1 )  {
				
				String homeTeamStr = semis.get(n).getHomeTeam();
				String awayTeamStr = semis.get(n).getAwayTeam();
				
				System.out.println("homeTeamStr");
				System.out.println(homeTeamStr);
				System.out.println("awayTeamStr");
				System.out.println(awayTeamStr);
				
				List<Player> homeSquad = teamService.findByName(homeTeamStr).get(0).getPlayers();
				List<Player> awaySqaud = teamService.findByName(awayTeamStr).get(0).getPlayers();
				
				
				md.setAwayLineUp(awaySqaud);
				md.setHomeSquad(homeSquad);
			} else {
				
			}
			
			int upper = (n * 2) + 1;
			int lower = upper - 1;
			
			if( !semis.get(n).isLive() || !semis.get(n).isEnded() ) {
				md.setAwayScore("?");
				md.setHomeScore("?");
			}
			
			if(quarters.get(lower).getWinner() != null ) {
				md.setHomeTeam(quarters.get(lower).getWinner());
			} else {
				md.setHomeTeam( "Winner" + quarters.get(lower).getTitle());
			}
			
			if( quarters.get(upper).getWinner() != null ) {
				md.setAwayTeam(quarters.get(upper).getWinner());
			} else {
				md.setAwayTeam("Winner " + quarters.get(upper).getTitle());
			}
			
			switch( n ) {
			case 0:
				sf.setSemiFinal1(md);
				break;
				
			case 1: 
				sf.setSemiFinal2(md);
			}
		}
		
		return sf;
	}
	
	public FinalMatch fetchFinal(Match finalGame, List<Match> semis) {
		
		FinalMatch finalMatch = new FinalMatch();
		MatchDetails md = new MatchDetails();
		md.setTitle(finalGame.getTitle());
		md.setDate(finalGame.getDate());
		md.setTime(finalGame.getKickOff());
		md.setStage(finalGame.getStage());
		md.setMatchId(finalGame.getId());
		
		md.setAwayLineUp(finalGame.getAwayLineUp());
		md.setHomeLineUp(finalGame.getHomeLineUp());
		
		
		if( finalGame.getAwayTeam().length() > 1 && finalGame.getHomeTeam().length() > 1 ) {
			
			String homeTeamStr = finalGame.getHomeTeam();
			String awayTeamStr = finalGame.getAwayTeam();
			
			List<Player> homeSquad = teamService.findByName(homeTeamStr).get(0).getPlayers();
			List<Player> awaySqaud = teamService.findByName(awayTeamStr).get(0).getPlayers();
			
			
			md.setAwayLineUp(awaySqaud);
			md.setHomeSquad(homeSquad);
			
		}
		
		if( semis.get(0).getWinner() != null ) {
			md.setHomeTeam(semis.get(0).getWinner());
		} else md.setHomeTeam("Winner " + semis.get(0).getTitle());
		
		if( semis.get(1).getWinner() != null ) {
			md.setAwayTeam(semis.get(1).getWinner());
		} else md.setAwayTeam("Winner" + semis.get(1).getTitle());
		
		finalMatch.setFinalMatch(md);
		
		return finalMatch;
	}
	
	@RequestMapping("/scorers")
	public List<Player> fetchScorers(){
		List<Player> scorers = new ArrayList<Player>();
		
		List<Player> players = playerService.getAllPlayers();
		
		for(Player player : players) {
			if( player.getGoals() > 0 ) {
				scorers.add(player);
			}
		}
		
		Collections.sort(scorers);
		System.out.println( scorers );
		
		return scorers;
	}

}
