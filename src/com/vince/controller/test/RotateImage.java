/**
 * 
 */
package com.vince.controller.test;

/**
 * @author 亮
 *
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
  
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
  
public class RotateImage {
    public static void main(String[] args){
        JFrame frame=new RotateImgFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
  
class RotateImgFrame extends JFrame{
    public RotateImgFrame(){
        setTitle("RotateImgTest");
        setSize(WIDTH,HEIGHT);
        setAlwaysOnTop(true);
          
        final ImgPanel imgPanel=new ImgPanel();
        imgPanel.setBounds(0, 0, 400, 400);
        Container con=getContentPane();
        con.add(imgPanel);
          
        antiRotateBtn=new JButton("逆转");
        rotateBtn=new JButton("正转");
        btnPanel=new JPanel();
        btnPanel.add(rotateBtn);
        btnPanel.add(antiRotateBtn);
        con.add(btnPanel,BorderLayout.SOUTH);
        antiRotateBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                imgPanel.setRotate(imgPanel.getRotate()-Math.PI/2);
                imgPanel.repaint();
            }
        });
        rotateBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                imgPanel.setRotate(imgPanel.getRotate()+Math.PI/2);
                imgPanel.repaint();
            }
        });
    }
      
    public static final int WIDTH=400;
    public static final int HEIGHT=450;
    private JPanel btnPanel;
    private JButton antiRotateBtn;
    private JButton rotateBtn;
}
  
class ImgPanel extends JPanel{
      
    public void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        g.fillRect(0, 0, 400, 400);
        g2.rotate(rotate, 200, 200);
          
        image=new ImageIcon("src\\images\\rightCaretHover.png").getImage();
        int imgW=image.getWidth(this);
        int imgH=image.getHeight(this);
        g2.drawImage(image, (400-imgW)/2, (400-imgH)/2,this);
        g2.dispose();
    }
    public Image getImage(){
        return image;
    }
    public double getRotate(){
        return rotate;
    }
    public void setRotate(double rotate){
        this.rotate=rotate;
    }
      
    private Image image;
    private double rotate=0;
}
