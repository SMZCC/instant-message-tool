/**
 * @author 刘亮
 *
 */
package com.vince.controller.listener;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.vince.controller.util.SystemTrayUtil;
import com.vince.controller.util.Util;
import com.vince.view.util.ExitDialog;


public class MainFrameListener extends MyAdapter{
	private JFrame jframe = null;
	private ExitDialog exitDialog = null;
	//private SystemTrayUtil systemTrayUtil = null;
	private Timer timer = null;
	private TimerTask timerTask = null;
	private boolean isDragging = false;
	private int x = -1;
	private int y = -1;
	public MainFrameListener(){
		
	}
	public MainFrameListener(JFrame jframe,ExitDialog exitDialog){
		this.jframe = jframe;
		this.exitDialog = exitDialog;
		//this.systemTrayUtil = systemTrayUtil;
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jframe){
			exitDialog = new ExitDialog(jframe, "关闭程序", Dialog.ModalityType.APPLICATION_MODAL);
			exitDialog.addMouseListener(this);
			exitDialog.addMouseMotionListener(this);
			exitDialog.addWindowListener(this);
			//实现确定按钮和取消按钮的事件监听
			exitDialog.getConfirm().addMouseListener(this);
			exitDialog.getCancel().addMouseListener(this);
			exitDialog.setVisible(true);
		}else if(e.getSource() == exitDialog){
			exitDialog.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getqChat()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getqChat(),"src\\images\\qChat.png", "Q我吧");
					//mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getOnLine(),"src\\images\\online.png", "我在线上");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getOnLine()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getOnLine(),"src\\images\\online.png", "我在线上");
					//mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getqChat(),"src\\images\\qChat.png", "Q我吧");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getLeave()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getLeave(),"src\\images\\leave.png", "离开");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getBusy()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getBusy(),"src\\images\\busy.png", "忙碌");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getNoDisturb()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getNoDisturb(),"src\\images\\noDisturb.png", "请勿打扰");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getInvisible()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getInvisible(),"src\\images\\invisible.png", "隐身");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getOffLine()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getOffLine(),"src\\images\\offLine.png", "离线");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getAddStatusInfo()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getAddStatusInfo(),null, "添加状态信息");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getCloseAllSound()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getCloseAllSound(),"src\\images\\closeAllSound.png", "关闭所有声音");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getCloseAvatarFlashing()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getCloseAvatarFlashing(),null, "关闭头像闪动");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getLockQq()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getLockQq(),"src\\images\\lockQq.png", "锁定QQ Ctrl + Alt + L");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getShow()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getShow(),null, "打开面板");
				}
			});
			
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getExit()){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					mouseEnterInvokeRepaint(SystemTrayUtil.getSystemTrayInstance().getExit(),null, "退出");
				}
			});
			
		}else{
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exitDialog.getConfirm()){
			if(exitDialog.getMiniRadioButton().isSelected()){
				if(jframe.isVisible())  {
					exitDialog.dispose();
					jframe.setVisible(false);
				}
				SystemTrayUtil systemTrayUtil = SystemTrayUtil.getSystemTrayInstance();
				systemTrayUtil.setSystemTray("./images/qqSystemTray.png", "自由自在的我(liuliangsir@foxmail.com)", false, true, true);
				systemTrayUtil.getExit().addActionListener(this);
				systemTrayUtil.getShow().addActionListener(this);
				//清除以前的mouseListener
				Util.removeAllOrSpecificItemMouseListener(systemTrayUtil.getOnLine());
				systemTrayUtil.getOnLine().addMouseListener(this);
				systemTrayUtil.getLeave().addMouseListener(this);
				systemTrayUtil.getLockQq().addMouseListener(this);
				systemTrayUtil.getAddStatusInfo().addMouseListener(this);
				systemTrayUtil.getBusy().addMouseListener(this);
				systemTrayUtil.getCloseAllSound().addMouseListener(this);
				systemTrayUtil.getCloseAvatarFlashing().addMouseListener(this);
				systemTrayUtil.getExit().addMouseListener(this);
				systemTrayUtil.getInvisible().addMouseListener(this);
				systemTrayUtil.getNoDisturb().addMouseListener(this);
				systemTrayUtil.getOffLine().addMouseListener(this);
				systemTrayUtil.getqChat().addMouseListener(this);
				systemTrayUtil.getShow().addMouseListener(this);
				systemTrayUtil.getTrayIcon().addMouseListener(this);
				systemTrayUtil = null;
			}else{
				System.exit(0);
			}
		}else if(e.getSource() == exitDialog.getCancel()){
			exitDialog.dispose();
		}else{
			
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exitDialog){
			x = e.getX();
			y = e.getY();
			isDragging = true;
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == exitDialog){
			if(isDragging){
				int left = exitDialog.getLocation().x;
                int top = exitDialog.getLocation().y;
                exitDialog.setLocation(left + e.getX() - x, top + e.getY() - y);
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		SystemTrayUtil systemTrayUtil = SystemTrayUtil.getSystemTrayInstance();
		if(e.getSource() == exitDialog){
			isDragging = false;
		}else if(e.getSource() == systemTrayUtil.getTrayIcon() && e.getButton() == MouseEvent.BUTTON3){
			Dimension dimension = systemTrayUtil.getPopMenu().getPreferredSize();
			systemTrayUtil.getPopMenu().setLocation(e.getX() - dimension.width, e.getY() - dimension.height);
			//systemTrayUtil.getPopMenu().setInvoker(systemTrayUtil.getPopMenu());
			//System.out.println(systemTrayUtil.getOnLine().getGraphics() == null);
			//systemTrayUtil.getPopMenu().repaint();
			systemTrayUtil.getPopMenu().validate();
			systemTrayUtil.getPopMenu().setVisible(true);
			dimension = null;
		}
		systemTrayUtil = null;
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getTrayIcon()){
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getExit()){
			System.exit(0);
		}else if(e.getSource() == SystemTrayUtil.getSystemTrayInstance().getShow()){
			
			if(!jframe.isVisible()) jframe.setVisible(true);
			//exitDialog.dispose();
		}
	}
	public void mouseEnterInvokeRepaint(JMenuItem menuItem,String url,String content){
		Graphics g = menuItem.getGraphics();
		SystemTrayUtil systemTrayUtil = SystemTrayUtil.getSystemTrayInstance();
		Dimension dimension = menuItem.getPreferredSize();
		int firstLength = SystemTrayUtil.SYSTEM_TRAY_UTIL_FIRST_DRAW_WIDTH;
		int secondLength = dimension.width - firstLength;
		int componentHeight = SystemTrayUtil.SYSTEM_TRAY_UTIL_SET_HEIGHT;
		systemTrayUtil.paintComponentDetails(g, firstLength, secondLength, componentHeight, url, content, true);
		//System.out.println("end......");
		menuItem.validate();
		
		g = null;//销毁引用
		systemTrayUtil = null;//同上
	}
}
