package week26.boj1504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_G4_특정한_최단경로 {
	static int N,E;
	static final int INF = 100_000_000;
	static List<Node>[] list;
	static int[] dist;
	static class Node implements Comparable<Node>{
		int end;
		int dis;
		
		public Node(int end, int dis) {
			this.end = end;
			this.dis = dis;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dis - o.dis;
		}
		
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		dist = new int[N+1];

		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 양방향 이므로 각각 넣어준다.
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int result1 = 0;
		int result2 = 0;
		// 1, v1, v2, N 노드 순서로 이동해야함 
		dijkstra(1);
		result1 += dist[v1];
		dijkstra(v1);
		result1 += dist[v2];
		dijkstra(v2);
		result1 += dist[N];
		
		// 1, v2, v1, N 노드 순서로 이동해야함 
		dijkstra(1);
		result2 += dist[v2];
		dijkstra(v2);
		result2 += dist[v1];
		dijkstra(v1);
		result2 += dist[N];
		
		if(result1 >= INF && result2 >= INF) {
			System.out.println("-1");
		}else {
			System.out.println(Math.min(result1, result2));
		}


	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[N+1];
		pq.add(new Node(start,0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			if(visited[cur])	continue;
			
			visited[cur] = true;
			
			for (Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.dis) {
					dist[node.end] = dist[cur] + node.dis;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
		
		
	}

}
