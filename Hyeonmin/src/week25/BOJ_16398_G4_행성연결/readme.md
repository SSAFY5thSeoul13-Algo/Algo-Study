## BOJ 16398 G4 행성 연결
- MST
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/16398

홍익 제국의 중심은 행성 T이다. 제국의 황제 윤석이는 행성 T에서 제국을 효과적으로 통치하기 위해서, N개의 행성 간에 플로우를 설치하려고 한다.

두 행성 간에 플로우를 설치하면 제국의 함선과 무역선들은 한 행성에서 다른 행성으로 무시할 수 있을 만큼 짧은 시간만에 이동할 수 있다. 하지만, 치안을 유지하기 위해서 플로우 내에 제국군을 주둔시켜야 한다.

모든 행성 간에 플로우를 설치하고 플로우 내에 제국군을 주둔하면, 제국의 제정이 악화되기 때문에 황제 윤석이는 제국의 모든 행성을 연결하면서 플로우 관리 비용을 최소한으로 하려 한다.

N개의 행성은 정수 1,…,N으로 표시하고, 행성 i와 행성 j사이의 플로우 관리비용은 Cij이며, i = j인 경우 항상 0이다.

제국의 참모인 당신은 제국의 황제 윤석이를 도와 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화하자.  이때 플로우의 설치비용은 무시하기로 한다.


#### 입력
입력으로 첫 줄에 행성의 수 N (1 ≤ N ≤ 1000)이 주어진다.

두 번째 줄부터 N+1줄까지 각 행성간의 플로우 관리 비용이 N x N 행렬 (Cij),  (1 ≤ i, j ≤ N, 1 ≤ Cij ≤ 100,000,000, Cij = Cji) 로 주어진다.

#### 출력
모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.

###  💡 풀이

kruskal 알고리즘을 이용해서 풀었다

각 노드의 최상위 노드를 자기 자신으로 설정

```java
		parent = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
```

모든 간선에 대한 정보를 `pq`에 저장. 간선의 가중치로 오름차순 정렬

```java
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//자기 자신에 대한 경로가 아닌 경우 pq에 저장
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				
				if(weight == 0)	continue;
				
				pq.offer(new Edge(i,j,weight));
			}
		}
```

MST를 구할 때 까지 간선을 선택

```java
		while(!pq.isEmpty()) {
			
			//모든 경로가 설정된 경우
			if(count == N-1)
				break;
			
			Edge edge = pq.poll();
			
			//두 노드의 최상위 부모 노드
			int parentOne = findParent(edge.nodeOne);
			int parentTwo = findParent(edge.nodeTwo);
			
			//속한 집단이 다른 경우
			if(parentOne != parentTwo) {
				result += edge.weight;
				union(parentOne, parentTwo);
				count++;
			}
		}
```

입력한 노드의 최상위 부모 노드를 리턴하는 메소드

```java
	static int findParent(int idx) {
		if(parent[idx] == idx)
			return idx;
		else
			return findParent(parent[idx]);
	}
```

서로 다른 두 집단의 노드를 한 집단으로 합치는 메소드

```java
	static void union(int idx1, int idx2) {
		parent[idx1] = idx2; 
	}
```



<br><br>

###  💡 소스코드
```java
## BOJ 2096 G4 내려가기
- 슬라이딩 윈도우
- DP
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2096

N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다. 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다. 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.

숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.


#### 입력
첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다. 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

#### 출력
첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.

###  💡 풀이

변수
`int[][] map` : 입력된 숫자를 저장할 배열
`int[][] maxDp` : 지나오면서 더 큰 값을 가진 경우를 저장하는 배열
`int[][] minDp` : 지나오면서 더 작은 값을 가진 경우를 저장하는 배열


<br>

최상단에서 내려오면서 더 큰 경우를 저장하는 배열과 더 작은 경우를 저장하는 배열 2개의 배열을 사용해 값을 구하였다

각 위치에 숫자를 저장하고 `minDp`의 값은 최대값으로 초기화한다

```java
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minDp[i][j] = Integer.MAX_VALUE;
			}
		}
```

최상단의 dp배열에는 `map`과 같은 숫자를 저장한다

```java
		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = minDp[0][i] = map[0][i];
		}
```

최상단부터 밑으로 내려가면서 각 위치에 올 수 있는 최대값, 최소값을 저장한다

```java
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 3; j++) {
				//아래에 있는 3방향에 대해서
				for (int d = 0; d < 3; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					//범위 밖이면 스킵
					if(!canGo(ny, nx))	continue;
					
					//더 큰 수를 ny, nx위치에 저장
					maxDp[ny][nx] = Math.max(maxDp[ny][nx], maxDp[i][j] + map[ny][nx]);
					//더 작은 수를 ny, nx위치에 저장
					minDp[ny][nx] = Math.min(minDp[ny][nx], minDp[i][j] + map[ny][nx]);
				}
			}
		}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16398_G4_행성연결 {
	static int N, count;
	static long result;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge>{
		int nodeOne;
		int nodeTwo;
		int weight;
		
		public Edge(int nodeOne, int nodeTwo, int weight) {
			super();
			this.nodeOne = nodeOne;
			this.nodeTwo = nodeTwo;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//자기 자신에 대한 경로가 아닌 경우 pq에 저장
			for (int j = 0; j < N; j++) {
				int weight = Integer.parseInt(st.nextToken());
				
				if(weight == 0)	continue;
				
				pq.offer(new Edge(i,j,weight));
			}
		}
		
		while(!pq.isEmpty()) {
			
			//모든 경로가 설정된 경우
			if(count == N-1)
				break;
			
			Edge edge = pq.poll();
			
			//두 노드의 최상위 부모 노드
			int parentOne = findParent(edge.nodeOne);
			int parentTwo = findParent(edge.nodeTwo);
			
			//속한 집단이 다른 경우
			if(parentOne != parentTwo) {
				result += edge.weight;
				union(parentOne, parentTwo);
				count++;
			}
		}
		
		System.out.println(result);
	}
	
	//해당 노드의 최상위 부모 노드를 리턴
	static int findParent(int idx) {
		if(parent[idx] == idx)
			return idx;
		else
			return findParent(parent[idx]);
	}
	
	//두 집단을 합침
	static void union(int idx1, int idx2) {
		parent[idx1] = idx2; 
	}
}




```


<br>



메모리|시간
--|--
176524 KB|1644 ms