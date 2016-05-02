package com.vince.controller.test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @author http://xp9802.iteye.com/
 * 2011-11-19下午04:31:38
 */
public class FullScreenDemo1 {

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
		jframe.setUndecorated(false);
		jframe.getGraphicsConfiguration().getDevice()
				.setFullScreenWindow(jframe);
		jframe.setVisible(true);
	}
}
