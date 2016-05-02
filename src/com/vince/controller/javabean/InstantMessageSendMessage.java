/**
 * @author 流浪大法师
 * @time 2016-5-2 下午2:42:51
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.javabean;

import java.util.ArrayList;

public class InstantMessageSendMessage {
	private String to = null;
	private String content = null;
	private ArrayList<int[]> emojiStartEnd = null;
	public InstantMessageSendMessage(String to,String content,ArrayList<int[]> emojiStartEnd){
		this.to = to;
		this.content = content;
		this.emojiStartEnd = emojiStartEnd;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the emojiStartEnd
	 */
	public ArrayList<int[]> getEmojiStartEnd() {
		return emojiStartEnd;
	}
	/**
	 * @param emojiStartEnd the emojiStartEnd to set
	 */
	public void setEmojiStartEnd(ArrayList<int[]> emojiStartEnd) {
		this.emojiStartEnd = emojiStartEnd;
	}
}
