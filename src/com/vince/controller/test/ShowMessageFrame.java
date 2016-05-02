/**
 * 
 */
package com.vince.controller.test;

import javax.swing.JFrame;

/**
 * @author 流浪大法师
 * @time 2015-5-7 下午9:31:44
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.vince.controller.listener.ShowMessageFrameListener;
import com.vince.controller.util.Util;

public class ShowMessageFrame extends JFrame {
    private JLabel text;
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension screenSize = tk.getScreenSize();
    private int screenHeight = screenSize.height;
    private int screenWidth = screenSize.width;
    private String str = null;
    private static int MARGIN_RIGHT = 18;
    private int width = 0;
    private int height = 0;
    
    public ShowMessageFrame(String str) {
        this(str, 208, 125);
    }
    public ShowMessageFrame(String str,int width,int height){
    	this.str = str;
        this.width = width;
        this.height = height;
        new Thread(new Runnable() {
            @Override
            public void run() {
                initGUI();
            }
        }).start();
    }
    private void initGUI() {
        try {
            setUndecorated(true);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            text = new JLabel("<html>" + str + "</html>", JLabel.CENTER);
            getContentPane().add(text, BorderLayout.CENTER);
            text.setBackground(new Color(255, 251, 240));
            setBounds(screenWidth - width - MARGIN_RIGHT, screenHeight - height- Util.getTaskHeight(), width, height);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		ShowMessageFrame showMessageFrame = new ShowMessageFrame("hello world!!!");
		ShowMessageFrameListener showMessageFrameListener = new ShowMessageFrameListener(showMessageFrame);
		showMessageFrame = null;
		showMessageFrameListener = null;
    }
}
