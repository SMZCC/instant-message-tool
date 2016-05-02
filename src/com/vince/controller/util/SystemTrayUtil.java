/**
 * @author 流浪大法师
 * @time 2015-4-30 上午1:48:01
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
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.Popup;
import javax.swing.plaf.PopupMenuUI;

public final class SystemTrayUtil {

	/**
	 * 
	 */
	private static SystemTrayUtil systemTrayUtilInstance = null;
	private TrayIcon trayIcon = null;
	private SystemTray systemTray = null;
	private JPopupMenu popMenu = null;
	private JMenuItem exit = null,show = null,onLine = null,lockQq = null;
	private JMenuItem qChat = null,leave = null,busy = null;
	private JMenuItem noDisturb = null,invisible = null,offLine = null;
	private JMenuItem addStatusInfo = null,closeAllSound = null,closeAvatarFlashing = null;
	private String toolTip = null;
	public static int SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH = 25;
	public static int SYSTEM_TRAY_UTIL_SET_HEIGHT = 22;
	public static int SYSTEM_TRAY_UTIL_SET_WIDTH = 178;
	private SystemTrayUtil() {
		// TODO Auto-generated constructor stub
		super();
	}
	public static SystemTrayUtil getSystemTrayInstance(){
		if(systemTrayUtilInstance == null) systemTrayUtilInstance = new SystemTrayUtil();
		return systemTrayUtilInstance;
	}
	public void setSystemTray(String imagePath,String userName,boolean hasSound,boolean hasMessageReminder,boolean isTaskAvatarFlashing){
		if(SystemTray.isSupported()){
			
			toolTip = "QQ："+userName+ Util.getNewLine() +
				"声音："+(hasSound ?"开启":"关闭")+Util.getNewLine()+
				"消息提示框："+(hasMessageReminder ? "开启":"关闭")+Util.getNewLine()+
				"会话消息："+(isTaskAvatarFlashing ? "任务栏头像闪动":"任务栏头像不闪动");
			if(trayIcon == null){
				trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(imagePath)));
				//trayIcon.setPopupMenu(popMenu);
				trayIcon.setToolTip(toolTip);
				//实现系统托盘的事件
			}
			if(popMenu == null){
				popMenu = new JPopupMenu();
				popMenu.setSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,13 * SYSTEM_TRAY_UTIL_SET_HEIGHT));
			}
			//width 177x height 308x,29px 308px
			try {
				if(qChat == null)				qChat = new JMenuItem("Q我吧"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\qChat.png", "Q我吧", false);
					}
				};
				qChat.setToolTipText(setMyTooltip("表示希望好友主动联系您。",true,true,true,1));
				//qChat.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(onLine == null)				onLine = new JMenuItem("我在线上"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\online.png", "我在线上", false);
					}
				};
				onLine.setToolTipText(setMyTooltip("表示希望好友看到你在线。",true,true,true,0));
				//onLine.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				

				if(leave == null)				leave = new JMenuItem("离开"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\leave.png", "离开", false);
					}
				};
				leave.setToolTipText(setMyTooltip("表示离开，暂无法处理消息。", true, true, true, 2));
				//leave.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(busy == null)				busy = new JMenuItem("忙碌"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\busy.png", "忙碌", false);
					}
				};
				busy.setToolTipText(setMyTooltip("表示忙碌，不会及时处理消息。", true, true, true, 3));
				//busy.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(noDisturb == null)			noDisturb = new JMenuItem("请勿打扰"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\noDisturb.png", "请勿打扰",false);
					}
				};
				noDisturb.setToolTipText(setMyTooltip("表示不想被打扰。", true, true, true, 4));
				//noDisturb.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(invisible == null)			invisible = new JMenuItem("隐身"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\invisible.png", "隐身",false);
					}
				};
				invisible.setToolTipText(setMyTooltip("表示好友看到您是离线的。", true, true, true, 5));
				//invisible.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(offLine == null)				offLine = new JMenuItem("离线"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\offLine.png", "离线",false);
					}
				};
				offLine.setToolTipText(setMyTooltip("断开与服务器连接。", false, false, false, 6));
				//offLine.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(addStatusInfo == null)		addStatusInfo = new JMenuItem("添加状态信息"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "添加状态信息",false);
					}
				};
				addStatusInfo.setToolTipText(setMyTooltip(null, false, false, false, 7));
				//addStatusInfo.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(closeAllSound == null)		closeAllSound = new JMenuItem("关闭所有声音"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\closeAllSound.png", "关闭所有声音", false);
					}
				};
				closeAllSound.setToolTipText(setMyTooltip(null, false, false, false, 8));
				//closeAllSound.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(closeAvatarFlashing == null)	closeAvatarFlashing = new JMenuItem("关闭头像闪动"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "关闭头像闪动",false);
					}
				};
				closeAvatarFlashing.setToolTipText(setMyTooltip(null, false, false, false, 9));
				//closeAvatarFlashing.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(lockQq == null)				lockQq = new JMenuItem("锁定QQ Ctrl + Alt + L"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, "src\\images\\lockQq.png", "锁定QQ Ctrl + Alt + L",false);
					}
				};
				lockQq.setToolTipText(setMyTooltip(null, false, false, false, 10));
				//lockQq.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(show == null)				show = new JMenuItem("打开主面板"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "打开主面板",false);
					}
				};
				show.setToolTipText(setMyTooltip(null, false, false, false, 11));
				//show.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
				if(exit == null)				exit = new JMenuItem("退出"){
					@Override
					protected void paintComponent(Graphics g2) {
						// TODO Auto-generated method stub
						//popMenu.add(onLine);
						int componentWidth = popMenu.getPreferredSize().width;
						paintComponentDetails(g2, SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, componentWidth - SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH, SYSTEM_TRAY_UTIL_SET_HEIGHT, null, "退出",false);
					}
				};
				exit.setToolTipText(setMyTooltip(null, false, false, false, 12));
				//exit.setPreferredSize(new Dimension(SYSTEM_TRAY_UTIL_SET_WIDTH,SYSTEM_TRAY_UTIL_SET_HEIGHT));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			popMenu.add(qChat);popMenu.add(leave);popMenu.add(busy);popMenu.add(onLine);
			popMenu.add(noDisturb);popMenu.add(invisible);popMenu.add(offLine);popMenu.add(addStatusInfo);
			popMenu.add(closeAllSound);popMenu.add(closeAvatarFlashing);popMenu.add(lockQq);popMenu.add(show);popMenu.add(exit);
			
			systemTray = SystemTray.getSystemTray();
			
			try {
//				System.out.println(systemTray.getTrayIcons().length);
				if(systemTray.getTrayIcons().length == 0)	systemTray.add(trayIcon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String setMyTooltip(String content,boolean hasSound,boolean hasMessageReminder,boolean isTaskAvatarFlashing,int index){
		if(index > 6) return null;
		else if(index == 6) return content;
		else{
			String str = "<html>"+content+ "<br/>" +
					"声音："+(hasSound ?"开启":"关闭")+"<br/>"+
					"消息提示框："+(hasMessageReminder ? "开启":"关闭")+"<br/>"+
					"会话消息：";
			if(index == 1){
				str += isTaskAvatarFlashing ? "自动弹出会话窗口":""+"</html>";
			}else if(index == 3|| index == 4){
				str += isTaskAvatarFlashing ? "任务栏显示气泡":""+"</html>";
			}else{
				str += isTaskAvatarFlashing ? "任务栏头像闪动":"任务栏头像不闪动"+"</html>";
			}
			return str;
		}
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
			g.setColor(Color.white);
			g.drawRect(firstLength, 0, secondLength, componentHeight);
			g.fillRect(firstLength, 0, secondLength, componentHeight);
		}else{
			g.setColor(new Color(51,127,209));
			g.drawRect(0, 0, firstLength + secondLength, componentHeight);
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
	 * @return the systemTray
	 */
	public SystemTray getSystemTray() {
		return systemTray;
	}
	/**
	 * @param systemTray the systemTray to set
	 */
	public void setSystemTray(SystemTray systemTray) {
		this.systemTray = systemTray;
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
	 * @return the toolTip
	 */
	public String getToolTip() {
		return toolTip;
	}
	/**
	 * @param toolTip the toolTip to set
	 */
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	/**
	 * @return the onLine
	 */
	public JMenuItem getOnLine() {
		return onLine;
	}
	/**
	 * @param onLine the onLine to set
	 */
	public void setOnLine(JMenuItem onLine) {
		this.onLine = onLine;
	}
	/**
	 * @return the lockQq
	 */
	public JMenuItem getLockQq() {
		return lockQq;
	}
	/**
	 * @param lockQq the lockQq to set
	 */
	public void setLockQq(JMenuItem lockQq) {
		this.lockQq = lockQq;
	}
	/**
	 * @return the qChat
	 */
	public JMenuItem getqChat() {
		return qChat;
	}
	/**
	 * @param qChat the qChat to set
	 */
	public void setqChat(JMenuItem qChat) {
		this.qChat = qChat;
	}
	/**
	 * @return the leave
	 */
	public JMenuItem getLeave() {
		return leave;
	}
	/**
	 * @param leave the leave to set
	 */
	public void setLeave(JMenuItem leave) {
		this.leave = leave;
	}
	/**
	 * @return the busy
	 */
	public JMenuItem getBusy() {
		return busy;
	}
	/**
	 * @param busy the busy to set
	 */
	public void setBusy(JMenuItem busy) {
		this.busy = busy;
	}
	/**
	 * @return the noDisturb
	 */
	public JMenuItem getNoDisturb() {
		return noDisturb;
	}
	/**
	 * @param noDisturb the noDisturb to set
	 */
	public void setNoDisturb(JMenuItem noDisturb) {
		this.noDisturb = noDisturb;
	}
	/**
	 * @return the invisible
	 */
	public JMenuItem getInvisible() {
		return invisible;
	}
	/**
	 * @param invisible the invisible to set
	 */
	public void setInvisible(JMenuItem invisible) {
		this.invisible = invisible;
	}
	/**
	 * @return the offLine
	 */
	public JMenuItem getOffLine() {
		return offLine;
	}
	/**
	 * @param offLine the offLine to set
	 */
	public void setOffLine(JMenuItem offLine) {
		this.offLine = offLine;
	}
	/**
	 * @return the addStatusInfo
	 */
	public JMenuItem getAddStatusInfo() {
		return addStatusInfo;
	}
	/**
	 * @param addStatusInfo the addStatusInfo to set
	 */
	public void setAddStatusInfo(JMenuItem addStatusInfo) {
		this.addStatusInfo = addStatusInfo;
	}
	/**
	 * @return the closeAllSound
	 */
	public JMenuItem getCloseAllSound() {
		return closeAllSound;
	}
	/**
	 * @param closeAllSound the closeAllSound to set
	 */
	public void setCloseAllSound(JMenuItem closeAllSound) {
		this.closeAllSound = closeAllSound;
	}
	/**
	 * @return the closeAvatarFlashing
	 */
	public JMenuItem getCloseAvatarFlashing() {
		return closeAvatarFlashing;
	}
	/**
	 * @param closeAvatarFlashing the closeAvatarFlashing to set
	 */
	public void setCloseAvatarFlashing(JMenuItem closeAvatarFlashing) {
		this.closeAvatarFlashing = closeAvatarFlashing;
	}
}
