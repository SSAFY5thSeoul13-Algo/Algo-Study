package week25.BOJ_16398_G4_행성연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 16398 행성연결
 * @Author : Daun JO
 * @Date : 2021. 9. 6. 
 * @Algorithm : MST (Kruskal)
 *
 */
public class Main_BOJ_16398_G4_행성연결 {
	
	static int N, parents[];
	static long ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	static class Edge implements Comparable<Edge>{
		
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //행성의 수

		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i,j, cost));
			}
		}
		
		Collections.sort(edgeList);
		
		make();
		
		int cnt = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				ans += edge.cost;
				cnt++;
				
				if(cnt==N-1) break;
			}
		}
		
		System.out.println(ans);
		
	}
	private static boolean union(int from, int to) {
		int fRoot = findSet(from);
		int tRoot = findSet(to);
		
		if(fRoot==tRoot) return false;
		parents[tRoot] = fRoot;
		return true;
	}
	private static int findSet(int v) {
		if(parents[v]==v) return v;
		return parents[v] = findSet(parents[v]);
	}
	private static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}
