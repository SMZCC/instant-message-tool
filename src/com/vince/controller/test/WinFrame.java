package com.vince.controller.test;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;


   public class WinFrame extends JFrame {

	public WinFrame(){
		this.setName("Window 窗口状态");
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowStateListener(new WindowStateListener () {

			public void windowStateChanged(WindowEvent state) {
				
				if(state.getNewState() == 1 || state.getNewState() == 7) {
					System.out.println("窗口最小化");
				}else if(state.getNewState() == 0) {
					System.out.println("窗口恢复到初始状态");
				}else if(state.getNewState() == 6) {
					System.out.println("窗口最大化");
				}
			}
		});
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new WinFrame();
	}
   }
