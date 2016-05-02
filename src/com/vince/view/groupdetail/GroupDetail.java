/**
 * @author 流浪大法师
 * @time 2015 上午2:33:29
 * @email liuliangsir@gmail.com
 * @description
 */
package com.vince.view.groupdetail;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.vince.view.relationtab.RelationTab;

public class GroupDetail extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 873303313553631149L;
	private JButton iconButton = null;
	private JLabel groupNameLabel = null;
	private JLabel groupDataLabel = null;
	private int index = 0;
	/**
	 * 默认构造方法
	 * 
	 * */
	public GroupDetail(){
		
	}
	/**
	 * 有参数的构造方法
	 * @param imageIcon
	 * @param groupName
	 * @param groupOnlineMemberDivideGroupMemberSum
	 * */
	public GroupDetail(ImageIcon imageIcon,String groupName,String groupOnlineMemberDivideGroupMemberSum,int index){
		this.index = index;
		setLayout(null);
		setSize(263,31);
		//setPreferredSize(new Dimension(169,31));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		//setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.white, Color.gray));
		int height = 0;
		FontMetrics fontMetrics =  null;
		//设置图标按钮
		iconButton = new JButton(imageIcon);
		height = (getSize().height - imageIcon.getIconHeight())/2;
		iconButton.setBounds(RelationTab.BUTTON_INIT_POSITION_X, height, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		iconButton.setContentAreaFilled(false);iconButton.setFocusPainted(false);iconButton.setFocusable(false);iconButton.setBorderPainted(false);
		//设置分组名称
		groupNameLabel = new JLabel(groupName);
		fontMetrics = groupNameLabel.getFontMetrics(RelationTab.NAME_LABEL_FONT);
		height = (getSize().height - fontMetrics.getHeight())/2;
		groupNameLabel.setFont(RelationTab.NAME_LABEL_FONT);
		groupNameLabel.setForeground(Color.black);
		groupNameLabel.setBounds(RelationTab.NAME_LABEL_INIT_POSITION_X, height, fontMetrics.stringWidth(groupName)+RelationTab.NAME_LABEL_WIDTH_SPACE, fontMetrics.getHeight());
		//设置分组数据
		groupDataLabel = new JLabel(groupOnlineMemberDivideGroupMemberSum);
		fontMetrics = groupDataLabel.getFontMetrics(RelationTab.DATA_LABEL_FONT);
		height = (getSize().height - fontMetrics.getHeight())/2;
		groupDataLabel.setFont(RelationTab.DATA_LABEL_FONT);
		groupDataLabel.setForeground(Color.black);
		Rectangle rectangle = groupNameLabel.getBounds();
		groupDataLabel.setBounds(rectangle.x + rectangle.width + RelationTab.DATA_LABEL_X_SPACE, height + RelationTab.DATA_LABEL_Y_SPACE, fontMetrics.stringWidth(groupDataLabel.getText()), fontMetrics.getHeight());
		//添加上去
		add(iconButton);
		add(groupNameLabel);
		add(groupDataLabel);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawRect(0, 0, getBounds().width, getBounds().height);
		g.fillRect(0, 0, getBounds().width, getBounds().height);
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
