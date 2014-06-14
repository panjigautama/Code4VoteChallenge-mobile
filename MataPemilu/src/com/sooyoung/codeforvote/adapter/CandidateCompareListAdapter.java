package com.sooyoung.codeforvote.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sooyoung.codeforvote.R;

public class CandidateCompareListAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<String> text;
	int layoutResouceId;

	public CandidateCompareListAdapter(Context context, List<String> text, int layoutResourceId) {
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.text = text;
		this.layoutResouceId = layoutResourceId;
	}

	@Override
	public int getCount() {
		return text.size();
	}

	@Override
	public Object getItem(int position) {
		return text.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(layoutResouceId, null);
		}
		Log.d("text", text.get(position));
		TextView label = (TextView) view.findViewById(R.id.label_text);
		ImageView image = (ImageView) view.findViewById(R.id.imageView1);
		if (position == 0) {
			image.setImageResource(R.drawable.icon_bio);
		} else if (position == 1) {
			image.setImageResource(R.drawable.icon_edu);
		} else if (position == 2) {
			image.setImageResource(R.drawable.icon_wrk);
		} else if (position == 3) {
			image.setImageResource(R.drawable.icon_awd);
		} else if (position == 4) {
			image.setImageResource(R.drawable.icon_org);
		}

		label.setText(text.get(position));
		return view;
	}

}
