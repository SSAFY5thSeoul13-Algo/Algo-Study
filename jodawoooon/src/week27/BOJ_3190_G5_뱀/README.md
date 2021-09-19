## BOJ 3190 G5 뱀
- 구현
- gold5



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/3190

 'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.

게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.

뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.

먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.


<br>

#### ✔ 입력
첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)

다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.

다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)

다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
<br>

#### ✔ 출력
첫째 줄에 게임이 몇 초에 끝나는지 출력한다.
<br>


<br>

###  💡 풀이

1. 뱀의 회전 명령을 저장하기 위해 `HashMap`을 이용했다. x index로 부터 C를 알아내기 위해서이다.


2. 뱀은 1,1부터 이동시켰고, 자신의 몸과 부딪히는지 체크하기 위해 board 위에 뱀의 위치를 -1로 체크했다.

3. 뱀의 위치를 저장할 때, 꼬리부분 (뒤쪽)에서 poll하기 위해서 `Deque`를 이용했다. 


4. `while`문 안에서 매 초마다 조건에 맞게 뱀의 머리를 이동시켰다. 이동 방향 부분만 주의해서 풀면 될 것 같다.




<br><br>

###  💬 소스코드

```java
package week27.BOJ_3190_G5_뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3190_G5_뱀 {
	static int N, K, L, board[][], time;
	static int dx[] = {0,1,0,-1}; // 우 하 좌 상
	static int dy[] = {1,0,-1,0};
	static HashMap<Integer, Character> command;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		command = new HashMap<>();
		
		
		N= Integer.parseInt(br.readLine()); //board의 크기
		K= Integer.parseInt(br.readLine()); //사과의 갯수
		
		board = new int[N+1][N+1];

		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = 1; //사과
		}
		
		L = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수
		
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C  = st.nextToken().charAt(0);

			//게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전
			//x 인덱스로 C 알아내기 -> hashmap
			command.put(X,  C);
		}
		
		
		Deque<Node> queue = new LinkedList<>();
		queue.add(new Node(1,1)); //뱀의 초기위치 (1,1)
		board[1][1] = -1; //뱀
		//게임 시작

		
		int dir = 0;
		time = 1; //첫 이동 시 1초
		
		while(true) {

			Node head = queue.peekFirst();
			
			int nx = head.x + dx[dir];
			int ny = head.y + dy[dir];
			
			if(!check(nx,ny)) break;
			
			queue.addFirst(new Node(nx,ny)); 
			//먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			
			
			if(board[nx][ny]==1) { 
				//만약 이동한 칸에 사과가 있다면
				board[nx][ny]=-1; 
				//그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			}else if(board[nx][ny]==0) { 
				//만약 이동한 칸에 사과가 없다면
				board[nx][ny]=-1;
				//몸길이를 줄여서 꼬리가 위치한 칸을 비워준다
				Node tail = queue.pollLast();
				board[tail.x][tail.y]=0;
			}

			if(command.containsKey(time)) {
				if(command.get(time)=='L') {
					//왼쪽
					dir = (dir+3)%4;
				}else if(command.get(time)=='D') {
					//오른쪽
					dir = (dir+1)%4;
				}
			}
			

			time++;
		}
		
		System.out.print(time);
		
		
	}
	
	private static boolean check(int nx, int ny) {
		//벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
		
		if(nx<=0||ny<=0||nx>N||ny>N) return false; //벽 검사
		
		if(board[nx][ny]==-1) return false; //자기자신의 몸
		
		return true;
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}

```
<br><br>


###  💯 채점 결과
12300	88