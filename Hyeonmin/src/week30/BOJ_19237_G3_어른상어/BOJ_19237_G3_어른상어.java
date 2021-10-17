package week30.BOJ_19237_G3_어른상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19237_G3_어른상어 {
	//각 위치의 정보
	static class Node{
		int num;
		int time;
		
		public Node(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
	}
	
	//상어의 정보
	static class Shark{
		int y;
		int x;
		int num;
		int dir; 
		boolean isDead = false;
		
		public Shark(int y, int x, int num, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
	}
	
	static int N, M, K, time, deadCount;
	//이동 예정인 상어
	static Queue<Shark> sharkQ = new LinkedList<>();
	//상어 배열
	static Shark[] sharkArr;
	static Node[][] map;
	//각 상어의 방향에 따른 다음 이동 우선순위
	static List<List<List<Integer>>> priorityList = new ArrayList<>();
	static int[][] delta= {
			{0,0},
			{-1,0},
			{1,0},
			{0,-1},
			{0,1}
	};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Node[N][N];
		sharkArr = new Shark[M+1];
		sharkArr[0] = new Shark(0,0,0,0);
		
		//상어와 맵 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				map[i][j] = new Node(num,0);
				
				if(num !=0) {
					sharkArr[num] = new Shark(i,j,num,0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i <= M; i++) {
			priorityList.add(new ArrayList<List<Integer>>());
		}
		
		//상어의 초기 방향
		for (int i = 1; i <= M; i++) {
			Shark shark = sharkArr[i];
			shark.dir = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 5; j++) {
				priorityList.get(i).add(new ArrayList<Integer>());
			}
		}

		//각 상어의 우선순위 저장
		for (int i = 0; i < 4*M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = i/4+1;
			int dir = i%4+1;
			
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				priorityList.get(idx).get(dir).add(num);
			}
		}
		
		
		while(deadCount < M - 1 && time <= 1000) {
			//각 상어 이동위치 탐색
			for (int i = 1; i <= M; i++) {
				Shark shark = sharkArr[i];
				
				if(shark.isDead)	continue;
				
				//주변에 비곳으로 이동
				boolean isFound = findEmpty(shark, i);
				
				//내 냄새가 있는 곳으로 이동
				if(!isFound)	findMySmell(shark, i);
			}
			
			time++;
			
			//각 상어 이동
			move();
		}
		
		System.out.println(time > 1000 ? -1 : time);
	}
	
	//각 상어 이동
	static void move() {
		while(!sharkQ.isEmpty()) {
			Shark shark = sharkQ.poll();
			int y = shark.y;
			int x = shark.x;
			
			Node node = map[y][x];

			//죽음
			if(node.time == time && node.num != 0 && node.num < shark.num) {
				shark.isDead = true;
				deadCount++;
			}
			else {
				node.num = shark.num;
				node.time = time;
			}
		}
	}
	
	static boolean findEmpty(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//냄새가 없는 경우
				if(time - node.time >= K || node.num == 0) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	static void findMySmell(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//내 냄새가 있는 경우
				if(time - node.time < K && node.num == shark.num) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return;
				}
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N)	return false;
		
		return true;
	}
}
