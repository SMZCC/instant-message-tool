package com.vince.controller.test;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Weather
{

	public InputStream lianJie(String strUrl) throws IOException
	{
		URL url = new URL(strUrl);
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		InputStream is = urlConnection.getInputStream();
		
		
		if(is!=null)
		{
			return is;
		}
		return null;
	}
	
	public void resolutionXML(String strUrl) throws ParserConfigurationException, SAXException, IOException
	{
		WeatherData wd = new WeatherData();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new InputStreamReader(lianJie(strUrl) )));
		
		// 得到xml_api_reply 
		Element rootElement = document.getDocumentElement();
		System.out.println(rootElement.getNodeName());
		Node weatherNode =  rootElement.getElementsByTagName("weather").item(0);
		
//		Node weatherNode = rootElement.getFirstChild();
		System.out.println(weatherNode.getNodeName());
		// 得到weather 
//		Node nodeWeather = weatherNode.getChildNodes();

		// 得到weather下节点数组
		NodeList nodeForecastWeathers =  weatherNode.getChildNodes();
		
		// 遍历weather下的节点
		for(int i=0; i<nodeForecastWeathers.getLength(); i++)
		{
			
			Node nodeForecastWeather = nodeForecastWeathers.item(i);
			// 筛选节点  找名称为 forecast_conditions 节点
			if(nodeForecastWeather.getNodeType()==Node.ELEMENT_NODE
					&&nodeForecastWeather.getNodeName().equals("forecast_conditions"))
			{
				        // 建立forecast_conditions下节点数组
				        NodeList nodeListForecastConditions =  nodeForecastWeather.getChildNodes();
				        
				        for(int j=0; j<nodeListForecastConditions.getLength(); j++)
				        {
				        	//day_of_week low high condition
				        	Node data = nodeListForecastConditions.item(j);
						if(data.getNodeName().equals("day_of_week"))
						{
							wd.setDayOfWeek(data.getAttributes().getNamedItem("data").getNodeValue());
						}
						else if(data.getNodeName().equals("low"))
						{
							wd.setLow(data.getAttributes().item(0).getNodeValue());
						}
						else if(data.getNodeName().equals("high"))
						{
							wd.setHigh(data.getAttributes().item(0).getNodeValue());
						}
						else if(data.getNodeName().equals("condition"))
						{
							wd.setConditionData(data.getAttributes().item(0).getNodeValue());
						}
				        }
				        System.out.println(wd.printWeatheaInfo());
					
				}
			}
		
		}
			
		
	
		
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		Weather weather = new Weather();
		weather.resolutionXML("http://www.google.com/ig/api?hl=zh_CN&weather=wuhan");
 
	}
	class WeatherData
	{
		String dayOfWeek;
		String low;
		String high;
		String conditionData;
		public void setDayOfWeek(String dayOfWeek)
		{
			this.dayOfWeek = dayOfWeek;
		}
		public void setLow(String low)
		{
			this.low = low;
		}

		public void setHigh(String high)
		{
			this.high = high;
		}

		public void setConditionData(String conditionData)
		{
			this.conditionData = conditionData;
		}
		
		public String printWeatheaInfo()
		{
			return dayOfWeek+"\n"+"温度: "+low+"~~"+high+"  \n天气情况: "+conditionData;
		}
	}
}