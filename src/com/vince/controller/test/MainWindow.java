/**
 * 
 */
package com.vince.controller.test;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author liuliang
 *
 */
public class MainWindow extends JFrame{

	/**
	 * 
	 */
	 protected JPanel panel1;
	    MainWindow(String s){
	        super(s);
	        this.setSize(800,600);
	        this.setLocation(200, 100);
	        //this.getContentPane().setBackground(Color.white);
	        
	        Image image1=this.getToolkit().createImage("src\\images\\loading0501.gif");
	        this.setVisible(true);
	        this.getContentPane().getGraphics().drawImage(image1, 200, 100, this);
	        
	    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow("图片测试");
	}

}
