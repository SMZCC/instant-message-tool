/**
 * @author 流浪大法师
 * @time 2016-3-30 下午3:50:19
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.util;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public final class LoginSystemTrayUtil {
	private static LoginSystemTrayUtil LoginSystemTrayUtilInstance = null;
	private TrayIcon trayIcon = null;
	private SystemTray systemTray = null;
	private JPopupMenu popMenu = null;
	private JMenuItem exit = null,show = null;
	private String toolTip = null;
	public static int SYSTEM_TRAY_UTIL_SET_HEIGHT = 22;
	public static int SYSTEM_TRAY_UTIL_SET_WIDTH = 170;
	public static int SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH = 25;
	private LoginSystemTrayUtil() {
		// TODO Auto-generated constructor stub
		super();
	}
	public static LoginSystemTrayUtil getLoginSystemTrayInstance(){
		if(LoginSystemTrayUtilInstance == null) LoginSystemTrayUtilInstance = new LoginSystemTrayUtil();
		return LoginSystemTrayUtilInstance;
	}
	public void setSystemTray(String imagePath, String toolTips){
		if(SystemTray.isSupported()){
			
			toolTip = toolTips;
			if(trayIcon == null){
				trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(imagePath)));
				//trayIcon.setPopupMenu(popMenu);
				trayIcon.setToolTip(toolTip);
				//实现系统托盘的事件
			}
			if(popMenu == null){
				popMenu = new JPopupMenu();
				popMenu.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,2 * SYSTEM_TRAY_UTIL_SET_HEIGHT));
			}
			//width 177x height 308x,29px 308px
			try {
				if(show == null)				show = new JMenuItem(){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						int componentWidth = popMenu.getSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "打开主面板",false);
					}
				};
				show.setToolTipText(setMyTooltip(null,11));
				//show.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(exit == null)				exit = new JMenuItem(){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "退出",false);
					}
				};
				exit.setToolTipText(setMyTooltip(null,12));
				//exit.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			popMenu.add(show);popMenu.add(exit);
			systemTray = SystemTray.getSystemTray();
			try {
				if(systemTray.getTrayIcons().length == 0)	systemTray.add(trayIcon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * @return the exit
	 */
	public JMenuItem getExit() {
		return exit;
	}
	/**
	 * @param exit the exit to set
	 */
	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}
	/**
	 * @return the show
	 */
	public JMenuItem getShow() {
		return show;
	}
	/**
	 * @param show the show to set
	 */
	public void setShow(JMenuItem show) {
		this.show = show;
	}
	/**
	 * @return the trayIcon
	 */
	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
	/**
	 * @param trayIcon the trayIcon to set
	 */
	public void setTrayIcon(TrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}
	/**
	 * @return the popMenu
	 */
	public JPopupMenu getPopMenu() {
		return popMenu;
	}
	/**
	 * @param popMenu the popMenu to set
	 */
	public void setPopMenu(JPopupMenu popMenu) {
		this.popMenu = popMenu;
	}
	public String setMyTooltip(String content,int index){
		if(index > 6) return null;
		else if(index == 6) return content;
		return "";
	}
	public void paintComponentDetails(Graphics g2,int firstLength,int secondLength,int componentHeight,String url,String content,boolean isMouseEnter){
		Graphics2D g = (Graphics2D)g2;
		//实现图标的绘制
		if(url != null){
			Image image = new ImageIcon(url).getImage();
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			int x = (firstLength - width)/2;
			int y = (componentHeight - height)/2;
			g.drawImage(image, x, y, width, height, null, null);
			image = null;
		}
		if(!isMouseEnter){
			g.setColor(Color.WHITE);
			//g.drawRect(firstLength, 0, secondLength, componentHeight);
			g.fillRect(firstLength, 0, secondLength, componentHeight);
		}else{
			g.setColor(new Color(51,127,209));
			//g.drawRect(0, 0, firstLength + secondLength, componentHeight);
			g.fillRect(0, 0, firstLength + secondLength, componentHeight);
		}
		g.translate(firstLength, 0);
		if(content != null){
			if(!isMouseEnter)	g.setColor(Color.BLACK);
			else g.setColor(Color.white);
			g.setFont(new Font("微软雅黑",Font.PLAIN,12));
			g.drawString(content, 11, 14);
		}
		g = null;//销毁引用
	}
}
