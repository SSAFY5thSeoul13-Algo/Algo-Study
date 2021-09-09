package week25.BOJ_16398_G4_행성연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16398_G4_행성연결 {
	static int N, count;
	static long result;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge>{
		int nodeOne;
		int nodeTwo;
		int weight;
		
		public Edge(int nodeOne, int nodeTwo, int weight) {
			super();
			this.nodeOne = nodeOne;
			this.nodeTwo = nodeTwo;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//자기 자신에 대한 경로가 아닌 경우 pq에 저장
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				
				if(weight == 0)	continue;
				
				pq.offer(new Edge(i,j,weight));
			}
		}
		
		while(!pq.isEmpty()) {
			
			//모든 경로가 설정된 경우
			if(count == N-1)
				break;
			
			Edge edge = pq.poll();
			
			//두 노드의 최상위 부모 노드
			int parentOne = findParent(edge.nodeOne);
			int parentTwo = findParent(edge.nodeTwo);
			
			//속한 집단이 다른 경우
			if(parentOne != parentTwo) {
				result += edge.weight;
				union(parentOne, parentTwo);
				count++;
			}
		}
		
		System.out.println(result);
	}
	
	//해당 노드의 최상위 부모 노드를 리턴
	static int findParent(int idx) {
		if(parent[idx] == idx)
			return idx;
		else
			return findParent(parent[idx]);
	}
	
	//두 집단을 합침
	static void union(int idx1, int idx2) {
		parent[idx1] = idx2; 
	}
}
