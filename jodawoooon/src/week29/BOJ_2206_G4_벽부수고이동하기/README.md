## BOJ 2206 G4 벽부수고이동하기

- BFS
- gold4


<br><br>


### 🔍 문제 설명

https://www.acmicpc.net/problem/2206

N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.

한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

<br>

#### ✔ 입력

첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
<br>

#### ✔ 출력

첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
<br>


<br>

###  💡 풀이


예전에 풀어본 적이 있는 문제이다. 처음 풀었을 땐 visited 체크하는 부분이 너무 어려웠던 기억이..

<br>

`BFS`를 이용해서 최단거리를 구했다.

평범한 BFS와는 다르게 이동이 불가능하더라도 다음 좌표로 벽을 부수고 이동할 수 있다. 단, 이동 중 단 한번만 벽을 부술 수 있다. 벽을 만났을 때 벽을 부술 수 있는지 없는지 체크해야 하므로 해당 정보를 포함한 `Node` 클래스를 선언했다. 



```java
	static class Node {
		int x,y;
		int cnt;
		int canBroken;
		public Node(int x, int y, int cnt, int canBroken) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.canBroken = canBroken;
		}
	}
```

x,y는 좌표값이고 cnt는 이동 횟수, canBroken이 1이면 부술 수 있고 0이면 부술 수 없다.



다음 이동 위치 `nx`와 `ny`를 찾았을 때 `visited` 체크가 중요한 문제이다. 

벽을 부순 적이 있는지 없는지 확인하기 위해 3중 배열로 방문 체크를 했다.



1. 먼저 맵의 범위를 벗어나는지 확인한다

   ```java
   if(!isIn(nx,ny)) continue;
   ```

2. 다음 위치가 벽이 아닌지? 이동 가능하다면 방문한 적 이 있는지 체크한다.

   => 추가로 벽을 부수거나 하지 않으므로 검사할 필요 없이 `canBroken` 변수를 그대로 가져간다. 

   ```java
   if(visited[nx][ny][n.canBroken]) continue;
   if(map[nx][ny]==0) { //이동 가능하면
   	visited[nx][ny][n.canBroken] = true;
   	queue.add(new Node(nx,ny,n.cnt+1, n.canBroken));
   }
   ```

3. 다음 위치가 벽이라면, 벽을 부술 수 있는지 체크한다.

   => 벽을 부순 적이 없다면 (`canBroken`==1) 벽을 부수고 이동한다

   => 벽을 부순 적이 있다면 이동할 수 없다

   ```java
   else if(map[nx][ny]==1) { //벽이라면
   	if(n.canBroken==1&&!visited[nx][ny][0]) { //벽을 부실 수 있다면 
   		visited[nx][ny][0]=true;
   		queue.add(new Node(nx,ny,n.cnt+1, 0));
   	}
   
   ```

4.  (N,M)까지 이동했다면 `cnt`를 출력 후 종료한다. 

   `while`문  종료 시 까지 이동하지 못했다면 `-1`을 출력한다.



<br><br>

###  💬 소스코드

```java
package week29.BOJ_2206_G4_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2206_G4_벽부수고이동하기 {
	static int N, M, map[][],ans;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        
        for(int i = 1 ; i <= N ; i++) {
        	String str = br.readLine();
        	for(int j= 1; j <= M; j++) {
        		map[i][j] = str.charAt(j-1)-'0';
        	}
        }
        //기본 인풋 세팅
        
        bfs();
        
    }
	private static void bfs() {
		
		//최단경로 => BFS
		boolean visited[][][] = new boolean[N+1][M+1][2];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(1,1,1,1)); //(1,1)에서 시작
		visited[1][1][1] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			if(n.x==N&&n.y==M) { //(N,M)도착
				System.out.println(n.cnt);
				return;
			}
			
			for(int d = 0 ; d < 4; d++) {
				int nx = n.x + dx[d];
				int ny = n.y + dy[d];
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny][n.canBroken]) continue;
				
				if(map[nx][ny]==0) { //이동 가능하면
					visited[nx][ny][n.canBroken] = true;
					queue.add(new Node(nx,ny,n.cnt+1, n.canBroken));
				}else if(map[nx][ny]==1) { //벽이라면
					if(n.canBroken==1&&!visited[nx][ny][0]) { //벽을 부실 수 있다면 
						visited[nx][ny][0]=true;
						queue.add(new Node(nx,ny,n.cnt+1, 0));
					}
				}
			}
		}
		System.out.println(-1); //N,M까지 갈 수 없다
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<1||ny<1||nx>N||ny>M) return false;
		return true;
	}

	static class Node {
		int x,y;
		int cnt;
		int canBroken;
		public Node(int x, int y, int cnt, int canBroken) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.canBroken = canBroken;
		}
	}
}

```

<br><br>


###  💯 채점 결과

	133016	544