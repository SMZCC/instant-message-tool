/**
 * 
 */
package com.vince.controller.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * @author 亮
 *
 */
public class ImageIconRotate {
	public ImageIcon iamgeIcon = null;
	public BufferedImage bufferedImage = null;
	public BufferedImage originBufferedImage = null;
	public Graphics2D bufferImageGraphics2D = null; //缓冲区图像的图形环境
	public Image image = null;
	/**
	 * 
	 */
	public ImageIconRotate() {
		// TODO Auto-generated constructor stub
	}
	
	public void loadImage(String filePath){
		image = new ImageIcon(filePath).getImage();
		//System.out.println(image);
		originBufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);//创建原始缓冲区图像
		//MediaTracker mt = new MediaTracker(comp)
		//System.out.println(originBufferedImage);
		bufferedImage = originBufferedImage;
		bufferImageGraphics2D  = bufferedImage.createGraphics();//创建bufImage的图形环境
		bufferImageGraphics2D.drawImage(image,0,0,null);//传输源图像数据到缓冲区图像中
	}
	public void loadImage(URL filePath){
		this.loadImage(filePath.getPath());	
	}
	public void loadImage(File filePath){
		this.loadImage(filePath);
	}
	public ImageIcon  ratoteImage(double angle){

        if (bufferedImage == null)	return null; //如果bufImage为空则直接返回
        BufferedImage filteredBufImage =new BufferedImage(image.getWidth(null) ,image.getHeight(null),BufferedImage.TYPE_INT_ARGB); //过滤后的图像
        AffineTransform transform = new AffineTransform(); //仿射变换对象
        bufferImageGraphics2D.setTransform(transform);
        transform.rotate(angle,200,200); //旋转图像
        AffineTransformOp imageOp = new AffineTransformOp(transform, null);//创建仿射变换操作对象           
        imageOp.filter(originBufferedImage, filteredBufImage);//过滤图像，目标图像在filteredBufImage
        bufferedImage = filteredBufImage; //让用于显示的缓冲区图像指向过滤后的图像
        //重绘组件
        return new ImageIcon(bufferedImage);
    
	}
}
