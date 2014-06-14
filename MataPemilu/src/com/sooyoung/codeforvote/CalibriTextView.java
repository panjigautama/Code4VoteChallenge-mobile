package com.sooyoung.codeforvote;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CalibriTextView extends TextView {

	public CalibriTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CalibriTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CalibriTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setTypeface(Typeface tf, int style) {
		super.setTypeface(TypeFaces.getTypeFace(getContext(),
				"Calibri.ttf"));
	}

}
