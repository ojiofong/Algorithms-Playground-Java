package zzz;

import java.util.ArrayList;
import java.util.List;

public class Test {

	static ArrayList<String> paths = new ArrayList<String>();
	static ArrayList<String> pathsNN = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("yes boss");
		Test solu = new Test();

		List<String> list1 = robotPaths(2);
		List<String> list2 = solu.robotPaths2(2);
		System.out.println(list1.toString() + " size: " + list1.size());
		System.out.println(list2.toString() + " size: " + list2.size());

		// final int start_Point = 0;
		// int grid_Height = 2 - 1;
		// int grid_Width = 2 - 1;
		//
		// getUnique(grid_Height, grid_Width, start_Point, start_Point, "");
		// printPaths();
		//
		 int x1 = 0;
		 int y1 = 0;
		 int x2 = 1;
		 int y2 = 1;
		 int nn = paths(x1, y1, x2, y2);
		 System.out.println("nn size: " + nn);
		 System.out.println("nn data: " + pathsNN.toString());

	}

	public static List<String> robotPaths(int n) {
		List<String> pathList = new ArrayList<String>();
		getPaths(n, 1, 1, "", pathList);
		return pathList;
	}

	public static void getPaths(int n, int i, int j, String path, List<String> pathList) {
		path += String.format(" (%d,%d) ", i, j);
		if (i == n && j == n) { // reach the (n,n) point
			pathList.add(path);
		} else if (i > n || j > n) {// wrong way
			return;
		} else {
			getPaths(n, i + 1, j, path, pathList);
			getPaths(n, i, j + 1, path, pathList);
		}
	}

	////////////////////
	public List<String> robotPaths2(int n) {
		// n= 3;
		List<String> pathList = new ArrayList<String>();
		getPaths2(n - 1, 0, 0, 1, 0, "", pathList);
		return pathList;
	}
	
	public void getPaths2(int n, int i, int j, int ey, int ex, String path, List<String> pathList) {

		path += String.format(" (%d,%d) ", i, j);

		if (i == ey && j == ex) { // reach the (n,n) point
			pathList.add(path);
		} else if (i > n || j > n) {// wrong way
			return;
		} else if (i < 0 || j < 0) {// wrong way
			return;
		} else {
			getPaths(ey, i + 1, j, path, pathList);
			getPaths(ex, i, j + 1, path, pathList);
			if (i - 1 >= 0)
				getPaths(n, i - 1, j, path, pathList);
			if (j - 1 >= 0)
				getPaths(n, i, j - 1, path, pathList);
		}

	}

	public void getPaths2(int n, int i, int j, String path, List<String> pathList) {

		path += String.format(" (%d,%d) ", i, j);

		if (i == n && j == n) { // reach the (n,n) point
			pathList.add(path);
		} else if (i > n || j > n) {// wrong way
			return;
		} else if (i < 0 || j < 0) {// wrong way
			return;
		} else {
			getPaths(n, i + 1, j, path, pathList);
			getPaths(n, i, j + 1, path, pathList);
			if (i - 1 >= 0)
				getPaths(n, i - 1, j, path, pathList);
			if (j - 1 >= 0)
				getPaths(n, i, j - 1, path, pathList);
		}

	}

	/*****
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * **/
	public static long getUnique(int m, int n, int i, int j, String pathlist) {

		pathlist += ("(" + i + ", " + (j) + ") => ");

		if (m == i && n == j) {
			paths.add(pathlist);
		}

		if (i > m || j > n) {
			return 0;
		}

		return getUnique(m, n, i + 1, j, pathlist) + getUnique(m, n, i, j + 1, pathlist);

	}

	public static void printPaths() {
		int count = 1;
		System.out.println("There are " + paths.size() + " unique paths: \n");

		for (int i = paths.size() - 1; i >= 0; i--) {

			System.out.println("path " + count + ":   " + paths.get(i));
			count++;
		}

	}

	static String s = "";
	static boolean first = true;

	public static int paths(int x1, int y1, int x2, int y2) {
		// if (!first)
		s += String.format("(%d,%d) ", x2, y2);
		// first = false;

		if ((x2 == x1 && y2 != y1) || (x2 != x1 && y2 == y1)) {
			pathsNN.add(s);
			return 1;
		} else {
			return paths(x1, y1, x2 - 1, y2) + paths(x1, y1, x2, y2 - 1);
		}
	}

}
