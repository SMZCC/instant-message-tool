/**
 * 
 */
package com.vince.view.config;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;



import com.vince.controller.util.GIFImageMetadata;

/**
 * @author liuliang
 *
 */
public class MyGifPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private GifBean[] gifBeans;
    private Map<Integer, Integer[]> gifBeanMap = new HashMap<Integer, Integer[]>();
    private int index = 0;
    private int delayFactor;
    private Timer timer;

    /**
     * 
     * @param gifFile
     * @param delayFactor
     *            显示gif每帧图片的时间因子
     */
    public MyGifPanel(File gifFile, int delayFactor) {
    setOpaque(false);
	setDelayFactor(delayFactor);
	setGifFile(gifFile);
    }

    /**
     * 设置Gif文件
     * 
     * @param gifFile
     */
    public void setGifFile(File gifFile) {
   
    
	ImageReader reader = null;
	try {
	    ImageInputStream imageIn = ImageIO.createImageInputStream(gifFile);
	    //System.out.println(imageIn.read());
	    Iterator<ImageReader> iter = ImageIO
		    .getImageReadersByFormatName("gif");
	    if (iter.hasNext()) {
		reader = iter.next();
	    }
	    //System.out.println(iter.next());
	    reader.setInput(imageIn, false);
	    gifBeanMap.clear();
	    gifBeans = new GifBean[reader.getNumImages(true)];
	    GIFImageMetadata meta = null;
	    for (int i = 0; i < gifBeans.length; i++) {
	    //System.out.println(reader.);
	    try{
	    	meta = (GIFImageMetadata) reader.getImageMetadata(i);
	    }catch(ClassCastException e){
	    	//e.printStackTrace();
	    }
		gifBeans[i] = new GifBean();
		gifBeans[i].image = reader.read(i);
		//gifBeans[i].x = meta.imageLeftPosition;
		//gifBeans[i].y = meta.imageTopPosition;
		//gifBeans[i].width = meta.imageWidth;
		//gifBeans[i].height = meta.imageHeight;
		//gifBeans[i].disposalMethod = meta.disposalMethod;
		//gifBeans[i].delayTime = meta.delayTime == 0 ? 1
			//: meta.delayTime;
	    }
	    for (int i = 1; i < gifBeans.length; i++) {
		if (gifBeans[i].disposalMethod == 2) {
		    gifBeanMap.put(new Integer(i), new Integer[] { i });
		    continue;
		}
		int firstIndex = getFirstIndex(i);
		List<Integer> list = new ArrayList<Integer>();
		for (int j = firstIndex; j <= i; j++) {
		    list.add(j);
		}
		gifBeanMap.put(new Integer(i), list.toArray(new Integer[] {}));
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	setTimer();
   }

    private synchronized void setTimer() {
	if (timer != null) {
	    timer.cancel();
	}
	timer = new Timer(false);
	timer.schedule(new TimerTask() {
	    @Override
	    public void run() {
		repaint();
		try {
		    //Thread.sleep(gifBeans[index].delayTime * delayFactor);
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		index++;
		if (index >= gifBeans.length) {
		    index = 0;
		}
	    }

	}, 0, 1);

    }

    /**
     * 设置时间因子
     * 
     * @param delayFactor
     */
    public void setDelayFactor(int delayFactor) {
	this.delayFactor = delayFactor;
    }

    @Override
    protected void paintComponent(Graphics g) {
    int x = (this.getSize().width - gifBeans[0].image.getWidth())/2,y = (this.getSize().height - gifBeans[0].image.getHeight())/2;
	//g.drawImage(gifBeans[0].image, x, y, this);
    g.drawImage(gifBeans[0].image, x, y, 50, 50, this);
	if (index > 0) {
	    Integer[] array = gifBeanMap.get(index);
	    for (Integer i : array) {
	    	x = (this.getSize().width - gifBeans[i].image.getWidth())/2;
	    	y = (this.getSize().height - gifBeans[i].image.getHeight())/2;
		g.drawImage(gifBeans[i].image, x, y,50,50,
			this);
	    }
	}
    }

    private int getFirstIndex(int index) {
	int tempIndex = index;
	while (tempIndex > 1) {
	    if (tempIndex - 1 > 0
		    && gifBeans[tempIndex - 1].disposalMethod == 2) {
		return index;
	    }
	    tempIndex--;
	}
	return tempIndex;
    }

    /**
     * 用于保持gif每帧图片的信息
     */
    public class GifBean {
	public BufferedImage image;
	public int x;
	public int y;
	public int width;
	public int height;
	public int disposalMethod;
	public int delayTime;
    }
    /*public static void main(String[] args) {
    	MyGifPanel gifComponent = new MyGifPanel(new File("src\\images\\loading0501.gif"),1);
		JFrame jframe = new JFrame("测试");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(100, 100, 200, 200);
		jframe.getContentPane().add(gifComponent);
		jframe.setVisible(true);
	}*/

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}