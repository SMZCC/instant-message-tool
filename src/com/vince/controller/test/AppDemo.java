/**
 * @author 流浪大法师
 * @time 2016-4-22 下午8:38:27
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.test;


import javax.swing.*;
import java.awt.*;

public class AppDemo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
	            JFrame frame = new JFrame();
	            JButton eastButton = new JButton("East");
	            JButton westButton = new JButton("West");
	            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, eastButton, westButton);

	            JPanel content = new JPanel();
	            content.setLayout(new BorderLayout());
	            content.add(splitPane, BorderLayout.CENTER);

	            frame.setContentPane(content);
	            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            frame.setPreferredSize(new Dimension(500, 400));
	            frame.pack();
	            frame.setVisible(true);
			}
        }
		);

    }
}
