/**
 * 
 */
package com.vince.controller.test;

/**
 * @author 亮
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MyWindow1  extends  JFrame {    
public  static void main(String []agrs)
{
  UIManager.LookAndFeelInfo  []info=UIManager.getInstalledLookAndFeels() ;  
  for(UIManager.LookAndFeelInfo tem:info)
  {
   System.out.println(tem.getClassName());
  }
}
}
