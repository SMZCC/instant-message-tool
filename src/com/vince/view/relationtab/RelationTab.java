/**
 * @author 亮
 *
 */
package com.vince.view.relationtab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.vince.controller.listener.RelationTabScrollPaneListener;
import com.vince.controller.util.Util;
import com.vince.view.relationerdetail.RelationerDetail;
import com.vince.view.util.ContentPanel;

public class RelationTab extends ContentPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8178433007378228637L;
	private static RelationTab relationTab = null;
	public static final int MARGIN_TOP = 27;
	public static final int BUTTON_INIT_POSITION_X = 11;
	public static final int BUTTON_INIT_POSITION_Y = 11;
	public static final int NAME_LABEL_INIT_POSITION_X = 29;
	public static final int NAME_LABEL_WIDTH_SPACE = 5;
	public static final int DATA_LABEL_X_SPACE = 3;
	public static final int DATA_LABEL_Y_SPACE = 2;
	public static final int NAME_LABEL_COMPARE = 7;
	public static final int BUTTON_COMPARE_TO_NAME_LABEL = 4;
	public static final Font DEFAULT_FONT = new Font("Dialog", Font.PLAIN, 14);
	public static final Font NAME_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 14);
	public static final Font DATA_LABEL_FONT = new Font("微软雅黑", Font.PLAIN, 10);
	//public static JScrollPane scroll = null;
	//public String[] sortByGroupName = new String[]{"我的设备","我的好友","朋友","家人","小学同学","中学同学","高中同学","大学同学","我恢复的好友","老乡","生活服务","黑名单"};
	//public String[] srotByGroupData = new String[]{"1/1","38/57","2/2","17/37","12/16","17/30","85/136","100/160","0/0","1/7","0/1","0/11"};
	public List<String> sortByGroupName = new ArrayList<String>();
	public List<String> sortByGroupData = new ArrayList<String>();
	public List<String> sortByGroupId = new ArrayList<String>();
	public List<JButton> sortByGroupIcon = new ArrayList<JButton>();
	public List<JLabel> sortByGroupNameLabel = new ArrayList<JLabel>();
	public List<JLabel> sortByGroupDataLabel = new ArrayList<JLabel>();
	public Map<Integer,List<RelationerDetail>> groupDetailByGroupName = new HashMap<Integer, List<RelationerDetail>>();
	public String account = null;
	/**
	 * 
	 */
	private RelationTab(String url,int realWidth,Color color,String account) {
		// TODO Auto-generated constructor stub
		super(url,realWidth,color);
		this.account = account;
		init();
	}
	public static RelationTab getRelationTab(String url,int realWidth,Color color,String account){
		if(relationTab == null) {
			relationTab = new RelationTab(url,realWidth,color,account);
		}
		return relationTab;
	}
	public void getRelationTabScrollPaneListener(RelationTabScrollPaneListener relationTabScrollPaneListener){
		//实现组件的监控器
		for(JButton temp : sortByGroupIcon){
			temp.addMouseListener(relationTabScrollPaneListener);
		}
		for(JLabel temp : sortByGroupNameLabel){
			temp.addMouseListener(relationTabScrollPaneListener);
		}
		for(JLabel temp : sortByGroupDataLabel){
			temp.addMouseListener(relationTabScrollPaneListener);
		}
	}
	private void init(){
		initData();
		initView();
	}
	private void initView(){
		this.setPreferredSize(new Dimension(283, NAME_LABEL_COMPARE+MARGIN_TOP*sortByGroupName.size()));
		this.setLayout(null);
		//开始模拟
		JLabel labelTemp = null;JButton buttonTemp = null;int intTemp = 0,xTemp = 0,yTemp = NAME_LABEL_COMPARE,widthTemp = 0;
		JLabel label = null;
		for(String temp : sortByGroupName){
			buttonTemp = new JButton();
			buttonTemp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/rightCaretUnhover.png")));
			buttonTemp.setBounds(BUTTON_INIT_POSITION_X, BUTTON_INIT_POSITION_Y+intTemp*MARGIN_TOP, 8, 11);
			buttonTemp.setBorderPainted(false);
			buttonTemp.setContentAreaFilled(false);
			buttonTemp.setFocusPainted(false);
			add(buttonTemp);
			sortByGroupIcon.add(buttonTemp);
			labelTemp = new JLabel();
			labelTemp.setText(temp);
			labelTemp.setFont(NAME_LABEL_FONT);
			labelTemp.setForeground(Color.black);
			yTemp = intTemp == 0 ? yTemp : yTemp + MARGIN_TOP;
			//yTemp = yTemp + MARGIN * intTemp;
			labelTemp.setBounds(NAME_LABEL_INIT_POSITION_X, yTemp, labelTemp.getFontMetrics(DEFAULT_FONT).stringWidth(temp)+NAME_LABEL_WIDTH_SPACE, labelTemp.getFontMetrics(DEFAULT_FONT).getHeight());
			//labelTemp.setBorder(BorderFactory.createEtchedBorder());
			add(labelTemp);
			sortByGroupNameLabel.add(labelTemp);
			widthTemp = NAME_LABEL_INIT_POSITION_X + labelTemp.getFontMetrics(DEFAULT_FONT).stringWidth(temp)+NAME_LABEL_WIDTH_SPACE;
			label = new JLabel(sortByGroupData.get(intTemp));
			label.setFont(DATA_LABEL_FONT);
			label.setForeground(Color.black);
			label.setBounds(widthTemp + DATA_LABEL_X_SPACE, yTemp+DATA_LABEL_Y_SPACE, label.getFontMetrics(getFont()).stringWidth(label.getText()), label.getFontMetrics(getFont()).getHeight());
			add(label);
			sortByGroupDataLabel.add(label);
			buttonTemp = null;label = null;labelTemp = null;
			intTemp++;
		}		
	}
	private void initData(){
		/*初始化数据*/
		JSONArray jsonArray = (JSONArray) Util.parseJSONByParamName(account,"groupData");
		JSONObject jsonObject = null;
		for(int i = 0,length = jsonArray.size();i < length;i++){
			jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.has("group_id")){
				sortByGroupId.add(jsonObject.getString("group_id"));
			}
			if(jsonObject.has("group_name")){
				sortByGroupName.add(jsonObject.getString("group_name"));
			}
			if(jsonObject.has("onlineDivideTotal")){
				sortByGroupData.add(jsonObject.getString("onlineDivideTotal"));
			}
		}	
	}
}
