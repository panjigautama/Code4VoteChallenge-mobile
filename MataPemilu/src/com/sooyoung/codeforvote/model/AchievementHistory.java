package com.sooyoung.codeforvote.model;

import android.content.Context;

import com.orm.SugarRecord;

public class AchievementHistory extends SugarRecord<AchievementHistory> {

	Long id;
	String candidateId;
	String entityName;
	String yearAchieved;
	String institution;

	public AchievementHistory(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getYearAchieved() {
		return yearAchieved;
	}

	public void setYearAchieved(String yearAchieved) {
		this.yearAchieved = yearAchieved;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
