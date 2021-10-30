## BOJ 17837 G2 새로운 게임 2
- 시뮬레이션
- gold2

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/17837

재현이는 주변을 살펴보던 중 체스판과 말을 이용해서 새로운 게임을 만들기로 했다. 새로운 게임은 크기가 N×N인 체스판에서 진행되고, 사용하는 말의 개수는 K개이다. 말은 원판모양이고, 하나의 말 위에 다른 말을 올릴 수 있다. 체스판의 각 칸은 흰색, 빨간색, 파란색 중 하나로 색칠되어있다.

게임은 체스판 위에 말 K개를 놓고 시작한다. 말은 1번부터 K번까지 번호가 매겨져 있고, 이동 방향도 미리 정해져 있다. 이동 방향은 위, 아래, 왼쪽, 오른쪽 4가지 중 하나이다.

턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것이다. 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다. 말의 이동 방향에 있는 칸에 따라서 말의 이동이 다르며 아래와 같다. 턴이 진행되던 중에 말이 4개 이상 쌓이는 순간 게임이 종료된다.

A번 말이 이동하려는 칸이
흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
A번 말의 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동한다.
예를 들어, A, B, C로 쌓여있고, 이동하려는 칸에 D, E가 있는 경우에는 A번 말이 이동한 후에는 D, E, A, B, C가 된다.
빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여있는 순서를 반대로 바꾼다.
A, B, C가 이동하고, 이동하려는 칸에 말이 없는 경우에는 C, B, A가 된다.
A, D, F, G가 이동하고, 이동하려는 칸에 말이 E, C, B로 있는 경우에는 E, C, B, G, F, D, A가 된다.
파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다. 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
체스판을 벗어나는 경우에는 파란색과 같은 경우이다

체스판의 크기와 말의 위치, 이동 방향이 모두 주어졌을 때, 게임이 종료되는 턴의 번호를 구해보자.

#### 입력
첫째 줄에 체스판의 크기 N, 말의 개수 K가 주어진다. 둘째 줄부터 N개의 줄에 체스판의 정보가 주어진다. 체스판의 정보는 정수로 이루어져 있고, 각 정수는 칸의 색을 의미한다. 0은 흰색, 1은 빨간색, 2는 파란색이다.

다음 K개의 줄에 말의 정보가 1번 말부터 순서대로 주어진다. 말의 정보는 세 개의 정수로 이루어져 있고, 순서대로 행, 열의 번호, 이동 방향이다. 행과 열의 번호는 1부터 시작하고, 이동 방향은 4보다 작거나 같은 자연수이고 1부터 순서대로 →, ←, ↑, ↓의 의미를 갖는다.

같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다.

#### 출력
게임이 종료되는 턴의 번호를 출력한다. 그 값이 1,000보다 크거나 절대로 게임이 종료되지 않는 경우에는 -1을 출력한다.

###  💡 풀이

각 좌표별 쌓여있는 말의 번호를 저장하는 List를 2차원 배열 `horseMap`으로 저장하여 풀었다.

`list`에 각각의 말의 좌표, 번호, 방향을 를 저장하고 순서대로 해당 말이 있는 좌표를 찾아가 해당 좌표에 있는 말들의 이동을 처리하였다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] delta= {
			{0,0},
			{0,1},
			{0,-1},
			{-1,0},
			{1,0}
	};
	static int[][] map;
	static List<Integer>[][] horseMap;
	static int N, K, time;
	static List<Node> list = new ArrayList<>();
	
	static class Node{
		int y;
		int x;
		int num;
		int dir;
		
		public Node(int y, int x, int num, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		horseMap = new ArrayList[N+1][N+1];
		map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horseMap[i][j] = new ArrayList<>();
			}
		}
		
		//더비 값
		list.add(new Node(0,0,0,0));
		
		//각 말의 정보와 위치 저장
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			list.add(new Node(y, x, i, dir));
			horseMap[y][x].add(i);
		}
		
		Loop:while(true) {
			time++;
			
			if(time > 1000)	break;
			
			//쌓인 말의 수
			int size = 0;
			
			for (Node node : list) {
				int y = node.y;
				int x = node.x;
				int num = node.num;
				int dir = node.dir;
				
				//더미 제외
				if(num == 0)	continue;
				
				int ny = y + delta[dir][0];
				int nx = x + delta[dir][1];
				
				boolean isReverse = false;
				
				//범위 밖으로 향하는 경우
				if(!isIn(ny, nx)) {
					dir = node.dir = (dir%2 == 0)? dir-1 :dir+1;
					
					ny = y + delta[dir][0];
					nx = x + delta[dir][1];
					
					if(map[ny][nx] == 2) {
						continue;
					}
					else if(map[ny][nx] == 1) {
						isReverse = true;
					}
				}
				//파란색
				else if(map[ny][nx] == 2) {
					dir = node.dir = (dir%2 == 0)? dir-1 :dir+1;
					
					ny = y + delta[dir][0];
					nx = x + delta[dir][1];
					
					if(!isIn(ny, nx) || map[ny][nx] == 2) {
						continue;
					}
					else if(map[ny][nx] == 1) {
						isReverse = true;
					}
				}
				//빨간색
				else if(map[ny][nx] == 1) {
					isReverse = true;
				}
				
				boolean isTarget = false;
				
				List<Integer> tempList = new ArrayList<>();
				
				for (int idx = 0; idx < horseMap[y][x].size(); idx++) {
					Node horse = list.get(horseMap[y][x].get(idx));
					
					if(horse.num == num)	isTarget = true;
					
					//이번에 움직이려는 말보다 아래에 있는 말인 경우
					if(!isTarget)	continue;
					
					//이동할 말 번호 저장
					tempList.add(horse.num);
					//좌표 수정
					horse.y = ny;
					horse.x = nx;
					
					horseMap[y][x].remove(idx--);
				}
				
				//빨간색으로 이동하는 경우
				if(isReverse) {
					Collections.reverse(tempList);
				}
				
				//이번에 이동되는 말을 모두 이동
				for (int horseNum: tempList) {
					horseMap[ny][nx].add(horseNum);
					size = Math.max(size, horseMap[ny][nx].size());
				}
				
				//쌓인 말의 수가 4 이상인 경우
				if(size >= 4) break Loop;
			}
		}
		
		System.out.println(time > 1000 ? -1 : time);
	}
	
	static boolean isIn(int y, int x) {
		if(y < 1 || x < 1 || y > N || x > N)	return false;
		
		return true;
	}

}





```


<br>



메모리|시간
--|--
12904 KB|120 ms