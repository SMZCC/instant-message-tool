/**
 * @author 流浪大法师
 * @time 2015-5-9 上午3:41:07
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.util;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

import com.vince.controller.listener.ChatFrameListener;
import com.vince.controller.listener.LoginFrameListener;
import com.vince.controller.util.LogUtil;
import com.vince.controller.util.LoginSystemTrayUtil;
import com.vince.view.chatFrame.ChatFrame;
import com.vince.view.loginframe.Login;

public class MenuButtonsComponent extends JPanel implements MouseListener{

	private boolean hasDeleteButton = false;
	private boolean hasMaxButton = false;
	private boolean hasMinButton = false;
	private boolean hasMoreButton = false;
	private JButton deleteButton = null;
	private JButton minButton = null;
	private JButton maxButton = null;
	private JButton moreButton = null;
	private JButton button = null;
	private Login login = null;
	private ChatFrame chatFrame = null;
	private ImageIcon imageIcon = null;
	private LoginSystemTrayUtil loginSystemTrayUtil = null;
	private List<JButton> list = new ArrayList<JButton>();
	private int width = 0;
	private int height = 0;
	private MyConfirmDialog myConfirmDialog = null;
	private final static int DISTANCE_BETWEEN_WITH_BUTTONS = 0;
	private final static String DEFAULT_DELETE_BUTTON_IMAGE_PATH = "src"+File.separator+"images"+File.separator+"delete.png";
	private final static String DEFAULT_MIN_BUTTON_IMAGE_PATH = "src"+File.separator+"images"+File.separator+"min.png";
	private final static String DEFAULT_MAX_BUTTON_IMAGE_PATH = "src"+File.separator+"images"+File.separator+"max.png";
	private final static String DEFAULT_MORE_BUTTON_IMAGE_PATH = "src"+File.separator+"images"+File.separator+"more.png";
	private final static Color DELETE_BUTTON_COLOR_ON_HOVER = new Color(212,64,39);
	private final static Color MINIMUM_BUTTON_COLOR_ON_HOVER = new Color(58,149,222);
	private final static Color MAXIMUM_BUTTON_COLOR_ON_HOVER = new Color(58,149,222);
	private final static Color MORE_BUTTON_COLOR_ON_HOVER = new Color(58,149,222);
	private LoginFrameListener loginFrameListener = null;//监听器
	private ChatFrameListener chatFrameListener = null;
	/**
	 * 默认构造方法
	 */
	public MenuButtonsComponent() {
		// TODO Auto-generated constructor stub
		this(true, true, true, true);
	}
	/**
	 * @param boolean hasMaxButton
	 * @param boolean hasMinButton
	 * @param boolean hasMoreButton
	 */
	public MenuButtonsComponent(boolean hasMaxButton,boolean hasMinButton,boolean hasMoreButton){
		this(hasMaxButton, hasMinButton, true, hasMoreButton);
	}
	/**
	 * @param boolean hasDeleteButton
	 * @param boolean hasMaxButton
	 * @param boolean hasMinButton
	 * @param boolean hasMoreButton
	 */	
	public MenuButtonsComponent(boolean hasMaxButton,boolean hasMinButton,boolean hasDeleteButton,boolean hasMoreButton){
		this.hasMaxButton = hasMaxButton;
		this.hasMinButton = hasMinButton;
		this.hasDeleteButton = hasDeleteButton;
		this.hasMoreButton = hasMoreButton;
		initMenuButtonsComponent();
	}
	public void initMenuButtonsComponent(){
		if(hasMoreButton){
			moreButton = setButtonConfig(DEFAULT_MORE_BUTTON_IMAGE_PATH,"设置");
		}
		if(hasMinButton){
			minButton = setButtonConfig(DEFAULT_MIN_BUTTON_IMAGE_PATH,"最小化");
		}
		if(hasMaxButton){
			maxButton = setButtonConfig(DEFAULT_MAX_BUTTON_IMAGE_PATH,"最大化");
		}
		if(hasDeleteButton){
			deleteButton = setButtonConfig(DEFAULT_DELETE_BUTTON_IMAGE_PATH,"关闭");
		}
		setOpaque(false);
		setLayout(null);
		setSize(width + DISTANCE_BETWEEN_WITH_BUTTONS * (list.size() - 1), height);
		setButtonPosition();
	}
	/**
	 * @return the deleteButton
	 */
	public void setLogin(Login login){
		this.login = login;
	}
	public void setChatFrame(ChatFrame chatFrame){
		this.chatFrame = chatFrame;
	}
	public JButton getDeleteButton() {
		return deleteButton;
	}
	/**
	 * @param deleteButton the deleteButton to set
	 */
	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}
	/**
	 * @return the minButton
	 */
	public JButton getMinButton() {
		return minButton;
	}
	/**
	 * @param minButton the minButton to set
	 */
	public void setMinButton(JButton minButton) {
		this.minButton = minButton;
	}
	/**
	 * @return the maxButton
	 */
	public JButton getMaxButton() {
		return maxButton;
	}
	/**
	 * @param maxButton the maxButton to set
	 */
	public void setMaxButton(JButton maxButton) {
		this.maxButton = maxButton;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == minButton){
			if(login != null){
				login.getJframe().setVisible(false);
				/*生成系统托盘*/
				loginSystemTrayUtil = LoginSystemTrayUtil.getLoginSystemTrayInstance();
				loginSystemTrayUtil.setSystemTray("./images/qqSystemTray.png", "QQ");
				if(loginFrameListener != null){
					loginSystemTrayUtil.getExit().addActionListener(loginFrameListener);
					loginSystemTrayUtil.getShow().addActionListener(loginFrameListener);
					loginSystemTrayUtil.getTrayIcon().addMouseListener(loginFrameListener);
				}
			}
		}else if(e.getSource() == maxButton){
			
		}else if(e.getSource() == moreButton){
			if(login != null){
				if(myConfirmDialog  == null || myConfirmDialog.getDispose()){
					myConfirmDialog = new MyConfirmDialog(login.getJframe(),"网络代理不好搞，此功能暂未开发，么么哒:)","温馨提示");
					myConfirmDialog.setVisible(true);
				}
			}
		}else if(e.getSource() == deleteButton){
			LogUtil.log("正在关闭程序ing",null,this.getClass().getSimpleName()+".java第168行");
			System.exit(0);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		button = (JButton) e.getSource();
		if(e.getSource() == maxButton){
			button.setBackground(MAXIMUM_BUTTON_COLOR_ON_HOVER);
		}else if(e.getSource() == minButton){
			button.setBackground(MINIMUM_BUTTON_COLOR_ON_HOVER);
		}else if(e.getSource() == deleteButton){
			button.setBackground(DELETE_BUTTON_COLOR_ON_HOVER);
		}else if(e.getSource() == moreButton){
			button.setBackground(MORE_BUTTON_COLOR_ON_HOVER);
		}
		button.setContentAreaFilled(true);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		button = (JButton) e.getSource();
		if(e.getSource() == maxButton){}
		else if(e.getSource() == minButton){}
		else if(e.getSource() == deleteButton){}
		else if(e.getSource() == moreButton){}
		button.setContentAreaFilled(false);
	}
	public JButton setButtonConfig(String path, String tooltips){
		imageIcon = new ImageIcon(path);
		button = new JButton(imageIcon);
		button.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		button.setToolTipText(tooltips);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addMouseListener(this);
		width = width + imageIcon.getIconWidth();
		height = ( height > imageIcon.getIconHeight() ) ?	height : imageIcon.getIconHeight();
		list.add(button);
		return button;
	}
	public void setButtonPosition(){
		Iterator<JButton> iterator = list.listIterator();
		int index = 0;
		while(iterator.hasNext()){
			button = iterator.next();
			if(button == list.get(0)){
				button.setLocation(0, 0);
			}else{
				button.setLocation(list.get(index - 1).getLocation().x + list.get(index - 1).getSize().width + DISTANCE_BETWEEN_WITH_BUTTONS, 0);
			}
			index++;
			add(button);
		}
	}
	/**
	 * @return the loginFrameListener
	 */
	public LoginFrameListener getLoginFrameListener() {
		return loginFrameListener;
	}
	/**
	 * @param loginFrameListener the loginFrameListener to set
	 */
	public void setLoginFrameListener(LoginFrameListener loginFrameListener) {
		this.loginFrameListener = loginFrameListener;
	}
	/**
	 * @return the chatFrameListener
	 */
	public ChatFrameListener getChatFrameListener() {
		return chatFrameListener;
	}
	/**
	 * @param chatFrameListener the chatFrameListener to set
	 */
	public void setChatFrameListener(ChatFrameListener chatFrameListener) {
		this.chatFrameListener = chatFrameListener;
	}
}
