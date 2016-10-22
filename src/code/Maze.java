/*
 * Assignment 5 Maze Generator.
 * 
 *  Louis Yang, Michael Wilson.
 *  GUI Extra Credit Completed 
 */
package code;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Maze Generator for the Maze Program.
 * 
 * @author Louis Yang, Michael Wilson.
 * @version 1.0
 */
public class Maze {
	/** Maze row. */
	private int myRow;
	/** Maze column. */
	private int myColumn;
	/** Debug mode on. */
	private boolean myDebug;
	/** Maze representation. */
	private char[][] myMaze;
	/** Maze visited vertices representation. */
	private boolean[][] myCanVisit;
	/** Used for the DFS Search Algorithm. */
	private Stack<Point> myStack;
	/** List of all the vertices in the maze. */
	private ArrayList<Point> myVertices;
	
	/**
	 * Constructor for the Maze Class.
	 * 
	 * @param width of the Maze. 
	 * @param depth of the Maze.
	 * @param debug mode of the maze on.
	 */
	public Maze(int width, int depth, boolean debug) {
		myRow = depth * 2 + 1;
		myColumn = width * 2 + 1;
		myDebug = debug;
		myMaze = new char[myRow][myColumn];
		myCanVisit = new boolean[myRow][myColumn]; 
		myStack = new Stack<Point>();
		myVertices = new ArrayList<Point>();
		makeMaze();
		makePaths();
		markSolutionPath();
	}
	
	/** Prints outs the maze for visualization. */
	public void display() {
		for (int row = 0; row < myRow; row++) {
			for (int column = 0; column < myColumn; column++) {
				System.out.print(myMaze[row][column] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
	
	/** 
	 * Gets the maze representation.
	 *
	 * @return the maze representation.
	 */
	public char[][] getMaze() {
		return myMaze;
	}
	
	/** Makes the maze. */
	private void makeMaze() {
		for (int row = 0; row < myRow; row++) {
			for (int column = 0; column < myColumn; column++) {
				if(row % 2 == 0 || column % 2 == 0) {
					myMaze[row][column] = 'X';
					myCanVisit[row][column] = false;
				} else {
					myMaze[row][column] = ' ';
					myCanVisit[row][column] = true;
					myVertices.add(new Point(row, column));
				}
			}
		}
		//indicates the entrance and exit.
		myMaze[0][1] = ' ';
		myMaze[myRow - 1][myColumn - 2] = ' ';
	}
	
	/** Makes the randomized paths and stores the solution to the maze. */
	private void makePaths() {
		Random rand = new Random();
		Point currentVertice = new Point(1,1);
		insertVistedVertice(currentVertice);
		Point targetPoint = new Point(myRow - 2, myColumn - 2);
		ArrayList<Point> currentValidMoves = new ArrayList<Point>();
		// Used for storing the solution of the maze.
		Stack<Point> path = new Stack<Point>();
		
		while(!myStack.isEmpty()) {
			currentValidMoves = unVisitedVertices(currentVertice);
			int validMoveOptions = currentValidMoves.size();
			if (validMoveOptions != 0) {
				if (myDebug) {
					display();
				}
				int validMovesChoice = rand.nextInt(validMoveOptions);
				Point nextVertice = currentValidMoves.get(validMovesChoice);
				removeWall(currentVertice, nextVertice);
				insertVistedVertice(nextVertice);
				currentVertice = nextVertice;
				// found the solution to the maze, lets store it.
				if (currentVertice.equals(targetPoint)) {
					path.addAll(myStack);
				}
			} else {
				myStack.pop();
				if (!myStack.isEmpty()) {
					currentVertice = myStack.peek();
				}
			}
		}
		// sets the solution. 
		myStack.addAll(path);
	}
	
	/**
	 * Gets the unvisited neighbors. 
	 * 
	 * @param the current vertices. 
	 * @return the possible neighbors left to travel.
	 */
	private ArrayList<Point> unVisitedVertices(Point vertice) {
		ArrayList<Point> unvisitedVertices = new ArrayList<Point>();
		int x = (int) vertice.getX();
		int y = (int) vertice.getY();
		// checks the top neighbor.
		if (x - 2 >= 1) {
			if (myCanVisit[x - 2][y]) {
				unvisitedVertices.add(new Point(x - 2, y));
			}
		}
		// checks the bottom neighbor.
		if (x + 2 < myRow) {
			if (myCanVisit[x + 2][y]) {
				unvisitedVertices.add(new Point(x + 2, y));
			}
		}
		// checks the left neighbor.
		if (y - 2 >= 1) {
			if (myCanVisit[x][y - 2]) {
				unvisitedVertices.add(new Point(x, y - 2));
			}
		}
		// checks the right neighbor.
		if (y + 2 < myColumn) {
			if (myCanVisit[x][y + 2]) {
				unvisitedVertices.add(new Point(x, y + 2));
			}
		}
		return unvisitedVertices;
	}
	
	/**
	 * Removes the wall between the two vertices. 
	 * 
	 * @param current vertices.
	 * @param next vertices. 
	 */
	private void removeWall (Point current, Point next) {
		int x = (int)((current.getX() + next.getX()) / 2);
		int y = (int)((current.getY() + next.getY()) / 2);
		myMaze[x][y] = ' ';
	}
	
	/**
	 * Marks the visited vertices and stores it for memory. 
	 * 
	 * @param the current vertices.
	 */
	private void insertVistedVertice(Point vertice) {
		int x = (int) vertice.getX();
		int y = (int) vertice.getY();
		if (myDebug) {
			myMaze[x][y] = 'V';
		} 
		myCanVisit[x][y] = false;
		myStack.push(vertice);
		myVertices.add(vertice);
	}
	
	/** Prints the solution path. */
	private void markSolutionPath() {
		if (myDebug) {
			display();
			for(int i = 0; i < myVertices.size(); i ++) {
				int x = (int) myVertices.get(i).getX();
				int y = (int) myVertices.get(i).getY();
				myMaze[x][y] = ' ';
			}
		}
		while (!myStack.isEmpty()) {
			int x = (int) myStack.peek().getX();
			int y = (int) myStack.peek().getY();
			myMaze[x][y] = '+';
			myStack.pop();
		}
		display();
	}
}
