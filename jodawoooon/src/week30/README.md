## BOJ 14500 G5 테트로미노

- gold5
- 구현



<br><br>


### 🔍 문제 설명

https://www.acmicpc.net/problem/14500

폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.

- 정사각형은 서로 겹치면 안 된다.
- 도형은 모두 연결되어 있어야 한다.
- 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.

정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.

[![img](https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14500/1.png)](https://commons.wikimedia.org/wiki/File:All_5_free_tetrominoes.svg)

아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.

테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.

테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.

<br>

#### ✔ 입력

첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)

둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.<br>

#### ✔ 출력

첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
<br>


<br>

###  💡 풀이

테트로미노를 두가지 종류로 분류하였다.

1. (i,j)에서 시작하여 4칸을 이동하여 탐색이 가능한 유형
2. (i,j)에서 시작하여 4칸을 재귀함수로 방문할 수 없는 유형 (ㅏ,ㅓ,ㅗ,ㅜ)



그리고 1번 유형은 완전탐색으로 4칸을 이동하여 얻을 수 있는 가장 큰 `max sum`을 찾았다.

```java
	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(sum, max);
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isIn(nx,ny)) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1,sum+map[nx][ny]);
			visited[nx][ny] = false;
		}
	}
```



2번 유형은  `if`문을 이용하여 ㅏ, ㅓ, ㅗ, ㅜ를 구분하여 경우의 수의  sum에 대해 `max`을 검사했다.

```java
	private static void find(int i, int j) {
		//ㅏ
		if(i-1>=0&&i+1<N&&j+1<M) {
			int sum = map[i][j] + map[i-1][j] + map[i+1][j] + map[i][j+1];
			max = Math.max(sum, max);
		}
		//ㅓ
		if(i-1>=0&&i+1<N&&j-1>=0) {
			int sum = map[i][j] + map[i-1][j] + map[i+1][j] + map[i][j-1];
			max = Math.max(sum, max);
		}
		//ㅗ
		if(i-1>=0&&j-1>=0&j+1<M) {
			int sum = map[i][j] + map[i-1][j] + map[i][j-1] + map[i][j+1];
			max = Math.max(sum, max);
		}
		//ㅜ
		if(i+1<N&&j-1>=0&j+1<M) {
			int sum = map[i][j] + map[i+1][j] + map[i][j-1] + map[i][j+1];
			max = Math.max(sum, max);
		}
	}
```



<br><br>

###  💬 소스코드

```java
package week30;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_G5_테트로미노 {
	static int N, M, map[][], max;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		
		for(int i = 0 ; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i,j,1,map[i][j]);
				find(i,j);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}

	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==4) {
			max = Math.max(sum, max);
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isIn(nx,ny)) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1,sum+map[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	private static void find(int i, int j) {
		//ㅏ
		if(i-1>=0&&i+1<N&&j+1<M) {
			int sum = map[i][j] + map[i-1][j] + map[i+1][j] + map[i][j+1];
			max = Math.max(sum, max);
		}
		//ㅓ
		if(i-1>=0&&i+1<N&&j-1>=0) {
			int sum = map[i][j] + map[i-1][j] + map[i+1][j] + map[i][j-1];
			max = Math.max(sum, max);
		}
		//ㅗ
		if(i-1>=0&&j-1>=0&j+1<M) {
			int sum = map[i][j] + map[i-1][j] + map[i][j-1] + map[i][j+1];
			max = Math.max(sum, max);
		}
		//ㅜ
		if(i+1<N&&j-1>=0&j+1<M) {
			int sum = map[i][j] + map[i+1][j] + map[i][j-1] + map[i][j+1];
			max = Math.max(sum, max);
		}
	}

	
	private static boolean isIn(int nx, int ny) {
		if(nx<0||ny<0||nx>=N||ny>=M) return false;
		return true;
	}

}

```

<br><br>


###  💯 채점 결과

메모리 33348KB

시간 704ms