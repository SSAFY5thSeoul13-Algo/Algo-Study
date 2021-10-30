## BOJ 9663 G5 N-Queen
- 백트래킹
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/9663

N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

#### 출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

###  💡 풀이

각 행에 하나의 퀸 만 놓을 수 있고 모든 행에 퀸이 있는 경우에만 퀸의 수가 `N`이 된다

1행부터 N행까지 순서대로 퀸을 놓을 것이기 때문에 퀸을 놓은 경우 그 퀸으로 인해 퀸을 못놓게 되는 자리는 그 행 기준으로 같거나 밑인 행만 고려하면 된다

`map`배열에 퀸을 놓거나 놓은 퀸으로 인해서 새 퀸을 못놓는 경우 +1을 하여 체크한다

dfs방식으로 위 방법을 반복하여 마지막 행까지 실행이 완료된 경우를 구해서 답을 구한다



<br><br>

###  💡 소스코드
```java
package week32.BOJ_9663_G5_NQueen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9663_G5_NQueen {
	static int[][] map;
	static boolean[][] isVisited;
	static int N, result;
	static int[][] delta= {
			{1,0},
			{1,-1},
			{1,1},
	};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		isVisited = new boolean[N][N];
		
		dfs(0, 0);
		
		System.out.println(result);
	}
	
	static void dfs(int y, int count) {
		if(y == N) {
			if(N == count)	result++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if(map[y][j] == 0) {
				inputVisit(y, j);
				dfs(y+1, count+1);
				deleteVisit(y, j);
			}
		}
	}
	
	static void inputVisit(int y, int x) {
		map[y][x]++;
		
		for (int d = 0; d < 3; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			while(isIn(ny, nx)) {
				map[ny][nx]++;
				
				ny += delta[d][0];
				nx += delta[d][1];
			}
		}
	}
	
	static void deleteVisit(int y, int x) {
		map[y][x]--;
		
		for (int d = 0; d < 3; d++) {
			int ny = y + delta[d][0];
			int nx = x + delta[d][1];
			
			while(isIn(ny, nx)) {
				map[ny][nx]--;
				
				ny += delta[d][0];
				nx += delta[d][1];
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N)	return false;
		
		return true;
	}

}





```


<br>



메모리|시간
--|--
14536 KB|2696 ms