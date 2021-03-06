package com.vince.controller.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.*;

public class JTabbedPane2 implements ActionListener, ChangeListener {
	int index = 0;
	int newNumber = 1;
	JTabbedPane tabbedPane = null;

	public JTabbedPane2() {
		JFrame f = new JFrame("JTabbedPane2");
		Container contentPane = f.getContentPane();
		contentPane.setPreferredSize(new Dimension(400, 200));
		JLabel label1 = new JLabel(new ImageIcon(".\\icons\\flower.jpg"));
		JPanel panel1 = new JPanel();
		panel1.add(label1);

		JLabel label2 = new JLabel("Label 2", JLabel.CENTER);
		label2.setBackground(Color.pink);
		label2.setOpaque(true);
		JPanel panel2 = new JPanel();
		panel2.add(label2);

		JLabel label3 = new JLabel("Label 3", JLabel.CENTER);
		label3.setBackground(Color.yellow);
		label3.setOpaque(true);
		JPanel panel3 = new JPanel();
		panel3.add(label3);

		tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.TOP);// 设置标签置放位置。

		tabbedPane.addChangeListener(this);
		tabbedPane.addTab("Picture", null, panel1, "图案");
		tabbedPane.addTab("Label 2", panel2);
		tabbedPane.addTab("Label 3", null, panel3, "label");
		tabbedPane.setEnabledAt(2, false);// 设Label 3标签为Disable状态
		JButton b = new JButton("新增标签");
		b.addActionListener(this);
		contentPane.add(b, BorderLayout.SOUTH);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		f.pack();
		f.setVisible(true);
		/*f.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void stateChanged(ChangeEvent e){
		//System.out.println(e.getSource());
		System.out.println(tabbedPane.getSelectedIndex()+" index = "+index);
		if (index!=tabbedPane.getSelectedIndex()){
			tabbedPane.setEnabledAt(index+1,true);
			System.out.println("此内容执行啦....");
		}
		index=tabbedPane.getSelectedIndex();
}

	public void actionPerformed(ActionEvent e) {
		JPanel pane1 = new JPanel();
		JLabel label4 = new JLabel("new label" + newNumber, JLabel.CENTER);
		label4.setOpaque(true);
		pane1.add(label4);
		tabbedPane.addTab("new " + newNumber, pane1);
		tabbedPane.setEnabledAt(newNumber + 2, false);
		newNumber++;
		tabbedPane.validate();
	}

	public static void main(String[] args) {
		new JTabbedPane2();
	}
}