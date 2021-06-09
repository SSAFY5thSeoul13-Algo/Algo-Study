package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_구슬탈출2_13460 {
	static int N, M;								// 보드의 세로, 가로
	static char[][] map;							// 보드
	
	/* Delta Array */
	static int DOWN=0, RIGHT=1, UP=2, LEFT=3;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static Point first = new Point(0,0,0,0,0, -1);	// BFS에 넣을 첫번째 좌표, -1 : 첫번째 넣는건 방향이 정해지지 않았음
	static int ans;									// 정답 stage를 저장할 변수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'R') {
					first.ry = i;
					first.rx = j;
				}
				else if(map[i][j] == 'B') {
					first.by = i;
					first.bx = j;
				}
			}
		}
		
		ans = -1;					// 정답을 찾지 못할경우 -1출력
		BFS();						// BFS시작
		System.out.println(ans);	// 정답출력

	}
	
	static void BFS() {
		Queue<Point> q = new LinkedList<>();
		q.offer(first);						// 첫번째 시작좌표 넣기
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int dir=0; dir<4; dir++) {
		
				/* 방향확인 
				 * 방금 굴렸던 방향이나, 반대방향으로 공을 굴릴필요가 없다.
				 * 즉, 	cur.dir == LEFT or RIGHT 	=> UP, DOWN만 수행
				 * 		cur.dir == UP or DOWN 		=> LEFT or RIGHT만 수행 
				 * */
				if(cur.dir == dir || dirBack(cur.dir) == dir) continue;
				
				/* 파란공 이동 */
				int bx = cur.bx + dx[dir];
				int by = cur.by + dy[dir];
				
				while(map[by][bx] != '#') {			// 현재 위치가 '#'이 아니라면 반복
					if(map[by][bx] == 'O') break;	// 현재 위치가 구멍이라면 탈출
					// 한칸이동
					bx += dx[dir];
					by += dy[dir];
				}
				
				if(map[by][bx] == 'O') continue;	// 파란공이 구멍에 빠지면 다음 경우로 넘김
				else {								// 구멍에 빠진게 아니라면 현재 위치는 '#' 이므로
					bx -= dx[dir];					// 한칸 전으로 보정
					by -= dy[dir];
				}
					
				/* 빨간공 이동 */
				int rx = cur.rx + dx[dir];
				int ry = cur.ry + dy[dir];

				while(map[ry][rx] != '#') {			// 현재 위치가 '#'이 아니라면 반복
					if(map[ry][rx] == 'O') break;	// 현재 위치가 구멍이면 탈출
					// 한칸 이동
					rx += dx[dir];
					ry += dy[dir];
				}
				
				if(map[ry][rx] == 'O') { // 파란공이 빠지지 않은 시점에서 빨간공이 구멍에 빠졌다면 정답!
					ans = cur.stage+1;
					return;
				}
				else {					// 구멍에 빠진게 아니라면 현재 위치는 '#' 이므로
					rx -= dx[dir];		// 한칸전으로 보정
					ry -= dy[dir];
				}
				
					
				/* 빨파란공 위치 같은 경우 보정 */
				if(ry==by && rx==bx) {
					/* 거리에 따라 뭔거리인걸 조정
					 * EX)			(1)								(2)
					 * 			##########						##########
					 * 			#R...B...#	오른쪽으로 기울이기 ->	#......RB#
					 * 			##########						##########
					 * 		즉, 원래 (2)의 B위치에 R,B가 모두 위치하는데 R,B는 겹칠수 없기 때문에 위치조정이 필요하다
					 * 			이때 조정되어야할 대상은 B보다 더 많이 이동한 R이 왼쪽으로 한칸 이동해야한다. 
					 */
					if(distance(cur.by, cur.bx, by, bx) > distance(cur.ry, cur.rx, ry, rx)){
						bx -= dx[dir];
						by -= dy[dir];
					} else {
						rx -= dx[dir];
						ry -= dy[dir];
					}
				}
				
				/* 큐에 넣기 */
				if(cur.stage != 9) {	// cur.stage == 9면 방금 수행한 BFS는 10번째가 된다.
					q.offer(new Point(ry,rx,by,bx,cur.stage+1, dir));
				}
				
			}
		}
	}
	/* 두 좌표사이의 거리를 측정반환함
	 * y1, x1 : 1번 좌표
	 * y2, x2 : 2번 좌표
	 * */
	static int distance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1-y2) + Math.abs(x1-x2);
	}
	
	/* 현재 진행방향의 반대방향을 반환하는 함수
	 * dir : 현재 진행방향
	 * */
	static int dirBack(int dir) {
		int backDir;
		if(dir == DOWN)
			backDir=UP;
		else if(dir == UP)
			backDir=DOWN;
		else if(dir == LEFT)
			backDir=RIGHT;
		else if(dir == RIGHT)
			backDir=LEFT;
		else
			backDir = dir;
		return backDir;
	}
	
	static class Point{
		/* 현재 상태의 
		 * ry : Red ball의 y 좌표
		 * ry : Red ball의 x 좌표
		 * by : Blue ball의 y 좌표
		 * by : Blue ball의 x 좌표
		 * stage : 현재 수행한 stage
		 * dir : 방금 기울인 방향
		 */
		int ry, rx, by, bx, stage, dir;

		public Point(int ry, int rx, int by, int bx, int stage, int dir) {
			super();
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.stage = stage;
			this.dir = dir;
		}
	}
}

