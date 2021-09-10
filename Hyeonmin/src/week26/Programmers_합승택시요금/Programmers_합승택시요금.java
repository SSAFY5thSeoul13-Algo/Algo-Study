package week26.Programmers_합승택시요금;

import java.util.*;

public class Programmers_합승택시요금 {
	//연결되어 있는 경로 정보
	static List<List<Node>> list;
	//각 지점에서 나머지 까지의 거리
	static int[] disA, disB, disS;
	static final int INF = 200 * 100000 + 1;

	static class Node implements Comparable<Node> {
		int index;
		int dis;

		public Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;

		list = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<Node>());
		}

		//경로를 리스트에 저장
		for (int[] fare : fares) {
			int idx1 = fare[0];
			int idx2 = fare[1];
			int dis = fare[2];

			list.get(idx1).add(new Node(idx2, dis));
			list.get(idx2).add(new Node(idx1, dis));
		}

		disA = dijkstra(a, n);
		disB = dijkstra(b, n);
		disS = dijkstra(s, n);

		for (int i = 1; i <= n; i++) {
			answer = Math.min(disS[i] + disA[i] + disB[i], answer);
		}

		return answer;
	}

	static int[] dijkstra(int start, int n) {
		boolean[] isVisited = new boolean[n + 1];
		int[] distance = new int[n + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (isVisited[node.index])
				continue;

			isVisited[node.index] = true;

			for (Node nNode : list.get(node.index)) {
				if (!isVisited[nNode.index] && distance[nNode.index] >= nNode.dis + node.dis) {
					distance[nNode.index] = nNode.dis + node.dis;
					pq.offer(new Node(nNode.index, distance[nNode.index]));
				}
			}
		}

		return distance;
	}
}
