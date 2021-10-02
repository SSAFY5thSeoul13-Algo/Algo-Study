## BOJ 2206 G4 벽 부수고 이동하기
- BFS
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2206

N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.

숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.


#### 입력
첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

#### 출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.

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