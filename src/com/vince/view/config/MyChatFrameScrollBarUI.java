/**
 * @author 流浪大法师
 * @time 2016-4-24 上午10:27:46
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.plaf.metal.MetalScrollButton;

import com.vince.controller.listener.RelationTabScrollPaneListener;
import com.vince.controller.util.Util;
import com.vince.view.groupdetail.GroupDetail;
import com.vince.view.relationerdetail.RelationerDetail;
import com.vince.view.relationtab.RelationTab;
import com.vince.view.util.MyGifPanel;

public class MyChatFrameScrollBarUI extends MetalScrollBarUI{
	public Color thumbColor = new Color(184, 182, 176);
	public Color thumbShadow = new Color(110,109,105);
	public Color thumbHighlightColor = new Color(133, 133, 134);
	public static int minThumbHeight = 10;
	private int width = 12;
	private int height = 12;
	protected void installDefaults() {
		// TODO Auto-generated method stub
		super.installDefaults();
		scrollBarWidth = 12;
		//bumps = new MetalBumps( 10, 10, thumbHighlightColor, thumbShadow, thumbColor );    
	}
	@Override
    protected JButton createDecreaseButton(int orientation) {
		ImageIcon iconRollover = Util.resizeImage(Util.getImage("src\\images\\decreaseNoPress.png", true),width,height,null,null);
		ImageIcon iconDefault = Util.resizeImage(Util.getImage("src\\images\\transparent.png", true),width,height,null,null);
		ImageIcon iconPressed = Util.resizeImage(Util.getImage("src\\images\\decreaseOnHover.png", true),width,height,null,null);
		JButton button = Util.setButtonThreeStatus(iconRollover, iconDefault, iconPressed, null, null,width,height);
        button.setVerticalAlignment(JButton.TOP);
		return button;//return Util.createButton("src\\images\\decreaseOnHover.png", null, 12);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
		ImageIcon iconRollover = Util.resizeImage(Util.getImage("src\\images\\increaseNoPress.png", true),width,height,null,null);
		ImageIcon iconDefault = Util.resizeImage(Util.getImage("src\\images\\transparent.png", true),width,height,null,null);
		ImageIcon iconPressed = Util.resizeImage(Util.getImage("src\\images\\increaseOnHover.png", true),width,height,null,null);
		JButton button = Util.setButtonThreeStatus(iconRollover, iconDefault, iconPressed, null, null,width,height);
		button.setVerticalAlignment(JButton.TOP);
		return button;
    }

    @Override
    protected void paintDecreaseHighlight(Graphics g){
	    Insets insets = scrollbar.getInsets();
	    Rectangle thumbR = getThumbBounds();
	    g.setColor(thumbColor);
	    if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
	        int x = insets.left+decrButton.getWidth()/2-2;
	        int y = decrButton.getY() + decrButton.getHeight();
	        int w = 4;
	        int h = thumbR.y - y;
	        g.fillRect(x, y, w, h);
	    }else{
	        int x, w;
	        if(scrollbar.getComponentOrientation().isLeftToRight()){
	        	x = decrButton.getX() + decrButton.getWidth();
	        	w = thumbR.x - x;
	        }else{
	        	x = thumbR.x + thumbR.width;
	        	w = decrButton.getX() - x;
	        }
	        int y = insets.top;
	        int h = scrollbar.getHeight() - (insets.top + insets.bottom);
	        g.fillRect(x, y, w, h);
	    }
    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {
            Insets insets = scrollbar.getInsets();
            Rectangle thumbR = getThumbBounds();
            g.setColor(thumbColor);
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                int x = insets.left+decrButton.getWidth()/2-2;
                int y = thumbR.y;
                int w = 4;
                int h = incrButton.getY() - y;
                g.fillRect(x, y, w, h);
            }
        }


    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)  
    {
        g.setColor(thumbColor);
        g.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height,15,15);
        //paintDecreaseHighlight(g);
        //paintIncreaseHighlight(g);

    }

  @Override
  protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds){
      if(thumbBounds.isEmpty()||!scrollbar.isEnabled())return;
      boolean leftToRight = c.getComponentOrientation().isLeftToRight();
      g.translate(thumbBounds.x, thumbBounds.y);
      if(scrollbar.getOrientation() == JScrollBar.VERTICAL){
		if(!isFreeStanding){
			thumbBounds.width += 2;
			if(!leftToRight){
				g.translate(-1, 0);
			}
		}
	    g.setColor(thumbShadow);
		g.drawRoundRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1, 15, 15);
		if(!isFreeStanding){
			thumbBounds.width -= 2;
			if(!leftToRight){
				g.translate(1, 0);
			}
		}
      }else{// HORIZONTAL
		if(!isFreeStanding){
			thumbBounds.height += 2;
		}
		g.setColor(thumbShadow);
		g.drawRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 2);
		g.setColor(thumbHighlightColor);
		g.drawLine(1, 1, thumbBounds.width - 3, 1);
		g.drawLine(1, 1, 1, thumbBounds.height - 3);
		if(!isFreeStanding){
			thumbBounds.height -= 2;
		}
      }
      g.translate(-thumbBounds.x, -thumbBounds.y);
  }
	@Override
	protected void configureScrollBarColors() {
		// TODO Auto-generated method stub
		super.configureScrollBarColors();
	}
	@Override
	protected void setThumbBounds(int x, int y, int width, int height) {
		/* If the thumbs bounds haven't changed, we're done.
		 */
		if ((thumbRect.x == x) && 
		    (thumbRect.y == y) && 
		    (thumbRect.width == width) && 
		    (thumbRect.height == height)) {
		    return;
		}

		/* Update thumbRect, and repaint the union of x,y,w,h and 
		 * the old thumbRect.
		 */
		int minX = Math.min(x, thumbRect.x);
		int minY = Math.min(y, thumbRect.y);
		int maxX = Math.max(x + width, thumbRect.x + thumbRect.width);
		int maxY = Math.max(y + height, thumbRect.y + thumbRect.height);
		if(height <= minThumbHeight) height = minThumbHeight;
		thumbRect.setBounds(x, y, width, height);
		scrollbar.repaint(minX, minY, (maxX - minX)+1, (maxY - minY)+1);
	}

}
