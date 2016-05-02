/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vince.view.util;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author lgh
 */
public class MyBasicSplitPaneDivider extends BasicSplitPaneDivider implements MouseMotionListener {
	protected static final int ONE_TOUCH_SIZE = 2;
    public MyBasicSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
    }
    public void setBorder(Border b) {
    }
    public void paint(Graphics g) {
        g.setColor(new Color(236,234,225));
       
        g.fillRect(0, 0, getSize().width, getSize().height);
        super.paint(g);
    }
    public void mouseDragged(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
}
