package com.sooyoung.codeforvote;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

@EActivity(R.layout.activity_candidate_profile)
public class CandidateProfileActivity extends FragmentActivity {

	FragmentManager mFragmentManager;

	@AfterViews
	void initialize() {
		mFragmentManager = getSupportFragmentManager();
		CandidateProfileFragment jokoFragment = CandidateProfileFragment_
				.createFragment("jw", "366987179");
		CandidateProfileFragment jusufFragment = CandidateProfileFragment_
				.createFragment("jk", "903172238");
		CandidateProfileFragment prabowoFragment = CandidateProfileFragment_
				.createFragment("ps", "40580714");
		CandidateProfileFragment hattaFragment = CandidateProfileFragment_
				.createFragment("hr", "156691421");

		mFragmentManager.beginTransaction().add(R.id.container, jusufFragment)
				.commit();
	}

}
