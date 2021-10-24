package week31.BOJ_17837_G2_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17837_G2_새로운게임2 {
	static int[][] delta= {
			{0,0},
			{0,1},
			{0,-1},
			{-1,0},
			{1,0}
	};
	static int[][] map;
	static List<Integer>[][] horseMap;
	static int N, K, time;
	static List<Node> list = new ArrayList<>();
	
	static class Node{
		int y;
		int x;
		int num;
		int dir;
		
		public Node(int y, int x, int num, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		horseMap = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horseMap[i][j] = new ArrayList<>();
			}
		}
		
		//더비 값
		list.add(new Node(0,0,0,0));
		
		//각 말의 정보와 위치 저장
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			list.add(new Node(y, x, i, dir));
			horseMap[y][x].add(i);
		}
		
		Loop:while(true) {
			time++;
			
			if(time > 1000)	break;
			
			//쌓인 말의 수
			int size = 0;
			
			for (Node node : list) {
				int y = node.y;
				int x = node.x;
				int num = node.num;
				int dir = node.dir;
				
				//더미 제외
				if(num == 0)	continue;
				
				int ny = y + delta[dir][0];
				int nx = x + delta[dir][1];
				
				boolean isReverse = false;
				
				//범위 밖으로 향하는 경우
				if(!isIn(ny, nx)) {
					dir = node.dir = (dir%2 == 0)? dir-1 :dir+1;
					
					ny = y + delta[dir][0];
					nx = x + delta[dir][1];
					
					if(map[ny][nx] == 2) {
						continue;
					}
					else if(map[ny][nx] == 1) {
						isReverse = true;
					}
				}
				//파란색
				else if(map[ny][nx] == 2) {
					dir = node.dir = (dir%2 == 0)? dir-1 :dir+1;
					
					ny = y + delta[dir][0];
					nx = x + delta[dir][1];
					
					if(!isIn(ny, nx) || map[ny][nx] == 2) {
						continue;
					}
					else if(map[ny][nx] == 1) {
						isReverse = true;
					}
				}
				//빨간색
				else if(map[ny][nx] == 1) {
					isReverse = true;
				}
				
				boolean isTarget = false;
				
				List<Integer> tempList = new ArrayList<>();
				
				for (int idx = 0; idx < horseMap[y][x].size(); idx++) {
					Node horse = list.get(horseMap[y][x].get(idx));
					
					if(horse.num == num)	isTarget = true;
					
					//이번에 움직이려는 말보다 아래에 있는 말인 경우
					if(!isTarget)	continue;
					
					//이동할 말 번호 저장
					tempList.add(horse.num);
					//좌표 수정
					horse.y = ny;
					horse.x = nx;
					
					horseMap[y][x].remove(idx--);
				}
				
				//빨간색으로 이동하는 경우
				if(isReverse) {
					Collections.reverse(tempList);
				}
				
				//이번에 이동되는 말을 모두 이동
				for (int horseNum: tempList) {
					horseMap[ny][nx].add(horseNum);
					size = Math.max(size, horseMap[ny][nx].size());
				}
				
				//쌓인 말의 수가 4 이상인 경우
				if(size >= 4) break Loop;
			}
		}
		
		System.out.println(time > 1000 ? -1 : time);
	}
	
	static boolean isIn(int y, int x) {
		if(y < 1 || x < 1 || y > N || x > N)	return false;
		
		return true;
	}

}
