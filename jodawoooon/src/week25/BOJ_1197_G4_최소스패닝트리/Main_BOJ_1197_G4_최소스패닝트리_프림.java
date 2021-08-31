package week25.BOJ_1197_G4_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1197 최소 스패닝 트리
 * @Author : Daun JO
 * @Date : 2021. 8. 31. 
 * @Algo : MST (프림)
 *
 */
public class Main_BOJ_1197_G4_최소스패닝트리_프림 {
	
	static class Node implements Comparable<Node> {
		int to;
		int cost;


		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}



		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	static int V, E, ans;
	static boolean[] visited;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		
		visited = new boolean[V+1];
		nodeList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//from번 정점과 to번 정점이 가중치 cost인 간선으로 연결되어 있다
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));
		}
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()){
			Node n = pq.poll();
			int to = n.to;
			int cost = n.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			ans += cost;
			
			for(Node next : nodeList[to]) {
				if(!visited[next.to]) pq.add(next);
			}
		}
		
		System.out.println(ans);
	}
	


}
