package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11967_G4_불켜기 {
	static int N, M, max;
	//방문 가능한 방인지 확인하는 배열
	static boolean[][] visited;
	//불이 켜진 방을 확인하는 배열
	static boolean[][] on;
	//각 방에서 불을 켤 수 있는 방의 좌표 
	static List<Point>[][] list;
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static class Point{
		int x;
		int y;
		
		public Point(int y, int x) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//각 방의 스위치 배열
		list = new ArrayList[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				list[i][j] = new ArrayList<Point>();
			}
		}
		
		visited = new boolean[N+1][N+1];
		on = new boolean[N+1][N+1];
		
		//스위치 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			
			list[y][x].add(new Point(ty, tx));
		}
		
		//킬 수 있는 모든 방의 불을 키는 메소드 
		process();
		
		//불이 켜져 있는 방 갯수 확인
		check();
		
		System.out.println(max);
	}
	
	static void process() {
		Queue<Point> q = new LinkedList<Point>();
		
		//시작하는 방 세팅
		q.offer(new Point(1,1));
		on[1][1] = true;

		while(!q.isEmpty()) {
			Point p = q.poll();
			
			//방문하려는 방이 이미 방문을 했거나 불이 꺼져 있으면 스킵
			if(visited[p.y][p.x] || !on[p.y][p.x])
				continue;
			
			//방문 처리
			visited[p.y][p.x] = true;
			
			//방문한 방에서 켤 수 있는 방의 스위치들
			for (Point point : list[p.y][p.x]) {
				//방의 불을 킴
				on[point.y][point.x] = true;
				
				//불을 킨 방 기준으로 4방 탐색
				for (int d = 0; d < 4; d++) {
					int ny = point.y + dy[d];
					int nx = point.x + dx[d];
					
					//방의 4방향중에서 이미 방문했던 방이 있는 경우 새로 불을 켠 방에 갈수 있다는
					//의미 이므로 큐에 추가
					if(canGo(ny, nx) && visited[ny][nx]) {
						q.offer(new Point(point.y, point.x));
						break;
					}
				}
			}
			
			//방문한 방에서 4방 탐색
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				//아직 방문하지 않고 불이 켜진 방이 있으면 큐에 추가
				if(canGo(ny, nx) && !visited[ny][nx] && on[ny][nx]) {
					q.offer(new Point(ny, nx));
				}
			}
		}
	}
	
	//불이 켜진 방의 개수를 max에 저장
	static void check() {
		max = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(on[i][j])
					max++;
			}
		}
	}
	
	//배열 범위 확인하는 메소드
	static boolean canGo(int ny, int nx) {
		if(ny < 1 || nx < 1 || ny > N || nx > N)
			return false;
		
		return true;
	}
}