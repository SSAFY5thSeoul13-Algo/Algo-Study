## BOJ 21610 마법사 상어와 비바라기 
- Simulation
- 🥇 Gold5
- 🔗[마법사 상어와 비바라기 문제 바로가기](https://www.acmicpc.net/problem/21610)



## 풀이

이 문제는 주어진 조건을 구현하여 문제를 풀었습니다. 
구름의 위치를 list와 boolean 타입의 배열에 각각 넣어주어 문제를 풀었고
이 문제에서 1번행과 N번행을 연결해주는 부분에서 많이 헤맸는데

~~~java
			int nr = cr+ dr[d-1]*s;
			int nc = cc+ dc[d-1]*s;
~~~

이렇게 두어 배열범위를 초과하는 오류가 났었습니다.
s가 N보다 큰 경우를 생각해 주지 못했기 때문입니다.
그래서 이부분을 

~~~java
			int nr = cr+ dr[d-1]*s%N;
			int nc = cc+ dc[d-1]*s%N;
~~~

이렇게 수정하여 문제를 해결할 수 있었습니다.

## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21610_G5_마법사_상어와_비바라기 {
	static class Node{
		int r;
		int c;
		public Node(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N,M;
	static int[][] arr;
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {-1,-1,0,1,1,1,0,-1};
	static List<int[]> moveInfo = new ArrayList<>();
	static List<Node> cloudInfo = new ArrayList<>();
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		// 처음 구름의 위치 표시 
		cloudInfo.add(new Node(N,1));
		cloudInfo.add(new Node(N,2));
		cloudInfo.add(new Node(N-1,1));
		cloudInfo.add(new Node(N-1,2));
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			moveInfo.add(new int[] {d,s});
		}
		
		for(int i=0; i<M; i++) {
			move(i);
		}
		int result = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(arr[i][j]>=1) {
					result += arr[i][j];
				}
			}
		
		}
		
		System.out.println(result);

	}
	private static void move(int idx) {
		boolean[][] cloud = new boolean[N+1][N+1];
		int[] tmp = moveInfo.get(idx);
		int d = tmp[0];
		int s = tmp[1];
		// 이동의 정보대로 구름 이동 
		for(int i=0; i<cloudInfo.size(); i++) {
			Node n = cloudInfo.get(i);
			int cr = n.r;
			int cc = n.c;
			int nr = cr+ dr[d-1]*s%N;
			int nc = cc+ dc[d-1]*s%N;
			
			if(nr>N) {
				nr -= N;
			}
			
			if(nr<=0) {
				nr+=N;
			}
			
			if(nc>N) {
				nc-=N;
			}

			if(nc<=0) {
				nc+=N;
			}
			

			cloud[nr][nc] = true;
			// 구름이 있는 칸의 물의 양 1 증가 
			arr[nr][nc] += 1;
			cloudInfo.set(i,new Node(nr,nc));
		}
		
		

		
		// 물 복사 버그 마법 
		for (Node n : cloudInfo) {
			int cr = n.r;
			int cc = n.c;
			int cnt = 0;
			// 방향좌표의 배열 중 index가 1,3,5,7인 값이 현재 구름 위치를 기준으로 대각선 좌표를 나타낸다.
			for(int i=1; i<=7; i+=2) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				// 대각선의 좌표가 배열의 범위 내에 있으면 
				if(nr>0 && nc>0 && nr<=N && nc<=N) {
					if(arr[nr][nc]>=1) {
						cnt++;
					}
				}

			}
			// cnt된 만큼 물이 있는 바구니의 수 이므로 그만큼 더해준다.
			arr[cr][cc] += cnt;
		}
		
		// 기존 구름의 정보 삭제 
		cloudInfo.clear();
		

		
		// 구름이 사라진 칸이 아니고 물의 양이 2 이상의 바구니가 존재하는 칸에 구름이 생김 
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!cloud[i][j] && arr[i][j]>=2) {
					cloudInfo.add(new Node(i,j));
					arr[i][j] -= 2;
				}
			}
		}

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|23028kb|240ms|