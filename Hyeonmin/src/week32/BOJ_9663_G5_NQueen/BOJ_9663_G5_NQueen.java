package week32.BOJ_9663_G5_NQueen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_G5_NQueen {
	static int[][] map;
	static boolean[][] isVisited;
	static int N, result;
	static int[][] delta= {
			{1,0},
			{1,-1},
			{1,1},
	};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		isVisited = new boolean[N][N];
		
		dfs(0, 0);
		
		System.out.println(result);
	}
	
	static void dfs(int y, int count) {
		if(y == N) {
			if(N == count)	result++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if(map[y][j] == 0) {
				inputVisit(y, j);
				dfs(y+1, count+1);
				deleteVisit(y, j);
			}
		}
	}
	
	static void inputVisit(int y, int x) {
		map[y][x]++;
		
		for (int d = 0; d < 3; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			while(isIn(ny, nx)) {
				map[ny][nx]++;
				
				ny += delta[d][0];
				nx += delta[d][1];
			}
		}
	}
	
	static void deleteVisit(int y, int x) {
		map[y][x]--;
		
		for (int d = 0; d < 3; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			while(isIn(ny, nx)) {
				map[ny][nx]--;
				
				ny += delta[d][0];
				nx += delta[d][1];
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N)	return false;
		
		return true;
	}

}
