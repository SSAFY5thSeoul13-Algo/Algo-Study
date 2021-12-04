## BOJ 17086 S2 아기 상어
- BFS
- silver2

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/17086

N×M 크기의 공간에 아기 상어 여러 마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 아기 상어가 최대 1마리 존재한다.

어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와의 거리이다. 두 칸의 거리는 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수이고, 이동은 인접한 8방향(대각선 포함)이 가능하다.

안전 거리가 가장 큰 칸을 구해보자. 


#### 입력
첫째 줄에 공간의 크기 N과 M(2 ≤ N, M ≤ 50)이 주어진다. 둘째 줄부터 N개의 줄에 공간의 상태가 주어지며, 0은 빈 칸, 1은 아기 상어가 있는 칸이다. 빈 칸의 개수가 한 개 이상인 입력만 주어진다.

#### 출력
첫째 줄에 안전 거리의 최댓값을 출력한다.

###  💡 풀이

상어의 위치들을 기준으로 bfs를 활용해 풀었다.

상어위 첫 위치를 기준으로 4방향에 대해서 bfs를 실행하며 각 시간마다 도착하는 위치를 `tempMap`에 저장한다.

해당 위치에 도착을 했을 때 `tempMap`의 값이 이전 위치에서의 `tempMap`의 값 +1 보다 큰 경우에 해당 위치의 갚을 갱신한다.

값을 갱신할 때 마다 `result`에 갱신한 값의 최대값을 저장하고 bfs가 모두 끝난 후에 `result` 값을 출력한다



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_S2_아기상어2 {
	static int[][] map;
	static int[][] tempMap;
	static int N, M, result;
	static int[][] delta = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		tempMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = Integer.MAX_VALUE;
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					q.offer(new int[] { i, j });
					tempMap[i][j] = 0;
				}
			}
		}

		bfs();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result = Math.max(result, tempMap[i][j]);
			}
		}

		System.out.println(result);
	}

	static void bfs() {

		while (!q.isEmpty()) {
			int[] p = q.poll();
			int py = p[0];
			int px = p[1];

			for (int d = 0; d < 8; d++) {
				int ny = py + delta[d][0];
				int nx = px + delta[d][1];

				if ((ny < 0 || nx < 0 || ny >= N || nx >= M) || tempMap[ny][nx] <= tempMap[py][px] +1 )	continue;

				tempMap[ny][nx] = tempMap[py][px] + 1;
				result = Math.max(result, tempMap[ny][nx]);
				q.offer(new int[] { ny, nx });
			}
		}
	}

}





```


<br>



메모리|시간
--|--
12108 KB|96 ms