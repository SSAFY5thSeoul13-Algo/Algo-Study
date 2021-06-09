package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_레이저짐은어디로_3709{

	/* 보드크기, 우향우 거울수 */
	static int N, R;
	/* 거울이 설치된 보드현황 */
	static boolean[][] map;
	/* 레이저가 방문한 체크 */
	static int[][] vis;
	
	/* delta array */
	// 0 : 우 , 1 : 하 , 2 : 좌 , 3 : 상 => +1 : 우측 90도 회전
	static int dy[] = {0, 1, 0, -1};
	static int dx[] = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			map = new boolean[N+2][N+2];
			vis = new int[N+2][N+2];
			
			/* 거울위치 설정 */
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[y][x] = true;
			}
			
			st = new StringTokenizer(br.readLine());
			
			/* 레이저 좌표 */
			int ly = Integer.parseInt(st.nextToken());
			int lx = Integer.parseInt(st.nextToken());
			
			/* 현재 레이저방향 설정 */
			int dir = 3;
			if(lx == 0) dir = 0;
			else if(lx == N+1) dir = 2;
			else if(ly == 0) dir = 1;
			
			while(true) {
				lx += dx[dir];
				ly += dy[dir];
				
				/* 보드를 벗어남 => 정답 찾음 */
				if(ly<1 || lx<1 || ly>N || lx>N) {
					break;
				}
				
				/* 레이저라면 */
				if(map[ly][lx]) {
					/* 현재 방향으로 이미 레이저를 방문한 적 있음 : 순환구조 => (0,0) 출력 */
					if( (vis[ly][lx] & 1<<dir) != 0) {
						ly = 0;
						lx = 0;
						break;
					}
					/* 아니면 방문체크하고 방향전환 */
					else {
						vis[ly][lx] |= 1<<dir;
						dir = (dir+1) % 4;
					}
				}
			}
			/* 레이저빔 마지막 좌표 출력 */
			System.out.println(ly + " " + lx);
		}
	}

}
