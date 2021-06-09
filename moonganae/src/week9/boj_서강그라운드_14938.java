package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 문제명 : 서강그라운드
 * 번호 : 14938
 * 난이도 : 골4
 * 풀이시간 : 30분
 * 사용 알고리즘 : 다익스트라 (플로이드워샬)
 */
public class boj_서강그라운드_14938 {
	
	/* 지역수, 수색범위, 길의 수 */
	static int N, M, R;
	/* 지역별 아이템수 */
	static int[] item;
	/* 길의 최대거리 지역최대수(100) * 길의 최대길이(15) = 1500 보다 큰 2000 */
	static final int INF = 2000;
	/* 인접리스트 */
	static List<Edge>[] adj;
	/* 최대아이템수 */
	static int maxItem = 0;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		item = new int[N+1];
		adj = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		/* 아이템수 입력 */
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		/* 지역별 거리 입력 */
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adj[s].add(new Edge(e,w));
			adj[e].add(new Edge(s,w));
		}
		
		/* 다익스트라 셋팅 */
		PriorityQueue<Edge> pq = new PriorityQueue<>( (e1, e2) -> (e1.w-e2.w));
		int[] dist = new int[N+1];
		
		/* i : 예은이 착륙지점 */
		for(int i=1; i<=N; i++) {
			
			/* 다익스트라 */
			for(int j=1; j<=N; j++) {
				dist[j] = INF;
			}
			
			dist[i] = 0;
			pq.add(new Edge(i, 0));
			
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				
				int next = cur.to;
				int w = cur.w;
				
				if(w > dist[next]) continue;
				
				for(Edge e : adj[next]) {
					int distance = e.w + w;
					/* 예은이의 수색범위 안쪽에 있으면서 더 짧은 거리라면 */
					if(distance <= M && dist[e.to] >  distance) {
						dist[e.to] = e.w + w;
						pq.offer(new Edge(e.to, distance));
					}
				}
			}
			
			/* 아이템 수 구하기 */
			int cnt = 0;
			for(int j=1; j<=N; j++) {
				if(dist[j] != INF) {
					cnt += item[j];
				}
			}
			maxItem = Math.max(maxItem, cnt);
		}
		System.out.println(maxItem);
	}
	static class Edge{
		int to, w;

		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
	}

}
