package com.sooyoung.codeforvote;

import org.androidannotations.annotations.EFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.ScaleAnimation;

@EFragment
public class CandidateProfileDialog extends DialogFragment {

	LayoutInflater mInflater;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		mInflater = getActivity().getLayoutInflater();
		View view = mInflater.inflate(R.layout.dialog_candidate_profile, null);
		ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setFillAfter(true);
		animation.setDuration(500);
		view.startAnimation(animation);
		builder.setView(view);
		Dialog dialog = builder.create();
		//dialog.getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;
		return dialog;
	}

}
