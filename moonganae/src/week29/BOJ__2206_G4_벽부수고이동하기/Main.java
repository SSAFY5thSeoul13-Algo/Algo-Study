package week29.BOJ__2206_G4_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;		/*  */
	static int[][][] vis;	/* 방문체크 및 경로길이 */
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		/*
		 * vis[y][x][1] : 벽부수기 안했을때의 길이
		 * vis[y][x][0] : 벽부수기 했을때의 길이
		 * */
		vis = new int[N][M][2];

		for(int i=0; i<N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				map[i][j++] = c - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static int bfs() {
		
		if(N==1 && M==1) return 1;
		
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(0,0,1));
		vis[0][0][0] = 1;
		vis[0][0][1] = 1;
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			if(cur.y == N-1 && cur.x == M-1) {
				return vis[cur.y][cur.x][cur.opp];
			}
			
			for(int z=0; z<4; z++) {
				
				int zy = cur.y + dy[z];
				int zx = cur.x + dx[z];
				
				if(zy<0 || zx<0 || zy>=N || zx>=M) continue;
				if(vis[zy][zx][cur.opp] > 0) continue;
				
				// 벽이없다면 그냥 이동
				if(map[zy][zx] == 0) {
					q.offer(new Point(zy,zx, cur.opp));
					vis[zy][zx][cur.opp] = vis[cur.y][cur.x][cur.opp] + 1;
				}
				// 벽이있고, 벽을 부술찬스가 있다면 이동
				else if(cur.opp==1) {
					q.offer(new Point(zy, zx, 0));
					vis[zy][zx][0] = vis[cur.y][cur.x][cur.opp] + 1;
				}
			}
		}
		
		return -1;
	}
	
	static class Point {
		int y, x, opp;

		public Point(int y, int x, int opp) {
			super();
			this.y = y;
			this.x = x;
			this.opp = opp;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", opp=" + opp + "]";
		}
	}
}
