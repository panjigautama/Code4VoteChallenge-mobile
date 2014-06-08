package com.sooyoung.codeforvote.model;

import android.content.Context;

import com.orm.SugarRecord;

public class Candidate extends SugarRecord<Candidate> {

	String candidateId;
	String name;
	String role;
	String gender;
	String religion;
	String birthPlace;
	long birthDate;
	String maritalStatus;
	String spouseName;
	int numOfChild;
	String hometownCity;
	String hometownRegion;
	String party;
	String biography;

	public Candidate(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public long getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(long birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public int getNumOfChild() {
		return numOfChild;
	}

	public void setNumOfChild(int numOfChild) {
		this.numOfChild = numOfChild;
	}

	public String getHometownCity() {
		return hometownCity;
	}

	public void setHometownCity(String hometownCity) {
		this.hometownCity = hometownCity;
	}

	public String getHometownRegion() {
		return hometownRegion;
	}

	public void setHometownRegion(String hometownRegion) {
		this.hometownRegion = hometownRegion;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

}
