/**
 * @author 流浪大法师
 * @time 2016-3-31 下午2:54:37
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.util;
public final class LogUtil {
	private final static String DEFAULT_PATTERN = ":(		---->来自";
	private final static String SUCCESS_PATTERN = ":)		---->来自";
	public static void log(String message,String pattern,String location){
		if(pattern == null)	pattern = DEFAULT_PATTERN;
		if("success".equalsIgnoreCase(pattern) || "ok".equalsIgnoreCase(pattern)) pattern = SUCCESS_PATTERN;
		System.out.println(message + pattern + location);
	}
}
