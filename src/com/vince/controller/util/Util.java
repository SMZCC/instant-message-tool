
package com.vince.controller.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.vince.controller.file.ConfigUtil;
import com.vince.controller.file.FileUtil;
import com.vince.controller.javabean.InstantMessageFile;
import com.vince.controller.javabean.InstantMessageGroupData;
import com.vince.controller.javabean.InstantMessageLogin;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 流浪大法师
 *
 */
public final class Util {

	/**
	 * 
	 */
	private Util() {
		// TODO Auto-generated constructor stub
	}
	public static String[][] statusImageFilePathAndDescription =  {{"src\\images\\online.png","  我在线上"},
		{"src\\images\\leave.png","  离开"},{"src\\images\\busy.png","  忙碌"},
		{"src\\images\\noDisturb.png","  请勿打扰"},{"src\\images\\qChat.png","  Q我吧"},{"src\\images\\invisible.png","  隐身"}};
	public static String[][] statusImageIconArray = (String[][]) ArrayUtils.addAll(Util.statusImageFilePathAndDescription, new String[][]{{"src\\images\\android.png","正在移动设备上使用QQ"},{"src\\images\\android_active.png","正在移动设备上使用QQ&QQ达人"},{"src\\images\\android.png","正在移动设备上使用QQ"},{"src\\images\\mac_active.png","正在iPhone上使用QQ&QQ达人"}});
	public static String IMAGE_PREFIX = "http://liuliangsir.sinaapp.com/Public/images/";
	public static String WEBSOCKET_URL = "https://vicco20160520.wilddogio.com";
	public static int myTwoChatRecordGap = 33;
	public static int youWithOtherChatRecordGap = 30;
	public static ArrayList<String[]> EMOJI_FACE = new ArrayList<String[]>(){
		{
			add(new String[]{"src\\images\\emoji\\1.gif","/撇嘴","/pz"});
			add(new String[]{"src\\images\\emoji\\2.gif","/色","/se"});
			add(new String[]{"src\\images\\emoji\\3.gif","/发呆","/fd"});
			add(new String[]{"src\\images\\emoji\\4.gif","/得意","/dy"});
			add(new String[]{"src\\images\\emoji\\5.gif","/流泪","/ll"});
			add(new String[]{"src\\images\\emoji\\6.gif","/害羞","/hx"});
			add(new String[]{"src\\images\\emoji\\7.gif","/闭嘴","/bz"});
			add(new String[]{"src\\images\\emoji\\8.gif","/睡","/shui"});
			add(new String[]{"src\\images\\emoji\\9.gif","/大哭","/dk"});
			add(new String[]{"src\\images\\emoji\\10.gif","/尴尬","/gg"});
			add(new String[]{"src\\images\\emoji\\11.gif","/发怒","/fn"});
			add(new String[]{"src\\images\\emoji\\12.gif","/调皮","/tp"});
			add(new String[]{"src\\images\\emoji\\13.gif","/呲牙","/cy"});
			add(new String[]{"src\\images\\emoji\\14.gif","/微笑","/wx"});
			add(new String[]{"src\\images\\emoji\\15.gif","/难过","/ng"});
			add(new String[]{"src\\images\\emoji\\16.gif","/酷","/kuk"});
			add(new String[]{"src\\images\\emoji\\18.gif","/抓狂","/zk"});
			add(new String[]{"src\\images\\emoji\\19.gif","/吐","/tuu"});
			add(new String[]{"src\\images\\emoji\\20.gif","/偷笑","/tx"});
			add(new String[]{"src\\images\\emoji\\21.gif","/可爱","/ka"});
			add(new String[]{"src\\images\\emoji\\22.gif","/白眼","/baiy"});
			add(new String[]{"src\\images\\emoji\\23.gif","/傲慢","/am"});
			add(new String[]{"src\\images\\emoji\\24.gif","/饥饿","/jie"});
			add(new String[]{"src\\images\\emoji\\26.gif","/惊恐","/jk"});
			add(new String[]{"src\\images\\emoji\\27.gif","/流汗","/lh"});
			add(new String[]{"src\\images\\emoji\\28.gif","/憨笑","/hx"});
			add(new String[]{"src\\images\\emoji\\29.gif","/大兵","/db"});
			add(new String[]{"src\\images\\emoji\\30.gif","/奋斗","/fendou"});
			add(new String[]{"src\\images\\emoji\\31.gif","/咒骂","/zhm"});
			add(new String[]{"src\\images\\emoji\\32.gif","/疑问","/yiw"});
			add(new String[]{"src\\images\\emoji\\33.gif","/嘘...","/xu"});
			add(new String[]{"src\\images\\emoji\\34.gif","/晕","/yun"});
			add(new String[]{"src\\images\\emoji\\35.gif","/折磨","/zhem"});
			add(new String[]{"src\\images\\emoji\\36.gif","/衰","/shuai"});
			add(new String[]{"src\\images\\emoji\\37.gif","/骷髅","/kl"});
			add(new String[]{"src\\images\\emoji\\38.gif","/敲打","/qiao"});
			add(new String[]{"src\\images\\emoji\\39.gif","/再见","/zj"});
			add(new String[]{"src\\images\\emoji\\41.gif","/发抖","/fad"});
			add(new String[]{"src\\images\\emoji\\42.gif","/爱情","/aiq"});
			add(new String[]{"src\\images\\emoji\\43.gif","/跳跳","/tiao"});
			add(new String[]{"src\\images\\emoji\\46.gif","/猪头","/zt"});
			add(new String[]{"src\\images\\emoji\\49.gif","/拥抱","/yb"});
			add(new String[]{"src\\images\\emoji\\53.gif","/蛋糕","/dg"});
			add(new String[]{"src\\images\\emoji\\54.gif","/闪电","/shd"});
			add(new String[]{"src\\images\\emoji\\55.gif","/炸弹","/zhd"});
			add(new String[]{"src\\images\\emoji\\56.gif","/刀","/dao"});
			add(new String[]{"src\\images\\emoji\\57.gif","/足球","/zq"});
			add(new String[]{"src\\images\\emoji\\59.gif","/便便","/bb"});
			add(new String[]{"src\\images\\emoji\\61.gif","/饭","/fan"});
			add(new String[]{"src\\images\\emoji\\62.gif","/药","/yao"});
			add(new String[]{"src\\images\\emoji\\63.gif","/玫瑰","/mg"});
			add(new String[]{"src\\images\\emoji\\64.gif","/凋谢","/dx"});
			add(new String[]{"src\\images\\emoji\\116.gif","/示爱","/sa"});
			add(new String[]{"src\\images\\emoji\\66.gif","/爱心","/xin"});
			add(new String[]{"src\\images\\emoji\\67.gif","/心碎","/xs"});
			add(new String[]{"src\\images\\emoji\\69.gif","/礼物","/lw"});
			add(new String[]{"src\\images\\emoji\\72.gif","/邮件","/youjian"});
			add(new String[]{"src\\images\\emoji\\74.gif","/太阳","/ty"});
			add(new String[]{"src\\images\\emoji\\75.gif","/月亮","/yl"});
			add(new String[]{"src\\images\\emoji\\76.gif","/强","/qiang"});
			add(new String[]{"src\\images\\emoji\\77.gif","/弱","/ruo"});
			add(new String[]{"src\\images\\emoji\\78.gif","/握手","/ws"});
			add(new String[]{"src\\images\\emoji\\79.gif","/胜利","/shl"});
			add(new String[]{"src\\images\\emoji\\85.gif","/飞吻","/fw"});
			
			add(new String[]{"src\\images\\emoji\\86.gif","/怄火","/oh"});
			add(new String[]{"src\\images\\emoji\\89.gif","/西瓜","/xig"});
			add(new String[]{"src\\images\\emoji\\96.gif","/冷汗","/lengh"});
			add(new String[]{"src\\images\\emoji\\97.gif","/擦汗","/ch"});
			add(new String[]{"src\\images\\emoji\\98.gif","/抠鼻","/kb"});
			add(new String[]{"src\\images\\emoji\\99.gif","/鼓掌","/gz"});
			add(new String[]{"src\\images\\emoji\\100.gif","/糗大了","/qd"});
			add(new String[]{"src\\images\\emoji\\101.gif","/坏笑","/huaix"});
			add(new String[]{"src\\images\\emoji\\102.gif","/左哼哼","/zhh"});
			add(new String[]{"src\\images\\emoji\\103.gif","/右哼哼","/yhh"});
			add(new String[]{"src\\images\\emoji\\104.gif","/哈欠","/hq"});
			add(new String[]{"src\\images\\emoji\\105.gif","/鄙视","/bs"});
			add(new String[]{"src\\images\\emoji\\106.gif","/委屈","/wq"});
			add(new String[]{"src\\images\\emoji\\107.gif","/快哭了","/kk"});
			add(new String[]{"src\\images\\emoji\\108.gif","/阴险","/yx"});
			add(new String[]{"src\\images\\emoji\\109.gif","/亲亲","/qq"});
			add(new String[]{"src\\images\\emoji\\110.gif","/吓","/xia"});
			add(new String[]{"src\\images\\emoji\\111.gif","/可怜","/kel"});
			add(new String[]{"src\\images\\emoji\\112.gif","/菜刀","/cd"});
			add(new String[]{"src\\images\\emoji\\113.gif","/啤酒","/pj"});
			add(new String[]{"src\\images\\emoji\\114.gif","/篮球","/lq"});
			add(new String[]{"src\\images\\emoji\\115.gif","/乒乓","/pp"});
			add(new String[]{"src\\images\\emoji\\117.gif","/瓢虫","/pch"});
			add(new String[]{"src\\images\\emoji\\118.gif","/抱拳","/bq"});
			
			add(new String[]{"src\\images\\emoji\\119.gif","/勾引","/gy"});
			add(new String[]{"src\\images\\emoji\\120.gif","/拳头","/qt"});
			add(new String[]{"src\\images\\emoji\\121.gif","/差劲","/cj"});
			add(new String[]{"src\\images\\emoji\\122.gif","/爱你","/aini"});
			add(new String[]{"src\\images\\emoji\\123.gif","/NO","/bu"});
			add(new String[]{"src\\images\\emoji\\124.gif","/OK","/hd"});
			add(new String[]{"src\\images\\emoji\\125.gif","/转圈","/zhq"});
			add(new String[]{"src\\images\\emoji\\126.gif","/磕头","/kt"});
			add(new String[]{"src\\images\\emoji\\127.gif","/回头","/ht"});
			add(new String[]{"src\\images\\emoji\\128.gif","/跳绳","/tsh"});
			
			add(new String[]{"src\\images\\emoji\\129.gif","/挥手","/hsh"});
			add(new String[]{"src\\images\\emoji\\130.gif","/激动","/jd"});
			add(new String[]{"src\\images\\emoji\\132.gif","/街舞","/jw"});
			add(new String[]{"src\\images\\emoji\\133.gif","/左太极","/ztj"});
			add(new String[]{"src\\images\\emoji\\134.gif","/右太极","/ytj"});
			add(new String[]{"src\\images\\emoji\\136.gif","/祈祷","/qidao"});
		}
	};
	public static int getIntArraySum(int...i){
		int sum = 0;
		for (int j : i) {
			sum = sum + j;
		}
		return sum;
	}
	public static String toolsTipFormat(String tips){
		return "<html><body style='background:white;font-size:10px;color:gray;margin:0px;padding:0px;'>"+tips+"</body></html>";
	}
	public static String substr(String originalStr){
		int strlen = originalStr.length();
		if(strlen > 15){
			return originalStr.substring(0, 15);
		}else{
			return originalStr;
		}
	}
	public static String toolsTipFormatMultipleLineByTalk(String tips){
		StringBuffer sb = new StringBuffer("个性签名更新:").append(tips);
		int length = sb.length();
		int counter = length / 15;
		//String sReturn = System.getProperty("line.separator"); 
		for(int i = 1;i <= counter;i++){
			sb = sb.insert(i*15+(i-1)*5, "<br/>");
		}
		if(length % 15 != 0){
			sb.append("<br/>"+"<a href='javascript:;'>评论</a>"+"</html");
		}
		return "<html>"+sb.toString();
	}
	public static String toolsTipFormatMultipleLineByQQZone(String tips){

		StringBuffer sb = new StringBuffer("最新说说:").append(tips);
		int length = sb.length();
		int counter = length / 15;
		//String sReturn = System.getProperty("line.separator"); 
		for(int i = 1;i <= counter;i++){
			sb = sb.insert(i*15+(i-1)*5, "<br/>");
		}
		if(length % 15 != 0){
			sb.append("<br/>"+"<a href='javascript:;'>评论</a>"+"</html");
		}
		return "<html>"+sb.toString();
	
	}
	public static String toolsTipFormatByVip(String originalStr,boolean isSVip){
		String html = "<html>"+originalStr+"<br/>";
		if(isSVip){
			html = html + "<a href=\"javascript:;\">超级会员尊享红色昵称</a>";
		}else{
			html = html + "<a href=\"javascript:;\">QQ会员尊享红色昵称</a>";
		}
		html = html +"</html>";
		return html;
	}
	public static int[] numStrToIntArrBySlash(String str){
		if(!str.contains("/")) return null;
		
		String[] strArr = str.split("/");
		int[] intArr = new int[2];
		int index = 0;
		for(String temp : strArr){
			intArr[index] = Integer.parseInt(temp);
			index++;
		}
		return intArr;
	}
	public static String getNewLine(){
		Properties properties = System.getProperties();
		return properties.getProperty("line.separator");
	}
	public static void removeAllOrSpecificItemMouseListener(Component comp){
		int length = comp.getMouseListeners().length;
		//System.out.println(length+"   "+Arrays.toString(comp.getMouseListeners()));
		//ToolTipManager可能不是MouseListener
		for(int i = 0;i < length - 1;i++){
			//if(i == 1)
			comp.removeMouseListener(comp.getMouseListeners()[i]);
		}
	}
	public static Dimension getScreenSize(){
		
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	public static void setCheckBoxSelectedIfNeed(Object object,JCheckBox checkbox){
		if(!(object instanceof JCheckBox)){
			if(checkbox.isSelected()){
				checkbox.setSelected(false);
			}else{
				checkbox.setSelected(true);
			}
		}
	}
	public static int  getTaskHeight(){
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		//get maximum window bounds
		Rectangle maximumWindowBounds = graphicsEnvironment.getMaximumWindowBounds();
		//这种方式能够获取除去任务栏的高度，在想定位到弹出消息框到右下角的是时候很有用处。
		return getScreenSize().height - maximumWindowBounds.height;
	}
	public static ImageIcon getImage(String filePath,boolean isLocalResource){
		URL imageUrl = null;
		ImageIcon avatarImageIcon = null;
		if(isLocalResource){
			avatarImageIcon = new ImageIcon(filePath);
		}else{
			try {
				imageUrl = new URL(filePath);
				avatarImageIcon = new ImageIcon(ImageIO.read(imageUrl));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return avatarImageIcon;
	}
	public static void setAvatar(String filePath, JLabel avatar, boolean isLocalResource){
		if(filePath == null || "".equals(filePath.trim()) ) filePath = "src\\images\\1652510549.jpg";
		ImageIcon avatarImageIcon = getImage(filePath,isLocalResource);
		avatarImageIcon.setImage(avatarImageIcon.getImage().getScaledInstance(75, 75,Image.SCALE_SMOOTH));
		avatar.setIcon(avatarImageIcon);
		avatar.setBounds(40, 200, avatarImageIcon.getIconWidth(), avatarImageIcon.getIconHeight());
	}
	public static Object parseJSONByParamName(String account, String param){
		Object object = null;
		try {
			String filePath = ConfigUtil.CONFIG_INFORMATION_PATH + File.separator + account + File.separator +ConfigUtil.CONFIG_FILE_NAME;
			String fileContent = FileUtil.getFileContent(filePath, "UTF-8").trim();
			if(!fileContent.equalsIgnoreCase("")){
				JSONObject jsonObject = JSONObject.fromObject(fileContent);
				if(param == null || "".equalsIgnoreCase(param)) return jsonObject;
				return jsonObject.get(param);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	public static void addMapElement(Map<String, Object> map,String name,JSONObject jsonObject,Object obj){
		if(jsonObject.has(name)){
			JSONArray jsonArray = jsonObject.getJSONArray(name);
			List<Object> list = null;
			if(obj instanceof InstantMessageGroupData){
				list = (List<Object>)JSONArray.toCollection(jsonArray, InstantMessageGroupData.class);
			}else if(obj instanceof InstantMessageFile){
				list = (List<Object>)JSONArray.toCollection(jsonArray, InstantMessageFile.class);
			}else if(obj instanceof InstantMessageLogin){
				list = (List<Object>)JSONArray.toCollection(jsonArray, InstantMessageLogin.class);
			}else{
				
			}
			map.put(name, list);
		}
	}
	public static Map readJSON2Map(String json, String[] filter, Object obj,Object others) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(json);
		String message = jsonObject.getString("message");
		boolean success = jsonObject.getBoolean("success");
		addMapElement(map,"data",jsonObject,obj);
		addMapElement(map, "others", jsonObject, others);
		map.put("message", message);
		map.put("success", success);
	    return map;
	}
	public static Map parseJSONStringToMap(JSONObject jsonObject){
		Map<String,Object> map = new HashMap<String,Object>();
		Iterator<String> iterator = jsonObject.keys();
		String key = null;
		while(iterator.hasNext()){
			key = iterator.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}
	public static void setHorizontalAndVerticalAlignCenter(Component comp,int parentWidth,int parentHeight,int width,int height){
		int x = (parentWidth - width) / 2;
		int y = (parentHeight - height) / 2;
		comp.setBounds(x, y, width, height);
	}
	public static String replaceTextWithEllipsis(String text,int limit){
		if(!StringUtils.isEmpty(text) && text.length()>limit){
	    	return text.substring(0,14)+"...";
	    }
		return StringUtils.isEmpty(text) ? "用签名来展示自己的个性" : text;
	}
	public static int[] getTaskInfo(){
		int[] array = new int[4];
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment
	            .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		array[0] = screenSize.width - insets.left - insets.right;
		array[1] = screenSize.height - insets.top - insets.bottom;
		array[2] = insets.left;
		array[3] = insets.top;
	    return  array;
	}
	 public static JButton createButton(String url, String toolTip,int size) {

		    // Create image
		    BufferedImage a = null;
		    try {
		      a = ImageIO.read(new File(url));
		    } catch (IOException ex) {
		      ex.printStackTrace();
		    }
		    BufferedImage bi = new BufferedImage(size, size, BufferedImage.TRANSLUCENT);

		    Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g.drawImage(a, 0, 0, size, size, null);
		    g.dispose();   
		    // get the cursor for this button
		    Cursor cursor =
		            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		    ImageIcon iconRollover = new ImageIcon(bi);
		    ImageIcon iconDefault = new ImageIcon(bi);
		    ImageIcon iconPressed = new ImageIcon(bi);
		    return setButtonThreeStatus(iconRollover, iconDefault, iconPressed,toolTip,cursor,size,size);
		  }
	 public static JButton setButtonThreeStatus(ImageIcon iconRollover,ImageIcon iconDefault,ImageIcon iconPressed,String toolTip,Cursor cursor,int width,int height){
		 JButton button = new JButton();
		    //button.addActionListener(this);
		    button.setIgnoreRepaint(false);
		    button.setFocusable(false);
		    button.setBorderPainted(false);
		    button.setFocusPainted(false);
		    button.setRolloverEnabled(true);
		    button.setToolTipText(toolTip);
		    button.setBorder(null);
		    button.setContentAreaFilled(false);
		    button.setCursor(cursor);
		    button.setIcon(iconDefault);
		    button.setRolloverIcon(iconRollover);
		    button.setPressedIcon(iconPressed);
		    button.setPreferredSize(new Dimension(width,height));
		    return button;
	 }
	 public static ImageIcon resizeImage(Object img, Integer w, Integer h, Integer imgW, Integer imgH) {
		    Image image = objectToImage(img);
		    if(w == null)
		        w=image.getWidth(null);
		    if(h == null)
		        h=image.getHeight(null);
		    if(imgW == null) 
		        imgW=w;
		    else if(imgH == null)
		        imgH=imgW;
		    if(imgH == null)
		        imgH=h;

		    BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = resizedImage.createGraphics();
		    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2d.drawImage(toBufferedImage(image), (w-imgW)/2, (h-imgH)/2, imgW, imgH, null);
		    g2d.dispose();
		    return new ImageIcon(resizedImage);
		}

		public static Image objectToImage(Object img) {
		    if(img instanceof ImageIcon)
		        return ((ImageIcon)img).getImage();
		    else if(img instanceof Image)
		        return (Image)img;
		    else
		        throw new ClassCastException();
		}

		public static BufferedImage toBufferedImage(Image img) { 
		    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = bi.createGraphics();
		    g2d.drawImage(img, 0, 0, null);
		    g2d.dispose();
		    return bi;
		}
		public static JLabel setAvatar(ImageIcon avatarFile,int x,int y,int width,int height,String tooltips,boolean condition,String path){
			avatarFile.setImage(avatarFile.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
			JLabel avatar = new JLabel(avatarFile);
			avatar.setToolTipText(tooltips);
			avatar.setBounds(x, y, width, height);
			if(condition){
				ImageIcon avatarRightBottomImageIcon = new ImageIcon(path);
				JLabel avatarRightBottom = new JLabel(avatarRightBottomImageIcon);
				width = avatarRightBottomImageIcon.getIconWidth();
				height = avatarRightBottomImageIcon.getIconHeight();
				x = avatar.getSize().width - width;
				y = avatar.getSize().height - height;
				avatarRightBottom.setBounds(x, y, width, height);
				avatar.add(avatarRightBottom);
			}
			return avatar;
		}
		public static Image resizePNG(String fromFile, int x, int y, int outputWidth,
				int outputHeight, boolean proportion) {
			
			BufferedImage to = null;
			try {
				File f2 = new File(fromFile);
				BufferedImage bi2 = ImageIO.read(f2);
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) bi2.getWidth(null))
							/ (double) outputWidth + 0.1;
					double rate2 = ((double) bi2.getHeight(null))
							/ (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					//System.out.println(rate1+"  "+rate2);//TODO
					double rate = rate1 < rate2 ? rate1 : rate2;
					newWidth = (int) (((double) bi2.getWidth(null)) / rate);
					newHeight = (int) (((double) bi2.getHeight(null)) / rate);
				} else {
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				to = new BufferedImage(newWidth, newHeight,

				BufferedImage.TYPE_INT_RGB);
				
				Graphics2D g2d = to.createGraphics();

				to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth,
						newHeight,

						Transparency.TRANSLUCENT);

				g2d.dispose();
				
				g2d = to.createGraphics();
				Image from = bi2.getSubimage(x, y, newWidth, newHeight);
				
				g2d.drawImage(from, 0, 0, null);
				
				g2d.dispose();
				//ImageIO.write(to, "png", new File("F:/my.png"));
				//System.out.println(".............");
				
			} catch (IOException e) {

				e.printStackTrace();

			}finally{
				return to;
			}

		}		
		
}
