package com.sooyoung.codeforvote;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.sooyoung.codeforvote.adapter.CandidateComparePagerAdapter;

@EActivity(R.layout.activity_candidate_compare)
public class CandidateCompareActivity extends SherlockFragmentActivity implements OnPageChangeListener {

	@ViewById(R.id.view_pager)
	ViewPager mViewPager;

	FragmentManager mFragmentManager;
	CandidateComparePagerAdapter mAdapter;

	@Extra("compare")
	boolean mPresidentCompare;

	List<String> mCandidateIdList;

	@AfterViews
	void initialize() {
		mFragmentManager = getSupportFragmentManager();
		mCandidateIdList = new ArrayList<String>();
		if (mPresidentCompare) {
			setTitle("Presiden");
			mCandidateIdList.add("ps");
			mCandidateIdList.add("jw");
		} else {
			setTitle("Wakil Presiden");
			mCandidateIdList.add("hr");
			mCandidateIdList.add("jk");
		}
		mAdapter = new CandidateComparePagerAdapter(mFragmentManager,
				mCandidateIdList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
