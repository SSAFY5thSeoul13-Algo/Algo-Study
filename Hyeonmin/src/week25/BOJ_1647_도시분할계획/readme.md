## BOJ 1647 G4 도시 분할 계획
- 슬라이딩 윈도우
- DP
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1647

마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다.

마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 마을이 너무 커서 혼자서는 관리할 수 없기 때문이다. 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 마을에는 집이 하나 이상 있어야 한다.

그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다. 이것을 구하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.

#### 출력
첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.

###  💡 풀이 - Prim

Prim 알고리즘을 사용하여 MST를 구한후에 가장 큰 가중치 `max`를 뺸 것을 결과로 출력했다

<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획_prim {
	static int N, M, count, sum, max;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static boolean[] isVisited;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
		}
		
		//시작하는 노드를 잡기 위한 데이터
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 집이 연결된 경우
			if(count == N)
				break;
			
			Edge edge = pq.poll();
			
			//이미 선택된 노드로의 간선은 제외
			if(isVisited[edge.to])
				continue;
			
			//노드 방문처리
			isVisited[edge.to] = true;
			
			//비용과 선택한 노드 수정
			sum+= edge.weight;
			count++;
			max = Math.max(max, edge.weight);
			
			//선택한 노드에서 이어진 간선들에 대한 처리
			for (Edge nextEdge : graph.get(edge.to)) {
				if(isVisited[nextEdge.to])	continue;
				
				pq.offer(nextEdge);
			}
		}
		
		//MST에서 가장 비용이 큰 간선 하나의 가중치를 제외한 경우를 출력
		System.out.println(sum - max);

	}

}





```


<br>



메모리|시간
--|--
363808 KB|2472 ms

###  💡 풀이 - Kruskal

Kruskal 알고리즘을 사용하여 MST에서 1개의 간선을 덜 구한 경우의 가중치 합 `sum`을 출력하였다

모든 간서의 정보를 가중치의 오름차순으로 `pq`에 저장한다

```java
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(from, to, weight));
		}
```

MST에서 1개의 간선을 제외한 경우가 될 때까지 while문을 반복한다

```java
		while(!pq.isEmpty()) {
			//그룹이 2개여야 하니까 MST에서 간선 1개를 뺀 경우 중지
			if(count == N-2)
				break;
			
			Edge edge = pq.poll();
			
			//두 그룹이 합쳐지는 경우
			if(union(edge.numOne, edge.numTwo)) {
				sum+= edge.weight;
				count++;
			}
		}
```

각 노드의 최상의 노드를 구하는 메소드

자기가 최상단이 아닐 경우 최상단 노드를 `parents[idx]`에 저장해 다음 탐색 시간을 단축시킨다

```java
	static int findParent(int idx) {
		if(idx == parents[idx])
			return idx;
		
		//최상단 부모를 저장
		return parents[idx] = findParent(parents[idx]);
	}
```

두 그룹을 합치는 메소드

```java
	static boolean union(int idx1, int idx2) {
		int parentOne = findParent(idx1);
		int parentTwo = findParent(idx2);
		
		if(parentOne == parentTwo)
			return false;
		
		if(idx1 > idx2)
			parents[parentOne] = parentTwo;
		else
			parents[parentTwo] = parentOne;
		
		return true;
	}
```
<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획_kruskal {
	static int N, M, count, sum;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parents;
	
	static class Edge implements Comparable<Edge>{
		int numOne;
		int numTwo;
		int weight;
		
		public Edge(int numOne, int numTwo, int weight) {
			super();
			this.numOne = numOne;
			this.numTwo = numTwo;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(from, to, weight));
		}
		
		while(!pq.isEmpty()) {
			//그룹이 2개여야 하니까 MST에서 간선 1개를 뺀 경우 중지
			if(count == N-2)
				break;
			
			Edge edge = pq.poll();
			
			//두 그룹이 합쳐지는 경우
			if(union(edge.numOne, edge.numTwo)) {
				sum+= edge.weight;
				count++;
			}
		}
		
		System.out.println(sum);

	}
	
	static int findParent(int idx) {
		if(idx == parents[idx])
			return idx;
		
		//최상단 부모를 저장
		return parents[idx] = findParent(parents[idx]);
	}
	
	//두 그룹을 합침
	static boolean union(int idx1, int idx2) {
		int parentOne = findParent(idx1);
		int parentTwo = findParent(idx2);
		
		if(parentOne == parentTwo)
			return false;
		
		if(idx1 > idx2)
			parents[parentOne] = parentTwo;
		else
			parents[parentTwo] = parentOne;
		
		return true;
	}

}






```


<br>



메모리|시간
--|--
324004 KB|1564 ms