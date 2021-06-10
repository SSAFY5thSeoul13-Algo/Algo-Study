package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1520_G4_내리막길 {
	static int M, N;
	//각 위치의 높이
	static int[][] height;
	//dp배열. 각 위치에 도달할 수 있는 방법의 수
	static int[][] map;
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		height = new int[M][N];
		map = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				height[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		bfs();
		
		System.out.println(map[M-1][N-1]);
	}
	
	static void bfs() {
		boolean[][] visited = new boolean[M][N];
		
		map[0][0] = 1;
		visited[0][0] = true;
		
		//높이가 기준으로 내림차순
		PriorityQueue<int[]> q = new PriorityQueue<int[]>( (o2, o1) -> Integer.compare(height[o1[0]][o1[1]], height[o2[0]][o2[1]]));
		
		//시작점
		q.offer(new int[] {0,0});
		
		while(!q.isEmpty()) {
			//현재까지 지나온 위치에서 갈 수 있는 가장 높은 위치 좌표
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			
			//4방향
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				//해당 위치가 범위 이내이고 현재 위치에서 갈 수 있는 경우
				if(canGo(ny, nx) && height[y][x] > height[ny][nx]) {
					//해당 위치에 가는 방법의 수는 현재 위치에 올 수 있는 방법 수만큼 증가 된다
					map[ny][nx] += map[y][x];
					//이전에 방문하지 않은 곳이면 큐에 추가
					if(!visited[ny][nx]) {
						q.offer(new int[] {ny,nx});
						visited[ny][nx] = true;
					}
				}
			}
		}
	}
	
	//범위 확인
	static boolean canGo(int y, int x) {
		if(y < 0 || x < 0 || y >= M || x >= N)
			return false;
		
		return true;
	}
	
}