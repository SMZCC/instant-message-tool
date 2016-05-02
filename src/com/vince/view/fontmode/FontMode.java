/**
 * @author 流浪大法师
 * @time 2016-4-30 上午2:18:28
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.fontmode;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.commons.lang3.StringUtils;

import com.vince.controller.util.Util;
import com.vince.view.util.ContentPanel;

public class FontMode extends ContentPanel{
	private String content = null;
	private ArrayList<int[]> emojiStartEnd = null;
	protected int width = 0;
	protected int height = 0;
	protected int minHeight = 30;
	protected int maxWidth = 370;
	protected int minWidth = 100;
	protected int x = 0;
	protected int y = 0;
	protected int xOffset = 17;
	protected int adjustTextEmojiDistance = 10;
	protected int adjustEmojiTextDistance = 10;
	protected int yOffset = 10;
	private Font font = new Font("微软雅黑",Font.PLAIN,14);
	private JTextPane textPane = null;
	private JPanel panel = null;
	private JPanel mainPanel = null;
	private JLabel avatarLabel = null;
	private Color bgColor = null;
	private int gapOfMainPanelWithAvatarLabel = 15;
	/**
	 * @param url
	 * @param realWidth
	 * @param color
	 */
	public FontMode(String content,String url, int realWidth, final Color bgColor,final int arc,boolean isSetRight,ArrayList<int[]> emojiStartEnd,String avatarPath) {
		super(url, realWidth, null);
		// TODO 在适当情况下对content进行剪枝
		this.content = content;
		this.bgColor = bgColor;
		this.emojiStartEnd = emojiStartEnd;
		setLayout(null);
		mainPanel = new JPanel(null){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(bgColor);
				if(arc == 0){	
					g.drawRect(0, 0, getBounds().width, getBounds().height);
					g.fillRect(0, 0, getBounds().width, getBounds().height);
				}else{
					g.drawRoundRect(0, 0, getBounds().width, getBounds().height, arc, arc);
					g.fillRoundRect(0, 0, getBounds().width, getBounds().height, arc, arc);
				}
			}
		};
		avatarLabel = new JLabel();avatarLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		parseString();
		if(width > maxWidth) width = maxWidth;if(width < minWidth) width = minWidth;
		if(height < minHeight) height = minHeight;
		//System.out.println("width = "+width+" height = "+height);
		mainPanel.setBounds(0, 0, width, height);
		avatarLabel.setIcon(new ImageIcon(Util.getImage(avatarPath, false).getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH)));
		avatarLabel.setBounds(width+gapOfMainPanelWithAvatarLabel, 0, height, height);
		add(mainPanel);add(avatarLabel);
		setSize(avatarLabel.getBounds().x + height,height);
	}
	private void parseString(){
		Iterator<int[]> iterator = emojiStartEnd.iterator();
		int startIndex = 0,endIndex = 0,size = emojiStartEnd.size();
		int[] intArray = null;
		if(size> 0){
			while(iterator.hasNext()){
				intArray = iterator.next();
				if(dataIsRight(intArray[0],intArray[1])){
					setTextPaneLayout(startIndex,intArray[0]);
					setPanelLayout(intArray[0],intArray[1]+1);
					startIndex = intArray[1]+1;endIndex = intArray[1];
				}
			}
		}
		if(isSetTextPaneLayout(size, endIndex)){
			if(endIndex != 0) endIndex++;
			setTextPaneLayout(endIndex,content.length());
		}
	}
	private boolean isSetTextPaneLayout(int size,int endIndex){
		if(size == 0 || endIndex < content.length() - 1){
			return true;
		}
		return false;
	}
	private boolean dataIsRight(int a,int b){
		if(a != -1000 && b != -1000){
			return true;
		}
		return false;
	}
	private void setTextPaneLayout(int startIndex,int endIndex){
		String str = content.substring(startIndex,endIndex);
		if(StringUtils.isEmpty(str)) return;
		textPane = new JTextPane();textPane.setText(str);textPane.setEditable(false);textPane.setFont(font);textPane.setAlignmentX(0.0f);textPane.setBackground(null);textPane.setOpaque(false);
		FontMetrics fontMetrics = textPane.getFontMetrics(textPane.getFont());
		int width = fontMetrics.stringWidth(str) + xOffset;int height = fontMetrics.getHeight() + yOffset;
		
		this.width += width;if(this.height < height) this.height = height;
		textPane.setBounds(x, y, width, height);
		x += width - adjustTextEmojiDistance;
		mainPanel.add(textPane);
	}
	
	private void setPanelLayout(int startIndex,int endIndex){
		String str = content.substring(startIndex,endIndex).replace("&", "");
		//final Image image = ImageIO.read(new File());
		final ImageIcon imageIcon = new ImageIcon(findEmojiPathByContent(str));
		final int width = imageIcon.getIconWidth(),height = imageIcon.getIconHeight();
		panel = new JPanel(){
			private static final long serialVersionUID = -7672232415291983249L;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); 
				g.drawImage(imageIcon.getImage(),0,0,width,height,bgColor,this);
			};
		};
		panel.setBounds(x, y, width, height);
		this.width += width;if(this.height < height) this.height = height;
		x += width - adjustEmojiTextDistance;
		mainPanel.add(panel);
	}
	private String findEmojiPathByContent(String content){
		String path = null;
		ArrayList<String[]> emoji = Util.EMOJI_FACE;
		Iterator<String[]> iterator = emoji.iterator();
		String[] strArr = null;
		while(iterator.hasNext()){
			strArr = iterator.next();
			if(strArr[1].equals(content) || strArr[2].equals(content)){
				path = strArr[0];
				break;
			}
		}
		return path;
	}
	/**
	 * @param content
	 */
	public void setContent(String content) {
		// TODO Auto-generated method stub
		this.content = content;
	}
	
	
}
