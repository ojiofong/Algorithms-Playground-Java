package miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Miscellaneous
 */
public class Solution {

	public static void main(String[] args) {
		getMaxMemory(null);
		maxNoOfMeetingRooms(null);
		maxNoOfMeetingRooms2(null);
		maxNoOfMeetingRooms3(null);
	}

	static class Node {
		long start;
		long end;
		long ram;

		public Node(long start, long end, long ram) {
			this.start = start;
			this.end = end;
			this.ram = ram;
		}

		public boolean collission(Node n) {
			boolean startCollides = n.start > this.start && n.start < this.end;
			boolean endCollides = n.end > this.start && n.end < this.end;
			return startCollides || endCollides;
		}

		public boolean collissionMeeting(Node n) {
			boolean startCollides = n.start >= this.start && n.start <= this.end;
			boolean endCollides = n.end > this.start && n.end < this.end;
			return startCollides || endCollides;
		}
	}

	/*-
	 * per process RAM consumption
	|
	|            3GB
	|          |----------------------|
	| 2GB      |                      |
	|-----|    |                      |
	|     |    |          1GB         |
	|     |    |     |-----------|    |
	|     |    |     |           |    |
	|_____|____|_____|___________|____|_____time
	t0    t1   t2    t3          t4   t5
	
	Here we'll return 4GB = (3+1) max memory needed
	
	*/
	// Time- O(n^2)
	// Space average O(V + E)
	public static long getMaxMemory(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(0, 1, 2));
		list.add(new Node(2, 7, 3));
		list.add(new Node(3, 4, 1)); // expect 4
		list.add(new Node(5, 8, 2)); // expect 6

		if (list == null || list.isEmpty())
			throw new IllegalArgumentException();

		long maxRam = 0;

		// Graph - Adjacency list of neighbors with collision
		Map<Node, List<Node>> multi = new HashMap<>();

		for (Node n : list)
			System.out.print(n.end + ", ");

		// O(n^2)
		for (Node a : list) {
			for (Node b : list) {
				if (a != b && a.collission(b)) {
					List<Node> neighbors = multi.get(a);
					if (neighbors == null)
						neighbors = new ArrayList<>();

					neighbors.add(b);
					multi.put(a, neighbors);
				}
			}
		}

		// Calculate maxRam by adding collided neighbors ram
		// Space Average - O(V + E) -- worst O(V^2)
		// Time - same as "Space" for this solution
		for (Node a : list) {
			maxRam = Math.max(maxRam, a.ram);
			if (multi.containsKey(a)) {
				long sum = a.ram;
				for (Node b : multi.get(a)) {
					sum += b.ram;
				}
				maxRam = Math.max(maxRam, sum);
			}
		}

		System.out.println("\nmaxRam -> " + maxRam);

		return maxRam;
	}

	/*
	 * Given a list of meetings and times, find the max number of meeting rooms
	 * Request additional room if there's a time collision
	 */
	public static int maxNoOfMeetingRooms(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(900, 1100, 0));
		list.add(new Node(900, 1100, 0));
		list.add(new Node(1330, 1500, 0));
		list.add(new Node(730, 1200, 0));
		list.add(new Node(900, 1100, 0)); // expect 4

		if (list == null || list.isEmpty())
			return 0;

		int roomCount = 0;

		Collections.sort(list, new Comparator<Node>() {
			public int compare(Node lhs, Node rhs) {
				Long first = lhs.end;
				Long last = rhs.end;
				return first.compareTo(last);
			}
		});

		for (Node n : list)
			System.out.print(n.start + "-" + n.end + ", ");

		Queue<Node> q = new LinkedList<>();
		q.add(list.get(0));

		for (int i = 1; i < list.size(); i++) {
			Node curr = list.get(i);
			while (!q.isEmpty() && curr.start > q.peek().end) {
				q.remove();
			}
			q.add(curr);
			roomCount = Math.max(roomCount, q.size());

		}

		System.out.println("\nMax no. of rooms -> " + roomCount);

		// returns 4
		return roomCount;
	}

	public static int maxNoOfMeetingRooms2(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(900, 1100, 0));
		list.add(new Node(900, 1100, 0));
		list.add(new Node(1330, 1500, 0));
		list.add(new Node(730, 1200, 0));
		list.add(new Node(900, 1100, 0)); // expect 4

		if (list == null || list.isEmpty())
			return 0;

		long[] start = new long[list.size()];
		long[] end = new long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			start[i] = list.get(i).start;
			end[i] = list.get(i).end;
		}

		// Sort O(n log n)
		Arrays.sort(start);
		Arrays.sort(end);

		int roomCount = 1; // At least one room
		int i = 0; // index curr
		int j = 1; // index curr + 1
		int roomNeeded = 0;
		int n = list.size();

		while (i < n && j < n) {
			// collision
			if (start[i] < end[j]) {
				roomNeeded++;
				i++;
				roomCount = Math.max(roomCount, roomNeeded);
			} else {
				roomNeeded--;
				j++;
			}
		}

		for (Node node : list)
			System.out.print(node.start + "-" + node.end + ", ");

		System.out.println("\nMax no. of rooms2 -> " + roomCount);

		// returns 4
		return roomCount;
	}

	public static int maxNoOfMeetingRooms3(List<Node> list) {
		// test list data
		list = new ArrayList<>();
		list.add(new Node(900, 1100, 0));
		list.add(new Node(900, 1100, 0));
		list.add(new Node(1330, 1500, 0));
		list.add(new Node(730, 1200, 0));
		list.add(new Node(900, 1100, 0)); // expect 4

		if (list == null || list.isEmpty())
			return 0;

		// Sort by end times
		Collections.sort(list, new Comparator<Node>() {
			public int compare(Node l, Node r) {
				Long first = l.end;
				Long last = r.end;
				return first.compareTo(last);
			}
		});

		int rooms = 1;
		int count = 1;

		for (int i = 0; i < list.size() - 1; i++) {
			// if no collision reset count
			if (list.get(i).end < list.get(i + 1).start) {
				count = 1;
				System.out.println("no collison");
			} else {
				count++;
				rooms = Math.max(rooms, count);
				System.out.println("yes collison");
			}
		}


		for (Node node : list)
			System.out.print(node.start + "-" + node.end + ", ");

		System.out.println("\nMax no. of rooms3 -> " + rooms);

		// returns 4
		return rooms;
	}

}
