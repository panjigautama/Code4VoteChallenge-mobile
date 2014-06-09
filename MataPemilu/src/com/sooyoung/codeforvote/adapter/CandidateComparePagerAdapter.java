package com.sooyoung.codeforvote.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sooyoung.codeforvote.CandidateCompareFragment;
import com.sooyoung.codeforvote.CandidateCompareFragment_;
import com.viewpagerindicator.IconPagerAdapter;

public class CandidateComparePagerAdapter extends FragmentStatePagerAdapter
		implements IconPagerAdapter {

	public CandidateComparePagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		CandidateCompareFragment fragment = CandidateCompareFragment_
				.createFragment(null);
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public int getIconResId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

}
