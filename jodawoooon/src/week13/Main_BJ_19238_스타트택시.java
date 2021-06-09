package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_19238_스타트택시 {
	static int N, M, energy, map[][], startX, startY;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static ArrayList<Info> infoList;
	static Queue<Node> queue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //목표량
		energy = Integer.parseInt(st.nextToken()); //초기연료
		
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//1은 벽 0은 빈칸
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		//운전을 시작하는 칸
		//M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다.
		
		infoList = new ArrayList<>();
		for (int i = 0; i <M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1= Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			infoList.add(new Info(x1,y1,x2,y2));
		}
		
		//백준이 태울 승객을 고를 때는 현재 위치에서 최단거리가 가장 짧은 승객을 고른다. 
		//그런 승객이 여러 명이면 그중 행 번호가 가장 작은 승객을,
		//그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.
		
		// 연료는 한 칸 이동할 때마다 1만큼 소모된다.
		//현재 위치에서 거리를 구해서.. queue에 집어넣고
		while(infoList.size()>0) {
			
			Collections.sort(infoList);
			int size = infoList.size();
			
			int next_idx = 0;
			int next_dis = Integer.MAX_VALUE;
			for (int i = 0; i < size; i++) {
				//거리를 구한다
				
			
				
				int nextX = infoList.get(i).stX;
				int nextY = infoList.get(i).stY;
				
				
				int dis = bfs(nextX, nextY);
	
				if(dis==-1) {
					// 모든 손님을 이동시킬 수 없는 경우에도 -1을 출력한다.
					System.out.println(-1);
					return;
				}
				else {
					if(next_dis > dis) {
						next_dis = dis;
						next_idx = i;
					}
				}
				
			}
			
			
			//다음으로 이동할 승객을 정했다.

			Info info = infoList.get(next_idx);
			int stX = info.stX;
			int stY = info.stY;
			int dsX = info.dsX;
			int dsY = info.dsY;
			
			if(energy-next_dis<0) {
				//이동할 연료가 부족하다면
				System.out.println(-1);
				return;
			}
			
			
			else {
				
				energy -= next_dis;
				//System.out.println(energy);
				startX = stX;
				startY = stY;
				int moveDis = bfs(dsX, dsY);
				
				if(moveDis==-1) {
					System.out.println(-1);
					return;
				}
				if(energy-moveDis<0) {
					System.out.println(-1);
					return;
				}
				energy -= moveDis;
				
				startX = dsX;
				startY = dsY;
				

				//System.out.println(energy);
				energy += (moveDis*2);
				
				//System.out.println(energy);
				
				infoList.remove(next_idx);
				
			}
			
		}
		
		System.out.println(energy);
		
	}
	

	private static int bfs( int nextX, int nextY) {
		
		
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(startX, startY, 0));
		visited[startX][startY]= true;
		while(!queue.isEmpty()) {
			
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			int dis = n.dis;
			if(x==nextX&&y==nextY) {
				//승객위치찾으면
				return dis;
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx>0&&ny>0&&nx<=N&&ny<=N) {
					if(!visited[nx][ny]) {
						//System.out.println(nx+" "+ny);
						if(map[nx][ny]==0) {
							visited[nx][ny] = true;
							queue.add(new Node(nx,ny,dis+1));
						
						}
					}
				}
			}
			
		}
		
		return -1;
		
	}


	static class Info implements Comparable<Info>{
		int stX, stY, dsX, dsY;

		public Info(int stX, int stY, int dsX, int dsY) {
			super();
			this.stX = stX;
			this.stY = stY;
			this.dsX = dsX;
			this.dsY = dsY;
		}

		@Override
		public int compareTo(Info o) {
			if(this.stX!=o.stX) {
				return this.stX - o.stX;
			}else {
				return this.stY - o.stY;
			}
		}
		
		
	}
	
	static class Node {
		int x,y,dis;

		public Node(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
		
	}
	
	
}
