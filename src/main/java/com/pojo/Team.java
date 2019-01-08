package com.pojo;

public class Team {

	private String teamName;
	private int totalMatches;
	private int wins;
	private int loses;
	private int tie;
	private String status="Rejected";
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public double getTotalMatches() {
		return totalMatches;
	}
	public void setTotalMatches(int totalMatches) {
		this.totalMatches = totalMatches;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLoses() {
		return loses;
	}
	public void setLoses(int loses) {
		this.loses = loses;
	}
	public int getTie() {
		return tie;
	}
	public void setTie(int tie) {
		this.tie = tie;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
