## BOJ 19236 G2 청소년 상어
- 시뮬레이션
- 백트래킹
- gold2

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/19236

4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다. 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다. 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다. 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다. 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.

물고기는 번호가 작은 물고기부터 순서대로 이동한다. 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다. 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.


#### 입력
첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다. 물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다. 방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.

#### 출력
상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.

###  💡 풀이

각 단계별로 재귀호출을 사용해서 풀이한다

각 단계에서 공간의 상태와 물고기의 상태를 복사하여 다음 단계에 넘겨주는 방식으로 데이터를 유지시킨다

<br><br>

###  💡 소스코드
```java
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





```


<br>



메모리|시간
--|--
11632 KB|84 ms