package week29.boj2206;

import java.io.*;
import java.util.*;

public class BOJ_2206_G4_벽_부수고_이동하기 {
	static int N,M;
	static int[][] arr;
	static int[][] visited;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static class Node{
		int r;
		int c;
		int dis;
		int breakCnt;
		public Node(int r, int c, int dis, int breakCnt) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.breakCnt = breakCnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String str = br.readLine();
			for(int j=1; j<=M; j++) {
				arr[i][j] = str.charAt(j-1)-'0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		// 1행 1열부터 시작 , 현재 이동거리는 1, 벽을 부순 횟수는 0 
		int ans = bfs(1,1,1,0);
		
		System.out.println(ans);
		

	}
	private static int bfs(int r, int c,int dis,int breakCnt) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r,c,dis,breakCnt));
		visited[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			// 목적지 (N,M) 에 도착했다면 break 
			if(node.r == N && node.c == M) {
				return node.dis;
			}
			
			for(int d=0; d<4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				int ndis = node.dis;
				int bCnt = node.breakCnt;
		
				if(nr<=0 || nc<=0 || nr>N || nc>M)
					continue;
				
				// 이미 벽을 부순곳이라면 
				if(visited[nr][nc]<=bCnt) continue;
			
				// 이동이 가능하다면 이동거리만 늘려서 저장하면 된다 
				if(arr[nr][nc]==0) {
					queue.add(new Node(nr,nc,ndis+1,bCnt));
					visited[nr][nc] = bCnt;
				}
				// 다음 이동할 곳이 벽인 경우 
				else if(arr[nr][nc]==1) {
					// 지금까지 벽을 부순 횟수가 0이라면 
					if(bCnt==0) {
						// 이동거리와 벽을 부신 횟수를 늘려 저장 
						queue.add(new Node(nr,nc,ndis+1,bCnt+1));
						visited[nr][nc] = bCnt+1;
					}
				}
			}
			
			
		}
		
		return -1;
		
	}

}
