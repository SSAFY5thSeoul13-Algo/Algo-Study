package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17071_숨바꼭질5_홀짝 {
	static class Edge{
		int v;
		int time;

		public Edge(int v, int time) {
			super();
			this.v = v;
			this.time = time;
		}

		
	}
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //동생 위치
		

		if(N==K) {
			System.out.println(0);
			return;
		}

		
		//1. 수빈
		//수빈이는 걷거나 순간이동을 할 수 있다. 
		//만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
		//순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
		
		
		//2. 동생
		//동생은 매초마다 이동하며, 이동에는 가속이 붙는다.
		//동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다.
		int dist[][] = new int[500001][2];
		for (int i = 0; i <=500000 ; i++) {
			dist[i][0] = dist[i][1] = -1;
		}
		
		Queue<Edge> q = new LinkedList<>();
		dist[N][0] = 0;
		q.add(new Edge(N,0));
		
		while(!q.isEmpty()) {
			
			Edge e= q.poll();
			int v = e.v;
			int t = e.time;
			
			int nv = v+1;
			if(nv<=500000) {
				if(dist[nv][1-t]==-1) {
					dist[nv][1-t] = dist[v][t] + 1;
					q.add(new Edge(nv, 1-t));
				}
			}
			nv = v-1;
			if(nv>=0) {
				if(dist[nv][1-t]==-1) {
					dist[nv][1-t] = dist[v][t] + 1;
					q.add(new Edge(nv, 1-t));
				}
			}
			
			nv = 2*v;
			if(nv<=500000) {
				if(dist[nv][1-t]==-1) {
					dist[nv][1-t] = dist[v][t] + 1;
					q.add(new Edge(nv, 1-t));
				}
			}
		}
		
		
		int ans = 0;
		for (int t = 0; ; t++) {
			K += t;
			if(K>500000)break;
			if(dist[K][t%2]<=t) {
				ans = t;
				break;
			}
		}
		System.out.println(ans);
		
	}
}
