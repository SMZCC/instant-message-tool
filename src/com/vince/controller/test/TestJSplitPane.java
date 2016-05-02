/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vince.controller.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import com.vince.view.util.MyBasicSplitPaneUI;

/**
 *
 * @author lgh
 */
public class TestJSplitPane {

    private JLabel left,  right;

    public TestJSplitPane() {
        initComponents();
    }

    private void initComponents() {
        JPanel jp = new JPanel();
        left = new JLabel("left");
        right = new JLabel("right");
        SlipPane sp1 = new SlipPane();
        sp1.setDividerSize(100);
        MyBasicSplitPaneUI ui = new MyBasicSplitPaneUI();
        sp1.setUI(ui);
        sp1.setLeftComponent(left);
        sp1.setRightComponent(right);
        initJFrame(sp1);
    }

    public void initJFrame(Component c) {
        JFrame jf = new JFrame("测试");
        jf.setSize(800, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        jf.add(c, BorderLayout.CENTER);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        TestJSplitPane test = new TestJSplitPane();
    }
}
