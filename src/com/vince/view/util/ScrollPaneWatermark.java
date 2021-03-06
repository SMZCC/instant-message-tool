/**
 * @author 流浪大法师
 * @time 2016-4-24 上午10:58:56
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.util;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

public class ScrollPaneWatermark extends JViewport {
  BufferedImage fgimage, bgimage;

  TexturePaint texture;

  public ScrollPaneWatermark() {
    super();
    // setOpaque(false);
  }

  public void setBackgroundTexture(URL url) throws IOException {
    bgimage = ImageIO.read(url);
    Rectangle rect = new Rectangle(0, 0, bgimage.getWidth(null), bgimage.getHeight(null));
    texture = new TexturePaint(bgimage, rect);
  }

  public void setForegroundBadge(URL url) throws IOException {
    fgimage = ImageIO.read(url);
  }

  public void paintComponent(Graphics g) {
    // do the superclass behavior first
    super.paintComponent(g);

    // paint the texture
    if (texture != null) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setPaint(texture);
      g.fillRect(0, 0, getWidth(), getHeight());
    }
  }

  public void paintChildren(Graphics g) {
    super.paintChildren(g);
    if (fgimage != null) {
      g.drawImage(fgimage, getWidth() - fgimage.getWidth(null), 0, null);
    }
  }

  public void setView(JComponent view) {
    view.setOpaque(false);
    super.setView(view);
  }

    // ta.setOpaque(false);

    //ScrollPaneWatermark watermark = new ScrollPaneWatermark();
   // watermark.setBackgroundTexture(new File("src\\images\\vicco.png").toURL());
    //watermark.setForegroundBadge(new File("src\\images\\33.jpg").toURL());
    //watermark.setView(ta);

    //JScrollPane scroll = new JScrollPane();
   // scroll.setViewport(watermark);
  //}

}
