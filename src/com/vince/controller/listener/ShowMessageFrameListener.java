/**
 * 
 */
package com.vince.controller.listener;

/**
 * @author 流浪大法师
 * @time 2015-5-8 下午8:54:01
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.vince.controller.test.ShowMessageFrame;

public class ShowMessageFrameListener extends MyAdapter {

	private ShowMessageFrame showMessageFrame = null;
	private int x = -1;
	private int y = -1;
	private boolean isDragging = false;
	/**
	 * 默认构造方法
	 */
	public ShowMessageFrameListener() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param ShowMessageFrame showMessageFrame
	 * */
	public ShowMessageFrameListener(ShowMessageFrame showMessageFrame){
		this.showMessageFrame = showMessageFrame;
		initListeners();
	}
	public void initListeners(){
		showMessageFrame.addMouseListener(this);
		showMessageFrame.addMouseMotionListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showMessageFrame){
			x = e.getX();
			y = e.getY();
			isDragging = true;
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showMessageFrame){
			if(isDragging){
				int showMessageFrameCurrentXPosition = showMessageFrame.getLocationOnScreen().x;
                int showMessageFrameCurrentYPosition = showMessageFrame.getLocationOnScreen().y;
                showMessageFrame.setLocation(showMessageFrameCurrentXPosition + e.getX() - x, showMessageFrameCurrentYPosition + e.getY() - y);
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == showMessageFrame){
			isDragging = false;
		}
	}
}
