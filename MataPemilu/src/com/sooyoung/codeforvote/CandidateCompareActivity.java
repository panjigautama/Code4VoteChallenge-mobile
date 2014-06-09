package com.sooyoung.codeforvote;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.sooyoung.codeforvote.adapter.CandidateComparePagerAdapter;
import com.viewpagerindicator.IconPageIndicator;

@EActivity(R.layout.activity_candidate_compare)
public class CandidateCompareActivity extends SherlockFragmentActivity {

	@ViewById(R.id.view_pager)
	ViewPager mViewPager;

	@ViewById(R.id.view_pager_indicator)
	IconPageIndicator mViewPagerIndicator;

	FragmentManager mFragmentManager;
	CandidateComparePagerAdapter mAdapter;

	@Extra("compare")
	boolean mPresidentCompare;

	@AfterViews
	void initialize() {
		mFragmentManager = getSupportFragmentManager();
		mAdapter = new CandidateComparePagerAdapter(mFragmentManager);
		mViewPager.setAdapter(mAdapter);
		mViewPagerIndicator.setViewPager(mViewPager);
	}
}
