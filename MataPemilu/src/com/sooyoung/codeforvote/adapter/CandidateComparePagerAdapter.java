package com.sooyoung.codeforvote.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sooyoung.codeforvote.CandidateCompareFragment;
import com.sooyoung.codeforvote.CandidateCompareFragment_;

public class CandidateComparePagerAdapter extends FragmentStatePagerAdapter {

	List<String> candidateIdList;

	public CandidateComparePagerAdapter(FragmentManager fm,
			List<String> candidateIdList) {
		super(fm);
		this.candidateIdList = candidateIdList;
	}

	@Override
	public Fragment getItem(int position) {
		CandidateCompareFragment fragment = CandidateCompareFragment_
				.createFragment(candidateIdList.get(position), position);
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
