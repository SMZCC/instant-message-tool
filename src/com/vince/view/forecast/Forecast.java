package com.vince.view.forecast;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.vince.controller.getweatherimage.WeatherImage;
import com.vince.controller.test.MyWeather;

/**
 * 
 * @author 亮
 * 
*/
public class Forecast extends JFrame{
	private Container container;
	private JLabel todayRealTempNum;
	private JLabel tempUnit;
	private JLabel currentCity;
	private JLabel weather;
	private JLabel wind;
	private JLabel airCondition;
	private JLabel pm;
	private JPanel maskPanel;
	private JLabel today;
	private JLabel todayTempRange;
	private JLabel todayImage;
	private JLabel tomorrow;
	private JLabel tomorrowTempRange;
	private JLabel tomorrowImage;
	private JLabel bigtomorrow;
	private JLabel bigtomorrowTempRange;
	private JLabel bigtomorrowImage;
	public Forecast(){
		super();
		//MyWeather mw = new MyWeather();
		//String city = mw.getCityCode("59.54.45.171", "CeANufPqkAgo5kNG7oaEdj2w", false);
		//List<MyWeather.Data> list = mw.getMyWeather(city,"CeANufPqkAgo5kNG7oaEdj2w");
		//System.out.println(list);
		container = this.getContentPane();
		this.setLayout(null);
		/*((JPanel)this.container).setOpaque(false);
		ImageIcon image  = new ImageIcon("src\\images\\bg_8.png","蓝天白云");
		//System.out.println(image);
		JLabel background = new JLabel(image);background.setOpaque(false);
		this.getLayeredPane().add(background,Integer.MIN_VALUE);
		background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());*/
		//ImageIO image = new ImageIcon(filename)
		//this.container.setBackground(Color.white);
		this.setUndecorated(true);
		this.setSize(240, 250);
		//界面设计
		todayRealTempNum = new JLabel();
		todayRealTempNum.setText("10");
		todayRealTempNum.setSize(38, 34);todayRealTempNum.setFont(new Font("黑体",Font.BOLD,30));todayRealTempNum.setBounds(28, 18, 38, 34);todayRealTempNum.setForeground(Color.white);container.add(todayRealTempNum);
		tempUnit = new JLabel();tempUnit.setText("℃");tempUnit.setSize(30,22);tempUnit.setBounds(65, 24, 40, 22);tempUnit.setForeground(Color.white);tempUnit.setFont(new Font("黑体",Font.BOLD,20));container.add(tempUnit);
		currentCity = new JLabel();
		currentCity.setText("九江(本地)");
		currentCity.setSize(100, 20);currentCity.setBounds(23, 55, 100, 20);currentCity.setForeground(Color.white);currentCity.setFont(new Font("宋体",Font.BOLD,12));container.add(currentCity);
		//天气风向
		weather = new JLabel();
		weather.setText("多云转晴");
		weather.setSize(60, 20);weather.setBounds(23, 75, 60, 20);weather.setForeground(Color.white);container.add(weather);
		wind = new JLabel();
		wind.setText("东北风5级");
		wind.setSize(100, 20);wind.setBounds(83, 75, 100, 20);wind.setForeground(Color.white);container.add(wind);
		//污染
		airCondition = new JLabel();
		airCondition.setText("轻度污染");
		airCondition.setSize(60, 20);airCondition.setBounds(23, 95, 60, 20);airCondition.setForeground(Color.white);container.add(airCondition);
		//pm
		pm = new JLabel();
		pm.setText("PM2.5 129");
		pm.setSize(120, 20);pm.setBounds(83, 95, 120, 20);pm.setForeground(Color.white);container.add(pm);
		//实现mask的效果的面板
		
		maskPanel = new JPanel(null);
		maskPanel.setSize(238, 102);maskPanel.setBounds(0, 146, 238, 102);maskPanel.setBackground(Color.gray);/*maskPanel.setOpaque(false);*/container.add(maskPanel);
		today = new JLabel();
		today.setText("今天");
		today.setSize(28, 16);today.setBounds(26, 5, 28, 16);today.setForeground(Color.white);today.setFont(new Font("黑体",Font.BOLD,11));maskPanel.add(today);//today.setBorder(BorderFactory.createEtchedBorder());
		todayTempRange = new JLabel();
		todayTempRange.setText("10℃/20℃");
		todayTempRange.setSize(56,11);todayTempRange.setBounds(16, 25, 56, 11);todayTempRange.setForeground(Color.white);todayTempRange.setFont(new Font("黑体",Font.BOLD,10));maskPanel.add(todayTempRange);
		//http://api.map.baidu.com/images/weather/night/duoyun.png
		todayImage = new JLabel(new WeatherImage("http://api.map.baidu.com/images/weather/night/duoyun.png").getImage());
		todayImage.setSize(42, 30);todayImage.setBounds(20, 42, 42, 30);todayImage.setForeground(Color.white);maskPanel.add(todayImage);
		
		
		tomorrow = new JLabel();
		tomorrow.setText("明天");
		tomorrow.setSize(28, 16);tomorrow.setBounds(110, 5, 28, 16);tomorrow.setForeground(Color.white);tomorrow.setFont(new Font("黑体",Font.BOLD,11));maskPanel.add(tomorrow);//tomorrow.setBorder(BorderFactory.createEtchedBorder());
		tomorrowTempRange = new JLabel();
		tomorrowTempRange.setText("1℃/20℃");
		tomorrowTempRange.setSize(56, 11);tomorrowTempRange.setBounds(102, 25, 56, 11);tomorrowTempRange.setForeground(Color.white);tomorrowTempRange.setFont(new Font("黑体",Font.BOLD,10));maskPanel.add(tomorrowTempRange);
		tomorrowImage = new JLabel(new WeatherImage("http://api.map.baidu.com/images/weather/night/duoyun.png").getImage());
		tomorrowImage.setSize(42, 30);tomorrowImage.setBounds(106, 42, 42, 30);tomorrowImage.setForeground(Color.white);maskPanel.add(tomorrowImage);
		
		
		bigtomorrow = new JLabel();
		bigtomorrow.setText("后天");
		bigtomorrow.setSize(28, 16);bigtomorrow.setBounds(194, 5, 28, 16);bigtomorrow.setForeground(Color.white);bigtomorrow.setFont(new Font("黑体",Font.BOLD,11));maskPanel.add(bigtomorrow);//bigtomorrow.setBorder(BorderFactory.createEtchedBorder());
		bigtomorrowTempRange = new JLabel();
		bigtomorrowTempRange.setText("10℃/20℃");
		bigtomorrowTempRange.setSize(56, 11);bigtomorrowTempRange.setBounds(182, 25, 56, 11);bigtomorrowTempRange.setForeground(Color.white);bigtomorrowTempRange.setFont(new Font("黑体",Font.BOLD,10));maskPanel.add(bigtomorrowTempRange);
		bigtomorrowImage = new JLabel(new WeatherImage("http://api.map.baidu.com/images/weather/night/duoyun.png").getImage());
		bigtomorrowImage.setSize(42, 30);bigtomorrowImage.setBounds(188, 42, 42, 30);bigtomorrowImage.setForeground(Color.white);maskPanel.add(bigtomorrowImage);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Forecast();
	}
}
