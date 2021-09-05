## BAEKJOON GOLD4 1197 최소스패닝트리 
- MST
- Gold 4
- https://www.acmicpc.net/problem/1197
<br>

### 문제설명
> 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

### 입력
- 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
- 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
- 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. 
- C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
- 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
- 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

### 출력
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

### 입출력 예

#### 예제 1
입력
```
3 3
1 2 1
2 3 2
1 3 3
```
출력
```
3
```

### 풀이 및 과정
- MST 알고리즘 중 prim 알고리즘을 사용하였습니다.
- prim 알고리즘을 구현하기위해, 우선순위큐를 사용하였습니다.


### 소스코드 1 우선순위큐
```java
package week25_BOJ_1197_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static ArrayList<Edge>[] adj;
	static PriorityQueue<Edge> dist = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge>{
		int to, w;

		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w == o.w) return this.to - o.to;
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b,c));
			adj[b].add(new Edge(a,c));
		}
		
		System.out.println(prim());
	}
	
	static int prim() {
		int ans = 0;
		
		boolean[] selected = new boolean[V+1];
		
		for(int i=1; i<=V; i++) {
			selected[i] = false;
		}
		
		// 초기정점 설정하기
		selected[1] = true;
		for(Edge edge : adj[1]) {
			dist.offer(edge);
		}
		
		for(int i=1; i<V; i++) {
			
			int now =-1;
			int minDist = Integer.MAX_VALUE;
			
			while(!dist.isEmpty()) {
				now = dist.peek().to;
				
				// 선택하지 않은 정점인경우
				if(!selected[now]) {
					minDist = dist.peek().w;
					break;
				}
				dist.poll();
			}
			
			// MST를 만들수없는 경우
			if(minDist == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			
			// MST 설정하기
			ans += minDist;
			selected[now] = true;
			
			for(Edge edge : adj[now]) {
				dist.offer(edge);
			}
		}
		
		return ans;
	}	
}	
```

### 결과
```
메모리 : 57520KB	
시간 : 704ms
```
