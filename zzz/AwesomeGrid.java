package zzz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * For this solution there are 4n number of possible moves - right, left, up and down.
 * Using Binomial Coefficient the number of ways of choosing n unordered outcomes from 4n possibilities is
 * 4n choose n 4nCn = (4n)!/n!(4n-n)!.
 * 
 * Given that how does one select a path from so many possible ways? I decided to go with the shortest distance
 * between points on a 2d grid represented as an adjacency matrix. 
 * 
 * In my approach I'm using Breath First Search to traverse through every coordinate in search of possible paths.
 * Each cell in the matrix is represented as a Node or vertex of a graph. 
 * 
 * Two vertices are neighbors if they are adjacent to each other.
 * My approach uses O(2n) space to fill a 2d char[][] with a dummy value e.g. '1'.
 * 
 * This allows me to mark each coordinate within a given path with 'X' 
 * to avoid revisiting or crossing out the coordinate.
 * 
 * The start node is added to a Queue and marked as visited and subsequently its neighbors too
 * in a horizontal order before moving vertically. 
 * 
 * The BFS traversal run time is O(V + E), where V is the number of vertices and E is the number of edges.
 * 
 * However, my modified BFS algorithm program will run at worst case O(n^2 * V + E) time complexity, 
 * where n is the number rows and columns in the n X n matrix.
 * 
 * The total space complexity is O(V^2) because the graph is represented as an adjacency matrix. Here V is the number of vertices.
 * 
 * I have put together a fairly demonstrable code below. I included some comments to improve readability.
 * 
 * 
 * 
 * */
public class AwesomeGrid {

	static char[][] GRID;
	static final char BLOCKED = 'X';

	public static void main(String[] args) {
		int n = 5; // n x n matrix
		int rows = n;
		int cols = n;
		initializeGrid(rows, cols);

		// Call this multiple times with desired start and end target
		// Note - Node(x,y) is Node(row, column)
		shortestPath(GRID, rows, cols, new Node(0, 0), new Node(3, 3));
		shortestPath(GRID, rows, cols, new Node(0, 0), new Node(3, 3));
		shortestPath(GRID, rows, cols, new Node(0, 0), new Node(3, 3));
		shortestPath(GRID, rows, cols, new Node(0, 2), new Node(2, 4));
		
		// Prints the current matrix
		printGrid();

	}

	private static void initializeGrid(int rows, int cols) {
		GRID = new char[rows][cols];
		for (int r = 0; r < GRID.length; r++) {
			for (int c = 0; c < GRID[0].length; c++) {
				GRID[r][c] = '1';
			}
			System.out.println(" ");
		}
	}

	private static void printGrid() {
		System.out.println("");
		for (int i = 0; i < GRID.length; i++) {
			for (int c = 0; c < GRID[0].length; c++) {
				System.out.print(GRID[i][c] + " ");
			}
			System.out.println(" ");
		}
	}

	public static List<Node> shortestPath(char[][] grid, int rows, int cols, Node start, Node end) {

		boolean[][] visited = new boolean[rows][cols];
		Node[][] parent = new Node[rows][cols];
		List<Node> path = new ArrayList<>();

		Queue<Node> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			Node curr = (Node) queue.remove();
			visited[curr.x][curr.y] = true;

			// If we've gotten to the target End target
			// We'll iterate through the parent nodes to get the current path
			// We can have multiple paths but we will ignore long paths
			if (curr.x == end.x && curr.y == end.y) {
				List<Node> currPath = new ArrayList<>();
				while (parent[curr.x][curr.y] != start) {
					curr = parent[curr.x][curr.y];
					currPath.add(curr);
				}

				if (currPath.size() < Integer.MAX_VALUE) {
					path.clear();
					path.addAll(currPath);
				}
			}

			// Always keep track of the parent coordinates

			// if we can move to right column
			if (curr.y + 1 < cols && grid[curr.x][curr.y + 1] != BLOCKED && !visited[curr.x][curr.y + 1]) {
				queue.add(new Node(curr.x, curr.y + 1));
				parent[curr.x][curr.y + 1] = curr;
			}

			// if we can move to left column
			if (curr.y - 1 >= 0 && grid[curr.x][curr.y - 1] != BLOCKED && !visited[curr.x][curr.y - 1]) {
				queue.add(new Node(curr.x, curr.y - 1));
				parent[curr.x][curr.y - 1] = curr;
			}

			// if we can move up the row
			if (curr.x - 1 >= 0 && grid[curr.x - 1][curr.y] != BLOCKED && !visited[curr.x - 1][curr.y]) {
				queue.add(new Node(curr.x - 1, curr.y));
				parent[curr.x - 1][curr.y] = curr;
			}

			// if we can move down the row
			if (curr.x + 1 < rows && grid[curr.x + 1][curr.y] != BLOCKED && !visited[curr.x + 1][curr.y]) {
				queue.add(new Node(curr.x + 1, curr.y));
				parent[curr.x + 1][curr.y] = curr;
			}
		}

		// Now if we have a valid path let's update the coordinates to Blocked or mark 'X' to
		// avoid revisiting or crossing out
		// if path is empty then we cannot proceed.
		if (!path.isEmpty()) {
			for (Node p : path) {
				String s = String.format(" (%d,%d) ", p.x, p.y);
				GRID[p.x][p.y] = BLOCKED;
				System.out.print(s);
			}
			// Also block crossing through the start and end coordinates 
			GRID[start.x][start.y] = BLOCKED;
			GRID[end.x][end.y] = BLOCKED;
		} else {
			// if path is empty then we cannot proceed.
			String s = String.format(" (%d,%d) ", start.x, start.y);
			String e = String.format(" (%d,%d) ", end.x, end.y);
			System.out.print("\nStuck! Cannot move from " + s + " to " + e + "\n");

		}
		


		return path;

	}

	public static class Node {
		public int x;
		public int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}