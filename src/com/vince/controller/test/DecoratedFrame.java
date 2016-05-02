/**
 * 
 */
package com.vince.controller.test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 * @author 刘亮
 *
 */
public class DecoratedFrame extends JFrame { 
public DecoratedFrame() { 
   this.getContentPane().add(new JLabel("Just a test.")); 
   this.setUndecorated(true); //
   this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);// 
   this.setSize(300,150); 
} 
public static void main(String[] args) { 
   JFrame frame = new DecoratedFrame(); 
   frame.setVisible(true); 
} 
}