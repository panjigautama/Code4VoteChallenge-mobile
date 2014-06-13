package com.sooyoung.codeforvote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.JazzyViewPager.TransitionEffect;
import com.sooyoung.codeforvote.adapter.CandidateProfilePagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

@EActivity(R.layout.activity_candidate_profile)
@OptionsMenu(R.menu.candidate)
public class CandidateProfileActivity extends SherlockFragmentActivity
		implements OnPageChangeListener {
	private static final String PRES_ID = "pres_id";
	private static final String PRES_TWITTER_ID = "pres_twitter_id";
	private static final String WAPRES_ID = "wapres_id";
	private static final String WAPRES_TWITTER_ID = "wapres_twitter_id";

	@ViewById(R.id.container)
	JazzyViewPager mViewPager;
	CandidateProfilePagerAdapter mAdapter;

	@ViewById(R.id.pager_indicator)
	CirclePageIndicator mPageIndicator;

	FragmentManager mFragmentManager;
	String mCurrentPresId;

	PresCandidateProfileFragment mPresCandidateFragment;
	PresCandidateProfileFragment mWapresCandidateFragment;

	List<HashMap<String, String>> mPresCandidateList;
	List<HashMap<String, String>> mWapresCandidateList;

	ActionBar mActionBar;

	@AfterViews
	void initialize() {
		mFragmentManager = getSupportFragmentManager();
		mActionBar = getSupportActionBar();

		setTitle("Capres");
		createPresCandidateFragment();
		createWapresCandidateFragment();

		mAdapter = new CandidateProfilePagerAdapter(mFragmentManager,
				mPresCandidateList, mWapresCandidateList, mViewPager);
		mViewPager.setTransitionEffect(TransitionEffect.FlipHorizontal);
		mViewPager.setAdapter(mAdapter);

		mPageIndicator.setViewPager(mViewPager);
		mPageIndicator.setOnPageChangeListener(this);
	}

	@OptionsItem
	void compareSelected() {
		Intent i = new Intent(this, CandidateCompareActivity_.class);
		if (getTitle().equals("Capres")) {
			i.putExtra("compare", true);
		} else {
			i.putExtra("compare", false);
		}
		startActivity(i);
	}

	@OptionsItem
	void voteSelected() {
		Intent i = new Intent(this, VoteActivity_.class);
		startActivity(i);
	}

	private void createWapresCandidateFragment() {
		String firstWapresId = "hr";
		String firstWapresTwitterId = "156691421";
		String secondWapresId = "jk";
		String secondWapresTwitterId = "903172238";

		HashMap<String, String> firstWapresMap = new HashMap<String, String>();
		firstWapresMap.put(PRES_ID, firstWapresId);
		firstWapresMap.put(PRES_TWITTER_ID, firstWapresTwitterId);

		HashMap<String, String> secondWapresMap = new HashMap<String, String>();
		secondWapresMap.put(WAPRES_ID, secondWapresId);
		secondWapresMap.put(WAPRES_TWITTER_ID, secondWapresTwitterId);

		mWapresCandidateList = new ArrayList<HashMap<String, String>>();
		mWapresCandidateList.add(firstWapresMap);
		mWapresCandidateList.add(secondWapresMap);
	}

	private void createPresCandidateFragment() {
		String firstPresId = "ps";
		String firstPresTwitterId = "40580714";
		String secondPresId = "jw";
		String secondPresTwitterId = "366987179";

		HashMap<String, String> firstPresMap = new HashMap<String, String>();
		firstPresMap.put(PRES_ID, firstPresId);
		firstPresMap.put(PRES_TWITTER_ID, firstPresTwitterId);

		HashMap<String, String> secondPresMap = new HashMap<String, String>();
		secondPresMap.put(WAPRES_ID, secondPresId);
		secondPresMap.put(WAPRES_TWITTER_ID, secondPresTwitterId);

		mPresCandidateList = new ArrayList<HashMap<String, String>>();
		mPresCandidateList.add(firstPresMap);
		mPresCandidateList.add(secondPresMap);
	}

	@Override
	public void onPageScrollStateChanged(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		if (position == 0) {
			setTitle("Capres");
		} else {
			setTitle("Wapres");
		}
	}
}
