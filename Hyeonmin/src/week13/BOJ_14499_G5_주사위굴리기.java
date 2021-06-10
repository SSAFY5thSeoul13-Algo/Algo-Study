package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_G5_주사위굴리기 {
	static int N, M, K;
	static Dice dice = new Dice();
	//현재 주사위 위치
	static int[] now = new int[2];
	static int[][] map;
	// x 오 왼 위 아
	static int[] dy = {0, 0, 0, -1, 1 };
	static int[] dx = {0, 1, -1, 0, 0 };

	static class Dice {
		//주사위 면에 따른 arr 인덱스
		int top = 0;
		int left = 3;
		int right = 2;
		int bottom = 5;
		int front = 1;
		int back = 4;
		
		//주사위에 적힌 숫자
		int[] arr = { 0, 0, 0, 0, 0, 0};
		
		//주사위가 움직인 경우
		void move(int dir) {
			switch(dir) {
			case 1:
				right();
				break;
			case 2:
				left();
				break;
			case 3:
				up();
				break;
			case 4:
				down();
				break;
			}
		}

		void left() {
			int temp = top;
			
			top = right;
			right = bottom;
			bottom = left;
			left = temp;
		}

		void right() {
			int temp = top;
			
			top = left;
			left = bottom;
			bottom = right;
			right = temp;
		}

		void up() {
			int temp = top;
			
			top = back;
			back = bottom;
			bottom = front;
			front = temp;
		}

		void down() {
			int temp = top;
			
			top = front;
			front = bottom;
			bottom = back;
			back = temp;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//처음 주사위 시작 위치 입력
		now[0] = Integer.parseInt(st.nextToken());
		now[1] = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int k = 0; k < K; k++) {
			int dir = Integer.parseInt(st.nextToken());
			
			//주사위가 이동하려는 좌표
			int ny = now[0] + dy[dir];
			int nx = now[1] + dx[dir];
			
			//범위 이내인 경우
			if(canGo(ny, nx)) {
				//주사위 6면 변경
				dice.move(dir);
				//주사위 위치 갱신
				now[0] = ny;
				now[1] = nx;
				
				//이동한 곳위 맵에 숫자가 있는 경우
				if(map[now[0]][now[1]] != 0) {
					//해당 숫자 주사위 바닥면에 입력 후 0으로 변경
					dice.arr[dice.bottom] = map[now[0]][now[1]];
					map[now[0]][now[1]] = 0;
				}
				//이동한 곳의 숫자가 0인 경우
				else if(map[now[0]][now[1]] == 0) {
					//주사위 바닥면의 숫자를 복사
					map[now[0]][now[1]] = dice.arr[dice.bottom];
				}
				
				//현재 주사위 위쪽 숫자 출력
				System.out.println(dice.arr[dice.top]);
			}
			
			
		}
	}
	
	static boolean canGo(int y, int x) {
		if(y<0||x<0||y>=N||x>=M)
			return false;
		
		return true;
	}
}