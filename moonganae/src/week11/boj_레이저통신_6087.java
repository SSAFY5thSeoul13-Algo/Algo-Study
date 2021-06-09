package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_레이저통신_6087 {

	/* 지도의 가로, 세로길이 */
	static int W, H;
	/* 지도 */
	static char[][] map;
	/* 방문체크 및 거울횟수(방향전환수) */
	static int[][] vis;
	/* delta Array */
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static class Point {
		int y, x, dir, cnt;

		public Point(int y, int x, int dir, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		vis = new int[H][W];
		
		int ry = 0 , rx = 0;
		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 'C') {
					ry = i;
					rx = j;
				}
			}
		}
		
		System.out.println(BFS(ry,rx));
	}
	
	static int BFS(int ry, int rx) {
		
		Queue<Point> q = new LinkedList<>();
		map[ry][rx] = '*';	// 첫번째 레이저는 이제 벽으로
		
		/* 첫번째 레이저에서 사방으로 queue에 넣어주기 */
		for(int z=0; z<4; z++) {
			int zy = ry + dy[z];
			int zx = rx + dx[z];
			
			if(outBound(zy, zx)) continue;
			if(map[zy][zx] == '*') continue;
			
			q.offer(new Point(zy,zx, z, 0));
		}

		/* 최소 거울수 */
		int ans = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			int y = cur.y;
			int x = cur.x;
			int cnt = cur.cnt;
			/* 정답을 찾았다면 */
			if(map[y][x] == 'C') {
				ans = Math.min(ans, cnt);
				continue;
			}
			
			int dir = cur.dir;
			
			/* 4방탐색 */
			for(int z=0; z<4; z++) {
				int zy = y + dy[z];
				int zx = x + dx[z];
				
				/* 범위확인 */
				if(outBound(zy, zx)) continue;
				/* 벽확인 */
				if(map[zy][zx] == '*') continue;
				
				int zCnt = z!=dir ? cnt+1 : cnt;
				/* 이미 방문을 했는데, 거울수가 같거나 최소가 아니라면 */
				if(vis[zy][zx] != 0 && vis[zy][zx] < zCnt) continue;
				
				/* 큐에 삽입 */
				q.offer(new Point(zy,zx, z, zCnt));
				vis[zy][zx] = zCnt;
			}
		}
		return ans;
	}
	
	static boolean outBound(int y, int x) {
		if(y<0 || x<0 || y>=H || x>=W) return true;
		return false;
	}
}
/*
7 8
.......
......C
......*
*****.*
....*..
....*..
....*..
....C..
*/