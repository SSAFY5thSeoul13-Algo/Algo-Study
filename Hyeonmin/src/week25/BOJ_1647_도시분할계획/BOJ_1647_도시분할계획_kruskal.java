package week25.BOJ_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획_kruskal {
	static int N, M, count, sum;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int numOne;
		int numTwo;
		int weight;
		
		public Edge(int numOne, int numTwo, int weight) {
			super();
			this.numOne = numOne;
			this.numTwo = numTwo;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(from, to, weight));
		}
		
		while(!pq.isEmpty()) {
			//그룹이 2개여야 하니까 MST에서 간선 1개를 뺀 경우 중지
			if(count == N-2)
				break;
			
			Edge edge = pq.poll();
			
			//두 그룹이 합쳐지는 경우
			if(union(edge.numOne, edge.numTwo)) {
				sum+= edge.weight;
				count++;
			}
		}
		
		System.out.println(sum);

	}
	
	static int findParent(int idx) {
		if(idx == parents[idx])
			return idx;
		
		//최상단 부모를 저장
		return parents[idx] = findParent(parents[idx]);
	}
	
	//두 그룹을 합침
	static boolean union(int idx1, int idx2) {
		int parentOne = findParent(idx1);
		int parentTwo = findParent(idx2);
		
		if(parentOne == parentTwo)
			return false;
		
		if(idx1 > idx2)
			parents[parentOne] = parentTwo;
		else
			parents[parentTwo] = parentOne;
		
		return true;
	}

}
