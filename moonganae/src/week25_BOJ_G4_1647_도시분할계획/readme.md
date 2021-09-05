## BAEKJOON GOLD4 1647 도시분할계획
- MST
- Gold 4
- https://www.acmicpc.net/problem/1647
<br>

### 문제설명
> 동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그러다가 평화로운 마을에 가게 되었는데, 그곳에서는 알 수 없는 일이 벌어지고 있었다.
마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다.
마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 마을이 너무 커서 혼자서는 관리할 수 없기 때문이다. 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 마을에는 집이 하나 이상 있어야 한다.
그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다. 이것을 구하는 프로그램을 작성하시오.

### 입력
- 첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다.
- N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다.
- 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.

### 출력
첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.

### 입출력 예

#### 예제 1
입력
```
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
```
출력
```
8
```

### 풀이 및 과정
MST 중 크루스칼 알고리즘을 사용하였습니다.

이 문제는 두개의 분리된 마을로 분할하여야 하여 MST를 2개를 구해서 2개의 유지비 최솟값을 더하는 문제입니다.

하지만, 마을에 집이 한개이상 존재하면 되기때문에, 유지비의 최솟값은 기존 MST에서 비용이 최대인 간선 하나만 제거를 하게 된다면, 2개의 분리된 마을의 유지비 최솟값을 구할 수 있습니다!! (처음에 이것을 이해하지 못했음.)

따라서 기존의 크루스칼 알고리즘에서 변형되는 부분은 선택하는 간선이 N-1개가 아닌 N-2개가 되는 부분이다.

```java
// 기존의 MST는 N-1개지만, MST에서 최대비용의 간선을 하나를 제거하면 N-2개의 간선을 가짐
if(edgeCnt == N-2) break;
```


### 소스코드 1 우선순위큐
```java
package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 가장 큰수 N개를 가질 우선순위큐
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				
				int data = Integer.parseInt(st.nextToken());
				
				// 우선순위큐에 n개 이하라면 그냥 넣기
				if(q.size() < N) q.offer(data);
				// 현재 큐에서 가장 작은 수가 data보다 작다면
				else if(data > q.peek()) {
					// 데이터 교체
					q.poll();
					q.offer(data);
					
				}
			}
		}
		
		// q.peek() == N번째 큰수
		System.out.println(q.peek());
	}

}		
```

### 결과 1
```
메모리 : 294480KB	
시간 : 784ms
```

### 소스코드 2 그냥 정렬
```java
package week25_BOJ_G4_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Edge> edges = new ArrayList<>();
	static int[] parent;
	static class Edge implements Comparable<Edge>{
		int from, to, w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
//			if(this.w == o.w) return this.to - o.to;
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(a,b,c));
		}
		
		Collections.sort(edges);
		
		
		System.out.println(kruskal());
	
		
	}
	
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa < pb) parent[pb] = pa;
		else parent[pa] = pb;
	}
	
	static int kruskal() {
		int ans = 0;
		parent = new int[N+1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
		
		// 선택한 간선 수
		int edgeCnt = 0;
		
		for(Edge edge : edges) {
			
			if(find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				ans += edge.w;
				edgeCnt++;
			}
			
			// 기존의 MST는 N-1개지만, MST에서 최대비용의 간선을 하나를 제거하면 N-2개의 간선을 가짐
			if(edgeCnt == N-2) break;
		}
		
		return ans;
		
	}
}

```

### 결과
```
메모리 : 324664KB	
시간 : 1528ms
```
