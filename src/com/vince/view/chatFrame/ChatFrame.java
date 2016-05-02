/**
 * @author 流浪大法师
 * @time 2016-4-22 上午10:08:18
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.chatFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.commons.lang3.StringUtils;

import com.vince.controller.listener.ChatFrameListener;
import com.vince.controller.util.GBC;
import com.vince.controller.util.Util;
import com.vince.view.config.MyChatFrameScrollBarUI;
import com.vince.view.config.MyScrollBarUI;
import com.vince.view.relationerdetail.RelationerDetail;
import com.vince.view.util.JSplitPaneWithZeroSizeDivider;
import com.vince.view.util.MenuButtonsComponent;
import com.vince.view.util.MyBasicSplitPaneUI;
import com.vince.view.util.ScrollPaneWatermark;

public class ChatFrame {
	private JFrame jframe = null;
	private Container container = null;//获取内容窗格
	private JPanel content = null;
	private JPanel topPanel = null;
	private JPanel topLeftPanel = null;
	private JPanel leftPanel = null;
	private JPanel rightPanel = null;
	private JPanel chatRecordPanel = null;
	private JPanel chatOperatePanel = null;
	private JPanel buttonPanel = null;
	private JTextPane textPane = null;
	private final static int CHAT_FRAME_WIDTH = 585;
	private final static int CHAT_FRAME_HEIGHT = 509;
	private final static int TOP_PANEL_MIN_HEIGHT = 84;
	private final static int TOP_PANEL_MIN_WIDTH = CHAT_FRAME_WIDTH;
	private final static int LEFT_PANEL_MIN_WIDTH = 434;
	private final static int TOP_LEFT_PANEL_MIN_WIDTH = 321;
	private final static int MENU_BUTTONS_COMPONENT_MIN_WIDTH_PADDING = 1;
	private final static int MENU_BUTTONS_COMPONENT_MIN_HEIGHT_PADDING = 0;
	private final static int LEFT_PANEL_MIN_HEIGHT = CHAT_FRAME_HEIGHT - TOP_PANEL_MIN_HEIGHT;
	private final static int RIGHT_PANEL_MIN_WIDTH = CHAT_FRAME_WIDTH - LEFT_PANEL_MIN_WIDTH;
	private final static int RIGHT_PANEL_MIN_HEIGHT = LEFT_PANEL_MIN_HEIGHT;
	private final static int LEFT_TOP_PANEL_MIN_HEIGHT = 56;
	private final static String QQ_SHOW_DEFAULT_PATH = "src\\images\\default_qq_show.png";
	private ChatFrameListener chatFrameListener = null;
	private MenuButtonsComponent menuButtonsComponent = null;
	private JSplitPaneWithZeroSizeDivider splitPane = null;
	private JScrollPane top = null;
	private JScrollPane bottom = null;
	private JScrollPane textPaneScrollPane = null;
	private RelationerDetail relationerDetail = null;
	private ImageIcon avatarFile = null;
	private String nowNameContent = null;
	private String dynamicMessageContent = null;
	private String account = null;
	private String ownerAccount = null;
	private int dynamicMessageContentType = -1;
	private int onlineType = -1;
	private ArrayList<JButton> buttonList = null;
	private ArrayList<String[]> buttonPathList = null;
	private ArrayList<JButton> bottomButtonList = null;
	private ArrayList<String[]> bottomButtonPathList = null;
	private JButton closeButton = null;
	private JButton submitButton = null;
	private JButton caretButton = null;
	private StyledDocument  styledDocument = null;
	private SimpleAttributeSet  attributeSet = null;
	private String avatarPath = null;
	private boolean isDisposed = false;
	public ChatFrame(String ownerAccount,String account,String avatarPath,ImageIcon avatarFile,String nowNameContent,String dynamicMessageContent,int dynamicMessageContentType,int onlineType){
		this.ownerAccount = ownerAccount;
		this.account = account;
		this.avatarPath = avatarPath;
		this.avatarFile = avatarFile;
		this.nowNameContent = nowNameContent;
		this.dynamicMessageContent = dynamicMessageContent;
		this.dynamicMessageContentType = dynamicMessageContentType;
		this.onlineType = onlineType;
		init();
	}
	public void init(){
		initData();
		initView();
	}
	private void initView(){
		content.setOpaque(false);
		content.setLayout(new GridBagLayout());
		jframe.setContentPane(content);
		
		try {
			jframe.setIconImage(Toolkit.getDefaultToolkit().createImage(new URL(avatarPath)).getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		container = jframe.getContentPane();
		/*顶部布局*/
		menuButtonsComponent = new MenuButtonsComponent(true,true,true,true);
		menuButtonsComponent.setChatFrame(this);
		
		/*topLeftPanel布局详情*/
		topLeftPanel.add(relationerDetail);
		initButtonListPosition(topLeftPanel,buttonPathList,relationerDetail,buttonList,4,3,false,0);
		topPanel.add(topLeftPanel, new GBC(0,0,4,1).setFill(GBC.BOTH).setWeight(4.0, 1.0));
		topPanel.add(menuButtonsComponent, new GBC(4,0,1,1).setFill(GBC.BOTH).setWeight(1.0, 1.0).setIpad(MENU_BUTTONS_COMPONENT_MIN_WIDTH_PADDING,MENU_BUTTONS_COMPONENT_MIN_HEIGHT_PADDING));
		topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(219,217,210)));
		container.add(topPanel, new GBC(0,0,4,1).setFill(GBC.BOTH).setIpad(TOP_PANEL_MIN_WIDTH, TOP_PANEL_MIN_HEIGHT));
		
		leftPanel.setLayout(new BorderLayout());
		chatRecordPanel.setPreferredSize(new Dimension(LEFT_PANEL_MIN_WIDTH,300));
		top = new JScrollPane(chatRecordPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER){
			private static final long serialVersionUID = -1618957743603910647L;
			@Override
	    	public JScrollBar createVerticalScrollBar() {
	    		// TODO Auto-generated method stub
	    		final ScrollBar scrollBar = new ScrollBar(JScrollBar.VERTICAL);
	    		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollBar.setUI(new MyChatFrameScrollBarUI());
						scrollBar.validate();
					}
				});
	    		return scrollBar;
	    	}
	    };top.setBorder(BorderFactory.createEmptyBorder());top.setOpaque(false);top.getViewport().setOpaque(false);top.setMinimumSize(new Dimension(LEFT_PANEL_MIN_WIDTH,56));
	    
	    /*top滚动窗格的scrollbar绑定事件*/
	    top.getVerticalScrollBar().addMouseListener(chatFrameListener);
	    int maxHeight = initButtonListPosition(chatOperatePanel,bottomButtonPathList,null,bottomButtonList,6,3,true,90) + 7;
	    setPlainStyle("微软雅黑", 20, true, false, null, true,false,false,null,0,0,0,0);
	    textPane.setOpaque(false);textPane.setPreferredSize(new Dimension(CHAT_FRAME_WIDTH, 60));
	    textPaneScrollPane = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER){
	    	@Override
	    	public JScrollBar createVerticalScrollBar() {
	    		// TODO Auto-generated method stub
	    		final ScrollBar scrollBar = new ScrollBar(JScrollBar.VERTICAL);
	    		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollBar.setUI(new MyChatFrameScrollBarUI());
						scrollBar.validate();
					}
				});
	    		return scrollBar;
	    	}
	    };textPaneScrollPane.setBorder(null);textPaneScrollPane.setOpaque(false);textPaneScrollPane.getViewport().setOpaque(false);textPaneScrollPane.setSize(CHAT_FRAME_WIDTH, 65);textPaneScrollPane.setLocation(0, maxHeight);
	    textPaneScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));textPaneScrollPane.setWheelScrollingEnabled(true);
	    chatOperatePanel.add(textPaneScrollPane);
	    chatOperatePanel.setPreferredSize(new Dimension(LEFT_PANEL_MIN_WIDTH,100));
	    bottom = new JScrollPane(chatOperatePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER){
			private static final long serialVersionUID = -1618957743603910647L;
			@Override
	    	public JScrollBar createVerticalScrollBar() {
	    		// TODO Auto-generated method stub
	    		final ScrollBar scrollBar = new ScrollBar(JScrollBar.VERTICAL);
	    		SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						scrollBar.setUI(new MyChatFrameScrollBarUI());
						scrollBar.validate();
					}
				});
	    		return scrollBar;
	    	}
	    };bottom.setBorder(null);bottom.setMinimumSize(new Dimension(LEFT_PANEL_MIN_WIDTH,72));bottom.setWheelScrollingEnabled(false);
		splitPane.setBorder(null);splitPane.setDividerLocation(200);
		splitPane.setTopComponent(top);splitPane.setBottomComponent(bottom);
		leftPanel.add(splitPane,BorderLayout.CENTER);
		buttonPanel.add(caretButton);
		buttonPanel.add(submitButton);
		buttonPanel.add(closeButton);
		buttonPanel.setPreferredSize(new Dimension(CHAT_FRAME_WIDTH,36));
		leftPanel.add(buttonPanel,BorderLayout.SOUTH);
		container.add(leftPanel, new GBC(0,1,2,3).setFill(GBC.BOTH).setWeight(2.0, 1.0));
		rightPanel.setToolTipText("QQ秀商城");
		rightPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		container.add(rightPanel, new GBC(2,1,1,3).setFill(GBC.BOTH).setIpad(120, 0).setWeight(1.0, 1.0));
		
		jframe.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		jframe.setUndecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(false);
		jframe.setVisible(true);
		jframe.setResizable(true);
		jframe.setSize(CHAT_FRAME_WIDTH,CHAT_FRAME_HEIGHT);
		Util.setHorizontalAndVerticalAlignCenter(jframe, Util.getScreenSize().width, Util.getScreenSize().height, CHAT_FRAME_WIDTH, CHAT_FRAME_HEIGHT);
		chatFrameListener = new ChatFrameListener(this);
		menuButtonsComponent.setChatFrameListener(chatFrameListener);
		jframe.addMouseListener(chatFrameListener);
		//jframe.setIconImage(avatarFile.getImage());
		textPane.getDocument().addDocumentListener(chatFrameListener);
		submitButton.addMouseListener(chatFrameListener);
		closeButton.addMouseListener(chatFrameListener);
		jframe.addMouseMotionListener(chatFrameListener);
	}
	private void initData(){
		jframe = new JFrame();
		
		content = new JPanel();
		topPanel = new JPanel(new GridBagLayout());
		leftPanel = new JPanel();
		topLeftPanel = new JPanel(null);
		rightPanel = new JPanel(){
			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.setColor(new Color(210,207,203));
				g.drawRect(0, 0, RIGHT_PANEL_MIN_WIDTH, RIGHT_PANEL_MIN_HEIGHT);
				g.fillRect(0, 0, RIGHT_PANEL_MIN_WIDTH, RIGHT_PANEL_MIN_HEIGHT);
				g.drawImage(Toolkit.getDefaultToolkit().getImage(QQ_SHOW_DEFAULT_PATH),0,0,this);
			}
		};
		chatRecordPanel = new JPanel(null);
		chatOperatePanel = new JPanel(null);
		splitPane = new JSplitPaneWithZeroSizeDivider(JSplitPane.VERTICAL_SPLIT);
		buttonPanel = new JPanel(null);
		textPane = new JTextPane();
		styledDocument = textPane.getStyledDocument();
		relationerDetail = new RelationerDetail(null,avatarFile, null, nowNameContent, dynamicMessageContent, account, dynamicMessageContentType, onlineType , false, false, false,false);
		buttonPathList = initButtonPathList();
		buttonList = new ArrayList<JButton>();
		bottomButtonPathList = initBottomButtonPathList();
		bottomButtonList = new ArrayList<JButton>();
		ImageIcon iconRollover = new ImageIcon("src\\images\\close.png");ImageIcon iconDefault = new ImageIcon("src\\images\\close_no_border.png");ImageIcon iconPressed = new ImageIcon("src\\images\\close.png");
		int width = iconRollover.getIconWidth(),height = iconRollover.getIconHeight(),x = 282,y = 4;
		closeButton = Util.setButtonThreeStatus(iconRollover, iconDefault, iconPressed, null, null, width, height);closeButton.setBounds(x, y, width, height);
		iconRollover = new ImageIcon("src\\images\\send.png");iconDefault = new ImageIcon("src\\images\\send_no_border.png");iconPressed = new ImageIcon("src\\images\\send.png");
		x = x + width + 6;width = iconRollover.getIconWidth();height = iconRollover.getIconHeight();
		submitButton = Util.setButtonThreeStatus(iconRollover, iconDefault, iconPressed, "按Enter键发送信息，按Ctrl + Enter键换行", null, width, height);submitButton.setBounds(x,y,width,height);
		iconRollover = new ImageIcon("src\\images\\caret.png");iconDefault = new ImageIcon("src\\images\\caret_no_border.png");iconPressed = new ImageIcon("src\\images\\caret.png");
		x = x + width - 1;width = iconRollover.getIconWidth();height = iconRollover.getIconHeight();
		caretButton = Util.setButtonThreeStatus(iconRollover, iconDefault, iconPressed, null, null, width, height);caretButton.setBounds(x,y,width,height);
	}
	public void setPlainStyle(String fontFamily,int size,boolean isBold,boolean isItalic,Color fontColor,boolean isUnderLine,boolean hasBackground,boolean hasIcon,String imagePath,int x,int y,int width,int height){
		attributeSet = new SimpleAttributeSet();
		if(hasIcon){
			StyleConstants.setIcon(attributeSet, new ImageIcon(Util.resizePNG(imagePath, x, y, width, height, false)));
		}else{
			StyleConstants.setBold(attributeSet, isBold);
			StyleConstants.setFontFamily(attributeSet, fontFamily);
			if(fontColor != null) StyleConstants.setForeground(attributeSet, fontColor);
			StyleConstants.setFontSize(attributeSet, size);
			StyleConstants.setUnderline(attributeSet, isUnderLine);
			StyleConstants.setBidiLevel(attributeSet, 10);
			if(hasBackground){
				StyleConstants.setBackground(attributeSet, Color.WHITE);
			}
		}
	}
	private int initButtonListPosition(JPanel topLeftPanel,ArrayList<String[]> myButtonPathList,JComponent sibling,ArrayList<JButton> myButtonList,int x,int offset,boolean isSetLastItem,int otherOffset){
		Iterator<String[]> iterator = myButtonPathList.iterator();
		JButton button = null;
		String[] strArray = null,arr = myButtonPathList.get(myButtonPathList.size()-1);
		ImageIcon imageIcon = null;
		Rectangle rect = sibling == null ? new Rectangle(0, 0) : sibling.getBounds();
		int y = rect.y+rect.height,width = 0,height = 0,maxHeight = -10000;
		while(iterator.hasNext()){
			strArray = iterator.next();
			imageIcon = new ImageIcon(strArray[1]);
			width = imageIcon.getIconWidth();
			height = imageIcon.getIconHeight();
			button = Util.setButtonThreeStatus(imageIcon, new ImageIcon(strArray[0]), imageIcon, strArray[3], null, width, height);
			if(strArray[3].equals(arr[3]) && isSetLastItem){
				x = x + otherOffset - offset;
			}
			button.setBounds(x, y, width, height);
			if(y+height > maxHeight) maxHeight = y + height;
			x = x + width + offset;
			topLeftPanel.add(button);
			myButtonList.add(button);
		}
		return maxHeight;
	}
	private ArrayList<String[]> initButtonPathList(){
		ArrayList<String[]> pathList = new ArrayList<String[]>();
		pathList.add(new String[]{"src\\images\\record.png","src\\images\\record_border.png","src\\images\\record_border.png","发起语音通话"});
		pathList.add(new String[]{"src\\images\\video.png","src\\images\\video_border.png","src\\images\\video_border.png","发起视频通话"});
		pathList.add(new String[]{"src\\images\\chart.png","src\\images\\chart_border.png","src\\images\\chart_border.png","远程演示"});
		pathList.add(new String[]{"src\\images\\file.png","src\\images\\file_border.png","src\\images\\file_border.png","传送文件"});
		pathList.add(new String[]{"src\\images\\computer.png","src\\images\\computer_border.png","src\\images\\computer_border.png","远程桌面"});
		pathList.add(new String[]{"src\\images\\message.png","src\\images\\message_border.png","src\\images\\message_border.png","创建讨论组"});
		pathList.add(new String[]{"src\\images\\application.png","src\\images\\application_border.png","src\\images\\application_border.png","应用"});
		return pathList;
	}
	private ArrayList<String[]> initBottomButtonPathList(){
		ArrayList<String[]> pathList = new ArrayList<String[]>();
		pathList.add(new String[]{"src\\images\\font_no_border.png","src\\images\\font.png","src\\images\\font.png","字体选择工具栏"});
		pathList.add(new String[]{"src\\images\\face_no_border.png","src\\images\\face.png","src\\images\\face.png","选择表情"});
		pathList.add(new String[]{"src\\images\\magic_no_border.png","src\\images\\magic.png","src\\images\\magic.png","VIP魔法表情/超级表情/涂鸦表情/宠物炫"});
		pathList.add(new String[]{"src\\images\\shock_no_border.png","src\\images\\shock.png","src\\images\\shock.png","向好友发送窗口震动"});
		pathList.add(new String[]{"src\\images\\voiceMessage_no_border.png","src\\images\\voiceMessage.png","src\\images\\voiceMessage.png","语音消息"});
		pathList.add(new String[]{"src\\images\\multi-input_no_border.png","src\\images\\multi-input.png","src\\images\\multi-input.png","多功能辅助输入"});
		pathList.add(new String[]{"src\\images\\picture_no_border.png","src\\images\\picture.png","src\\images\\picture.png","发送图片"});
		pathList.add(new String[]{"src\\images\\music_no_border.png","src\\images\\music.png","src\\images\\music.png","点歌"});
		pathList.add(new String[]{"src\\images\\knife_no_border.png","src\\images\\knife.png","src\\images\\knife.png","屏幕截图 CTRL + ALT + A"});
		pathList.add(new String[]{"src\\images\\history_no_border.png","src\\images\\history.png","src\\images\\history.png","显示消息记录"});
		return pathList;
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
	 * @return the top
	 */
	public JScrollPane getTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(JScrollPane top) {
		this.top = top;
	}
	/**
	 * @return the textPane
	 */
	public JTextPane getTextPane() {
		return textPane;
	}
	/**
	 * @param textPane the textPane to set
	 */
	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
	/**
	 * @return the ownerAccount
	 */
	public String getOwnerAccount() {
		return ownerAccount;
	}
	/**
	 * @param ownerAccount the ownerAccount to set
	 */
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	/**
	 * @return the attributeSet
	 */
	public SimpleAttributeSet getAttributeSet() {
		return attributeSet;
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
	/**
	 * @param attributeSet the attributeSet to set
	 */
	public void setAttributeSet(SimpleAttributeSet attributeSet) {
		this.attributeSet = attributeSet;
	}
	/**
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
	/**
	 * @param submitButton the submitButton to set
	 */
	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}
	/**
	 * @return the closeButton
	 */
	public JButton getCloseButton() {
		return closeButton;
	}
	/**
	 * @param closeButton the closeButton to set
	 */
	public void setCloseButton(JButton closeButton) {
		this.closeButton = closeButton;
	}
	/**
	 * @return the chatRecordPanel
	 */
	public JPanel getChatRecordPanel() {
		return chatRecordPanel;
	}
	/**
	 * @param chatRecordPanel the chatRecordPanel to set
	 */
	public void setChatRecordPanel(JPanel chatRecordPanel) {
		this.chatRecordPanel = chatRecordPanel;
	}
	/**
	 * @return the isDisposed
	 */
	public boolean isDisposed() {
		return isDisposed;
	}
	/**
	 * @param isDisposed the isDisposed to set
	 */
	public void setDisposed(boolean isDisposed) {
		this.isDisposed = isDisposed;
	}
}
