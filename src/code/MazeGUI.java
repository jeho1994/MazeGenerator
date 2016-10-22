/*
 * Assignment 5 Maze Generator.
 * 
 *  Louis Yang.
 *  GUI Extra Credit Completed 
 */
package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * MazeGui for the Maze Program.
 * 
 * @author Louis Yang.
 * @version 1.0
 */
public class MazeGUI extends JFrame {
	/** The serialization number for the frame. */
	private static final long serialVersionUID = 8968383636931297470L;
	/** The GUI icon. */
	public static final ImageIcon FRAME_IMAGE = new ImageIcon("/uwseal.gif");
	/** Panel for the GUI. */
	private MazePanel myPanel;
	
	/**
	 * Constructor for the MazeGUI program.
	 * 
	 * @param maze representation.
	 */
	public MazeGUI(char[][] maze) {
		super();
		myPanel = new MazePanel(maze);
		guiSetup();
	}
	
	/** Sets up the GUI. */
	private void guiSetup() {
		this.setIconImage(FRAME_IMAGE.getImage());
		this.add(myPanel, BorderLayout.CENTER);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        /** A ToolKit. */
        final Toolkit kit = Toolkit.getDefaultToolkit();    
        //centers the program in the center
        this.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - this.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - this.getHeight() / 2));
        this.pack();
        this.setVisible(true);
	}
	
	/** Panel Inner Class used for the MazeGUI. */
	private class MazePanel extends JPanel {
		/** Serialization for the panel. */
		private static final long serialVersionUID = -7498312592503338952L;
		/** Panel width. */
		private int myPanelWidth;
		/** Panel height. */
		private int myPanelHeight;
		/** Panel dimension. */
		private Dimension myDimension;
		/** Maze representation. */
		private char[][] myMaze;
		
		/**
		 * MazePanel constructor.
		 * 
		 * @param maze the maze representaion.
		 */
		private MazePanel(char[][] maze) {
			myPanelWidth = maze[0].length;
			myPanelHeight = maze.length;
			myDimension = new Dimension(myPanelWidth * 22, myPanelHeight * 22);
			myMaze = maze;
			setup();
		}
		
		/** Sets up the panel. */
	    private void setup() {
	    	this.setSize(myDimension);
	        this.setMinimumSize(myDimension);
	        this.setPreferredSize(myDimension);
	        this.setBackground(Color.DARK_GRAY);
	    }
	    /** 
	     * Prints the maze and components of the panel.
	     * 
	     * @param theGraphics the graphics.  
	     */
		@Override
		public void paintComponent(final Graphics theGraphics) {
			super.paintComponent(theGraphics);
	        final Graphics2D g2d = (Graphics2D) theGraphics;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                             RenderingHints.VALUE_ANTIALIAS_ON);
	        final double width = myDimension.getWidth() / myPanelWidth;
	        final double height = myDimension.getHeight() / myPanelHeight;
	        g2d.setPaint(Color.WHITE);
	        for (int row = 0; row < myPanelWidth; row ++) {
	        	for (int column = 0; column < myPanelHeight; column++) {
	        		if (myMaze[row][column] != ' ') {
	        			final Shape rectangle = new Rectangle2D.Double(column * width, 
        						row * height, width, height);
	        			if(myMaze[row][column] != 'X') {
	        				g2d.setPaint(Color.RED);
	        			} 
	        			g2d.fill(rectangle);
	        			g2d.setPaint(Color.WHITE);
	        		}
	        	}
	        }
	        g2d.setPaint(Color.black);
	        g2d.drawRect(0, 0, (int) myDimension.getWidth(),
                    (int) myDimension.getHeight());
		}
	}
}
