package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_로봇청소기_14503 {
	static int map[][];
	static int N;
	static int M;
	static int clean = 0;
	
			// 북 동 남 서
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {-1, 0 , 1 , 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		// 영역 설정
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean doFirst = true;		// 1단부터인지 2단계부터인지를 설정하는 변수
		while(true) {
			
			// 동작 규칙 1번 : 현재 위치를 청소한다
			if(doFirst) {
				map[robot.y][robot.x] =2; 	// 청소 
				clean++;; 					// 청소한 방 증가
				doFirst=false;				// 다음은 1번부터
			}
			
			// 동작규칙 2번 : 현재 위치, 현재 방향기준 왼쪽으로 차례대로 탐색
			boolean allClean = true;				// 모든 방이 청소되었는지 확인
			for(int i=0; i<4; i++) {
				robot.moveLeft();					// 로봇회전
				
				int zx = robot.x + dx[robot.dir];	// 왼쪽방향 x좌표
				int zy = robot.y + dy[robot.dir];	// 왼쪽방향 y좌표
				
				// 범위체크
				if(zx<1 || zy <1 || zx>=M-1 || zy>=N-1) continue;
				
				// (a) : 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
				if(map[zy][zx] == 0) {
					robot.x=zx; robot.y = zy;
					doFirst = true;				// 다음은 1번부터
					allClean = false;			// 전부 청소된 방이 아닙니다.
					break;
				}
				// (b) : 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
				else {
					doFirst = false;
				}
			}
			
			if(allClean) {						// 이미 모든방이 청소되었다면
				int back = robot.getBack();		// 로봇뒤돌기
				int bx = robot.x + dx[back];
				int by = robot.y + dy[back];
				
				// (d) : 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
				if(bx<1 || by <1 || bx>=M-1 || by>=N-1) break;
				if(map[by][bx] == 1) break;
				
				// (c) : 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
				robot.x = bx; robot.y = by;
				doFirst = false;
			}
		}

		System.out.println(clean);

	}
	static class Robot{
		int y, x, dir;
		public Robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
		// 로봇 왼쪽회전
		public void moveLeft() {
			if(--dir<0) dir = 3;
		}
		// 로봇 오른쪽 회전
		public void moveRight() {
			dir = (dir+1) %4;
		}
		// 로봇 뒤로 회전
		public int getBack() {
			return (dir+2)%4;
		}
		
	}
}
