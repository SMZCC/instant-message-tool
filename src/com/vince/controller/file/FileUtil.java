/**
 * @author 流浪大法师
 * @time 2016-3-31 下午2:00:34
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.vince.controller.util.LogUtil;

public final class FileUtil {
	/**
	 * 新建目录.
	 * 
	 * @param path 文件路径
	 * @throws Exception
	 */
	private final static String DEFAULT_ENCODE = "UTF-8";
	public static void createDirectory(String path) throws Exception {
		if(StringUtils.isEmpty(path)){
			return;
		}
		try{
			// 获得文件对象
			File f = new File(path);
			if(!f.exists()){
				f.mkdirs();
			}
		}catch(Exception e){
			//System.out.println("创建目录错误.path=" + path);
			throw e;
		}
	}

	/**
	 * 新建文件.
	 * 
	 * @param path 文件路径
	 * @throws Exception
	 */
	public static File createFile(String path) throws Exception {
		if(StringUtils.isEmpty(path)){
			return null;
		}
		try{
			// 获得文件对象
			File f = new File(path);
			if (f.exists()) {
				return f;
			}
			// 如果路径不存在,则创建
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			f.createNewFile();
			return f;
		}catch(Exception e){
			throw e;
		}
	}

	/**
	 * 保存文件(文件不存在会自动创建).
	 * 
	 * @param path 文件路径
	 * @param content 文件内容
	 * @throws Exception
	 */
	public static void saveFile(String path, String content) throws Exception {
		saveFile(path, content, DEFAULT_ENCODE);
	}
	/**
	 * 获取文件内容.
	 * 
	 * @param path 文件路径
	 * @param encoding 编码(UTF-8/gb2312/...)
	 * @throws Exception
	 * @return content
	 */
	public static String getFileContent(String path, String encoding) throws Exception {
		String fileContent = "";
	    String line = null;
		// 获得文件对象
		File f = createFile(path); 
		// 默认编码UTF-8
		encoding = (StringUtils.isEmpty(encoding)) ? DEFAULT_ENCODE : encoding;
		// 开始读取文件
	    InputStreamReader read = new InputStreamReader(new FileInputStream(f),encoding); 
	    BufferedReader reader=new BufferedReader(read);  
	    while ((line = reader.readLine()) != null) { 
	     fileContent += line; 
	    }   
	    read.close();
	    return fileContent;
	}	
	/**
	 * 保存文件(文件不存在会自动创建).
	 * 
	 * @param path 文件路径
	 * @param content 文件内容
	 * @param encoding 编码(UTF-8/gb2312/...)
	 * @throws Exception
	 */
	public static void saveFile(String path, String content, String encoding) throws Exception {
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bw = null;
		try{
			// 获得文件对象
			File f = new File(path);
			// 默认编码UTF-8
			encoding = (StringUtils.isEmpty(encoding)) ? DEFAULT_ENCODE : encoding;
			// 如果路径不存在,则创建
			if(!f.getParentFile().exists()){
				f.getParentFile().mkdirs();
			}
			// 开始保存文件
			fileOutputStream = new FileOutputStream(path,false);
			bw = new BufferedOutputStream(fileOutputStream);
			bw.write(content.getBytes(encoding));
			if(bw != null) bw.close();
			if(fileOutputStream != null) fileOutputStream.close();
		}catch(Exception e){
			LogUtil.log("保存文件错误.path=" + path + ",content=" + content + ",encoding=" + encoding, null, "FileUtil.java第138行");
			throw e;
		}
	}
}
