/**
 * @author 流浪大法师
 * @time 2016-4-14 下午5:31:26
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class ContentPanel extends JPanel {
	protected Image image = null;
	protected ImageIcon imageIcon = null;
	protected MediaTracker mt = null;
	protected Color color = null;
	private int arc = 0;
	public ContentPanel(String url,int realWidth,Color color) {
		initGenerator(url, realWidth,color);
	  }
	public ContentPanel(String url,int realWidth,Color color,int arc){
		initGenerator(url, realWidth,color);
		this.arc = arc;
	}
	private void initGenerator(String url,int realWidth,Color color){
		this.color = color;
		if(url != null && realWidth != 0){
			init(url,realWidth);
		}
	}
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if(image != null){
		    int width = image.getWidth(null);
		    int height = image.getHeight(null);
		    g.drawImage(image, 0, 0, width, height, null);	    	
	    }
	    if(color != null){
	    	g.setColor(color);
	    	if(arc == 0){
	    		g.drawRect(0, 0, getBounds().width, getBounds().height);
	    		g.fillRect(0, 0, getBounds().width, getBounds().height);
	    	}else{
	    		g.drawRoundRect(0, 0, getBounds().width, getBounds().height, arc, arc);
	    		g.fillRoundRect(0, 0, getBounds().width, getBounds().height, arc, arc);
	    	}
	    }
	  }
	  protected void init(String url,int realWidth){
		    try {
		    	//mt = new MediaTracker(this);
				imageIcon = new ImageIcon(ImageIO.read(new File(url)));
				int realHeight = (int)(imageIcon.getIconHeight() / imageIcon.getIconWidth() * realWidth);
				imageIcon.setImage(imageIcon.getImage().getScaledInstance(realWidth, realHeight, Image.SCALE_SMOOTH));
				image = imageIcon.getImage();
				//mt.addImage(image, 0);
				// mt.waitForAll();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
	}
