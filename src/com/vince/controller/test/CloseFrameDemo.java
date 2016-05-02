/**
 * 
 */
package com.vince.controller.test;

import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author 亮
 *
 */
public class CloseFrameDemo extends JFrame{
	public CloseFrameDemo(){
        this.setTitle("CloseFrameDemo");
        jbInit();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
    }
    
    private void jbInit(){
        JDesktopPane desktop = new JDesktopPane();
        JInternalFrame internalFrame = this.createJInternalFrame();
        JButton btn = new JButton("DEMO");
        internalFrame.add(btn, "Center");
        desktop.add(internalFrame, JDesktopPane.PALETTE_LAYER);
        internalFrame.setSize(400,400);
        internalFrame.setVisible(true);
        this.add(desktop, "Center");
    }
    
    private JInternalFrame createJInternalFrame(){
        JInternalFrame internalFrame = new JInternalFrame("JInternalFrame Close Demo", true, true, true, true){
            /***
             * 重写此方法让用户确认是否需要关闭
             * */
            @Override
            public void doDefaultCloseAction(){
                //关闭的提示选择
                int result= JOptionPane.showConfirmDialog(
                          this,
                          ("确认要关闭JInternalFrame吗？"),
                          ("关闭"),
                          JOptionPane.YES_NO_OPTION);

                  if(result == JOptionPane.YES_OPTION){
                      //如果选择YES，交由上层设置的关闭处理
                      super.doDefaultCloseAction();
                  }
            }
        };
        return internalFrame;
    }

    /***
     * 重写此方法让用户确认是否需要关闭
     * */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        boolean flag = false;
        
        if (e.getID() == WindowEvent.WINDOW_CLOSING) { 
          //关闭的提示选择
          int result= JOptionPane.showConfirmDialog(
                              this,
                              ("确认要关闭吗？"),
                              ("关闭"),
                              JOptionPane.YES_NO_OPTION);

              if(result == JOptionPane.NO_OPTION){
                  //不关闭，系统托盘
                  flag = true;
              }else{
                  System.out.println("您选择的是yes");
              }
        }
         if(!flag){
           //点击的了YES,那么交给上面去处理关闭的处理
            super.processWindowEvent(e);
          }
     }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new CloseFrameDemo();
            }
        }) ;
    }
}

