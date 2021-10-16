package week29.BOJ_2206_G4_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G4_벽부수고이동하기 {
	static int N,M,min = -1;
	static char[][] map;
	static boolean[][][] isVisited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		isVisited = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		bfs();
		
		System.out.println(min);

	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {0,0,1,1});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			int cnt = p[2];
			int time = p[3];
			
			if(y == N-1 && x == M-1) {
				min = time;
				break;
			}
			
			if(isVisited[y][x][cnt]) {
				continue;
			}
			
			for (int i = cnt; i >= 0; i--) {
				isVisited[y][x][i] = true;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= M)	continue;
				
				if(map[ny][nx] == '1') {
					if(cnt == 1) {
						q.offer(new int[] {ny,nx,0,time+1});
					}
				}
				else {
					q.offer(new int[] {ny,nx,cnt,time+1});
				}
			}
		}
	}

}
