package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
bfs로 풀었습니다.



- 과정
먼저 승객의 출발지,위치정보는 Info타입의 배열에 저장했고,
이 때 승객의 출발위치를 map에 idx로 표시해주었습니다.

1. 현 위치에서 승객들의 거리를 구하기 위해 bfs를 이용하여 getList()메소드를 구현했습니다. getList() 메소드는 승객들을 찾으면(map[x][y]>1 이면) list에 각 위치와 거리값을 저장하였고 현 연료값으로 갈 수 있는 승객을 모두 찾으면 이 list를 리턴합니다.
이 때 list의 size가 0이면, 태울 수 있는 승객이 없으므로 -1 출력후 종료합니다

2. list 정렬
문제에서 주어진대로 최단거리 순으로 list를 정렬했습니다

3. 승객 이동
조건을 만족하는 최단거리 승객을 선택 후, go메소드(bfs)로 승객을 이동시켰습니다.
go() 메소드는 승객을 목적지(dsX, dsY)까지 이동시킵니다. 목적지에 도착했다면 거리값을 리턴하고, 연료가 부족하거나 도착하지 못했다면 -1을 리턴합니다.

이 결과 성공적으로 목적지까지 이동시키지 못했다면 -1 출력 후 종료하고
성공적으로 승객을 이동시켰다면, 현 위치를 갱신하고 연료량을 갱신합니다.



- 어려웠던 점
처음에는 맵과 list의 idx를 활용하지 않고 getList()없이
모든 승객의 정보에 대해서 정렬 후, bfs로 각각의 거리를 모두 구한 뒤
최소 거리를 갱신해나가면서 모든 승객에 대한 정보 확인 후 최소 거리를 찾는 형식으로 list를 구현했습니다. 그런식으로 구현했더니 결과가 상당히 좋지않아서 맵을 활용하는 방법으로 변경했습니다.
또한 bfs를 종료할때 목적지까지 성공적으로 이동시키는 부분을 제대로 체크하지 않아서 틀렸었습니다.


- 결과
33160	288
*/
public class Main_BJ_19238_스타트택시3 {
	
	
	static int N, M, energy, startX, startY, distance[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};

	static Info[] infos;
	static int[][] map;
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
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		//운전을 시작하는 칸
		//M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다.
		
		infos = new Info[M+2]; //승객정보 저장할 배열
		
		for (int i = 2; i <=M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x1= Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			map[x1][y1] = i; //지도에 승객의 출발위치를 승객의 idx로 표시한다.
			infos[i] = new Info(x1,y1,x2,y2); //해당 idx 승객의 출발지,도착지 정보 기억하기

		}
		
		
		
		for (int i = 0; i < M; i++) {

			ArrayList<Point> list = getList(); 
			//startX,startY에서 승객들의 거리를 계산하여 list에 저장하는 method

			
			if(list.size()==0) { //태울수있는 승객이 없으면 종료
				System.out.println(-1); 
				return;
			}
			
			Collections.sort(list, new Comparator<Point>() {
				//최단거리 순으로 정렬한다
				@Override
				public int compare(Point o1, Point o2) {
					if(o1.dis==o2.dis) { //그런승객이 여러명이면
						if(o1.x==o2.x) { //행번호 -> 열번호 순으로 정렬한다.
							return o1.y-o2.y; 
						}else {
							return o1.x-o2.x;
						}
					}else {
						return o1.dis-o2.dis;
					}
				}

			}); //승객 정렬
			
			Point p = list.get(0); //승객을 선택함
			map[p.x][p.y] = 0; //맵에서 제거
			
			
			energy-=p.dis;
			startX = p.x;
			startY = p.y;
			int dsX = infos[p.idx].dsX;
			int dsY = infos[p.idx].dsY;
			int moveDis = go(dsX, dsY);
			if(moveDis==-1) { //목적지로 성공적으로 이동시키지 못했다
				System.out.println(-1); //업무를 끝낸다.
				return;
			}
			
			//성공적으로 승객을 이동시켰다면
			energy -= moveDis; //연료 일단 감소시킴
			startX = dsX; //현재 위치 갱신
			startY = dsY;
			energy += moveDis*2; //소모한 연료량의 2배가 충전된다
		}
		
		System.out.println(energy);
		
	}
	


	private static ArrayList<Point> getList() {
		//현 위치에서 승객들을 찾아서 list에 저장한다.
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startX,startY,0));
		visited[startX][startY] = true;
		
		ArrayList<Point> list = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node n = queue.poll();
				int x = n.x;
				int y = n.y;
				int dis = n.dis;
				
				if(energy-dis<0) break; //연료없으면 종료
				if(map[x][y]>1) { //이동중에 만난 승객 (승객의 idx는 2부터)
					list.add(new Point(map[x][y], x,y,dis)); //리스트에 저장
					
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(!isIn(nx,ny)) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny]==1) continue;
					
					visited[nx][ny] = true;
					queue.add(new Node(nx,ny,dis+1));
				}
				
			}
			
		}
		
		return list;
	}



	private static int go(int dsX, int dsY) {
		//승객을 이동시킨다.
		
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(startX,startY,0));
		visited[startX][startY] = true;
		
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int x = n.x;
			int y = n.y;
			int dis = n.dis;
			if(energy-dis<0) { //연료가 부족하면 -1리턴
				return -1;
			}
			
			if(x==dsX&&y==dsY) { //목적지에 도착했다면 distance값 리턴
				return dis;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==1) continue;
				visited[nx][ny] = true;
				queue.add(new Node(nx,ny,dis+1));
			}
		}
		
		return -1; //도착하지 못했다.
	}



	private static boolean isIn(int nx, int ny) {
		if(nx<=0||nx>N||ny<=0||ny>N) return false;
		return true;
	}

	static class Node{
		int x,y,dis;

		public Node(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		
	}
	
	static class Info {
		int stX,stY,dsX,dsY; //출발지, 도착지 좌표

		public Info(int stX, int stY, int dsX, int dsY) {
			super();
			this.stX = stX;
			this.stY = stY;
			this.dsX = dsX;
			this.dsY = dsY;

		}
		
	}
	
	static class Point {
		int idx, x,y,dis; //승객의 idx와 (x,y)좌표, dis값

		public Point(int idx, int x, int y, int dis) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

	}

	
}
