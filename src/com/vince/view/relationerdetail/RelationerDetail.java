/**
 * @author 亮
 *
 */
package com.vince.view.relationerdetail;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.vince.controller.util.Util;


public class RelationerDetail extends JPanel{

	public ImageIcon avatarFile = null;
	public ImageIcon onlineTypeFile = null;
	public ImageIcon icon = null;
	public JLabel avatar = null;
	public JLabel avatarRightBottom = null;
	public ImageIcon avatarRightBottomImageIcon = null;
	public JLabel onlineTypeLabel = null;
	public JLabel originalName = null;
	public JLabel nowName = null;
	public JLabel dynamicMessage = null;
	public ImageIcon dynamicMessageTypeFile = null;
	public JLabel dynamicMessageType = null;
	public JLabel video = null;
	public ImageIcon videoIcon = null;
	public ImageIcon vipIcon = null;
	public JLabel vip = null;
	public Font defaultFont = new Font("黑体",Font.PLAIN,12);
	public ImageIcon[] imageIconArray = new ImageIcon[]{null,null,null,null,null,null,new ImageIcon("src\\images\\2g.png"),new ImageIcon("src\\images\\3g.png"),new ImageIcon("src\\images\\wifi.png")};
	public String[][] statusImageIconArray = Util.statusImageIconArray;
	public boolean isSetOpaque = true;
	boolean isNotNull = true;
	private String nowNameContent = null;
	private String dynamicMessageContent = null;
	private String avatarPath = null;
	private int dynamicMessageContentType = -1;
	private int onlineType = -1;
	private String account = null;
	public RelationerDetail(){
		
	}
	public RelationerDetail(String avatarPath,ImageIcon avatarFile,String originalNameContent,String nowNameContent,String dynamicMessageContent,String account,int dynamicMessageContentType,int onlineType,boolean isHasCamera,boolean isVip,boolean isSvip,boolean isSetOpaque) {
		// TODO Auto-generated constructor stub
		//ToolTipManager.sharedInstance().setDismissDelay(5000);// 设置为5秒
		this.avatarPath = avatarPath;
		this.nowNameContent = nowNameContent;
		this.dynamicMessageContent = dynamicMessageContent;
		this.dynamicMessageContentType = dynamicMessageContentType;
		this.account = account;
		this.onlineType = onlineType;
		init(avatarFile,originalNameContent,nowNameContent,dynamicMessageContent,account,dynamicMessageContentType,onlineType,isHasCamera,isVip,isSvip);
		setLayout(null);
		setSize(270,55);
		this.isSetOpaque = isSetOpaque;
		add(avatar);
		add(nowName);
		add(originalName);
		add(dynamicMessage);
	}
	public void init(ImageIcon avatarFile,String originalNameContent,String nowNameContent,String dynamicMessageContent,String account,int dynamicMessageContentType,int onlineType,boolean isHasCamera,boolean isVip,boolean isSvip){
		//初始化tooltip样式
		/*SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				PopupFactory.setSharedInstance(new NonRectanglePopupFactory());
			}
		});*/
		this.avatarFile = avatarFile;
		avatarFile.setImage(avatarFile.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
		isNotNull = !StringUtils.isEmpty(originalNameContent);
		this.avatar = new JLabel(avatarFile);
		if(isNotNull) this.avatar.setToolTipText("点击查看高清头像");
		this.avatar.setBounds(11, 5, 45, 45);
		this.nowName = new JLabel(nowNameContent);
		this.nowName.setFont(defaultFont);
		if(isVip || isSvip){
			nowName.setForeground(Color.red);
		}
		nowName.setSize(nowName.getFontMetrics(getFont()).stringWidth(nowNameContent) + 5, 18);
		nowName.setBounds(avatar.getBounds().x + avatar.getBounds().width + 8, 10, nowName.getFontMetrics(getFont()).stringWidth(nowNameContent) + 5, 18);
		/*originalName布局代码开始处*/
		this.originalName = isNotNull ? new JLabel("("+originalNameContent+")") : new JLabel();
		originalName.setForeground(Color.gray);
		int x = nowName.getBounds().x+nowName.getBounds().width;
		int y = nowName.getBounds().y;
		int width = originalName.getFontMetrics(getFont()).stringWidth(originalName.getText());
		int height = nowName.getBounds().height;
		originalName.setBounds(x,y,width>50?50:width,height);
		/*originalName布局代码结束处*/
		
		if(isVip || isSvip){
			vip = new JLabel();
			vipIcon = isVip ? new ImageIcon("src\\images\\vipLogoInQQ.png") : new ImageIcon("src\\images\\svipLogoInQQ.png");
			String tooltips = isVip ? "超级会员身份铭牌" : "QQ会员身份铭牌";
			vip.setToolTipText(tooltips);vip.setIcon(vipIcon);vip.setBounds(originalName.getBounds().x+originalName.getBounds().width +1, 14, vipIcon.getIconWidth(), vipIcon.getIconHeight());
			setUserToolstip(isNotNull, nowNameContent, originalNameContent, account);
			add(vip);
		}else{
			setUserToolstip(isNotNull, nowNameContent, originalNameContent, account);
		}
		//onlineType = 1表示的是电脑在线
		//onlineType = 2表示的是2G手机在线
		//onlineType = 3表示的是3G手机在线
		//onlineType = 4表示的是wifi手机在线
		icon = imageIconArray[onlineType];
		if(icon != null){
			if(isNotNull){
				onlineTypeLabel = new JLabel(icon);
				onlineTypeLabel.setBounds(260-icon.getIconWidth(), 10, icon.getIconWidth(), icon.getIconHeight());
				add(onlineTypeLabel);
			}
			if(onlineType >= Util.statusImageFilePathAndDescription.length){
				String[] current_online_info = statusImageIconArray[onlineType];
				avatarRightBottomImageIcon = new ImageIcon(current_online_info[0]);
				avatarRightBottom = new JLabel(avatarRightBottomImageIcon);
				width = avatarRightBottomImageIcon.getIconWidth();
				height = avatarRightBottomImageIcon.getIconHeight();
				x = avatar.getSize().width - width;
				y = avatar.getSize().height - height;
				avatarRightBottom.setBounds(x, y, width, height);
				avatar.add(avatarRightBottom);
			}		
		}
		//dynamicMessageContentType = 0 表示的是QQ签名
		//dynamicMessageContentType = 1 表示的是QQ空间说说
		//dynamicMessageContentType = 2 表示的是QQ腾讯微博
		dynamicMessage = new JLabel(Util.substr(dynamicMessageContent));
		dynamicMessage.setForeground(Color.gray);
		dynamicMessage.setFont(defaultFont);
		
		if(dynamicMessageContentType == 0){
			dynamicMessage.setToolTipText(Util.toolsTipFormatMultipleLineByTalk(dynamicMessageContent));
			dynamicMessage.setBounds(avatar.getBounds().x + avatar.getBounds().width + 8, originalName.getBounds().y + originalName.getBounds().height, 180, 18);
		}else if(dynamicMessageContentType == 1){
			dynamicMessageTypeFile = isSvip ? new ImageIcon("src\\images\\qqZoneLogoInQQ.png") : new ImageIcon("src\\images\\qqZoneLogoInQQ.png");
			dynamicMessageType = new JLabel(dynamicMessageTypeFile);
			dynamicMessageType.setBounds(avatar.getBounds().x + avatar.getBounds().width + 8, originalName.getBounds().y + originalName.getBounds().height + 2, dynamicMessageTypeFile.getIconWidth(), dynamicMessageTypeFile.getIconHeight());
			add(dynamicMessageType);
			dynamicMessage.setToolTipText(Util.toolsTipFormatMultipleLineByTalk(dynamicMessageContent));
			dynamicMessage.setBounds(dynamicMessageType.getBounds().x + dynamicMessageType.getBounds().width , originalName.getBounds().y + originalName.getBounds().height, 180, 18);
		}else if(dynamicMessageContentType == 2){
			
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(isSetOpaque){
			g.setColor(new Color(249, 250, 251));
			//g.setColor(Color.blue);
			g.drawRect(0, 0, getBounds().width, getBounds().height);
			g.fillRect(0, 0, getBounds().width, getBounds().height);
		}
	}
	/**
	 * @return the avatarFile
	 */
	public ImageIcon getAvatarFile() {
		return avatarFile;
	}
	/**
	 * @param avatarFile the avatarFile to set
	 */
	public void setAvatarFile(ImageIcon avatarFile) {
		this.avatarFile = avatarFile;
	}
	/**
	 * @return the nowNameContent
	 */
	public String getNowNameContent() {
		return nowNameContent;
	}
	/**
	 * @param nowNameContent the nowNameContent to set
	 */
	public void setNowNameContent(String nowNameContent) {
		this.nowNameContent = nowNameContent;
	}
	/**
	 * @return the dynamicMessageContent
	 */
	public String getDynamicMessageContent() {
		return dynamicMessageContent;
	}
	/**
	 * @param dynamicMessageContent the dynamicMessageContent to set
	 */
	public void setDynamicMessageContent(String dynamicMessageContent) {
		this.dynamicMessageContent = dynamicMessageContent;
	}
	/**
	 * @return the dynamicMessageContentType
	 */
	public int getDynamicMessageContentType() {
		return dynamicMessageContentType;
	}
	/**
	 * @param dynamicMessageContentType the dynamicMessageContentType to set
	 */
	public void setDynamicMessageContentType(int dynamicMessageContentType) {
		this.dynamicMessageContentType = dynamicMessageContentType;
	}
	/**
	 * @return the onlineType
	 */
	public int getOnlineType() {
		return onlineType;
	}
	/**
	 * @param onlineType the onlineType to set
	 */
	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}
	/**
	 * @return the avatarPath
	 */
	public String getAvatarPath() {
		return avatarPath;
	}
	/**
	 * @param avatarPath the avatarPath to set
	 */
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	private void setUserToolstip(boolean condition,String nowNameContent,String originalNameContent,String account){
		if(condition){
			nowName.setToolTipText(nowNameContent+"("+originalNameContent+")"+"("+account+")");
			originalName.setToolTipText(nowNameContent+"("+originalNameContent+")"+"("+account+")");
		}
	}
}
