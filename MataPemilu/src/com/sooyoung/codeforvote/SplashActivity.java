package com.sooyoung.codeforvote;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.content.Intent;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

	@AfterViews
	void initialize() {
		loading();
	}

	@Background
	void loading() {
		try {
			Thread.sleep(2000);
			showActivity();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@UiThread
	void showActivity() {
		Intent i = new Intent(getApplicationContext(),
				CandidateProfileActivity_.class);
		startActivity(i);
		finish();
	}

}
