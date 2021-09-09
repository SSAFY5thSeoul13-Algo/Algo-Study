## BOJ 1922 G4 네트워크 연결
- MST
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1922

컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 하지만 아쉽게도 허브가 있지 않아 컴퓨터와 컴퓨터를 직접 연결하여야 한다. 그런데 모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. (a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어 있다.)

그런데 이왕이면 컴퓨터를 연결하는 비용을 최소로 하여야 컴퓨터를 연결하는 비용 외에 다른 곳에 돈을 더 쓸 수 있을 것이다. 이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 모든 컴퓨터를 연결할 수 없는 경우는 없다.


#### 입력
첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.

둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.

셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 만큼 든다는 것을 의미한다. a와 b는 같을 수도 있다.

#### 출력
모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.

###  💡 풀이

프림 알고리즘을 사용해서 풀이했다

두 컴퓨터 사이를 연결하는 간선을 `Edge`클래스를 사용해서 표현하였다

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

1번 컴퓨터를 시작적으로 잡고 모든 컴퓨터를 연결할 때 까지 while문을 반복하였다.

새로운 컴퓨터가 연결 될 때 연결한 컴퓨터의 수 `count`와 현재까지 연결한 선의 가중치 합 `sum`의 값을 변화시킨다.

새로운 컴퓨터가 연결되면 그 컴퓨터에서 이어진 간선들을 `pq`에 저장한다.

```java
		//시작하는 노드를 잡기 위한 데이터
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 컴퓨터가 연결된 경우
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
			
			//선택한 노드에서 이어진 간선들에 대한 처리
			for (Edge nextEdge : graph.get(edge.to)) {
				if(isVisited[nextEdge.to])	continue;
				
				pq.offer(nextEdge);
			}
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

public class BOJ_1922_G4_네트워크연결 {
	static int N, M, count, sum;
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
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		isVisited = new boolean[N+1];
		
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Edge>());
		}
		
		//선 정보를 입력
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
		}
		
		//시작하는 노드를 잡기 위한 데이터
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			//모든 컴퓨터가 연결된 경우
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
			
			//선택한 노드에서 이어진 간선들에 대한 처리
			for (Edge nextEdge : graph.get(edge.to)) {
				if(isVisited[nextEdge.to])	continue;
				
				pq.offer(nextEdge);
			}
		}
		
		System.out.println(sum);

	}

}





```


<br>



메모리|시간
--|--
50388 KB|548 ms