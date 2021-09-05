package week25.BOJ_1922_G4_네트워크연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922_G4_네트워크연결 {
	static int N, M, count, sum;
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
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		isVisited = new boolean[N+1];
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
		}
		
		//시작하는 노드를 잡기 위한 데이터
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 컴퓨터가 연결된 경우
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
			
			//선택한 노드에서 이어진 간선들에 대한 처리
			for (Edge nextEdge : graph.get(edge.to)) {
				if(isVisited[nextEdge.to])	continue;
				
				pq.offer(nextEdge);
			}
		}
		
		System.out.println(sum);

	}

}
