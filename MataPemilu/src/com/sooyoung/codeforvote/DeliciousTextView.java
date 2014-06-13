package com.sooyoung.codeforvote;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class DeliciousTextView extends TextView {

	public DeliciousTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DeliciousTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public DeliciousTextView(Context context) {
		super(context);
	}

	@Override
	public void setTypeface(Typeface tf, int style) {
		super.setTypeface(TypeFaces.getTypeFace(getContext(),
				"DELICIOUS-HEAVY.OTF"));
	}

}
