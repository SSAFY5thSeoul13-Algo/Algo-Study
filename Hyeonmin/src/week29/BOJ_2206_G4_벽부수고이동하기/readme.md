## BOJ 2206 G4 벽 부수고 이동하기
- BFS
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2206

N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.

한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.


#### 입력
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

#### 출력
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

###  💡 풀이

3차원 배열 `isVisited`로 방문지역을 체크하며 bfs로 풀었다

`isVistied[y][x][cnt]`로  y,x좌표에 도착을 했을 때 벽을 부술 수 있는 횟수 cnt까지를 인덱스로 하여서 방문을 체크한다

벽을 부술 수 있는 경우가 1번 남은 경우에는 `isVisted[y][x][0]`과 `isVisted[y][x][1]` 모두 true 값으로 변경한다 

```java
			for (int i = cnt; i >= 0; i--) {
				isVisited[y][x][i] = true;
			}
```

목표 위치에 도착한 경우 해당 경우의 `time`값을 최소 거리 `min`에 저장한 후 해당 값을 출력한다

<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G4_벽부수고이동하기 {
	static int N,M,min = -1;
	static char[][] map;
	static boolean[][][] isVisited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		isVisited = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		bfs();
		
		System.out.println(min);

	}
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {0,0,1,1});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int y = p[0];
			int x = p[1];
			int cnt = p[2];
			int time = p[3];
			
			if(y == N-1 && x == M-1) {
				min = time;
				break;
			}
			
			if(isVisited[y][x][cnt]) {
				continue;
			}
			
			for (int i = cnt; i >= 0; i--) {
				isVisited[y][x][i] = true;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= M)	continue;
				
				if(map[ny][nx] == '1') {
					if(cnt == 1) {
						q.offer(new int[] {ny,nx,0,time+1});
					}
				}
				else {
					q.offer(new int[] {ny,nx,cnt,time+1});
				}
			}
		}
	}

}





```


<br>



메모리|시간
--|--
276956 KB|580 ms