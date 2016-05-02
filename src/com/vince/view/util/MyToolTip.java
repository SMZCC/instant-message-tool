package com.vince.view.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JToolTip;

public class MyToolTip extends JToolTip {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6737508545760185544L;
	private String tips = null;
	/**
	 * 默认无参构造方法
	 */
	public MyToolTip() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * 有参构造函数
	 * @param tips
	 * */
	public MyToolTip(String tips){
		this.tips = tips;
	}
	/**
	 * 有参构造函数
	 * @param width
	 * @param height
	 * @param tips
	 * */
	public MyToolTip(int width,int height,String tips){
		super();
		this.tips = tips;
		setPreferredSize(new Dimension(width, height));
	}
	@Override
	protected void paintBorder(Graphics g) {
		// TODO Auto-generated method stub
		/*super.paintBorder(g);*/
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.drawRect(0, 0, getBounds().width, getBounds().height);
		g2.fillRect(0, 0, getBounds().width, getBounds().height);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
