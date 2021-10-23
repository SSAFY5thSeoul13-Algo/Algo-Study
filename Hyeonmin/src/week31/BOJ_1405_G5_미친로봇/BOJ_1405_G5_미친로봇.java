package week31.BOJ_1405_G5_미친로봇;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405_G5_미친로봇 {
	static int[][]delta = {
			{0,1},
			{0,-1},
			{1,0},
			{-1,0}
	};
	static boolean[][] map;
	static int N;
	static double result;
	static int[] percent = new int[4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new boolean[N*2+1][N*2+1];
		
		for (int i = 0; i < 4; i++) {
			percent[i] = Integer.parseInt(st.nextToken());
		}
		
		map[N][N] = true;
		recursive(0, N, N, 1);
		
		System.out.println(result);
	}
	
	static void recursive(int step, int y, int x, double per) {
		if(step == N) {
			result += per;
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			if(percent[d] == 0)	continue;
			
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			//단순한 경로인 경우
			if(!map[ny][nx]) {
				map[ny][nx] = true;
				recursive(step + 1, ny, nx, per * (double)percent[d]/100);
				map[ny][nx] = false;
			}
		}
	}

}
