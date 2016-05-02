/**
 * @author 流浪大法师
 * @time 2016-4-22 下午2:56:56
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.listener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.list.CursorableLinkedList;
import org.apache.commons.lang3.StringUtils;

import com.vince.controller.javabean.InstantMessageSendMessage;
import com.vince.controller.util.MyWebsocket;
import com.vince.controller.util.Util;
import com.vince.view.chatFrame.ChatFrame;
import com.vince.view.fontmode.FontMode;
import com.wilddog.client.Wilddog;


public class ChatFrameListener extends MyAdapter{
	private ChatFrame chatFrame = null;
	private boolean isDragging = false;
	private int x = -1;
	private int y = -1;
	private int xPosition = 0;
	private int yPosition = 0;
	private boolean flag = true;
	private boolean symbol = true;
	private int deleteLength = 0;
	private int startPosition = 0;
	private int counter = 0;
	private ArrayList<int[]> emojiStartEnd = new ArrayList<int[]>();
	private FontMode fontMode = null;
	private boolean isFriendSendMessage = false;
	private int chatRecordPanelHeight = 0;
	private int scrollBarWidth = 0;
	private Wilddog wilddog = null;
	private JSONObject jsonObject = null;
	private JSONArray jsonArray = null;
	public ChatFrameListener(ChatFrame chatFrame){
		this.chatFrame = chatFrame;
		
		/*if(chatFrame.getTop().getVerticalScrollBar() != null){
			scrollBarWidth = chatFrame.getTop().getVerticalScrollBar().getPreferredSize().width;
		}*/
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getJframe()){
			x = e.getX();
			y = e.getY();
			isDragging = true;
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getJframe()){
			JFrame jframe = chatFrame.getJframe();
			if(isDragging){
				int left = jframe.getLocation().x;
                int top = jframe.getLocation().y;
                jframe.setLocation(left + e.getX() - x, top + e.getY() - y);
			}
			jframe = null;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getJframe()){
			isDragging = false;
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getTop().getVerticalScrollBar()){
			
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getTop().getVerticalScrollBar()){
		}
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(e.getDocument() == chatFrame.getTextPane().getDocument()){
			final String str = chatFrame.getTextPane().getText();
			flag = true;
			findEmojiFace(str);
		}
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(e.getDocument() == chatFrame.getTextPane().getDocument()){
			String str = chatFrame.getTextPane().getText();
			if(StringUtils.isEmpty(str)){
				emojiStartEnd = new ArrayList<int[]>();
				return;
			}
			int cursorPosition = chatFrame.getTextPane().getCaretPosition();
			if(cursorPosition > str.length()) cursorPosition = str.length() - 1;
			else cursorPosition = cursorPosition - 2;
			if(cursorPosition < 0) cursorPosition = 0;
			symbol = true;
			//System.out.println(str);
			if(cursorPosition >= 0)	updateEmojiStartEnd(cursorPosition);
		}
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		if(e.getDocument() == chatFrame.getTextPane().getDocument()){

		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chatFrame.getSubmitButton()){
			final String text = chatFrame.getTextPane().getText();
			if(StringUtils.isEmpty(text.trim())){
				
			}else{
				setUpSocket(text);
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						fontMode = new FontMode(text,null, 0, new Color(255,255,255,125), 15,true,emojiStartEnd,"http://liuliangsir.sinaapp.com/Public/images/2016-03-23/default.png");
						Rectangle rect = fontMode.getBounds(),otherRect = chatFrame.getChatRecordPanel().getBounds();
						xPosition = otherRect.width - rect.width;
						fontMode.setLocation(xPosition, yPosition);
						if(!isFriendSendMessage){
							yPosition += Util.myTwoChatRecordGap + rect.height;
						}else{
							yPosition += Util.youWithOtherChatRecordGap + rect.height;
						}
						chatFrame.getChatRecordPanel().add(fontMode);
						chatFrame.getChatRecordPanel().repaint();
						chatFrame.getChatRecordPanel().validate();
						/*生成json*/
						
					}
				});
				
			}
		}else if(e.getSource() == chatFrame.getCloseButton()){
			chatFrame.setDisposed(true);
			chatFrame.getJframe().dispose();
		}
	}
	private void setUpSocket(String text){
		String ownerAccount = chatFrame.getOwnerAccount();
		String account = chatFrame.getAccount();
		InstantMessageSendMessage instantMessageSendMessage = new InstantMessageSendMessage(account, text, emojiStartEnd);
		wilddog = MyWebsocket.getMyWebsocketInstance("/").getRef();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(ownerAccount, instantMessageSendMessage);
		wilddog.setValue(map);
	}
	private void updateEmojiStartEnd(int cursorPosition){
		Iterator<int[]> iterator = emojiStartEnd.iterator();
		int step = 1;
		boolean boolOne = false,boolTwo = false;
		while(iterator.hasNext()){
			final int[] intArray = iterator.next();
			if(intArray[0] == -1000 && intArray[1] == -1000) continue;
			boolOne = intArray[0] <= cursorPosition;boolTwo = intArray[1] <= cursorPosition;
			//System.out.println("0 = "+intArray[0]+" 1 = "+intArray[1]+" "+cursorPosition);
			if(!boolOne){intArray[0] = intArray[0] - step;intArray[1] = intArray[1] - step;}
			if(boolOne && !boolTwo){
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(symbol){
							try {
								deleteLength = intArray[1] - intArray[0];
								startPosition = intArray[0];
								counter = findIndex(intArray[0],intArray[1]);
								intArray[0] = -1000;intArray[1] = -1000;
								updateEmoji(counter);
								chatFrame.getTextPane().getStyledDocument().remove(startPosition, deleteLength);
							} catch (BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							symbol = false;
						}
					}
				});
				if(!symbol) break;
			}
		}
	}
	private int findIndex(int a,int b){
		int j = 0,len = 0,index = 0;
		for(j = 0,len = emojiStartEnd.size();j < len;j++){
			if(emojiStartEnd.get(j)[0] == a && emojiStartEnd.get(j)[1] == b){
				index = j;break;
			}
		}
		return index;
	}
	private void updateEmoji(int index){
		for(int i = index+1,len = emojiStartEnd.size();i < len;i++){
			if(emojiStartEnd.get(i)[0] != -1000 && emojiStartEnd.get(i)[1] != -1000){
				emojiStartEnd.get(i)[0] = emojiStartEnd.get(i)[0] - deleteLength;
				emojiStartEnd.get(i)[1] = emojiStartEnd.get(i)[1] - deleteLength;
			}
		}
	}
	private void findEmojiFace(final String str){
		ArrayList<String[]> emoji = Util.EMOJI_FACE;
		Iterator<String[]> iterator = emoji.iterator();
		ImageIcon imageIcon = null;
		while(iterator.hasNext()){
			final String[] strArray = iterator.next();
			final boolean boolOne = str.lastIndexOf(strArray[1]) >= 0;
			final boolean boolTwo = str.lastIndexOf(strArray[2]) >= 0;
			if(boolOne||boolTwo){
				SwingUtilities.invokeLater(new Runnable() {
					//@Override
					public void run() {
						if(flag){
							synchronized(str){
								chatFrame.setPlainStyle("微软雅黑", 20, true, false, null, true,false,true,strArray[0],0,0,24,24);
								try {
									String content = null;if(boolOne){content = strArray[1];}if(boolTwo){content = strArray[2];}
									int offsetOne = str.length() - content.length(),length = content.length();content = content.replace("/", "/&");
									int offsetTwo = offsetOne + content.length();
									//System.out.println("offsetOne = "+offsetOne+" str = "+str+" content = "+content);
									int[] intArr = {offsetOne,offsetTwo-1};
									chatFrame.getTextPane()
											.getStyledDocument()
											.insertString(offsetOne,
													content,
													chatFrame.getAttributeSet());
									emojiStartEnd.add(intArr);
									chatFrame.getTextPane().getStyledDocument().remove(offsetTwo, length);
									flag = false;
								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					
				});
							break;
			}
		}
	}
}
