/**
 * @author 流浪大法师
 * @time 2016-3-27 上午3:30:01
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.file;

import java.io.File;

import com.vince.controller.util.LogUtil;
import com.vince.controller.util.LoginSystemTrayUtil;

public final class ConfigUtil {
	private static ConfigUtil ConfigUtilInstance = null;
	public final static String CONFIG_INFORMATION_PATH = "E:"+File.separator+"QQ";
	public final static String CONFIG_FILE_NAME = "config.json";
	private File file = null;
	private ConfigUtil() {
		// TODO Auto-generated constructor stub
		super();
	}
	public static ConfigUtil getConfigUtilInstance(){
		if(ConfigUtilInstance == null) ConfigUtilInstance = new ConfigUtil();
		return ConfigUtilInstance;
	}
	public void saveConfig(String config, String account){
		try {
			String accountDirectoryPrefix = CONFIG_INFORMATION_PATH + File.separator;
			FileUtil.createDirectory(accountDirectoryPrefix + account);
			
			String filePathPrefix = accountDirectoryPrefix + account + File.separator;
			FileUtil.createFile(filePathPrefix + CONFIG_FILE_NAME);
			FileUtil.saveFile(filePathPrefix + CONFIG_FILE_NAME, config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
