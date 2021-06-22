package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_주사위굴리기_14499 {
	/* 지도크기, 좌표, 명령의 갯수 */ 
	static int N, M, x, y, K;
	/* 지도 */
	static int[][] map;

	/* Delta Array */
	static int[] dy = {0,0,0,-1,1};
	static int[] dx = {0,1, -1, 0, 0};
	static class Dice{
		/*						up
		 * 				left	bottom		right
		 * 						down
		 * 						top
		 * */
		int top = 0, bot = 0, up=0, down=0, left=0, right=0;
		
		void move(int dir) {
			
			int tmp = bot;
			// 동
			if(dir == 1) {
				bot = right;
				right = top;
				top = left;
				left = tmp;
			}
			// 서
			else if(dir == 2){
				bot = left;
				left = top;
				top = right;
				right = tmp;
			}
			// 북
			else if(dir == 3){
				bot = up;
				up = top;
				top = down;
				down = tmp;
			}
			// 남
			else if(dir == 4){
				bot = down;
				down = top;
				top = up;
				up = tmp;
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append("\t"+up + "\n");
			sb.append(left + "\t" + bot + "\t" + right+ "\n");
			sb.append("\t"+down+ "\n");
			sb.append("\t" + top+ "\n");
			sb.append("\n");
			
			return sb.toString();
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Dice dice = new Dice();
		
		map= new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			// 현재 주사위를 굴릴 방향
			int dir = Integer.parseInt(st.nextToken());
			
			/* 이동 */
			y += dy[dir];
			x += dx[dir];
			
			/* 범위가 벗어난다면 */
			if(y < 0 || x <0 || y>=N || x>=M) {
				// 이동취소
				y -= dy[dir];
				x -= dx[dir];
				continue;
			}
			
			// 주사위 이동
			dice.move(dir);
			
			// 바닥수가 0이라면
			if(map[y][x] == 0) {
				// 바닥면 수가 복사
				map[y][x] = dice.bot;
			}
			// 0이 아니라면
			else {
				// 바닥면수 복사
				dice.bot = map[y][x];
				map[y][x] = 0;
			}
			
			sb.append(dice.top + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
