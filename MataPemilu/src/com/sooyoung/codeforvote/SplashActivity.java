package com.sooyoung.codeforvote;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.content.Intent;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

	@AfterViews
	void initialize() {
		Intent i = new Intent(getApplicationContext(),
				CandidateProfileActivity_.class);
		startActivity(i);
	}

}
