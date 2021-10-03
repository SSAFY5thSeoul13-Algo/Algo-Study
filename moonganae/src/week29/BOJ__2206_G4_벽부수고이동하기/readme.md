## BAEKJOON GOLD4 2206 벽 부수고 이동하기
- BFS
- Gold 4
- https://www.acmicpc.net/problem/2206
<br>

### 문제설명

> N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

### 입력
- 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.



### 출력
- 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

### 입출력 예

#### 예제 1
입력
```
6 4
0100
1110
1000
0000
0111
0000
```
출력
```
15
```

### 풀이 및 과정

최단거리를 구하는 문제이기 때문에 BFS를 사용하여 문제를 해결하였습니다.

BFS를 수행하는 하나의 Point는 위치좌표(y,x)와 벽부수기 찬스를 가진 opp를 멤버변수로 가집니댜.

3차원 vis배열을 통해서 벽부술 기회를 가질때와 가지지 않을때를 구분하여 경로를 지정합니다.

```java
// 벽이없다면 그냥 이동
if(map[zy][zx] == 0) {
	q.offer(new Point(zy,zx, cur.opp));
	vis[zy][zx][cur.opp] = vis[cur.y][cur.x][cur.opp] + 1;
}
// 벽이있고, 벽을 부술찬스가 있다면 이동
else if(cur.opp==1) {
	q.offer(new Point(zy, zx, 0));
	vis[zy][zx][0] = vis[cur.y][cur.x][cur.opp] + 1;
}
```

### 소스코드
```java
package week29.BOJ__2206_G4_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;		/*  */
	static int[][][] vis;	/* 방문체크 및 경로길이 */
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		/*
		 * vis[y][x][1] : 벽부수기 안했을때의 길이
		 * vis[y][x][0] : 벽부수기 했을때의 길이
		 * */
		vis = new int[N][M][2];

		for(int i=0; i<N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				map[i][j++] = c - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static int bfs() {
		
		if(N==1 && M==1) return 1;
		
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(0,0,1));
		vis[0][0][0] = 1;
		vis[0][0][1] = 1;
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			if(cur.y == N-1 && cur.x == M-1) {
				return vis[cur.y][cur.x][cur.opp];
			}
			
			for(int z=0; z<4; z++) {
				
				int zy = cur.y + dy[z];
				int zx = cur.x + dx[z];
				
				if(zy<0 || zx<0 || zy>=N || zx>=M) continue;
				if(vis[zy][zx][cur.opp] > 0) continue;
				
				// 벽이없다면 그냥 이동
				if(map[zy][zx] == 0) {
					q.offer(new Point(zy,zx, cur.opp));
					vis[zy][zx][cur.opp] = vis[cur.y][cur.x][cur.opp] + 1;
				}
				// 벽이있고, 벽을 부술찬스가 있다면 이동
				else if(cur.opp==1) {
					q.offer(new Point(zy, zx, 0));
					vis[zy][zx][0] = vis[cur.y][cur.x][cur.opp] + 1;
				}
			}
		}
		
		return -1;
	}
	
	static class Point {
		int y, x, opp;

		public Point(int y, int x, int opp) {
			super();
			this.y = y;
			this.x = x;
			this.opp = opp;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + ", opp=" + opp + "]";
		}
	}
}

```

### 결과
```
메모리 : 146864KB	
시간 : 640ms
```
