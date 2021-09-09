package week25.BOJ_1197_G4_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소스패닝트리 {
	static int V, E, count, result;
	static boolean[] isChecked;
	static ArrayList<ArrayList<Edge>> graph;
	
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
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		//이미 선택한 노드
		isChecked = new boolean[V+1];
		//각 노드에서 이어진 간선을 저장할 리스트
		graph = new ArrayList<>();
		
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//각 간선을 리스트에 추가
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(new Edge(node2,weight));
			graph.get(node2).add(new Edge(node1,weight));
		}
		
		//시작 노드를 1번 노드로 설정
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 노드를 선택한 경우 종료
			if(count == V)
				break;
			
			Edge edge = pq.poll();
			
			//이미 선택된 노드로 향하는 간선일 경우
			if(isChecked[edge.to])
				continue;
			
			isChecked[edge.to] = true;
			result += edge.weight;
			
			//선택한 노드에서 시작되는 간선을 추가
			for (Edge nEdge : graph.get(edge.to)) {
				if(isChecked[nEdge.to])	continue;
				
				pq.offer(nEdge);
			}
			
			count++;
		}
		
		System.out.println(result);
	}

}
