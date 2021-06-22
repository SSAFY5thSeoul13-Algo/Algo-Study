package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_두동전_16197 {

	/* 보드의 가로 세로크기 */
	static int N, M;
	/* 보드초기 기물 */
	static char[][] map;
	/* 동전 2개가 방문한 위치를 체크하기위한 4차원배열 */
	static boolean[][][][] vis;
	/* BFS용 queue : 동전이 연속으로 2개씩 들어가있다 */
	static Queue<Point> q = new LinkedList<>();
	
	/* delta array */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		vis = new boolean[N][M][N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				// 동전이면 queque에 삽입
				if(map[i][j] == 'o') {
					q.add(new Point(i,j));
					
				}
			}
		}
		
		System.out.println(BFS());

	}
	
	static int BFS() {
		
		/* 총 10번의 시도 */
		for(int t=1; t<=10; t++) {
			/* 동전이 2개가 연속이기 대문에 나누기 2를 해준다. */
			int size = q.size()/2;
			
			// queue size 만큼
			while(size-- > 0) {
				
				Point cur = q.poll();
				Point cur2 = q.poll();
				
				// 동전이 겹쳐있다면 이미 가망이 없는 수이다.
				if(cur.y == cur2.y && cur.x == cur2.x) continue;
				
				// 델타이동
				for(int z=0; z<4; z++) {
					int y1 = cur.y + dy[z];
					int x1 = cur.x + dx[z];
					int y2 = cur2.y + dy[z];
					int x2 = cur2.x + dx[z];
					
					boolean fall1 = isFall(y1, x1);
					boolean fall2 = isFall(y2,x2);
					
					/* 둘다 떨어졌다면 안됨 */
					if(!fall1 && !fall2) continue;
					
					/* 하나만 떨어졌다면 그게 정답 */
					if(fall1 ^ fall2) {
						return t;
					}
					
					/* 여기부터는 무조건 동전이 이동하는 영역 */
					
					// 1번동전이 벽에 막혀있다면 이동X
					if(map[y1][x1] == '#') {
						y1 = cur.y;
						x1 = cur.x;
					}
					
					// 2번동전이 벽에 막혀있다면 이동X
					if(map[y2][x2] == '#') {
						y2 = cur2.y;
						x2 = cur2.x;
					}
					
					// 갈장소가 이미 이동해본 장소라면 안감
					if(vis[y1][x1][y2][x2]) continue;
					
					// 한번도 안가봤다면 이동
					vis[y1][x1][y2][x2] = true;
					q.offer(new Point(y1,x1));
					q.offer(new Point(y2,x2));
				}
			}
		}
		
		return -1;
	}
	
	static boolean isFall(int y, int x) {
		/* 떨어졌냐? */
		if(y<0 || x<0 || y>=N || x>=M) return false;
		
		return true;
	}
	
	static class Point{
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

}
