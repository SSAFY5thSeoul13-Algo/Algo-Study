package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 전력난
 * 번호 : 6497
 * 난이도 : 골드4
 * 풀이시간 : 23분
 * 사용 알고리즘 : 크루스칼 
 */
public class boj_전력난_6497 {
	/* 집의수, 길의수 */
	static int M, N;
	/* union & find의 parent배열 */
	static int[] boss;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			// 0,0일 경우 프로그램 종료
			if(M==0 && N==0) break;
			
			/* parent 초기화 */
			boss = new int[M];
			for(int i=1; i<M; i++) {
				boss[i] = i;
			}
			
			/* 간선입력 */
			Edge[] edges = new Edge[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(from, to, w);
			}
			
			/* 간선정렬 : 거리의 오름차순 */
			Arrays.sort(edges, (e1, e2) -> e1.w-e2.w);
			
			/* 절약할수 있는 비용(거리) 구하기 */
			int ans = 0;
			/* MST에 포함된 정점의 수 */
			int cnt = 0;
			for(Edge e : edges) {
				int pf = find(e.from);
				int pt = find(e.to);
				
				// 절약할 수 있는 거리 == MST에 포함되지 않는 간선들
				if(pf==pt) {
					ans += e.w;
					continue;
				}
				
				// union
				boss[pt] = pf;
				
				cnt++;
				// 모든 정점을 찾았다면 종료
				if(cnt == M) break;
			}
			
			System.out.println(ans);
		}
	}
	
	static int find(int k) {
		if(k == boss[k]) return k;
		else return boss[k] = find(boss[k]);
	}
	static class Edge{
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", w=" + w + "]";
		}
	}
}
