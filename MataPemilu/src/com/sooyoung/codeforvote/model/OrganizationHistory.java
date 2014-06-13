package com.sooyoung.codeforvote.model;

import android.content.Context;

import com.orm.SugarRecord;

public class OrganizationHistory extends SugarRecord<OrganizationHistory> {

	String candidateId;
	String entityName;
	String position;
	String startDate;
	String endDate;

	public OrganizationHistory(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

}
