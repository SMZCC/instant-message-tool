/**
 * @author 流浪大法师
 * @time 2016-2-14 下午10:38:10
 * @email liuliangsir@gmail.com
 * @descript 定义数据库配置项
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.model.common;
public class JdbcConfig {
	/*数据库驱动*/
	public final static String DRIVERCLASSNAME = "com.mysql.jdbc.Driver";
	/*数据库URL*/
	public final static String URL = "jdbc:mysql://localhost:3306/qq";
	/*数据库用户名*/
	public final static String USERNAME = "root";
	/*数据库密码*/
	public final static String PASSWORD = "";
}
