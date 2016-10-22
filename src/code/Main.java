/*
 * Assignment 5 Maze Generator.
 * 
 *  Louis Yang, Michael Wilson.
 *  GUI Extra Credit Completed 
 */
package code;

/**
 * Maze Program Executor.
 * 
 * @author Louis Yang
 * @version 1..0
 */
public class Main {

	/**
	 * Maze and GUI executor. 
	 * 
	 * @param args the args is not used for this program.
	 */
	public static void main(String[] args) {
		Maze maze = new Maze(20, 20, true);
		new MazeGUI(maze.getMaze());
		//testMaze();
	}
	
//	/** Test method for the Maze Program. */
//	private static void testMaze() {
//      display() method is tested inside the maze class so no need to call it from here.
//		new Maze(4, 3, true);
//		new Maze(3, 4, false);
//		new Maze(20, 20, true);
//		new Maze(20, 20, false);
//	}
}
