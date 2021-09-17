package week26.BOJ_1504_G4_특정한최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_G4_특정한최단경로 {
	static int N, E, pointOne, pointTwo;
	static final int INF = 200000*1000+1;
	static int[] distance;
	static boolean[] isVisited;
	static List<List<Node>> list;
	
	static class Node implements Comparable<Node>{
		int index;
		int dis;
		
		public Node(int index, int dis) {
			super();
			this.index = index;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		distance = new int[N+1];
		isVisited = new boolean[N+1];
		list = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new Node(to, dis));
			list.get(to).add(new Node(from, dis));
		}
		
		st = new StringTokenizer(br.readLine());
		
		pointOne = Integer.parseInt(st.nextToken());
		pointTwo = Integer.parseInt(st.nextToken());
		
		//1 -> 경유지1
		int startToOne = dijkstra(1, pointOne);
		//1 -> 경유지2
		int startToTwo = dijkstra(1, pointTwo);
		//경유지1 <-> 경유지2
		int pointToPoint = dijkstra(pointOne, pointTwo);
		//경유지1 -> 목적지
		int oneToEnd = dijkstra(pointOne, N);
		//경유지2 -> 목적지
		int twoToEnd = dijkstra(pointTwo, N);
		
		//경로1
		int result1 = startToOne + pointToPoint + twoToEnd;
		//경로2
		int result2 = startToTwo + pointToPoint + oneToEnd;
		
		//경로가 없는 경우
		if(result1 >= INF && result2 >= INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Math.min(result1, result2));
	}
	
	static int dijkstra(int start, int end) {
		Arrays.fill(distance, INF);
		Arrays.fill(isVisited, false);
		distance[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(isVisited[node.index])	continue;
			
			isVisited[node.index] = true;
			
			for (Node nextNode : list.get(node.index)) {
				if(!isVisited[nextNode.index] && distance[nextNode.index] > nextNode.dis + node.dis) {
					distance[nextNode.index] = nextNode.dis + node.dis;
					pq.offer(new Node(nextNode.index, distance[nextNode.index]));
				}
				
			}
		}
		
		return distance[end];
	}

}
