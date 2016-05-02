/**
 * @author 流浪大法师
 * @time 2016-5-2 下午1:08:29
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.util;

import com.vince.view.relationtab.RelationTab;
import com.wilddog.client.Wilddog;


public class MyWebsocket {
	private Wilddog ref = null;
	private static MyWebsocket myWebSocket = null;
	private MyWebsocket(String path){
		ref = new Wilddog(Util.WEBSOCKET_URL+path);
	}
	public static MyWebsocket getMyWebsocketInstance(String path){
		if(myWebSocket == null) myWebSocket = new MyWebsocket(path);
		return myWebSocket;
	}
	/**
	 * @return the ref
	 */
	public Wilddog getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(Wilddog ref) {
		this.ref = ref;
	}
}
