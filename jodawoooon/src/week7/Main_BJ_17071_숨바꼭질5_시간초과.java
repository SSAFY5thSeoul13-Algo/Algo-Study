package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_17071_숨바꼭질5_시간초과 {
	static class Edge{
		int v;
		int time;
		int k;
		int cnt;
		public Edge(int v, int time) {
			super();
			this.v = v;
			this.time = time;
		}
		public Edge(int v, int time, int k, int cnt) {
			super();
			this.v = v;
			this.time = time;
			this.k = k;
			this.cnt = cnt;
		}
		
		
		
	}
	static int N, K, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //동생 위치
		
		ans = -1;
		if(N==K) {
			System.out.println(0);
			return;
		}
		
		if(K>500000) {
			System.out.println(-1);
			return;
		}
		
		//1. 수빈
		//수빈이는 걷거나 순간이동을 할 수 있다. 
		//만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
		//순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
		
		
		//2. 동생
		//동생은 매초마다 이동하며, 이동에는 가속이 붙는다.
		//동생이 이동하는 거리는 이전에 이동한 거리보다 1을 더한 만큼 이동한다.
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.time - o2.time;
			}
			
		});
		
		pq.add(new Edge(N,0,K,1));
		
		while(!pq.isEmpty()) {
			
			Edge e= pq.poll();
			int v = e.v;
			int t = e.time;
			int k = e.k;
			int cnt = e.cnt;

			
			if(v==k) {
				ans = t;
				break;
			}
			//if(time[v] < t) continue;
			//경우의수 1. 걷는다 => X-1 :1초
			
			if(k>500000) {
				continue;
			}
			
			
			int nv = 0;
			int nt = 0;

				//수빈 - X-1
				nv = v - 1;
				nt = t + 1;
				if(nv>=0) {
					//System.out.println(nv+" "+(k+cnt));
					pq.add(new Edge(nv, nt, k+cnt, cnt+1));
				}
				
				//수빈 - X+1
				nv = v + 1;
				nt = t + 1;
				if(nv<=500000) {
					//System.out.println(nv+" "+(k+cnt));
					pq.add(new Edge(nv, nt, k+cnt, cnt+1));
				}
				
				
				//수빈 - 순간이동
				nv = 2*v;
				nt = t+1;
				if(nv<=500000) {
					//System.out.println(nv+" "+(k+cnt));
					pq.add(new Edge(nv, nt, k+cnt, cnt+1));
				}
				

		}
		System.out.println(ans);
		
	}
}
