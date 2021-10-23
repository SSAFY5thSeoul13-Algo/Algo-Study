package week30.BOJ_14500_G5_테트로미노;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_G5_테트로미노 {
	static int N, M, max;
	static int[][] map;
	// 아 오 위 왼
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int[][][] tetrominoes = { { { 0, -1 }, { 0, 1 }, { 1, 0 } }, // 왼오아
			{ { 0, -1 }, { 1, 0 }, { -1, 0 } }, // 왼아위
			{ { 0, -1 }, { 0, 1 }, { -1, 0 } }, // 왼오위
			{ { 0, 1 }, { 1, 0 }, { -1, 0 } }// 아아위
	};
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 1, map[i][j]);
				checkTetro(i, j);
			}
		}

		System.out.println(max);

	}

	static void dfs(int y, int x, int depth, int sum) {
		// 너비가 4가 되는 순간의 숫자의 합을 비교
		if (depth == 4) {
			max = Math.max(max, sum);
			return;
		}

		isVisited[y][x] = true;

		for (int[] del : delta) {
			int ny = y + del[0];
			int nx = x + del[1];

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || isVisited[ny][nx])
				continue;

			dfs(ny, nx, depth + 1, sum + map[ny][nx]);
		}

		isVisited[y][x] = false;
	}

	// dfs 4칸으로 확인 할 수 없는 모양을 체크
	static void checkTetro(int y, int x) {

		for (int[][] tetromino : tetrominoes) {
			int sum = map[y][x];
			Loop: for (int[] del : tetromino) {
				int ny = y + del[0];
				int nx = x + del[1];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					break Loop;

				sum += map[ny][nx];
			}
			max = Math.max(max, sum);
		}
	}
}
