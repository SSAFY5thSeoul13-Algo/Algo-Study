package week30.boj14500;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14500_G5_테트로미노 {
	static int N,M;
	static int max = Integer.MIN_VALUE;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dfs(i,j,0,0);
				exception(i,j);
			}
		}
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		//System.out.println(max);

	}
	
	private static void dfs(int r, int c, int cnt, int sum) {
		// 탐색한 정사각형의 갯수가 4가 되면 return 
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr<0 || nc<0 || nr>=N || nc>=M)	continue;
			if(visited[nr][nc])	continue;
			
			visited[nr][nc] = true;
			dfs(nr,nc,cnt+1,sum+arr[nr][nc]);
			visited[nr][nc] = false;
		}
		
	}
	
	// ㅗ 모양은 dfs를 이용한 탐색이 불가능하느로 따로 처리 
	private static void exception(int r, int c) {
		// ㅗ 
		if(r-1>=0 && c+1<M && c-1>=0) {
			max = Math.max(max, arr[r][c] + arr[r-1][c] + arr[r][c+1] + arr[r][c-1]);
		}
		// ㅜ 
		if(r+1<N && c+1<M && c-1>=0) {
			max = Math.max(max, arr[r][c] + arr[r+1][c] + arr[r][c+1] + arr[r][c-1]);
		}
		// ㅓ 
		if(r-1>=0 && r+1<N && c-1>=0) {
			max = Math.max(max, arr[r][c] + arr[r][c-1] + arr[r-1][c] + arr[r+1][c]);
		}
		// ㅏ 
		if(r-1>=0 && r+1<N && c+1<M) {
			max = Math.max(max, arr[r][c] + arr[r][c+1] + arr[r-1][c] + arr[r+1][c]);
		}
	}
	

}
