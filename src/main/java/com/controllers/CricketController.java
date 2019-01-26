package com.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.Dao.Impl.CricketDao;
import com.google.gson.Gson;
import com.pojo.IplResponse;
import com.pojo.Score;
import com.pojo.Team;

@Controller
public class CricketController {

	CricketDao dao = new CricketDao();
	
	static String baseUrl = "http://192.168.0.16:8080";

	@RequestMapping(value = "/showScore")
	public String showScore(Model model) {
		System.out.println("fetching score!!");

//		Need to hit url from here
		// Hit rest api and get score
		final String uri = baseUrl + "//crick-app/ipl/getScore";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
		// Convert json to score java object
		Gson gson = new Gson();
		Score score = gson.fromJson(result, Score.class);

		model.addAttribute("score", score);
		return "score";
	}

	@RequestMapping(value = "/registerTeam", method = RequestMethod.POST)
	public String registerTeam(Model model, Team team) {
		System.out.println(team.getTeamName());
		// Hit rest api from here to register team for ipl
		
		RestTemplate restTemplate = new RestTemplate();

		// prepare headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);

		Gson gson = new Gson();
		String teamJson = gson.toJson(team);

		HttpEntity<String> entity = new HttpEntity<String>(teamJson, header);

		ResponseEntity<String> result = restTemplate.exchange("http://192.168.0.16:8080/crick-app/ipl/registerForIpl",
				HttpMethod.POST, entity, String.class);
		System.out.println("Response is : " + result.getBody());
		
		IplResponse iplResponse = gson.fromJson(result.getBody(), IplResponse.class);
		
		if(iplResponse.getErrorCode().equals("000")) {
			String responseData = iplResponse.getResponseData();
			Team responseTeam = gson.fromJson(responseData, Team.class);
			if(responseTeam.getStatus().equals("Accepted")) {
				dao.registerTeam(responseTeam);
				model.addAttribute("message", "you are in for ipl!!");
			} else {
				model.addAttribute("message", "You are not qualified!! Try next time!!");
			}
		} else {
			model.addAttribute("message", iplResponse.getErrorMessage());
		}
		
		return "Home";
	}

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		// prepare headers
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);

		Team team = new Team();
		team.setTeamName("SRH");
		team.setTotalMatches(240);
		team.setWins(210);
		team.setLoses(30);
		team.setTie(0);
		
		Gson gson = new Gson();
		String teamJson = gson.toJson(team);

		HttpEntity<String> entity = new HttpEntity<String>(teamJson, header);

		ResponseEntity<String> result = restTemplate.exchange("http://192.168.0.9:8080/cricket-app/ipl/registerForIpl",
				HttpMethod.POST, entity, String.class);

		if(result.getStatusCode().value() != 201) {
			System.out.println("Something went wrong with reegistration");
		}
		System.out.println(result.getBody());
		System.out.println(result.getStatusCode());
		System.out.println(result.getHeaders().getContentType());
		System.out.println(result);

	}
}
