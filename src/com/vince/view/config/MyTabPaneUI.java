/**
 * 
 */
package com.vince.view.config;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * @author 亮
 *
 */
public class MyTabPaneUI extends BasicTabbedPaneUI {
	
	@Override
	protected void paintContentBorder(Graphics g, int tabPlacement,
			int selectedIndex) {
		// TODO Auto-generated method stub
		super.paintContentBorder(g, tabPlacement, selectedIndex);
	}
	@Override
	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		//super.paintContentBorderBottomEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	}
	@Override
	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		//super.paintContentBorderRightEdge(g, tabPlacement, selectedIndex, x, y, w, h);
	}
	//重绘Tab边框
	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		// TODO Auto-generated method stub

        g.setColor(Color.white);  

        switch (tabPlacement) {
          case LEFT:
              g.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight
              g.drawLine(x, y+2, x, y+h-3); // left highlight
              g.drawLine(x+1, y+1, x+1, y+1); // top-left highlight
              g.drawLine(x+2, y, x+w-1, y); // top highlight

              g.setColor(shadow);
              g.drawLine(x+2, y+h-2, x+w-1, y+h-2); // bottom shadow

              g.setColor(darkShadow);
              g.drawLine(x+2, y+h-1, x+w-1, y+h-1); // bottom dark shadow
              break;
          case RIGHT:
              g.drawLine(x, y, x+w-3, y); // top highlight

              g.setColor(shadow);
              g.drawLine(x, y+h-2, x+w-3, y+h-2); // bottom shadow
              g.drawLine(x+w-2, y+2, x+w-2, y+h-3); // right shadow

              g.setColor(darkShadow);
              g.drawLine(x+w-2, y+1, x+w-2, y+1); // top-right dark shadow
              g.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
              g.drawLine(x+w-1, y+2, x+w-1, y+h-3); // right dark shadow
              g.drawLine(x, y+h-1, x+w-3, y+h-1); // bottom dark shadow
              break;              
          case BOTTOM:
              g.drawLine(x, y, x, y+h-3); // left highlight
              g.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight

              g.setColor(shadow);
              g.drawLine(x+2, y+h-2, x+w-3, y+h-2); // bottom shadow
              g.drawLine(x+w-2, y, x+w-2, y+h-3); // right shadow

              g.setColor(darkShadow);
              g.drawLine(x+2, y+h-1, x+w-3, y+h-1); // bottom dark shadow
              g.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
              g.drawLine(x+w-1, y, x+w-1, y+h-3); // right dark shadow
              break;
          case TOP:
          default:           
              g.drawLine(x, y+2, x, y+h-1); // left highlight
              g.drawLine(x+1, y+1, x+1, y+1); // top-left highlight
              g.drawLine(x+2, y, x+w-3, y); // top highlight              

              g.setColor(Color.white);  
              g.drawLine(x+w-2, y+2, x+w-2, y+h-1); // right shadow

              g.setColor(Color.white); 
              g.drawLine(x+w-1, y+2, x+w-1, y+h-1); // right dark-shadow
              g.drawLine(x+w-2, y+1, x+w-2, y+1);// top-right shadow
              //选中后下边界高亮
              if(isSelected) {
            	  g.setColor(Color.gray);
            	  g.drawLine(x, y+h-2, x+w, y+h-2);
              }
        }
    
	}
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		// TODO Auto-generated method stub
        //g.setColor(!isSelected ? tabPane.getBackgroundAt(tabIndex) : Color.white);
        //System.out.println("x = "+x+" y = "+y+" w = "+w+" h = "+h);
        g.setColor(Color.white);
		switch(tabPlacement) {
          case LEFT:
              g.fillRect(x+1, y+1, w-1, h-3);
              break;
          case RIGHT:
              g.fillRect(x, y+1, w-2, h-3);
              break;
          case BOTTOM:
              g.fillRect(x+1, y, w-3, h-1);
              break;
          case TOP:
          default:
              {
            	  if(!isSelected)	g.fillRect(x-1, y-1, w, h);
            	  else g.fillRect(x, y, w, h-2);
            	  //g.fillRect(x, y, w, h-2);
              }
        }
		
	}
	@Override
	protected int calculateMaxTabHeight(int tabPlacement) {
		// TODO Auto-generated method stub
		//System.out.println(super.calculateMaxTabHeight(tabPlacement));
		return super.calculateMaxTabHeight(tabPlacement) > 40 ? 40 : 40;
	}
	@Override
	protected int calculateMaxTabWidth(int tabPlacement) {
		// TODO Auto-generated method stub
		return super.calculateMaxTabWidth(tabPlacement) > 46 ? 46 : 46;
	}
	@Override
	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		// TODO Auto-generated method stub
		//System.out.println(super.calculateTabHeight(tabPlacement, tabIndex, fontHeight));
		return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight) > 40 ? 40 : 40;
	}
	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		// TODO Auto-generated method stub
		return super.calculateTabWidth(tabPlacement, tabIndex, metrics) > 46 ? 46 : 46;
	}
	
	/*
	private static final Color SELECTED_TAB_COLOR = new Color(10, 36, 106);
	private static final int TAB_MINIMUM_SIZE = 8;

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		g.setColor(Color.GRAY);
		if (tabPlacement == BOTTOM) {
			g.drawLine(x, y + h, x + w, y + h);
		}

		// right
		g.drawLine(x + w - 1, y, x + w - 1, y + h);

		if (tabPlacement == TOP) {
			// And a white line to the left and top
			g.setColor(Color.WHITE);
			g.drawLine(x, y, x, y + h);
			g.drawLine(x, y, x + w - 2, y);
		}

		if (tabPlacement == BOTTOM && isSelected) {
			g.setColor(Color.WHITE);
			// Top
			g.drawLine(x + 1, y + 1, x + 1, y + h);
			// Right
			g.drawLine(x + w - 2, y, x + w - 2, y + h);
			// Left
			g.drawLine(x + 1, y + 1, x + w - 2, y + 1);
			// Bottom
			g.drawLine(x + 1, y + h - 1, x + w - 2, y + h - 1);
		}
	}

	protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects,
			int tabIndex, Rectangle iconRect, Rectangle textRect) {
		super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);

		Rectangle rect = rects[tabIndex];

		g.setColor(Color.black);
		// 绘制边框
		g.drawRect(rect.x + rect.width - 16, rect.y + 4, 13, 12);

		g.drawLine(rect.x + rect.width - 16, rect.y + 7, rect.x + rect.width
				- 10, rect.y + 13);
		g.drawLine(rect.x + rect.width - 10, rect.y + 7, rect.x + rect.width
				- 16, rect.y + 13);
		g.drawLine(rect.x + rect.width - 15, rect.y + 7, rect.x + rect.width
				- 9, rect.y + 13);
		g.drawLine(rect.x + rect.width - 9, rect.y + 7, rect.x + rect.width
				- 15, rect.y + 13);
	}

	*//**
	 * Give selected tab blue color with a gradient!!.
	 * 
	 * FIXME: with Plastic L&F the unselected background is too dark
	 *//*
	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Color color = UIManager.getColor("control");
		if (isSelected) {
			if (tabPlacement == TOP) {
				Graphics2D g2 = (Graphics2D) g;
				Paint storedPaint = g2.getPaint();
				g2.setPaint(new GradientPaint(x, y, SELECTED_TAB_COLOR, x + w,
						y + h, color));
				g2.fillRect(x, y, w, h);
				g2.setPaint(storedPaint);
			}
		} else {
			g.setColor(color);
			g.fillRect(x, y, w - 1, h);
		}
	}

	*//**
	 * Do not paint a focus indicator.
	 *//*
	@Override
	protected void paintFocusIndicator(Graphics arg0, int arg1,
			Rectangle[] arg2, int arg3, Rectangle arg4, Rectangle arg5,
			boolean arg6) {
		// Leave it
	}

	*//**
	 * We do not want the tab to "lift up" when it is selected.
	 *//*
	@Override
	protected void installDefaults() {
		super.installDefaults();
		tabAreaInsets = new Insets(0, 100, 0, 0);
		selectedTabPadInsets = new Insets(0, 0, 0, 0);
		contentBorderInsets = new Insets(1, 0, 0, 0);
	}

	*//**
	 * Nor do we want the label to move.
	 *//*
	@Override
	protected int getTabLabelShiftY(int tabPlacement, int tabIndex,
			boolean isSelected) {
		return 0;
	}

	*//**
	 * Increase the tab height a bit
	 *//*
	@Override
	protected int calculateTabHeight(int tabPlacement, int tabIndex,
			int fontHeight) {
		return fontHeight + 10;
	}

	@Override
	protected void layoutLabel(int arg0, FontMetrics arg1, int arg2,
			String arg3, Icon arg4, Rectangle arg5, Rectangle arg6,
			Rectangle arg7, boolean arg8) {
		super.layoutLabel(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
	}

	*//**
	 * Selected labels have a white color.
	 *//*
	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font,
			FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		if (isSelected && tabPlacement == TOP) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}
		if (title.length() > TAB_MINIMUM_SIZE) {
			title = "..."
					+ title.substring(title.length() - TAB_MINIMUM_SIZE + 3,
							title.length());
			textRect.x += 14;
		}
		// Font tabFont = new Font("微软雅黑", Font.BOLD, 11);
		// g.setFont(tabFont);
		g.drawString(title, textRect.x - 8, textRect.y + metrics.getAscent());
	}

	@Override
	protected int calculateTabWidth(int tabPlacement, int tabIndex,
			FontMetrics metrics) {
		int taille = 0;
		String title = tabPane.getTitleAt(tabIndex);

		if (title.length() > TAB_MINIMUM_SIZE) {
			taille = SwingUtilities.computeStringWidth(
					metrics,
					(title.substring(title.length() - TAB_MINIMUM_SIZE,
							title.length()))) + 3;
		} else {
			taille = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
		}
		taille = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
		return taille;
	}

	@Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		if (selectedIndex != -1 && tabPlacement == TOP) {
			g.setColor(Color.GRAY);
			g.drawLine(x, y, x + w, y);
		}
	}

	@Override
	protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		g.setColor(Color.GRAY);
		g.drawLine(x, y + h, x + w, y + h);
	}

	@Override
	protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// do nothingx, y, x, y + h);
	}

	@Override
	protected void paintContentBorderRightEdge(Graphics g, int tabPlacement,
			int selectedIndex, int x, int y, int w, int h) {
		// do nothing
	}

	@Override
	protected MouseListener createMouseListener() {
		return new CloseHandler();
	}

	class CloseHandler extends MouseHandler {
		public CloseHandler() {
			super();
		}

		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int tabIndex = -1;
			int tabCount = tabPane.getTabCount();
			for (int i = 0; i < tabCount; i++) {
				if (rects[i].contains(x, y)) {
					tabIndex = i;
					break;
				}
			}

			if (tabIndex >= 0 && !e.isPopupTrigger()) {
				Rectangle tabRect = rects[tabIndex];
				y = y - tabRect.y;
				if ((x >= tabRect.x + tabRect.width - 18)
						&& (x <= tabRect.x + tabRect.width - 8) && (y >= 5)
						&& (y <= 15)) {
					tabPane.remove(tabIndex);
				}
			}
		}
	}
*/
}
