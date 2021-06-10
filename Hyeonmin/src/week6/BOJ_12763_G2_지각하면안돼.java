package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12763_G2_지각하면안돼 {
	//N: 건물 수, L: 경로 수, T: 최대 시간, M: 최대 비용
	static int N, L, T, M;
	//이미 지나온 킬을 저장할 배열
	static boolean[] visited;
	//각 건물의 다른 건물로 향하는 경로들을 저장한 리스트배열
	static List<Road>[] distance;
	//최소비용
	static int min = Integer.MAX_VALUE;
	
	//경로를 저장하는 클래스
	static class Road{
		int to;
		int minute;
		int fee;
		
		public Road(int to, int minute, int fee) {
			super();
			this.to = to;
			this.minute = minute;
			this.fee = fee;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		
		distance = new ArrayList[N+1];
		
		for (int i = 0; i <= N; i++) {
			distance[i] = new ArrayList<Road>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		L = Integer.parseInt(br.readLine());
		
		//각 경로의 정보를 읽어서 리스트에 저장
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int minute = Integer.parseInt(st.nextToken());
			int fee = Integer.parseInt(st.nextToken());
			
			distance[from].add(new Road(to, minute, fee));
			distance[to].add(new Road(from, minute, fee));
		}
		
		//시작점을 다시 돌아오지 않게 방문처리
		visited[1] = true;
		
		dfs(0, 0, 0, 1);
		
		min = min == Integer.MAX_VALUE ? -1 : min;
		
		System.out.println(min);
	}
	
	//경로 탐색
	static void dfs(int cnt, int tSum, int mSum, int current) {
		//비용 or 시간이 이미 초과인 경우나 이미 구한 최소 비용이상이면 종료
		if(tSum > T || mSum > M || mSum >= min) {
			return;
		}
		
		//목표 위치에 도착한 경우
		if(current == N) {
			//더 작은 값 저장
			min = Math.min(min, mSum);
			
			return;
		}
		
		//갈 수 있는 모든 곳을 간 경우
		if(cnt == N) {
			return;
		}
		
		//현재 위치에서 갈 수 있는 모든 경로
		for (Road road : distance[current]) {
			//경로의 도착 위치가 아직 방문하지 않은 곳인 경우
			if(!visited[road.to]) {
				visited[road.to] = true; 
				//다음 경로 탐색
				dfs(cnt+1, tSum + road.minute, mSum + road.fee, road.to);
				visited[road.to] = false; 
			}
		}
	}
}