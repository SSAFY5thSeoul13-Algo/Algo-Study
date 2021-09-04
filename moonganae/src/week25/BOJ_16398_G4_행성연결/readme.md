## BAEKJOON GOLD4 16398 행성연결
- MST
- Gold 4
- https://www.acmicpc.net/problem/16398
<br>

### 문제설명

>홍익 제국의 중심은 행성 T이다. 제국의 황제 윤석이는 행성 T에서 제국을 효과적으로 통치하기 위해서, N개의 행성 간에 플로우를 설치하려고 한다.
두 행성 간에 플로우를 설치하면 제국의 함선과 무역선들은 한 행성에서 다른 행성으로 무시할 수 있을 만큼 짧은 시간만에 이동할 수 있다. 하지만, 치안을 유지하기 위해서 플로우 내에 제국군을 주둔시켜야 한다.
모든 행성 간에 플로우를 설치하고 플로우 내에 제국군을 주둔하면, 제국의 제정이 악화되기 때문에 황제 윤석이는 제국의 모든 행성을 연결하면서 플로우 관리 비용을 최소한으로 하려 한다.
N개의 행성은 정수 1,…,N으로 표시하고, 행성 i와 행성 j사이의 플로우 관리비용은 Cij이며, i = j인 경우 항상 0이다.
제국의 참모인 당신은 제국의 황제 윤석이를 도와 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화하자.  이때 플로우의 설치비용은 무시하기로 한다.

### 입력
- 입력으로 첫 줄에 행성의 수 N (1 ≤ N ≤ 1000)이 주어진다.
- 두 번째 줄부터 N+1줄까지 각 행성간의 플로우 관리 비용이 N x N 행렬 (Cij),<br>  
(1 ≤ i, j ≤ N, 1 ≤ Cij ≤ 100,000,000, Cij = Cji) 로 주어진다.

### 출력
모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.

### 입출력 예

#### 예제 1
입력
```
3
0 2 3
2 0 1
3 1 0
```
출력
```
3
```

### 풀이 및 과정
MST 알고리즘 중 크루스칼 알고리즘을 사용하였습니다.

1.간선들을 비용기준으로 오름차순으로 정렬하기

```java
static class Edge implements Comparable<Edge>{
	int from, to, w;
	public Edge(int from, int to, int w) {
		this.from = from;
		this.to = to;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o1) {			
		return this.w - o1.w;
	}
}

List<Edge> edge = new ArrayList<>();
		
for(int i=0; i<N; i++) {
	for(int j=i+1; j<N; j++) {
		edge.add(new Edge(i,j,cost[i][j]));
	}
}

Collections.sort(edge);
```

2. 해당 간선을 선택했을때 사이클인지 확인 (즉, 해당 간선의 두정점이 같은 트리에 이미 속하는지 확인)
3. 사이클이 아니라면 연결하기

```java
for(Edge e : edge) {
	// 같은 트리가 아니라면
	if(find(e.from) != find(e.to)) {
		// 결합
		union(e.from, e.to);
		totalCost += e.w;
	}
}
```




### 소스코드
```java
package week25.BOJ_16398_G4_행성연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] cost;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(kruskal());
	}
	
	static long kruskal() {
		List<Edge> edge = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				edge.add(new Edge(i,j,cost[i][j]));
			}
		}
		
		
		// Union & Find 초기화
		parent = new int[N];
		for(int i=1; i<N; i++) {
			parent[i] = i;
		}
		
		Collections.sort(edge);
		
		long totalCost = 0;
		
		for(Edge e : edge) {
			// 같은 트리가 아니라면
			if(find(e.from) != find(e.to)) {
				// 결합
				union(e.from, e.to);
				totalCost += e.w;
			}
		}
		
		
		return totalCost;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, w;
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o1) {			
			return this.w - o1.w;
		}
	}
	
}



```

### 결과
```
메모리 : 157284KB	
시간 : 972ms
```
