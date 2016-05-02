/**
 * 
 */
package com.vince.view.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

/**
 * @author 流浪大法师
 * @time 2015-4-28 下午7:38:30
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
public class ExitDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7707612717523792703L;
	private JLabel exitMessageLabel = null;
	private JLabel miniMessageLabel = null;
	private JRadioButton exitRadioButton = null;
	private JRadioButton miniRadioButton = null;
	private ButtonGroup buttonGroup = null;
	private JRadioButtonMenuItem radioButtonMenuItem = null;
	private JButton confirm = null;
	private JButton cancel = null;
	private Container parent = null;
	private Container myself = null;
	private JPanel backgroundPanel = null;
	private Font defaultFont = new Font("微软雅黑",Font.BOLD + Font.PLAIN,16);
	/**
	 * 默认构造方法
	 */
	public ExitDialog(JFrame jframe,String title,ModalityType modalityType){

		//super("关闭程序");
		super(jframe, title,modalityType);
		setResizable(false);
		setUndecorated(true);
		parent = getParent();
		Rectangle rect2 = parent.getBounds();
		int height2 = 140;
		int initYPositionSpace = 140;
		int miniYPositionSpace = 30;
		setBounds(rect2.x, (rect2.height - height2)/2 + initYPositionSpace, rect2.width, height2);
		//setBounds(0, 0, 200, 283);
		//myself = getContentPane();
		backgroundPanel = new JPanel(null){
			/**
			 * 
			 */
			private static final long serialVersionUID = 8032478215980561878L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
	    		g.setColor(new Color(200,219,238));
	    		g.drawRect(0, 0, getBounds().width, getBounds().height);
	    		g.fillRect(0, 0, getBounds().width, getBounds().height);
			};
		};
		backgroundPanel.setBounds(0, 0, getBounds().width, getBounds().height);
		
		Rectangle rect = backgroundPanel.getBounds();
		buttonGroup = new ButtonGroup();
		//实现最小化
		miniRadioButton = new JRadioButton("最小化", null, true);miniRadioButton.setFont(defaultFont);
		FontMetrics fontMetrics = miniRadioButton.getFontMetrics(defaultFont);
		miniRadioButton.setBorderPainted(false);miniRadioButton.setFocusPainted(false);
		int width = fontMetrics.stringWidth(miniRadioButton.getText())+30;
		int height = fontMetrics.getHeight();
		int x = (getBounds().width - width)/2;
		int y = (getBounds().height - height)/2 - miniYPositionSpace;
		int xSpace = 30,ySpace = 10;
		miniRadioButton.setBounds(x, y, width, height);
		//实现退出程序
		exitRadioButton = new JRadioButton("直接退出程序", null, false);exitRadioButton.setFont(defaultFont);
		fontMetrics = exitRadioButton.getFontMetrics(defaultFont);
		exitRadioButton.setBounds(x, y+height, fontMetrics.stringWidth(exitRadioButton.getText())+xSpace, fontMetrics.getHeight());
		exitRadioButton.setBorderPainted(false);exitRadioButton.setFocusPainted(false);
		//实现确定按钮
		confirm = new JButton("确定");
		confirm.setFont(defaultFont);
		confirm.setFocusPainted(false);confirm.setBorderPainted(false);//confirm.setContentAreaFilled(false);
		fontMetrics = confirm.getFontMetrics(defaultFont);
		//重新配置
		width = fontMetrics.stringWidth(confirm.getText())+40;
		height = fontMetrics.getHeight();
		x = (getBounds().width - 2 * width)/2;
		y = exitRadioButton.getBounds().y + exitRadioButton.getBounds().height + ySpace;
		confirm.setBounds(x, y, width, height);
		//实现取消按钮
		cancel = new JButton("取消");
		cancel.setFont(defaultFont);
		cancel.setFocusPainted(false);cancel.setBorderPainted(false);
		cancel.setBounds(x + width + 5, y, width, height);
		//实现添加到容器中
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				buttonGroup.add(miniRadioButton);buttonGroup.add(exitRadioButton);
				backgroundPanel.add(miniRadioButton);
				backgroundPanel.add(exitRadioButton);
				backgroundPanel.add(confirm);
				backgroundPanel.add(cancel);
				add(backgroundPanel);
			}
		});
		//add(backgroundPanel);
		
	}
	/**
	 * @return the exitRadioButton
	 */
	public JRadioButton getExitRadioButton() {
		return exitRadioButton;
	}
	/**
	 * @param exitRadioButton the exitRadioButton to set
	 */
	public void setExitRadioButton(JRadioButton exitRadioButton) {
		this.exitRadioButton = exitRadioButton;
	}
	/**
	 * @return the miniRadioButton
	 */
	public JRadioButton getMiniRadioButton() {
		return miniRadioButton;
	}
	/**
	 * @param miniRadioButton the miniRadioButton to set
	 */
	public void setMiniRadioButton(JRadioButton miniRadioButton) {
		this.miniRadioButton = miniRadioButton;
	}
	/**
	 * @return the buttonGroup
	 */
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	/**
	 * @param buttonGroup the buttonGroup to set
	 */
	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}
	/**
	 * @return the confirm
	 */
	public JButton getConfirm() {
		return confirm;
	}
	/**
	 * @param confirm the confirm to set
	 */
	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}
	/**
	 * @return the cancel
	 */
	public JButton getCancel() {
		return cancel;
	}
	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
}
