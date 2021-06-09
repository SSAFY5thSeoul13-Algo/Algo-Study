package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 파티
 * 번호 : 1238
 * 난이도 : 골드3
 * 풀이시간 : 19분
 * 사용 알고리즘 : 플로이드 워샬 
 */
public class boj_파티_1238 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 마을의 수(학생번호) */
		int N = Integer.parseInt(st.nextToken());
		/* 단방향 도로의 수 */
		int M = Integer.parseInt(st.nextToken());
		/* 파티를 벌일 마을번호 */
		int X = Integer.parseInt(st.nextToken());
		/* 마을간 거리배열 */
		int[][] dist = new int[N+1][N+1];
		/* N의 MAX * 거리의 최대값 + 1 = 1000 * 100 + 1 = 100001 */
		int INF = 100001;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				dist[i][j] = INF;
			}
		}
		
		/* 단방향 거리 입력 */
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dist[f][t] = w;
		}
		
		/* 플로이드-워샬 */
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(dist[i][k] == INF) continue;
				if(i==k) continue;
				for(int j=1; j<=N; j++) {
					if(dist[k][j]==INF)continue;
					if(j==i || j==k) continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		/* 가장 오래걸리는 학생 == i->X, X->i 거리의 합이 가장 큰 학생 */
		int max = -1;
		for(int i=1; i<=N; i++) {
			max = Math.max(max, dist[i][X] + dist[X][i]);
		}
		System.out.println(max);
	}

}
