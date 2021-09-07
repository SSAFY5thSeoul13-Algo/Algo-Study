package week25.boj1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소_스패닝_트리_Kruskal {
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
			// TODO Auto-generated method stub
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
		
		if(aRoot==bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	static int V,E;
	static int[] parents;
	static Edge[] EdgeList;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		EdgeList = new Edge[E];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			EdgeList[i] = new Edge(from,to,weight);
		}
		
		Arrays.sort(EdgeList);
		
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int count = 0;
		
		for (Edge e : EdgeList) {
			// 싸이클이 발생한다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count==E-1) {
					break;
				}
			}
		}
		
		System.out.println(result);

	}

}
