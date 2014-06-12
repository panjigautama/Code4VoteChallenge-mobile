package com.sooyoung.codeforvote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

@EActivity(R.layout.activity_candidate_profile)
@OptionsMenu(R.menu.candidate)
public class CandidateProfileActivity extends SherlockFragmentActivity
		implements OnGestureListener, AnimationListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private static final String PRES_ID = "pres_id";
	private static final String PRES_TWITTER_ID = "pres_twitter_id";
	private static final String WAPRES_ID = "wapres_id";
	private static final String WAPRES_TWITTER_ID = "wapres_twitter_id";

	FragmentManager mFragmentManager;
	GestureDetectorCompat mDetector;
	String mCurrentPresId;

	CandidateProfileFragment mFirstCandidateFragment;
	CandidateProfileFragment mSecondCandidateFragment;

	Animation mFadeInAnimation;
	Animation mFadeOutAnimation;

	ActionBar mActionBar;

	@AfterViews
	void initialize() {
		mFragmentManager = getSupportFragmentManager();
		mDetector = new GestureDetectorCompat(this, this);
		mActionBar = getSupportActionBar();

		createFirstCandidateFragment();
		createSecondCandidateFragment();

		mFragmentManager.beginTransaction()
				.replace(R.id.container, mFirstCandidateFragment).commit();

		mFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		mFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
	}

	@OptionsItem
	void compareSelected() {
		Toast.makeText(this, "compare", Toast.LENGTH_SHORT).show();
		final Intent i = new Intent(this, CandidateCompareActivity_.class);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Presiden atau Wakil Presiden?")
				.setPositiveButton("Presiden", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						i.putExtra("compare", true);
						startActivity(i);
					}
				}).setNegativeButton("Wakil Presiden", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						i.putExtra("compare", false);
						startActivity(i);
					}
				}).show();
	}

	@OptionsItem
	void voteSelected() {
		Intent intent = new Intent( this, VoteActivity_.class );
		startActivity(intent);
	}

	private void createSecondCandidateFragment() {
		String presId = "jw";
		String presTwitterId = "366987179";
		String wapresId = "jk";
		String wapresTwitterId = "903172238";

		HashMap<String, String> presMap = new HashMap<String, String>();
		presMap.put(PRES_ID, presId);
		presMap.put(PRES_TWITTER_ID, presTwitterId);

		HashMap<String, String> wapresMap = new HashMap<String, String>();
		wapresMap.put(WAPRES_ID, wapresId);
		wapresMap.put(WAPRES_TWITTER_ID, wapresTwitterId);

		List<HashMap<String, String>> idList = new ArrayList<HashMap<String, String>>();
		idList.add(presMap);
		idList.add(wapresMap);

		mSecondCandidateFragment = CandidateProfileFragment
				.createFragment(idList);
	}

	private void createFirstCandidateFragment() {
		String presId = "ps";
		String presTwitterId = "40580714";
		String wapresId = "hr";
		String wapresTwitterId = "156691421";

		HashMap<String, String> presMap = new HashMap<String, String>();
		presMap.put(PRES_ID, presId);
		presMap.put(PRES_TWITTER_ID, presTwitterId);

		HashMap<String, String> wapresMap = new HashMap<String, String>();
		wapresMap.put(WAPRES_ID, wapresId);
		wapresMap.put(WAPRES_TWITTER_ID, wapresTwitterId);

		List<HashMap<String, String>> idList = new ArrayList<HashMap<String, String>>();
		idList.add(presMap);
		idList.add(wapresMap);

		mFirstCandidateFragment = CandidateProfileFragment
				.createFragment(idList);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			if (mCurrentPresId == "jw") {
				return true;
			}

			String presId = "jw";

			mFragmentManager.beginTransaction()
					.replace(R.id.container, mSecondCandidateFragment)
					.setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
					.commit();
			mCurrentPresId = presId;
		} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			if (mCurrentPresId == "ps") {
				return true;
			}

			String presId = "ps";

			mFragmentManager.beginTransaction()
					.replace(R.id.container, mFirstCandidateFragment)
					.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
					.commit();
			mCurrentPresId = presId;
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
