/**
 * 
 */
package com.vince.controller.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author 亮
 *
 */
public class MyWeather {

	/**
	 * 
	 */
	public MyWeather() {
		// TODO Auto-generated constructor stub
		list = new ArrayList<Data>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyWeather mw = new MyWeather(); 
		String city = mw.getCityCode("59.54.45.171", "CeANufPqkAgo5kNG7oaEdj2w", false);
		System.out.println(mw.getMyWeather(city,"CeANufPqkAgo5kNG7oaEdj2w"));;
	}
	public String getCityCode(String ipAddress,String pk,boolean isCoorShow){
		String urlRequest= "http://api.map.baidu.com/location/ip?ak="+pk+"&ip="+ipAddress;
		if(isCoorShow) urlRequest = urlRequest+"&coor=bd09ll";
		//System.out.println(urlRequest);
		URL url = null;
		URLConnection urlConn = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			url = new URL(urlRequest);
			urlConn = url.openConnection();
			urlConn.connect();
			is = urlConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String str = br.readLine();
			JSONObject json = new JSONObject(str);
			//JSONArray jsonArr = new JSONArray(json.getString("address_detail"));
			JSONObject addressJson = new JSONObject(json.getString("content")).getJSONObject("address_detail");
			String province = addressJson.getString("province").substring(0, 2);
			String city = addressJson.getString("city").substring(0, 2);
			//TODO 由于天气接口改变后，下面代码可以不使用
			return city;
//			String county = addressJson.getString("district");
//			StringBuffer code = new StringBuffer("101");
//			// 下面开始根据这三个名称来获取weather.com.cn的省代码  
//			urlRequest = "http://m.weather.com.cn/data5/city.xml";
//			url = new URL(urlRequest);
//			urlConn = url.openConnection();
//			urlConn.connect();
//			is = urlConn.getInputStream();
//			br = new BufferedReader(new InputStreamReader(is));
//			String provinceCode = br.readLine();
//			//System.out.println(provinceCode);
//			int index = -1;//System.out.println(index);
//			char[] dst = new char[50];
//			if((index = provinceCode.indexOf(province))!= -1){
//				provinceCode.getChars(index-3, index-1, dst, 0);
//			}
			// 下面开始根据这三个名称来获取weather.com.cn的城市代码  
//			urlRequest = "http://m.weather.com.cn/data5/city"+new String(dst, 0, 2)+".xml";
//			url = new URL(urlRequest);
//			urlConn = url.openConnection();
//			urlConn.connect();
//			is = urlConn.getInputStream();
//			br = new BufferedReader(new InputStreamReader(is));
//			//String provinceCode = br.readLine();
//			String cityCode = br.readLine();
//			index = -1;
			//System.out.println(cityCode);
//			if((index = cityCode.indexOf(city))!= -1){
//				char[] dst1 = new char[50];
//				cityCode.getChars(index-5, index-1, dst1, 0);
//				code.append(new String(dst1, 0, 4));
//			}
//			if(county.trim().length() == 0) code.append("01");
//			//TODO
//			return code.toString();
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public  JSONObject getJSONObject(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace(System.err);
        }
        return jsonObject;
    }
	public List<String> getMyWeather(String cityCode){
		if(cityCode == null || cityCode.trim().length() == 0) return null;
		List<String> list = new ArrayList<String>();
		String urlRequest = "http://m.weather.com.cn/data/" +cityCode+ ".html";
		URL url = null;
		URLConnection urlConn = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			url = new URL(urlRequest);
			urlConn = url.openConnection();
			urlConn.connect();
			is = urlConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			String temp = "";
			while((temp = br.readLine()) != null){
				str +=temp;
			}
			System.out.println(str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Data> getMyWeather(String city,String pk){
		if(city == null) return null;
		URL url = null;
		URLConnection urlConn = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			city = new String(city.getBytes(),"UTF-8");
			String urlRequest = "http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak="+pk;
			url = new URL(urlRequest);
			urlConn = url.openConnection();
			urlConn.connect();
			is = urlConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String str = br.readLine();
			System.out.println(str);
			Data data = null;
			//下面开始解析json
			JSONObject json = new JSONObject(str);
			//JSONArray jsonArr = new JSONArray(json.getString("results"));
			JSONArray jsonArr = json.getJSONArray("results");
			//jsonArr
			for(int i = 0;i < jsonArr.length();i++){
				JSONObject obj = (JSONObject) jsonArr.get(i);
				this.currentCity = obj.getString("currentCity");
				JSONArray arr = obj.getJSONArray("weather_data");
				for(int j = 0;j < arr.length();j++){
					JSONObject object = arr.getJSONObject(j);
					data = new Data();
					data.date = object.getString("date");
					data.dayPictureUrl = object.getString("dayPictureUrl");
					data.nightPictureUrl = object.getString("nightPictureUrl");
					data.weather = object.getString("weather");
					data.wind = object.getString("wind");
					data.temperature = object.getString("temperature");
					list.add(data);
					data = null;
				}
			}
			
			this.error = json.getInt("error");
			this.status = json.getString("status");
			this.date = json.getString("date");
			return list;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public String currentCity;
	public int pm25;
	public String date;
	public int error;
	public String status;
	public class Data{
		String date;
		String dayPictureUrl;
		String nightPictureUrl;
		String weather;
		String wind;
		String temperature;
	}
	private List<Data> list;
}
