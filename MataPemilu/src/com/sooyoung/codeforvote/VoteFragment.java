package com.sooyoung.codeforvote;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sooyoung.codeforvote.helper.SooYoungRestClient;
import com.sooyoung.codeforvote.model.PresidolCandidate;

@EFragment(R.layout.fragment_vote)
public class VoteFragment extends Fragment 
{

	private List<PresidolCandidate> mPresidols;
	
	@ViewById
	DeliciousTextView sentiment_neutral_one;
	@ViewById
	DeliciousTextView sentiment_positive_one;
	@ViewById
	DeliciousTextView sentiment_negative_one;
	@ViewById
	DeliciousTextView sentiment_neutral_two;
	@ViewById
	DeliciousTextView sentiment_positive_two;
	@ViewById
	DeliciousTextView sentiment_negative_two;
	
	@AfterViews
	void initialize() 
	{
		mPresidols = new ArrayList<PresidolCandidate>();
		
		loadSentiment();
	}
	
	@UiThread
	void parseSentiment()
	{
		
		if( mPresidols.size() < 0 )
		{
			return;
		}
		
		// prabowo - hatta
		PresidolCandidate presidol_prabowo = mPresidols.get(0);
		
		float prabowo_neutral = ((float) presidol_prabowo.getSentiment().getNeutral()) / presidol_prabowo.getSentiment().getCounts() * 100;
		float prabowo_positive = ((float) presidol_prabowo.getSentiment().getPositive()) / presidol_prabowo.getSentiment().getCounts() * 100;
		float prabowo_negative = ((float) presidol_prabowo.getSentiment().getNegative()) / presidol_prabowo.getSentiment().getCounts() * 100;
		
		sentiment_neutral_one.setText( String.format("%.01f", prabowo_neutral) + "%" );
		sentiment_positive_one.setText( String.format("%.01f", prabowo_positive) + "%" );
		sentiment_negative_one.setText( String.format("%.01f", prabowo_negative) + "%" );
		
		// prabowo - hatta
		PresidolCandidate presidol_jokowi = mPresidols.get(1);
		
		float jokowi_neutral = ((float) presidol_jokowi.getSentiment().getNeutral()) / presidol_jokowi.getSentiment().getCounts() * 100;
		float jokowi_positive = ((float) presidol_jokowi.getSentiment().getPositive()) / presidol_jokowi.getSentiment().getCounts() * 100;
		float jokowi_negative = ((float) presidol_jokowi.getSentiment().getNegative()) / presidol_jokowi.getSentiment().getCounts() * 100;
		
		sentiment_neutral_two.setText( String.format("%.01f", jokowi_neutral) + "%" );
		sentiment_positive_two.setText( String.format("%.01f", jokowi_positive) + "%" );
		sentiment_negative_two.setText( String.format("%.01f", jokowi_negative) + "%" );

	}
	
	@Background
	void loadVotes()
	{
		
	}
	
	@Background
	void loadSentiment()
	{
		SooYoungRestClient.get("http://presidol.panjigautama.com/api/1/sentiment", null, new AsyncHttpResponseHandler(){

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,Throwable arg3) 
			{
				super.onFailure(arg0, arg1, arg2, arg3);
			}
			
			

			@Override
			public void onSuccess(int statusCode, String content) {
				try {
					JSONObject object = new JSONObject(content);
					
					int code = object.getInt("code");
					JSONObject message = object.getJSONObject("message");
					
					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();

					JSONArray presidolsArr = message.getJSONArray("candidates");
					for(int i = 0; i < presidolsArr.length(); i++)
					{
						PresidolCandidate presidol = gson.fromJson(presidolsArr.getString(i), PresidolCandidate.class);
						mPresidols.add(presidol);
					}
					
					parseSentiment();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			
		});
	}
	
}
