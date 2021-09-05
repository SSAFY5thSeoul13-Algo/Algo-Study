package week25_BOJ_G4_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Edge> edges = new ArrayList<>();
	static int[] parent;
	static class Edge implements Comparable<Edge>{
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
//			if(this.w == o.w) return this.to - o.to;
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(a,b,c));
		}
		
		Collections.sort(edges);
		
		
		System.out.println(kruskal());
	
		
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}
	
	static int kruskal() {
		int ans = 0;
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		// 선택한 간선 수
		int edgeCnt = 0;
		
		for(Edge edge : edges) {
			
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				ans += edge.w;
				edgeCnt++;
			}
			
			// 기존의 MST는 N-1개지만, MST에서 최대비용의 간선을 하나를 제거하면 N-2개의 간선을 가짐
			if(edgeCnt == N-2) break;
		}
		
		return ans;
		
	}
}
