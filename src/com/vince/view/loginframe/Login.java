/**
 * @author 流浪大法师
 * @time 2016-3-12 下午2:31:51
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.loginframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JWindow;

import com.vince.controller.listener.LoginFrameListener;
import com.vince.controller.util.LoginSystemTrayUtil;
import com.vince.controller.util.Util;
import com.vince.view.util.MenuButtonsComponent;

public class Login {
	private JWindow jwindow = null;//主程序的容器
	private Container container = null;//窗体的内容窗格
	private LoginFrameListener loginFrameListener = null;//监听器
	private JPanel panel = null;//QQ logo面板
	private JPanel bottomPanel = null;//背景色
	private ImageIcon background = new ImageIcon("src\\images\\logo.gif");
	private ImageIcon avatarImageIcon = null;
	private ImageIcon avatarStatusImageIcon = new ImageIcon("src\\images\\online.png");
	private ImageIcon keyBoardImageIcon = new ImageIcon("src\\images\\keyBoard.png");
	private ImageIcon loginImageIcon = new ImageIcon("src\\images\\login.png");
	private ImageIcon logoImageIcon = new ImageIcon("src\\images\\qq.png");
	private JLabel avatar = null;
	private JLabel avatarStatus = null;
	private JLabel keyBoard = null;
	private JLabel register = null;
	private JLabel forgetSecret = null;
	private JTextField username = null;
	private JPasswordField password = null;
	private JCheckBox rememberPassword = null;
	private JCheckBox autoLogin = null;
	private JLabel rememberPasswordLabel = null;
	private JLabel autoLoginLabel = null;
	private JLabel logoLabel = null;
	private JButton login = null;
	private MenuButtonsComponent menuButtonsComponent = null;
	private static final int WIDTH = 430;
	private static final int HEIGHT = 330;
	private static final Font FONT = new Font("",Font.BOLD,15);
	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	public Login(){
		init();
	}
	private void init(){
		jwindow = new JWindow(new JFrame(){public boolean isShowing(){return true;}});
		panel = new JPanel(null){
			private static final long serialVersionUID = -496539397313335427L;

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(background.getImage(), 0, 0, background.getIconWidth(), background.getIconHeight(), this);
			}
		};
		logoImageIcon.setImage(logoImageIcon.getImage().getScaledInstance(147, 72, Image.SCALE_AREA_AVERAGING));
		logoLabel = new JLabel(logoImageIcon);
		logoLabel.setBounds(142, 55, 147, 72);
		panel.add(logoLabel);
		avatar = new JLabel();
		loginFrameListener = new LoginFrameListener(this);
		container = jwindow.getContentPane();
		/*QQ 最大化 最小化 关闭基础设置*/
		menuButtonsComponent = new MenuButtonsComponent(false, true, true);
		menuButtonsComponent.setLocation(WIDTH-menuButtonsComponent.getSize().width,0);
		menuButtonsComponent.setLogin(this);
		menuButtonsComponent.setLoginFrameListener(loginFrameListener);
		panel.add(menuButtonsComponent);
		/*QQ logo面板基础设置*/
		panel.setBounds(0, 0, WIDTH, 182);
		container.add(panel);
		/*QQ avatar label基础设置*/
		Util.setAvatar(null, avatar, true);
		container.add(avatar);
		
		/*QQ avatar label添加状态基础设置*/
		avatarStatusImageIcon.setImage(avatarStatusImageIcon.getImage().getScaledInstance(11, 11, Image.SCALE_SMOOTH));
		avatarStatus = new JLabel(avatarStatusImageIcon);
		avatarStatus.setBounds(59,57,22,22);
		avatar.add(avatarStatus);
		/*添加QQ号输入框基础设置*/
		username=new JTextField("",20);
		username.setFont(FONT);
		username.setBounds(125,200,200,29);
		username.setBorder(BorderFactory.createLineBorder(new Color(194,194,194)));
		username.setFocusable(true);
		container.add(username);
		/*添加密码框*/
		password=new JPasswordField("",20);
		password.setFont(FONT);
		password.setBounds(125,229,200,29);
	    password.setBorder(BorderFactory.createLineBorder(new Color(194, 194, 194)));//设置边框的颜色
		container.add(password);
		/*添加小键盘*/
		keyBoardImageIcon.setImage(keyBoardImageIcon.getImage());
		keyBoard = new JLabel(keyBoardImageIcon);
		keyBoard.setBounds(178, 5, keyBoardImageIcon.getIconWidth(), keyBoardImageIcon.getIconHeight());
		keyBoard.setFocusable(true);
		keyBoard.setToolTipText("打开软键盘");
		password.add(keyBoard);
		/*注册帐号的基础设置*/
		register = new JLabel("注册帐号");
		register.setBounds(332,205,51,15);
		register.setFont(new Font("",Font.PLAIN,12));
		register.setForeground(Color.blue);
		register.setCursor(DEFAULT_CURSOR);
		container.add(register);
		/*忘记密码的基础设置*/
		forgetSecret = new JLabel("找回密码");
		forgetSecret.setBounds(330,237,51,14);
		forgetSecret.setFont(new Font("",Font.PLAIN,12));
		forgetSecret.setForeground(Color.blue);
		forgetSecret.setCursor(DEFAULT_CURSOR);
		container.add(forgetSecret);
		/*记住密码的基础设置*/
		rememberPassword = new JCheckBox();
		rememberPassword.setBounds(125, 270, 15, 15);
		rememberPassword.setContentAreaFilled(false);
		container.add(rememberPassword);
		rememberPasswordLabel = new JLabel("记住密码");
		rememberPasswordLabel.setForeground(Color.gray);
		rememberPasswordLabel.setFont(new Font("宋体",Font.PLAIN,12));
		rememberPasswordLabel.setBounds(144, 268, 60, 15);
		container.add(rememberPasswordLabel);
		/*自动登录的基础设置*/
		autoLogin = new JCheckBox();
		autoLogin.setBounds(251,270, 15, 15);
		container.add(autoLogin);
		autoLoginLabel = new JLabel("自动登录");
		autoLoginLabel.setForeground(Color.gray);
		autoLoginLabel.setFont(new Font("宋体",Font.PLAIN,12));
		autoLoginLabel.setBounds(271, 268, 60, 15);
		container.add(autoLoginLabel);
		/*登录按钮的基础设置*/
		login = new JButton();
		login.setIcon(loginImageIcon);
		login.setBorderPainted(false);
		//login.setFocusPainted(false);
		login.setCursor(DEFAULT_CURSOR);
		login.setBounds(126,292,loginImageIcon.getIconWidth(),loginImageIcon.getIconHeight());
		container.add(login);
		bottomPanel = new JPanel(null);
		bottomPanel.setBackground(new Color(235,242,249));
		bottomPanel.setBounds(0, 182, WIDTH, HEIGHT - 182);
		container.add(bottomPanel);
	    /*针对登录窗格基础设置*/
		//jwindow.setUndecorated(true);//将窗体设置为无边框
		jwindow.setBounds(450, 300, WIDTH, HEIGHT);
		jwindow.setLayout(null);
		//jwindow.setAlwaysOnTop(true);
		//jwindow.setResizable(false);//将窗体设置为尺寸不可以改变
		jwindow.setVisible(true);
		
		/*添加监听器*/
		avatarStatus.addMouseListener(loginFrameListener);
		rememberPassword.addMouseListener(loginFrameListener);
		rememberPasswordLabel.addMouseListener(loginFrameListener);
		autoLogin.addMouseListener(loginFrameListener);
		autoLoginLabel.addMouseListener(loginFrameListener);
		username.addMouseListener(loginFrameListener);
		password.addMouseListener(loginFrameListener);
		keyBoard.addMouseListener(loginFrameListener);
		login.addMouseListener(loginFrameListener);
		jwindow.addMouseMotionListener(loginFrameListener);
		jwindow.addMouseListener(loginFrameListener);
		menuButtonsComponent.getMinButton().addMouseListener(loginFrameListener);
		menuButtonsComponent.getDeleteButton().addMouseListener(loginFrameListener);
	}
	/**
	 * @return the jwindow
	 */
	public JWindow getJframe() {
		return jwindow;
	}
	/**
	 * @param jwindow the jwindow to set
	 */
	public void setJframe(JWindow jwindow) {
		this.jwindow = jwindow;
	}
	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}
	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	/**
	 * @return the avatar
	 */
	public JLabel getAvatar() {
		return avatar;
	}
	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(JLabel avatar) {
		this.avatar = avatar;
	}
	/**
	 * @return the avatarStatus
	 */
	public JLabel getAvatarStatus() {
		return avatarStatus;
	}
	/**
	 * @param avatarStatus the avatarStatus to set
	 */
	public void setAvatarStatus(JLabel avatarStatus) {
		this.avatarStatus = avatarStatus;
	}
	/**
	 * @return the keyBoard
	 */
	public JLabel getKeyBoard() {
		return keyBoard;
	}
	/**
	 * @param keyBoard the keyBoard to set
	 */
	public void setKeyBoard(JLabel keyBoard) {
		this.keyBoard = keyBoard;
	}
	/**
	 * @return the register
	 */
	public JLabel getRegister() {
		return register;
	}
	/**
	 * @param register the register to set
	 */
	public void setRegister(JLabel register) {
		this.register = register;
	}
	/**
	 * @return the forgetSecret
	 */
	public JLabel getForgetSecret() {
		return forgetSecret;
	}
	/**
	 * @param forgetSecret the forgetSecret to set
	 */
	public void setForgetSecret(JLabel forgetSecret) {
		this.forgetSecret = forgetSecret;
	}
	/**
	 * @return the username
	 */
	public JTextField getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(JTextField username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public JPasswordField getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(JPasswordField password) {
		this.password = password;
	}
	/**
	 * @return the rememberPassword
	 */
	public JCheckBox getRememberPassword() {
		return rememberPassword;
	}
	/**
	 * @param rememberPassword the rememberPassword to set
	 */
	public void setRememberPassword(JCheckBox rememberPassword) {
		this.rememberPassword = rememberPassword;
	}
	/**
	 * @return the autoLogin
	 */
	public JCheckBox getAutoLogin() {
		return autoLogin;
	}
	/**
	 * @param autoLogin the autoLogin to set
	 */
	public void setAutoLogin(JCheckBox autoLogin) {
		this.autoLogin = autoLogin;
	}
	/**
	 * @return the rememberPasswordLabel
	 */
	public JLabel getRememberPasswordLabel() {
		return rememberPasswordLabel;
	}
	/**
	 * @param rememberPasswordLabel the rememberPasswordLabel to set
	 */
	public void setRememberPasswordLabel(JLabel rememberPasswordLabel) {
		this.rememberPasswordLabel = rememberPasswordLabel;
	}
	/**
	 * @return the autoLoginLabel
	 */
	public JLabel getAutoLoginLabel() {
		return autoLoginLabel;
	}
	/**
	 * @param autoLoginLabel the autoLoginLabel to set
	 */
	public void setAutoLoginLabel(JLabel autoLoginLabel) {
		this.autoLoginLabel = autoLoginLabel;
	}
	/**
	 * @return the login
	 */
	public JButton getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(JButton login) {
		this.login = login;
	}
	/**
	 * @return the menuButtonsComponent
	 */
	public MenuButtonsComponent getMenuButtonsComponent() {
		return menuButtonsComponent;
	}
	/**
	 * @return the bottomPanel
	 */
	public JPanel getBottomPanel() {
		return bottomPanel;
	}
	/**
	 * @param bottomPanel the bottomPanel to set
	 */
	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}
	/**
	 * @param menuButtonsComponent the menuButtonsComponent to set
	 */
	public void setMenuButtonsComponent(MenuButtonsComponent menuButtonsComponent) {
		this.menuButtonsComponent = menuButtonsComponent;
	}
}
