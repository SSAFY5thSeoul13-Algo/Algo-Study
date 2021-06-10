package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_G5_파이프옮기기1 {
	static int N;
	static int[][] map;
	//오른쪽, 대각선, 아래쪽
	static int[] dy = {0, 1, 1};
	static int[] dx = {1, 1, 0};
	static int count = 0;
	
	//파이프의 끝 좌표와 방향
	static class Point{
		int x;
		int y;
		int dir;
		
		Point(int y, int x, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		//집 정보 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0);
		
		System.out.println(count);
	}
	
	//파이프 이동
	static void dfs(int y, int x, int dir) {
		//3방향 이동
		for (int i = 0; i < 3; i++) {
			//현재 방향에 따라서 이동 불가능한 방향이면 스킵
			if(Math.abs(dir-i) > 1)
				continue;
			
			//이동할 좌표
			int ny = y +dy[i];
			int nx = x +dx[i];

			//이동 가능한 위치인지 확인
			if(canGo(ny, nx, i)) {
				//이동한 위치가 목표 위치면 카운트 상승
				if(ny == N-1 && nx == N-1) {
					count++;
				}
				//목표 위치가 아니면 다음 이동 실행
				else {
					dfs(ny, nx, i);
				}
			}
		}
	}
	
	static boolean canGo(int ny, int nx, int dir) {
		//해당 좌표가 범위 밖이거나 벽이면 이동 불가능
		if(ny < 0 || nx < 0 || ny>= N || nx >= N || map[ny][nx] == 1) 
			return false;

		//방향에 따른 이동 가능 여부
		switch(dir) {
		//오른쪽
		case 0:
			if(map[ny][nx] == 1)
				return false;
			break;
		//대각선
		case 1:
			if(map[ny][nx-1] == 1 || map[ny][nx] == 1 || map[ny-1][nx] == 1)
				return false;
			break;
		//아래쪽 
		case 2:
			if(map[ny][nx] == 1)
				return false;
			break;
		}
		
		return true;
	}
}