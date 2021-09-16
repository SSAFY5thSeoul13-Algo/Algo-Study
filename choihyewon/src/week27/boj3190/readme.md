## BOJ 3190 뱀 
- Simulation 
- 🥇 Gold5
- 🔗[뱀 문제 바로가기](https://www.acmicpc.net/problem/3190)



## 풀이

먼저 2차원 배열에 사과가 위치한 곳을 표시해주었습니다.

~~~java
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			// 사과 있는 곳 표시 
			arr[row][col] = 1;
		}
~~~

뱀의 방향 전환 정보를 담는 클래스를 만들어 주어 L개의 방향 전환 정보를 list에 담았습니다.

~~~java
	// 뱀의 방향 전환 정보를 담는 클래스 
	static class DirInfo{
		int sec;
		char dir;
		public DirInfo(int sec,char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
~~~

~~~java
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			info.add(new DirInfo(sec,dir));
		}
~~~

뱀은 오른쪽 방향으로 1,1 좌표에서 시작하므로 dr,dc 각 행,열 방향좌표를 담은 배열은 우 하 좌 상 순으로 넣어주었습니다.
그리고 현재 뱀의 몸의 위치 정보를 담는 snake 리스트에 1,1을 넣어주었습니다.
그 다음 뱀의 방향전환정보를 이용하여 뱀을 움직여 주었습니다. 사과를 먹을때, 먹지않을때를 구분하여 움직여주었습니다.

~~~java
while(true) {
			time++;

			int nextR = curR + dr[curDir];
			int nextC = curC + dc[curDir];
			
			if(isBreak(nextR,nextC))	break;
			
			// 사과를 먹는 경우 
			if(arr[nextR][nextC]==1) {
				snake.add(new int[] {nextR,nextC});
				arr[nextR][nextC] = 0;
			}
			// 사과가 없는 경우 
			else {
				// 꼬리를 제거한다.
				snake.remove(0);
				snake.add(new int[] {nextR,nextC});
			}
			
			curR = nextR;
			curC = nextC;
			
			if(idx<L) {
				int infoSec = info.get(idx).sec;
				if(time==infoSec) {
					char changeDir = info.get(idx).dir;
					if(changeDir == 'D') {
						curDir++;
						if(curDir==4) {
							curDir = 0;
						}
					}else if(changeDir == 'L') {
						curDir--;
						if(curDir==-1) {
							curDir = 3;
						}
					}
					idx++;
				}
			}
		}
~~~

그리고 뱀이 정사각 보드를 나가거나 자신의 몸과 부딪힐 경우 break해주었습니다.(isBreak메소드 이용)


## 막힌점
뱀이 사과를 먹고난 후 사과가 위치한 표시를 지우는 로직을 추가해주어야 했는데, 지금 테케에서는 그 로직이 없어도 답이 나왔어서 반례를 찾고난 후에 로직을 추가할 수 있었습니다.

~~~java
				arr[nextR][nextC] = 0;
~~~

## 소스코드
~~~java
package week27.boj3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3190_G5_뱀 {
	// 뱀의 방향 전환 정보를 담는 클래스 
	static class DirInfo{
		int sec;
		char dir;
		public DirInfo(int sec,char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
	static int N,K,L;
	static int[][] arr;
	// 우 하 좌 상 방향 
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static List<DirInfo> info = new ArrayList<>();
	// 뱀의 몸이 위치하고 있는 좌표 
	static List<int[]> snake = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N+1][N+1];
		
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			// 사과 있는 곳 표시 
			arr[row][col] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			info.add(new DirInfo(sec,dir));
		}
		// 뱀은 처음에 1행 1열에서 시작하고 길이는 
		int curR = 1;
		int curC = 1;
		int time = 0;
		int idx = 0;
		int curDir = 0;
		
		snake.add(new int[] {curR,curC});
		
		while(true) {
			time++;

			int nextR = curR + dr[curDir];
			int nextC = curC + dc[curDir];
			
			if(isBreak(nextR,nextC))	break;
			
			// 사과를 먹는 경우 
			if(arr[nextR][nextC]==1) {
				snake.add(new int[] {nextR,nextC});
				arr[nextR][nextC] = 0;
			}
			// 사과가 없는 경우 
			else {
				// 꼬리를 제거한다.
				snake.remove(0);
				snake.add(new int[] {nextR,nextC});
			}
			
			curR = nextR;
			curC = nextC;
			
			if(idx<L) {
				int infoSec = info.get(idx).sec;
				if(time==infoSec) {
					char changeDir = info.get(idx).dir;
					if(changeDir == 'D') {
						curDir++;
						if(curDir==4) {
							curDir = 0;
						}
					}else if(changeDir == 'L') {
						curDir--;
						if(curDir==-1) {
							curDir = 3;
						}
					}
					idx++;
				}
			}
		}
		
		System.out.println(time);

	}
	// 뱀이 벽에 부딪히거나 자신의 몸에 부딪히는 경우 true return
	private static boolean isBreak(int nextR, int nextC) {
		if(nextR<=0 || nextC<=0 || nextR>N || nextC>N)
			return true;
		for(int i=0; i<snake.size(); i++) {
			// 현재 뱀의 몸의 위치 
			int snakeR = snake.get(i)[0];
			int snakeC = snake.get(i)[1];
			if(snakeR == nextR && snakeC == nextC)	return true;
		}
		return false;
	}
	

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 12016kb| 100ms|