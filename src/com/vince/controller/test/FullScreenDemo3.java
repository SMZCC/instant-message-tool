package com.vince.controller.test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author http://xp9802.iteye.com/
 * 2011-11-19下午04:39:38
 */
public class FullScreenDemo3 {

	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(1);
			}
		});
		jframe.add(exitButton);
		jframe.setLayout(new FlowLayout());
		/**
		 * true无边框 全屏显示
		 * false有边框 全屏显示
		 */
		jframe.setUndecorated(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle bounds = new Rectangle(screenSize);
		jframe.setBounds(bounds);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
	}
}
