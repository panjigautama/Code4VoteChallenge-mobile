package com.sooyoung.codeforvote;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import android.os.Bundle;
import android.support.v4.app.Fragment;

@EFragment(R.layout.fragment_candidate_compare)
public class CandidateCompareFragment extends Fragment {

	@FragmentArg
	String id;

	public static CandidateCompareFragment createFragment(String id) {
		CandidateCompareFragment fragment = new CandidateCompareFragment_();
		Bundle args = new Bundle();
		args.putString("id", id);
		fragment.setArguments(args);
		return fragment;
	}

}
