package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_6467_G4_전력난 {
	static int M, N;
	//길의 정보를 저장할 배열
	static List<Road>[] list;
	//현재 고른 집에서 인접한 집까지의 거리
	static int[] distance;
	static boolean[] visited;
	//불을 키는 최소 액수
	static int result;
	//모든 불을 키는 경우의 액수
	static int max;
	
	//길 정보를 저장할 클래스
	static class Road{
		int from;
		int to;
		int meter;
		
		public Road(int from, int to, int meter) {
			super();
			this.from = from;
			this.to = to;
			this.meter = meter;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			//2개가 모두 0이면 종료
			if(M+N == 0)
				break;
			
			//집 개수만큼 배열 생성
			list = new ArrayList[M];
			distance = new int[M];
			visited = new boolean[M];
			max = 0;
			result = 0;
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			for (int i = 0; i < M; i++) {
				list[i] = new ArrayList<Road>();
			}
			
			//길 정보를 입력
			for(int i=0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				int meter = Integer.parseInt(st.nextToken());
				
				list[from].add(new Road(from, to, meter));
				list[to].add(new Road(to, from, meter));
				max += meter;
			}
			
			//0번 집을 시작점으로 시작
			distance[0] = 0;
			//프림 실행	
			prim();
		
			System.out.println(max - result);
		}
	}
	
	static void prim() {
		//모든 집을 방문할 만큼 반복
		for (int i = 0; i < M; i++) {
			int min = Integer.MAX_VALUE;
			int idx = 0;
			
			//현재 갈 수 있는 최소비용의 길 선택
			for (int j = 0; j < M; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					idx = j;
				}
			}
			
			//선택한 집을 방문 처리
			visited[idx] = true;
			result += min;
			
			//선택한 집에서 연결된 길과 기존 길을 비교해 더 작은 값 저장
			for (Road road : list[idx]) {
				if(!visited[road.to] && distance[road.to] > road.meter) {
					distance[road.to] = road.meter;
				}
			}
			
		}
	}
}
