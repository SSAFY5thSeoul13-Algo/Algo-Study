package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14938_G4_서강그라운드 {
	//N : 지역 수, M : 수색 범위, R : 경로 수, max : 습득 가능 아이템
	static int N, M, R, max;
	//각 지역에 있는 아이템 수
	static int[] items;
	//경유지를 포함한 각 위치로의 최단 이동 거리
	static int[][] distance;
	//길이 없는 경우의 distance 값
	static final int INF = 16; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		distance = new int[N+1][N+1];
		items = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		//각 지역 아이템 수 입력
		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		//각 지역의 양방향 경로 입력
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			distance[start][end] = dis;
			distance[end][start] = dis;
		}
		
		//직통이 아닌 경우 값을 INF로 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(i != j && distance[i][j] == 0)
				distance[i][j] = INF;
			}
		}
		
		//플로이드 워샬
		floydWarshall();
		
		//각 지역을 시작점으로 획득 가능한 아이템을 구함
		for (int i = 1; i <= N; i++) {
			//아이템 수
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				//현재 지역에서 다른 지역까지의 거리가 탐색 범위 이내이면 아이템 추가
				if(distance[i][j] <= M) {
					temp += items[j];
				}
			}
			
			//구한 아이템 값과 기존 값 비교
			if(max < temp)
				max = temp;
		}
		
		System.out.println(max);
	}
	
	static void floydWarshall() {
		//경유
		for (int k = 1; k <= N; k++) {
			//출발
			for (int i = 1; i <= N; i++) {
				if(i == k)
					continue;
				//도착
				for (int j = 1; j <= N; j++) {
					if(distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j]; 
					}
				}
			}
		}
	}
}