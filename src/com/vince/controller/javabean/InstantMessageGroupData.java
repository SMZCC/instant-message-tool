/**
 * @author 流浪大法师
 * @time 2016-4-19 下午4:33:25
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.javabean;


public class InstantMessageGroupData {
	private int group_id;
	private String group_name;
	private String onlineDivideTotal;
	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	/**
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * @return the onlineDivideTotal
	 */
	public String getOnlineDivideTotal() {
		return onlineDivideTotal;
	}
	/**
	 * @param onlineDivideTotal the onlineDivideTotal to set
	 */
	public void setOnlineDivideTotal(String onlineDivideTotal) {
		this.onlineDivideTotal = onlineDivideTotal;
	}
	
}
