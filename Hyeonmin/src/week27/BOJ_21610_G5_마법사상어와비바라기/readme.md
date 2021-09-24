## BOJ 21610 G5 마법사 상어와 비바라기
- 구현
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/21610

마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 마법을 할 수 있다. 오늘 새로 배운 마법은 비바라기이다. 비바라기를 시전하면 하늘에 비구름을 만들 수 있다. 오늘은 비바라기를 크기가 N×N인 격자에서 연습하려고 한다. 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다. (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.

격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.

비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.

모든 구름이 di 방향으로 si칸 이동한다.
각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
구름이 모두 사라진다.
2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.


#### 입력
첫째 줄에 N, M이 주어진다.

둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.

다음 M개의 줄에는 이동의 정보 di, si가 순서대로 한 줄에 하나씩 주어진다.

#### 출력
첫째 줄에 M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.

###  💡 풀이

int[]를 저장하는 큐 `q`에 현재 구름의 위치와 이동한 구름의 위치를 저장하며 풀었다.

큐의 크기를 `size`에 저장하여 for문을 반복하여 구름을 이동 시키고 이동시킨 위치를 `q`에 저장한다

for문의 반목이 끝나면 `waterBug`를 실행해 `q`에 있는 위치에서 대각선위치에 물이 있는 수 만큼 `map`의 수를 증가시킨다.

그 후 `map`을 순회하여 크기가 2이상인 경우 구름을 만들어 `q`에 해당 위치를 저장한다

위 과정을 반복후 결과를 출력한다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_G5_마법사상어와비바라기 {
	static int N,M;
	static int[][] map;
	static boolean[][] isCloud;
	static Queue<int[]> q = new LinkedList<>();
	//좌 좌상 상 우상 우 우하 하 좌하
	static int[] dx = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,0,-1,-1,-1,0,1,1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작 구름 위치
		q.offer(new int[] {0,N-1});
		q.offer(new int[] {1,N-1});
		q.offer(new int[] {0,N-2});
		q.offer(new int[] {1,N-2});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int dir = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken())%N;
			
			//이번에 생긴 구름이 이동한 위치를 체크할 배열
			isCloud = new boolean[N][N];
			
			int size = q.size();
			
			for (int j = 0; j < size; j++) {
				int[] p = q.poll();
				
				int nx = (p[0] +dx[dir]*num) < 0 ? N + (p[0] +dx[dir]*num) : (p[0] +dx[dir]*num)%N;
				int ny = (p[1] +dy[dir]*num) < 0 ? N + (p[1] +dy[dir]*num) : (p[1] +dy[dir]*num)%N;
				
				isCloud[ny][nx] = true;
				//비내림
				map[ny][nx]++;
				//물복사 버그 실행할 위치 저장
				q.offer(new int[] {nx,ny});
			}
			
			//물복사버그
			waterBug();
			//구름생성
			makeCloud();
		}
		
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count += map[i][j];
			}
		}
		
		System.out.println(count);
		
	}
	
	static void waterBug() {
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			int x = p[0];
			int y = p[1];
			
			int count = 0;
		
			//대각선 확인후 물복사
			for (int d = 2; d <= 8; d+=2) {
				int nx = x +dx[d];
				int ny = y +dy[d];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 0)	continue;
				
				count++;
			}
			
			map[y][x] += count;
		}
		
	}
	
	static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//2이상인 경우 구름 생성
				if(!isCloud[i][j] && map[i][j] >= 2) {
					map[i][j] -= 2;
					q.offer(new int[] {j, i});
				}
			}
		}
	}
}





```


<br>



메모리|시간
--|--
56540 KB|420 ms