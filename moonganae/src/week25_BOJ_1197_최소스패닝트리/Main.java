package week25_BOJ_1197_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static ArrayList<Edge>[] adj;
	static PriorityQueue<Edge> dist = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge>{
		int to, w;

		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w == o.w) return this.to - o.to;
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b,c));
			adj[b].add(new Edge(a,c));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		int ans = 0;
		
		boolean[] selected = new boolean[V+1];
		
		for(int i=1; i<=V; i++) {
			selected[i] = false;
		}
		
		// 초기정점 설정하기
		selected[1] = true;
		for(Edge edge : adj[1]) {
			dist.offer(edge);
		}
		
		for(int i=1; i<V; i++) {
			
			int now =-1;
			int minDist = Integer.MAX_VALUE;
			
			while(!dist.isEmpty()) {
				now = dist.peek().to;
				
				// 선택하지 않은 정점인경우
				if(!selected[now]) {
					minDist = dist.peek().w;
					break;
				}
				dist.poll();
			}
			
			// MST를 만들수없는 경우
			if(minDist == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			
			// MST 설정하기
			ans += minDist;
			selected[now] = true;
			
			for(Edge edge : adj[now]) {
				dist.offer(edge);
			}
		}
		
		return ans;
	}	
}
