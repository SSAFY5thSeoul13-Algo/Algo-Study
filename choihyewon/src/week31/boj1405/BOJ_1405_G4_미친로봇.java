package week31.boj1405;

import java.io.*;
import java.util.*;

public class BOJ_1405_G4_미친로봇 {
	// 동 서 남 북 
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static double[] direction = new double[4];
	static boolean[][] visited = new boolean[30][30];
	static int N;
	static double answer;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<4; i++) {
			direction[i] = Double.parseDouble(st.nextToken()) / 100;
		}
		
		// 시작점은 (15,15)로 고정 
		visited[15][15] = true;
		
		backTracking(15,15,1.0,0);
		
		System.out.println(answer);
		
		
	}
	private static void backTracking(int r, int c, double result, int cnt) {
		if(cnt == N) {
			answer += result;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0 || nc<0 || nr>=30 || nc>=30)	continue;

			if(!visited[nr][nc] && direction[i]>0) {
				visited[nr][nc] = true;
				backTracking(nr,nc,result*direction[i],cnt+1);
				visited[nr][nc] = false;
			}

		}
		
	}

}
