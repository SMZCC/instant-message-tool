/**
 * 
 */
package com.vince.controller.getweatherimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

/**
 * @author 亮
 *
 */
public class WeatherImage {
	private String urlRequest;
	private URL url;
	private URLConnection urlConn;
	private InputStream is;
	public WeatherImage(String url){
		this.urlRequest = url;
	}
	public ImageIcon getImageIcon(){
		return new ImageIcon(url);
	}
	public ImageIcon getImage(){
		try {
			url = new URL(urlRequest);
			urlConn = url.openConnection();
			urlConn.connect();
			is = urlConn.getInputStream();
			return new ImageIcon(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
