package com.vince.controller.test;

import java.awt.*;

import javax.swing.*;

/**
 * @author http://xp9802.iteye.com/
 * 2011-11-19下午04:40:38
 */
public class FullScreenDemo {

	public static void main(String[] args) {
		final JFrame jframe = new JFrame();
		JButton fullsButton = new JButton("全屏显示");
		JButton exitButton = new JButton("退出");
		exitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(1);
			}
		});
		fullsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				//通过调用GraphicsEnvironment的getDefaultScreenDevice方法获得当前的屏幕设备了
				GraphicsDevice gd = ge.getDefaultScreenDevice();
				// 全屏设置
				gd.setFullScreenWindow(jframe);
			}
		});
		jframe.add(fullsButton);
		jframe.add(exitButton);
		jframe.setLayout(new FlowLayout());
		jframe.setSize(400, 300);
		jframe.setVisible(true);
	}
}
