package week15.BOJ_3085_S4_사탕게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3085_S4_사탕게임 {
	static int N, max;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		//사탕 정보 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		//현재 상태에서 먹을 수 있는 가장 많은 사탕 확인
		check();

		//각 점을 기준으로 사탕의 색을 바꿈
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				change(i, j);
			}
		}
		
		System.out.println(max);
	}

	static void change(int y, int x) {
		//오른쪽 사탕과 색이 다른 경우
		if (x != N-1 && map[y][x] != map[y][x + 1]) {
			//사탕을 바꿈
			char temp = map[y][x];
			map[y][x] = map[y][x + 1];
			map[y][x + 1] = temp;

			//연속된 갯수 확인
			check();

			//원상 복귀
			temp = map[y][x];
			map[y][x] = map[y][x + 1];
			map[y][x + 1] = temp;
		}

		//왼쪽 사탕과 색이 다른 경우
		if (y != N-1 && map[y][x] != map[y + 1][x]) {
			//사탕을 바꿈
			char temp = map[y][x];
			map[y][x] = map[y + 1][x];
			map[y + 1][x] = temp;

			//연속된 갯수 확인
			check();

			//원상 복귀
			temp = map[y][x];
			map[y][x] = map[y + 1][x];
			map[y + 1][x] = temp;
		}
	}
	
	static void check() {
		//가로 줄의 사탕을 확인
		for (int i = 0; i < N; i++) {
			int count = 1;
			int num = map[i][0];
			
			for (int j = 1; j < N; j++) {
				if(map[i][j] == num) {
					count++;
				}
				else {
					num = map[i][j];
					count = 1;
				}
				max = Math.max(count, max);
			}
		}
		
		//세로 줄의 사탕을 확인
		for (int j = 0; j < N; j++) {
			int count = 1;
			int num = map[0][j];
			
			for (int i = 1; i < N; i++) {
				if(map[i][j] == num) {
					count++;
				}
				else {
					num = map[i][j];
					count = 1;
				}
				max = Math.max(count, max);
			}
		}
	}
}
