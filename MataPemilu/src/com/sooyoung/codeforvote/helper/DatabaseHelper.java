package com.sooyoung.codeforvote.helper;

import java.util.List;

import com.orm.query.Condition;
import com.orm.query.Select;
import com.sooyoung.codeforvote.model.AchievementHistory;
import com.sooyoung.codeforvote.model.Candidate;
import com.sooyoung.codeforvote.model.EducationHistory;
import com.sooyoung.codeforvote.model.WorkHistory;

/**
 * Global helper for retrieving data from database
 * 
 * @author aldi
 * 
 */
public class DatabaseHelper {

	/**
	 * Get candidate list
	 * 
	 * @return List of all available candidates
	 */
	public static List<Candidate> getCandidateList() {
		return Candidate.listAll(Candidate.class);
	}

	/**
	 * Get candidate by specified id
	 * 
	 * @param id
	 *            Id of the candidate {"jw", "jk", "ps", "hr"}
	 * @return Candidate object
	 */
	public static Candidate getCandidateById(String id) {
		return (Candidate) Select.from(Candidate.class)
				.where(Condition.prop("candidateId").eq(id)).list().get(0);
	}

	/**
	 * Get education history of the specified candidate
	 * 
	 * @param id
	 *            Id of the candidate {"jw", "jk", "ps", "hr"}
	 * @return List of education history of the specified candidate
	 */
	public static List<EducationHistory> getEducationHistoryOfCandidate(
			String id) {
		return EducationHistory.find(EducationHistory.class, "candidateId = ?",
				id);
	}

	/**
	 * Get work history of the specified candidate
	 * 
	 * @param id
	 *            Id of the candidate {"jw", "jk", "ps", "hr"}
	 * @return List of work history of the specified candidate
	 */
	public static List<WorkHistory> getWorkHistoryOfCandidate(String id) {
		return WorkHistory.find(WorkHistory.class, "candidateId = ?", id);
	}

	/**
	 * Get achievement history of the specified candidate
	 * 
	 * @param id
	 *            Id of the candidate {"jw", "jk", "ps", "hr"}
	 * @return List of achievement history of the specified candidate
	 */
	public static List<AchievementHistory> getAchievementHistoryOfCandidate(
			String id) {
		return AchievementHistory.find(AchievementHistory.class,
				"candidateId = ?", id);
	}

}
