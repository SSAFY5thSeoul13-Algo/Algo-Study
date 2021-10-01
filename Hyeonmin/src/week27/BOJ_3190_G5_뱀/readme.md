## BOJ 3190 G5 뱀
- 규현
- 덱
- 큐
- gold5

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/3190

 'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.


#### 입력
첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)

다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.

다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)

다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.

#### 출력
첫째 줄에 게임이 몇 초에 끝나는지 출력한다.

###  💡 풀이

뱀이 현재 위치하고 있는 위치는 Deque에 저장을 하였다

사과의 위치는 `map`에 2로 표시를 하고 뱀이 위치하고 있는 위치는 1로 표시를 하였다

```java
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			//사과는 2로 표시
			map[x][y] = 2;
		}
		
		dq.offerLast(new int[] {1,1});
		map[1][1] = 1;
```

방향 전환을 하는 경우의 정보는 Queue에 저장을 해서 풀었다

```java
		//방향 변화 입력
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			
			q.offer(new Node(time, dir));
		}
```

새로 이동한 곳에 사과가 없으면 `dq`에서 꼬리 부분의 데이터를 삭제하고 `map`의 해당 위치의 값을 0으로 바꿔준다

```java
			if(map[nx][ny] == 2) {
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
			else {
				int[] deletePoint = dq.pollFirst();
				
				map[deletePoint[0]][deletePoint[1]] = 0;
				
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_G5_뱀 {
	//뱀 몸통
	static Deque<int[]> dq = new ArrayDeque<>();
	//방향 변하는 시기
	static Queue<Node> q = new LinkedList<>();;
	static int N, K, L, time=0, dir=0;
	static int[][] map;
	//우 하 좌 상
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node{
		int time;
		char dir;
		
		public Node(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			//사과는 2로 표시
			map[x][y] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		
		//방향 변화 입력
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			
			q.offer(new Node(time, dir));
		}
		
		//시작 위치
		dq.offerLast(new int[] {1,1});
		map[1][1] = 1;
		
		while(true) {
			time++;
			int[] point = dq.peekLast();
			
			int nx = point[0] + dx[dir];
			int ny = point[1] + dy[dir];
			
			//범위 밖
			if(nx<1 || ny<1 || nx>N || ny>N || map[nx][ny] == 1)	break;
			
			//사과를 먹는 경우
			if(map[nx][ny] == 2) {
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
			else {
				int[] deletePoint = dq.pollFirst();
				
				map[deletePoint[0]][deletePoint[1]] = 0;
				
				map[nx][ny] = 1;
				dq.offerLast(new int[] {nx,ny});
			}
			
			if(!q.isEmpty()) {
				Node node = q.peek();
				
				//방향을 바꾸는 경우
				if(node.time == time) {
					q.poll();
					
					//좌
					if(node.dir == 'L') {
						dir = dir == 0 ? 3 : dir-1;
					}
					//우
					else if(node.dir == 'D'){
						dir = dir == 3 ? 0 : dir+1;
					}
				}
			}
			
		}
		
		System.out.println(time);

	}

}





```


<br>



메모리|시간
--|--
11932 KB|84 ms