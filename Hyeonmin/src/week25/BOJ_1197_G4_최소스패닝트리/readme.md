## BOJ 1197 G4 최소 스패닝 트리
- 최소 스패닝 트리
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1197

그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.


#### 입력
첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

#### 출력
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

###  💡 풀이

간선의 정보를 저장할 클래스 `Edge`를 정의한다

```java
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
```

선택한 노드를 체크할 배열 `isChecked`와 각 노드의 간선을 저장할 `graph`를 정의한다

```java
		//이미 선택한 노드
		isChecked = new boolean[V+1];
		//각 노드에서 이어진 간선을 저장할 리스트
		graph = new ArrayList<>();
		
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
```

각 노드에 대한 간선 정보를 저장하고 1번 노드를 시작 노드로 설정한다

```java
		//각 간선을 리스트에 추가
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(new Edge(node2,weight));
			graph.get(node2).add(new Edge(node1,weight));
		}
		
		//시작 노드를 1번 노드로 설정
		pq.offer(new Edge(1,0));
```

`pq`에서 가중치가 가장 작은 간선을 선택하고 해당 간선이 향하는 노드에서 연결된 간선중 아직 선택하지 않은 노드로 향하는 간선을 `pq`에 추가한다.

```java
		while(!pq.isEmpty()) {
			//모든 노드를 선택한 경우 종료
			if(count == V)
				break;
			
			Edge edge = pq.poll();
			
			//이미 선택된 노드로 향하는 간선일 경우
			if(isChecked[edge.to])
				continue;
			
			isChecked[edge.to] = true;
			result += edge.weight;
			
			//선택한 노드에서 시작되는 간선을 추가
			for (Edge nEdge : graph.get(edge.to)) {
				if(isChecked[nEdge.to])	continue;
				
				pq.offer(nEdge);
			}
			
			count++;
		}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소스패닝트리 {
	static int V, E, count, result;
	static boolean[] isChecked;
	static ArrayList<ArrayList<Edge>> graph;
	
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
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		//이미 선택한 노드
		isChecked = new boolean[V+1];
		//각 노드에서 이어진 간선을 저장할 리스트
		graph = new ArrayList<>();
		
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		//각 간선을 리스트에 추가
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(new Edge(node2,weight));
			graph.get(node2).add(new Edge(node1,weight));
		}
		
		//시작 노드를 1번 노드로 설정
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 노드를 선택한 경우 종료
			if(count == V)
				break;
			
			Edge edge = pq.poll();
			
			//이미 선택된 노드로 향하는 간선일 경우
			if(isChecked[edge.to])
				continue;
			
			isChecked[edge.to] = true;
			result += edge.weight;
			
			//선택한 노드에서 시작되는 간선을 추가
			for (Edge nEdge : graph.get(edge.to)) {
				if(isChecked[nEdge.to])	continue;
				
				pq.offer(nEdge);
			}
			
			count++;
		}
		
		System.out.println(result);
	}

}




```


<br>



메모리|시간
--|--
55260 KB|588 ms