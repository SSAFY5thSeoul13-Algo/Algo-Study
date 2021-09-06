package week25.BOJ_1922_G4_네트워크연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1922 네트워크연결
 * @Author : Daun JO
 * @Date : 2021. 9. 6. 
 * @Algorithm : MST (Kruskal)
 *
 */

public class Main_BOJ_1922_G4_네트워크연결 {

	static int N, M, parents[], ans;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
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

		N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		M = Integer.parseInt(br.readLine()); //간선의 수

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			pq.add(new Edge(from, to, cost));

		}

		parents = new int[N+1];

		make();
		int cnt = 0;

		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
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
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

}