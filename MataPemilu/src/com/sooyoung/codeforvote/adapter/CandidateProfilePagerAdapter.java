package com.sooyoung.codeforvote.adapter;

import java.util.HashMap;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.sooyoung.codeforvote.PresCandidateProfileFragment_;
import com.sooyoung.codeforvote.WapresCandidateProfileFragment_;

public class CandidateProfilePagerAdapter extends FragmentPagerAdapter {

	private JazzyViewPager viewPager;
	private List<HashMap<String, String>> firstCandidateIdList;
	private List<HashMap<String, String>> secondCandidateIdList;

	public CandidateProfilePagerAdapter(FragmentManager fm,
			List<HashMap<String, String>> firstCandidateIdList,
			List<HashMap<String, String>> secondCandidateIdList,
			JazzyViewPager viewPager) {
		super(fm);
		this.firstCandidateIdList = firstCandidateIdList;
		this.secondCandidateIdList = secondCandidateIdList;
		this.viewPager = viewPager;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment;
		if (position == 0) {
			fragment = PresCandidateProfileFragment_
					.createFragment(firstCandidateIdList);
		} else {
			fragment = WapresCandidateProfileFragment_
					.createFragment(secondCandidateIdList);
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Object obj = super.instantiateItem(container, position);
		viewPager.setObjectForPosition(obj, position);
		return obj;
	}

}
