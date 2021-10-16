package week30.BOJ_20058_G4_마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_G4_마법사상어와파이어볼 {
	static int[][] map, temp;
	static boolean[][] isVisited;
	static int N, Q, max, sum;
	static int[][] delta = {
			{-1,0},
			{1,0},
			{0,-1},
			{0,1}
	};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = (int) Math.pow(2, Integer.parseInt(st.nextToken())); 
		Q = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		isVisited = new boolean[N][N];
		
		//초기 얼음
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		//파이어 스톰
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			
			fireStorm(L);
		}
		
		//파이어 스톰 시전후 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				checkMass(i, j);
			}
		}
		
		
		System.out.println(sum);
		System.out.println(max);
	}
	
	static void fireStorm(int L) {
		int length = (int) Math.pow(2, L);
		
		int step = N / length;	
		
		//회전 결과를 저장할 배열
		temp = new int[N][N];
		
		//각 구역별 회전 실행
		for(int i = 0; i < step; i++) {
			for (int j = 0; j < step; j++) {
				int startY = length * i;
				int startX = length * j;
				int endY = length * (i+1)-1;
				int endX = length * (j+1)-1;
				
				rotate(startX, startY, endX, endY);
			}
		}
		
		//회전이 완료된 경우
		map = temp;
		
		//녹는 얼음 확인
		melt();
	}
	
	static void rotate(int sx, int sy, int ex, int ey) {
		for (int i = sy; i <= ey; i++) {
			for (int j = sx; j <= ex; j++) {
				temp[j - sx + sy][ex - i + sy] = map[i][j];
			}
		}
	}
	
	static void melt() {
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = 0;
				
				if(map[i][j] <= 0)	continue;
				
				for (int[] d : delta) {
					int ny = i + d[0];
					int nx = j + d[1];
					
					if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= 0)	continue;
					
					count++;
				}
				
				if(count < 3)	q.offer(new int[] {i, j});
			}
		}
		
		//녹는 얼음은 마지막에 일괄 처리
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			map[p[0]][p[1]]--;
		}
	}
	
	static void checkMass(int i, int j) {
		if(map[i][j] <= 0) {
			isVisited[i][j] = true;
			return;
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {i, j});
		
		int num = 0;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			
			if(isVisited[y][x])	continue;
			
			num++;
			sum += map[y][x];
			isVisited[y][x] = true;
			
			for (int[] d : delta) {
				int ny = y + d[0];
				int nx = x + d[1];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] <= 0 || isVisited[ny][nx])	continue;
				
				q.offer(new int[] {ny, nx});
			}
		}
		
		max = Math.max(max, num);
	}
}
