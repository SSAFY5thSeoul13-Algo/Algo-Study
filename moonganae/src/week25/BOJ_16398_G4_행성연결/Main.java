package week25.BOJ_16398_G4_행성연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] cost;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(kruskal());
	}
	
	static long kruskal() {
		List<Edge> edge = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				edge.add(new Edge(i,j,cost[i][j]));
			}
		}
		
		
		// Union & Find 초기화
		parent = new int[N];
		for(int i=1; i<N; i++) {
			parent[i] = i;
		}
		
		Collections.sort(edge);
		
		long totalCost = 0;
		
		for(Edge e : edge) {
			// 같은 트리가 아니라면
			if(find(e.from) != find(e.to)) {
				// 결합
				union(e.from, e.to);
				totalCost += e.w;
			}
		}
		
		
		return totalCost;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, w;
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o1) {			
			return this.w - o1.w;
		}
	}
	
}


