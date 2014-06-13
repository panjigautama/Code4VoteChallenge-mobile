package com.sooyoung.codeforvote;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

@EActivity(R.layout.activity_vote)
@OptionsMenu(R.menu.about)
public class VoteActivity extends SherlockFragmentActivity
{

	ActionBar mActionBar;
	FragmentManager mFragmentManager;
	Fragment mVoteFragment;

	@AfterViews
	void initialize() {
		
		// wire up fragment
		mFragmentManager 	= getSupportFragmentManager();
		mVoteFragment 		= VoteFragment.newInstance();
		mFragmentManager.beginTransaction().replace(R.id.frame_vote, mVoteFragment).commit();
		
		// enable back button
		mActionBar 			= getSupportActionBar();
		mActionBar.setHomeButtonEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
	    onBackPressed();
	    return true;
	}
	
	@OptionsItem
	void aboutSelected() {
		Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
	}

	
}
