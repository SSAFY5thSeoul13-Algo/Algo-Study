## BOJ 1504 G4 특정한 최단 경로
- 다익스트라
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1504

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.


#### 입력
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1)

#### 출력
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

###  💡 풀이

다익스트라 알고리즘을 활용하여 각 구간의 경로를 모두 구해서 2가지의 경로가 있는지를 확인하였다

```java
	static int dijkstra(int start, int end) {
		Arrays.fill(distance, INF);
		Arrays.fill(isVisited, false);
		distance[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(isVisited[node.index])	continue;
			
			isVisited[node.index] = true;
			
			for (Node nextNode : list.get(node.index)) {
				if(!isVisited[nextNode.index] && distance[nextNode.index] > nextNode.dis + node.dis) {
					distance[nextNode.index] = nextNode.dis + node.dis;
					pq.offer(new Node(nextNode.index, distance[nextNode.index]));
				}
				
			}
		}
		
		return distance[end];
	}
```

출발지에서 각 경우지까지의 거리 `startToOne`,`startToTwo`
두 경유지 사이의 거리 `pointToPoint`
각 경유지에서 목적지까지의 거리 `OneToEnd`,`TwoToEnd`

를 각각 구하고 2개의 경로의 거리를 `result1`과 `result2`에 저장하였다  

각 위치에 숫자를 저장하고 `minDp`의 값은 최대값으로 초기화한다

```java
		//1 -> 경유지1
		int startToOne = dijkstra(1, pointOne);
		//1 -> 경유지2
		int startToTwo = dijkstra(1, pointTwo);
		//경유지1 <-> 경유지2
		int pointToPoint = dijkstra(pointOne, pointTwo);
		//경유지1 -> 목적지
		int oneToEnd = dijkstra(pointOne, N);
		//경유지2 -> 목적지
		int twoToEnd = dijkstra(pointTwo, N);
		
		//경로1
		int result1 = startToOne + pointToPoint + twoToEnd;
		//경로2
		int result2 = startToTwo + pointToPoint + oneToEnd;
```

`result1`과 `result2` 모두 존재하지 않는 경로인 경우는 -1, 존재하는 경로인 경우에는 더 작은 값을 출력하였다

```java
		//경로가 없는 경우
		if(result1 >= INF && result2 >= INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Math.min(result1, result2));
```




<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_G4_특정한최단경로 {
	static int N, E, pointOne, pointTwo;
	static final int INF = 200000*1000+1;
	static int[] distance;
	static boolean[] isVisited;
	static List<List<Node>> list;
	
	static class Node implements Comparable<Node>{
		int index;
		int dis;
		
		public Node(int index, int dis) {
			super();
			this.index = index;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		distance = new int[N+1];
		isVisited = new boolean[N+1];
		list = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new Node(to, dis));
			list.get(to).add(new Node(from, dis));
		}
		
		st = new StringTokenizer(br.readLine());
		
		pointOne = Integer.parseInt(st.nextToken());
		pointTwo = Integer.parseInt(st.nextToken());
		
		//1 -> 경유지1
		int startToOne = dijkstra(1, pointOne);
		//1 -> 경유지2
		int startToTwo = dijkstra(1, pointTwo);
		//경유지1 <-> 경유지2
		int pointToPoint = dijkstra(pointOne, pointTwo);
		//경유지1 -> 목적지
		int oneToEnd = dijkstra(pointOne, N);
		//경유지2 -> 목적지
		int twoToEnd = dijkstra(pointTwo, N);
		
		//경로1
		int result1 = startToOne + pointToPoint + twoToEnd;
		//경로2
		int result2 = startToTwo + pointToPoint + oneToEnd;
		
		//경로가 없는 경우
		if(result1 >= INF && result2 >= INF) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(Math.min(result1, result2));
	}
	
	static int dijkstra(int start, int end) {
		Arrays.fill(distance, INF);
		Arrays.fill(isVisited, false);
		distance[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(isVisited[node.index])	continue;
			
			isVisited[node.index] = true;
			
			for (Node nextNode : list.get(node.index)) {
				if(!isVisited[nextNode.index] && distance[nextNode.index] > nextNode.dis + node.dis) {
					distance[nextNode.index] = nextNode.dis + node.dis;
					pq.offer(new Node(nextNode.index, distance[nextNode.index]));
				}
				
			}
		}
		
		return distance[end];
	}

}





```


<br>



메모리|시간
--|--
68136 KB|780 ms