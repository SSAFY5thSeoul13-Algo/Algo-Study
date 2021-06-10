package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class boj_PuyoPuyo_11559 {

	/* 필드크기 가로, 세로 */
	static int N = 12, M = 6;
	/* 뿌요가 놓여있는 필드 */
	static char[][] map = new char[N][M];
	/* DFS용 방문좌표 */
	static boolean[][] vis;
	/* Delta Array */
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	/* 현재 찾고있는 색깔 */
	static char color;
	/* 터질 뿌요리스트 */
	static List<Point> bomb = new ArrayList<>();
	/* 위치 클래스 */
	static class Point {
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int t=0;		// 연쇄작용 횟수
		while(true) {
			// 전체 좌표 전수조사
			if(process() == false) break;
			
			// 중력작용으로 인한 뿌요 떨어짐
			fall();
			
			// 연쇄작용 횟수증가
			t++;
		}
		
		
		System.out.println(t);

	}
	
	/* 모든 좌표에대해서 전수조사함 */
	public static boolean process() {
		
		boolean isBomb = false;	// 터짐이 발생하였는가?
		vis = new boolean[N][M];
		for(int i=N-1; i>=0; i--) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == '.') continue;	// 빈공이면 넘어가라
				if(vis[i][j]) continue;			// 이미 방문한 좌표는 넘어가라
				
				color = map[i][j];				// 현재 찾을 색깔
				
				// 초기 좌표 확인
				vis[i][j] = true;			
				bomb.add(new Point(i,j));
				// DFS 수행
				DFS(i,j);
				
				// 하나의 그룹이 4이상이면 터져라~
				if(bomb.size() > 3) {
					for(Point p : bomb) {
						map[p.y][p.x] = '.';
					}
					isBomb = true;		// 터짐이 발생했음을 체크
				}
				
				bomb.clear();		// 시체처리
			}
		}
		
		return isBomb;
	}
	
	public static void DFS(int y, int x) {
		for(int z=0; z<4; z++) {
			int zy = y + dy[z];
			int zx = x + dx[z];
			if(zy <0 || zx< 0 || zy>=N || zx>=M) continue;	// 범위확인
			if(map[zy][zx] != color ) continue;				// 색깔확인
			if(vis[zy][zx]) continue;						// 방문확인
			
			vis[zy][zx] = true;
			bomb.add(new Point(zy,zx));;
			DFS(zy,zx);
		}
	}
	
	public static void fall() {
		for(int i=0; i<M; i++) {
			Queue<Point> dot = new LinkedList<>();			// 덮어질 빈공간 좌표를 보관할 리스트
			
			for(int j=N-1; j>=0; j--) {
				
				if(map[j][i] == '.') {						// 빈공간이면서
					if(!vis[j][i]) break;					// 방문한적이 없다 == 뿌요들이 터져서 발생한 빈공간이 아님 => 더이상 탐색 불필요
					
					// 방문한적이 있다. == 연쇄 폭발로 인한 빈공간 위의 뿌요들이 내려와야한다.
					dot.offer(new Point(j,i));
					continue;
				}
				if(dot.size() == 0) continue;	// 뿌요를 찾았는데 밑에 공간이 없다면 위를 더 탐색해봐야함
				
				Point cur = dot.poll();			// 현재 채워질 공간
				
				// 뿌요 이동
				map[cur.y][cur.x] = map[j][i];
				map[j][i] = '.';
				
				// 새로 생긴 빈공간 넣어주기
				cur.y = j;
				cur.x = i;
				dot.offer(cur);
			}
		}
	}
}

