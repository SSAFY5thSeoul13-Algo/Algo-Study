package week33.boj17086;

import java.io.*;
import java.util.*;

public class BOJ_17086_S2_아기상어2 {
	static class Node{
		int r;
		int c;
		int cnt;
		public Node(int r,int c,int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N,M,answer=0;
	static int[][] arr;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					bfs(i,j,0);
				}
			}
		}
		

		System.out.println(answer);
		
	}
	private static void bfs(int r, int c,int cnt) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Node(r,c,cnt));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for(int i=0; i<8; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M || visited[nr][nc])	continue;
				
				if(arr[nr][nc]==1) {
					answer = Math.max(answer, cur.cnt+1);
					return;
				}
				
				visited[nr][nc] = true;
				queue.add(new Node(nr,nc,cur.cnt+1));
			}
		}
		
	}

}
