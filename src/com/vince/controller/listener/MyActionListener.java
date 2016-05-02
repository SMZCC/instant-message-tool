/**
 * @author liuliang
 *
 */
package com.vince.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import com.vince.view.config.MyScrollBarUI;
import com.vince.view.relationerdetail.RelationerDetail;

public class MyActionListener implements ActionListener{

	private MyActionListener(){}
	private MyActionListener(int direction,int moveDistance,int index,int start,int end,JScrollPane relationScrollPane){
		this.direction = direction;
		this.moveDistance = moveDistance;
		this.start = start;
		this.end = end;
		this.relationScrollPane = relationScrollPane;
		this.index = index;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(direction == 1){
			end = end + moveDistance;
			start = start + moveDistance;
			//System.out.println("end = "+end);
			MyScrollBarUI.end = end;MyScrollBarUI.start = start;
			RelationerDetail relationerDetail = null;
			//MyScrollBarUI.index = MyScrollBarUI.updateGroupMemberByMousePressOrMouseClickOrMouseDragOrMouseMotion(start, end, index, relationerDetail);
		}else if(direction == -1){
			end = end - moveDistance;
			start = start - moveDistance;
			
			if(start <=0 ||end <= relationScrollPane.getBounds().height){
				start = 0;
				end = relationScrollPane.getBounds().height;
				//重新初始化，避免出界
			}
			MyScrollBarUI.end = end;MyScrollBarUI.start = start;
		}
	
	}
	public static MyActionListener getMyActionListenerInstance(int direction,int moveDistance,int index,int start,int end,JScrollPane relationScrollPane){
		if(myActionListenerInstance == null) myActionListenerInstance = new MyActionListener(direction,moveDistance,index,start,end,relationScrollPane);
		return myActionListenerInstance;
	}
	public static MyActionListener myActionListenerInstance = null;
	public int direction = +1;
	public int moveDistance = 1;
	public int index = 0;
	public int start = 0;
	public int end = 0;
	public JScrollPane relationScrollPane = null;
}
