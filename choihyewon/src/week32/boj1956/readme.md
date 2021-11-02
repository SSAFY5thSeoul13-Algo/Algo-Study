## BOJ 1956 운동 
- Floyd-Warshall
- 🥇 Gold4
- 🔗[운동 문제 바로가기](https://www.acmicpc.net/problem/1956)



## 풀이

이 문제는 모든 정점으로부터 모든 정점까지의 최소거리를 구하는 문제이므로 플로이드 워샬 알고리즘을 이용하여 풀었습니다.

먼저 2차원 배열에 최대값을 넣어주고 시작점과 도착점의 길이 정보를 넣어줍니다.

~~~java
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i != j)	matrix[i][j] = INF;
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			matrix[start][end] = len;
		}
~~~

그리고 플로이드 워샬을 이용하여 각 정점으로부터 정점까지의 최소거리를 구해줍니다.
이 때, 출발지로부터 도착지의 최소거리를 구하기 위해 출발지-경유지 + 경유지-도착지 의 합과 비교하여 최소값을 갱신합니다.

~~~java
		for(int j=1; j<=V; j++) {
			for(int i=1; i<=V; i++) {
				// 경유지와 출발지가 같으면 continue 
				if(j==i)	continue;
				for(int k=1; k<=V; k++) {
					if(j==k || i==k)	continue;
					// 출발지 - 도착지 거리와 출발지 - 경유지 - 도착지 거리를 비교하여 최소값으로 갱신 
					if(matrix[i][k] > matrix[i][j] + matrix[j][k]) {
						matrix[i][k] = matrix[i][j] + matrix[j][k];
						//System.out.println("hgg" + matrix[i][k] + " " +  matrix[i][j] + " " +  matrix[j][k]);
					}
				}

			}
		}
~~~

이 문제에서는 최소거리를 구하면 끝나는것이 아니라 왕복최소거리를 구하는 것이기 때문에 
배열에서 출발지오 도착지가 같은 경우와 최소거리가 갱신되지 않은 경우를 제외한 거리중 제일 최소값을 구하여 답을 구했습니다.

~~~java
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j)	continue;
				if(matrix[i][j]!=INF && matrix[j][i]!=INF) {
					int sum = matrix[i][j] + matrix[j][i];
					answer = Math.min(answer, sum);
				}
			}
		}
~~~


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1956_G4_운동 {
	static final int INF = 9999999;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int[V+1][V+1];
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i != j)	matrix[i][j] = INF;
			}
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			matrix[start][end] = len;
		}
		
		
		for(int j=1; j<=V; j++) {
			for(int i=1; i<=V; i++) {
				// 경유지와 출발지가 같으면 continue 
				if(j==i)	continue;
				for(int k=1; k<=V; k++) {
					if(j==k || i==k)	continue;
					// 출발지 - 도착지 거리와 출발지 - 경유지 - 도착지 거리를 비교하여 최소값으로 갱신 
					if(matrix[i][k] > matrix[i][j] + matrix[j][k]) {
						matrix[i][k] = matrix[i][j] + matrix[j][k];
						//System.out.println("hgg" + matrix[i][k] + " " +  matrix[i][j] + " " +  matrix[j][k]);
					}
				}

			}
		}
		
	
		
		int answer = Integer.MAX_VALUE;
		
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(i==j)	continue;
				if(matrix[i][j]!=INF && matrix[j][i]!=INF) {
					int sum = matrix[i][j] + matrix[j][i];
					answer = Math.min(answer, sum);
				}
			}
		}
		
		if(answer != Integer.MAX_VALUE) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
		

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 65288kb| 1176ms|