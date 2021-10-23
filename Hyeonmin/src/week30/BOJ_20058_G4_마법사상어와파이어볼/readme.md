## BOJ 20058 G4 마법사 상어와 파이어스톰
- 구현
- 그래프 이론
- 그래프 탐색
- 너비 우선 탐색
- 깊이 우선 탐색
- 시뮬레이션
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/20058

마법사 상어는 파이어볼과 토네이도를 조합해 파이어스톰을 시전할 수 있다. 오늘은 파이어스톰을 크기가 2N × 2N인 격자로 나누어진 얼음판에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 얼음의 양을 의미한다. A[r][c]가 0인 경우 얼음이 없는 것이다.

파이어스톰을 시전하려면 시전할 때마다 단계 L을 결정해야 한다. 파이어스톰은 먼저 격자를 2L × 2L 크기의 부분 격자로 나눈다. 그 후, 모든 부분 격자를 시계 방향으로 90도 회전시킨다. 이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다. (r, c)와 인접한 칸은 (r-1, c), (r+1, c), (r, c-1), (r, c+1)이다. 아래 그림의 칸에 적힌 정수는 칸을 구분하기 위해 적은 정수이다.

		
마법을 시전하기 전	L = 1	L = 2
마법사 상어는 파이어스톰을 총 Q번 시전하려고 한다. 모든 파이어스톰을 시전한 후, 다음 2가지를 구해보자.

남아있는 얼음 A[r][c]의 합
남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
얼음이 있는 칸이 얼음이 있는 칸과 인접해 있으면, 두 칸을 연결되어 있다고 한다. 덩어리는 연결된 칸의 집합이다.


#### 입력
첫째 줄에 N과 Q가 주어진다. 둘째 줄부터 2N개의 줄에는 격자의 각 칸에 있는 얼음의 양이 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.

마지막 줄에는 마법사 상어가 시전한 단계 L1, L2, ..., LQ가 순서대로 주어진다.

#### 출력
첫째 줄에 남아있는 얼음 A[r][c]의 합을 출력하고, 둘째 줄에 가장 큰 덩어리가 차지하는 칸의 개수를 출력한다. 단, 덩어리가 없으면 0을 출력한다.

###  💡 풀이

파이어 스톰을 시전한 `rotate` 메소드로 회전 -> `melt` 메소드로 녹는 얼음을 확인하여 `L`만큼 반복하였다

얼음이 녹을 때는 모든 위치를 확인한 다음에 한번에 녹는 것이므로 `queue`를 사용하였다

파이어 스톰을 시전이 끝이 나면 bfs를 사용하여 전체 탐색을 하며 얼음의 합 `sum`을 구하며 동시에 가장 큰 덩어리가 차지하는 칸 `max`를 구하였다




<br><br>

###  💡 소스코드
```java
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






```


<br>



메모리|시간
--|--
37428 KB|328 ms