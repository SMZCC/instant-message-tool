/**
 * @author 流浪大法师
 * @time 2016-4-1 下午1:54:12
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.util;

import java.awt.Component;
import java.awt.Point;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;


public final class AnimationUtil {
	private Timer timer = null;
	private MyTimerTask timerTask = null;
	private Map<String, Object> map = null;
	private boolean isStop = false;
	public AnimationUtil(String config,Component comp) {
		// TODO Auto-generated constructor stub
		timer = new Timer(false);
		init(config,comp);
	}
	private void init(String config,Component comp){
		map = Util.parseJSONStringToMap(new JSONObject().fromObject(config));
		int duration = Integer.parseInt(((String)map.get("duration")).replace("s",""));
		int value = (Integer)(map.get("value"));
		timerTask = new MyTimerTask(comp,(String)map.get("property"),value,this);
		timer.schedule(timerTask, new Date(), (int)(duration*1000/value));
	}
	public void stop(){
		if(timer != null){
			timer.cancel();
		}
	}
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}
	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	/**
	 * @return the isStop
	 */
	public boolean isStop() {
		return isStop;
	}
	/**
	 * @param isStop the isStop to set
	 */
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
}
class MyTimerTask extends TimerTask{
	private String property = null;
	private final static int STEP = 1;
	private AnimationUtil animation = null;
	private int counter = 0;
	private int value = 0;
	private Component comp = null;
	private Point point = null;
	public MyTimerTask(Component comp,String property,int value,AnimationUtil animation) {
		// TODO Auto-generated constructor stub
		this.property = property;
		this.comp = comp;
		this.value = value;
		this.animation = animation;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if("x".equalsIgnoreCase(property)){
			point = comp.getLocation();
			comp.setLocation(point.x + STEP, point.y);
			counter++;
			if(counter == value){
				animation.stop();
				animation.setStop(true);
			}
		}
	}
	
}
