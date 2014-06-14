package com.sooyoung.codeforvote;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.ListView;

import com.sooyoung.codeforvote.adapter.CandidateCompareListAdapter;
import com.sooyoung.codeforvote.helper.DatabaseHelper;
import com.sooyoung.codeforvote.model.AchievementHistory;
import com.sooyoung.codeforvote.model.Candidate;
import com.sooyoung.codeforvote.model.EducationHistory;
import com.sooyoung.codeforvote.model.OrganizationHistory;
import com.sooyoung.codeforvote.model.WorkHistory;

@EFragment(R.layout.fragment_candidate_compare)
public class CandidateCompareFragment extends Fragment {

	@ViewById(R.id.compare_candidate_image_view)
	ImageView mCandidateImage;

	@ViewById(R.id.compare_list_view)
	ListView mDataListView;

	@FragmentArg
	String id;

	@FragmentArg
	int position;

	CandidateCompareListAdapter mAdapter;
	Candidate mCandidate;
	List<String> mLabelList;

	public static CandidateCompareFragment createFragment(String id,
			int position) {
		CandidateCompareFragment fragment = new CandidateCompareFragment_();
		Bundle args = new Bundle();
		args.putString("id", id);
		args.putInt("position", position);
		fragment.setArguments(args);
		return fragment;
	}

	@AfterViews
	void initialize() {
		mCandidate = DatabaseHelper.getCandidateById(id);

		if (id == "jw") {
			mCandidateImage.setImageResource(R.drawable.jw_vs);
		} else if (id == "jk") {
			mCandidateImage.setImageResource(R.drawable.jk_vs);
		} else if (id == "ps") {
			mCandidateImage.setImageResource(R.drawable.pb_vs);
		} else if (id == "hr") {
			mCandidateImage.setImageResource(R.drawable.ht_vs);
		}
		mLabelList = new ArrayList<String>();
		String biodata = processBiodata();
		String educationHistory = processEducation();
		String workHistory = processWork();
		String achievementHistory = processAchievement();
		String organizationHistory = processOrganization();

		mLabelList.add(biodata);
		mLabelList.add(educationHistory);
		mLabelList.add(workHistory);
		mLabelList.add(achievementHistory);
		mLabelList.add(organizationHistory);

		int layoutResourceId;
		if (position == 0) {
			layoutResourceId = R.layout.list_item_candidate_profile;
		} else {
			layoutResourceId = R.layout.list_item_second_candidate_profile;
		}

		mAdapter = new CandidateCompareListAdapter(getActivity(), mLabelList,
				layoutResourceId);

		mDataListView.setAdapter(mAdapter);
	}

	private String processAchievement() {
		List<AchievementHistory> achievementList = DatabaseHelper
				.getAchievementHistoryOfCandidate(id);
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\n");
		for (int i = 0; i < achievementList.size(); i++) {
			builder.append(achievementList.get(i).getEntityName());
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

	private String processWork() {
		List<WorkHistory> workList = DatabaseHelper
				.getWorkHistoryOfCandidate(id);
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\n");
		for (int i = 0; i < workList.size(); i++) {
			builder.append(workList.get(i).getEntityName());
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

	private String processEducation() {
		List<EducationHistory> educationList = DatabaseHelper
				.getEducationHistoryOfCandidate(id);
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\n");
		for (int i = 0; i < educationList.size(); i++) {
			builder.append(educationList.get(i).getSchoolName());
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

	private String processBiodata() {
		String name = mCandidate.getName();
		String religion = mCandidate.getReligion();
		String birthDate = (String) DateFormat.format("dd-MM-yyyy",
				mCandidate.getBirthDate());
		List<String> list = new ArrayList<String>();
		list.add(name);
		list.add(religion);
		list.add(birthDate);
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\n");
		for (int i = 0; i < 3; i++) {
			builder.append(list.get(i));
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

	private String processOrganization() {
		List<OrganizationHistory> organizationList = OrganizationHistory.find(
				OrganizationHistory.class, "candidate_id = ?", id);
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("\n");
		for (int i = 0; i < organizationList.size(); i++) {
			builder.append(organizationList.get(i).getPosition());
			builder.append("\n");
			builder.append(organizationList.get(i).getEntityName());
			builder.append("\n");
			builder.append("\n");
		}
		return builder.toString();
	}

}
