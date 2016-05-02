/**
 * 
 */
package com.vince.controller.test;

/**
 * @author 流浪大法师
 * @time 2016-4-25 下午12:28:54
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Test2 {    
    public Test2() {
        JFrame frame = new JFrame();
        frame.setTitle("test");
        frame.getContentPane().setLayout(new GridLayout(1,2));
        frame.setSize(800, 600);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());

        JLabel label = new JLabel("name");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();   
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel1.add(label, gridBagConstraints);

        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        JComboBox petList = new JComboBox(petStrings);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel1.add(petList, gridBagConstraints);    

        frame.getContentPane().add(panel1);
        frame.getContentPane().add(new JPanel());       

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);      
    }

    public static void main(String[] args) {
        new Test2();
    }
}
