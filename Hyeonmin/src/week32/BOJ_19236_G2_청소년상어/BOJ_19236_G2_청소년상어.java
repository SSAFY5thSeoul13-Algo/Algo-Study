package week32.BOJ_19236_G2_청소년상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19236_G2_청소년상어 {
	static int[][] map = new int[4][4];
	static boolean[] isDead = new boolean[17];
	static Fish[] fishArr = new Fish[17];
	static Fish shark;
	static int result;
	static int[][] delta= {
			{-1,0},
			{-1,-1},
			{0,-1},
			{1,-1},
			{1,0},
			{1,1},
			{0,1},
			{-1,1},
	};
	
	static class Fish{
		int dir;
		int y;
		int x;
		
		public Fish(int dir, int y, int x) {
			super();
			this.dir = dir;
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				fishArr[num] = new Fish(dir-1,i,j);
				map[i][j] = num;
			}
		}
		
		int num = map[0][0];
		
		Fish fish = fishArr[num];
		
		//상어 첫 먹이
		shark = new Fish(fish.dir,0,0);
		
		map[0][0] = -1;
		isDead[num] = true;
		
		dfs(num, map, fishArr);
		
		System.out.println(result);
	}
	
	static void dfs(int count, int[][] map, Fish[] fishArr) {
		result = Math.max(result, count);
		
		//물고기 이동
		for (int i = 1; i <= 16; i++) {
			if(isDead[i])	continue;
			
			Fish fish = fishArr[i];
			
			for (int d = 0; d < 8; d++) {
				int dir = (fish.dir + d)%8;
				
				int y = fish.y;
				int x = fish.x;
				
				int ny = y + delta[dir][0];
				int nx = x + delta[dir][1];
				
				if(!isIn(ny, nx) || map[ny][nx] == -1)	continue;
				
				int tempNum = map[ny][nx];
				
				if(tempNum == 0) {
					map[ny][nx] = i;
					fish.y = ny;
					fish.x = nx;
					fish.dir = dir;
					
					map[y][x] = 0;
				}
				else {
					Fish tempFish = fishArr[tempNum];
					
					map[ny][nx] = i;
					fish.y = ny;
					fish.x = nx;
					fish.dir = dir;
					
					map[y][x] = tempNum;
					tempFish.y = y;
					tempFish.x = x;
				}
				
				break;
			}
		}
		
		int y = shark.y;
		int x = shark.x;
		int dir = shark.dir;
		
		int ny = y;
		int nx = x;
		
		//상어 이동
		while(true) {
			ny += delta[dir][0];
			nx += delta[dir][1];
			
			if(!isIn(ny,nx))	break;
			
			if(map[ny][nx] == 0)	continue;
			
			int num = map[ny][nx];
			Fish fish = fishArr[num];
			
			int[][] tempMap = copyMap(map); 
			Fish[] tempFish = copyArr(fishArr);
			
			isDead[num] = true;
			tempMap[ny][nx] = -1;
			tempMap[y][x] = 0;
			shark.y = ny;
			shark.x = nx;
			shark.dir = fish.dir;
			
			dfs(count + num, tempMap, tempFish);
			
			isDead[num] = false;
			shark.y = y;
			shark.x = x;
			shark.dir = dir;
		}
	}
	
	static int[][] copyMap(int[][] map){
		int[][] tempMap = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
		
		return tempMap;
	}
	
	static Fish[] copyArr(Fish[] fishArr) {
		Fish[] tempFishArr = new Fish[17];
		
		for (int i = 1; i <= 16; i++) {
			Fish fish = fishArr[i];
			tempFishArr[i] = new Fish(fish.dir,fish.y,fish.x);		
		}
		
		return tempFishArr;
	}
	
	static boolean isIn(int y, int x){
		if(y < 0 || x < 0 || y >= 4 || x >= 4) {
			return false;
		}
		
		return true;
	}
}
