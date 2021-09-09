package week25.BOJ_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획_prim {
	static int N, M, count, sum, max;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[] isVisited;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
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
		
		isVisited = new boolean[N+1];
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
		}
		
		//시작하는 노드를 잡기 위한 데이터
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 집이 연결된 경우
			if(count == N)
				break;
			
			Edge edge = pq.poll();
			
			//이미 선택된 노드로의 간선은 제외
			if(isVisited[edge.to])
				continue;
			
			//노드 방문처리
			isVisited[edge.to] = true;
			
			//비용과 선택한 노드 수정
			sum+= edge.weight;
			count++;
			max = Math.max(max, edge.weight);
			
			//선택한 노드에서 이어진 간선들에 대한 처리
			for (Edge nextEdge : graph.get(edge.to)) {
				if(isVisited[nextEdge.to])	continue;
				
				pq.offer(nextEdge);
			}
		}
		
		//MST에서 가장 비용이 큰 간선 하나의 가중치를 제외한 경우를 출력
		System.out.println(sum - max);

	}

}
