/**
 * @author 流浪大法师
 * @time 2016-4-22 下午8:49:30
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.test;


/*import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame window = new Main();
                window.setVisible(true);
            }
        });        
    }

    public Main() {
        JPanel east = new JPanel();
        east.setOpaque(true);
        east.setBackground(Color.WHITE);
        JPanel west = new JPanel();
        west.setOpaque(true);
        west.setBackground(Color.BLUE);

        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        content.add(east, gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        content.add(west, gbc);

        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}*/
/*import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        final JPanel buttons = new JPanel();
        final JScrollPane pane = new JScrollPane(buttons);
        pane.getViewport().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                System.err.println("Change in " + e.getSource());
                System.err.println("Vertical visible? " + pane.getVerticalScrollBar().isVisible());
                System.err.println("Horizontal visible? " + pane.getHorizontalScrollBar().isVisible());
            }
        });
        panel.add(pane);
        frame.setContentPane(panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(800);
                    buttons.add(new JButton("Hello " + i));
                    buttons.revalidate();
                }
                return null;
            }
        };
        worker.execute();
    }
}*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{
    JTable table;
    JPanel panel = new JPanel();
    JButton button = new JButton("Add"); 

    String data[][]={{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},                        {"hey","hey"},{"hey","hey"}
,{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"}
,{"hey","hey"},{"hey","hey"}};
    String columns[] = {"First","Second"};

    public Main(){
        setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500,400));
        button.addActionListener(this);
        add(panel,BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

    }

    public static void main(String [] a){
        Main aitor = new Main();
        aitor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aitor.pack();
        aitor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        panel.removeAll();
        table =new JTable(data,columns);
        panel.add(new JScrollPane(table),BorderLayout.CENTER);
        repaint();
        
    }
}