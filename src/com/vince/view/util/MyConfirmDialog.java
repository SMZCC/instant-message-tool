/**
 * 
 */
package com.vince.view.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * @author 流浪大法师
 * @time 2016-3-21 下午4:25:23
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
public class MyConfirmDialog extends JDialog {
	private JButton confirm = null;
	private Font defaultFont = new Font("微软雅黑",Font.BOLD + Font.PLAIN,16);
	private JLabel messageLabel = null;
	private FontMetrics fontMetrics = null;
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private int INITIAL_WIDTH = 262;
	private int INITIAL_HEIGHT = 92;
	private Rectangle rect = null;
	private boolean isDispose = false;
	public MyConfirmDialog(JWindow jWindow, String message, String title) {
		// TODO Auto-generated constructor stub
		setResizable(false);
		setUndecorated(true);
		setLayout(null);
		setAlwaysOnTop(true);
		//实现提示信息
		messageLabel = new JLabel(message);
		messageLabel.setFont(defaultFont);
		fontMetrics = messageLabel.getFontMetrics(defaultFont);
		width = fontMetrics.stringWidth(messageLabel.getText());
		height = fontMetrics.getHeight();
		x = (INITIAL_WIDTH - width) / 2;
		y = 25;
		messageLabel.setBounds(x > 0 ? x : 0, y,width,height);
		if(x <= 0){
			INITIAL_WIDTH = width;
		}
		//实现确定按钮
		confirm = new JButton("确定");
		confirm.setFont(defaultFont);
		confirm.setFocusPainted(false);confirm.setBorderPainted(false);//confirm.setContentAreaFilled(false);
		fontMetrics = confirm.getFontMetrics(defaultFont);
		//重新配置
		width = fontMetrics.stringWidth(confirm.getText())+40;
		height = fontMetrics.getHeight();
		x = (INITIAL_WIDTH - width) / 2;
		y = 50;
		confirm.setBounds(x > 0 ? x : 0, y, width, height);
		add(confirm);add(messageLabel);
		rect = jWindow.getBounds();
		width = getMaxNumber(INITIAL_WIDTH,messageLabel.getSize().width,confirm.getSize().width);
		height = getMaxNumber(INITIAL_HEIGHT,messageLabel.getSize().height,confirm.getSize().height);
		x = rect.x + (rect.width - width)/2;y = rect.y + (rect.height - height)/2;
		setBounds(x,y,width,height);
		/*注册监听器*/
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isDispose = true;
				dispose();
			}
		});
	}
	/**
	 * @return the isDispose
	 */
	public boolean getDispose() {
		return isDispose;
	}
	/**
	 * @param isDispose the isDispose to set
	 */
	public void setDispose(boolean isDispose) {
		this.isDispose = isDispose;
	}
	public int getMaxNumber(int... intArray){
		Arrays.sort(intArray);
		return intArray[intArray.length - 1];
	}
}
