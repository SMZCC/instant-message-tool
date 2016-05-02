/**
 * @author 亮
 * @desc 针对打开分组下拉框的效率问题，故采取一些措施:
 * 1.点击分组下拉框如果数量多的话默认打开5个组员的详情
 */
package com.vince.controller.listener;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;

import com.vince.controller.javabean.InstantMessageLogin;
import com.vince.controller.util.ImageIconRotate;
import com.vince.controller.util.Util;
import com.vince.view.config.MyScrollBarUI;
import com.vince.view.groupdetail.GroupDetail;
import com.vince.view.relationerdetail.RelationerDetail;
import com.vince.view.relationtab.RelationTab;

public class RelationTabScrollPaneListener extends MyAdapter{

	public static boolean[] buttonStatus = null;
	public static int[] buttonClickCounter = null;
	public static int[][] groupNameReallyOpenNum = null;
	public static boolean[] groupMemberNumIsBig = null;
	public RelationTab relationTabScrollPanel = null;
	public static int MARGIN_TOP = 6;
	public static int OPEN_MAX_NUM = 5;
	public JScrollPane relationScrollPane = null;
	public String account = null;
	public RelationTabScrollPaneListener() {
		// TODO Auto-generated constructor stub
	}
	public RelationTabScrollPaneListener(RelationTab relationTabScrollPanel,JScrollPane relationScrollPane,String account){
		this.relationTabScrollPanel = relationTabScrollPanel;
		this.relationScrollPane = relationScrollPane;
		this.account = account;
		int capacity = relationTabScrollPanel.sortByGroupIcon.size();
		buttonClickCounter = new int[capacity];
		buttonStatus = new boolean[capacity];
		groupMemberNumIsBig = new boolean[capacity];
		groupNameReallyOpenNum = new int[capacity][1];
		for(int i = 0;i < capacity;i ++){
			buttonStatus[i] = false;
			buttonClickCounter[i] = 0;
			groupMemberNumIsBig[i] = false;
		}
	}
	public RelationTabScrollPaneListener(RelationTab relationTabScrollPanel){
		this.relationTabScrollPanel = relationTabScrollPanel;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//flag = false;
		int count = 0;
		if(e.getSource() == MyScrollBarUI.groupDetail){
			
		}else{
			iconChangeByMouseEntered(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupIcon, count);
			// 实现点击分组名称实现类似功能
			iconChangeByMouseEntered(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupNameLabel, count);
			// 实现点击分组数据实现类似功能
			iconChangeByMouseEntered(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupDataLabel, count);
		}
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

		int count = 0;
		if(e.getSource() == MyScrollBarUI.groupDetail){
			
		}else{
			iconChangeByMouseExited(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupIcon, count);
		// 实现点击分组名称实现类似功能
			iconChangeByMouseExited(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupNameLabel, count);
		// 实现点击分组数据实现类似功能
			iconChangeByMouseExited(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupDataLabel, count);
		}
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int count = 0;
		if(e.getSource() == MyScrollBarUI.groupDetail){
			//点击分组详情关闭组员
			GroupDetail groupDetail = MyScrollBarUI.groupDetail;
			int index = groupDetail.getIndex();
			
			relationTabScrollPanel.sortByGroupIcon.get(index).setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/rightCaretHover.png")));
			//实现点击后将打开的分组成员关闭
			buttonStatus[index] = false;
			relationGroupCloseByMouseClicked(index);
			//System.out.println(index);
			if(groupDetail != null){
				//System.out.println(relationTabScrollPanel == null);
				relationTabScrollPanel.remove(groupDetail);
			}
			groupDetail = null;
		}else{
			// 实现点击分组图标实现类似功能
			iconChangeByMouseClicked(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupIcon, count);
			// 实现点击分组名称实现类似功能
			iconChangeByMouseClicked(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupNameLabel, count);
			// 实现点击分组数据实现类似功能
			iconChangeByMouseClicked(e, relationTabScrollPanel.sortByGroupIcon, relationTabScrollPanel.sortByGroupDataLabel, count);
		}
		
		
		
							
	}
	//实现点击分组图标、分组名称、分组数据时，分组内容能够展开或者收缩
	public void iconChangeByMouseClicked(MouseEvent e , List<JButton> iconList , List list , int count){
		for(Object temp : list){
			if(e.getSource().equals(temp)){

				buttonClickCounter[count] ++;
				
				if(buttonClickCounter[count] % 2 == 0){
					iconList.get(count).setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/rightCaretHover.png")));
					//实现点击后将打开的分组成员关闭
					buttonStatus[count] = false;
					relationGroupCloseByMouseClicked(count);
					
				}else{
					
					iconList.get(count).setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/downCaretHover.png")));
					//实现点击后分组的成员打开
					buttonStatus[count] = true;
					int groupId = Integer.parseInt(relationTabScrollPanel.sortByGroupId.get(count));
					relationGroupExpandByMouseClicked(count,getMemberByGroupId(count,groupId));
					
				}
				buttonClickCounter[count] = buttonClickCounter[count] % 2; 
				break;
			}
			count++;
		}
	}
	public void iconChangeByMouseEntered(MouseEvent e , List<JButton> iconList , List list , int count){
		for(Object temp : list){
			if(e.getSource().equals(temp)){
				if(!buttonStatus[count])
				iconList.get(count).setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/rightCaretHover.png")));
				break;
			}
			count++;
		}		
	}
	public void iconChangeByMouseExited(MouseEvent e , List<JButton> iconList , List list , int count){
		for(Object temp : list){
			if(e.getSource().equals(temp)){
				if(!buttonStatus[count])
					iconList.get(count).setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("./images/rightCaretUnhover.png")));
				break;
			}
			count++;
		}
	}
	public void relationGroupExpandByMouseClicked(int count,ArrayList arrayList){
		JButton iconButton = relationTabScrollPanel.sortByGroupIcon.get(count);
		JLabel realtionGroupLabel = relationTabScrollPanel.sortByGroupNameLabel.get(count);
		
		int startX = 0;
		int startY = realtionGroupLabel.getBounds().y + realtionGroupLabel.getBounds().height + MARGIN_TOP;
		String onlineNumAndTotalNum = relationTabScrollPanel.sortByGroupDataLabel.get(count).getText();
		RelationerDetail relationerDetail = null;
		List<RelationerDetail> list = new ArrayList<RelationerDetail>();
		//实现由于分组组员数多，点击打开后，渲染速度慢的代码实现处
		//恢复myTimerDelayIsSetZero状态
		MyScrollBarUI.myTimerDelayIsSetZero = true;
		int scrollPaneHeight = relationScrollPane.getSize().height;
		InstantMessageLogin instantMessageLogin = null;
		for(int i = 0 ; i < Util.numStrToIntArrBySlash(onlineNumAndTotalNum)[1] ; i++){
			//容易抛出数组下标超出异常
			if(relationTabScrollPanel.groupDetailByGroupName.get(count) !=null && (i < groupNameReallyOpenNum[count][0])&& relationTabScrollPanel.groupDetailByGroupName.get(count).get(i) != null){
				relationerDetail = relationTabScrollPanel.groupDetailByGroupName.get(count).get(i);
			}else{
				instantMessageLogin = (InstantMessageLogin) arrayList.get(i);
				String filePath = Util.IMAGE_PREFIX + instantMessageLogin.getSave_path() + instantMessageLogin.getSave_name();
				String originalNameContent = instantMessageLogin.getUser_name();
				String nowNameContent = instantMessageLogin.getFriend_remark();
				String dynamicMessageContent = instantMessageLogin.getContent();
				String account = instantMessageLogin.getAccount();
				int dynamicMessageContentType = Integer.parseInt(StringUtils.isEmpty(instantMessageLogin.getType())? "0" : instantMessageLogin.getType());
				int current_online_type = instantMessageLogin.getCurrent_online_type();
				boolean isHasCamera = instantMessageLogin.getIs_has_camera() == 1;
				boolean isVip = instantMessageLogin.getIs_qq_member() == 1;
				boolean isSvip = instantMessageLogin.getIs_super_vip() == 1;
				relationerDetail = new RelationerDetail(filePath,Util.getImage(filePath, false),originalNameContent,nowNameContent,dynamicMessageContent,account,dynamicMessageContentType,current_online_type,isHasCamera,isVip,isSvip,true);
				relationerDetail.addMouseListener(new MyDoubleClickListener(account));
			}
			
			relationerDetail.setBounds(startX, startY + relationerDetail.getHeight() * i, relationerDetail.getWidth(), relationerDetail.getHeight());
			//只有分组的组员的位置完全处于滚动窗格之外退出
			scrollPaneHeight = MyScrollBarUI.end == 0 ? scrollPaneHeight : MyScrollBarUI.end;
			//TODO System.out.println("y的坐标:"+relationerDetail.getBounds().y+" scrollPaneHeight = "+scrollPaneHeight);
			if(relationerDetail.getBounds().y > scrollPaneHeight){
				//分组下显示的组员的位置
				//TODO 是否需要判断i==0的情况
				groupNameReallyOpenNum[count][0] = i;
				//System.out.println(groupNameReallyOpenNum[count][0]);
				groupMemberNumIsBig[count] = true;
				
				break;
			}
			relationTabScrollPanel.add(relationerDetail);
			list.add(relationerDetail);
			
		}
		if(relationerDetail == null) return;
		int distance = MARGIN_TOP + relationerDetail.getHeight() * Util.numStrToIntArrBySlash(onlineNumAndTotalNum)[1];
		//更新
		updateGroupMember(distance,count,list,true);
		//TODO 代码复用度低><
	}
	public void relationGroupCloseByMouseClicked(int count){
		//实现点击收起分组成员
		JLabel realtionGroupLabel = relationTabScrollPanel.sortByGroupNameLabel.get(count);
		List<JButton> iconButtonList = relationTabScrollPanel.sortByGroupIcon;
		List<JLabel> groupNameLabelList = relationTabScrollPanel.sortByGroupNameLabel;
		List<JLabel> groupDataLabelList = relationTabScrollPanel.sortByGroupDataLabel;
		Rectangle rectangle  = null;
		int groupMemberCapacity = Util.numStrToIntArrBySlash(groupDataLabelList.get(count).getText())[1];
		List<RelationerDetail> list = relationTabScrollPanel.groupDetailByGroupName.get(count);
		int distance = 0;//该分组下所有成员占据的长度
		if(list == null) return;
		int listLength = list.size();
		if(listLength > 0) distance = MARGIN_TOP + list.get(0).getHeight() * groupMemberCapacity;
		//实现将分组下已经显示出来的隐藏或者去除
		//TODO 消除分组中组员数量少，导致groupNameReallyOpenNum[count][0]为0
		if(groupNameReallyOpenNum[count][0] > 0){
			for(int i = 0; i < groupNameReallyOpenNum[count][0]; i++){
				if(i < listLength)
				relationTabScrollPanel.remove(list.get(i));
			}
		}else{
			for(int i = 0; i < groupMemberCapacity; i++){
				relationTabScrollPanel.remove(list.get(i));
			}
		}
		//实现将后面的分组移动distance距离，然后补上两个分组之间的距离，调整relationTabScrollPanel大小
		//int yPosition = realtionGroupLabel.getBounds().y + realtionGroupLabel.getBounds().height + RelationTab.NAME_LABEL_COMPARE;
		//清除list中的元素
		//list.clear();
		updateGroupMember(distance, count, list,false);
	}
	public void updateGroupMember(int distance,int count,List<RelationerDetail> list,boolean isGroupMemberExpand){
		JLabel realtionGroupLabel = relationTabScrollPanel.sortByGroupNameLabel.get(count);
		List<JButton> iconButtonList = relationTabScrollPanel.sortByGroupIcon;
		List<JLabel> groupNameLabelList = relationTabScrollPanel.sortByGroupNameLabel;
		List<JLabel> groupDataLabelList = relationTabScrollPanel.sortByGroupDataLabel;
		Rectangle rectangle  = null;
		int yPosition = realtionGroupLabel.getBounds().y + realtionGroupLabel.getBounds().height + (isGroupMemberExpand ? distance : RelationTab.NAME_LABEL_COMPARE);
		for(int i = count + 1; i < relationTabScrollPanel.sortByGroupData.size(); i++){
			
			//System.out.println(iconButtonList.get(i).getBounds());
			rectangle = iconButtonList.get(i).getBounds();
			iconButtonList.get(i).setBounds(rectangle.x, RelationTab.MARGIN_TOP * (i - (count + 1))+ yPosition + RelationTab.BUTTON_COMPARE_TO_NAME_LABEL, rectangle.width, rectangle.height);
			relationTabScrollPanel.add(iconButtonList.get(i));
			rectangle = groupNameLabelList.get(i).getBounds();
			groupNameLabelList.get(i).setBounds(rectangle.x, RelationTab.MARGIN_TOP * (i - (count + 1))+ yPosition, rectangle.width, rectangle.height);
			relationTabScrollPanel.add(groupNameLabelList.get(i));
			rectangle = groupDataLabelList.get(i).getBounds();
			groupDataLabelList.get(i).setBounds(rectangle.x, RelationTab.MARGIN_TOP * (i - (count + 1))+ yPosition + RelationTab.DATA_LABEL_Y_SPACE, rectangle.width, rectangle.height);
			relationTabScrollPanel.add(groupDataLabelList.get(i));
			
			if(buttonStatus[i]) {
				//如果被点击后，会显示对应的组员
				int x = 0;
				int y = groupNameLabelList.get(i).getBounds().height + groupNameLabelList.get(i).getBounds().y + MARGIN_TOP;
				RelationerDetail relationerDetails = null;
				JLabel tempLabel = groupDataLabelList.get(i);
				//System.out.println(groupMemberNumIsBig[i]);
				int openCapacity = groupMemberNumIsBig[i] ?  groupNameReallyOpenNum[i][0] :  Util.numStrToIntArrBySlash(tempLabel.getText())[1];
				int height = 0;
				//System.out.println("index = "+ i +"  "+openCapacity);
				for(int j = 0; j < openCapacity; j++){
					relationerDetails = relationTabScrollPanel.groupDetailByGroupName.get(i).get(j);
					relationerDetails.setBounds(x, y + relationerDetails.getHeight() * j, relationerDetails.getWidth(), relationerDetails.getHeight());
					if(j == openCapacity - 1) height = relationerDetails.getHeight();
					relationTabScrollPanel.add(relationerDetails);
				}
				//更新yPosition
				int sum = Util.numStrToIntArrBySlash(groupDataLabelList.get(i).getText())[1];
				yPosition = yPosition + MARGIN_TOP +  height * sum;
			}//点击展开分组退出
		}
			relationTabScrollPanel.groupDetailByGroupName.put(count, list);
		if(isGroupMemberExpand){
			relationTabScrollPanel.setPreferredSize(new Dimension(relationTabScrollPanel.getPreferredSize().width, relationTabScrollPanel.getPreferredSize().height + distance));
		}else{
			relationTabScrollPanel.setPreferredSize(new Dimension(relationTabScrollPanel.getPreferredSize().width, relationTabScrollPanel.getPreferredSize().height - distance));
		}
		//relationTabScrollPanel.validate();
		relationTabScrollPanel.repaint();
	}
	public ArrayList getMemberByGroupId(int ListIndex,int groupId){
		JSONArray jsonArray = (JSONArray) Util.parseJSONByParamName(account, "groupMember");
		List list = (List)JSONArray.toCollection(jsonArray, InstantMessageLogin.class);
		Iterator it = list.iterator();
		InstantMessageLogin instantMessageLogin = null;
		ArrayList<InstantMessageLogin> arrayList = new ArrayList<InstantMessageLogin>();
        while(it.hasNext()){  
        	instantMessageLogin = (InstantMessageLogin)it.next();  
            if(instantMessageLogin.getGroup_id() == groupId){
            	arrayList.add(instantMessageLogin);
            }
        } 
		return arrayList;
	}
}
