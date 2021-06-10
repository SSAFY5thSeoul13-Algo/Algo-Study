package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_G5_로봇청소기 {
	//N: 세로, M: 가로, count: 청소한 구역 수
	static int N, M, count;
	static int[][] map;
	//로봇 청소기 초기 위치 정보
	static int x, y, d;
	//북 동 남 서
	static int[] dy= {-1, 0, 1, 0};
	static int[] dx= {0, 1, 0, -1};
	/*d 
	 0:북, 1:동, 2:남, 3:서
	 0->3
	 1->0
	 2->1
	 3->2
	 */
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//영역 크기 설정
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		y=Integer.parseInt(st.nextToken());
		x=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		
		//영역 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//처음 로봇 청소기를 놓는 곳에서 시작
		move(y, x, d);
		
		System.out.println(count);
		
	}
	static void move(int y, int x, int dir) {
		//현재 위치가 청소하지 않은 곳이면 표시하고 count 증가
		if(map[y][x] == 0) {
			map[y][x] = 2;
			count++;
		}
		
		//현재 위치에서 이동할 수 있는 구역을 확인
		setDir(y, x, dir);
	}
	
	static void setDir(int y, int x, int dir) {
		int nextDir = dir;
		//현재 위치에서 4방향을 탐색
		for (int i = 0; i < 4; i++) {
			//탐색한 방향을 설정
			 nextDir = (nextDir == 0) ? 3 : nextDir - 1;
			
			int nextY = y + dy[nextDir];
			int nextX = x + dx[nextDir];
			//탐색하려는 곳이 범위 밖인 경우 방향을 바꿔서 탐색
			if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= M) {
				continue;
			}
			//탐색한 곳이 아직 청소하지 않은 곳이면 해당 위치로 이동
			if(map[nextY][nextX] == 0) {
				move(nextY, nextX, nextDir);
				return;
			}
		}
		
		//4방향 모두 벽이거나 이미 탐색한 경우
		//바라보던 방향에서 후진한 경우의 위치
		int nextY = y - dy[dir];
		int nextX = x - dx[dir]; 
		
		//해당 위치가 벽이 이면 중단
		if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= M || map[nextY][nextX] == 1) {
			return;
		}
		//벽이 아닌 경우 후진해서 해당 위치로 이동
		else {
			move(nextY, nextX, dir);
		}
	}
}