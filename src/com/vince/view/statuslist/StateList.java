/**
 * @author 流浪大法师
 * @time 2016-3-13 下午1:51:59
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 * @warning 数据库用户表的status为0代表在线，1表示离开，2表示忙碌，3表示请勿打扰，4表示Q我吧，5表示隐身，99表示正常，100表示禁用/注销
 */
package com.vince.view.statuslist;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import com.vince.controller.util.Util;

public class StateList extends JDialog{
	private String[][] statusImageFilePathAndDescription = Util.statusImageFilePathAndDescription;
	private ArrayList<JButton> status = null;
	private Font FONT = new Font("宋体",Font.PLAIN,12);
	private final static int IMAGE_ICON_CONTAINER_HEIGHT = 25;
	private final static int STATUS_LIST_WIDTH = 100;
	private int STATUS_LIST_HEIGHT = 0;
	private final static Color DEFAULT_COLOR = new Color(54,54,54);
	private final static Insets DEFAULT_INSETS = new Insets(0,0,0,0);
	private JWindow owner = null;
	public StateList(JWindow jWindow) {
		this.owner = jWindow;
		init();
	}
	private void init(){
		initStatus();
		initDialog();
	}
	private void initStatus(){
		JButton button = null;
		ImageIcon imageIcon = null;
		int index = 0,length = statusImageFilePathAndDescription.length;
		status = new ArrayList<JButton>(length);
		for(String[] str : statusImageFilePathAndDescription){
			imageIcon = new ImageIcon(str[0]);
			button = new JButton(str[1],imageIcon);
			initStatusView(button,index++);
			add(button);
			status.add(button);
			button = null;
		}
		STATUS_LIST_HEIGHT = IMAGE_ICON_CONTAINER_HEIGHT * length;
	}
	private void initStatusView(JButton button,int index){
		button.setHorizontalAlignment(JButton.LEFT);//设置按钮上文字的对齐方式
		button.setBounds(0, index * IMAGE_ICON_CONTAINER_HEIGHT, STATUS_LIST_WIDTH, IMAGE_ICON_CONTAINER_HEIGHT);
		button.setBackground(Color.white);
		button.setForeground(DEFAULT_COLOR);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);
		button.setMargin(DEFAULT_INSETS);
		button.setFont(FONT);
		button = null;
	}
	private void initDialog(){
		int x = owner.getLocationOnScreen().x + 99;
		int y = owner.getLocationOnScreen().y + 279;
		setBounds(x, y, STATUS_LIST_WIDTH, STATUS_LIST_HEIGHT);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);
		setBackground(Color.white);		
	}
	/**
	 * @return the status
	 */
	public ArrayList<JButton> getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ArrayList<JButton> status) {
		this.status = status;
	}	
}
