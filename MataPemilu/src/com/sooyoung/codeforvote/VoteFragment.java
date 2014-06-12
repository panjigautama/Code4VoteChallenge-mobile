package com.sooyoung.codeforvote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class VoteFragment extends Fragment {

	public static VoteFragment newInstance() {
		VoteFragment fragment = new VoteFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	ScrollView root = (ScrollView) inflater.inflate(R.layout.fragment_vote, container, false);
        return root;
    }
	
}
