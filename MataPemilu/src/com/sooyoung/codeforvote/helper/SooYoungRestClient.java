package com.sooyoung.codeforvote.helper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SooYoungRestClient {

	private static AsyncHttpClient mHttpClient = new AsyncHttpClient();

	// TODO Define Base Url
	private static final String BASE_URL = "";

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler callback) {
		mHttpClient.get(getAbsoluteUrl(url), params, callback);
	}

	public static void getCustom(String url, RequestParams params,
			AsyncHttpResponseHandler callback) {
		mHttpClient.get(url, params, callback);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler callback) {
		mHttpClient.post(getAbsoluteUrl(url), params, callback);
	}

	public static void postCustom(String url, RequestParams params,
			AsyncHttpResponseHandler callback) {
		mHttpClient.post(url, params, callback);
	}

	private static String getAbsoluteUrl(String url) {
		return BASE_URL + url;
	}

}
