package com.sooyoung.codeforvote.model;

import android.content.Context;

import com.orm.SugarRecord;

public class EducationHistory extends SugarRecord<EducationHistory> {

	Long id;
	String candidateId;
	String schoolName;
	String startYear;
	String endYear;

	public EducationHistory(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

}
