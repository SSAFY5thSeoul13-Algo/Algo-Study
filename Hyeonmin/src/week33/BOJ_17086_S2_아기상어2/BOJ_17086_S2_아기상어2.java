package week33.BOJ_17086_S2_아기상어2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_S2_아기상어2 {
	static int[][] map;
	static int[][] tempMap;
	static int N, M, result;
	static int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		tempMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = Integer.MAX_VALUE;
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					q.offer(new int[] { i, j });
					tempMap[i][j] = 0;
				}
			}
		}

		bfs();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result = Math.max(result, tempMap[i][j]);
			}
		}

		System.out.println(result);
	}

	static void bfs() {

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int py = p[0];
			int px = p[1];

			for (int d = 0; d < 8; d++) {
				int ny = py + delta[d][0];
				int nx = px + delta[d][1];

				if ((ny < 0 || nx < 0 || ny >= N || nx >= M) || tempMap[ny][nx] <= tempMap[py][px] +1 )	continue;

				tempMap[ny][nx] = tempMap[py][px] + 1;
				result = Math.max(result, tempMap[ny][nx]);
				q.offer(new int[] { ny, nx });
			}
		}
	}

}
