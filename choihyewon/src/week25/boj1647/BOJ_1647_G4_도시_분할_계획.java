package week25.boj1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647_G4_도시_분할_계획 {
	static int N,M;
	static int[] parents;
	static Edge[] EdgeList;
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		EdgeList = new Edge[M];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			EdgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(EdgeList);

		int result = 0;
		int count = 0;
		
		for (Edge e : EdgeList) {
			if(union(e.from,e.to)) {
				result += e.weight;
				// 도시를 분할하므로 N-2만큼 간선이 count되면 break
				if(++count == N-2)	break;
			}
		}
		
		System.out.println(result);
		

	}

}
