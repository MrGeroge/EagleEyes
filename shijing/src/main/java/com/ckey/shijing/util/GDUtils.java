package com.ckey.shijing.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class GDUtils {
	private static String KEY = "7fa4d146404ef89a8c789bb8be40d4df";
	private static String TABLEID = "55473c13e4b0a21162b7b275";
	private static String CREATE = "http://yuntuapi.amap.com/datamanage/data/create";
	private static HttpClient client = HttpClients.createDefault();
	

	public static void createSingleDate(String longitude, String latitude,
			String name, String minSize, String maxSize,String des) throws Exception {
		HttpPost post = new HttpPost(CREATE);
		post.addHeader("content-type", "application/x-www-form-urluncoded");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", KEY));
		params.add(new BasicNameValuePair("tableid", TABLEID));
		params.add(new BasicNameValuePair("loctype", "1"));
		UploadJson json = new UploadJson();
		json.set_location(longitude+","+latitude);
		json.set_name(name);
		json.setDes(des);
		json.setOrigin_picture(maxSize);
		json.setSmall_picture(minSize);
		params.add(new BasicNameValuePair("data",new Gson().toJson(json)));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
		post.setEntity(entity);
		HttpResponse resp = client.execute(post);
	}
}
