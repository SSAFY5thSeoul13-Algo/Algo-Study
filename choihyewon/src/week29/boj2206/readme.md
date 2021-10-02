## BOJ 2206 벽 부수고 이동하기 
- BFS
- 🥇 Gold4
- 🔗[벽 부수고 이동하기 문제 바로가기](https://www.acmicpc.net/problem/2206)



## 풀이

가중치가 없는 배열에서 최단경로를 구하는 문제이기 때문에 bfs를 이용하여 문제를 풀었습니다.
먼저 행,열,이동거리,벽을 부순 횟수 정보가 포함된 클래스 Node를 선언해주었습니다.

그리고 벽의 유무를 나타내는 배열 arr와, 방문여부체크 + 벽을 부순 횟수 정보를 나타내는 visited 배열을 사용하였습니다.

~~~java
		arr = new int[N+1][M+1];
		visited = new int[N+1][M+1];
~~~

bfs를 실행하여 먼저 queue에 1행1열, 이동거리 1, 벽을 부순 횟수 0 정보를 담아주고 visited 배열에 벽을 부순 횟수를 0으로 초기화 해주었습니다.
그리고 큐가 빈 상태가 될동안 사방탐색을 진행하였습니다.
먼저 배열범위체크와 벽을 부실 수 있는지 체크를 해주었습니다.

~~~java
				if(nr<=0 || nc<=0 || nr>N || nc>M)
					continue;
				
				if(visited[nr][nc]<=bCnt) continue;
~~~

그리고 이동이 가능하다면 벽이 아닌 경우 queue에 다음 이동할 위치 정보와 이동거리+1, 벽을 부수지 않았으므로 벽을 부순 횟수는 그대로 저장합니다.

~~~java
				if(arr[nr][nc]==0) {
					queue.add(new Node(nr,nc,ndis+1,bCnt));
					visited[nr][nc] = bCnt;
				}
~~~

만약 이동위치가 벽인 경우에 현재 벽을 부순 횟수가 0이라면 이동거리와 벽을 부순횟수를 +1 하여 quque에 넣어주었습니다.

~~~java
				else if(arr[nr][nc]==1) {
					// 지금까지 벽을 부순 횟수가 0이라면 
					if(bCnt==0) {
						// 이동거리와 벽을 부신 횟수를 늘려 저장 
						queue.add(new Node(nr,nc,ndis+1,bCnt+1));
						visited[nr][nc] = bCnt+1;
					}
				}
~~~

그렇게 위치가 N행 M열에 도착한 경우, 현재 이동거리를 return 해주었습니다.

~~~java
			if(node.r == N && node.c == M) {
				return node.dis;
			}
~~~

## 소스코드
~~~java
import java.io.*;
import java.util.*;

public class BOJ_2206_G4_벽_부수고_이동하기 {
	static int N,M;
	static int[][] arr;
	static int[][] visited;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static class Node{
		int r;
		int c;
		int dis;
		int breakCnt;
		public Node(int r, int c, int dis, int breakCnt) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.breakCnt = breakCnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		
		for(int i=1; i<=N; i++) {
			String str = br.readLine();
			for(int j=1; j<=M; j++) {
				arr[i][j] = str.charAt(j-1)-'0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		// 1행 1열부터 시작 , 현재 이동거리는 1, 벽을 부순 횟수는 0 
		int ans = bfs(1,1,1,0);
		
		System.out.println(ans);
		

	}
	private static int bfs(int r, int c,int dis,int breakCnt) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r,c,dis,breakCnt));
		visited[r][c] = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			// 목적지 (N,M) 에 도착했다면 break 
			if(node.r == N && node.c == M) {
				return node.dis;
			}
			
			for(int d=0; d<4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				int ndis = node.dis;
				int bCnt = node.breakCnt;
		
				if(nr<=0 || nc<=0 || nr>N || nc>M)
					continue;
				
				if(visited[nr][nc]<=bCnt) continue;
			
				// 이동이 가능하다면 이동거리만 늘려서 저장하면 된다 
				if(arr[nr][nc]==0) {
					queue.add(new Node(nr,nc,ndis+1,bCnt));
					visited[nr][nc] = bCnt;
				}
				// 다음 이동할 곳이 벽인 경우 
				else if(arr[nr][nc]==1) {
					// 지금까지 벽을 부순 횟수가 0이라면 
					if(bCnt==0) {
						// 이동거리와 벽을 부신 횟수를 늘려 저장 
						queue.add(new Node(nr,nc,ndis+1,bCnt+1));
						visited[nr][nc] = bCnt+1;
					}
				}
			}
			
			
		}
		
		return -1;
		
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 135996kb| 464ms|