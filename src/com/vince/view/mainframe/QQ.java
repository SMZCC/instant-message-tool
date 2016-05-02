/**
 * @author 流浪大法师
 * @description QQ主界面  width:283px,height:530px;
 * @warning 尽量不要用setSize(),在没有使用layout manager的时候用setSize，
 * 在使用了layout manager 的时候用setPreferredSize,
 * 并且setPreferredSize通常和setMinimumSize、setMaximumSize联系起来使用
 * @warning JFrame默认使用的BorderLayout,需要调整为FlowLayout
 */
package com.vince.view.mainframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.jvnet.substance.painter.gradient.StandardGradientPainter;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.watermark.SubstanceImageWatermark;



import com.vince.controller.listener.MainFrameListener;
import com.vince.controller.listener.RelationTabScrollPaneListener;
import com.vince.controller.util.SystemTrayUtil;
import com.vince.view.config.MyScrollBarUI;
import com.vince.view.config.MyTabPaneUI;
import com.vince.view.relationtab.RelationTab;
import com.vince.view.util.ContentPanel;
import com.vince.view.util.ExitDialog;
import com.vince.controller.util.Util;

public class QQ {
	private static JFrame jframe = new JFrame("QQ(java版)");//主程序的容器
	private Container container = null;//获取内容窗格
	private Dimension preferredSize = null,maximumSize = null,minimumSize = null;//界面的最大化，最小化，最适合的尺寸
	private FlowLayout flowLayout = null;
	private List<JButton> layeredPaneButtonList = Collections.synchronizedList(new ArrayList<JButton>());//标题栏上的按钮
	private List<JButton> serverButtonList = Collections.synchronizedList(new ArrayList<JButton>());
	private List<JButton> footerButtonList = Collections.synchronizedList(new ArrayList<JButton>());
	private JLabel myNickNameLabel = null;//昵称
	private String myNickName = null;
	private JButton myStatus = null;//在线状态
	private ImageIcon myStatusImage = null;//显示在线状态图片
	private ImageIcon backgroundImageIcon = null;//背景图片
	private JLabel myQqRankLabel = null;//qq等级
	private String myQqRank = null;
	private JButton isQqVIP = null;//是否是qq会员
	private ImageIcon isQqVIPImage = null;//图标是否被点亮
	private JTextField myQqSignature = null;//qq签名
	private String signature = null;//qq签名内容
	private JTextField myResearch = null;//搜索框
	private JButton myResearchButton = null;//搜索框的搜索按钮
	private JTabbedPane myTabPane = null;//选项卡
	private JPanel myQqFocusTab;//QQ特别关注选项卡
	private JPanel myQqCrowdTab;//QQ群/讨论组选项卡
	private JPanel myQqRelationTab;//QQ联系人选项卡
	private JPanel myQqEducationTab;//QQ教育选项卡
	private JPanel myQqChatTab;//QQ会话选项卡
	private JPanel myQqPhoneTab;//QQ手机选项卡
	private JPanel myQqFooter;//QQ底部面板
	private MyTabPaneUI myTabPaneUI;
	private JPanel tabPanel;
	private JScrollPane relationScroll;
	private JScrollPane chatScroll;
	private ExitDialog exitDialog = null;
	private SystemTrayUtil systemTrayUtil = null;
	private ContentPanel contentPanel = null;
	private Color contentPanelBackgroundColor = new Color(202,199,195,200);
	private RelationTab relationTab = null;
	private final static int MAINFRAME_WIDTH = 283;
	private final static int MAINFRAME_HEIGHT = 530;
	private final static int MAINFRAME_MINIMUM_WIDTH = 283;
	private final static int MAINFRAME_MINIMUM_HEIGHT = 530;
	private final static int MAINFRAME_MAXIMUM_WIDTH = 543;
	private final static int MAINFRAME_MAXIMUM_HEIGHT = Util.getScreenSize().height-QQ.getTaskHeight();
	private final static String MAINFRAME_ICON_IMAGE_PATH = "src\\images\\logo_4.png";
	private final static String INSTANT_MESSAGE_BROWSER_MEDAL_PATH = "src\\images\\medal.png";
	private final static String COMPUTER_HOUSEKEEPER = "src\\images\\computerHouseKeeper.png";
	private final static Border EMPTY_BORDER = BorderFactory.createEmptyBorder(0, 0, 0, 0);
	private final static Font DEFAULT_FONT = new Font("微软雅黑",Font.PLAIN,19);
	private final static Font INSTANT_MESSAGE_RANK_FONT = new Font("微软雅黑",Font.PLAIN,9);
	private final static Cursor DEFAULT_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	private String relationTabBackgroundImagePath = null;
	private String waterMarkImagePath = null;
	private String account = null;
	public QQ(String account){
		this.account = account;
		init();
	}
	public void init(){
		dataInit();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewInit();
			}
		});
	}
	public void viewInit(){
		contentPanel = new ContentPanel(null,0,contentPanelBackgroundColor);
		jframe.setContentPane(contentPanel);
		container = jframe.getContentPane();
		container.setLayout(flowLayout);
		jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		minimumSize = new Dimension(MAINFRAME_MINIMUM_WIDTH,MAINFRAME_MINIMUM_HEIGHT);preferredSize = new Dimension(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);maximumSize = new Dimension(MAINFRAME_MAXIMUM_WIDTH, MAINFRAME_MAXIMUM_HEIGHT);
		jframe.setMinimumSize(minimumSize);jframe.setSize(minimumSize);jframe.setMaximumSize(maximumSize);
		Util.setHorizontalAndVerticalAlignCenter(jframe,Util.getScreenSize().width,Util.getScreenSize().height,preferredSize.getSize().width,preferredSize.height);
		jframe.setUndecorated(true);
		jframe.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		jframe.setIconImage(Toolkit.getDefaultToolkit().createImage(MAINFRAME_ICON_IMAGE_PATH));
		
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);  
				    JDialog.setDefaultLookAndFeelDecorated(true);
					SubstanceImageWatermark watermark = new SubstanceImageWatermark(new FileInputStream(waterMarkImagePath));
		            watermark.setKind(ImageWatermarkKind.APP_TILE);
		            watermark.setOpacity((float)0.9);
		            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
		            SwingUtilities.updateComponentTreeUI(jframe);
		            UIManager.put("swing.boldMetal",false);
		            SubstanceSkin skin = new OfficeBlue2007Skin().withWatermark(watermark);
		            
		            SubstanceLookAndFeel.setSkin(skin);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		JLayeredPane jLayeredPane = (JLayeredPane)jframe.getRootPane().getComponents()[1];
		JComponent bar = (JComponent) jLayeredPane.getComponents()[1];
		jframe.getRootPane().setBorder(EMPTY_BORDER);
		jframe.getLayeredPane().setBorder(EMPTY_BORDER);
		bar.setBorder(EMPTY_BORDER);
		setLayeredPaneButton(jLayeredPane,INSTANT_MESSAGE_BROWSER_MEDAL_PATH,180,0,15,22,"已获取所有勋章,QQ聊天更安全快捷");
		setLayeredPaneButton(jLayeredPane,COMPUTER_HOUSEKEEPER,204,0,26,25,"电脑管家帐号宝正在保护您的QQ");
	    //昵称
	    myNickNameLabel = new JLabel(myNickName);myNickNameLabel.setBounds(4, 34, 114, 23);myNickNameLabel.setFont(DEFAULT_FONT);myNickNameLabel.setCursor(DEFAULT_CURSOR);myNickNameLabel.setToolTipText(myNickName);container.add(myNickNameLabel);
	    myNickNameLabel.setHorizontalAlignment(JLabel.RIGHT);myNickNameLabel.setForeground(Color.black);
	    //在线状态
	    /*myStatusImage = new ImageIcon("src\\images\\online.png");*/
	    myStatus = new JButton(myStatusImage);myStatus.setBounds(123,40,(myStatusImage.getIconWidth()+1),(myStatusImage.getIconHeight())+1);myStatus.setBorderPainted(false);myStatus.setFocusable(false);myStatus.setFocusPainted(false);myStatus.setOpaque(false);myStatus.setContentAreaFilled(false);container.add(myStatus);
	    //qq等级
	    myQqRankLabel = new JLabel(myQqRank);
	    myQqRankLabel.setBounds(141, 41, 24, 9);myQqRankLabel.setFont(INSTANT_MESSAGE_RANK_FONT);myQqRankLabel.setForeground(new Color(235,240,49));container.add(myQqRankLabel);
	    /*设置text-shadow*/
	    //vip会员
	    isQqVIP = new JButton(isQqVIPImage);isQqVIP.setBounds(165, 38, 24, 15);isQqVIP.setBorderPainted(false);isQqVIP.setFocusable(false);isQqVIP.setContentAreaFilled(false);container.add(isQqVIP);
	    //qq签名
	    myQqSignature = new JTextField(6);
	    myQqSignature.setFont(new Font("宋体",Font.PLAIN,13));
	    myQqSignature.setForeground(Color.BLACK);
	    myQqSignature.setText(Util.replaceTextWithEllipsis(signature, 14));
	    myQqSignature.setToolTipText((signature == null ? getTips(myQqSignature.getText()) : getTips(signature)));
	    //ToolTipManager.sharedInstance().setDismissDelay(5000);// 设置为5秒
	    myQqSignature.setBounds(5, 58, 212, 25);myQqSignature.setBorder(EMPTY_BORDER);myQqSignature.setOpaque(false);myQqSignature.setEditable(false);container.add(myQqSignature);
	    
	    //服务
	    setServerView("./images/qqZone_2.png",5,85,24,20,"<html><body style='font-size:10px;color:gray;'>QQ空间<br/>&nbsp;&nbsp;有新好友动态</body></html>",serverButtonList,container);
	    setServerView("./images/weiBo.png",29,85,24,20,"<html><body style='font-size:10px;color:gray;'>腾讯微博<br/>&nbsp;&nbsp;有新好友动态</body></html>",serverButtonList,container);
	    setServerView("./images/qqMail.png",53,85,36,20,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>QQ邮箱<br/>&nbsp;&nbsp;收件箱(361)&nbsp;&nbsp;<br/>&nbsp;&nbsp;群邮件(54)&nbsp;&nbsp;<br/>&nbsp;&nbsp;漂流瓶(258)&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/shoppingCar.png",89,85,24,20,"<html><body style='font-size:10px;color:gray;'>拍拍乐享&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/qqWallet.png",113,85,24,20,"<html><body style='font-size:10px;color:gray;'>QQ钱包&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/qqInfo.png",137,85,24,20,"<html><body style='font-size:10px;color:gray;'>QQ会员信息中心&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/qqNews.png",161,85,24,20,"<html><body style='font-size:10px;color:gray;'>腾讯网迷你版&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/forMore.png",185,85,15,22,null,null,container);
	    setServerView("./images/myTheme.png",218,85,24,20,"<html><body style='font-size:10px;color:gray;'>更改外观&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    setServerView("./images/myQqNews.png",230,85,41,20,"<html><body style='font-size:10px;color:gray;'>打开消息盒子&nbsp;&nbsp;</body></html>",serverButtonList,container);
	    //搜索框的实现
	    myResearch = new JTextField(6);
	    myResearch.setText("  搜索:联系人、讨论组、群、企业");
	    myResearch.setForeground(Color.white);
	    myResearch.setToolTipText("<html><body style='font-size:10px;color:gray;'>输入QQ号码、姓名/昵称、拼音、Email查找<br/>联系人还可以通过完整的QQ号查找陌生<br/>人</body></html>");
	    myResearch.setBounds(0, 110, MAINFRAME_WIDTH, 31);
	    myResearch.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.gray));
	    myResearch.setOpaque(false);
	    myResearchButton = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource("./images/searchLogo.png")));
	    myResearchButton.setBounds(252, 110, 31, 30);
	    myResearchButton.setContentAreaFilled(false);
	    myResearchButton.setBorderPainted(false);
	    myResearchButton.setFocusPainted(false);
	    container.add(myResearchButton);
	    container.add(myResearch);
	    tabPanel = new ContentPanel(null,0,new Color(255, 255, 255, 255));
	    tabPanel.setLayout(null);
	    tabPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(241,244,246)));
	    tabPanel.setBounds(0,141,MAINFRAME_WIDTH,300);
	    container.add(tabPanel);
	    //选项卡
	    myTabPane = new JTabbedPane();
	    myTabPane.setBounds(0, 0, MAINFRAME_WIDTH, 300);
	    relationTab = RelationTab.getRelationTab(relationTabBackgroundImagePath,MAINFRAME_WIDTH,null,account);
	    relationScroll = new JScrollPane(relationTab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER){
			private static final long serialVersionUID = -1618957743603910647L;
			@Override
	    	public JScrollBar createVerticalScrollBar() {
	    		// TODO Auto-generated method stub
	    		final ScrollBar scrollBar = new ScrollBar(JScrollBar.VERTICAL);
	    		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollBar.setUI(new MyScrollBarUI(relationTab,relationScroll));
						scrollBar.validate();
					}
				});
	    		return scrollBar;
	    	}
	    };
	    relationScroll.setSize(MAINFRAME_WIDTH, 250);
	    relationScroll.setBorder(EMPTY_BORDER);
	    relationScroll.setViewportBorder(EMPTY_BORDER);
	    //实现监听器和事件源建立起关联
	    relationTab.getRelationTabScrollPaneListener(new RelationTabScrollPaneListener(relationTab,relationScroll,account));
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/qqZoneUntouch.png")), new JLabel(), "<html><body style='font-size:10px;'>QQ空间-特别关心</body></html>");
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/crowdUntouch.png")), new JLabel(), "<html><body style='font-size:10px;'>群/讨论组</body></html>");
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/relationUntouch.png")), relationScroll, "<html><body style='font-size:10px;'>联系人</body></html>");
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/educationUntouch.png")), new JLabel(), "<html><body style='font-size:10px;'>腾讯课堂</body></html>");
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/chatUntouch.png")), new JLabel(), "<html><body style='font-size:10px;'>会话</body></html>");
	    myTabPane.addTab(null, new ImageIcon(this.getClass().getClassLoader().getResource("./images/phoneUntouch.png")), new JLabel(), "<html><body style='font-size:10px;'>我的手机</body></html>");
	    SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				myTabPane.setUI(new MyTabPaneUI());
				
			    myTabPane.validate();
			}
		});
	    myTabPane.setBorder(EMPTY_BORDER);
	    tabPanel.add(myTabPane);
	    //设置底部的面板
	    myQqFooter = new ContentPanel(null,0,new Color(241,244,246));
	    myQqFooter.setLayout(null);
	    myQqFooter.setBounds(0, 441, MAINFRAME_WIDTH, 63);
	    container.add(myQqFooter);
	    //设置应用管理器
	    setServerView("./images/applicantManager.png",256,9,22,22,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开应用管理器</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/mainMenu.png",5,36,25,25,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>主菜单</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/setting.png",35,36,25,25,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开系统设置</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/messageManager.png",65,36,24,25,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开消息管理器</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/fileHelper.png",94,36,25,25,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开文件助手</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/collecter.png",124,36,24,25,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开我的收藏</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/searcher.png",153,36,53,24,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>找人，找群，找服务</body></html>",footerButtonList,myQqFooter);
	    setServerView("./images/applicant.png",211,36,61,26,"<html><body style='font-size:10px;color:gray;margin:0px;padding:0px;'>打开应用宝</body></html>",footerButtonList,myQqFooter);
	    jframe.setResizable(false);
		jframe.setVisible(true);
		//实现jframe关闭按钮进行事件控制
		jframe.addWindowListener(new MainFrameListener(jframe, exitDialog));
			
	}
	public void dataInit(){
		JSONObject jsonObject = (JSONObject) Util.parseJSONByParamName(account,"loginInfo");
		if(!StringUtils.isEmpty(jsonObject.getString("content")))	signature = jsonObject.getString("content");
		if(!StringUtils.isEmpty(jsonObject.getString("user_name")))	myNickName = jsonObject.getString("user_name");
		if(myQqRank == null) myQqRank = "LV"+jsonObject.getInt("rank");
		if(myStatusImage == null){
			int firstIndex = jsonObject.getInt("online_status"),
				secondIndex = 0;
			String path = Util.statusImageFilePathAndDescription[firstIndex][secondIndex];
			myStatusImage  = new ImageIcon(path);
		}
		if(isQqVIPImage == null && jsonObject.getInt("is_qq_member") == 1){
			isQqVIPImage = new ImageIcon("src\\images\\vip.png");
		}
		if(contentPanelBackgroundColor == null){
			JSONArray jsonArray = jsonObject.getJSONArray("backgroundColor");
			if(jsonArray != null) contentPanelBackgroundColor = new Color(jsonArray.getInt(0),jsonArray.getInt(1),jsonArray.getInt(2),jsonArray.getInt(3));
		}
		//System.out.println(jsonObject);
		if(relationTabBackgroundImagePath == null){
			relationTabBackgroundImagePath = (String) Util.parseJSONByParamName(account,"relationTabBackgroundImage");
		}
		if(waterMarkImagePath == null){
			waterMarkImagePath = (String) Util.parseJSONByParamName(account,"waterMarkImage");
		}
	}
	/**
	 * @return the tips
	 * @description 获取提示框的信息
	 * */
	public String getTips(String tips){
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
	/**
	 * @return the taskHeight
	 * @description 获取任务栏的高度
	 * */
	public static int getTaskHeight(){
		//获取屏幕边界  
        Insets   screenInsets   =   Toolkit.getDefaultToolkit().getScreenInsets(jframe.getGraphicsConfiguration());   
        //取得底部边界高度，即任务栏高度  
        int taskHeight=screenInsets.bottom;
        //System.out.println(taskHeight);
		return taskHeight;
	}

	/**
	 * @return the jframe
	 */
	public JFrame getJframe() {
		return jframe;
	}
	/**
	 * @param jframe the jframe to set
	 */
	public void setJframe(JFrame jframe) {
		this.jframe = jframe;
	}
	/**
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}
	/**
	 * @param container the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}
	/**
	 * @return the preferredSize
	 */
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	/**
	 * @param preferredSize the preferredSize to set
	 */
	public void setPreferredSize(Dimension preferredSize) {
		this.preferredSize = preferredSize;
	}
	/**
	 * @return the maximumSize
	 */
	public Dimension getMaximumSize() {
		return maximumSize;
	}
	/**
	 * @param maximumSize the maximumSize to set
	 */
	public void setMaximumSize(Dimension maximumSize) {
		this.maximumSize = maximumSize;
	}
	/**
	 * @return the minimumSize
	 */
	public Dimension getMinimumSize() {
		return minimumSize;
	}
	/**
	 * @param minimumSize the minimumSize to set
	 */
	public void setMinimumSize(Dimension minimumSize) {
		this.minimumSize = minimumSize;
	}
	private void setLayeredPaneButton(JLayeredPane jLayeredPane,String path,int x,int y,int width,int height,String tooltips){
		JButton button = new JButton(null, new ImageIcon(path));
		button.setBounds(x, y, width, height);
		button.setToolTipText(tooltips);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		jLayeredPane.add(button);layeredPaneButtonList.add(button);
	}
	private void setServerView(String path,int x,int y,int width,int height,String tooltips,List<JButton> list,Object comp){
		JButton btn = new JButton(new ImageIcon(QQ.class.getClassLoader().getResource(path)));
		btn.setBounds(x, y, width, height);btn.setContentAreaFilled(false);btn.setBorderPainted(false);btn.setFocusPainted(false);btn.setFocusable(false);
		if(tooltips != null)	btn.setToolTipText(tooltips);
		if(comp instanceof Container)	((Container)comp).add(btn);
		else if(comp instanceof JPanel) ((JPanel) comp).add(btn);
		if(list != null)	list.add(btn);		
	}
}
