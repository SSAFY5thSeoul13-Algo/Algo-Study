package week25.boj1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소_스패닝_트리_Prim {
	static int V;
	static int E;
	static boolean[] visited;
	static ArrayList<Node>[] arr;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		public Node(int vertex,int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new boolean[V+1];
		arr = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			arr[i] = new ArrayList<>();
		}

		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			arr[from].add(new Node(to,weight));
			arr[to].add(new Node(from,weight));
		}
		
		int answer = 0;
		int count = 0;
		
		// 1번 노드 부터 탐색 시작 
		pq.add(new Node(1,0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.vertex])	continue;
			
			visited[cur.vertex] = true;
			
			answer += cur.weight;
			
			for (Node node : arr[cur.vertex]) {
				if(!visited[node.vertex]) {
					pq.add(node);
				}
			}
			// 모든 정점이 선택되었다면 break
			if(++count == V)	break;
		}
		
		System.out.println(answer);

	}

}
