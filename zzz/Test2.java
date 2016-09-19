package zzz;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		directorySolution(getSampleListing());

	}

	/*-
	 * Given a listing of files and directories in a file system
	 * as a String separated by new lines and space indentation
	 * 
	 * dir1
	 *  dir11
	 *  dir12
	 *   picture.jpg
	 *   dir121
	 *   file1.txt
	 * dir2
	 *  file2.gif
	 *  
	 * Find the longest absolute path to an image file (.jpg or .gif)
	 * From sample /dir1/dir12/picture.jpg is 24 characters long
	 * 
	 * */
	private static int directorySolution(String str) {
		int max = 0;
		String[] arr = str.split("\n");

		System.out.println(str);
		System.out.println(Arrays.toString(arr));
		System.out.println(isImage("test.jpg"));

		// Algorithm is to read from reverse once we find an image
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			if (isImage(s)) {
				int indentCount = indentCount(s);
				int loopTime = indentCount;
				int index = i;
				StringBuilder sb = new StringBuilder();
				sb.append(s.trim());
				while (loopTime > 0) {
					index--;
					int parentIndent = indentCount(arr[index]);
					if (parentIndent == indentCount)
						continue;

					sb.append("/").append(arr[index].trim());

					loopTime--;
				}
				sb.append("/");
				System.out.println(sb.toString());

				max = Math.max(max, sb.length());

				System.out.println("Max Directory " + max);
			}
		}

		return max;
	}

	private static String getSampleListing() {
		StringBuilder sb = new StringBuilder();
		sb.append("dir1").append("\n");
		sb.append(" dir11").append("\n");
		sb.append(" dir12").append("\n");
		sb.append("  picture.jpg").append("\n");
		sb.append("  dir121").append("\n");
		sb.append("  file1.txt").append("\n");
		sb.append("dir2").append("\n");
		sb.append(" file2.gif");

		return sb.toString();
	}

	private static int indentCount(String str) {
		return str.length() - str.replace(" ", "").length();
	}

	private static String ext(String str) {
		if (!str.contains("."))
			return null;
		return str.substring(str.lastIndexOf("."), str.length());
	}

	private static boolean isImage(String str) {
		return ".jpg".equals(ext(str)) || ".gif".equals(ext(str));
	}

}
