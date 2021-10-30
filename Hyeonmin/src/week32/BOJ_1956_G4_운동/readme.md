## BOJ 1956 G4 운동
- 플로이드-와샬
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1956

V개의 마을와 E개의 도로로 구성되어 있는 도시가 있다. 도로는 마을과 마을 사이에 놓여 있으며, 일방 통행 도로이다. 마을에는 편의상 1번부터 V번까지 번호가 매겨져 있다고 하자.

당신은 도로를 따라 운동을 하기 위한 경로를 찾으려고 한다. 운동을 한 후에는 다시 시작점으로 돌아오는 것이 좋기 때문에, 우리는 사이클을 찾기를 원한다. 단, 당신은 운동을 매우 귀찮아하므로, 사이클을 이루는 도로의 길이의 합이 최소가 되도록 찾으려고 한다.

도로의 정보가 주어졌을 때, 도로의 길이의 합이 가장 작은 사이클을 찾는 프로그램을 작성하시오. 두 마을을 왕복하는 경우도 사이클에 포함됨에 주의한다.


#### 입력
첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1)) 다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다. a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다. (a → b임에 주의) 거리는 10,000 이하의 자연수이다. (a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.

#### 출력
첫째 줄에 최소 사이클의 도로 길이의 합을 출력한다. 운동 경로를 찾는 것이 불가능한 경우에는 -1을 출력한다.

###  💡 풀이

플로이드-와샬로 풀이한다

경로가 없는 경우 `map`에 `INF`값을 집어넣는다

플로이드 와샬 알고리즘을 실행하여 모든 경로를 구한 후 `map[i][i]`의 값 중에서 최소값을 찾는다

찾은 최소값 `result`가 `INF`와 같은 경우 `-1`, 아닌 경우 `result`값을 출력한다



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1956_G4_운동 {
	static int V, E, result = Integer.MAX_VALUE;
	static int[][] map;
	static final int INF = Integer.MAX_VALUE/2;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[V+1][V+1];
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				map[i][j] = INF;
			}
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			map[from][to] = distance;
		}
		
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			result = Math.min(map[i][i], result);
		}
		
		System.out.println(result == INF? -1 : result);

	}

}





```


<br>



메모리|시간
--|--
66988 KB|728 ms