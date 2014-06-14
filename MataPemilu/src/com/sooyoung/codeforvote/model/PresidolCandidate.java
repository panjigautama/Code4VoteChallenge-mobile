package com.sooyoung.codeforvote.model;

public class PresidolCandidate {
	
	int id;
	String name;
	int last_updated;
    PresidolSentiment sentiment;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(int last_updated) {
		this.last_updated = last_updated;
	}
	public PresidolSentiment getSentiment() {
		return sentiment;
	}
	public void setSentiment(PresidolSentiment sentiment) {
		this.sentiment = sentiment;
	}

}
