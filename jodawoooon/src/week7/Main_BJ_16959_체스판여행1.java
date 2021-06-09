package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16959_체스판여행1 {
	
	static int N, map[][], ans;
	static int[] dx1 = {-1,1,0,0}; //룩
	static int[] dy1 = {0,0,-1,1};
	static int[] dx2 = {-1,-1,1,1}; //비숍
	static int[] dy2 = {-1,1,-1,1};
	
	static int[] dx3 = {-2,-2,-1,1,2,2,1,-1}; //나이트
	static int[] dy3 = {-1,1,2,2,1,-1,-2,-2};
	
	static class Point {
		int x,y,cnt;
		int nextNum;
		int type;
		
		public Point(int x, int y, int cnt, int nextNum, int type) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.nextNum = nextNum;
			this.type = type;
		}

	}
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		map = new int[N][N];
		int startX=0, startY=0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		//문제에 주어진 대로 방문하는데 필요한 시간의 최솟값을 출력한다. 
		//BFS
		bfs(startX, startY);
		
		//1. 먼저, 1에 나이트, 비숍, 룩 중 하나를 놓는다.
		//1에서 3가지 경우의 수로 시작
		/*
		 * 체스 규칙
		 * 룩 => 상하좌우 쭉
		 * 비숍 => 대각선 쭉
		 * 나이트 => 8방향
		 * 
		 */
		
		
		//지학이가 1초 동안 할 수 있는 행동은 체스판 위에 놓인 말을 이동시키거나, 다른 말로 바꾸는 것이다.
		
		
		System.out.println(ans);
		
		
	}
	private static void bfs(int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][N][N*N+1][4];
		
		queue.add(new Point(startX, startY, 0, 2, 1)); //1=룩
		queue.add(new Point(startX, startY, 0, 2, 2)); //2==비숍
		queue.add(new Point(startX, startY, 0, 2, 3)); //3==나이트
		
		visited[startX][startY][2][1] = true;
		visited[startX][startY][2][2] = true;
		visited[startX][startY][2][3] = true;
		
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.x;
			int y = p.y;
			int cnt = p.cnt;
			int nextNum= p.nextNum;
			int type = p.type;
			
			if(nextNum==(N*N+1)) {
				ans= Math.min(ans, cnt);
				return;
			}
			

			//말을 이동시키거나
			//말을 바꾼다
			// 말을 바꿈
	        for (int i = 1; i <= 3; i++)
	        {
	            if(i!=type&&!visited[x][y][nextNum][i]) {
	            	visited[x][y][nextNum][i] = true;
	            	queue.add(new Point(x,y,cnt+1,nextNum,i));
	            }
	        }
			
			
			//말을이동시킴
			if(type==1) {
				//룩
				//상하좌우
				for (int d = 0; d < 4; d++) {
					int nx = x;
					int ny = y;
					while(true) {
						nx += dx1[d];
						ny += dy1[d];
						
						if(nx<0||nx>=N||ny<0||ny>=N) break;
						if(!visited[nx][ny][nextNum][type]) {
							visited[nx][ny][nextNum][type] = true;
							if(map[nx][ny]==nextNum) {
								queue.add(new Point(nx,ny,cnt+1,nextNum+1,type));
							}else {
								queue.add(new Point(nx,ny,cnt+1,nextNum,type));
							}
						}
					}
				}
			}else if(type==2) {
				//비숍
				for (int d = 0; d < 4; d++) {
					int nx = x;
					int ny = y;
					while(true) {
						nx += dx2[d];
						ny += dy2[d];
						
						if(nx<0||nx>=N||ny<0||ny>=N) break;
						if(!visited[nx][ny][nextNum][type]) {
							visited[nx][ny][nextNum][type] = true;
							if(map[nx][ny]==nextNum) {
								queue.add(new Point(nx,ny,cnt+1,nextNum+1,type));
							}else {
								queue.add(new Point(nx,ny,cnt+1,nextNum,type));
							}
						}
					}
				}
			}else if(type==3) {
				//나이트
				for (int d = 0; d < 8; d++) {
					int nx = x + dx3[d];
					int ny = y + dy3[d];
			
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					if(!visited[nx][ny][nextNum][type]) {
						visited[nx][ny][nextNum][type] = true;
						if(map[nx][ny]==nextNum) {
							queue.add(new Point(nx,ny,cnt+1,nextNum+1,type));
						}else {
							queue.add(new Point(nx,ny,cnt+1,nextNum,type));
						}
					}
					
				}
			}
			
		}
	}
}
