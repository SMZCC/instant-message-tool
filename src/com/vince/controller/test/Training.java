/**
 * @author 流浪大法师
 * @time 2016-4-24 下午10:40:22
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.test;
/*        import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

        public class Training extends JFrame {

            public Training() {

                getContentPane().setLayout(new FlowLayout());
                JTable table = new JTable();
                table.setModel(new DefaultTableModel(new Object[][] { { "joe", "joe" },
                        { "mickel", "mickel" }, }, new String[] { "LastName",
                        "FirstName" }));
                final JScrollPane pane = new JScrollPane(table);
                pane.setVisible(false);
                getContentPane().add(pane);

                JButton btn = new JButton("show");
                add(btn);
                btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        pane.setVisible(true);
                        getContentPane().validate();
                        //getContentPane().repaint();
                    }
                });
            }

            public static void main(String[] args) {
                Training app = new Training();
                app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                app.setSize(600, 600);
                app.setVisible(true);
            }
        }*/
/*import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Training {

    private JFrame frame;

    public Training() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JTable table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][]{{"joe", "joe"},
                    {"mickel", "mickel"},}, new String[]{"LastName",
                    "FirstName"}));
        final JScrollPane pane = new JScrollPane(table);
        pane.setVisible(false);
        frame.add(pane);

        JButton btn = new JButton("show");
        frame.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.setVisible(true);

                frame.pack();//this is so the frame will resize after adding pane
                //frame.revalidate();
                frame.repaint();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Training();
            }
        });
    }
}*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Training extends JFrame implements ActionListener{
    JTable table;
    JPanel panel = new JPanel();
    JButton button = new JButton("Add"); 

    String data[][]={{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},                        {"hey","hey"},{"hey","hey"}
,{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"},{"hey","hey"}
,{"hey","hey"},{"hey","hey"}};
    String columns[] = {"First","Second"};

    public Training(){
        setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500,400));
        button.addActionListener(this);
        add(panel,BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        //Create GUI on EDT Thread
    	JFrame frame = new JFrame("JScroll Pane Test");
        
        frame.setSize(new Dimension(800, 600));

        JTextArea txtNotes = new JTextArea();
        txtNotes.setText("Hello World");
        JScrollPane scrollPane = new JScrollPane(txtNotes);
        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        panel.removeAll();
        table =new JTable(data,columns);
        panel.add(new JScrollPane(table),BorderLayout.CENTER);
        repaint();
        pack();
        //revalidate();
    }
}