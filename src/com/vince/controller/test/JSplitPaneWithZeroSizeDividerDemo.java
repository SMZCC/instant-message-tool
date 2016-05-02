package com.vince.controller.test;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;

import com.vince.view.util.JSplitPaneWithZeroSizeDivider;

/**
 * A demo for JSplitPaneWithZeroSizeDividerDemo.
 */
public class JSplitPaneWithZeroSizeDividerDemo
{
	public static void main( String[] args ) {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch( Exception ex ) {
			ex.printStackTrace();
		}

		// create left and right components
		JComponent left = new JScrollPane( new JTree() );
		JComponent right = new JScrollPane( new JList(
			new String[] { "white", "black", "gray", "red", "green", "blue" } ) );

		// remove borders from scroll panes
		left.setBorder( null );
		right.setBorder( null );

		// create split pane
		JSplitPaneWithZeroSizeDivider splitPane = new JSplitPaneWithZeroSizeDivider();
		splitPane.setBorder( null );
		splitPane.setDividerLocation( 200 );
		splitPane.setLeftComponent( left );
		splitPane.setRightComponent( right );

		// create frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add( splitPane );
		frame.setSize( 400, 300 );
		frame.setVisible( true );
	}
}
