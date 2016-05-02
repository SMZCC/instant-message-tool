/**
 * @author 流浪大法师
 * @time 2016-3-12 下午2:55:17
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.listener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.directwebremoting.extend.Sleeper;

import com.vince.controller.file.ConfigUtil;
import com.vince.controller.file.FileUtil;
import com.vince.controller.javabean.InstantMessageFile;
import com.vince.controller.javabean.InstantMessageGroupData;
import com.vince.controller.javabean.InstantMessageLogin;
import com.vince.controller.util.AnimationUtil;
import com.vince.controller.util.HttpTookit;
import com.vince.controller.util.LogUtil;
import com.vince.controller.util.LoginSystemTrayUtil;
import com.vince.controller.util.SystemTrayUtil;
import com.vince.controller.util.Util;
import com.vince.view.loginframe.Login;
import com.vince.view.mainframe.QQ;
import com.vince.view.softkeyboard.SoftKeyBoard;
import com.vince.view.statuslist.StateList;
import com.vince.view.util.MyConfirmDialog;

public class LoginFrameListener extends MyAdapter {
	private Login login = null;
	private boolean isDragging = false;
	private int x = -1;
	private int y = -1;
	private StateList stateList = null;
	private SoftKeyBoard softKeyBoard = null;
	private ArrayList<JButton> status = null;
	private ArrayList<JButton> softKeyBoardButtonArray = null;
	private MyConfirmDialog myConfirmDialog = null;
	private final static String LOWER_CASE_Q = "q";
	private final static String UPPER_CASE_Q = "Q";
	private final static String NUMBER_LABEL_ONE_FIRST = "`";
	private final static String OP_LABEL_TWO_FIRST = "~";
	private final static String URL_PREFIX = "http://liuliangsir.sinaapp.com/index.php/InstantMessageLogin/";
	private final static String IMAGE_PREFIX = Util.IMAGE_PREFIX;
	private boolean isUsernameGetJsonSuccess = false;
	private boolean islogin = true;
	private String previousUsernameValue = "";
	private JButton button = null;
	private LoginSystemTrayUtil loginSystemTrayUtil = LoginSystemTrayUtil.getLoginSystemTrayInstance();
	private ArrayList<Component> filter = new ArrayList<Component>();
	private ImageIcon cancelImageIcon = new ImageIcon("src\\images\\cancel.png");
	private ImageIcon loginImageIcon = new ImageIcon("src\\images\\login.png");
	private AnimationUtil animation = null;
	private Point point = null;
	private QQ tencent = null;
	private Timer timer = null;
	private final static int ALREADY_LOGIN = 1;
	private boolean isStatusButtonClicked = false;
	private final static String RELATION_TAB_BACKGROUND_IMAGE_PATH = "src\\images\\bear.jpg";
	private final static String WATER_MARK_IMAGE_PATH = "src\\images\\33.jpg";
	public LoginFrameListener(){}
	public LoginFrameListener(Login login){
		this.login = login;
		/*初始化过滤数组*/
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == login.getJframe()){
			x = e.getX();
			y = e.getY();
			isDragging = true;
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == login.getJframe()){
			JWindow jframe = login.getJframe();
			if(isDragging){
				int left = jframe.getLocation().x;
                int top = jframe.getLocation().y;
                jframe.setLocation(left + e.getX() - x, top + e.getY() - y);
                if(stateList != null){
                	stateList.dispose();
                	stateList = null;
                }
                if(softKeyBoard != null){
                	softKeyBoard.dispose();
                	softKeyBoard = null;
                }
                if(myConfirmDialog != null){
    				myConfirmDialog.dispose();
    				myConfirmDialog = null;
    			}
			}
			jframe = null;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == login.getJframe()){
			isDragging = false;
		}else if(e.getSource() == loginSystemTrayUtil.getTrayIcon() && e.getButton() == MouseEvent.BUTTON3){
			Dimension dimension = loginSystemTrayUtil.getPopMenu().getPreferredSize();
			loginSystemTrayUtil.getPopMenu().setLocation(e.getX() - dimension.width, e.getY() - dimension.height);
			loginSystemTrayUtil.getPopMenu().setVisible(true);
			dimension = null;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == login.getAvatarStatus()){
			if(stateList == null){
				stateList = new StateList(login.getJframe());
				stateList.setVisible(true);
				/*初始化status*/
				status = stateList.getStatus();
				for(JButton temp : status){
					temp.addMouseListener(this);
				}
			}
		}else if(e.getSource() == login.getRememberPassword() || e.getSource() == login.getRememberPasswordLabel()){
			Util.setCheckBoxSelectedIfNeed(e.getSource(),login.getRememberPassword());
			String password = String.valueOf(login.getPassword().getPassword());
			Map<String,Object> map =  getConfigJsonContentMap();
			if(map.get("isRememberPassword") == null || login.getRememberPassword().isSelected()){
				map.put("isRememberPassword", true);
			}else{
				map.put("isRememberPassword", false);
			}
			if(map.get("password") == null || !password.equalsIgnoreCase((String)map.get(password))){
				map.put("password", password);
			}
			ConfigUtil.getConfigUtilInstance().saveConfig(JSONObject.fromObject(map).toString(), login.getUsername().getText());
			password = null;
		}else if(e.getSource() == login.getAutoLogin() || e.getSource() == login.getAutoLoginLabel()){
			Util.setCheckBoxSelectedIfNeed(e.getSource(),login.getAutoLogin());
			Map<String,Object> map = getConfigJsonContentMap();
			if(map.get("isAutoLogin") == null || login.getAutoLogin().isSelected()){
				map.put("isAutoLogin", true);
			}else{
				map.put("isAutoLogin", false);
			}
			ConfigUtil.getConfigUtilInstance().saveConfig(JSONObject.fromObject(map).toString(), login.getUsername().getText());
		}else if(e.getSource() == login.getLogin()){
			if(StringUtils.isEmpty(login.getUsername().getText().trim()) || "".equalsIgnoreCase(String.valueOf(login.getPassword().getPassword()))){
				setConfigDialog("密码不能为空或者用户帐号不能为空");
			}else{
				Map<String,Object> hashMap = null;
				Map<String,Object> map = null;
				initFilter();
				/*登录操作*/
				if(islogin){
					loginOperate(hashMap,map);
				}else{
					/*点击取消动画消失以及不出现登录界面*/
					if(timer != null) timer.cancel();
					if(animation != null) animation.stop();
					hashMap = (Map<String, Object>)Util.parseJSONByParamName(login.getUsername().getText(), "avatarLocationInfo");
					login.getAvatar().setLocation((Integer)hashMap.get("x"),(Integer)hashMap.get("y"));
					
					setComponentVisible(login.getJframe().getContentPane().getComponents(),true);
					login.getLogin().setIcon(loginImageIcon);
					login.getLogin().setSize(loginImageIcon.getIconWidth(), loginImageIcon.getIconHeight());
					islogin = true;
				}
				
			}
		}else if(e.getSource() == login.getKeyBoard()){
			if(softKeyBoard == null){
				softKeyBoard = new SoftKeyBoard(login.getJframe());
				softKeyBoard.setVisible(true);
				/*添加监听器*/
				softKeyBoardButtonArray = softKeyBoard.getAll_button();
				for(JButton temp : softKeyBoardButtonArray){
					temp.addMouseListener(this);
				}
			}
		}else if(softKeyBoard != null && e.getSource() == softKeyBoard.getShift()){
			int[] result = findIndex(softKeyBoardButtonArray,false);
			int index = result[1];
			boolean isNumber = (result[0] == 1);
			if(isNumber){
				setButtonText(softKeyBoardButtonArray,softKeyBoard.getNumber_label_two(),softKeyBoard.getOp_label_two(),index);
			}else{
				setButtonText(softKeyBoardButtonArray,softKeyBoard.getNumber_label_one(),softKeyBoard.getOp_label_one(),index);
			}
		}else if(softKeyBoard != null && e.getSource() == softKeyBoard.getExit_button()){
			softKeyBoard.dispose();
			if(myConfirmDialog != null){
				myConfirmDialog.dispose();
				myConfirmDialog = null;
			}
			softKeyBoard = null;
		}else if(softKeyBoard != null && e.getSource() == softKeyBoard.getCancel()){
			String password = String.valueOf(login.getPassword().getPassword());
			if(password.length() >= 1){
				login.getPassword().setText(password.substring(0, password.length()-1));
			}else{
				setConfigDialog("密码不能为空");
			}
		}else if(softKeyBoard != null && e.getSource() == softKeyBoard.getCaps_lock()){
			int[] result = findIndex(softKeyBoardButtonArray,true);
			int index = result[1];
			boolean isLowerCase = (result[0] == 1);
			if(isLowerCase){
				setButtonText(softKeyBoardButtonArray,softKeyBoard.getAlphabet_label_one_to_upper_case(), softKeyBoard.getAlphabet_label_two_to_upper_case(),index);
			}else{
				setButtonText(softKeyBoardButtonArray,softKeyBoard.getAlphabet_label_one(), softKeyBoard.getAlphabet_label_two(),index);
			}
		}else{
			String password = String.valueOf(login.getPassword().getPassword());
			if(softKeyBoardButtonArray != null){
				for(JButton temp : softKeyBoardButtonArray){
					if(temp == e.getSource()){
						login.getPassword().setText(password+temp.getText());
						break;
					}
				}				
			}
		}
		if(status != null){
			int index = 0;
			for(JButton temp : status){
				if(e.getSource() == temp){
					login.getAvatarStatus().setIcon(temp.getIcon());
					if(!isStatusButtonClicked)	isStatusButtonClicked = true;
					/*将状态放到本地*/
					String usernameText = login.getUsername().getText().trim();
					if(!StringUtils.isEmpty(usernameText)){
						Map<String,Object> map = getConfigJsonContentMap();
						map.put("setOnlineStatus", index);
						ConfigUtil.getConfigUtilInstance().saveConfig(JSONObject.fromObject(map).toString(), usernameText);
					}
					stateList.dispose();
					stateList = null;
					break;
				}
				index++;
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(status != null){
			for(JButton temp : status){
				if(e.getSource() == temp){
					temp.setBackground(Color.blue);
					break;
				}
			}
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(status != null){
			for(JButton temp : status){
				if(e.getSource() == temp){
					temp.setBackground(null);
					break;
				}
			}
		}
		if(e.getSource() == login.getUsername()){
			String account = login.getUsername().getText().trim();
			userNameTextFillChangeAvatar(account);
			previousUsernameValue = account;
		}else if(e.getSource() == login.getPassword()){
			String password = String.valueOf(login.getPassword().getPassword());
			/*自动登录*/
			Map<String,Object> map = getConfigJsonContentMap();
			boolean isAutoLogin = (Boolean) (map.get("isAutoLogin") == null ? false : map.get("isAutoLogin"));
			if(!StringUtils.isEmpty(password) && isAutoLogin){
				Map<String,Object> hashMap = null;
				map = null;
				initFilter();
				loginOperate(hashMap,map);
			}
		}else{
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginSystemTrayUtil.getLoginSystemTrayInstance().getExit()){
			System.exit(0);
		}else if(e.getSource() == loginSystemTrayUtil.getLoginSystemTrayInstance().getShow()){
			if(!login.getJframe().isVisible()){
				login.getJframe().setVisible(true);
				loginSystemTrayUtil.getPopMenu().setVisible(false);
			}
		}
	}
	private int[] findIndex(ArrayList<JButton> softKeyBoardButtonArray,boolean isAllChar){
		int[] result = {0,0};
		int index = 0;
		for(JButton temp : softKeyBoardButtonArray){
			if(isAllChar){
				if(temp.getText() == LOWER_CASE_Q){
					result[0] = 1;
					break;
				}else if(temp.getText() == UPPER_CASE_Q){
					result[0] = 0;
					break;
				}				
			}else{
				if(temp.getText() == NUMBER_LABEL_ONE_FIRST){
					result[0] = 1;
					break;
				}else if(temp.getText() == OP_LABEL_TWO_FIRST){
					result[0] = 0;
					break;
				}					
			}
			index++;
		}
		result[1] = index;
		return result;
	}
	private void setButtonText(ArrayList<JButton> softKeyBoardButtonArray,String[] strArrOne,String[] strArrTwo,int index){
		String[] strArray = (String[]) ArrayUtils.addAll(strArrOne, strArrTwo);
		for(int i = index; i < index + strArray.length; i++){
			softKeyBoardButtonArray.get(i).setText(strArray[i-index]);
		}
	}
	private void userNameTextFillChangeAvatar(String account){
		if(isSendImageJson(account)){
			String jsonString = sendAjax("getImageJson",login.getUsername().getText(),null);
			Map map2 = Util.readJSON2Map(jsonString,null,new InstantMessageFile(),null);
			LogUtil.log((String)map2.get("message"),"success",this.getClass().getSimpleName()+".java第293行");
			isUsernameGetJsonSuccess = (Boolean) map2.get("success");
			/*设置图片*/
			ArrayList arrayList = (ArrayList)map2.get("data");
			if(arrayList.size() != 1){
				LogUtil.log("本地输出的提示信息：数据有误，请重新获取数据", null, this.getClass().getSimpleName()+".java第290行");
				return;
			}
			InstantMessageFile imf = (InstantMessageFile) arrayList.get(0);
			String imageUrlStr = IMAGE_PREFIX + imf.getSave_path() + imf.getSave_name();
			Util.setAvatar(imageUrlStr, login.getAvatar(), false);
			
			/*记住密码*/
			if(Util.parseJSONByParamName(account,"isRememberPassword") != null){
				boolean isSelected = (Boolean)Util.parseJSONByParamName(account,"isRememberPassword");
				login.getRememberPassword().setSelected(isSelected);
				if(isSelected){
					/*保存密码后读取密码*/
					login.getPassword().setText((String)Util.parseJSONByParamName(account,"password"));
				}
			}
			/*自动登录*/
			if(Util.parseJSONByParamName(account,"isAutoLogin") != null) login.getAutoLogin().setSelected((Boolean)Util.parseJSONByParamName(account,"isAutoLogin"));
		}
	}
	private String sendAjax(String api, String account, Map<String,Object> others){
		String url = URL_PREFIX + api;
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("account", account);
		if(others != null){
			for (Map.Entry<String, Object> entry : others.entrySet()) { 
				map1.put(entry.getKey(),entry.getValue()+"");
            }
		}
		String jsonString = HttpTookit.doPost(url, map1, true);
		return jsonString;
	}
	private boolean isSendImageJson(String account){
		return !(StringUtils.isEmpty(account) || isUsernameGetJsonSuccess) || (!previousUsernameValue.equalsIgnoreCase(account) && !StringUtils.isEmpty(account));
	}
	private void setConfigDialog(String message){
		if(myConfirmDialog == null || myConfirmDialog.getDispose()){
			myConfirmDialog = new MyConfirmDialog(login.getJframe(),message,"温馨提示");
			myConfirmDialog.setVisible(true);
		}
	}
	private void setComponentVisible(Component[] componentArray,boolean isSetVisible){
		for(Component comp : componentArray) {
			if(filter.indexOf(comp) >= 0){
				comp.setVisible(isSetVisible);
			}
		}
	}
	private void initFilter(){
		if(filter.isEmpty()){
			filter.add(login.getAutoLogin());filter.add(login.getUsername());
			filter.add(login.getPassword());filter.add(login.getAutoLoginLabel());
			filter.add(login.getRememberPassword());filter.add(login.getRememberPasswordLabel());
			filter.add(login.getRegister());filter.add(login.getForgetSecret());			
		}
	}
	private Map getConfigJsonContentMap(){
		Map<String,Object> map = null;
		if(Util.parseJSONByParamName(login.getUsername().getText(),null) != null){
			map = Util.parseJSONStringToMap((JSONObject)Util.parseJSONByParamName(login.getUsername().getText(),null));
		}else{
			map = new HashMap<String, Object>();
		}
		return map;
	}
	private void loginOperate(Map<String,Object> hashMap,Map<String,Object> map){
		/*实现动画开始之前记录avatar的x以及y坐标*/
		point = login.getAvatar().getLocation();
		hashMap = new HashMap<String, Object>();
		hashMap.put("x", point.x);hashMap.put("y", point.y);
		map = getConfigJsonContentMap();
		map.put("avatarLocationInfo", hashMap);
		/*默认水印设置*/
		if(StringUtils.isEmpty((String)Util.parseJSONByParamName(login.getUsername().getText(),"relationTabBackgroundImage"))) map.put("relationTabBackgroundImage", RELATION_TAB_BACKGROUND_IMAGE_PATH);
		if(StringUtils.isEmpty((String)Util.parseJSONByParamName(login.getUsername().getText(),"waterMarkImage"))) map.put("waterMarkImage", WATER_MARK_IMAGE_PATH);
		ConfigUtil.getConfigUtilInstance().saveConfig(JSONObject.fromObject(map).toString(), login.getUsername().getText());
		/*发送ajax请求*/
		map = new HashMap<String, Object>();
		map.put("password", String.valueOf(login.getPassword().getPassword()));
		if(isStatusButtonClicked){
			map.put("setOnlineStatus", Util.parseJSONByParamName(login.getUsername().getText(),"setOnlineStatus"));						
		}
		String jsonString = sendAjax("getLoginInformationJson", login.getUsername().getText(),map);
		Map map2 = Util.readJSON2Map(jsonString,null,new InstantMessageLogin(),new InstantMessageGroupData());
		if(!(Boolean)map2.get("success")){
			setConfigDialog((String)map2.get("message"));
			return;
		}else{
			LogUtil.log((String)map2.get("message"),"success",this.getClass().getSimpleName()+".java第185行");
		}
		
		ArrayList arrayList = (ArrayList)map2.get("data");
		if(arrayList.size() == 0){
			LogUtil.log("本地输出的提示信息：数据有误，请重新获取数据", null, this.getClass().getSimpleName()+".java第190行");
			return;
		}
		InstantMessageLogin iml = (InstantMessageLogin) arrayList.get(0);
		if(iml.getIs_already_login() == ALREADY_LOGIN){
			setConfigDialog("您已登录"+login.getUsername().getText()+"不能重复登录");
			return;
		}
		/*去除第一个数据*/
		arrayList.remove(0);
		//登录数据保存在config.json文件中
		map = getConfigJsonContentMap();
		map.put("loginInfo", iml);
		map.put("groupMember", arrayList);
		if(map2.containsKey("others")){
			map.put("groupData", map2.get("others"));
		}
		ConfigUtil.getConfigUtilInstance().saveConfig(JSONObject.fromObject(map).toString(), login.getUsername().getText());
		//动画以及按钮
		setComponentVisible(login.getJframe().getContentPane().getComponents(),false);
		login.getLogin().setIcon(cancelImageIcon);
		login.getLogin().setSize(cancelImageIcon.getIconWidth(), cancelImageIcon.getIconHeight());
		animation = new AnimationUtil("{\"property\":\"x\",\"duration\":\"1s\",\"value\":135}",login.getAvatar());
		islogin = false;
		timer = new Timer(false);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(animation.isStop()){
					timer.cancel();
					//程序主界面
					if(tencent == null){
						login.getJframe().dispose();
						//SwingUtilities.invokeLater(new Runnable() {
							
							//public void run() {
								tencent = new QQ(login.getUsername().getText());								
							//}
						//});
					}
				}
			}
		},new Date(),100);
	}
}
