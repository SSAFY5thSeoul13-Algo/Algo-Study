## BOJ 14500 G5 테트로미노
- 시뮬레이션
- 구현
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/14500

폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

정사각형은 서로 겹치면 안 된다.
도형은 모두 연결되어 있어야 한다.
정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.


아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.

테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.

테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.


#### 입력
첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)

둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.

#### 출력
첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.

###  💡 풀이

2가지 방법으로 테트로미노가 놓인 칸을 확인한다

1. dfs를 사용하여 각 좌표에서 4칸을 이동한 후에 확인

```java
	static void dfs(int y, int x, int depth, int sum) {
		//너비가 4가 되는 순간의 숫자의 합을 비교
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		isVisited[y][x] = true;
		
		for (int[] del : delta) {
			int ny = y + del[0];
			int nx = x + del[1];
			
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || isVisited[ny][nx])	continue;
			
			dfs(ny, nx, depth + 1, sum + map[ny][nx]);
		}
		
		isVisited[y][x] = false;
	}
```


2. dfs로 확인이 불가능한 ㅓ,ㅏ,ㅗ,ㅜ 모양을 따로 확인

```java
	static void checkTetro(int y, int x) {

		for (int[][] tetromino : tetrominoes) {
			int sum = map[y][x];
			Loop: for (int[] del : tetromino) {
				int ny = y + del[0];
				int nx = x + del[1];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					break Loop;

				sum += map[ny][nx];
			}
			max = Math.max(max, sum);
		}
	}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_G5_테트로미노 {
	static int N, M, max;
	static int[][] map;
	//아 오 위 왼
	static int[][] delta = {
			{1,0},
			{0,1},
			{-1,0},
			{0,-1}
	};
	static int[][][] tetrominoes = {
			{{0,-1},{0,1},{1,0}},//왼오아
			{{0,-1},{1,0},{-1,0}},//왼아위
			{{0,-1},{0,1},{-1,0}},//왼오위
			{{0,1},{1,0},{-1,0}}//아아위
	};
	static boolean[][] isVisited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 1, map[i][j]);
				checkSquare(i, j);
			}
		}
		
		System.out.println(max);

	}
	
	static void dfs(int y, int x, int depth, int sum) {
		//너비가 4가 되는 순간의 숫자의 합을 비교
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		isVisited[y][x] = true;
		
		for (int[] del : delta) {
			int ny = y + del[0];
			int nx = x + del[1];
			
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || isVisited[ny][nx])	continue;
			
			dfs(ny, nx, depth + 1, sum + map[ny][nx]);
		}
		
		isVisited[y][x] = false;
	}
	
	//dfs 4칸으로 확인 할 수 없는 모양을 체크
	static void checkSquare(int y, int x) {	
		
		for (int[][] tetromino : tetrominoes) {
			int sum = map[y][x];
			Loop:for (int[] del : tetromino) {
				int ny = y + del[0];
				int nx = x + del[1];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= M)	break Loop;
				
				sum += map[ny][nx];
			}
			max = Math.max(max, sum);
		}
	}
}





```


<br>



메모리|시간
--|--
33812 KB|656 ms