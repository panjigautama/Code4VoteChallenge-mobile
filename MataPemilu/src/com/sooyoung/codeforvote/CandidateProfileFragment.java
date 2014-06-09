package com.sooyoung.codeforvote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sooyoung.codeforvote.helper.SooYoungRestClient;
import com.sooyoung.codeforvote.model.AchievementHistory;
import com.sooyoung.codeforvote.model.Candidate;
import com.sooyoung.codeforvote.model.EducationHistory;
import com.sooyoung.codeforvote.model.OrganizationHistory;
import com.sooyoung.codeforvote.model.WorkHistory;
import com.squareup.picasso.Picasso;

@EFragment(R.layout.fragment_candidate_profile)
public class CandidateProfileFragment extends Fragment {

	private static final String PRES_ID = "pres_id";
	private static final String PRES_TWITTER_ID = "pres_twitter_id";
	private static final String WAPRES_ID = "wapres_id";
	private static final String WAPRES_TWITTER_ID = "wapres_twitter_id";

	@ViewById(R.id.candidate_pres)
	ImageView mPresImageView;

	@ViewById(R.id.candidate_wapres)
	ImageView mWapresImageView;

	@FragmentArg(PRES_ID)
	String presId;

	@FragmentArg(PRES_TWITTER_ID)
	String presTwitterId;

	@FragmentArg(WAPRES_ID)
	String wapresId;

	@FragmentArg(WAPRES_TWITTER_ID)
	String wapresTwitterId;

	Candidate mPresCandidate;
	Candidate mWapresCandidate;

	public static CandidateProfileFragment createFragment(
			List<HashMap<String, String>> id) {
		CandidateProfileFragment fragment = new CandidateProfileFragment_();
		Bundle args = new Bundle();
		args.putString(PRES_ID, id.get(0).get(PRES_ID));
		args.putString(PRES_TWITTER_ID, id.get(0).get(PRES_TWITTER_ID));
		args.putString(WAPRES_ID, id.get(1).get(WAPRES_ID));
		args.putString(WAPRES_TWITTER_ID, id.get(1).get(WAPRES_TWITTER_ID));
		fragment.setArguments(args);
		return fragment;
	}

	@Click(R.id.candidate_pres)
	void showPresidentProfile() {
		CandidateProfileDialog dialog = new CandidateProfileDialog();
		dialog.show(getFragmentManager(), null);
	}

	@AfterViews
	void initialize() {
		List<Candidate> presCandidateList = Candidate.find(Candidate.class,
				"candidate_id = ?", presId);
		List<Candidate> wapresCandidateList = Candidate.find(Candidate.class,
				"candidate_id = ?", wapresId);

		if (presCandidateList.size() == 0) {
			getCandidateProfile(presId);
		} else {
			mPresCandidate = presCandidateList.get(0);
		}

		getCandidateProfileImage(presTwitterId, mPresImageView);
		if (wapresCandidateList.size() == 0) {
			getCandidateProfile(wapresId);
		} else {
			mWapresCandidate = wapresCandidateList.get(0);
		}

		getCandidateProfileImage(wapresTwitterId, mWapresImageView);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("FRAGMENT", "destroyed");
	}

	private void getCandidateProfileImage(String twitterId,
			final ImageView imageView) {
		SooYoungRestClient.getCustom(
				"http://54.255.165.249/?r=user/view&user_id=" + twitterId,
				null, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						try {
							Picasso.with(getActivity())
									.load(response.getJSONObject("items")
											.getString("avatar_url"))
									.into(imageView);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
	}

	private void getCandidateProfile(final String id) {
		RequestParams params = new RequestParams();
		params.put("apiKey", "5fdc9c9a378147868203d4c50f37ca52");
		SooYoungRestClient.getCustom(
				"http://api.pemiluapi.org/calonpresiden/api/caleg/" + id,
				params, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						super.onSuccess(response);
						JSONObject candidateProfile = null;
						JSONArray education = null;
						JSONArray achievement = null;
						JSONArray work = null;
						JSONArray organization = null;
						try {
							candidateProfile = response.getJSONObject("data")
									.getJSONObject("results")
									.getJSONArray("caleg").getJSONObject(0);
							education = candidateProfile
									.getJSONArray("riwayat_pendidikan");
							achievement = candidateProfile
									.getJSONArray("riwayat_penghargaan");
							work = candidateProfile
									.getJSONArray("riwayat_pekerjaan");
							organization = candidateProfile
									.getJSONArray("riwayat_organisasi");

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

							for (int i = 0; i < education.length(); i++) {
								JSONObject educationProfile = education
										.getJSONObject(i);
								EducationHistory educationHistory = new EducationHistory(
										getActivity());
								educationHistory.setCandidateId(id);
								educationHistory.setEndYear(educationProfile
										.getString("tanggal_selesai"));
								educationHistory.setSchoolName(educationProfile
										.getString("ringkasan"));
								educationHistory.setStartYear(educationProfile
										.getString("tanggal_mulai"));
								educationHistory.save();
							}

							for (int i = 0; i < work.length(); i++) {
								JSONObject workProfile = work.getJSONObject(i);
								WorkHistory workHistory = new WorkHistory(
										getActivity());
								workHistory.setCandidateId(id);
								workHistory.setEndYear(workProfile
										.getString("tanggal_selesai"));
								workHistory.setEntityName(workProfile
										.getString("ringkasan"));
								workHistory.setStartYear(workProfile
										.getString("tanggal_mulai"));
								workHistory.save();
							}

							for (int i = 0; i < achievement.length(); i++) {
								JSONObject achievementProfile = achievement
										.getJSONObject(i);
								AchievementHistory achievementHistory = new AchievementHistory(
										getActivity());
								achievementHistory.setCandidateId(id);
								achievementHistory
										.setEntityName(achievementProfile
												.getString("ringkasan"));
								achievementHistory
										.setYearAchieved(achievementProfile
												.getString("tanggal"));
								achievementHistory
										.setInstitution(achievementProfile
												.getString("institusi"));
								achievementHistory.save();
							}

							for (int i = 0; i < organization.length(); i++) {
								JSONObject organizationProfile = organization
										.getJSONObject(i);
								OrganizationHistory organizationHistory = new OrganizationHistory(
										getActivity());
								organizationHistory.setCandidateId(id);
								organizationHistory
										.setEndDate(organizationProfile
												.getString("tanggal_selesai"));
								organizationHistory
										.setEntityName(organizationProfile
												.getString("ringkasan"));
								organizationHistory
										.setPosition(organizationProfile
												.getString("jabatan"));
								organizationHistory
										.setStartDate(organizationProfile
												.getString("tanggal_mulai"));
								organizationHistory.save();
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					private long getDateInMiliseconds(String date) {
						Log.d("Candidate", date);
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd");
						try {
							Date dat = formatter.parse(date);
							return dat.getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return 0;
					}
				});
	}
}
