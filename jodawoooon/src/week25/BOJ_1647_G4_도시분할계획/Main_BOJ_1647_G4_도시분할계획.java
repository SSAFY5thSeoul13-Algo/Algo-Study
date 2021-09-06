package week25.BOJ_1647_G4_도시분할계획;

import java.io.*;
import java.util.*;
/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1647 G4 도시분할계획
 * @Author : Daun JO
 * @Date : 2021. 9. 3. 
 *
 */
public class Main_BOJ_1647_G4_도시분할계획 {
	

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
	static int N, M, parents[], ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
	
		for(int i = 0; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			//A번 집과 B번 집을 연결하는 길의 유지비가 C
			
			edgeList.add(new Edge(A,B,C));
			
		}

		Collections.sort(edgeList);
		make();
		
		
		int max = 0;
		int cnt = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge .from, edge .to)){
				
				max = Math.max(edge.cost, max); //가장 큰 가중치값 구하기
				
				ans += edge.cost;
				cnt++;
				
				if(cnt==N-1) break;
			}
		}
		
		ans -= max;
		System.out.println(ans);

	}
	
	
	private static boolean union(int from, int to) {
		
		int fromRoot = findSet(from);
		int toRoot = findSet(to);
		
		if(fromRoot==toRoot) return false;
		else parents[toRoot] = fromRoot;
		return true;
	}

	private static int findSet(int v) {
		
		if(parents[v]==v) return v;
		else return parents[v] = findSet(parents[v]);
	}

	private static void make() {
		for(int i = 1 ; i <= N ; i++) {
			parents[i] = i;
		}
	}
}
