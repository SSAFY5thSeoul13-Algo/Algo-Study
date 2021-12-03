## BOJ 17086 아기상어2 
- bfs
- 🥈 Silver2
- 🔗[아기상어2 문제 바로가기](https://www.acmicpc.net/problem/17086)



## 풀이

저는 bfs를 이용하여 문제를 풀었습니다. 먼저 입력받은 숫자를 2차원 배열에 넣어주었습니다.
그리고 상어가 없는 칸에서 bfs를 진행하였습니다.

이동은 8방향이 가능하기 때문에 8방향으로 탐색을 진행하였습니다.
이동이 가능한 경우에 방문체크를 해주고 거리를 +1, 이동가능한 위치 좌표는 queue에 넣어주었습니다.
만약 범위를 벗어나거나 방문했다면 continue,
아기 상어를 만났다면 현재 이동거리와 answer중 최댓값 비교를 하여 return해주었습니다.

~~~java

		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Node(r,c,cnt));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for(int i=0; i<8; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M || visited[nr][nc])	continue;
				
				if(arr[nr][nc]==1) {
					answer = Math.max(answer, cur.cnt+1);
					return;
				}
				
				visited[nr][nc] = true;
				queue.add(new Node(nr,nc,cur.cnt+1));
			}
		}
		
	}
~~~

## 소스코드
~~~java
import java.io.*;
import java.util.*;

public class BOJ_17086_S2_아기상어2 {
	static class Node{
		int r;
		int c;
		int cnt;
		public Node(int r,int c,int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static int N,M,answer=0;
	static int[][] arr;
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					bfs(i,j,0);
				}
			}
		}
		

		System.out.println(answer);
		
	}
	private static void bfs(int r, int c,int cnt) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Node(r,c,cnt));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for(int i=0; i<8; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M || visited[nr][nc])	continue;
				
				if(arr[nr][nc]==1) {
					answer = Math.max(answer, cur.cnt+1);
					return;
				}
				
				visited[nr][nc] = true;
				queue.add(new Node(nr,nc,cur.cnt+1));
			}
		}
		
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 169364kb| 376ms|