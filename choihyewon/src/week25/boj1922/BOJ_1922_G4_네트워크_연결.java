package week25.boj1922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_G4_네트워크_연결 {
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from,int to,int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 모든 간선들의 가중치를 오름차순으로 정렬 
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}
	static int N,M;
	static int parents[];
	static Edge[] edgeList;
	
	static int findSet(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		edgeList = new Edge[M];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(a,b,c);
		}
		
		// 모든 간선들의 가중치를 오름차순으로 정렬.
		Arrays.sort(edgeList);
		
		// 자기자신을 부모로 갖는 set을 만든다.
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int count = 0;
		
		for (Edge e : edgeList) {
			// 사이클이 발생하지 않는다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count == N-1) {
					break;
				}
			}
		}
			
		System.out.println(result);


	}
	

}
