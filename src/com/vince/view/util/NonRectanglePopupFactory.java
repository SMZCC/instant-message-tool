/*
package com.vince.view.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.Border;

public class NonRectanglePopupFactory extends PopupFactory {
	private static final int BORDER_PAD = 20;
	private static Robot robot;
	static{
		try{
			robot = new Robot();
		}catch (Exception e){
			
		}
	}

	public NonRectanglePopupFactory() {
	}

	@Override
	public Popup getPopup(Component owner, Component contents, int x, int y)
			throws IllegalArgumentException {
		if (contents instanceof JToolTip) {
			((JToolTip) contents).setBorder(BorderFactory.createEmptyBorder());
			Dimension dim = contents.getPreferredSize();
			Rectangle bound = new Rectangle(x, y, dim.width + 2 * BORDER_PAD,
					dim.height + 2 * BORDER_PAD);
			*//**
			 * 杩欐槸鍏抽敭鍒涘缓鍖呭惈浠庡睆骞曚腑璇诲彇鐨勫儚绱犵殑鍥惧儚銆傝鍥惧儚涓嶅寘鎷紶鏍囧厜鏍囥��
			 *//*
			BufferedImage backgroundImage = robot.createScreenCapture(bound);
			NonRectangleFrame frame = new NonRectangleFrame(owner, contents,
					backgroundImage);
			return super.getPopup(owner, frame, x, y);
		} else
			return super.getPopup(owner, contents, x, y);
	}

	class NonRectangleFrame extends JComponent {
		*//**
		 * 
		 *//*
		private static final long serialVersionUID = -7361891909026860122L;

		public NonRectangleFrame(Component owner, Component content,
				BufferedImage backgroundImage) {
			setLayout(new BorderLayout());
			setOpaque(false);
			add(content, BorderLayout.CENTER);
			setBorder(new NonRectangleBorder(owner, content, backgroundImage));
		}
	}

	class NonRectangleBorder implements Border {
		private BufferedImage leftImage;
		private BufferedImage rightImage;
		private BufferedImage topImage;
		private BufferedImage bottomImage;
		private Component content;
		private Color backColor = new Color(255, 255, 255);
		private Color borderColor = new Color(95, 145, 145);

		NonRectangleBorder(Component owner, Component content,
				BufferedImage backgroundImage) {
			this.content = content;
			// backColor = this.content.getBackground();
			// borderColor = backColor.darker();
			generateLeftImage(backgroundImage);
			generateTopImage(backgroundImage);
			generateRightImage(backgroundImage);
			generateBottomImage(backgroundImage);
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			// 缁樺埗鍥惧舰锛岃繖浜涘浘褰㈡槸褰撳墠浣嶇疆鐨勬埅鍥惧浘褰�
			g.drawImage(leftImage, x, y, c);
			g.drawImage(rightImage, x + width - BORDER_PAD, y, c);
			g.drawImage(topImage, x + BORDER_PAD, y, c);
			g.drawImage(bottomImage, x + BORDER_PAD, y + height - BORDER_PAD, c);
			Rectangle bounds = new Rectangle(x, y, width, height);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(backColor); // 鑳屾櫙棰滆壊
			content.setBackground(backColor);// 浣胯儗鏅壊涓庡～鍏呯殑澶氳竟褰㈤鑹蹭竴鑷�
			g2d.fill(getArea(bounds.getSize()));
			g2d.setColor(borderColor);
			g2d.draw(getArea(bounds.getSize()));// 鐢昏竟妗�

			g.setColor(Color.black);
		}

		*//**
		 * 杩斿洖鐢诲浘鎵�闇�瑕佺殑鍖哄煙<br>
		 * 杩欓噷涓昏鐢ㄥ埌浜嗗浘褰㈠悎骞跺叡鑳姐�傞�氳繃鍥惧舰鍚堝苟鎴戜滑鍙互瀹炵幇鍚勭鑷畾涔夌殑鍥惧舰
		 * 
		 * @param dim
		 * @return
		 *//*
		private Area getArea(Dimension dim) {
			int roundX = BORDER_PAD - 2;
			int roundY = BORDER_PAD - 10;
			Shape r = new RoundRectangle2D.Float(roundX, roundY, dim.width
					- roundX * 2, dim.height - roundY * 2, 10, 10); // 鍦嗚鐭╁舰
			Area area = new Area(r);
			Polygon polygon = new Polygon();// 澶氳竟褰�
			polygon.addPoint(25, roundY);
			polygon.addPoint(35, roundY);
			polygon.addPoint(22, 0);
			area.add(new Area(polygon)); // 鍚堝苟鍥惧舰
			return area;
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(BORDER_PAD, BORDER_PAD, BORDER_PAD, BORDER_PAD);
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}

		private void generateLeftImage(BufferedImage backgroundImage) {
			leftImage = backgroundImage.getSubimage(0, 0, BORDER_PAD,
					backgroundImage.getHeight());
		}

		private void generateTopImage(BufferedImage backgroundImage) {
			topImage = backgroundImage.getSubimage(BORDER_PAD, 0,
					backgroundImage.getWidth() - 2 * BORDER_PAD, BORDER_PAD);
		}

		private void generateRightImage(BufferedImage backgroundImage) {
			rightImage = backgroundImage.getSubimage(backgroundImage.getWidth()
					- BORDER_PAD, 0, BORDER_PAD, backgroundImage.getHeight());
		}

		private void generateBottomImage(BufferedImage backgroundImage) {
			bottomImage = backgroundImage.getSubimage(BORDER_PAD,
					backgroundImage.getHeight() - BORDER_PAD,
					backgroundImage.getWidth() - 2 * BORDER_PAD, BORDER_PAD);
		}
	}
}
*/