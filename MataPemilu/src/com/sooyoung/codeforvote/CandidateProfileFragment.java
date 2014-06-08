package com.sooyoung.codeforvote;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sooyoung.codeforvote.helper.SooYoungRestClient;
import com.sooyoung.codeforvote.model.Candidate;
import com.squareup.picasso.Picasso;

@EFragment(R.layout.fragment_candidate_profile)
public class CandidateProfileFragment extends Fragment {

	@ViewById(R.id.profile_photo)
	ImageView mProfileImageView;

	Candidate mCandidate;

	public static CandidateProfileFragment createFragment(String id,
			String twitterId) {
		CandidateProfileFragment fragment = new CandidateProfileFragment_();
		Bundle args = new Bundle();
		args.putString("id", id);
		args.putString("twitter_id", twitterId);
		fragment.setArguments(args);
		return fragment;
	}

	@AfterViews
	void initialize() {
		String id = getArguments().getString("id");
		String twitterId = getArguments().getString("twitter_id");
		List<Candidate> candidateList = Candidate.find(Candidate.class,
				"candidate_id = ?", id);
		if (candidateList.size() == 0) {
			getCandidateProfile(id);
		} else {
			mCandidate = candidateList.get(0);
		}
		getCandidateProfileImage(twitterId);
	}

	private void getCandidateProfileImage(String twitterId) {
		SooYoungRestClient.getCustom(
				"http://54.255.165.249/?r=user/view&user_id=" + twitterId,
				null, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						try {
							Picasso.with(getActivity())
									.load(response.getJSONObject("items")
											.getString("avatar_url"))
									.into(mProfileImageView);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
	}

	private void getCandidateProfile(String id) {
		RequestParams params = new RequestParams();
		params.put("apiKey", "5fdc9c9a378147868203d4c50f37ca52");
		SooYoungRestClient.getCustom(
				"http://api.pemiluapi.org/calonpresiden/api/caleg/" + id,
				params, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						super.onSuccess(response);
						JSONObject candidateProfile = null;
						try {
							candidateProfile = response.getJSONObject("data")
									.getJSONObject("results")
									.getJSONArray("caleg").getJSONObject(0);

							Candidate candidate = new Candidate(getActivity());
							candidate.setBiography(candidateProfile
									.getString("biografi"));
							candidate
									.setBirthDate(getDateInMiliseconds(candidateProfile
											.getString("tanggal_lahir")));
							candidate.setBirthPlace(candidateProfile
									.getString("tempat_lahir"));
							candidate.setGender(candidateProfile
									.getString("jenis_kelamin"));
							candidate.setHometownCity(candidateProfile
									.getString("kab_kota_tinggal"));
							candidate.setHometownRegion(candidateProfile
									.getString("provinsi_tinggal"));
							candidate.setCandidateId(candidateProfile
									.getString("id"));
							candidate.setMaritalStatus(candidateProfile
									.getString("status_perkawinan"));
							candidate.setName(candidateProfile
									.getString("nama"));
							candidate.setNumOfChild(candidateProfile
									.getInt("jumlah_anak"));
							candidate.setParty(candidateProfile.getJSONObject(
									"partai").getString("nama"));
							candidate.setReligion(candidateProfile
									.getString("agama"));
							candidate.setRole(candidateProfile
									.getString("role"));
							candidate.setSpouseName(candidateProfile
									.getString("nama_pasangan"));
							candidate.save();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					private long getDateInMiliseconds(String date) {
						DateTime birthDate = DateTime.parse(date);
						return birthDate.getMillis();
					}
				});
	}
}
