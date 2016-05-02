/**
 * 
 */
package com.vince.view.config;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Arrays;
import java.util.Date;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.Timer;
//import javax.swing.plaf.metal.MetalBumps;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.plaf.metal.MetalScrollButton;

import com.vince.controller.listener.RelationTabScrollPaneListener;
import com.vince.controller.util.Util;
import com.vince.view.relationerdetail.RelationerDetail;
import com.vince.view.relationtab.RelationTab;
import com.vince.view.util.MyGifPanel;
import com.vince.view.groupdetail.GroupDetail;


/**
 * @author 亮
 *
 */
public class MyScrollBarUI extends MetalScrollBarUI{

	/**
	 * 
	 */
	public Color highlightColor = new Color(255, 255, 255);
	public Color shadowColor = new Color(253, 253, 254);
	public Color thumbColor = new Color(199, 200, 201);
	public Color thumbShadow = Color.gray;
	public Color thumbHighlightColor = new Color(133, 133, 134);
	public Color trackDarkShadowColor = new Color(222, 223, 224);
	public Color trackShadowColor = new Color(222, 223, 224);
	public Color trackHighLightColor = new Color(222, 223, 224);
	public static int minThumbHeight = 37;
	public static int decreaseButtonFlag = 1;//java中已经定义
	public static int increaseButtonFlag = 5;//java中已经定义
	public RelationTab relationTab;
	public JScrollPane relationScrollPane = null;
	public int INITIAL_X_POSITION = 0;
	public int INITIAL_Y_POSITION = 10;
	public static int start = 0;//显示框的开始位置
	public static int end = 0;//显示框的结束位置
	public int clickNUm = 0;//按钮点击次数
	public int index = 0;//操作的分组名称在分组名称数组的index
	public Rectangle getThumbBounds = null;//获取滚动条的位置
	public ActionListener arrowDownButtonActionListener = null;
	public ActionListener arrowUpButtonActionListener = null;
	public ActionListener trackerActionListener = null;
	public JScrollBar myJScrollBar = null;
	public Timer myTimer = null;//由于滚动条时间与分组渲染的速度不匹配
	public int preScrollBarValue = 0;
	public static boolean myTimerDelayIsSetZero = true;
	public static MyGifPanel myGifPanel = null;
	public File gifImageFile = null;
	public static GroupDetail groupDetail = null;//显示分组详情
	public int groupMemeberDetailIndex = 0;
	public Rectangle myThumbRect = null;
	public Rectangle myTrackRect = null;
	public MyScrollBarUI(){}
	public MyScrollBarUI(RelationTab relationTab,JScrollPane relationScrollPane) {
		// TODO Auto-generated constructor stub
		this.relationTab = relationTab;
		this.relationScrollPane = relationScrollPane;
	}
	@Override
	protected void installDefaults() {
		// TODO Auto-generated method stub
		super.installDefaults();
		scrollBarWidth = 12;
		//bumps = new MetalBumps( 10, 10, thumbHighlightColor, thumbShadow, thumbColor );    
	}
	@Override
	protected JButton createDecreaseButton(int orientation) {
		// TODO Auto-generated method stub
		
		decreaseButton = new MetalScrollButton( orientation, scrollBarWidth, isFreeStanding ){
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub

				//super.paint(g);
				boolean leftToRight = this.getComponentOrientation()
						.isLeftToRight();
				boolean isEnabled = getParent().isEnabled();
				Color arrowColor = isEnabled ? new Color(101, 101, 101)
						: new Color(149, 149, 150);
				// System.out.println(MetalLookAndFeel.getControlDisabled());
				boolean isPressed = getModel().isPressed();
				int width = getWidth();
				int height = getHeight();
				int w = width;
				int h = height;
				int arrowHeight = (height + 1) / 3+1;
				int arrowWidth = height +1;
				//int xPosition = this.getBounds().x,yPosition = this.getBounds().y;
				if (isPressed) {
					g.setColor(new Color(253, 253, 254));
				} else {
					g.setColor(new Color(255, 255, 255));
				}

				g.fillRect(0, 0, width, height);
				if (getDirection() == NORTH) {
			        if ( !isFreeStanding ) {
	                    height +=1;
	                    g.translate( 0, -1 );
	                    width += 2;
	                    if ( !leftToRight ) {
	                        g.translate( -1, 0 );
	                    }
			}

		        // Draw the arrow
			        MyScrollBarUI.drawArrow(g, w, arrowWidth, arrowHeight, arrowColor,false);
		        
			if (isEnabled) {
				if(isPressed){
					Color arrowHighLightColor = new Color(100, 100, 100);
					MyScrollBarUI.drawArrow(g, w, arrowWidth, arrowHeight, arrowHighLightColor,false);
					g.setColor(new Color(125,125,126));
					//System.out.println();
					g.drawLine(0, 0, 0, height - 2);//left
					g.drawLine(1, 0, width - 2, 0);//top
					g.drawLine(1, height - 1, width - 1, height - 1);//bottom
					g.drawLine(width - 1, 0, width - 1, height - 1);//right
				}
			} else {
			    MyScrollBarUI.drawDisabledBorder(g, 0, 0, width, height+1);
			}
		        if ( !isFreeStanding ) {
	                    height -= 1;
	                    g.translate( 0, 1 );
	                    width -= 2;
	                    if ( !leftToRight ) {
	                        g.translate( 1, 0 );
	                    }
			}
		      
		    } else if (getDirection() == SOUTH) {
			        if ( !isFreeStanding ) {
					    height += 1;
			                    width += 2;
			                    if ( !leftToRight ) {
			                        g.translate( -1, 0 );
			                    }
					}

				        // Draw the arrow
				        g.setColor( arrowColor );

					int startY = (((h+1) - arrowHeight) / 2)+ arrowHeight-1;
					int startX = (w / 2);

					//	    System.out.println( "startX2 :" + startX + " startY2 :"+startY);

					for (int line = 0; line < arrowHeight; line++) {
					    g.drawLine( startX-line, startY-line, startX +line+1, startY-line);
					}

				/*	g.drawLine( 4, 5, 11, 5 );
					g.drawLine( 5, 6, 10, 6 );
					g.drawLine( 6, 7, 9, 7 );
					g.drawLine( 7, 8, 8, 8 ); */

					if (isEnabled) {
					    g.setColor( highlightColor );

					    if ( !isPressed )
					    {
						g.drawLine( 1, 0, width - 3, 0 );
						g.drawLine( 1, 0, 1, height - 3 );
					    }

					    g.drawLine( 1, height - 1, width - 1, height - 1 );
					    g.drawLine( width - 1, 0, width - 1, height - 1 );

					    g.setColor( shadowColor );
					    g.drawLine( 0, 0, 0, height - 2 );
					    g.drawLine( width - 2, 0, width - 2, height - 2 );
					    g.drawLine( 2, height - 2, width - 2, height - 2 );
					} else {
					    MyScrollBarUI.drawDisabledBorder(g, 0,-1, width, height+1);
					}

				        if ( !isFreeStanding ) {
					    height -= 1;
			                    width -= 2;
			                    if ( !leftToRight ) {
			                        g.translate( 1, 0 );
			                    }
					}
				    } else if (getDirection() == EAST) {
					if (!isFreeStanding) {
						height += 2;
						width += 1;
					}

					// Draw the arrow
					g.setColor(arrowColor);

					int startX = (((w + 1) - arrowHeight) / 2) + arrowHeight
							- 1;
					int startY = (h / 2);

					// System.out.println( "startX2 :" + startX +
					// " startY2 :"+startY);

					for (int line = 0; line < arrowHeight; line++) {
						g.drawLine(startX - line, startY - line, startX - line,
								startY + line + 1);
					}

					/*
					 * g.drawLine( 5, 4, 5, 11 ); g.drawLine( 6, 5, 6, 10 );
					 * g.drawLine( 7, 6, 7, 9 ); g.drawLine( 8, 7, 8, 8 );
					 */

					if (isEnabled) {
						g.setColor(highlightColor);

						if (!isPressed) {
							g.drawLine(0, 1, width - 3, 1);
							g.drawLine(0, 1, 0, height - 3);
						}

						g.drawLine(width - 1, 1, width - 1, height - 1);
						g.drawLine(0, height - 1, width - 1, height - 1);

						g.setColor(shadowColor);
						g.drawLine(0, 0, width - 2, 0);
						g.drawLine(width - 2, 2, width - 2, height - 2);
						g.drawLine(0, height - 2, width - 2, height - 2);
					} else {
						MyScrollBarUI.drawDisabledBorder(g, -1, 0, width + 1,
								height);
					}
					if (!isFreeStanding) {
						height -= 2;
						width -= 1;
					}
				} else if (getDirection() == WEST) {
					if (!isFreeStanding) {
						height += 2;
						width += 1;
						g.translate(-1, 0);
					}

					// Draw the arrow
					g.setColor(arrowColor);

					int startX = (((w + 1) - arrowHeight) / 2);
					int startY = (h / 2);

					for (int line = 0; line < arrowHeight; line++) {
						g.drawLine(startX + line, startY - line, startX + line,
								startY + line + 1);
					}

					/*
					 * g.drawLine( 6, 7, 6, 8 ); g.drawLine( 7, 6, 7, 9 );
					 * g.drawLine( 8, 5, 8, 10 ); g.drawLine( 9, 4, 9, 11 );
					 */

					if (isEnabled) {
						g.setColor(highlightColor);

						if (!isPressed) {
							g.drawLine(1, 1, width - 1, 1);
							g.drawLine(1, 1, 1, height - 3);
						}

						g.drawLine(1, height - 1, width - 1, height - 1);

						g.setColor(shadowColor);
						g.drawLine(0, 0, width - 1, 0);
						g.drawLine(0, 0, 0, height - 2);
						g.drawLine(2, height - 2, width - 1, height - 2);
					} else {
						MyScrollBarUI.drawDisabledBorder(g, 0, 0, width + 1,
								height);
					}

					if (!isFreeStanding) {
						height -= 2;
						width -= 1;
						g.translate(1, 0);
					}
				}
			
			}
		};
		
		return decreaseButton;
	}
	@Override
	protected JButton createIncreaseButton(int orientation) {
		// TODO Auto-generated method stub
		
		increaseButton =  new MetalScrollButton( orientation, scrollBarWidth, isFreeStanding ){
			@Override
			public void paint(Graphics g) {
				// super.paint(g);
				boolean leftToRight = this.getComponentOrientation()
						.isLeftToRight();
				boolean isEnabled = getParent().isEnabled();
				Color arrowColor = isEnabled ? new Color(101, 101, 101)
						: new Color(149, 149, 150);
				// System.out.println(MetalLookAndFeel.getControlDisabled());
				boolean isPressed = getModel().isPressed();
				int width = getWidth();
				int height = getHeight();
				int w = width;
				int h = height;
				int arrowHeight = (height + 1) / 3;
				int arrowWidth = height ;
				//int xPosition = this.getBounds().x,yPosition = this.getBounds().y;
				if (isPressed) {
					g.setColor(new Color(253, 253, 254));
				} else {
					g.setColor(new Color(255, 255, 255));
				}

				g.fillRect(0, 0, width, height);
				if (getDirection() == NORTH) {
					if (!isFreeStanding) {
						height += 1;
						g.translate(0, -1);
						width += 2;
						if (!leftToRight) {
							g.translate(-1, 0);
						}
					}

					// Draw the arrow
					g.setColor(arrowColor);
					int startY = ((h + 1) - arrowHeight) / 2;
					int startX = (w / 2);
					// System.out.println( "startX :" + startX +
					// " startY :"+startY);
					for (int line = 0; line < arrowHeight; line++) {
						g.drawLine(startX - line, startY + line, startX + line
								+ 1, startY + line);
					}
					/*
					 * g.drawLine( 7, 6, 8, 6 ); g.drawLine( 6, 7, 9, 7 );
					 * g.drawLine( 5, 8, 10, 8 ); g.drawLine( 4, 9, 11, 9 );
					 */

					if (isEnabled) {
						g.setColor(highlightColor);
						
						if (!isPressed) {
							g.drawLine(1, 1, width - 3, 1);
							g.drawLine(1, 1, 1, height - 1);
						}

						g.drawLine(width - 1, 1, width - 1, height - 1);

						g.setColor(shadowColor);
						g.drawLine(0, 0, width - 2, 0);
						g.drawLine(0, 0, 0, height - 1);
						g.drawLine(width - 2, 2, width - 2, height - 1);
					} else {
						MyScrollBarUI.drawDisabledBorder(g, 0, 0, width,
								height + 1);
					}
					if (!isFreeStanding) {
						height -= 1;
						g.translate(0, 1);
						width -= 2;
						if (!leftToRight) {
							g.translate(1, 0);
						}
					}
					//System.out.println(1);
				} else if (getDirection() == SOUTH) {
					if (!isFreeStanding) {
						height += 1;
						width += 2;
						if (!leftToRight) {
							g.translate(-1, 0);
						}
					}

					// Draw the arrow
					MyScrollBarUI.drawArrow(g, w, arrowWidth, arrowHeight, arrowColor,true);

					/*
					 * g.drawLine( 4, 5, 11, 5 ); g.drawLine( 5, 6, 10, 6 );
					 * g.drawLine( 6, 7, 9, 7 ); g.drawLine( 7, 8, 8, 8 );
					 */

					if (isEnabled) {
						if(isPressed){
							Color arrowHighLightColor = new Color(100, 100, 100);
							MyScrollBarUI.drawArrow(g, w, arrowWidth, arrowHeight, arrowHighLightColor,true);
							g.setColor(new Color(125,125,126));
							//System.out.println();
							g.drawLine(1, 0, 1, height - 2);//left
							g.drawLine(1, 0, width - 2, 0);//top
							g.drawLine(1, height - 1, width - 1, height - 1);//bottom
							g.drawLine(width - 1, 0, width - 1, height - 1);//right
						}
					} else {
						MyScrollBarUI.drawDisabledBorder(g, 0, -1, width,
								height + 1);
					}

					if (!isFreeStanding) {
						height -= 1;
						width -= 2;
						if (!leftToRight) {
							g.translate(1, 0);
						}
					}
					//System.out.println(2);
				} else if (getDirection() == EAST) {
					if (!isFreeStanding) {
						height += 2;
						width += 1;
					}

					// Draw the arrow
					g.setColor(arrowColor);

					int startX = (((w + 1) - arrowHeight) / 2) + arrowHeight
							- 1;
					int startY = (h / 2);

					// System.out.println( "startX2 :" + startX +
					// " startY2 :"+startY);

					for (int line = 0; line < arrowHeight; line++) {
						g.drawLine(startX - line, startY - line, startX - line,
								startY + line + 1);
					}

					/*
					 * g.drawLine( 5, 4, 5, 11 ); g.drawLine( 6, 5, 6, 10 );
					 * g.drawLine( 7, 6, 7, 9 ); g.drawLine( 8, 7, 8, 8 );
					 */

					if (isEnabled) {
						g.setColor(highlightColor);

						if (!isPressed) {
							g.drawLine(0, 1, width - 3, 1);
							g.drawLine(0, 1, 0, height - 3);
						}

						g.drawLine(width - 1, 1, width - 1, height - 1);
						g.drawLine(0, height - 1, width - 1, height - 1);

						g.setColor(shadowColor);
						g.drawLine(0, 0, width - 2, 0);
						g.drawLine(width - 2, 2, width - 2, height - 2);
						g.drawLine(0, height - 2, width - 2, height - 2);
					} else {
						MyScrollBarUI.drawDisabledBorder(g, -1, 0, width + 1,
								height);
					}
					if (!isFreeStanding) {
						height -= 2;
						width -= 1;
					}
				} else if (getDirection() == WEST) {
					if (!isFreeStanding) {
						height += 2;
						width += 1;
						g.translate(-1, 0);
					}

					// Draw the arrow
					g.setColor(arrowColor);

					int startX = (((w + 1) - arrowHeight) / 2);
					int startY = (h / 2);

					for (int line = 0; line < arrowHeight; line++) {
						g.drawLine(startX + line, startY - line, startX + line,
								startY + line + 1);
					}

					/*
					 * g.drawLine( 6, 7, 6, 8 ); g.drawLine( 7, 6, 7, 9 );
					 * g.drawLine( 8, 5, 8, 10 ); g.drawLine( 9, 4, 9, 11 );
					 */

					if (isEnabled) {
						g.setColor(highlightColor);

						if (!isPressed) {
							g.drawLine(1, 1, width - 1, 1);
							g.drawLine(1, 1, 1, height - 3);
						}

						g.drawLine(1, height - 1, width - 1, height - 1);

						g.setColor(shadowColor);
						g.drawLine(0, 0, width - 1, 0);
						g.drawLine(0, 0, 0, height - 2);
						g.drawLine(2, height - 2, width - 1, height - 2);
					} else {
						MyScrollBarUI.drawDisabledBorder(g, 0, 0, width + 1,
								height);
					}

					if (!isFreeStanding) {
						height -= 2;
						width -= 1;
						g.translate(1, 0);
					}
				}
			}
			
		};
		return increaseButton;
	}
	@Override
	protected void installListeners() {
		// TODO Auto-generated method stub
		//super.installListeners();
		super.installListeners();
		//((MyScrollBarListener)propertyChangeListener).handlePropertyChange( scrollbar.getClientProperty( FREE_STANDING_PROP ) );
	}
	@Override
	protected void configureScrollBarColors() {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void paintDecreaseHighlight(Graphics g) {
		super.paintDecreaseHighlight(g);
	}
	@Override
	protected void paintIncreaseHighlight(Graphics g) {
		// TODO Auto-generated method stub
		super.paintIncreaseHighlight(g);
	}
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		// TODO Auto-generated method stub
		// super.paintThumb(g, c, thumbBounds);

		if (!c.isEnabled()) {
			return;
		}

		/*if (MetalLookAndFeel.usingOcean()) {
			oceanPaintThumb(g, c, thumbBounds);
			return;
		}*/

		boolean leftToRight = c.getComponentOrientation().isLeftToRight();

		g.translate(thumbBounds.x, thumbBounds.y);

		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			if (!isFreeStanding) {
				thumbBounds.width += 2;
				if (!leftToRight) {
					g.translate(-1, 0);
				}
			}

			g.setColor(thumbColor);
			//g.fillRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1);
			g.fillRoundRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1, 15, 15);
			g.setColor(thumbShadow);
			g.drawRoundRect(0, 0, thumbBounds.width - 2, thumbBounds.height - 1, 15, 15);

			/*g.setColor(thumbHighlightColor);
			g.drawLine(1, 1, thumbBounds.width - 3, 1);
			g.drawLine(1, 1, 1, thumbBounds.height - 2);*/

			//bumps.setBumpArea(thumbBounds.width - 6, thumbBounds.height - 7);
			//bumps.paintIcon(c, g, 3, 4);

			if (!isFreeStanding) {
				thumbBounds.width -= 2;
				if (!leftToRight) {
					g.translate(1, 0);
				}
			}
		} else // HORIZONTAL
		{
			if (!isFreeStanding) {
				thumbBounds.height += 2;
			}

			g.setColor(thumbColor);
			g.fillRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

			g.setColor(thumbShadow);
			g.drawRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

			g.setColor(thumbHighlightColor);
			g.drawLine(1, 1, thumbBounds.width - 3, 1);
			g.drawLine(1, 1, 1, thumbBounds.height - 3);

			//bumps.setBumpArea(thumbBounds.width - 7, thumbBounds.height - 6);
			//bumps.paintIcon(c, g, 4, 3);

			if (!isFreeStanding) {
				thumbBounds.height -= 2;
			}
		}

		g.translate(-thumbBounds.x, -thumbBounds.y);

	}
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		// TODO Auto-generated method stub
		// super.paintTrack(g, c, trackBounds);
		
		g.translate(trackBounds.x, trackBounds.y);

		boolean leftToRight = c.getComponentOrientation().isLeftToRight();

		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			if (!isFreeStanding) {
				trackBounds.width += 2;
				if (!leftToRight) {
					g.translate(-1, 0);
				}
			}

			if (c.isEnabled()) {
				g.setColor(trackDarkShadowColor);
				g.fillRoundRect(0, 0, trackBounds.width - 2, trackBounds.height - 1, 15, 15);
				g.setColor(trackShadowColor);
				g.drawRoundRect(0, 0, trackBounds.width - 2, trackBounds.height - 1, 15, 15);
				/*g.drawLine(0, 0, 0, trackBounds.height - 1);
				g.drawLine(trackBounds.width - 2, 0, trackBounds.width - 2,
						trackBounds.height - 1);
				g.drawLine(2, trackBounds.height - 1, trackBounds.width - 1,
						trackBounds.height - 1);
				g.drawLine(2, 0, trackBounds.width - 2, 0);

				g.setColor(trackShadowColor);
				// g.setColor( Color.red);
				g.drawLine(1, 1, 1, trackBounds.height - 2);
				g.drawLine(1, 1, trackBounds.width - 3, 1);
				if (scrollbar.getValue() != scrollbar.getMaximum()) { // thumb
																		// shadow
					int y = thumbRect.y + thumbRect.height - trackBounds.y;
					g.drawLine(1, y, trackBounds.width - 1, y);
				}
				g.setColor(highlightColor);
				g.drawLine(trackBounds.width - 1, 0, trackBounds.width - 1,
						trackBounds.height - 1);*/
			} else {
				MyScrollBarUI.drawDisabledBorder(g, 0, 0, trackBounds.width,
						trackBounds.height);
			}

			if (!isFreeStanding) {
				trackBounds.width -= 2;
				if (!leftToRight) {
					g.translate(1, 0);
				}
			}
		} else // HORIZONTAL
		{
			if (!isFreeStanding) {
				trackBounds.height += 2;
			}

			if (c.isEnabled()) {
				g.setColor(trackDarkShadowColor);
				g.drawLine(0, 0, trackBounds.width - 1, 0); // top
				g.drawLine(0, 2, 0, trackBounds.height - 2); // left
				g.drawLine(0, trackBounds.height - 2, trackBounds.width - 1,
						trackBounds.height - 2); // bottom
				g.drawLine(trackBounds.width - 1, 2, trackBounds.width - 1,
						trackBounds.height - 1); // right

				g.setColor(trackShadowColor);
				// g.setColor( Color.red);
				g.drawLine(1, 1, trackBounds.width - 2, 1); // top
				g.drawLine(1, 1, 1, trackBounds.height - 3); // left
				g.drawLine(0, trackBounds.height - 1, trackBounds.width - 1,
						trackBounds.height - 1); // bottom
				if (scrollbar.getValue() != scrollbar.getMaximum()) { // thumb
																		// shadow
					int x = thumbRect.x + thumbRect.width - trackBounds.x;
					g.drawLine(x, 1, x, trackBounds.height - 1);
				}
			} else {
				MyScrollBarUI.drawDisabledBorder(g, 0, 0, trackBounds.width,
						trackBounds.height);
			}

			if (!isFreeStanding) {
				trackBounds.height -= 2;
			}
		}

		g.translate(-trackBounds.x, -trackBounds.y);

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
	/*
	 * 难度如何将鼠标滚动事件和拖动事件产生的效果转换为点击事件的效果
	 * */
	@Override
	protected ArrowButtonListener createArrowButtonListener() {
		// TODO Auto-generated method stub
		//final int start = relationScrollPane.getBounds().y;
		//System.out.println(relationScrollPane.getBounds());
		return new ArrowButtonListener(){
			
			
			public final int num = 1;
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				super.mouseClicked(e);
				MetalScrollButton btn = (MetalScrollButton)e.getSource();
				//判断点击按钮是否IncreaseButton或者是DecreaseButton
				if(start == 0 && end == 0){
					start = 0;	
					end = relationScrollPane.getBounds().height;
				}
				if(btn.getDirection() == decreaseButtonFlag){
					end = end - num;
					start = start - num;
					if(start <=0 ||end <= relationScrollPane.getBounds().height){
						start = 0;
						end = relationScrollPane.getBounds().height;
						//重新初始化，避免出界
					}
					getPreGroupDetailActions();
					
				}else if(btn.getDirection() == increaseButtonFlag){
					//clickNUm++;
					//System.out.println(clickNUm);
					if(myThumbRect == null)	myThumbRect = getThumbBounds();
					if(myTrackRect == null)	myTrackRect = getTrackBounds();
					if(myThumbRect.y + myThumbRect.height < myTrackRect.y + myTrackRect.height){
						start = start + num;end = end + num;
						RelationerDetail relationerDetail = null;
						//System.out.println("1.start = "+start+" end = "+end+" index = "+index);
						index = updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(start, end, index, relationerDetail);
					}
				}
				//System.out.println(btn.getDirection());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				//getThumbBounds = getThumbBounds();//实时获取滚动条的位置
			}
			@Override
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				//实时获取滚动条的位置
				if(start == 0 && end == 0){
					start = 0;
					end = relationScrollPane.getBounds().height;
				}
				MetalScrollButton btn = (MetalScrollButton)e.getSource();
				if(btn.getDirection() == decreaseButtonFlag){
					if(arrowUpButtonActionListener == null){
						arrowUpButtonActionListener = new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								end = end - num;
								start = start - num;
								if(start <=0 ||end <= relationScrollPane.getBounds().height){
									start = 0;
									end = relationScrollPane.getBounds().height;
									//重新初始化，避免出界
								}
								getPreGroupDetailActions();
							}
						};
						scrollTimer.addActionListener(arrowUpButtonActionListener);
					}
					
				} else if (btn.getDirection() == increaseButtonFlag) {
					if (arrowDownButtonActionListener == null) {
						arrowDownButtonActionListener = new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								// System.out.println("start = "+start+" end = "+end+" bounds = "+getThumbBounds.y);
							/*	end = end + num;
								start = start + num;
								// System.out.println(scrollbar.getModel().getValue());
								// System.out.println("end = "+end);
								RelationerDetail relationerDetail = null;
								// System.out.println("2.start = "+start+" end = "+end+" index = "+index);
								index = updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(
										start, end, index, relationerDetail);*/
								if(myThumbRect == null)	myThumbRect = getThumbBounds();
								if(myTrackRect == null)	myTrackRect = getTrackBounds();
								if(myThumbRect.y + myThumbRect.height < myTrackRect.y + myTrackRect.height){
									start = start + num;end = end + num;
									RelationerDetail relationerDetail = null;
									//System.out.println("1.start = "+start+" end = "+end+" index = "+index);
									index = updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(start, end, index, relationerDetail);
								}
							}
						};
						scrollTimer
								.addActionListener(arrowDownButtonActionListener);
					}
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				//除去对时间监听
				scrollTimer.removeActionListener(arrowDownButtonActionListener);
				//System.out.println(getThumbBounds());
				scrollTimer.removeActionListener(arrowUpButtonActionListener);
				arrowUpButtonActionListener = null;
				arrowDownButtonActionListener = null;
			}
		};
	}
	@Override
	protected TrackListener createTrackListener() {
		// TODO Auto-generated method stub
		return new TrackListener(){
			public final int moveDistance = 1;
			public int direction = +1;
			public int tempYPosition = 0;//记录上一次拖动的鼠标的y轴坐标
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				//System.out.println("刚才是点击事件");
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if(myTimer == null) myTimer = new Timer(0, null);
				int y = e.getY();
				super.mouseDragged(e);
				//System.out.println();
				if(tempYPosition != y&&!(getThumbBounds().y == getTrackBounds().y ||(getThumbBounds().y + getThumbBounds().height == getTrackBounds().y + getTrackBounds().height))){
					direction = tempYPosition > y ? -1 : +1;
					//System.out.println(tempYPosition+"   "+y+"  "+direction);
					tempYPosition = y;
				}else if(getThumbBounds().y == getTrackBounds().y ||(getThumbBounds().y + getThumbBounds().height == getTrackBounds().y + getTrackBounds().height)){
					direction = 0;//表示已经达到顶部或者底部
				}
				
				//实时获取滚动条的位置
				if(start == 0 && end == 0){
					start = 0;
					end = relationScrollPane.getBounds().height;
				}
				//System.out.println(scrollTimer.getDelay());
				trackerActionListener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(direction == 1){
							if(myThumbRect == null)	myThumbRect = getThumbBounds();
							if(myTrackRect == null)	myTrackRect = getTrackBounds();
							if(myThumbRect.y + myThumbRect.height < myTrackRect.y + myTrackRect.height){
								start = start + moveDistance;end = end + moveDistance;
								RelationerDetail relationerDetail = null;
								//System.out.println("1.start = "+start+" end = "+end+" index = "+index);
								index = updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(start, end, index, relationerDetail);
							}
							//scrollTimer.setDelay(1000);
							//System.out.println("end = "+end);
						}else if(direction == -1){
							end = end - moveDistance;
							start = start - moveDistance;
							if(start <=0 ||end <= relationScrollPane.getBounds().height){
								start = 0;
								end = relationScrollPane.getBounds().height;
								//重新初始化，避免出界
							}
							getPreGroupDetailActions();
						}
					}
				};
				//System.out.println(scrollTimer.getActionListeners().length);
				/*for(int i=0;i<scrollTimer.getActionListeners().length;i++){
					if(scrollTimer.getActionListeners()[i] != scrollListener){
						scrollTimer.removeActionListener(scrollTimer.getActionListeners()[i]);
					}
				}*/
				/*System.out.println(scrollTimer.getActionListeners().length);*/
				//scrollTimer.addActionListener(trackerActionListener);
				/*if(!scrollTimer.isRunning()){
					scrollTimer.start();
					//scrollTimer.setDelay(0);
				}*/
				//去除多余的ActionListener，保证只有一个ActionListener处于工作状态
				for(int i=0;i<myTimer.getActionListeners().length;i++){
					myTimer.removeActionListener(myTimer.getActionListeners()[i]);
				}
				myTimer.addActionListener(trackerActionListener);
				//System.out.println(scrollTimer.getDelay());
				//System.out.println("myTimerDelayIsSetZero = "+myTimerDelayIsSetZero);
				if(myTimerDelayIsSetZero)	myTimer.setDelay(0);
				if(!myTimer.isRunning()){
					myTimer.start();
				}
				//记录上一次的scrollbar的value
				//preScrollBarValue = scrollbar.getModel().getValue();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				//System.out.println("刚才是Press事件");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				
				scrollTimer.stop();
				//scrollTimer.setDelay(200);
				if(myTimer != null){
					myTimer.stop();
					myTimer.removeActionListener(trackerActionListener);
				}
				//scrollTimer.removeActionListener(trackerActionListener);
				//恢复定时器默认
				//去除loading
				if(myGifPanel != null){
					relationTab.remove(myGifPanel);
					myGifPanel.getTimer().cancel();
				}
				relationTab.repaint();
				myGifPanel = null;
			}
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
				super.mouseWheelMoved(e);
				//System.out.println("hello world!!!!");
			}
			
		};
	}
	@Override
	protected ModelListener createModelListener() {
		// TODO Auto-generated method stub
		return super.createModelListener();
	}
	@Override
	protected PropertyChangeListener createPropertyChangeListener() {
		// TODO Auto-generated method stub
		return super.createPropertyChangeListener();
	}
	@Override
	protected ScrollListener createScrollListener() {
		// TODO Auto-generated method stub
		return super.createScrollListener();
	}
	/*public class MyScrollBarListener extends BasicScrollBarUI.PropertyChangeHandler{

        public void propertyChange(PropertyChangeEvent e)
	{
	    String name = e.getPropertyName();
	    System.out.println(name);
	    if ( name.equals( FREE_STANDING_PROP ) )
	    {
	        handlePropertyChange( e.getNewValue() );
	    }
	    else {
	        super.propertyChange( e );
	    }
	}

        public void handlePropertyChange( Object newValue )
	{
	    if ( newValue != null )
	    {
	        boolean temp = ((Boolean)newValue).booleanValue();
		boolean becameFlush = temp == false && isFreeStanding == true;
		boolean becameNormal = temp == true && isFreeStanding == false;
		
		isFreeStanding = temp;

		if ( becameFlush ) {
		    toFlush();
		}
		else if ( becameNormal ) {
		    toFreeStanding();
		}
	    }
	    else
	    {

	        if ( !isFreeStanding ) {
		    isFreeStanding = true;
		    toFreeStanding();
		}
		
		// This commented-out block is used for testing flush scrollbars.

	        if ( isFreeStanding ) {
		    isFreeStanding = false;
		    toFlush();
		}

	    }
	    
	    if ( increaseButton != null )
	    {
	        increaseButton.setFreeStanding( isFreeStanding );
	    }
	    if ( decreaseButton != null )
	    {
	        decreaseButton.setFreeStanding( isFreeStanding );
	    }	    
	}

        protected void toFlush() {
	    scrollBarWidth -= 2;
        }

        protected void toFreeStanding() {
	    scrollBarWidth += 2;
        }
    
	}*/
	static void drawDisabledBorder(Graphics g, int x, int y, int w, int h) {
        g.translate( x, y);
        g.setColor( MetalLookAndFeel.getControlShadow() );
        g.drawRect( 0, 0, w-1, h-1 );
        g.translate(-x, -y);
    }
	static void drawArrow(Graphics g,int w,int h,int arrowHeight,Color arrowColor,boolean arrowIsDown){
		g.setColor(arrowColor);
		//System.out.println(w+"   "+h+"   "+arrowHeight+"  "+arrowIsDown);
		int startY = arrowIsDown ? (((h + 1) - arrowHeight) / 2) + arrowHeight - 1 :(int) (h - arrowHeight) / 2;
		int startX = (w / 2) - 1;
		for (int line = 0; line < arrowHeight; line++) {
			if(arrowIsDown)	g.drawLine(startX - line, startY - line, startX + line+ 1, startY - line);
			else {
				g.drawLine( startX-line, startY+line, startX +line+1, startY+line);
				//System.out.println("(x1,y1)"+" ("+(startX-line)+","+(startY+line)+") "+"(x2,y2)"+" ("+(startX + line+ 1)+","+(startY - line)+")");
			}
		}
	}
	public void getPreGroupDetailActions(){
		int capacity = relationTab.sortByGroupDataLabel.size();
		int preIndex = getNewIndex(index,capacity);
		
		if(preIndex < capacity -1){
			Rectangle r2 = relationTab.sortByGroupNameLabel.get(preIndex).getBounds();
			Rectangle r3 = relationTab.sortByGroupNameLabel.get(preIndex + 1).getBounds();
			updateGroupDetail(r2,r3,preIndex,capacity);
		}
	}
	public void updateGroupDetail(Rectangle r2,Rectangle r3,int index,int capacity){
		//TODO 
		//System.out.println("start = "+start+" min = "+(r2.height + r2.y)+" max = "+(r3.height + r3.y)+" index = "+index);
		//TODO index值会改变(只要分组名称的成员被列出),所以不能根据index来重现分组详情
		Rectangle groupDetailRect = null;
		//System.out.println(start+" "+index);
		if(start >= r2.height + r2.y && start < r3.height + r3.y){
			//System.out.println("...............");
//			System.out.println(groupDetail == null);
			if(groupDetail == null){
				RelationTabScrollPaneListener listener = new RelationTabScrollPaneListener(relationTab);
				groupDetail = new GroupDetail(new ImageIcon("src\\images\\downCaretHover.png"), relationTab.sortByGroupName.get(index), relationTab.sortByGroupData.get(index),index);
				
				groupDetail.addMouseListener(listener);
				listener = null;
			}
			//实现滚动条到达底部,分组详情不会往下移动
			//Rectangle thumbRect = getThumbBounds();
			//Rectangle trackRect = getTrackBounds();
			//if(thumbRect.y + thumbRect.height < trackRect.y + trackRect.height){
				groupDetailRect = groupDetail.getBounds();
				//消除上一个分组详情跑到当前分组
				if(start + groupDetailRect.getSize().height <= r3.y){
					groupDetail.setBounds(-1, start, groupDetail.getSize().width, groupDetail.getSize().height);
					relationTab.add(groupDetail,0);
				}
				//if(groupDetailRect.height + groupDetailRect.y <= relationTab.sortByGroupNameLabel.get(this.index).getBounds().y){
					
				//}
		//}
			//实现将分组详情(分组名称/分组数据)放置在相应的组员上面
			
			relationTab.repaint();
		}else{
			//实现消除不需要的分组的详情
			if(start < r2.height + r2.y || start == r3.height + r3.y){
				//TODO groupDetail == null
				if(groupDetail != null){
					
					relationTab.remove(groupDetail);
					relationTab.repaint();
				}
			}
			groupDetail = null;
		}
	}
	public int getOpenGroupIndex(int size){
		int index = 0;
		for(int i = 0;i < size;i++){
			if(RelationTabScrollPaneListener.buttonStatus[i]){
				index = i;
				break;
			}
		}
		return index;
	}
	public int getNewIndex(int index,int size){
		int newIndex = 0;
		Rectangle r2 = null,r3 = null;
		//分组未达到条件index默认从0开始,导致后面有分组处于打开状态也查找不到
		for (int i = 0; i < size - 1 && i <= index; i++) {
			r2 = relationTab.sortByGroupNameLabel.get(i).getBounds();
			r3 = relationTab.sortByGroupNameLabel.get(i+1).getBounds();
			if(start >= r2.height + r2.y && start <= r3.height + r3.y){
				newIndex = i;
				break;
			}
			r2 = null;r3 = null;
		}
		if(newIndex == 0) newIndex = getOpenGroupIndex(size);
		return newIndex;
	}
	public int updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(int start,int end,int index,RelationerDetail relationerDetail){
		//设计缺陷(分组中分组成员全部出来后,新生成的分组详情不会随着滚动条下移而下移,判断条件视情况而定,因此条件有点多)
		int newIndex = 0,groupItemNum = relationTab.sortByGroupNameLabel.size();
		//重新搜索相应的index,避免出现只要分组成员被打开,index值会发生改变,造成不必要的影响
		newIndex = getNewIndex(index,groupItemNum);
		//System.out.println(newIndex+" index = "+index+" "+myTimerDelayIsSetZero);
		//if(newIndex != index) newIndex = index;
		Rectangle r2 = relationTab.sortByGroupNameLabel.get(newIndex).getBounds();
		Rectangle r3 = relationTab.sortByGroupNameLabel.get(newIndex + 1).getBounds();
		//实现分组成员不是很多同样出现分组详情
		if(!myTimerDelayIsSetZero || !RelationTabScrollPaneListener.groupMemberNumIsBig[index]){
			//System.out.println("2: "+index+"  "+newIndex);
			updateGroupDetail(r2, r3, newIndex,groupItemNum);
		}
		for(String str : relationTab.sortByGroupData){
			//System.out.print("index = "+index);
			if(RelationTabScrollPaneListener.buttonStatus[index] && RelationTabScrollPaneListener.groupMemberNumIsBig[index]){
				//由于分组名称位于最下面，所以有可能realOpenGroupNum为0
				int realOpenGroupNum = RelationTabScrollPaneListener.groupNameReallyOpenNum[index][0];
				//System.out.println("index = "+index+" realOpen = "+realOpenGroupNum);
				//System.out.println(realOpenGroupNum);
				Rectangle rect = realOpenGroupNum == 0 ? relationTab.sortByGroupNameLabel.get(index).getBounds(): relationTab.groupDetailByGroupName.get(index).get(realOpenGroupNum - 1).getBounds();
				int height = realOpenGroupNum == 0 ?rect.y + rect.height+ RelationTabScrollPaneListener.MARGIN_TOP: rect.y + rect.height + 4;//
				//TODO System.out.println("realOpenGroupNum = "+realOpenGroupNum+" end = "+end+" height = "+height);//分组组员详情不出来，请调试这里
				//System.out.println("extent = "+scrollbar.getModel().getExtent()+" value = "+scrollbar.getModel().getValue()+" position="+(scrollbar.getModel().getExtent()+scrollbar.getModel().getValue())+" end = "+end+" height = "+height);
				//如果刷新慢的话，可以用loading代替
				//TODO System.out.println("position="+(scrollbar.getModel().getExtent()+scrollbar.getModel().getValue())+" end = "+end+" height = "+height);
				int loadingYPosition = scrollbar.getModel().getExtent()+scrollbar.getModel().getValue();
				if(gifImageFile == null) gifImageFile = new File("src\\images\\loading0501.gif");
				if(myGifPanel == null) myGifPanel = new MyGifPanel(gifImageFile, 1);
				
				if(loadingYPosition > height){
					myGifPanel.setBounds(0, height, relationTab.getWidth(), loadingYPosition - height);
					relationTab.add(myGifPanel);
					relationTab.repaint();
				}else{
					
					relationTab.remove(myGifPanel);
					myGifPanel.getTimer().cancel();
					relationTab.repaint();
					myGifPanel = null;
				}
				//g.drawImage(Toolkit.getDefaultToolkit().getImage("src\\images\\loading0501.gif"), 200, loadingYPosition, null, null);
				//relationTab.repaint();
				//实现分组消失后，在成员列表重新显示分组信息
				if(index < groupItemNum - 1){
					//System.out.println("1: "+index+"  "+newIndex);
					if(index != newIndex){
						//相邻的分组且分组都打开
						
						if(index - newIndex == 1 && RelationTabScrollPaneListener.buttonStatus[index]&&RelationTabScrollPaneListener.buttonStatus[newIndex]){
							updateGroupDetail(r2, r3, newIndex,groupItemNum);
						}else{
							 Rectangle r4 = relationTab.sortByGroupNameLabel.get(index).getBounds();
							 Rectangle r5 = relationTab.sortByGroupNameLabel.get(index+1).getBounds();
							 updateGroupDetail(r4, r5, index,groupItemNum);
							 r4 = null;r5 = null;
						}
						
					}else{
						updateGroupDetail(r2, r3, newIndex,groupItemNum);
					}
				}
				//
				//if(index < groupItemNum && start >= .y + .height)
				if(end == height){
					//System.out.println("realOpenGroupNum = "+realOpenGroupNum+" end = "+end+" height = "+height);
					RelationTabScrollPaneListener.groupNameReallyOpenNum[index][0] = ++realOpenGroupNum;
					//System.out.println(RelationTabScrollPaneListener.groupNameReallyOpenNum[index][0]);
					//TODO 判断realOpenGroupNum >= 1
					//System.out.println(relationTab.groupDetailByGroupName.get(index).size()+"   "+(realOpenGroupNum - 1));
					//relationerDetail = new RelationerDetail(new ImageIcon("src\\images\\2596845794.jpg"),"Gala","秉喆"+realOpenGroupNum,"风 ——作者刘秉喆，谁也没有看见过风，不用说我和你了。但是树叶飘落的时候，我们知道风在那里了。谁也没有看见过风，不用说我和你了。但是禾苗弯腰的时候，我们知道风正走过了 ，谁也没有看见过风，不用说我和你了。但是荷叶跳舞的时候，我们知道风来游玩了。 ","2596845794",1,3,true,false,false);
					//rect = realOpenGroupNum == 1 ? : relationTab.groupDetailByGroupName.get(index).get(realOpenGroupNum - 2).getBounds();
					if(realOpenGroupNum == 1){
						relationerDetail.setBounds(0, rect.y + rect.height + RelationTabScrollPaneListener.MARGIN_TOP, relationerDetail.getBounds().width, relationerDetail.getBounds().height);
					}else{
						relationerDetail.setBounds(rect.x, rect.y + rect.height, rect.width, rect.height);
					}
					relationTab.add(relationerDetail);
					//relationTab.validate();
					relationTab.repaint();
					//将现在的组员详情加入其中
					relationTab.groupDetailByGroupName.get(index).add(relationerDetail);
					//点击次数重新计数
					//clickNUm = 0;
					//System.out.println(str);
					int numMax = Util.numStrToIntArrBySlash(relationTab.sortByGroupData.get(index))[1];
					if(realOpenGroupNum == numMax){
						//减慢myTimer定时器的速度
						//myTimer.setDelay(0);
						//改变myTimerDelayIsSetZero状态
						myTimerDelayIsSetZero = false;
						if(myTimer != null)
						myTimer.setDelay(6);
						RelationTabScrollPaneListener.groupMemberNumIsBig[index] = false;
						//去除loading
						if(myGifPanel != null){
							relationTab.remove(myGifPanel);
							myGifPanel.getTimer().cancel();
							relationTab.repaint();
						}
						myGifPanel = null;
					}
					//下一个组员应该出来
				}
				break;//跳出循环，否则下面分组有打开会有影响现在分组的realOpenGroupNum
			}
			index++;
			//避免溢出
			if(index == relationTab.sortByGroupData.size()){
				index = 0;
			}
		}
		r2 = null;r3 = null;
		return index;
	}
}
