/**
 * 
 */
package com.vince.controller.test;

/**
 * @author 亮
 *
 */
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//import com.sun.awt.AWTUtilities;
 
public class Demo extends JPanel
{
    
    public Demo()
    {
        //super();
    setVisible(true);
    setBackground(Color.white);
    setForeground(Color.WHITE);
    setBounds(0, 0, 600, 600);
    }
    public static void main(String[] args)
    {
        JFrame f = new JFrame();   
        
     //   AWTUtilities.setWindowOpacity(f, 0.8f);
             f.setSize(1024, 768);  
            f.add(new Demo());  
             f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
            f.setVisible(true);    
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        int width = getWidth()/2;
        int height = getHeight()/2;
        g2d.setStroke(new BasicStroke(6));//粗细
        g2d.setColor(Color.red);
        
        g2d.drawArc (5, 5, 500, 750, 45, 90+45);  //圆弧
         
       AlphaComposite alphaComposite=AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.42f);
       g2d.setComposite(alphaComposite);//透明度
       
       AffineTransform affineTransform=new AffineTransform();
       affineTransform.setToTranslation(0, 0);
       affineTransform.setToRotation(Math.PI/10);//旋转
       g2d.transform(affineTransform);
       
       ImageIcon imageIcon= new ImageIcon(getClass().getResource("/images/bg_7.jpg"));
       g2d.drawImage(imageIcon.getImage(),0,0,null);//背景
       
       Paint  paint=new GradientPaint(0, 0, Color.RED, 222,222, Color.green, true);//渐变
    g2d.setPaint(paint); 
    g2d.fillRoundRect(188, 188, 300, 300, 33, 33);//矩形
       g2d.setClip(50,50,300, 300);
       g2d.setColor(Color.blue); 
       g2d.fillPolygon(new int[] {0,200,400},new int[] {333,0,333},3);//三角形
       //在矩形范围内画三角形 形成梯形  
       
    }
}