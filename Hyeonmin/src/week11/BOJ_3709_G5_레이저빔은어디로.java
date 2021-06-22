package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3709_G5_레이저빔은어디로 {
	static int N, R, T, dir;
	static int[][] map;
	static int[] p = new int[2];
	//상 우 하 좌
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//보드 크기
			N = Integer.parseInt(st.nextToken());
			//거울 개수
			R = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			
			//거울 입력
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[y][x] = 1;
			}
			
			st = new StringTokenizer(br.readLine());
			
			//레이저 시작 좌표 입력
			p[1] = Integer.parseInt(st.nextToken());
			p[0] = Integer.parseInt(st.nextToken());
			
			//레이저 위치에 따른 방향 설정
			if(p[1] == 0) {
				dir = 1;
			}
			else if(p[1] == N+1) {
				dir = 3;
			}
			else if(p[0] == 0) {
				dir = 0;
			}
			else if(p[0] == N+1) {
				dir = 2;
			}
			
			//쏘아진 레이저가 보드 밖으로 나올때 까지 반복
			while(true) {
				//레이저 1칸 이동
				p[0] += dy[dir]; 
				p[1] += dx[dir];
				
				//보드 밖이면 중지
				if(p[0] <= 0 || p[1] <= 0 || p[0] > N || p[1] > N)
					break;
				
				//거울이 있으면 방향을 현재의 오른쪽으로 변경
				if(map[p[0]][p[1]] == 1) {
					dir = (dir+1)%4;
				}
			}
			
			//레이저 마지막 위치 출력
			System.out.println(p[1] + " " + p[0]);
		}
	}
}