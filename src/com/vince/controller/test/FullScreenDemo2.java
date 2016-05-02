package com.vince.controller.test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * @author http://xp9802.iteye.com/
 * 2011-11-19下午04:32:38
 */
public class FullScreenDemo2 {
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
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setSize(d.width, d.height);
		jframe.setVisible(true);
	}
}
