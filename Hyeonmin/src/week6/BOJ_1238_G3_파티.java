package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238_G3_파티 {
	static int N, M, X;
	//각 집에서 파티장에 가는 경우의 시간 
	static int[] go;
	//파티장에서 각 집에 가는 경우의 시간
	static int[] back;
	//정방향 길의 정보를 저장할 리스트
	static List<Road>[] listGo;
	//역방향 길의 정보를 저장할 리스트
	static List<Road>[] listBack;
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	
	//길 정보를 저장할 클래스
	static class Road implements Comparable<Road>{
		int to;
		int meter;
		
		public Road(int to, int meter) {
			super();
			this.to = to;
			this.meter = meter;
		}
		public Road() {}
		
		@Override
		public int compareTo(Road o) {
			return Integer.compare(this.meter, o.meter);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		listGo = new ArrayList[N+1];
		listBack = new ArrayList[N+1];
		
		go = new int[N+1];
		back = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			listGo[i] = new ArrayList<Road>();
			listBack[i] = new ArrayList<Road>();
		}
		
		//길의 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int meter = Integer.parseInt(st.nextToken());
			
			listGo[from].add(new Road(to, meter));
			listBack[to].add(new Road(from, meter));
		}
		
		//파티장으로 가는 경로 구하기
		dijkstraGo(X);
		//집으로 가는 경로 구하기
		dijkstraBack(X);
		
		//각 집마다의 왕복 거리중 가장 큰 것을 저장
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, go[i]+back[i]);
		}
		
		System.out.println(max);
	}

	static void dijkstraGo(int start) {
		PriorityQueue<Road> pq = new PriorityQueue<Road>();
		pq.offer(new Road(start, 0));
		
		go = new int[N+1];
		visited = new boolean[N+1];
		//아직 방분하지 않은 집까지의 거리를 max로 함
		Arrays.fill(go, Integer.MAX_VALUE);
		//시작지점의 거리는 0으로 해놓음
		go[start] = 0;
		
		while(!pq.isEmpty()) {
			Road road = pq.poll();
			
			//목표 지점이 이미 확인한 지역이면 다음 실행
			if(visited[road.to])
				continue;
			
			//방문 확인
			visited[road.to] = true;
			
			//다음으로 갈 수 있는 집에 대한 경로 정보를 모두 확인
			for (Road r : listBack[road.to]) {
				//아직 방문하지 않았고 현재 확인한 경로보다 더 좋은 경로가 있으면 저장
				if(!visited[r.to] && road.meter + r.meter <= go[r.to]) {
					go[r.to] = road.meter + r.meter;
					pq.add(new Road(r.to, go[r.to]));
				}
			}
		}
		
	}
	
	//Go와 동일
	static void dijkstraBack(int start) {
		PriorityQueue<Road> pq = new PriorityQueue<Road>();
		pq.offer(new Road(start, 0));
		
		back = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(back, Integer.MAX_VALUE);
		back[start] = 0;
		
		while(!pq.isEmpty()) {
			Road road = pq.poll();
			
			if(visited[road.to])
				continue;
			
			visited[road.to] = true;
			
			for (Road r : listGo[road.to]) {
				if(!visited[r.to] && road.meter + r.meter <= back[r.to]) {
					back[r.to] = road.meter + r.meter;
					pq.add(new Road(r.to, back[r.to]));
				}
			}
		}
		
	}
}
